package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.umake.permissions.Context;
import br.com.umake.permissions.Create;
import br.com.umake.permissions.View;

@Resource
@Path("adm")
@Context("SYSTEM")
public class AdministrationController {
	
	
	@Get @Path("/")
	@View
	@Create
	public void index(){
	
		
		
	}
	
}
