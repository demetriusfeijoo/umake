package br.com.umake.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.umake.model.Application;
import br.com.umake.model.Template;
import br.com.umake.model.Template.Css;

@Resource
@RequestScoped
public class TemplateController {

	private final Result result;
	private Application application;
	
	public TemplateController(Result result, Application application){
		
		this.result = result;
		this.application = application;
		
	}
	
	@Get("adm/template/editCss")
	public void editCss(){
				
		this.result.include("cssFile", this.application.getCurrentTemplate().getCssFiles().get(0).replaceContent(this.application.getCurrentTemplate().getCssFiles().get(0).getContent()));
	
	}
	
	@Put("adm/template/saveCss")
	public void saveCss( String cssContent ) throws IOException{
		
		String contentCss = this.application.getCurrentTemplate().getCssFiles().get(0).replaceContent(cssContent);
		
		this.application.getCurrentTemplate().getCssFiles().get(0).setContent(contentCss);
		File css = new File("C:\\Users\\Jonathan\\Documents\\apache-tomcat-7.0.26\\wtpwebapps\\umake\\templates\\sky\\css\\style.css");
		css.setWritable(true);
		try{
					
		FileOutputStream fos = new FileOutputStream(css);
		
		fos.write(this.application.getCurrentTemplate().getCssFiles().get(0).getContent().getBytes());
		fos.close();

		}catch(FileNotFoundException e){
			
		}	
		
		this.application = null;
		this.result.redirectTo(TemplateController.class).editCss();
		
	}
	
}
