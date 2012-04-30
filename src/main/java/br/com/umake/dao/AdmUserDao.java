package br.com.umake.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.AdmUser;

@Component
public class AdmUserDao {

	private final Session session;

	public AdmUserDao( Session session ) {
		
		this.session = session;

	}
	
	public Boolean insert(AdmUser admUser){
				
		try{
			
			this.session.save(admUser);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	public AdmUser get(AdmUser admUser){
		
		return (AdmUser) this.session.load( AdmUser.class , new Long(admUser.getId()) );

	}

	public Boolean delete( AdmUser admUser ){
		
		try{
			
			this.session.delete(admUser);

		}catch(HibernateException e){
			System.out.println(e.getMessage());
			return false;
			
		}
		
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmUser> getAll(){
		
		return this.session.createCriteria(AdmUser.class).list();
		
	}

	@SuppressWarnings("unchecked")
	public List<AdmUser> getAllLimitedAndOrdered(int offset, Integer length, String propertyName, String propertySortName ){
		
		String HQL;
		
		if( propertyName != null && propertySortName != null ){
			
			HQL = String.format("from AdmUser order by %s %s", propertyName, propertySortName);
			
		}else if(propertyName != null && propertySortName == null){
			
			HQL = String.format("from AdmUser order by %s ", propertyName);			
			
		}else{
			
			HQL = String.format("from AdmUser");			
			
		}
		
		return this.session.createQuery(HQL).setFirstResult(offset).setMaxResults(length).list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<AdmUser> getAllByPropertyName(String propertyName, String search){
	
		if( !propertyName.isEmpty() && !search.isEmpty() ){
			
			return this.session.createCriteria(AdmUser.class).add(Restrictions.ilike(propertyName, search)).list();
			
		}
		
		return new ArrayList<AdmUser>();
	}
	
	public Boolean edit( AdmUser admUser ){
		
		try{
			
			this.session.merge(admUser);
			
		}catch(HibernateException hibernateException ){
			
			return false;
			
		}
		
		return true;

	}
	
	public boolean updateLastDate(AdmUser admUser){
		
		try{
			
			this.session.update(admUser);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;		
	}
		
	public AdmUser findAdmUser(AdmUser admUser) {
		
		AdmUser userFound = (AdmUser) session.createCriteria(AdmUser.class)
				.add(Restrictions.eq("login", admUser.getLogin()))
				.add(Restrictions.eq("password", admUser.getPassword()))
				.add(Restrictions.eq("userBlock", false)).uniqueResult();
		
		return userFound;
		
	}

}