package br.com.umake.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.Group;
import br.com.umake.model.UserAdm;

@Component
public class GroupDao {
	
	AnnotationConfiguration configuration;
	Session session;
	
	public GroupDao(){
		
		configuration = new AnnotationConfiguration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
		
	}
	
	public Boolean insertGroup(Group group){

		group.setDateOfRegistration(new Date());
		
		try{
			
			this.session.save(group);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	public Group getGroup(Group group){
		
		Group groupLoaded = (Group) this.session.load( Group.class , new Long(group.getId()) );
		return groupLoaded;
		
	}
	
	public Group getAllGroups(){
		
		Group allGroups = (Group) session.createQuery("FROM umake_groups");
		
		return allGroups;
		
	}
	
	public Boolean editGroup( Group group ){
		
		try{
			
			this.session.merge(group);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}
	
	public Boolean deleteGroup( Group group ){
		
		try{
			
			this.session.delete(group);

		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroup(){
		
		return this.session.createCriteria(Group.class).list();
		
	}

}
