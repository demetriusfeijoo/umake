package br.com.umake.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.Page;

@Component
public class PageDao {
	
	private final Session session;
	
	public PageDao(Session session){
		
		this.session = session;
		
	}
	
	
	public Boolean insert(Page page){
		
		page.setDateOfRegistration(new Date());
		
		try{

			this.session.save(page);
			
		}catch(HibernateException e){

			return false;
			
		}

		return true;
		
	}
	
	public Page get(Page page){
				
		return (Page) this.session.createCriteria(Page.class).add(Restrictions.eq("id", page.getId())).uniqueResult();
		
	}
	
	public Page get(String propertyName, String value){
		
		return (Page) this.session.createCriteria(Page.class).add(Restrictions.eq(propertyName, value)).uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Page> getAll(){
		
		return this.session.createCriteria(Page.class).list();

	}

	@SuppressWarnings("unchecked")
	public List<Page> getAllActivies(){
		
		return this.session.createCriteria(Page.class).add(Restrictions.eq("status", 1)).list();

	}
	
	public Boolean edit( Page page ){
		
		try{
			
			this.session.merge(page);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}
	
	public Boolean delete( Page page ){
		
		try{

			this.session.createQuery("DELETE FROM umake_pages WHERE id="+page.getId()).executeUpdate();

		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}

	@SuppressWarnings("unchecked")
	public List<Page> getAllLimitedAndOrdered(int offset, Integer length, String propertyName, String propertySortName ){
		
		String HQL;
		
		if( propertyName != null && propertySortName != null ){
			
			HQL = String.format("from Page order by %s %s", propertyName, propertySortName);
			
		}else if(propertyName != null && propertySortName == null){
			
			HQL = String.format("from Page order by %s ", propertyName);			
			
		}else{
			
			HQL = String.format("from Page");			
			
		}
		
		return this.session.createQuery(HQL).setFirstResult(offset).setMaxResults(length).list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Page> getAllByPropertyName(String propertyName, String search){
	
		if( !propertyName.isEmpty() && !search.isEmpty() ){
			
			return this.session.createQuery("from Page where "+propertyName+" LIKE '%"+search+"%'").list();
		
		}
		
		return new ArrayList<Page>();
		
	}
}
