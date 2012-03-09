package br.com.umake.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.model.User;

@Component
public class UserDao {

	private final Session session;

	public UserDao( Session session ) {

		this.session = session;
		
	}

	public User getAllUsers() {

		return null;

	}

}