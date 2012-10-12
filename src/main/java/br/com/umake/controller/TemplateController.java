package br.com.umake.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.IOFileUploadException;

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
	
	public TemplateController(Result result, Application application){
		
		this.result = result;
		this.application = application;
		
	}
	
	@Get("adm/template/editCss")
	public void editCss(){
				
		this.result.include("cssFile", this.application.getCurrentTemplate().getCssFiles().get(0).getContent());
	
	}
	
	@Put("adm/template/saveCss")
	public void saveCss( String cssContent ){
		
		String contentCss = this.application.getCurrentTemplate().getCssFiles().get(0).replaceContent(cssContent);
		
		this.application.getCurrentTemplate().getCssFiles().get(0).setContent(contentCss);
		File css = new File("C:\\Users\\Jonathan\\Documents\\apache-tomcat-7.0.26\\wtpwebapps\\umake\\templates\\sky\\css\\style.css");

		try{
			
		PrintWriter gravador = new PrintWriter(css);

		gravador.print(this.application.getCurrentTemplate().getCssFiles().get(0).getContent());

		gravador.close();

		}catch(FileNotFoundException e){
			
		}	
		
		this.result.redirectTo(this).editCss();
		
	}
	
}
