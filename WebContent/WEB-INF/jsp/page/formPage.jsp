<%@include file="../../../admin/header.jsp" %>

<section id="contentPage">

	<div id="dialog-confirm">
	   <p>Você realmente deseja excluir essa página?</p>
	</div>
	
<c:if test="${page == null}"><h1>Cadastrar Página</h1></c:if>
<c:if test="${page != null}"><h1>Editar Página</h1></c:if>		
<form action="<c:url value="/adm/page" />" method="post" id="formPage">

	<div id="mensagens">
	
		<c:if test="${retorno == true}">
			<div id="sucesso">
				<p>Página ${tipoSubmit} com sucesso!</p>
			</div>
		</c:if>
		<c:if test="${retorno == false}">
			<div id="erro">
				<p>ERRO! Página näo foi ${tipoSubmit}.</p>
			</div>
		</c:if>	
		
	</div>
	
	<c:if test="${page.id != null}">
		<input name="page.id" type="hidden" value="${page.id}" />
	</c:if>	
	<table cellpadding="0" cellspacing="0">
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
			<td><label class="label">Autor:</label></td>
			<td>
				<select name="page.admUser.id">
				
						<option value="${page.admUser.id}">${page.admUser.name}</option>
						
					<c:forEach var="admUser" items="${admUsers}">
						
						<c:if test="${admUser.id != page.admUser.id}">
						
							<option value="${admUser.id}">${admUser.name}</option>
						
						</c:if>
						
					</c:forEach>
				</select>
			</td>
		</tr>		
		<tr>
			<td colspan="2"><textarea name="page.content" id="content" >${page.content}</textarea></td>
		</tr>	
		<tr>
			<td colspan="2">
				<c:if test="${page.id == null}">
					<button name="_method" class="submit botoes" value="post"></button>
				</c:if>
				<c:if test="${page.id != null}">
					<button name="_method" class="submit botoes" value="put"></button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<c:if test="${page != null}">

	<form method="post" action="<c:url value="/adm/page/${page.id}" />" id="formDeletePage">
		<input name="btnDeletePage" id="btnDeletePage" class="botoes delete" value="" type="button" /> 
		<input name="_method" id="_method" value="delete" type="hidden" />
	</form>

</c:if>


</section>

<%@include file="../../../admin/footer.jsp" %>