package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.umake.permissions.Context;


@Resource
@Path("adm")
@Context
public class AdministrationController {
	
	
	@Get @Path("/")
	public void index(){
			
	}

}
