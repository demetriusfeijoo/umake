<%@include file="../../../admin/header.jsp" %>

<section id="content">

	<div>
        
		<c:if test="${errorPermissions != null }">
			<h3>Essa ação necessita da(s) permissão(es) com</br>

				<c:forEach var="errorPermission" items="${errorPermissions}">

					Contexto: ${ errorPermission.context } - Tipo: ${ errorPermission.type }
					</br>
				</c:forEach>

			</h3>
		</c:if>
        
	</div>

</section>	

<%@include file="../../../admin/footer.jsp" %>