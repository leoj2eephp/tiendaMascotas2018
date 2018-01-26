<%@include file="../../header.jsp" %>
<%@include file="../../menu.jsp" %>

<c:choose>
    <c:when test="${sessionScope.persona != null}">
        <h2>Bienvenid@ ${sessionScope.persona.nombreUser}</h2>
        <a href="${pageContext.servletContext.contextPath}/cerrar.jsp">Cerrar Sesi�n</a>
    </c:when>
    <c:otherwise>
        Error!
    </c:otherwise>
</c:choose>

<%@include file="../../footer.jsp" %>