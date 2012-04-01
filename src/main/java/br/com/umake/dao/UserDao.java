package br.com.umake.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.User;

@Component
@ApplicationScoped
public class UserDao {

	private final Session session;
	private Transaction transaction;

	public UserDao( Session session ) {
		this.session = session;

	}
	
	public Boolean insertUser(User user){

		user.setDateOfRegistration(new Date());
		user.setDateLastVisit(new Date());
		user.setUserBlock(false);
		
		this.transaction = this.session.beginTransaction();
		this.session.save(user);
		this.transaction.commit();
		this.session.flush();
		
		return this.transaction.wasCommitted();
		
	}
	
	public User getUser(User user){
		
		return (User) this.session.load( User.class , new Long(user.getId()) );		

	}

	public Boolean deleteUser( User user ){
		
		return true;
		
	}
	
	public Boolean getAllUsers( User user ){
		
		this.session.load(UserDao.class, 1L);
		return true;
		
	}
	
	public Boolean editUser( User user ){
		
		return true;
		
	}
	
	public void updateInfoUser(User user){
	
		
		
	}
		
	public User findUser(User user) {

		return (User) session.createCriteria(User.class)
				.add(Restrictions.eq("login", user.getLogin()))
				.add(Restrictions.eq("password", user.getPassword()))
				.uniqueResult();
		
	}

}