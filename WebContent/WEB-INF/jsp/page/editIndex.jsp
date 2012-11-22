<%@include file="../../../admin/header.jsp" %>

<section id="contentPage">
	
<h1>Editar Index</h1>
<form action="<c:url value="/adm/page/editIndex" />" method="post" id="formPageIndex">

	<div id="mensagens">
	
		<c:if test="${retorno == true}">
			<div id="sucesso">
				<p>Index ${tipoSubmit} com sucesso!</p>
			</div>
		</c:if>
		<c:if test="${retorno == false}">
			<div id="erro">
				<p>ERRO! Index não foi ${tipoSubmit}.</p>
			</div>
		</c:if>	
		
	</div>
	
	<input name="indexPage.id" type="hidden" value="${indexPage.id}" />
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><label class="label">Status:</label></td>
			<td>
				<label for="ativado">Ativado</label><input type="radio" name="indexPage.status" id="ativado" <c:if test="${indexPage.status || indexPage.status == null }">checked="checked"</c:if> value="1" /> 
				<label for="desativado">Desativado</label><input type="radio" name="indexPage.status" id="desativado" <c:if test="${ !indexPage.status || indexPage.status == null }">checked="checked"</c:if> value="0" />
			</td>
		</tr>		
		<tr>
			<td><label class="label">Título:</label></td>
			<td><input type="text" name="indexPage.title" id="title" value="${indexPage.title}" /></td>
		</tr>		
		<tr>
			<td colspan="2"><textarea name="indexPage.content" id="content" >${indexPage.content}</textarea></td>
		</tr>	
		<tr>
			<td colspan="2">
				<button name="_method" class="submit botoes" value="put"></button>
			</td>
		</tr>
							
	</table>

</form>

</section>

<%@include file="../../../admin/footer.jsp" %>