package br.com.umake.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.User;

@Component
public class UserDao {

	private final Session session;

	public UserDao( Session session ) {
		System.out.println("criando session");
		this.session = session;
		
	}

	public Object getAllUsers() {

		return null;

	}

	public User findUser(User user) {

		return (User) session.createCriteria(User.class)
				.add(Restrictions.eq("login", user.getLogin()))
				.add(Restrictions.eq("password", user.getPassword()))
				.uniqueResult();
		
	}

}