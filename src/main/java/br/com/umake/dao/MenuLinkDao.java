package br.com.umake.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.MenuLink;
import br.com.umake.model.MenuLink;

@Component
public class MenuLinkDao {
	
	private final Session session;
	
	public MenuLinkDao(Session session){
		
		this.session = session;
		
	}
	
	public Boolean insert(MenuLink menuLink){
				
		try{

			this.session.save(menuLink);
			
		}catch(HibernateException e){

			return false;
			
		}

		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuLink> getAll(){
				
		return this.session.createCriteria(MenuLink.class).list();
		
	}
	
	@SuppressWarnings("unchecked")
	public MenuLink get(MenuLink menuLink){
				
		return (MenuLink) this.session.createCriteria(MenuLink.class).add(Restrictions.eq("id", menuLink.getId())).uniqueResult();
		
	}
	
	public Boolean edit( MenuLink menuLink ){
		
		try{
			
			this.session.merge(menuLink);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}

	public Boolean delete( MenuLink menuLink ){
		
		try{
			
			this.session.delete(menuLink);

		}catch(HibernateException e){

			return false;
			
		}
		
		return true;
		
	}

	@SuppressWarnings("unchecked")
	public List<MenuLink> getAllLimitedAndOrdered(int offset, Integer length, String propertyName, String propertySortName ){
		
		String HQL;
		
		if( propertyName != null && propertySortName != null ){
			
			HQL = String.format("from MenuLink order by %s %s", propertyName, propertySortName);
			
		}else if(propertyName != null && propertySortName == null){
			
			HQL = String.format("from MenuLink order by %s ", propertyName);			
			
		}else{
			
			HQL = String.format("from MenuLink");			
			
		}

		return this.session.createQuery(HQL).setFirstResult(offset).setMaxResults(length).list();
		
	}

	@SuppressWarnings("unchecked")
	public List<MenuLink> getAllByPropertyName(String propertyName, String search){
	
		if( !propertyName.isEmpty() && !search.isEmpty() ){
			
			return this.session.createQuery("from MenuLink where "+propertyName+" LIKE '%"+search+"%'").list();
		
		}
		
		return new ArrayList<MenuLink>();
	}	
}
