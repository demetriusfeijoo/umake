package br.com.umake.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.PermissionAdm;

@Component
public class PermissionAdmDao {
	
	AnnotationConfiguration configuration;
	Session session;
	
	public PermissionAdmDao(){
		
		configuration = new AnnotationConfiguration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		session = factory.openSession();
		
	}
	
	public Boolean insertPermissionAdm(PermissionAdm permissionAdm){
		
		permissionAdm.setDateOfRegistration(new Date());

		try{

			//this.session.createSQLQuery("INSERT INTO umake_groups(name, dateOfRegistration, parentGroup) VALUES('opera','2012-03-10 12:12:12', 1)").executeUpdate();
			this.session.saveOrUpdate(permissionAdm);

		}catch(HibernateException e){
			System.out.println(e.getCause());
			return false;
			
		}

		return true;
		
	}
	
	public PermissionAdm getPermissionAdm(PermissionAdm permissionAdm){
		
		PermissionAdm permissionAdmLoaded = (PermissionAdm) this.session.load( PermissionAdm.class , new Long(permissionAdm.getId()) );
		return permissionAdmLoaded;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PermissionAdm> getAllPermissionAdm(){
		
		Criteria allPermissionAdm = this.session.createCriteria(PermissionAdm.class);
		List<PermissionAdm> PermissionAdmList = null;
		
		try{
			PermissionAdmList = allPermissionAdm.list();
		}catch(HibernateException e){
			System.out.println(e.getCause());
		}
		
		return PermissionAdmList;
	}
	
	public Boolean editPermissionAdm( PermissionAdm permissionAdm ){
		
		try{
			
			this.session.merge(permissionAdm);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}
	
	public Boolean deletePermissionAdm( PermissionAdm permissionAdm ){
		
		try{
			
			this.session.delete(permissionAdm);

		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}

}
