package br.com.umake.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.umake.permissions.Restrictable;


@Resource
@Path("adm")
public class AdmController {
	
	
	@Path(value="", priority=Path.HIGH)
	@Restrictable
	public void index(){
			
	}


}
