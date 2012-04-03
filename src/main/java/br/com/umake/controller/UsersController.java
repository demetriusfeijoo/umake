package br.com.umake.controller;

import org.hibernate.Session;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.umake.dao.UserDao;
import br.com.umake.interceptor.UserControl;
import br.com.umake.model.User;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;


@Resource
public class UsersController { 

	private UserControl userControl;
	private UserDao userDao;
	private Result result;
	private Validator validator;
	private Session session;
	
	public UsersController(UserControl userControl, UserDao userDao, Result result, Validator validator, Session session) {
	
		this.userControl = userControl;
		this.userDao = userDao;
		this.result = result;
		this.validator = validator;
		this.session = session;
		
	}
	

	@Get
	@Path("adm/users/login")
	public void formLogin() {
		
		if( this.userControl.isLogged() ) 
			this.result.redirectTo(AdministrationController.class).index();
			
	}

	@Post
	@Path("adm/users/login")
	public void login(final User user) { // falta validar usuário pelo servidor.
		
		User recovery = this.userDao.findUser(user);
		
		if (recovery == null) {

			validator.add(new ValidationMessage("Login e/ou senha inválidos",""));

		}
		
		this.userControl.login(recovery);

		validator.onErrorUsePageOf(this).formLogin();
		
		result.redirectTo(AdministrationController.class).index();

	}

	@Path("adm/users/logout")
	@Restrictable
	public void logout() {
		
		this.userControl.logout();
		this.result.redirectTo(this).formLogin();

	}
	
    @Get("adm/users/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void formCreate() {
    	
	}
	
	@Get("adm/users")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void getAllUsers(){
		
		System.out.println("Listando todos os usuários");
		
	}
	
	@Post("adm/users")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.CREATE}) }) 
	public Boolean create(final User user) {
    	
    	if(this.userDao.insertUser(user)){
    		// ERRADOOOOOOOOOOOOOOOO AINDA mostra que foi inserido e joga para listagem de usuário
    		this.result.redirectTo(UsersController.class).formCreate();
    	}else{
    		this.result.redirectTo(UsersController.class).formCreate();
    	}
    	    	
		return true;
	}
	
	@Get("adm/users/{user.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void getUser( User user ){
		
		this.result.include("user", this.userDao.getUser(user));
		
		this.result.forwardTo(this).formCreate();
				
	}
	
	@Put("adm/users/{user.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.EDIT})}) 
	public void editUser(User user){

		User oldUser = this.userDao.getUser(user);
		oldUser.setName(user.getName());
		oldUser.setEmail(user.getEmail());
		oldUser.setLogin(user.getLogin());
		oldUser.setPassword(user.getPassword());
		oldUser.setReceiveEmail(user.getReceiveEmail());
		oldUser.setUserBlock(user.getUserBlock());
		
		this.userDao.editUser(user);

		this.result.include("user", this.userDao.getUser(user));
		
		this.result.forwardTo(this).formCreate();		
		
	}
	
    @Delete("adm/users/{user.id}")
    @Restrictable(permissions={@PermissionAnnotation(context="USER", permissionsTypes={ PermissionType.DELETE} )})
	public Boolean delete(final User user){
		System.out.println("deletou");
		return true;
	}
    
    @Path("atual")
    public void atualizaUser(){
    	
    	User user1 = new User();
    	user1.setId((long) 5);
    	
    	System.out.println("Atualiza");
    	
    	User userTemp = this.userDao.getUser( this.userControl.getUser() );
    	
    	this.session.refresh(userTemp);
    	
    }

}
