package br.com.umake.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.Menu;

@Component
public class MenuDao {
	
	private final Session session;
	
	public MenuDao(Session session){
		
		this.session = session;
		
	}
	
	
	public Boolean insert(Menu menu){
				
		try{
			
			this.session.save(menu);
			
		}catch(HibernateException e){

			return false;
			
		}

		return true;
		
	}
	
	public Menu get(Menu menu){
				
		return (Menu) this.session.createCriteria(Menu.class).add(Restrictions.eq("id", menu.getId())).uniqueResult();
		
	}
	
	public Menu get(String propertyName, String value){
		
		return (Menu) this.session.createCriteria(Menu.class).add(Restrictions.eq(propertyName, value)).uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getAll(){
		
		return this.session.createCriteria(Menu.class).list();

	}

	@SuppressWarnings("unchecked")
	public List<Menu> getAllActivies(){
		
		Boolean status = true;
		return this.session.createCriteria(Menu.class).add(Restrictions.eq("status", status)).list();

	}
	
	public Boolean edit( Menu page ){
		
		try{
			
			this.session.merge(page);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}
	
	public Boolean delete( Menu menu ){
		
		try{

			this.session.delete(menu);
			
		}catch(HibernateException e){
			
			e.printStackTrace();
			return false;
			
		}
		
		return true;
		
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getAllLimitedAndOrdered(int offset, Integer length, String propertyName, String propertySortName ){
		
		String HQL;
		
		if( propertyName != null && propertySortName != null ){
			
			HQL = String.format("from Menu order by %s %s", propertyName, propertySortName);
			
		}else if(propertyName != null && propertySortName == null){
			
			HQL = String.format("from Menu order by %s ", propertyName);			
			
		}else{
			
			HQL = String.format("from Menu");			
			
		}
		
		return this.session.createQuery(HQL).setFirstResult(offset).setMaxResults(length).list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getAllByPropertyName(String propertyName, String search){
	
		if( !propertyName.isEmpty() && !search.isEmpty() ){
			
			return this.session.createQuery("from Menu where "+propertyName+" LIKE '%"+search+"%'").list();
		
		}
		
		return new ArrayList<Menu>();
		
	}
}
