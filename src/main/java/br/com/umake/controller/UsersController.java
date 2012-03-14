package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.umake.dao.UserDao;
import br.com.umake.interceptor.UserControl;
import br.com.umake.model.User;

@Resource
@Path("/users")
public class UsersController {

	private UserControl userControl;
	private UserDao userDao;
	private Result result;
	
	public UsersController( UserControl userControl, UserDao userDao, Result result ){
	
		this.userControl = userControl;
		this.userDao = userDao;
		this.result = result;
	}
	
	@Get @Path("/login")
	public void formLogin(){
				
	}
	
	@Post @Path("/login")
	public void login( final User user ){ // falta validar usuário pelo servidor.
		
		User recovery = this.userDao.findUser( user );
		System.out.println(recovery.getName());
		this.userControl.login(recovery);
		
		result.redirectTo(AdministrationController.class).index();
		
	}
	
	public void logout(){
		
		this.userControl.logout();
		
	}
	
}
