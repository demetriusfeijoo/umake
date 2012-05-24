<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${!allowedAccess}">Acesso proibido</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="description" content="${siteDescription}" />
		<meta name="keywords" content="${siteKeywords}" />
		<meta name="reply-to" content="${siteEmail}" />
		
		<c:forEach items="${cssFiles}" var="css">		
		
			<link rel="stylesheet" type="text/css" href="<c:url value="${templateRoot}/${css.fileName}" />" media="${css.media}" />			
 
 		</c:forEach>
		
		<title>${siteTitle}</title>
	</head>
	
	<body>
	
	    <section id="content">
	
	        <header>
	
	            <img src="<c:url value="${templateRoot}/templateImg.png" />  " width="1000" height="250"/>
	
	        </header>
	
	        <section>
	
	            <aside>
	
	                <nav>
						${menu}	
	                </nav>
	
	            </aside>
	
	            <article>
	
	                <h1 style="font-size:40px;">Home</h1>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec adipiscing arcu posuere diam tempor varius. Aenean eu sapien massa. Etiam sit amet dui sit amet quam egestas vehicula. Nulla pretium varius lacus, nec auctor mauris condimentum a. Vivamus a fermentum neque. Aliquam hendrerit lorem et magna bibendum nec lacinia justo blandit. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin adipiscing eros quis est commodo euismod. Maecenas lacinia lectus quam, eu blandit sem. Aliquam erat volutpat. Fusce sed enim sem, non semper leo. Vestibulum rutrum ipsum quis nunc facilisis vel porta mi vestibulum. Nunc ornare commodo pretium. Curabitur a justo in justo porta tempor quis et quam. Nam ac lorem vel tortor convallis aliquet non nec tortor. Etiam sed massa lacus, in gravida dui.
					
					Curabitur iaculis metus et sapien dictum id convallis magna malesuada. Proin venenatis, dolor sed bibendum laoreet, elit neque congue lacus, et commodo sem nunc vitae lacus. Phasellus scelerisque fringilla lacus, eget commodo tortor porta ac. Quisque et orci vel nunc fringilla feugiat et et felis. In sit amet lacus porttitor nulla placerat interdum et sit amet neque. Phasellus mattis quam quis est hendrerit imperdiet. Nulla et purus nisl. Donec ultrices, massa ac consequat mattis, lorem nunc tempor tortor, sed ullamcorper metus erat eu massa. Suspendisse cursus lorem nec ligula ultricies quis adipiscing ipsum pharetra. Quisque at sapien nisi, quis scelerisque lacus. Morbi urna purus, eleifend eget gravida ac, venenatis eu justo.
					
					Sed non sapien quis magna pharetra aliquet nec non lectus. Morbi bibendum lorem eget nulla euismod ultricies pulvinar tortor convallis. Integer eros dolor, elementum vitae fringilla nec, elementum et turpis. Maecenas ut nibh sit amet nunc placerat luctus. Mauris elementum tortor bibendum tortor fringilla nec vestibulum nulla consequat. Donec felis turpis, fringilla quis rhoncus a, ultrices et justo. Curabitur metus nibh, tincidunt eget tempus eget, consectetur sed risus.
					
					Proin sit amet congue nibh. Integer pulvinar malesuada massa a congue. In placerat luctus risus, at gravida lorem dictum eget. Cras in sodales arcu. Sed dolor erat, feugiat sed dictum eu, venenatis non nunc. Fusce porta adipiscing risus, in fringilla turpis facilisis congue. Nulla gravida sapien sit amet quam bibendum cursus. Aenean sed vehicula neque. Vivamus urna urna, feugiat nec suscipit ut, auctor ut purus. Duis pretium facilisis leo, in fringilla lectus suscipit aliquam. Fusce condimentum leo magna. Aliquam sapien justo, blandit eu consequat et, lacinia non ligula. Nullam eget tellus erat. Aenean sollicitudin ultrices est, ac dapibus metus fermentum at. Aliquam erat volutpat.
					
					Proin laoreet eleifend sem at lacinia. In hac habitasse platea dictumst. Cras leo justo, tristique eget rutrum nec, iaculis mattis quam. Vestibulum et justo quam, in fringilla nisl. Fusce id tellus risus, vitae faucibus eros. Maecenas viverra consequat sapien, at placerat neque ultricies a. Duis commodo urna sed orci sollicitudin sit amet convallis augue tristique. Suspendisse ullamcorper, enim in pharetra aliquet, felis ligula tristique felis, eu iaculis metus risus vitae mi. Sed luctus mi in massa cursus gravida. Vestibulum sagittis, sapien in bibendum vestibulum, elit nunc ullamcorper felis, at pellentesque massa eros a nunc. Etiam ultrices adipiscing libero a ornare. Integer ornare adipiscing nisi vitae vestibulum. Ut mollis orci non urna tristique interdum.</p>
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec adipiscing arcu posuere diam tempor varius. Aenean eu sapien massa. Etiam sit amet dui sit amet quam egestas vehicula. Nulla pretium varius lacus, nec auctor mauris condimentum a. Vivamus a fermentum neque. Aliquam hendrerit lorem et magna bibendum nec lacinia justo blandit. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin adipiscing eros quis est commodo euismod. Maecenas lacinia lectus quam, eu blandit sem. Aliquam erat volutpat. Fusce sed enim sem, non semper leo. Vestibulum rutrum ipsum quis nunc facilisis vel porta mi vestibulum. Nunc ornare commodo pretium. Curabitur a justo in justo porta tempor quis et quam. Nam ac lorem vel tortor convallis aliquet non nec tortor. Etiam sed massa lacus, in gravida dui.
					
					Curabitur iaculis metus et sapien dictum id convallis magna malesuada. Proin venenatis, dolor sed bibendum laoreet, elit neque congue lacus, et commodo sem nunc vitae lacus. Phasellus scelerisque fringilla lacus, eget commodo tortor porta ac. Quisque et orci vel nunc fringilla feugiat et et felis. In sit amet lacus porttitor nulla placerat interdum et sit amet neque. Phasellus mattis quam quis est hendrerit imperdiet. Nulla et purus nisl. Donec ultrices, massa ac consequat mattis, lorem nunc tempor tortor, sed ullamcorper metus erat eu massa. Suspendisse cursus lorem nec ligula ultricies quis adipiscing ipsum pharetra. Quisque at sapien nisi, quis scelerisque lacus. Morbi urna purus, eleifend eget gravida ac, venenatis eu justo.
					
					Sed non sapien quis magna pharetra aliquet nec non lectus. Morbi bibendum lorem eget nulla euismod ultricies pulvinar tortor convallis. Integer eros dolor, elementum vitae fringilla nec, elementum et turpis. Maecenas ut nibh sit amet nunc placerat luctus. Mauris elementum tortor bibendum tortor fringilla nec vestibulum nulla consequat. Donec felis turpis, fringilla quis rhoncus a, ultrices et justo. Curabitur metus nibh, tincidunt eget tempus eget, consectetur sed risus.
					
					Proin sit amet congue nibh. Integer pulvinar malesuada massa a congue. In placerat luctus risus, at gravida lorem dictum eget. Cras in sodales arcu. Sed dolor erat, feugiat sed dictum eu, venenatis non nunc. Fusce porta adipiscing risus, in fringilla turpis facilisis congue. Nulla gravida sapien sit amet quam bibendum cursus. Aenean sed vehicula neque. Vivamus urna urna, feugiat nec suscipit ut, auctor ut purus. Duis pretium facilisis leo, in fringilla lectus suscipit aliquam. Fusce condimentum leo magna. Aliquam sapien justo, blandit eu consequat et, lacinia non ligula. Nullam eget tellus erat. Aenean sollicitudin ultrices est, ac dapibus metus fermentum at. Aliquam erat volutpat.
					
					Proin laoreet eleifend sem at lacinia. In hac habitasse platea dictumst. Cras leo justo, tristique eget rutrum nec, iaculis mattis quam. Vestibulum et justo quam, in fringilla nisl. Fusce id tellus risus, vitae faucibus eros. Maecenas viverra consequat sapien, at placerat neque ultricies a. Duis commodo urna sed orci sollicitudin sit amet convallis augue tristique. Suspendisse ullamcorper, enim in pharetra aliquet, felis ligula tristique felis, eu iaculis metus risus vitae mi. Sed luctus mi in massa cursus gravida. Vestibulum sagittis, sapien in bibendum vestibulum, elit nunc ullamcorper felis, at pellentesque massa eros a nunc. Etiam ultrices adipiscing libero a ornare. Integer ornare adipiscing nisi vitae vestibulum. Ut mollis orci non urna tristique interdum.
	            
	            </article>
	
	        </section>
	
	        <footer>
	
	                <nav>
	
	                    <ul>
	
	                        <li><a href="#">home</a></li>
	                        <li><a href="#">quem somos</a></li>
	                        <li><a href="#">fale conosco</a></li>
	                        <li><a href="#">cadastro</a></li>
	                        <li><a href="#">portfolio</a></li>
	
	                    </ul>
	
	                </nav>
	
	                <a id="poweredUmake" href="#">Powered by UMAKE</a>
	
	        </footer>
	
	    </section>
	
	</body>

</html>