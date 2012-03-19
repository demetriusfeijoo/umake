package br.com.umake.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.User;

@Component
@ApplicationScoped
public class UserDao {

	private final Session session;

	public UserDao( Session session ) {
		System.out.println("criando session novo usuario");
		this.session = session;
		
	}


	public User findUser(User user) {

		return (User) session.createCriteria(User.class)
				.add(Restrictions.eq("login", user.getLogin()))
				.add(Restrictions.eq("password", user.getPassword()))
				.uniqueResult();
		
	}

}