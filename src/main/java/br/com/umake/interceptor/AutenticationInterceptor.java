package br.com.umake.interceptor;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.umake.controller.AdministrationController;
import br.com.umake.controller.UsersController;
import br.com.umake.model.Permission;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Intercepts
@Lazy
public class AutenticationInterceptor implements Interceptor {

	private final UserControl user;
	private final Result result;

	public AutenticationInterceptor(UserControl user, Result result) {

		this.result = result;
		this.user = user;		

	}

	public boolean accepts(ResourceMethod method) {

		return method.getMethod().isAnnotationPresent(Restrictable.class);

	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object obj) throws InterceptionException {

		if(this.user.isLogged()){
			
			if( this.onlyRestrictable(method) ){
				
				stack.next(method, obj);
				
			}else{
				
				if( this.user.getUser().hasAllNecessariesPermissions( this.recoveryNecessariesPermissions(method) ) ){
					
					stack.next(method, obj);
					
				}else{
					
					this.result.redirectTo(AdministrationController.class).index();
					
				}
				
			}
			
		}else{
			
			this.result.redirectTo(UsersController.class).formLogin();
			
		}

	}

	private List<Permission> recoveryNecessariesPermissions( ResourceMethod method ) {
		
		List<Permission> permissions = new ArrayList<Permission>(4);

		Restrictable restrictable = method.getMethod().getAnnotation(Restrictable.class);
		PermissionAnnotation[] permissionsOfRestrictable = restrictable.permissions();
		
		for (PermissionAnnotation permissionAnnotation : permissionsOfRestrictable) {
			
			 if(permissionAnnotation.context().equals("") || permissionAnnotation.permissionsTypes().length == 0){
				 
				 continue;
				 
			 }
			 
			 String context = permissionAnnotation.context();
			 
			 for (PermissionType permissionType : permissionAnnotation.permissionsTypes()) {
				 
				 Permission permission = new Permission();
				 permission.setContext(context);
				 permission.setType(permissionType.name());
				 
				 permissions.add(permission);
				 
			 }
			 
		}
		
		return permissions;

	}
	
	private Boolean onlyRestrictable(ResourceMethod method){
		
		Restrictable restrictable = method.getMethod().getAnnotation(Restrictable.class);
			
		br.com.umake.permissions.PermissionAnnotation[] permissions = restrictable.permissions();
		 
		if(permissions.length == 1){
			 
			 if(permissions[0].context().equals("") || permissions[0].permissionsTypes().length == 0){
				 
				 return true;
				 
			 }
			 
		 }else if(permissions.length == 0){
			 
			 return true;
			 
		 }


		
		return false;
	}

}