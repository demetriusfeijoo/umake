package br.com.umake.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.umake.dao.ConfigDao;
import br.com.umake.model.Config;
import br.com.umake.model.LogManager;
import br.com.umake.model.TemplateFactory;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;


@Resource
public class SettingsController {

	private final Result result;
	private final ConfigDao configDao;
	private final HttpServletRequest httpServletRequest;
	private final TemplateFactory templateFactory;
	private final LogManager logManager;
	private static final String []configsSlugs = new String[]{"site-title", "site-email", "site-description", "site-keywords"};
	private static final String slugCurrentTemplate = "current-template";
	
	public SettingsController(Result result, ConfigDao configDao, HttpServletRequest httpServletRequest, TemplateFactory templateFactory, LogManager logManager){
		
		this.result = result;
		this.configDao = configDao;
		this.httpServletRequest = httpServletRequest;
		this.templateFactory = templateFactory;
		this.logManager = logManager;
		
	}
	
	@Get("adm/settings")
	@Restrictable(permissions={@PermissionAnnotation(context="ADM_SETTINGS", permissionsTypes = { PermissionType.VIEW})}) 
	public void formSettings(){
		
		this.result.include("configs", this.configDao.getConfigsBySlugs(SettingsController.configsSlugs));
		this.result.include("templates", this.templateFactory.getAllTemplates());
		
		try{
			
			this.result.include("currentTemplate", this.configDao.getConfigsBySlugs( SettingsController.slugCurrentTemplate).get(0));
			
		}catch(NullPointerException nullPointerException){
			
			this.logManager.error("The current-template's config doesn't exist", SettingsController.class);
			
		}

	}
	
	@Put("adm/settings")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_SETTINGS", permissionsTypes = { PermissionType.EDIT})}) 
	public void edit(){
				
		List<Config> configs = new ArrayList<Config>();
		
		/* Recovery the configs setted manually*/
		Config currentTemplateConfig = new Config();
		currentTemplateConfig.setSlug(SettingsController.slugCurrentTemplate);
		currentTemplateConfig.setValue(httpServletRequest.getParameter(SettingsController.slugCurrentTemplate));
		configs.add(currentTemplateConfig);		
		
		/* Recovery the configs setted automatically*/
		for (String configSlug : SettingsController.configsSlugs) {
		
			Config config = new Config();
			config.setSlug(configSlug);
			config.setValue(httpServletRequest.getParameter(configSlug));
			configs.add(config);
			
		}		
		
		this.configDao.editBySlug(configs);
		
		this.result.include("retorno", true );
		this.result.include("tipoSubmit", "editada" );
		
		this.result.redirectTo(this).formSettings();		
		
	}    
	
}
