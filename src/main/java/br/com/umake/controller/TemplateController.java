package br.com.umake.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
import br.com.umake.dao.MenuDao;
import br.com.umake.helper.flexigrid.FlexiGridJson;
import br.com.umake.interceptor.AdmUserControl;
import br.com.umake.model.Application;
import br.com.umake.model.Menu;
import br.com.umake.model.Template;
import br.com.umake.model.Template.Css;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class TemplateController {

	private final Result result;
	private Application application;
	
	public TemplateController(Result result){
		
		this.result = result;
		//this.application = application;
		
	}
	
	@Get("adm/template/editCss")
	public void editCss(){
		
		//System.out.println(this.application.getCurrentTemplate().getCssFiles().get(0).getContent());
		
		this.result.include("cssFile", this.application.getCurrentTemplate().getCssFiles().get(0).getContent());
	
	}
	
	@Post("adm/template/save_css")
	public void saveCss( Template css ){
		
		
		
	}
	
}
