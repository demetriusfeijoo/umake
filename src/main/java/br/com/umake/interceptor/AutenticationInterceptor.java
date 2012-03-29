package br.com.umake.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.umake.controller.AdministrationController;
import br.com.umake.controller.UsersController;
import br.com.umake.model.Permission;
import br.com.umake.permissions.Context;
import br.com.umake.permissions.Create;
import br.com.umake.permissions.Delete;
import br.com.umake.permissions.Edit;
import br.com.umake.permissions.IsNotPermission;
import br.com.umake.permissions.View;

@Intercepts
public class AutenticationInterceptor implements Interceptor {

	private final UserControl user;
	private final Result result;

	public AutenticationInterceptor(UserControl user, Result result) {

		this.result = result;
		this.user = user;

	}

	public boolean accepts(ResourceMethod method) {

		return method.getMethod().getDeclaringClass().isAnnotationPresent(Context.class);

	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object obj) throws InterceptionException {

		if( this.user.isLogged()){
			
			if(this.user.getUser().hasAllNecessariesPermissions(this.recoveryNecessariesPermissions(method, obj))){
				// tem context, tah logado e tem todas as permissoes
				
				if(method.getMethod().getName() != "formLogin"){//Já está logado, mas acessa a página de login novamente.
				
						stack.next(method, obj);

				}else{
					
					this.result.redirectTo(AdministrationController.class).index();	
					
				}

			}else{
				// Tem context, tah logado, não tem todas as permissoes caso tenha alguma.
				this.result.redirectTo(AdministrationController.class).index();
			}
			
		}else{
			
			if( method.getMethod().isAnnotationPresent(IsNotPermission.class) ){
				
				stack.next(method, obj);
				
			}else{
				
				this.result.forwardTo(UsersController.class).formLogin();

			}
		}

	}

	private List<Permission> recoveryNecessariesPermissions(
			ResourceMethod method, Object objectInUse) {

		List<Permission> permissoesExigidas = new ArrayList<Permission>(4);

		if (objectInUse.getClass().isAnnotationPresent(Context.class)) {

			String context = objectInUse.getClass()
					.getAnnotation(Context.class).value();

			Annotation[] annotations = method.getMethod().getAnnotations();

			for (Annotation annotation : annotations) {

				if (annotation.annotationType() == View.class
						|| annotation.annotationType() == Create.class
						|| annotation.annotationType() == Delete.class
						|| annotation.annotationType() == Edit.class) {

					String[] pedacosPerm = annotation.annotationType()
							.getName().split("\\.");
					int ultimaPosicao = pedacosPerm.length - 1;

					Permission permissionTemp = new Permission();
					permissionTemp.setContext(context);
					permissionTemp.setName(pedacosPerm[ultimaPosicao]);

					permissoesExigidas.add(permissionTemp);

				}

			}

		}

		return permissoesExigidas;

	}

}