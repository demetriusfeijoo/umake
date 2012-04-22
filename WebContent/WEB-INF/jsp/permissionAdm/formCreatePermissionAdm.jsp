<%@include file="../../../admin/header.jsp" %>

<form action="<c:url value="/adm/permissions" />" method="post" id="formCreatePermissionAdm">
	<c:if test="${permissionAdm != null}"><input name="permissionAdm.id" type="hidden" value="${permissionAdm.id}" /></c:if>	
	<table>
	
		<tr>
			<td><label for="name">Context: </label></td>
			<td><input type="text" name="permissionAdm.context" value="${permissionAdm.context}" id="name" maxlength="45" required autofocus /></td>
		</tr>
		<tr>
			<td><label for="type">Tipo: </label></td>
			<td>
				${permissionAdm.type}
				<select name="permissionAdm.type">
					<option value="CREATE">CREATE</option>
					<option value="EDIT">EDIT</option>
					<option value="VIEW">VIEW</option>
					<option value="DELETE">DELETE</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<c:if test="${permissionAdm.id == null}"><button name="_method" value="post">Criar</button></c:if>
				<c:if test="${permissionAdm.id != null}">
					<button name="_method" value="put">Editar</button>
					<button name="_method" value="delete">Deletar</button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>