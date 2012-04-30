package br.com.umake.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.AdmPermission;

@Component
public class AdmPermissionDao {
	
	private final Session session;
	
	public AdmPermissionDao(Session session){
		
		this.session = session;
		
	}
	
	public Boolean insert(AdmPermission admPermission){
		
		admPermission.setDateOfRegistration(new Date());

		try{

			this.session.save(admPermission);

		}catch(HibernateException e){

			return false;
			
		}

		return true;
		
	}
	
	public AdmPermission get(AdmPermission admPermission){
		
		AdmPermission admPermissionLoaded = (AdmPermission) this.session.load( AdmPermission.class , new Long(admPermission.getId()) );
		return admPermissionLoaded;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmPermission> getAll(){
		
		return this.session.createCriteria(AdmPermission.class).list();

	}
	
	public Boolean edit( AdmPermission admPermission ){
		
		try{
			
			this.session.merge(admPermission);

		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}
	
	public Boolean delete( AdmPermission admPermission ){
		
		try{
			
			this.session.delete(admPermission);

		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}

}
