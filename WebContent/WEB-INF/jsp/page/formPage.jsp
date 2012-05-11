<%@include file="../../../admin/header.jsp" %>

<c:if test="${retorno == true}">

	<p>Página ${tipoSubmit} com sucesso!</p>

</c:if>
<c:if test="${retorno == false}">

	<p>ERRO! Página näo foi ${tipoSubmit}.</p>

</c:if>

	<div id="dialog-confirm">
	   <p>Você realmente deseja excluir essa página?</p>
	</div>
	
<form action="<c:url value="/adm/page" />" method="post" id="formPage">
	<c:if test="${page != null}"><input name="page.id" type="hidden" value="${page.id}" /></c:if>	
	<table>
		<tr>
			<td>Status: </td>
			<td><label for="ativado">Ativado:</label><input type="radio" name="page.status" id="ativado" value="1" /> <label for="desativado">Desativado:</label><input type="radio" name="page.status" id="desativado" value="0" /> </td>
		</tr>
		<tr>
			<td>Título: </td>
			<td><input name="page.title" id="title" value="${page.title}" /></td>
		</tr>
		<tr>
			<td colspan="2"><textarea name="page.content" id="content" >${page.content}</textarea></td>
		</tr>	
		<tr>
			<td>
				<c:if test="${page == null}"><button name="_method" class="submit" value="post">Criar</button></c:if>
				<c:if test="${page.id != null}">
					<button name="_method" class="submit" value="put">Editar</button>
					<input name="submitDelete" id="submitDelete" type="button" value="Deletar"  />
					<input name="_method" value="delete" type="hidden" />
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>