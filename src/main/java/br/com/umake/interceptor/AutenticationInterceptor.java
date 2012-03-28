package br.com.umake.interceptor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.umake.controller.AdministrationController;
import br.com.umake.model.Permission;
import br.com.umake.permissions.Context;
import br.com.umake.permissions.Create;
import br.com.umake.permissions.Delete;
import br.com.umake.permissions.Edit;
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
		
		Object o = null;
		Boolean contextExists = false;
		
		try {
			
			o = method.getClass().newInstance();
			contextExists = o.getClass().isAnnotationPresent(Context.class);
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
/*		Annotation[] annotations = method.getMethod().getAnnotations();
		
		for (Annotation annotation : annotations) {

			if (annotation.annotationType() == View.class || annotation.annotationType() == Create.class || annotation.annotationType() == Delete.class || annotation.annotationType() == Edit.class) {
				
				return true;
				
			}

		}*/
		
		if(this.user.isLogged()){
			
			return true;
			
		}else{
			
			if( contextExists ){
			
				return true;
				
			}
			
		}
		
		return false;
		

	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object obj) throws InterceptionException {
		
		if(!this.user.isLogged()){
			
				this.result.redirectTo(br.com.umake.controller.UsersController.class).formLogin();	
				
		}else{ 

			if(this.user.getUser().hasAllNecessariesPermissions(this.recoveryNecessariesPermissions(method, obj))){
					
					stack.next(method, obj);
					
			}else{
				
					this.result.forwardTo(AdministrationController.class).index();
					
			}
				
		}

	}
	
	private List<Permission> recoveryNecessariesPermissions(ResourceMethod method, Object objectInUse){
		
		List<Permission> permissoesExigidas = new ArrayList<Permission>(4);
		
		if (objectInUse.getClass().isAnnotationPresent(Context.class)) {

			String context = objectInUse.getClass().getAnnotation(Context.class).value();

			Annotation[] annotations = method.getMethod().getAnnotations();

			
			for (Annotation annotation : annotations) {

				if (annotation.annotationType() == View.class || annotation.annotationType() == Create.class || annotation.annotationType() == Delete.class || annotation.annotationType() == Edit.class) {
					
					String[] pedacosPerm = annotation.annotationType().getName().split("\\.");
					int ultimaPosicao = pedacosPerm.length - 1;
					
					Permission permissionTemp = new Permission();
					permissionTemp.setContext(context);
					permissionTemp.setName(pedacosPerm[ultimaPosicao]);
					
					permissoesExigidas.add( permissionTemp );
					
				}

			}

		}
		
		return permissoesExigidas;
		
	}

}