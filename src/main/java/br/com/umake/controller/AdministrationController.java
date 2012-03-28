package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.umake.permissions.Context;
import br.com.umake.permissions.View;


@Resource
@Path("adm")
@Context
public class AdministrationController {
	
	
	@Get @Path("/")
	@View
	public void index(){
	
		System.out.println("Index adm");
		
	}

}
