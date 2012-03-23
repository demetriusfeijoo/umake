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
import br.com.umake.permissions.Context;
import br.com.umake.permissions.Create;
import br.com.umake.permissions.Delete;
import br.com.umake.permissions.Edit;
import br.com.umake.permissions.View;


@Resource
@Path("/users")
@Context("user")
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
			System.out.println("login");
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

	}
	
    @Create
	@Post
	@Path("/create")
	public Boolean create(final User user) {
    	
    	this.userDao.insertUser(user);
    	
    	System.out.println("cadastrado com sucesso!");
    	
		return true;
	}
	
    @Get
	@Create
	@Path("/create") 
	public void formCreateUser() {

	}
	
	@Delete
	@Edit
	@View
	@Path("/delete")
	public Boolean delete(final User user){
		System.out.println("deletou");
		return true;
	}

}
