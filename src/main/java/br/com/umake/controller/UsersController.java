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
import br.com.umake.dao.UserDao;
import br.com.umake.interceptor.UserControl;
import br.com.umake.model.User;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

@Resource
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

	@Get("adm/users/logout")
	@Restrictable
	public void logout() {
		
		this.userControl.logout();
		this.result.redirectTo(this).formLogin();

	}
	
	
    
	@Get("adm/users/{user.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void getUser( User user ){
		
		this.result.include("user", this.userDao.getUser(user));
		
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
	//@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW}) } ) 
	public void getAllUserInJson(){
		
		//this.result.use(Results.http()).body("{\"page\":10,\"total\":10,\"rows\":[{\"id\":\"AF\",\"cell\":{\"id\": \"AF\" ,  \"name\":\"Afghanistan\"}}]}");  

		DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
		
		System.out.println( df.format(this.userDao.getAllUsers().get(0).getDateLastVisit()));
		
		GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls().setDateFormat("dd/MM/YYYY").create();
		gsonBuilder.
		//System.out.println( gson.toJson( userQlqr )) ;
		
		//FlexiGridJson flexiGridJson = new FlexiGridJson<User>(10, 10, this.userDao.getAllUsers()  );

		//this.result.use(Results.json()).from(flexiGridJson, "rows").exclude("login", "password", "email", "dateOfRegistration", "dateLastVisit", "receiveEmail", "userBlock").serialize();
		
	}	
	
	@Post("adm/users")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(final User user) {
    	
    	if(this.userDao.insertUser(user)){
    		
    		this.result.include("user", this.userDao.getUser(user));
    	
    	}
    	 
		this.result.redirectTo(UsersController.class).formCreate();

	}
	
	@Put("adm/users")
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
	
    @Delete("adm/users")
    @Restrictable(permissions={@PermissionAnnotation(context="USER", permissionsTypes={ PermissionType.DELETE} )})
	public Boolean delete(final User user){

    	if(this.userDao.deleteUser(user)){
    		
        	this.result.forwardTo(this).list();
    		
    	}else{
    		
    		this.result.include("user", user);
        	this.result.forwardTo(this).formCreate();
    		
    	}
    	
    	return true;
    	
	}


}
