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
			<td><input type="text" name="group.parentGroup" value="${group.parentGroup}" id="parentGroup" maxlength="80" required /></td>
			<td>
				<select>
					<c:forEach var="allGroups" items="${allGroup}">
						<option>${allGroups.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<c:if test="${group.id == null}"><button name="_method" value="post">enviar</button></c:if>
				<c:if test="${group.id != null}"><button name="_method" value="put">enviar</button></c:if>
				<c:if test="${group.id != null}"><button name="_method" value="delete">Deletar</button></c:if>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>