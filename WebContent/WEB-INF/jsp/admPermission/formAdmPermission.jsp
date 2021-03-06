<%@include file="../../../admin/header.jsp" %>

<section id="content">

	<div id="dialog-confirm">
	   <p>Voc� realmente deseja excluir essa permiss�o?</p>
	</div>
	
<c:if test="${admPermission == null}"><h1>Cadastrar Permiss�o</h1></c:if>
<c:if test="${admPermission != null}"><h1>Editar Permiss�o</h1></c:if>	
<form action="<c:url value="/adm/permission" />" method="post" id="formAdmPermission">

	<div id="mensagens">
		
		<c:if test="${retorno == true}">
		
			<div id="sucesso">
				<p>Permiss�o ${tipoSubmit} com sucesso!</p>
			</div>

		</c:if>
		<c:if test="${retorno == false}">
		
			<div id="erro">
				<p>ERRO! Permiss�o n�o foi ${tipoSubmit}.</p>
			</div>
			
		</c:if>

	</div>
	
	<c:if test="${admPermission != null}"><input name="admPermission.id" type="hidden" value="${admPermission.id}" /></c:if>	
	<table>
	
		<tr>
			<td><label class="label" for="name">Context: </label></td>
			<td><input type="text" name="admPermission.context" value="${admPermission.context}" id="name" maxlength="45" required autofocus /></td>
		</tr>
		<tr>
			<td><label class="label" for="type">Tipo: </label></td>
			<td>
				<select name="admPermission.type">
					<option value="CREATE" <c:if test="${admPermission.type == 'CREATE'}">selected="selected"</c:if> >CREATE</option>
					<option value="EDIT" <c:if test="${admPermission.type == 'EDIT'}">selected="selected"</c:if> >EDIT</option>
					<option value="VIEW" <c:if test="${admPermission.type == 'VIEW'}">selected="selected"</c:if> >VIEW</option>
					<option value="DELETE" <c:if test="${admPermission.type == 'DELETE'}">selected="selected"</c:if> >DELETE</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<c:if test="${admPermission == null}"><button name="_method" class="submit botoes" value="post"></button></c:if>
				<c:if test="${admPermission.id != null}">
					<button name="_method" class="submit botoes" value="put"></button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<c:if test="${admPermission != null}">

	<form method="post" action="<c:url value="/adm/permission/${admPermission.id}" />" id="formDeleteAdmPermission">
		<input name="btnDeleteAdmPermission" class="delete botoes" id="btnDeleteAdmPermission" value="" type="button" />
		<input name="_method" id="_method" value="delete" type="hidden" />
	</form>
	
</c:if>

</section>

<%@include file="../../../admin/footer.jsp" %>