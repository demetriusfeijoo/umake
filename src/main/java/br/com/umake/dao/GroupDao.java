package br.com.umake.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.Group;

@Component
public class GroupDao {
	
	private final Session session;

	public GroupDao( Session session ) {
		
		this.session = session;

	}
	
	public Boolean insertGroup(Group group){
		
		group.setDateOfRegistration(new Date());

		try{

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
	
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroups(){
		
		return this.session.createCriteria(Group.class).list();

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
