package br.com.umake.model;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.umake.dao.ConfigDao;
import br.com.umake.dao.PageDao;


@Component
@RequestScoped
public class Application{
	
	private Page currentPage;
	private Template currentTemplate;
	private List<Page> allPages;
	private final ConfigManager configManager;
	private final ConfigDao configDao;
	private final PageDao pageDao;
	private final LogManager logManager;
	private final TemplateFactory templateFactory;
	private final Menu menu;
	private static final String SLUG_CONFIG_CURRENT_TEMPLATE = "current-template";
	public static final String APPLICATION_VERSION = "Umake Beta"; 
	
	public Application( ConfigManager configManager, ConfigDao configDao, PageDao pageDao, TemplateFactory templateFactory, LogManager logManager, Menu menu ){
		
		this.configManager = configManager;
		this.configDao = configDao;
		this.pageDao = pageDao;
		this.templateFactory = templateFactory;
		this.logManager = logManager;
		this.menu = menu;
		
		this.configManager.setConfigs( this.configDao.getAll() );
		this.allPages = this.pageDao.getAll();
		
		//this.initMenu();
		this.initTemplate(this.templateFactory);
				
	}
	
	/*private void initMenu(){
		
		List<Menuable> menuElements = new ArrayList<Menuable>(1);
		
		Page pageHome = new Page();
		pageHome.setSlug("/");
		pageHome.setTitle("Home");
		pageHome.setOrdered(0);
		
		menuElements.add(pageHome);
		
		for (Page elementPage : this.allPages) {
		
			if(elementPage.getStatus()){
			
				menuElements.add(elementPage);
				
			}
			
		}
		
		this.menu.setMenuElements(menuElements);
		
	}*/
	
	private void initTemplate(TemplateFactory templateFactory) {
		
		try{
			
			String currentTemplateName = this.getConfigManager().searchConfigBy(Application.SLUG_CONFIG_CURRENT_TEMPLATE).getValue();
			System.out.println("aqui");
			this.currentTemplate = templateFactory.getTemplate(currentTemplateName);
			
		}catch(NullPointerException nullPointerException){
			
			this.logManager.error("An error occurred while The Application was initializing. May the problem is with a Current Template. Please, Check It.", Application.class);
			
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
	
	public TemplateFactory getTemplateFactory(){
		
		return this.templateFactory;
		
	}
	
	public ConfigManager getConfigManager(){
		
		return this.configManager;
		
	}
		
	public LogManager getLogManager(){
		
		return this.logManager;
		
	}
	
	public Menu getMenu(){
		
		return this.menu;
		
	}
	
}
