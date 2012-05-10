package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.model.Application;

@Resource
public class IndexController {
	
	private Result result;
	private Application application;
	
	public IndexController(Result result, Application application){
	
		this.result = result;
		this.application = application;
		
	}

	//@Get("/")
	public void index(){
		
		//this.result.forwardTo("/WEB-INF/jsp/index.jsp");
		this.result.use(Results.http()).body("inicial");

		
	}
	
	//@Get({"/{namePage}", "/{namePage}/{teste}"})
	public void page(String namePage, String teste ){
	
		this.result.use(Results.http()).body(namePage+" "+teste);
		
	}
	
}
