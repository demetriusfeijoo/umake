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
		<c:forEach items="${cssPaths}" var="cssPath">		
		
			<link rel="stylesheet" type="text/css" href="<c:url value="${cssPath}" />" media="screen" />			
 
 		</c:forEach>
		
		<title>${siteTitle}</title>
	</head>

	<body>
	    <section id="content">
	
	        <header>
	
	            <nav>
	
	                <ul>
	
	                    <li><a href="#">Home</a></li>
	                    <li><a href="#">Quem somos</a></li>
	                    <li><a href="#">Fale conosco</a></li>
	                    <li><a href="#">Cadastro</a></li>
	                    <li><a href="#">Portifolio</a></li>
	
	                </ul>
	
	            </nav>
	
	        </header>
	
	        <section>
	
	            <h1>titulo</h1>
	
	            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi convallis vestibulum dapibus. Mauris et ante tellus, sed feugiat leo. Sed sagittis fringilla magna, sit amet placerat sapien vehicula eget. Pellentesque odio quam, gravida ut iaculis eu, elementum sit amet nulla. Donec nec sem arcu. Nulla sodales dui sit amet augue pharetra eu imperdiet nisi luctus. Nunc aliquet velit sit amet eros iaculis adipiscing. Mauris pulvinar imperdiet ipsum at vehicula. Curabitur at lacus est, eu vestibulum erat. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vivamus congue, nibh at accumsan consequat, quam ligula imperdiet diam, sagittis vulputate metus leo quis dolor. Duis ac mauris mi.
	
				Mauris dapibus consectetur turpis sit amet imperdiet. Aliquam erat volutpat. Quisque at laoreet orci. In adipiscing aliquam commodo. Donec vehicula, augue congue interdum ultricies, enim mi iaculis quam, ac euismod nisl dolor a leo. Nam ultrices consequat commodo. Suspendisse in est lacus.
				
				Vestibulum ullamcorper laoreet feugiat. Vestibulum semper tortor magna, non adipiscing ligula. In ut sagittis odio. Nam lectus purus, egestas vel mattis at, convallis ac odio. Pellentesque vulputate consectetur neque, a pretium mauris fermentum id. Curabitur tempor, nulla at volutpat adipiscing, dui purus imperdiet tortor, at mattis magna nunc tempor tortor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.
				
				Phasellus ut mauris urna, ullamcorper molestie dolor. Integer sagittis bibendum erat. Sed molestie, augue tristique condimentum facilisis, enim lorem euismod ligula, ac porta magna nisi non leo. Nulla a nulla in neque aliquam ornare. Mauris cursus magna sit amet dui pellentesque nec vulputate massa semper. Vestibulum lobortis iaculis arcu, sit amet rhoncus ligula luctus vel. Donec faucibus, erat id hendrerit posuere, augue velit ultricies ligula, at accumsan purus nunc id nisi. Aliquam sed magna non libero euismod sagittis at et felis. Ut feugiat, nulla et fermentum blandit, est magna viverra ante, ut semper massa purus sit amet ante. Integer metus elit, aliquam sit amet molestie et, imperdiet a arcu. Nullam quis semper lorem.
				
				Cras quam sapien, auctor porta dictum et, imperdiet et neque. Nulla augue sapien, varius eget consectetur eu, sollicitudin ac dui. Phasellus vitae tellus et nibh auctor consectetur et a quam. Maecenas et magna dui. Praesent nec mi et lorem laoreet consequat. In euismod, mi eu suscipit condimentum, lectus diam ultricies velit, id tempor velit enim nec lacus. Vestibulum felis felis, malesuada ac suscipit et, vulputate id mauris. Vestibulum lacinia est mauris. Duis at urna elit. Aliquam sit amet enim sed nisi congue facilisis. Cras pharetra lacus sed turpis lobortis quis ultricies risus auctor. Nullam in arcu sit amet est fringilla varius</p>
	
	        </section>
	
	        <footer>
	
	            <nav>
	
	                <ul>
	
	                    <li><a href="#">Home</a></li>
	                    <li><a href="#">Quem somos</a></li>
	                    <li><a href="#">Fale conosco</a></li>
	                    <li><a href="#">Cadastro</a></li>
	                    <li><a href="#">Portfolio</a></li>
	
	                </ul>
	
	            </nav>
	
	        </footer>
	
	    </section>
	
	</body>
</html>