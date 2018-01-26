<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../header.jsp" %>
<h2 class="center">Gestion Persona</h2>
<form action="${pageContext.servletContext.contextPath}/PersonaServlet" method="post">
    <div class="row">
        <div class="col s8 offset-s2">
            <table class="highlight">
                <thead class="blue white-text">
                    <tr>
                        <th>Rut</th>
                        <th>Nombre</th>
                        <th>Perfil</th>
                        <th>Mail</th>
                        <th>Activo</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${applicationScope.listaPersonas}" var="p">
                        <tr>
                            <td>${p.rut}</td>
                            <td>${p.nombre}</td>
                            <td>${p.perfil}</td>
                            <td>${p.mail}</td>
                            <td>${p.activo ? "Sí" : "No"}</td>
                            <td>
                                <button class="btn-floating blue" name="persoRut" value="${p.rut}">
                                    <i class="small material-icons">edit</i>
                                </button>
                                <%--
                                <a href="${pageContext.servletContext.contextPath}/PersonaServlet?action=editar&rut=${p.rut}">Editar</a>
                                --%>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</form>
<%@include file="../../footer.jsp" %>