package br.com.umake.model;


public class Application{

	private static Application applicationInstance = null;
	
	private Application(){
				
	}

	
	public void init(){
		System.out.println("Inicializou");
	}

	public static Application getInstance() {
		
		if(Application.applicationInstance == null){
			
			Application.applicationInstance = new Application();
			Application.applicationInstance.init();
			
			return Application.applicationInstance; 
			
		}
		
		return Application.applicationInstance;
		
	}
}
