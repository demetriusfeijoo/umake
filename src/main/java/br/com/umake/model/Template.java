package br.com.umake.model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Template {

	private String name;
	private static final String PATH_INDEX_FILE_NAME = "index.jsp";
	private static final String PATH_PAGE_FILE_NAME = "page.jsp";
	private static final String PATH_TEMPLATE_FOLDER_NAME = "/themes/";
	private static final String []PATHS_CSS_FILES_NAMES = {"css/style.css"};

	public Template(){
		
	}

	/**
	 * Start a template with template configurations. Need to have a name setted name.
	 * @param templateConfig <span>A Config with data of the current template</span>
	 * @throws IllegalStateException
	 * 
	 */
	public void init(Config templateConfig){
	
		String templateName = templateConfig.getValue();
		this.init(templateName);
		
	}

	/**
	 * Start a template with template configurations. Need to have a name setted name.
	 * @param templateName <span>The same name of the template's name</span>
	 * @throws IllegalStateException
	 * 
	 */
	public void init(String templateName){
	
		//Load 
		
	}
	
	/**
	 * Start a template with template configurations. Need to have a name setted name.
	 * @throws IllegalStateException
	 * 
	 */
	public void init() throws IllegalStateException{
		
		if( this.getName().isEmpty() ){
			
			throw new IllegalStateException("A template need to have a name. Please set it!");
			
		}else{
			
			this.init(this.getName());
			
		}
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getPathFolder(){
	
		return Template.PATH_TEMPLATE_FOLDER_NAME+this.getName(); 
		
	}
	
	public String getPathIndex(){
				
		return this.getPathFolder()+"/"+this.getPathIndexFileName();
		
	}
	
	public String getPathPage(){
		
		return this.getPathFolder()+"/"+this.getPathPageFileName();
		
	}
	
	public String[] getAllCssPaths(){
	
		String[] returnAllPaths = new String[this.getPathCssFilesNames().length];
		
		int count = 0;
		
		for (String cssPath : this.getPathCssFilesNames()) {
		
			returnAllPaths[count] = this.getPathFolder()+"/"+cssPath;
			count++;
			
		}
		
		return returnAllPaths;
		
	}
	
	public String getPathIndexFileName(){
		
		return Template.PATH_INDEX_FILE_NAME;
		
	}
	
	public String getPathPageFileName(){
		
		return Template.PATH_PAGE_FILE_NAME;		
		
	}
	
	public String[] getPathCssFilesNames(){
		
		return Template.PATHS_CSS_FILES_NAMES;
		
	}
	
}
