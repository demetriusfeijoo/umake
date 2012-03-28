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
		System.out.println("Insert user");
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
		user.setDateOfRegistration(new Date());
		user.setDateLastVisit(new Date());
		user.setUserBlock(false);
		user.setReceiveEmail(true);
		
		System.out.println(user.getName());
		this.transaction = this.session.beginTransaction();
		this.session.save(user);
		this.transaction.commit();
		this.session.flush();	
		
		return this.transaction.wasCommitted();
		
	}
	
	public User findUser(User user) {

		return (User) session.createCriteria(User.class)
				.add(Restrictions.eq("login", user.getLogin()))
				.add(Restrictions.eq("password", user.getPassword()))
				.uniqueResult();
		
	}

}