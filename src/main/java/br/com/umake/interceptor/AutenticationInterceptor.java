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
import br.com.umake.permissions.Context;
import br.com.umake.permissions.Create;
import br.com.umake.permissions.Delete;
import br.com.umake.permissions.Edit;
import br.com.umake.permissions.View;


@Intercepts
public class AutenticationInterceptor implements Interceptor {

	private final UserControl user;
	private final Result result;

	public boolean accepts(ResourceMethod method) {
		
		return true;

	}

	public void intercept(InterceptorStack arg0, ResourceMethod method,
			Object arg2) throws InterceptionException {
		
		
		if( method.containsAnnotation(View.class) || method.containsAnnotation(Create.class) || method.containsAnnotation(Delete.class)  || method.containsAnnotation(Edit.class) ){
			
			if(arg2.getClass().isAnnotationPresent(Context.class)){
				
				String context =  arg2.getClass().getAnnotation(Context.class).value();
				
				Annotation []annotations = method.getMethod().getAnnotations();
				
				List<Annotation> annotationsPermissions = new ArrayList<Annotation>(4);
				
				for (Annotation annotation : annotations) {
					// PEGAR DO PACOTE E JOGAR EM UM MÉTODO MAIS COESO
					
					if(annotation.annotationType() == View.class || annotation.annotationType() == Create.class || annotation.annotationType() == Delete.class || annotation.annotationType() == Edit.class){
						annotationsPermissions.add( annotation );
					}
						
				}
				
				System.out.println(annotationsPermissions.size());
			}else{
				
			}
			
		}
		
		arg0.next(method, arg2);

	}

	public AutenticationInterceptor(UserControl user, Result result) {
		// super();
		this.result = result;
		this.user = user;
	}

}
