package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.umake.dao.UserDao;
import br.com.umake.model.User;

@Resource
@Path("/administracao")
public class AdministracaoController {

	public AdministracaoController(){
		
	}
	
	@Get @Path("/login")
	public void formLogin( Result result ){
		
			result.include("usuario", new UserDao().getAllUsers());
	}
	
	@Post @Path("/login")
	public void login(){
		System.out.println("logar");
	}
	
	public void logout(){
		System.out.println("Sair");
	}
	
	@Path("/")
	public void index(){
		System.out.println("Inicial");
	}
}
