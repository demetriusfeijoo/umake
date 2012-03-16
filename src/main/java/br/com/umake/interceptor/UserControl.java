package br.com.umake.interceptor;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.umake.model.User;

@Component
@SessionScoped
public class UserControl {

	private User user;

	public void login(User user) {

		this.user = user;

	}

	public User getUser() {

		return this.user;

	}

	public void logout() {

		this.user = null;

	}

	public boolean isLogged() {
		
		return this.user != null;

	}
}
