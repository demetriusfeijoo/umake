<%@include file="../../../admin/header.jsp" %>

<form action="<c:url value="/adm/groups" />" method="post" id="formCreateGroup">
	<c:if test="${group != null}"><input name="group.id" type="hidden" value="${group.id}" /></c:if>	
	<table>
	
		<tr>
			<td><label for="name">Name: </label></td>
			<td><input type="text" name="group.name" value="${group.name}" id="name" maxlength="45" required autofocus /></td>
		</tr>
		<tr>
			<td><label for="parentGroup">Grupo Pai: </label></td>
			<td>
				<select name="group.parentGroup.id">
					<c:forEach var="group" items="${allGroups}">
						<option value="${group.id}">${group.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<c:if test="${group.id == null}"><button name="_method" value="post">Criar</button></c:if>
				<c:if test="${group.id != null}">
					<button name="_method" value="put">Editar</button>
					<button name="_method" value="delete">Deletar</button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>