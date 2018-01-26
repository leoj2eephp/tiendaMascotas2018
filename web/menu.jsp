<nav>
    <div class="nav-wrapper blue">
        <a href="#" class="brand-logo">
            <c:if test="${not empty sessionScope.admin}">
                Bienvenid@ ${sessionScope.admin.nombreUser}
            </c:if>
            <c:if test="${not empty sessionScope.persona}">
                Bienvenid@ ${sessionScope.person.nombreUser}
            </c:if>
        </a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <c:if test="${not empty sessionScope.admin}">
                <li><a href="${pageContext.servletContext.contextPath}/InicioServlet?action=misDatos">Mis Datos</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/ProductoServlet?action=productos">Gestion Productos</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/InicioServlet?action=categorias">Gestion Categorías</a></li>
                <li><a href="cerrar.jsp">Cerrar sesion</a></li>
            </c:if>
            <c:if test="${not empty sessionScope.persona}">
                <li><a href="${pageContext.servletContext.contextPath}/ProductoServlet?action=catalogo">Catálogo</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/InicioServlet?action=misDatos">Mis Datos</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/ProductoServlet?action=myCart">
                        <button type="submit" class="btn-floating orange">
                            <i class="material-icons">add_shopping_cart</i>
                        </button>
                    </a>
                </li>
                (${sessionScope.cant})
                <li><a href="cerrar.jsp">Cerrar sesion</a></li>
            </c:if>
        </ul>
    </div>
</nav>