package br.com.umake.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.AdmGroup;

@Component
public class AdmGroupDao {
	
	private final Session session;

	public AdmGroupDao( Session session ) {
		
		this.session = session;

	}
	
	public Boolean insert(AdmGroup admGroup){
		
		admGroup.setDateOfRegistration(new Date());

		try{

			this.session.save(admGroup);

		}catch(HibernateException e){

			return false;
			
		}

		return true;
		
	}
	
	public AdmGroup get(AdmGroup admGroup){
		
		AdmGroup admGroupLoaded = (AdmGroup) this.session.load( AdmGroup.class , new Long(admGroup.getId()) );
		
		return admGroupLoaded;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmGroup> getAll(){
		
		return this.session.createCriteria(AdmGroup.class).list();

	}
	
	public Boolean edit( AdmGroup admGroup ){
		
		try{
			
			this.session.merge(admGroup);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}
	
	public Boolean delete( AdmGroup admGroup ){
		
		try{

			this.session.delete(admGroup);

		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmGroup> getAllAdmGroup(){
		
		return this.session.createCriteria(AdmGroup.class).list();
		
	}

}
