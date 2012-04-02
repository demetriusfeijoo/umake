package br.com.umake.dao;

import java.util.Date;
import java.util.HashSet;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.Permission;
import br.com.umake.model.User;

@Component
@ApplicationScoped
public class UserDao {

	private final Session session;
	private Transaction transaction;

	public UserDao( Session session ) {
		this.session = session;
		this.session.setCacheMode(CacheMode.IGNORE);

	}
	
	public Boolean insertUser(User user){

		user.setDateOfRegistration(new Date());
		user.setDateLastVisit(new Date());
		
		this.transaction = this.session.beginTransaction();
		this.session.save(user);
		this.transaction.commit();
		
		return this.transaction.wasCommitted();
		
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
		
		this.session.flush();
		this.session.clear();
		this.transaction = this.session.beginTransaction();
		this.transaction.begin();
		this.session.merge(user);
		this.transaction.commit();

		return this.transaction.wasCommitted();
		
	}
	
	public void updateInfoUser(User user){
	
	}
		
	public User findUser(User user) {
		
		this.session.flush();
		this.session.clear();
		user.setId(new Long(5));
		System.out.println(session.contains(user));
	
		User userFound = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("login", user.getLogin()))
				.add(Restrictions.eq("password", user.getPassword()))
				.add(Restrictions.eq("userBlock", false)).setCacheMode(CacheMode.IGNORE).uniqueResult();
		return userFound;
		
	}

}