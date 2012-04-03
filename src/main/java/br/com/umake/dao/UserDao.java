package br.com.umake.dao;

import java.util.Date;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.User;

@Component
public class UserDao {

	private final Session session;
	private Transaction transaction;

	public UserDao( Session session ) {
		
		this.session = session;


	}
	
	public Boolean insertUser(User user){

		user.setDateOfRegistration(new Date());
		user.setDateLastVisit(new Date());
		
		this.session.save(user);
		
		return  this.session.getTransaction().wasCommitted();
		
	}
	
	public User getUser(User user){
		
		User userLoaded = (User) this.session.load( User.class , new Long(user.getId()) );
		
		return userLoaded;

	}

	public Boolean deleteUser( User user ){
		
		return true;
		
	}
	
	public Boolean getAllUsers( User user ){
		
		this.session.load(UserDao.class, 1L);
		return true;
		
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