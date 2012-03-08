package br.com.umake.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.umake.model.Teste;

public class UserDao {

	AnnotationConfiguration configuration;
	Session session;

	public UserDao() {

		configuration = new AnnotationConfiguration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
		
	}

	public Teste getAllUsers() {

		Teste user = (Teste) session.load(Teste.class, new Long(10)); 

		return user;

	}

}