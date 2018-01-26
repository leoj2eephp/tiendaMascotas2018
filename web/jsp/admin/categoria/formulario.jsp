<%@include file="../../../header.jsp" %>
<%@include file="../../../menu.jsp" %>

<h2 class="center">Categorías</h2>

<div class="container">
    <div class="row">
        <div class="col s6 offset-s3">
            <div class="card-panel">
                <form action="${pageContext.servletContext.contextPath}/InicioServlet" method="post">
                    <label>Nombre</label>
                    <input type="text" name="nombre" />
                    <button type="submit" name="action" value="nuevaCategoria" class="btn-flat blue white-text">Crear Categoría</button>
                    <label class="red-text">${requestScope.msg}</label>
                </form>
            </div>
            <div class="row">
                <div class="col s6 offset-s3 z-depth-2">
                    <table class="striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Categoría</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.categorias}" var="cat">
                                <tr>
                                    <td>${cat.idCategoria}</td>
                                    <td>${cat.nombreCategoria}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../../../footer.jsp" %>