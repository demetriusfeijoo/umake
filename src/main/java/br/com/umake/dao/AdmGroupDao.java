package br.com.umake.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.AdmGroup;
import br.com.umake.model.AdmUser;

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
	
	@SuppressWarnings("unchecked")
	public List<AdmGroup> getAllLimitedAndOrdered(int offset, Integer length, String propertyName, String propertySortName ){
		
		String HQL;
		
		if( propertyName != null && propertySortName != null ){
			
			HQL = String.format("from AdmGroup order by %s %s", propertyName, propertySortName);
			
		}else if(propertyName != null && propertySortName == null){
			
			HQL = String.format("from AdmGroup order by %s ", propertyName);			
			
		}else{
			
			HQL = String.format("from AdmGroup");			
			
		}
		
		return this.session.createQuery(HQL).setFirstResult(offset).setMaxResults(length).list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmGroup> getAllByPropertyName(String propertyName, String search){
	
		if( !propertyName.isEmpty() && !search.isEmpty() ){
			
			return this.session.createQuery("from AdmGroup where "+propertyName+" LIKE '%"+search+"%'").list();
		
		}
		
		return new ArrayList<AdmGroup>();
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

}
