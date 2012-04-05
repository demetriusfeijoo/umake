<%@include file="../../../admin/header.jsp" %>


<form action="<c:url value="/adm/users" />" method="post" id="formCreateUser">
	<c:if test="${user != null}"><input name="user.id" type="hidden" value="${user.id}" /></c:if>	
	<table>
	
		<tr>
			<td><label for="name">Name: </label></td>
			<td><input type="text" name="user.name" value="${user.name}" id="name" maxlength="45" required autofocus /></td>
		</tr>
		<tr>
			<td><label for="email">E-mail: </label></td>
			<td><input type="email" name="user.email" value="${user.email}" id="email" maxlength="80" required /></td>
		</tr>
		<tr>
			<td><label for="login">Login: </label></td>
			<td><input type="text" name="user.login" value="${user.login}" id="login" maxlength="45" required/></td>
		</tr>
		<tr>
			<td><label for="password">Password: </label></td>
			<td><input type="password" name="user.password" value="${user.password}" id="password" maxlength="45" required/></td>
		</tr>	
		<tr>
			<td><label for="userBlock">Bloqueado: </label></td>
			<td>
			<label for="blockTrue">Sim</label><input type="radio" name="user.userBlock" class="userBlock" id="userBlock" value="1" <c:if test="${user.userBlock == true}">checked="checked"</c:if>  />
			<label for="blockFalse">Não</label><input type="radio" name="user.userBlock" class="userBlock" id="userBlock" value="0"<c:if test="${user.userBlock == false || empty user.userBlock}">checked="checked"</c:if> />
			</td>
		</tr>
		<tr>
			<td><label for="receiveEmail">Gostaria de receber email: </label></td>
			<td>
			<label for="receiveEmailTrue">Sim</label><input type="radio" name="user.receiveEmail" class="receiveEmail" id="receiveEmailTrue" value="1" <c:if test="${user.receiveEmail == true || empty user.receiveEmail }">checked="checked"</c:if>  />
			<label for="receiveEmailFalse">Não</label><input type="radio" name="user.receiveEmail" class="receiveEmail" id="receiveEmailFalse" value="0"<c:if test="${user.receiveEmail == false}">checked="checked"</c:if> />
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td>
				<c:if test="${user.id == null}"><button name="_method" value="post">enviar</button></c:if>
				<c:if test="${user.id != null}"><button name="_method" value="put">enviar</button></c:if>
				<c:if test="${user.id != null}"><button name="_method" value="delete">Deletar</button></c:if>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>