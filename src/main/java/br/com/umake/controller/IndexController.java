package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.umake.model.Application;

@Resource
public class IndexController {
	
	private Result result;
	private Application application;
	
	public IndexController(Result result, Application application){
	
		this.result = result;
		this.application = application;
		
	}

	@Get("")
	public void index(Application app){
		
		this.result.forwardTo("/WEB-INF/jsp/index.jsp");
		
	}
	
}
