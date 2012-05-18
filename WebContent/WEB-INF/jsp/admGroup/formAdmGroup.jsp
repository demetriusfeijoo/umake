<c:import url="admin/header.jsp" />

<c:if test="${retorno == true}">

	<p>Grupo ${tipoSubmit} com sucesso!</p>

</c:if>
<c:if test="${retorno == false}">

	<p>ERRO! Grupo näo foi ${tipoSubmit}.</p>

</c:if>

	<div id="dialog-confirm">
	   <p>Você realmente deseja excluir esse grupo?</p>
	</div>
	
<form action="<c:url value="/adm/group" />" method="post" id="formAdmGroup">
	<c:if test="${admGroup != null}"><input name="admGroup.id" type="hidden" value="${admGroup.id}" /></c:if>	
	<table>
	
		<tr>
			<td><label for="name">Name: </label></td>
			<td><input type="text" name="admGroup.name" value="${admGroup.name}" id="name" maxlength="45" autofocus /></td>
		</tr>
		<tr>
			<td><label for="parentGroup">Grupo Pai: </label></td>
			<td>
				<select name="admGroup.parentAdmGroup.id">
				
					<option value="${admGroup.id}">Nenhum</option>
					
					<c:forEach var="admGroupList" items="${allAdmGroups}">
					
						<c:if test="${ admGroup.id != admGroupList.id }">
							<option value="${admGroupList.id}" <c:if test="${ admGroup.parentAdmGroup.id == admGroupList.id }">selected="selected"</c:if> >${admGroupList.name}</option>						
						</c:if>
						
					</c:forEach>
					
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label>Permissoes:</label>
				<br />
				<c:forEach var="permission" items="${permissions}" begin="0" varStatus="count">
					
					<label for="permission_${permission.id}">${permission.context}</label>
					
					<% String ifChecked = ""; %>
					
					<c:forEach var="groupPermission" items="${admGroup.admPermissions}">
													
						<c:if test="${groupPermission.id == permission.id}">
							<% ifChecked = "checked='checked'"; %>
						</c:if>
						
					</c:forEach>
					
					<input type="checkbox" id="permission_${permission.id}" name="permissions[${count.index}].id" <% out.print(ifChecked); %> value="${permission.id}" />
				</c:forEach>			
			</td>
			<td></td>
		</tr>
		<tr>
			<td>
				<c:if test="${admGroup == null}"><button name="_method" class="submit" value="post">Cadastrar</button></c:if>
				<c:if test="${admGroup.id != null}">
					<button name="_method" class="submit" value="put">Editar</button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<c:if test="${admGroup != null}">

	<form method="post" action="<c:url value="/adm/group/${admGroup.id}" />" id="formDeleteAdmGroup">
		<input name="btnDeleteAdmGroup" id="btnDeleteAdmGroup" value="Deletar" type="button" />
		<input name="_method" id="_method" value="delete" type="hidden" />
	</form>
	
</c:if>
<c:import url="admin/footer.jsp" />