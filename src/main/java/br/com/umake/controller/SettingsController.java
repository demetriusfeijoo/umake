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
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class SettingsController {

	private final Result result;
	private final ConfigDao configDao;
	private final HttpServletRequest httpServletRequest;
	private static final String []configsSlugs = new String[]{"site-title", "site-email", "site-description", "site-keywords"};
	
	public SettingsController(Result result, ConfigDao configDao, HttpServletRequest httpServletRequest){
		
		this.result = result;
		this.configDao = configDao;
		this.httpServletRequest = httpServletRequest;
		
	}
	
	@Get("adm/settings")
	@Restrictable(permissions={@PermissionAnnotation(context="ADM_SETTINGS", permissionsTypes = { PermissionType.VIEW})}) 
	public void formSettings(){
		
		this.result.include("configs", this.configDao.getConfigsBySlugs(SettingsController.configsSlugs));
		
	}
	
	@Put("adm/settings")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_SETTINGS", permissionsTypes = { PermissionType.EDIT})}) 
	public void edit(){
				
		List<Config> configs = new ArrayList<Config>();
		
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
