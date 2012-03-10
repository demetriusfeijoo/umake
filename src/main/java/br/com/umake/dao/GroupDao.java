package br.com.umake.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.umake.model.Group;

public class GroupDao {
	
	AnnotationConfiguration configuration;
	Session session;
	
	public GroupDao(){
		
		configuration = new AnnotationConfiguration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
		
	}
	
	public Group getAllGroups(){
		
		Group allGroups = (Group) session.load(Group.class, new Long(10)); 
		
		return allGroups;
		
	}

}
