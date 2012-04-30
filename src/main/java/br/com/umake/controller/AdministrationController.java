package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.umake.permissions.Restrictable;


@Resource
@Path("adm")
public class AdministrationController {
	
	
	@Get("")
	@Restrictable
	public void index(){
			
	}


}
