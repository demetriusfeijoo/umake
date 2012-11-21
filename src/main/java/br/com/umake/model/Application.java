package br.com.umake.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.umake.dao.ConfigDao;
import br.com.umake.dao.MenuDao;
import br.com.umake.dao.PageDao;


@Component
@RequestScoped
public class Application{
	
	private Page currentPage;
	private Template currentTemplate;
	private List<Page> allPages;
	private Page indexPage;
	private final ConfigManager configManager;
	private final ConfigDao configDao;
	private final PageDao pageDao;
	private final MenuDao menuDao;
	private final LogManager logManager;
	private final TemplateFactory templateFactory;
	private Menu menu;
	private static final String SLUG_CONFIG_CURRENT_TEMPLATE = "current-template";
	private static final String SLUG_CONFIG_SITE_URL = "site-url";
	private String siteUrl;
	public static final String APPLICATION_VERSION = "Umake Beta"; 
	
	public Application( ConfigManager configManager, ConfigDao configDao, PageDao pageDao, HttpServletRequest httpServletRequest, LogManager logManager, MenuDao menuDao){
		
		this.configManager = configManager;
		this.configDao = configDao;
		this.pageDao = pageDao;
		this.templateFactory = new TemplateFactory(httpServletRequest, logManager, this);
		this.logManager = logManager;
		this.menuDao = menuDao;
				
		this.configManager.setConfigs( this.configDao.getAll() );
		Config tempSiteUrl = this.configManager.searchConfigBy(Application.SLUG_CONFIG_SITE_URL);
		
		if(tempSiteUrl != null){
			this.siteUrl = tempSiteUrl.getValue();
		}
		
		this.allPages = this.pageDao.getAll();
		
		Page pageTemp = this.pageDao.getIndexPage();
		
		if(pageTemp != null && pageTemp.getStatus()){
		
			this.indexPage = pageTemp;
			
		}
		
		this.initTemplate(this.templateFactory);
		this.initMenu();
				
	}
	
	private void initTemplate(TemplateFactory templateFactory) {
		
		try{
			
			String currentTemplateName = this.getConfigManager().searchConfigBy(Application.SLUG_CONFIG_CURRENT_TEMPLATE).getValue();

			this.currentTemplate = templateFactory.getTemplate(currentTemplateName);
			
		}catch(NullPointerException nullPointerException){
			
			this.logManager.error("An error occurred while The Application was initializing. May the problem is with a Current Template. Please, Check It.", Application.class);
			
		}	
		
	}
	
	private void initMenu(){
		
		List<Menu> menusTemp = menuDao.getAllActivies();

		if( menusTemp.size() > 0 ){
			
			this.menu = menusTemp.get(0);	
			
		}else{
			
			this.menu = new Menu();
			
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
	
	public String getSiteUrl(){
		return this.siteUrl;
	}
	
	public Page getIndexPage(){
		return this.indexPage;
	}
	
}
