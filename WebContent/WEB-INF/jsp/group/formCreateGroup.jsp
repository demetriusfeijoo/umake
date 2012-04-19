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
				<select name="group.parentGroupId.id"> 
					<c:forEach var="listGroup" items="${allGroup}">
						<option value="${listGroup.id}">${listGroup.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<c:if test="${group.id == null}"><button name="_method" value="post">cadastrar</button></c:if>
				<c:if test="${group.id != null}"><button name="_method" value="put">editar</button></c:if>
				<c:if test="${group.id != null}"><button name="_method" value="delete">Deletar</button></c:if>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>