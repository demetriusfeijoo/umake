package br.com.umake.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class CreateSession implements ComponentFactory<Session>{
	
	private final SessionFactory factory;
	private Session session;
	
	public CreateSession( SessionFactory factory ){
		
		this.factory = factory;
		
	}

	public Session getInstance() {
				
		return this.session;
		
	}
	
	@PostConstruct
	public void openSession(){
		
		this.session = factory.openSession();
		
	}
	
	@PreDestroy
	public void closeSession(){
	
		this.session.close();
	}

}
