<%@include file="../../../admin/header.jsp" %>
<header>

	<nav>
		
		<ul>
				
			<li><a href="<c:url value="/adm/user/logout" />">Logout</a></li>
			<li><a href="<c:url value="/adm/user/create" />">Criar Usuário</a></li>
			<li><a href="<c:url value="/adm/user" />">Listar Usuário</a></li>
			<li><a href="<c:url value="/adm/group/create" />">Criar Grupo</a></li>
			<li><a href="<c:url value="/adm/group" />">Listar Grupos</a></li>
			<li><a href="<c:url value="/adm/permission/create" />">Criar Permissão</a></li>
			<li><a href="<c:url value="/adm/permission" />">Listar Permissões</a></li>
			
		</ul>
	
	</nav>
	
</header>

<%@include file="../../../admin/footer.jsp" %>