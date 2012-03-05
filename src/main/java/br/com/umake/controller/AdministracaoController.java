package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
@Path("/administracao")
public class AdministracaoController {

	public AdministracaoController(){
		
	}
	
	@Get @Path("/login")
	public void formLogin(){
		System.out.println("Logar");
	}
	
	
	public void login(){
		
	}
	
	public void logout(){
		System.out.println("Sair");
	}
	
	@Path("/")
	public void index(){
		System.out.println("Inicial");
	}
}
