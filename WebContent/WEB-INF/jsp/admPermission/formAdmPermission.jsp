<%@include file="../../../admin/header.jsp" %>

<c:if test="${retorno == true}">

	<p>Permiss�o ${tipoSubmit} com sucesso!</p>

</c:if>
<c:if test="${retorno == false}">

	<p>ERRO! Permiss�o n�o foi ${tipoSubmit}.</p>

</c:if>

	<div id="dialog-confirm">
	   <p>Voc� realmente deseja excluir essa permiss�o?</p>
	</div>
	
<form action="<c:url value="/adm/permission" />" method="post" id="formAdmPermission">
	<c:if test="${admPermission != null}"><input name="admPermission.id" type="hidden" value="${admPermission.id}" /></c:if>	
	<table>
	
		<tr>
			<td><label for="name">Context: </label></td>
			<td><input type="text" name="admPermission.context" value="${admPermission.context}" id="name" maxlength="45" required autofocus /></td>
		</tr>
		<tr>
			<td><label for="type">Tipo: </label></td>
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
				<c:if test="${admPermission == null}"><button name="_method" class="submit" value="post">Criar</button></c:if>
				<c:if test="${admPermission.id != null}">
					<button name="_method" class="submit" value="put">Editar</button>
					<input name="submitDelete" id="submitDelete" type="button" value="Deletar"  />
					<input name="_method" value="delete" type="hidden" />
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>