package br.com.umake.interceptor;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.umake.model.AdmUser;


@Component
@SessionScoped
public class AdmUserControl {

	private AdmUser admUser;

	public void login(AdmUser user) {

		this.admUser = user;

	}
	
	public void updateUser(AdmUser user){
		
		this.login(user);
		
	}

	public AdmUser getAdmUser() {
		
		return this.admUser; 

	}

	public void logout() {

		this.admUser = null;

	}

	public boolean isLogged() {
		
		return this.admUser != null;

	}
}
