<%@include file="../../../admin/header.jsp" %>

<div>
	<form method="POST" action="<c:url value="/users/login" />">
		<label for="login">Login: </label>
		<input name="user.login" id="login" /><br />
		<label for="pwd">Password: </label>
		<input type="password" name="user.password" id="pwd" /><br />
		<input type="submit" />
	</form>	
</div>

<%@include file="../../../admin/footer.jsp" %>