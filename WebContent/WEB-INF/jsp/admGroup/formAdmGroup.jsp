<%@include file="../../../admin/header.jsp" %>

<section id="content">

	<div id="dialog-confirm">
	   <p>Você realmente deseja excluir esse grupo?</p>
	</div>
	
<c:if test="${admGroup == null}"><h1>Cadastrar Grupo</h1></c:if>
<c:if test="${admGroup != null}"><h1>Editar Grupo</h1></c:if>
<form action="<c:url value="/adm/group" />" method="post" id="formAdmGroup">

	<div id="mensagens">
		
		<c:if test="${retorno == true}">
		
			<div id="sucesso">
				<p>Grupo ${tipoSubmit} com sucesso!</p>
			</div>

		</c:if>
		<c:if test="${retorno == false}">
		
			<div id="erro">
				<p>ERRO! Grupo näo foi ${tipoSubmit}.</p>
			</div>
			
		</c:if>

	</div>
	
	<c:if test="${admGroup != null}"><input name="admGroup.id" type="hidden" value="${admGroup.id}" /></c:if>	
	<table>
	
		<tr>
			<td><label for="name" class="label">Name: </label></td>
			<td><input type="text" name="admGroup.name" value="${admGroup.name}" id="name" maxlength="45" autofocus /></td>
		</tr>
		<tr>
			<td><label for="parentGroup" class="label">Grupo Pai: </label></td>
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
				<label class="label">Permissoes:</label>
			</td>
		</tr>
		
		<c:forEach var="permission" items="${permissions}" begin="0" varStatus="count">

			<c:if test="${count.index % 4 == 0 && count.index != 0}">
					
				<% out.print("</tr><tr>"); %>
										
			</c:if>
							
			<td style="padding:5px; border:1px solid gray;">
					
					<label for="permission_${permission.id}">${permission.context} - ${permission.type}</label>
					
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
				<c:if test="${admGroup == null}"><button name="_method" class="submit botoes" value="post"></button></c:if>
				<c:if test="${admGroup.id != null}">
					<button name="_method" class="submit botoes" value="put"></button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<c:if test="${admGroup != null}">

	<form method="post" action="<c:url value="/adm/group/${admGroup.id}" />" id="formDeleteAdmGroup">
		<input name="btnDeleteAdmGroup" class="botoes delete" id="btnDeleteAdmGroup" value="" type="button" />
		<input name="_method" id="_method" value="delete" type="hidden" />
	</form>
	
</c:if>
</section>
<%@include file="../../../admin/footer.jsp" %>