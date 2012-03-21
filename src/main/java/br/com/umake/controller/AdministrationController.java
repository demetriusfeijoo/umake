package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.umake.permissions.Context;
import br.com.umake.permissions.Create;
import br.com.umake.permissions.View;

@Resource
@Path("adm")
@Context("ADMINISTRATION")
public class AdministrationController {
	
	
	@Get @Path("/")
	@View
	public void index(){
	
		System.out.println("Index adm");
		
	}
	
	@Get @Path("/insert")
	@Create
	public void insert(){
		
		System.out.println("Insert adm");
		
	}
}
