<%@include file="../../../admin/header.jsp" %>

<form action="<c:url value="/adm/permission" />" method="post" id="formAdmPermission">
	<c:if test="${admPermission != null}"><input name="admPermission.id" type="hidden" value="${admPermission.id}" /></c:if>	
	<table>
	
		<tr>
			<td><label for="name">Context: </label></td>
			<td><input type="text" name="admPermission.context" value="${admPermission.context}" id="name" maxlength="45" required autofocus /></td>
		</tr>
		<tr>
			<td><label for="type">Tipo: </label></td>
			<td>
				${admPermission.type}
				<select name="admPermission.type">
					<option value="CREATE">CREATE</option>
					<option value="EDIT">EDIT</option>
					<option value="VIEW">VIEW</option>
					<option value="DELETE">DELETE</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<c:if test="${admPermission.id == null}"><button name="_method" value="post">Criar</button></c:if>
				<c:if test="${admPermission.id != null}">
					<button name="_method" value="put">Editar</button>
					<button name="_method" value="delete">Deletar</button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>