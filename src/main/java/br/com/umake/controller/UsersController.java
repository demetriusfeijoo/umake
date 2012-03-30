package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
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
@Path("/users")
public class UsersController { 

	private UserControl userControl;
	private UserDao userDao;
	private Result result;
	private Validator validator;
	
	public UsersController(UserControl userControl, UserDao userDao, Result result, Validator validator) {
	
		this.userControl = userControl;
		this.userDao = userDao;
		this.result = result;
		this.validator = validator;
		
	}
	

	@Get
	@Path("/login")
	public void formLogin() {
		
		if( this.userControl.isLogged() ) 
			this.result.redirectTo(AdministrationController.class).index();
			
	}

	@Post
	@Path("/login")
	public void login(final User user) { // falta validar usuário pelo servidor.
		
		User recovery = this.userDao.findUser(user);
		
		if (recovery == null) {

			validator.add(new ValidationMessage("Login e/ou senha inválidos",""));

		}
		
		this.userControl.login(recovery);

		validator.onErrorUsePageOf(this).formLogin();
		
		result.redirectTo(AdministrationController.class).index();

	}

	public void logout() {
		
		this.userControl.logout();
		this.result.redirectTo(this).formLogin();

	}
	
	@Post
	@Path("/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.CREATE}) }) 
	public Boolean create(final User user) {
    	
    	if(this.userDao.insertUser(user)){
    		// ERRADOOOOOOOOOOOOOOOO AINDA mostra que foi inserido e joga para listagem de usuário
    		this.result.redirectTo(UsersController.class).create();
    	}else{
    		this.result.redirectTo(UsersController.class).create();
    	}
    	    	
		return true;
	}
	
    @Get
	@Path("/create") 
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void create() {
    	
	}
	
	@Path("/delete")
	public Boolean delete(final User user){
		System.out.println("deletou");
		return true;
	}

}
