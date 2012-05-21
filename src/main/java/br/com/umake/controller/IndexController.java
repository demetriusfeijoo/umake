package br.com.umake.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.umake.dao.PageDao;
import br.com.umake.model.Application;
import br.com.umake.model.Config;
import br.com.umake.model.Page;

@Resource
public class IndexController {
	
	private Result result;
	private Application application;
	private PageDao pageDao;
	
	public IndexController(Result result, Application application, PageDao pageDao){
	
		this.result = result;
		this.application = application;
		this.pageDao = pageDao;
		
	}

	@Get("/")
	public void index(){
				
		this.initJspDependencies();

		this.result.forwardTo(this.application.getCurrentTemplate().getPathIndex());		
		
	}
	
	@Get("/{slug}")
	public void page(String slug){
		
		Page currentPage = this.pageDao.get("slug", slug);
		
		if( currentPage == null ){
			
			this.result.notFound();
			
			
		}else{
			
			if( !currentPage.getStatus() ){
				
				this.result.notFound();
				
			}else{

				this.initJspDependencies(currentPage);
				
				this.result.forwardTo(this.application.getCurrentTemplate().getPathPage());	
				
			}			
			
		}
		
	}
	
	private void initJspDependencies(){
		
		Config siteTitle = this.application.getConfigManager().searchConfigBy("site-title");
		Config siteEmail = this.application.getConfigManager().searchConfigBy("site-email");
		Config siteDescription = this.application.getConfigManager().searchConfigBy("site-description");
		Config siteKeywords = this.application.getConfigManager().searchConfigBy("site-keywords");
		
		this.result.include("UApplication", this.application);
		this.result.include("currentPage", this.application.getCurrentPage());
		this.result.include("templatePathFolder", this.application.getCurrentTemplate().getPathFolder());
		this.result.include("cssPaths", this.application.getCurrentTemplate().getAllCssPaths() );
		this.result.include("siteTitle", ( siteTitle != null ? siteTitle.getValue() : "" ) );
		this.result.include("siteEmail", ( siteEmail != null ? siteEmail.getValue() : "" ) );
		this.result.include("siteDescription", ( siteDescription != null ? siteDescription.getValue() : "" ) );
		this.result.include("siteKeywords", ( siteKeywords != null ? siteKeywords.getValue() : "" ) );
		this.result.include("allowedAccess", true);		
		
	}

	private void initJspDependencies(Page currentPage){

		this.application.setCurrentPage(currentPage);
		
		this.initJspDependencies();
		
	}
}
