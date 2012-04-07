package br.com.umake.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.serialization.xstream.XStreamJSONPSerialization;
import br.com.caelum.vraptor.serialization.xstream.XStreamJSONSerialization;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.dao.UserAdmDao;
import br.com.umake.interceptor.UserAdmControl;
import br.com.umake.model.UserAdm;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

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
		
		this.result.include("user", this.userAdmDao.getUserAdm(user));
		
		this.result.forwardTo(this).formCreate();
				
	}

	
    @Get("adm/users/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void formCreate() {
    	
	}

    
	@Get("adm/users")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW}) } ) 
	public void list(){
						
	}

	@Path("adm/users/json")
	public void getAllUserAdmInJson(){
		
		System.out.println("entrou");
		this.result.use(Results.json()).from(this.userAdmDao.getAllUsersAdm()).serialize();	
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

		UserAdm oldUser = this.userAdmDao.getUserAdm(user);
		oldUser.setName(user.getName());
		oldUser.setEmail(user.getEmail());
		oldUser.setLogin(user.getLogin());
		oldUser.setPassword(user.getPassword());
		oldUser.setReceiveEmail(user.getReceiveEmail());
		oldUser.setUserBlock(user.getUserBlock());
		
		this.userAdmDao.editUserAdm(user);

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


}
