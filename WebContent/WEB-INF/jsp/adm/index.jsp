<%@include file="../../../admin/header.jsp" %>
<header>

	<div>
	
		<c:if test="${errorPermissions != null }">
			<h3>Essa ação necessita da(s) permissão(es) com</br> 
			
				<c:forEach var="errorPermission" items="${errorPermissions}">
				
					Contexto: ${ errorPermission.context } - Tipo: ${ errorPermission.type }
					</br>	
				</c:forEach>
			
			</h3>
		</c:if>
	</div>
	
	<nav>
		
		<ul>
				
			<li><a href="<c:url value="/" />">Ir para o site</a></li>
			<li><a href="<c:url value="/adm/user/logout" />">Logout</a></li>
			<li><a href="<c:url value="/adm/user/create" />">Criar Usuário</a></li>
			<li><a href="<c:url value="/adm/user" />">Listar Usuários</a></li>
			<li><a href="<c:url value="/adm/group/create" />">Criar Grupo</a></li>
			<li><a href="<c:url value="/adm/group" />">Listar Grupos</a></li>
			<li><a href="<c:url value="/adm/permission/create" />">Criar Permissão</a></li>
			<li><a href="<c:url value="/adm/permission" />">Listar Permissões</a></li>
			<li><a href="<c:url value="/adm/page/create" />">Criar página</a></li>
			<li><a href="<c:url value="/adm/page" />">Listar páginas</a></li>
			
		</ul>
	
	</nav>
	
</header>

<%@include file="../../../admin/footer.jsp" %>