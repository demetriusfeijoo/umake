package br.com.umake.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.User;

@Component
public class UserDao {

	private final Session session;

	public UserDao( Session session ) {
		
		this.session = session;


	}
	
	public Boolean insertUser(User user){

		user.setDateOfRegistration(new Date());
		user.setDateLastVisit(new Date());
		
		try{
			
			this.session.save(user);
			
		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	public User getUser(User user){
		
		
		User userLoaded = (User) this.session.load( User.class , new Long(user.getId()) );
		return userLoaded;

	}

	public Boolean deleteUser( User user ){
		
		try{
			
			this.session.delete(user);

		}catch(HibernateException e){
			
			return false;
			
		}
		
		return true;
		
	}
	
	public List<User> getAllUsers(){
		
		return this.session.createCriteria(User.class).list();
		
	}
	
	public Boolean editUser( User user ){
		
		this.session.merge(user);	

		return this.session.getTransaction().wasCommitted();
		
	}
	
	public void updateInfoUser(User user){
	
	}
		
	public User findUser(User user) {
		
		User userFound = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("login", user.getLogin()))
				.add(Restrictions.eq("password", user.getPassword()))
				.add(Restrictions.eq("userBlock", false)).uniqueResult();
		
		return userFound;
		
	}

}