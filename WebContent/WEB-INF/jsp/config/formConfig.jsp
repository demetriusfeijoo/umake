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
	
<form action="<c:url value="/adm/config" />" method="post" id="formConfig">
	<c:if test="${config != null}"><input name="config.id" type="hidden" value="${config.id}" /></c:if>	
	<table>
		<tr>
			<td>Título: </td>
			<td><input name="config.title" id="title" value="${config.title}" /></td>
		</tr>	
		<tr>
			<td>
				<c:if test="${config == null}"><button name="_method" class="submit" value="post">Criar</button></c:if>
				<c:if test="${config.id != null}">
					<button name="_method" class="submit" value="put">Editar</button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>