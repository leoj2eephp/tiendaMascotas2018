<%@include file="../../../header.jsp" %>
<%@include file="../../../menu.jsp" %>

<h2 class="center">Crear Producto</h2>

<div class="container">
    <div class="row">
        <div class="col s6 offset-s3">
            <div class="card-panel">
                <form action="${pageContext.servletContext.contextPath}/ProductoServlet" method="post" enctype="multipart/form-data">
                    <label>Nombre</label>
                    <input type="text" name="nombre" />

                    <label>Precio</label>
                    <input type="number" name="precio" />

                    <label>Unidad</label>
                    <input type="number" name="unidad" />

                    <label>Descripción</label>
                    <textarea name="desc" class="materialize-textarea"></textarea>

                    <label>Categoria</label>
                    <select name="categoria">
                        <c:forEach items="${requestScope.categorias}" var="c">
                            <option value="${c.idCategoria}">${c.nombreCategoria}</option>
                        </c:forEach>
                    </select>

                    <div class="file-field input-field">
                        <div class="btn">
                            <span>Foto</span>
                            <input type="file" name="foto">
                        </div>
                        <div class="file-path-wrapper">
                            <input class="file-path validate" type="text">
                        </div>
                    </div>

                    <button type="submit" name="action" value="nuevoProducto" class="btn-flat blue white-text">Crear Producto</button>
                    <label class="red-text">${requestScope.msg}</label>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="../../../footer.jsp" %>