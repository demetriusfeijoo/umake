<%@include file="../../../admin/header.jsp" %>

<section id="contentPage">
	
<h1>Editar CSS</h1>		

<form action="<c:url value="/adm/template/saveCss" />" method="post" id="formCss">

	<textarea name="cssContent" id="code" class="textareaCancelTinyMace">
		${cssFile}
	</textarea>

	<button name="_method" class="submit botoes" value="put"></button>
	
</form>

</section>

<%@include file="../../../admin/footer.jsp" %>

