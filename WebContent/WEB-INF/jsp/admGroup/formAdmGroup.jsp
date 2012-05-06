<%@include file="../../../admin/header.jsp" %>

<form action="<c:url value="/adm/group" />" method="post" id="formAdmGroup">
	<c:if test="${admGroup != null}"><input name="admGroup.id" type="hidden" value="${admGroup.id}" /></c:if>	
	<table>
	
		<tr>
			<td><label for="name">Name: </label></td>
			<td><input type="text" name="admGroup.name" value="${admGroup.name}" id="name" maxlength="45" autofocus /></td>
		</tr>
		<tr>
			<td><label for="parentGroup">Grupo Pai: </label></td>
			<td>
				${admGroup.parentAdmGroup.name}
				<select name="admGroup.parentAdmGroup.id">
					    <option value="${$admGroup.id}">Nenhum</option>
					<c:forEach var="admGroup" items="${allAdmGroups}">
						<option value="${admGroup.id}">${admGroup.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<c:if test="${admGroup.id == null}"><button name="_method" value="post">Criar</button></c:if>
				<c:if test="${admGroup.id != null}">
					<button name="_method" value="put">Editar</button>
					<button name="_method" value="delete">Deletar</button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>