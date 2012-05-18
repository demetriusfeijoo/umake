package br.com.umake.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.Config;
import br.com.umake.model.Page;

@Component
public class ConfigDao {
	
	private final Session session;
	
	public ConfigDao(Session session){
		
		this.session = session;
		
	}
	
	public boolean insert(Config config){
		
		try{
			this.session.save(config);			
		}catch(HibernateException e){
			return false;
		}
		
		return true;
		
	}
	
	public Config get(Config config){
		
		return (Config) this.session.createCriteria(Config.class).add(Restrictions.eq("id", config.getId())).uniqueResult();
		
	}
	
	public Config getAll(){
		
		return (Config) this.session.createCriteria(Config.class).uniqueResult();
		
	}
	
	public Boolean edit( Config config ){
		
		try{
			
			this.session.merge(config);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}
	
}
