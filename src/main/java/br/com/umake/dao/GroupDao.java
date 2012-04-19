package br.com.umake.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.Group;

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

			//this.session.createSQLQuery("INSERT INTO umake_groups(name, dateOfRegistration, parentGroup) VALUES('opera','2012-03-10 12:12:12', 1)").executeUpdate();
			this.session.saveOrUpdate(group);

		}catch(HibernateException e){
			System.out.println(e.getCause());
			return false;
			
		}

		return true;
		
	}
	
	public Group getGroup(Group group){
		
		Group groupLoaded = (Group) this.session.load( Group.class , new Long(group.getId()) );
		return groupLoaded;
		
	}
	
	public Group getParentGroup(Long id){
		
		Group parentGroup = null;
		
		try{
			
			parentGroup = (Group) this.session.load(Group.class, new Long(id));
			
		}catch(HibernateException e ){
			
			System.out.println(e.getCause());
			
			return null;
		}
		
		return parentGroup;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroups(){
		
		Criteria allGroups = this.session.createCriteria(Group.class);
		List<Group> groupList = null;
		
		try{
			groupList = allGroups.list();
		}catch(HibernateException e){
			System.out.println(e.getCause());
		}
		
		return groupList;
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
