package br.com.umake.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.dao.UserAdmDao;
import br.com.umake.helper.FlexiGridJson;
import br.com.umake.interceptor.UserAdmControl;
import br.com.umake.model.UserAdm;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class UsersAdmController { 

	private UserAdmControl userAdmControl;
	private UserAdmDao userAdmDao;
	private Result result;
	private Validator validator;
	
	public UsersAdmController(UserAdmControl userControl, UserAdmDao userDao, Result result, Validator validator) {
	
		this.userAdmControl = userControl;
		this.userAdmDao = userDao;
		this.result = result;
		this.validator = validator;
		
	}
	
	@Get
	@Path("adm/users/login")
	public void formLogin() {
		
		if( this.userAdmControl.isLogged() ) 
			this.result.redirectTo(AdministrationController.class).index();
			
	}
    
	@Post
	@Path("adm/users/login")
	public void login(final UserAdm user) { // falta validar usuário pelo servidor.
		
		UserAdm recovery = this.userAdmDao.findUserAdm(user);
		
		if (recovery == null) {

			validator.add(new ValidationMessage("Login e/ou senha inválidos",""));

		}
		
		this.userAdmControl.login(recovery);

		validator.onErrorUsePageOf(this).formLogin();
		
		result.redirectTo(AdministrationController.class).index();

	}

	@Get("adm/users/logout")
	@Restrictable
	public void logout() {
		
		this.userAdmControl.logout();
		this.result.redirectTo(this).formLogin();

	}
	
	
    
	@Get("adm/users/{user.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void getUserAdm( UserAdm user ){
		
		UserAdm userRecovered = this.userAdmDao.getUserAdm(user);
		
		if( userRecovered.getId() != null ){
			
			this.result.include("user", userRecovered);
			
			this.result.forwardTo(this).formCreate();	
			
		}else{
			
			this.result.redirectTo(this).list();
			
		}

				
	}

	
    @Get("adm/users/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void formCreate() {
    	
	}

	@Get("adm/users")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW}) } ) 
	public void list(){
						
	}

	@Post("adm/users")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(final UserAdm user) {
    	
    	if(this.userAdmDao.insertUserAdm(user)){
    		
    		this.result.include("user", this.userAdmDao.getUserAdm(user));
    	
    	}
    	 
		this.result.redirectTo(UsersAdmController.class).formCreate();

	}
	
	@Put("adm/users")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.EDIT})}) 
	public void editUserAdm(UserAdm user){

		UserAdm newUser = this.userAdmDao.getUserAdm(user);
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setLogin(user.getLogin());
		newUser.setPassword(user.getPassword());
		newUser.setReceiveEmail(user.getReceiveEmail());
		newUser.setUserBlock(user.getUserBlock());
		
		this.userAdmDao.editUserAdm(newUser);

		this.result.include("user", this.userAdmDao.getUserAdm(user));
		
		this.result.forwardTo(this).formCreate();		
		
	}
	
    @Delete("adm/users")
    @Restrictable(permissions={@PermissionAnnotation(context="USER", permissionsTypes={ PermissionType.DELETE} )})
	public Boolean delete(final UserAdm user){

    	if(this.userAdmDao.deleteUserAdm(user)){
    		
        	this.result.forwardTo(this).list();
    		
    	}else{
    		
    		this.result.include("user", user);
        	this.result.forwardTo(this).formCreate();
    		
    	}
    	
    	return true;
    	
	}

	@Path("adm/users/flexiJson")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void getAllUserAdmInFlexiJson(Integer page, Integer rp, String sortname, String sortorder){
		
		FlexiGridJson<UserAdm> flexi = null;
		
		try {
			
			Integer offset = page == 1 ? 0 : (page * rp) - rp;
			List<UserAdm> flexiListUser = this.userAdmDao.getUsersLimited( offset , rp, sortname, sortorder );
			flexi = new FlexiGridJson<UserAdm>(page, 7, flexiListUser );
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		this.result.use(Results.json()).withoutRoot().from(flexi).recursive().serialize();
		
	}

}
