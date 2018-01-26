<%@include file="../../header.jsp" %>
<%@include file="../../menu.jsp" %>

<h2 class="center">Catálogo</h2>

<form action="${pageContext.servletContext.contextPath}/ProductoServlet">
    <input type="hidden" name="action" value="addToCart" />
    <div class="row">
        <c:forEach items="${requestScope.catalogo}" var="p">
            <div class="col s3">
                <div class="card">
                    <div class="card-image">
                        <i:TagImage arreglo="${p.fotoProducto}" ancho="50" ></i:TagImage>
                        <button class="btn-floating halfway-fab waves-effect waves-light red" name="idProd" type="submit" value="${p.idProducto}">
                            <i class="material-icons">pets</i>
                        </button>
                    </div>
                    <div class="card-content small">
                        <h5>${p.nombreProducto}</h5>
                        <p class="grey-text">$${p.precioProducto}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</form>

<%@include file="../../footer.jsp" %>