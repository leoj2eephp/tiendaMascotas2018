<%@include file="../../../header.jsp" %>
<%@include file="../../../menu.jsp" %>

<h2 class="center">Productos</h2>
<form action="${pageContext.servletContext.contextPath}/ProductoServlet" method="post">
    <div class="row">
        <div class="col s6 offset-s2">
            Para crear un nuevo producto, pulse en el botón --> 
            <button type="submit" name="action" class="btn-floating green" value="goToNewProductos">
                <i class="material-icons">add</i>
            </button>
        </div>
    </div>
</form>
<form action="${pageContext.servletContext.contextPath}/ProductoServlet" method="post">
    <div class="container">
        <div class="row">
            <div class="col s12 z-depth-2">
                <table class="striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th>Unidades</th>
                            <th>Descripción</th>
                            <th>Categoría</th>
                            <th>Foto</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.productos}" var="pro">
                            <tr>
                                <td>${pro.idProducto}</td>
                                <td>${pro.nombreProducto}</td>
                                <td>${pro.precioProducto}</td>
                                <td>${pro.unidadesProducto}</td>
                                <td>${pro.descripcionProducto}</td>
                                <td>${pro.categoria.nombreCategoria}</td>
                                <td><i:TagImage arreglo="${pro.fotoProducto}" ancho="50" ></i:TagImage></td>
                                <td>
                                    <button name="action" value="${pro.idProducto}" class="btn-floating">
                                        <i class="material-icons">edit</i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form>

<%@include file="../../../footer.jsp" %>