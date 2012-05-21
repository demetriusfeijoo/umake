package br.com.umake.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.Config;

@Component
public class ConfigDao {
	
	private final Session session;
	
	public ConfigDao(Session session){
		
		this.session = session;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Config> getAll(){
		
		return this.session.createCriteria(Config.class).list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Config> getConfigsBySlugs(String...slugs){

		return this.session.createCriteria(Config.class).add(Restrictions.in("slug", slugs)).list();	
		
	}	
	
	public Boolean edit( Config config ){
		
		try{
			
			this.session.merge(config);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}
	
	/**
	 * If you haven't the id, you can edit by slug.
	 * Sql structure: Update table SET value = config.getValue() WHERE slug = config.getSlug() 
	 * @param config
	 * */
	public void editBySlug(Config config){
			
		Query query = this.session.createQuery("UPDATE Config SET value = :value WHERE slug = :slug ");
		query.setParameter("value", config.getValue() );
		query.setParameter("slug", config.getSlug() );
		query.executeUpdate();
		
	}
	
	/**
	 * If you need edit a List of Config by slug. When your configs don't have id but you need edit it.
	 * Sql structure: Update table SET value = config.getValue() WHERE slug = config.getSlug()  
	 * @param configs
	 * */	
	public void editBySlug(List<Config> configs){
		
		for (Config config : configs) {
			
			Query query = this.session.createQuery("UPDATE Config SET value = :value WHERE slug = :slug ");
			query.setParameter("value", config.getValue() );
			query.setParameter("slug", config.getSlug() );
			query.executeUpdate();	
			
		}
		
	}
	
}
