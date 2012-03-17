package br.com.umake.components;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class FactorySessionFactory implements ComponentFactory<SessionFactory> {

	private SessionFactory factory;

	public SessionFactory getInstance() {

		return this.factory;

	}

	@PostConstruct
	public void openFactory() {

		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();

		this.factory = configuration.buildSessionFactory();

	}

	public void closeFactory() {

		this.factory.close();

	}

}
