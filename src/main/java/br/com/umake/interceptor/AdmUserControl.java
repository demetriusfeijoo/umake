package br.com.umake.interceptor;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.umake.model.AdmUser;


@Component
@SessionScoped
public class AdmUserControl {

	private AdmUser user;

	public void login(AdmUser user) {

		this.user = user;

	}

	public AdmUser getUserAdm() {
		
		return this.user; 

	}

	public void logout() {

		this.user = null;

	}

	public boolean isLogged() {
		
		return this.user != null;

	}
}
