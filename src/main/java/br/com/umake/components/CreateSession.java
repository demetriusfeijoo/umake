package br.com.umake.components;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
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

}