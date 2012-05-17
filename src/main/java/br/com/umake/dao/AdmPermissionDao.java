package br.com.umake.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
		
		return (AdmPermission) this.session.createCriteria(AdmPermission.class).add(Restrictions.eq("id", admPermission.getId())).uniqueResult();
		
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

			this.session.createQuery("DELETE FROM AdmPermission WHERE id="+admPermission.getId()).executeUpdate();

		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}

	@SuppressWarnings("unchecked")
	public List<AdmPermission> getAllLimitedAndOrdered(int offset, Integer length, String propertyName, String propertySortName ){
		
		String HQL;
		
		if( propertyName != null && propertySortName != null ){
			
			HQL = String.format("from AdmPermission order by %s %s", propertyName, propertySortName);
			
		}else if(propertyName != null && propertySortName == null){
			
			HQL = String.format("from AdmPermission order by %s ", propertyName);			
			
		}else{
			
			HQL = String.format("from AdmPermission");			
			
		}
		
		return this.session.createQuery(HQL).setFirstResult(offset).setMaxResults(length).list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmPermission> getAllByPropertyName(String propertyName, String search){
	
		if( !propertyName.isEmpty() && !search.isEmpty() ){
			
			return this.session.createQuery("from AdmPermission where "+propertyName+" LIKE '%"+search+"%'").list();
		
		}
		
		return new ArrayList<AdmPermission>();
	}
}
