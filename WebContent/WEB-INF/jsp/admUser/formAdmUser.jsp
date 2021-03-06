﻿<%@include file="../../../admin/header.jsp"%>

<section id="content">

<div id="dialog-confirm">
	<p>Você realmente deseja excluir esse usuário?</p>
</div>

<c:if test="${admUser == null}"><h1>Cadastrar Usuário</h1></c:if>
<c:if test="${admUser != null}"><h1>Editar Usuário</h1></c:if>

<form action="<c:url value="/adm/user" />" method="post" id="formAdmUser">

	<div id="mensagens">
		
		<c:if test="${retorno == true}">
		
			<div id="sucesso">
				<p>Usuário ${tipoSubmit} com sucesso!</p>
			</div>

		</c:if>
		<c:if test="${retorno == false}">
		
			<div id="erro">
				<p>ERRO! Usuário näo foi ${tipoSubmit}.</p>
			</div>
			
		</c:if>

	</div>

	<c:if test="${admUser != null}">
		<input name="admUser.id" type="hidden" value="${admUser.id}" />
	</c:if>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><label for="name" class="label">Name: </label>
			</td>
			<td><input type="text" name="admUser.name"
				value="${admUser.name}" id="name" maxlength="45" autofocus />
			</td>
		</tr>
		<tr>
			<td><label for="email" class="label">E-mail: </label>
			</td>
			<td><input type="text" name="admUser.email"
				value="${admUser.email}" id="email" maxlength="80" />
			</td>
		</tr>
		<tr>
			<td><label for="login" class="label">Login: </label></td>
			<td><input type="text" name="admUser.login"
				value="${admUser.login}" id="login" maxlength="45" />
			</td>
		</tr>
		<tr>
			<td><label for="password" class="label">Password: </label></td>
			<td><input type="password" name="admUser.password"
				value="${admUser.password}" id="password" maxlength="45" />
			</td>
		</tr>
		<tr>
			<td><label for="userBlock" class="label">Bloqueado: </label></td>
			<td><label for="blockTrue">Sim</label><input type="radio"
				name="admUser.status" class="userBlock" id="userBlock" value="2"
				<c:if test="${admUser.status == 2}">checked="checked"</c:if> />
				<label for="blockFalse">Não</label><input type="radio"
				name="admUser.status" class="userBlock" id="userBlock" value="1"
				<c:if test="${admUser.status != 2 || empty admUser.status}">checked="checked"</c:if> />
			</td>
		</tr>
		<tr>
			<td><label for="receiveEmail" class="label">Gostaria de
					receber email: </label>
			</td>
			<td><label for="receiveEmailTrue">Sim</label><input type="radio"
				name="admUser.receiveEmail" class="receiveEmail"
				id="receiveEmailTrue" value="1"
				<c:if test="${admUser.receiveEmail == true || empty admUser.receiveEmail }">checked="checked"</c:if> />
				<label for="receiveEmailFalse">Não</label><input type="radio"
				name="admUser.receiveEmail" class="receiveEmail"
				id="receiveEmailFalse" value="0"
				<c:if test="${admUser.receiveEmail == false}">checked="checked"</c:if> />
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td><label class="label">Grupos:</label></td>
		</tr>
		<tr>
			 <c:forEach var="admGroup" items="${admGroups}" begin="0" varStatus="count">
			
					<c:if test="${count.index % 4 == 0 && count.index != 0}">
					
						<% out.print("</tr><tr>"); %>
										
					</c:if>
						
					<td style="padding:5px; border:1px solid gray;">
						<label for="group_${admGroup.id}">${admGroup.name}</label>
	
						<%
							String ifChecked = "";
						%>
	
						<c:forEach var="userGroups" items="${admUser.admGroups}">
	
							<c:if test="${userGroups.id == admGroup.id}">
								<%
									ifChecked = "checked='checked'";
								%>
							</c:if>
	
						</c:forEach>
	
						<input type="checkbox" id="group_${admGroup.id}"
							name="admGroups[${count.index}].id" <%out.print(ifChecked);%>
							value="${admGroup.id}" />
					</td>
				</c:forEach>
		</tr>
		<tr>
			<td><label class="label">Permissoes:</label></td>
		</tr>
		<tr>
			 <c:forEach var="admPermission" items="${admPermissions}" begin="0" varStatus="count">
			
					<c:if test="${count.index % 4 == 0 && count.index != 0}">
					
						<% out.print("</tr><tr>"); %>
										
					</c:if>
						
					<td style="padding:5px; border:1px solid gray;">
						<label for="permission_${admPermission.id}">${admPermission.context} - ${admPermission.type}</label>
	
						<%
							String ifChecked = "";
						%>
	
						<c:forEach var="userPermissions" items="${admUser.admPermissions}">
	
							<c:if test="${userPermissions.id == admPermission.id}">
								<%
									ifChecked = "checked='checked'";
								%>
							</c:if>
	
						</c:forEach>
	
						<input type="checkbox" id="permission_${admPermission.id}"
							name="admPermissions[${count.index}].id"
							<%out.print(ifChecked);%> value="${admPermission.id}" />
					</td>
				</c:forEach>
		</tr>
		<tr>
			<td colspan="2">
				<c:if test="${admUser.id == null}">
					<button name="_method" class="submit botoes" value="post"></button>
				</c:if> 
				<c:if test="${admUser.id != null}">
					<button name="_method" class="submit botoes" value="put"></button>
				</c:if>
			</td>
		</tr>


	</table>

</form>

<c:if test="${admUser != null}">

	<form method="post" action="<c:url value="/adm/user/${admUser.id}" />" id="formDeleteAdmUser">
		<input name="btnDeleteAdmUser" id="btnDeleteAdmUser" class="botoes delete" value="" type="button" /> 
		<input name="_method" id="_method" value="delete" type="hidden" />
	</form>

</c:if> 

</section>

<%@include file="../../../admin/footer.jsp"%>