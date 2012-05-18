package br.com.umake.model;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.dao.PageDao;


@Component
public class Application{

	private PageDao pageDao;
	private List<Page> pages;
	
	public Application(PageDao pageDao){
		
		this.pageDao = pageDao;
		
		this.pages = this.pageDao.getAll();
		
	}
	
	public List<Page> getPages(){
		
		return this.pages;
		
	}
	
	/**
	 * This method return the current page. If you want to get a specific page you can use the overload method getPage( String slug ).
	 * 
	 * */
	public Page getPage(){
		
		return null;
		
	}
	
	public Page getPage(String slug) { 
	
		Page pageTemp = null;
		
		for (Page page : this.pages) {
		
			if( page.getSlug().equals(slug)){
				
				pageTemp = page;
				break;
				
			}
			
		}
		
		if(pageTemp == null ){
		
			
			
		}
		
		return pageTemp;
									
	}
	
}
