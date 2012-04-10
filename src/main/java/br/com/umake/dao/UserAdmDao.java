package br.com.umake.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.UserAdm;

@Component
public class UserAdmDao {

	private final Session session;

	public UserAdmDao( Session session ) {
		
		this.session = session;

	}
	
	public Boolean insertUserAdm(UserAdm user){

		user.setDateOfRegistration(new Date());
		user.setDateLastVisit(new Date());
		
		try{
			
			this.session.save(user);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	public UserAdm getUserAdm(UserAdm user){
		
		UserAdm userLoaded = (UserAdm) this.session.load( UserAdm.class , new Long(user.getId()) );
		return userLoaded;

	}

	public Boolean deleteUserAdm( UserAdm user ){
		
		try{
			
			this.session.delete(user);

		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<UserAdm> getAllUsersAdm(){
		
		return this.session.createCriteria(UserAdm.class).list();
		
	}
	
	public Boolean editUserAdm( UserAdm user ){
		
		try{
			
			this.session.merge(user);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;

	}
	
	public boolean updateLastDate(UserAdm user){
		
		try{
			
			this.session.saveOrUpdate(user);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;		
	}
		
	public UserAdm findUserAdm(UserAdm user) {
		
		UserAdm userFound = (UserAdm) session.createCriteria(UserAdm.class)
				.add(Restrictions.eq("login", user.getLogin()))
				.add(Restrictions.eq("password", user.getPassword()))
				.add(Restrictions.eq("userBlock", false)).uniqueResult();
		
		return userFound;
		
	}

}