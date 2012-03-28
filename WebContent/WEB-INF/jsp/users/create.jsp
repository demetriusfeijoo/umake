<%@include file="../../../admin/header.jsp" %>

<form action="<c:url value="/users/create" />" method="post" id="formCreateUser">

	<table>
	
		<tr>
			<td><input type="text" name="user.name" id="name" /></td>
		</tr>
		<tr>
			<td><input type="email" name="user.email" id="email" /></td>
		</tr>
		<tr>
			<td><input type="text" name="user.login" id="login" /></td>
		</tr>
		<tr>
			<td><input type="password" name="user.password" id="password" maxlength="11" /></td>
		</tr>	
		<tr>
			<td><label>Gostaria de receber email's</label></td>
			<td><input type="radio" name="user.receiveEmail" class="receiveEmail" value="1"/></td>
		</tr>
		<tr>
			<td><label>Não gostaria de receber email's</label></td>
			<td><input type="radio" name="user.receiveEmail" class="receiveEmail" value="0"/></td>
		</tr>
		<tr>
			<td><input type="submit" id="submit_usuario" value="Enviar"/></td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>