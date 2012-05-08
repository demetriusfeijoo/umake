<%@include file="../../../admin/header.jsp" %>
<style>
#formAdmUser label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}
</style>
<form action="<c:url value="/adm/user" />" method="post" id="formAdmUser">
	<c:if test="${admUser != null}">
		<input name="admUser.id" type="hidden" value="${admUser.id}" />
	</c:if>	
	<table>
	
		<tr>
			<td><label for="name">Name: </label></td>
			<td><input type="text" name="admUser.name" value="${admUser.name}" id="name" maxlength="45" autofocus /></td>
		</tr>
		<tr>
			<td><label for="email">E-mail: </label></td>
			<td><input type="text" name="admUser.email" value="${admUser.email}" id="email" maxlength="80"  /></td>
		</tr>
		<tr>
			<td><label for="login">Login: </label></td>
			<td><input type="text" name="admUser.login" value="${admUser.login}" id="login" maxlength="45" /></td>
		</tr>
		<tr>
			<td><label for="password">Password: </label></td>
			<td><input type="password" name="admUser.password" value="${admUser.password}" id="password" maxlength="45" /></td>
		</tr>	
		<tr>
			<td><label for="userBlock">Bloqueado: </label></td>
			<td>
			<label for="blockTrue">Sim</label><input type="radio" name="admUser.userBlock" class="userBlock" id="userBlock" value="1" <c:if test="${admUser.userBlock == true}">checked="checked"</c:if>  />
			<label for="blockFalse">Não</label><input type="radio" name="admUser.userBlock" class="userBlock" id="userBlock" value="0"<c:if test="${admUser.userBlock == false || empty admUser.userBlock}">checked="checked"</c:if> />
			</td>
		</tr>
		<tr>
			<td><label for="receiveEmail">Gostaria de receber email: </label></td>
			<td>
			<label for="receiveEmailTrue">Sim</label><input type="radio" name="admUser.receiveEmail" class="receiveEmail" id="receiveEmailTrue" value="1" <c:if test="${admUser.receiveEmail == true || empty admUser.receiveEmail }">checked="checked"</c:if>  />
			<label for="receiveEmailFalse">Não</label><input type="radio" name="admUser.receiveEmail" class="receiveEmail" id="receiveEmailFalse" value="0"<c:if test="${admUser.receiveEmail == false}">checked="checked"</c:if> />
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td>
				<label>Grupos:</label>
				<br />
				<c:forEach var="admGroup" items="${admGroups}" begin="0" varStatus="count">
				
					<label for="group_${admGroup.id}">${admGroup.name}</label>
					
					<% String ifChecked = ""; %>	
					
					<c:forEach var="userGroups" items="${admUser.admGroups}">
													
						<c:if test="${userGroups.id == admGroup.id}">
							<% ifChecked = "checked='checked'"; %>
						</c:if>
						
					</c:forEach>
					
					<input type="checkbox" id="group_${admGroup.id}" name="admGroups[${count.index}].id" <% out.print(ifChecked); %> value="${admGroup.id}"/>
														
				</c:forEach>			
			</td>
			<td></td>
		</tr>
		<tr>
			<td>
				<label>Permissoes:</label>
				<br />
				<c:forEach var="admPermission" items="${admPermissions}" begin="0" varStatus="count">
					
					<label for="permission_${admPermission.id}">${admPermission.context}</label>
					
					<% String ifChecked = ""; %>
					
					<c:forEach var="userPermissions" items="${admUser.admPermissions}">
													
						<c:if test="${userPermissions.id == admPermission.id}">
							<% ifChecked = "checked='checked'"; %>
						</c:if>
						
					</c:forEach>
					
					<input type="checkbox"  id="permission_${admPermission.id}" name="admPermissions[${count.index}].id" <% out.print(ifChecked); %> value="${admPermission.id}" />
				</c:forEach>			
			</td>
			<td></td>
		</tr>
		<tr>
			<td>
				<c:if test="${admUser.id == null}"><button name="_method" value="post">Criar</button></c:if>
				<c:if test="${admUser.id != null}">
					<button name="_method" value="put">Editar</button>
					<button name="_method" value="delete">Deletar</button>
				</c:if>
			</td>
		</tr>
		
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>