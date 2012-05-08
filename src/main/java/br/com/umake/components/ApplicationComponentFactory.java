package br.com.umake.components;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.umake.model.Application;

@Component
@ApplicationScoped
public class ApplicationComponentFactory implements ComponentFactory<Application>  {

	private Application applicationComponentFactory;
	
	public ApplicationComponentFactory(){
		
		this.applicationComponentFactory = Application.getInstance();
		
	}
	
	public Application getInstance() {
		
		return this.applicationComponentFactory;
		
	}

}
