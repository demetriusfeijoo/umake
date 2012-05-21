package br.com.umake.model;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.dao.ConfigDao;
import br.com.umake.dao.PageDao;


@Component
public class Application{
	
	private Page currentPage;
	private Template currentTemplate;
	private List<Page> allPages;
	private final ConfigManager configManager;
	private final ConfigDao configDao;
	private final PageDao pageDao;
	private static final String SLUG_CONFIG_CURRENT_TEMPLATE = "current-template";
	public static final String APPLICATION_VERSION = "Umake Beta"; 
	
	public Application( ConfigManager configManager, ConfigDao configDao, PageDao pageDao, Template currentTemplate ){
		
		this.configManager = configManager;
		this.configDao = configDao;
		this.pageDao = pageDao;
		this.currentTemplate = currentTemplate;
		
		this.configManager.setConfigs( this.configDao.getAll() );
		this.allPages = this.pageDao.getAll();
		
		try{
			
			this.currentTemplate.setName(this.getConfigManager().searchConfigBy(Application.SLUG_CONFIG_CURRENT_TEMPLATE).getValue());	
			this.currentTemplate.init();

		}catch(NullPointerException nullPointerException){
			
			System.out.println(nullPointerException.getMessage());
			
		}catch(IllegalStateException illegalStateException){
			
			System.out.println(illegalStateException.getMessage());
			
		}
				
	}

	public Page getCurrentPage() {
		
		return currentPage;
		
	}

	public void setCurrentPage(Page page) {
		
		this.currentPage = page;
		
	}

	public Template getCurrentTemplate() {
		
		return currentTemplate;
		
	}

	public void setCurrentTemplate(Template template) {
		
		this.currentTemplate = template;
		
	}
	
	public List<Page> getAllPages(){
		
		return this.allPages;
		
	}
	
	public void setAllPages(List<Page> pages){
		
		this.allPages = pages;
		
	}
	
	public ConfigManager getConfigManager(){
		
		return this.configManager;
		
	}
		
}
