<%@include file="../../../admin/header.jsp" %>

<section id="contentPage">
	
<h1>Editar CSS</h1>		
<form action="<c:url value="/adm/template/save_css" />" method="post" id="formCss">
		<textarea name="Template.cssFiles" id="code">
		${cssFile}
		</textarea>
</form>

	<form method="post" action="<c:url value="/adm/template/${template.css}" />" id="formEditCss">
		<input name="btnEditCss" id="btnEditCss" class="botoes edit" value="" type="button" /> 
		<input name="_method" id="_method" value="edit" type="hidden" />
	</form>

</section>

<%@include file="../../../admin/footer.jsp" %>