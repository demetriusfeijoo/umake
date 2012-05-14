package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.model.Application;
import br.com.umake.model.Page;

@Resource
public class IndexController {
	
	private Result result;
	private Application application;
	
	public IndexController(Result result, Application application){
	
		this.result = result;
		this.application = application;
		
	}

	@Get("/")
	public void index(String pageOrCategory, String complement ){
		
		//this.result.forwardTo("/WEB-INF/jsp/index.jsp");
		this.result.use(Results.http()).body(pageOrCategory+" - "+complement);

		
	}
	
	@Get("/{page.slug}")
	public void page(Page page){

		this.result.use(Results.http()).body(page.getSlug());
		
	}

}
