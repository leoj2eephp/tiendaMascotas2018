<%@include file="../../header.jsp" %>
<%@include file="../../menu.jsp" %>

<c:choose>
    <c:when test="${sessionScope.admin != null}">
        <h2>Bienvenid@ ${sessionScope.admin.nombreUser} (Administrador)</h2>
        <a href="${pageContext.servletContext.contextPath}/cerrar.jsp">Cerrar Sesión</a>
    </c:when>
    <c:otherwise>
        Error!
    </c:otherwise>
</c:choose>
        
<%@include file="../../footer.jsp" %>