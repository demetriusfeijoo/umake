package br.com.umake.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
	
	public Template createTemplate(String templateName){
		
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
	
}
