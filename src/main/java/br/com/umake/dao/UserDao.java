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
<<<<<<< HEAD
		System.out.println("criando session novo usuario");
		this.session = session;		
	}
	
	public void insertUser(User user){
		System.out.println("insertuser chamado");
=======
		this.session = session;

	}
	
	public Boolean insertUser(User user){
<<<<<<< HEAD

=======
		System.out.println("Insert user");
>>>>>>> upstream/master
/*		Set<Permission> listPermissions = new HashSet<Permission>();
		Permission permission1 = new Permission();
		Permission permission2 = new Permission();
		Permission permission3 = new Permission();
		Permission permission4 = new Permission();
		permission1.setName("CREATE");
		permission2.setName("VIEW");
		permission3.setName("EDIT");
		permission4.setName("DELETE");
		permission1.setContext("ADMINISTRATION");
		permission2.setContext("ADMINISTRATION");
		permission3.setContext("ADMINISTRATION");
		permission4.setContext("ADMINISTRATION");
		listPermissions.add(permission1);
		listPermissions.add(permission2);
		listPermissions.add(permission3);
		listPermissions.add(permission4);
		
		Set<Group> listGroup = new HashSet<Group>();
		
		user.setPermissions(listPermissions);
		user.setGroups(listGroup);*/
>>>>>>> f75ada794862b68edc54ee4ef66e0d4dd68858cc
		user.setDateOfRegistration(new Date());
		user.setDateLastVisit(new Date());
<<<<<<< HEAD
		user.setUserBlock(false);*/
		this.transaction = this.session.beginTransaction();

		this.session.save(user);
		this.transaction.commit();
		//this.session.flush();
		//this.session.close();	
		System.out.println("transacao ok");
=======
		user.setUserBlock(false);
		
		this.transaction = this.session.beginTransaction();
		this.session.save(user);
		this.transaction.commit();
		this.session.flush();
		
		return this.transaction.wasCommitted();
>>>>>>> upstream/master
		
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