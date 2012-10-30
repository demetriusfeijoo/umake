<%@include file="../../../admin/header.jsp" %>

<section id="contentPage">

	<div id="dialog-confirm">
	   <p>Você realmente deseja excluir esse link?</p>
	</div>
	
<c:if test="${menuLink == null}"><h1>Cadastrar Link</h1></c:if>
<c:if test="${menuLink != null}"><h1>Editar Link</h1></c:if>		
<form action="<c:url value="/adm/link" />" method="post" id="formMenuLink">

	<div id="mensagens">
	
		<c:if test="${retorno == true}">
			<div id="sucesso">
				<p>Link ${tipoSubmit} com sucesso!</p>
			</div>
		</c:if>
		<c:if test="${retorno == false}">
			<div id="erro">
				<p>ERRO! Link näo foi ${tipoSubmit}.</p>
			</div>
		</c:if>	
		
	</div>
	
	<c:if test="${menuLink.id != null}">
		<input name="menuLink.id" type="hidden" value="${menuLink.id}" />
	</c:if>	
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><label class="label">Nome:</label></td>
			<td><input type="text" name="menuLink.value" id="value" value="${menuLink.value}" /></td>
		</tr>
		<tr>
			<td><label class="label">Ordem:</label></td>
			<td><input type="text" name="menuLink.position" id="ordered" value="${menuLink.position}" /></td>
		</tr>		
		<tr>
			<td><label class="label">Url:</label></td>
			<td><input type="text" name="menuLink.url" id="ordered" value="${menuLink.url}" /></td>
		</tr>	
		<tr>
			<td colspan="2">
				<c:if test="${menuLink.id == null}">
					<button name="_method" class="submit botoes" value="post"></button>
				</c:if>
				<c:if test="${menuLink.id != null}">
					<button name="_method" class="submit botoes" value="put"></button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<c:if test="${menuLink != null}">

	<form method="post" action="<c:url value="/adm/link/${menuLink.id}" />" id="formDeleteLink">
		<input name="btnDeleteLink" id="btnDeleteLink" class="botoes delete" value="" type="button" /> 
		<input name="_method" id="_method" value="delete" type="hidden" />
	</form>

</c:if>


</section>

<%@include file="../../../admin/footer.jsp" %>