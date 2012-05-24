<%@include file="../../../admin/header.jsp" %>
<section id="content">

	<div id="dialog-confirm">
	   <p>Você realmente deseja deletar essa configuracao?</p>
	</div>
	
<form action="<c:url value="/adm/settings" />" method="post" id="formSettings">

	<div id="mensagens">
		
		<c:if test="${retorno == true}">
		
			<div id="sucesso">
				<p>Configurações ${tipoSubmit} com sucesso!</p>
			</div>

		</c:if>
		<c:if test="${retorno == false}">
		
			<div id="erro">
				<p>ERRO! Não foi possível ${tipoSubmit} as Configurações.</p>
			</div>
			
		</c:if>

	</div>
	<table>
		<c:forEach var="config" items="${configs}">
			<tr>
				<td><label class="label">${config.name}: </label></td>
				<td><input type="text" name="${config.slug}" id="${config.slug}" value="${config.value}" /></td>
			</tr>
		</c:forEach>
		<tr>
			<td><label class="label">Template Ativo: </label> </td>
			<td>
				<select name="current-template">
					<c:forEach var="template" items="${templates}">
						<option <c:if test="${template.name eq currentTemplate}">selected="selected"</c:if> value="${template.name}">${template.name}</option>
					</c:forEach>				
				</select>
			</td>
		</tr>	
		<tr>
			<td>
				<button name="_method" class="submit botoes" value="put"></button>
			</td>
		</tr>
							
	</table>

</form>
</section>
<%@include file="../../../admin/footer.jsp" %>