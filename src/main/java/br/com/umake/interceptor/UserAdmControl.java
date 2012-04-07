package br.com.umake.interceptor;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.umake.model.UserAdm;


@Component
@SessionScoped
public class UserAdmControl {

	private UserAdm user;

	public void login(UserAdm user) {

		this.user = user;

	}

	public UserAdm getUserAdm() {
		
		return this.user; 

	}

	public void logout() {

		this.user = null;

	}

	public boolean isLogged() {
		
		return this.user != null;

	}
}
