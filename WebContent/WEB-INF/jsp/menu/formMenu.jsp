<%@include file="../../../admin/header.jsp" %>

<section id="contentPage">

	<div id="dialog-confirm">
	   <p>Você realmente deseja excluir esse menu?</p>
	</div>
	
<c:if test="${menu == null}"><h1>Cadastrar Menu</h1></c:if>
<c:if test="${menu != null}"><h1>Editar Menu</h1></c:if>		
<form action="<c:url value="/adm/menu" />" method="post" id="formMenu">

	<div id="mensagens">
	
		<c:if test="${retorno == true}">
			<div id="sucesso">
				<p>Menu ${tipoSubmit} com sucesso!</p>
			</div>
		</c:if>
		<c:if test="${retorno == false}">
			<div id="erro">
				<p>ERRO! Menu não foi ${tipoSubmit}.</p>
			</div>
		</c:if>	
		
	</div>
	
	<c:if test="${menu != null}">
		<input name="menu.id" type="hidden" value="${menu.id}" />
	</c:if>	
	<table cellpadding="0" cellspacing="0">
		<!--<tr>
			<td><label class="label">Status:</label></td>
			<td>
				Ativado:<input type="radio" name="page.status" id="ativado" <c:if test="${page.status || page.status == null }">checked="checked"</c:if> value="1" /> 
				Desativado:<input type="radio" name="page.status" id="desativado" <c:if test="${ !page.status || page.status == null }">checked="checked"</c:if> value="0" />
			</td>
		</tr>-->
		<tr>
			<td><label class="label">Nome:</label></td>
			<td><input type="text" name="menu.name" id="name" value="${menu.name}" /></td>
		</tr>
		<tr>
			<td><label class="label">Posição:</label></td>
			<td>
			<select name="menu.position" id="position" value="${menu.position}">
			
				<option value="1">1</option>
				<option value="2">2</option>
				
			</select>
			</td>
		</tr>
		<tr>
			<td>
				<c:if test="${menu == null}">
					<button name="_method" class="submit botoes" value="post"></button>
				</c:if>
				<c:if test="${menu.id != null}">
					<button name="_method" class="submit botoes" value="put"></button>
				</c:if>
			</td>
		</tr>
							
	</table>

</form>

<c:if test="${menu != null}">

	<form method="post" action="<c:url value="/adm/menu/${menu.id}" />" id="formDeleteMenu">
		<input name="btnDeleteMenu" id="btnDeleteMenu" class="botoes delete" value="" type="button" /> 
		<input name="_method" id="_method" value="delete" type="hidden" />
	</form>

</c:if>


</section>

<%@include file="../../../admin/footer.jsp" %>