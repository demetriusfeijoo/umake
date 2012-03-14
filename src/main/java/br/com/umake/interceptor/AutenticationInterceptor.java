package br.com.umake.interceptor;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.umake.controller.UsersController;

@Intercepts
public class AutenticationInterceptor implements Interceptor {

	private final UserControl user;
	private final Result result;

	public boolean accepts(ResourceMethod arg0) {

		return !user.isLogged() && arg0.containsAnnotation(Restrictable.class);
	}

	public void intercept(InterceptorStack arg0, ResourceMethod arg1,
			Object arg2) throws InterceptionException {
		result.redirectTo(UsersController.class).formLogin();

	}

	public AutenticationInterceptor(UserControl user, Result result) {
		// super();
		this.result = result;
		this.user = user;
	}

}
