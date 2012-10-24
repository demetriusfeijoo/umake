package br.com.umake.model;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Template {

	private String name;
	private String author;
	private String authorEmail;
	private String authorWebsite;
	private String description;
	private String indexFileName;
	private String pageFileName;
	private List<Template.Css> cssFiles = new ArrayList<Template.Css>();
	private String templateUrl;
	private String templatePath;
	public final static String TEMPLATES_FOLDER_NAME = "/templates";
	public final static String TEMPLATE_FILE_NAME = "template.xml";
	
	public Template(){
		
	}
	
	public String toString(){
		
		return this.getName();
		
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getAuthorWebsite() {
		return authorWebsite;
	}

	public void setAuthorWebsite(String authorWebsite) {
		this.authorWebsite = authorWebsite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIndexFileName() {
		return indexFileName;
	}

	public void setIndexFileName(String indexFileName) {
		this.indexFileName = indexFileName;
	}

	public String getPageFileName() {
		return pageFileName;
	}

	public void setPageFileName(String pageFileName) {
		this.pageFileName = pageFileName;
	}

	public List<Css> getCssFiles() {
		return cssFiles;
	}

	public void setCssFiles(List<Css> cssFiles) {
		this.cssFiles = cssFiles;
	}
	
	public void setTemplateUrl(String templateUrl){
		
		this.templateUrl = templateUrl;
		
	}
	
	public String getTemplateUrl(){
		
		return this.templateUrl;
		
	}
		
	public String getIndexPath(){
	
		return Template.TEMPLATES_FOLDER_NAME+"/"+this.getName()+"/"+this.getIndexFileName();
		
	}
	
	public String getPagePath(){
		
		return Template.TEMPLATES_FOLDER_NAME+"/"+this.getName()+"/"+this.getPageFileName();
		
	}
	
	public void setTemplatePath(String templatePath){
		
		this.templatePath = templatePath;
		
	}
	
	public String getTemplatePath(){
		
		return this.templatePath;
		
	}
	
	public static class Css{
		
		private String fileName;
		private String media;
		private String content;
		
		public String toString(){
			
			return fileName;
			
		}

		public String getFileName() {
			
			return fileName;
			
		}

		public void setFileName(String fileName) {
			
			this.fileName = fileName;
			
		}

		public String getMedia() {
			
			return media;
			
		}

		public void setMedia(String media) {
			
			this.media = media;
			
		}
		
		public void setContent(String content){
			
			this.content = content;
			
		}
		
		public String getContent(){
			
			return this.content;
			
		}
		
		public String replaceContent( String css ){
			
			return css.trim().replace("{", "{\n").replace("*/", "*/\n").replace("}", "}\n").replace(";", ";\n");
			
		}
		
		
	}
}