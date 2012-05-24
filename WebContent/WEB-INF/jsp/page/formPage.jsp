<%@include file="../../../admin/header.jsp" %>

<section id="content">
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
			<td><label class="label">Status:</label></td>
			<td>
				Ativado:<input type="radio" name="page.status" id="ativado" <c:if test="${page.status || page.status == null }">checked="checked"</c:if> value="1" /> 
				Desativado:<input type="radio" name="page.status" id="desativado" <c:if test="${ !page.status || page.status == null }">checked="checked"</c:if> value="0" />
			</td>
		</tr>
		<tr>
			<td><label class="label">Título:</label></td>
			<td><input type="text" name="page.title" id="title" value="${page.title}" /></td>
		</tr>
		<tr>
			<td><label class="label">Ordem:</label></td>
			<td><input type="text" name="page.ordered" id="ordered" value="${page.ordered}" /></td>
		</tr>
		<tr>
			<td colspan="2"><textarea name="page.content" id="content2" >${page.content}</textarea></td>
		</tr>	
		<tr>
			<td>
				<c:if test="${page == null}"><button name="_method" class="submit botoes" value="post"></button></c:if>
				<c:if test="${page.id != null}">
					<button name="_method" class="submit botoes" value="put"></button>
					<input name="submitDelete" id="submitDelete" class="botoes delete" type="button" value=""  />
					<input name="_method" value="delete" type="hidden" />
				</c:if>
			</td>
		</tr>
							
	</table>

</form>
</section>
<%@include file="../../../admin/footer.jsp" %>