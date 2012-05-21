<%@include file="../../../admin/header.jsp" %>

<c:if test="${retorno == true}">

	<p>Configuracao ${tipoSubmit} com sucesso!</p>

</c:if>
<c:if test="${retorno == false}">

	<p>ERRO! Configuracao näo foi ${tipoSubmit}.</p>

</c:if>

	<div id="dialog-confirm">
	   <p>Você realmente deseja deletar essa configuracao?</p>
	</div>
	
<form action="<c:url value="/adm/settings" />" method="post" id="formSettings">
	<table>
		<c:forEach var="config" items="${configs}">
			<tr>
				<td>${config.name}: </td>
				<td><input name="${config.slug}" id="${config.slug}" value="${config.value}" /></td>
			</tr>
		</c:forEach>	
		<tr>
			<td>
				<button name="_method" class="submit" value="put">Editar</button>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>