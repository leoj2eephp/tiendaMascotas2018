<%@include file="../../header.jsp" %>
<%@include file="../../menu.jsp" %>

<h2 class="center">Carrito</h2>
<form action="${pageContext.servletContext.contextPath}/ProductoServlet" method="post">
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
                <th>Cantidad</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${sessionScope.carrito}" var="pro">
                <tr>
                    <td>${pro.idProducto}</td>
                    <td>${pro.nombreProducto}</td>
                    <td>${pro.precioProducto}</td>
                    <td>${pro.unidadesProducto}</td>
                    <td>${pro.descripcionProducto}</td>
                    <td>${pro.categoria.nombreCategoria}</td>
                    <td><i:TagImage arreglo="${pro.fotoProducto}" ancho="50" ></i:TagImage></td>
                        <td>
                            <input type="number" min="1" name="cantidad" style="width: 36px;" />
                        </td>
                    </tr>
            </c:forEach>
            <tr>
                <td colspan="8" class="right-align">
                    <button type="submit" class="btn-flat orange white-text" name="action" value="buy">Comprar 
                        <i class="material-icons">shopping_cart</i>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>
</form>
<%@include file="../../footer.jsp" %>