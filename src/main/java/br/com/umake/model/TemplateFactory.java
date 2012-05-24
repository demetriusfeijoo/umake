package br.com.umake.model;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.Template.Css;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;

@Component
public class TemplateFactory {

	private LogManager logManager;
	private String templateFilePath;
	
	@SuppressWarnings({"deprecation" })
	public TemplateFactory(HttpServletRequest httpServletRequest, LogManager logManager){
			
		this.logManager = logManager;
		this.templateFilePath = httpServletRequest.getRealPath(Template.TEMPLATES_FOLDER_NAME);
		
	}
	
	public Template getTemplate(String templateName){
		
		XStream xstream;
		File file;
		InputStream inputStream;
		Template template = null;
		String filePath = this.templateFilePath+"/"+templateName+"/"+Template.TEMPLATE_FILE_NAME;
		
		try {

			file = new File(filePath);
			inputStream = new FileInputStream(file);

			xstream = new XStream(new DomDriver());
			xstream.alias("template", Template.class);
			xstream.alias("cssFiles", List.class);
			xstream.alias("css", Css.class);
			
			template = (Template) xstream.fromXML(inputStream);
			
			return template;
			
		} catch (FileNotFoundException e) {
			
			this.logManager.error("Template file not exists in "+filePath, TemplateFactory.class);
			
		} catch (CannotResolveClassException e) {
			
			this.logManager.error("Template file have an error. Please check if the file"+filePath, TemplateFactory.class);
			
		}				
		
		return template;
		
	}
	
	
	public List<Template> getAllTemplates(){
	
		File file = new File(this.templateFilePath);
		
		List<Template> templates = null;
		
		try{
			
			if(file.exists() && file.isDirectory()){
			
				templates =  new ArrayList<Template>(1);
				
				File[] directories = file.listFiles(new FileFilter() {
					
					public boolean accept(File pathname) {
						
						return pathname.isDirectory();
						
					}
				});
				
				for (File dir : directories) {
				
					Template templateTemp = this.getTemplate(dir.getName());
					
					if( templateTemp != null ){
					
						templates.add(templateTemp);
						
					}
					
				}
				
				
			}else{
				
				throw new FileNotFoundException();
				
			}
			
		}catch(FileNotFoundException fileNotFoundException){
		
			this.logManager.error("May the template's folder doesn't exist", TemplateFactory.class);
			
		}

		return templates;
		
	}
}
