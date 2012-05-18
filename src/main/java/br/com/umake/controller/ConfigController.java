package br.com.umake.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.dao.ConfigDao;
import br.com.umake.helper.flexigrid.FlexiGridJson;
import br.com.umake.helper.utils.TextHelper;
import br.com.umake.model.Config;
import br.com.umake.model.Page;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
@RequestScoped
public class ConfigController {

	private final Result result;
	private final ConfigDao configDao;
	
	public ConfigController(Result result, ConfigDao configDao){
		
		this.result = result;
		this.configDao = configDao;
		
	}
	
	@Get("adm/config")
	@Restrictable(permissions={@PermissionAnnotation(context="ADM_CONFIG", permissionsTypes = { PermissionType.VIEW})}) 
	public void formConfig(){
		
		this.result.include("config", this.configDao.getAll());
		
	}
	
	@Post("adm/config")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_CONFIG", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(Config config) {
		
		this.result.include("retorno", this.configDao.insert(config) );
		this.result.include("tipoSubmit", "cadastrada" );		
		this.result.include("config", config);
		
		this.result.redirectTo(this).formConfig();
		
	}
	
	@Put("adm/config")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_CONFIG", permissionsTypes = { PermissionType.EDIT})}) 
	public void edit(final Config config){
				
		this.result.include("retorno", this.configDao.edit(config) );
		this.result.include("tipoSubmit", "editada" );
		
		this.result.forwardTo(this).formConfig();		
		
	}    
	
}
