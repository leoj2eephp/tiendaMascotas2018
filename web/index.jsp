<%@include file="header.jsp" %>
<form action="InicioServlet" method="post">
    <br><br>
    <div class="row">
        <div class="input-field col s6 offset-s3 z-depth-2">
            <div class="row">
                <div class="input-field col s6">
                    <input id="rut" type="text" class="validate" name="rut">
                    <label for="rut">Rut</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <input id="clave" type="password" class="validate" name="clave">
                    <label for="clave">Clave</label>
                </div>
            </div>
            <div class="row">
                <button type="submit" value="login" name="action" class="btn btn-flat blue white-text right-align">Ingresar</button>
            </div>
            <div class="row">
                <a href="${pageContext.servletContext.contextPath}/PersonaServlet?action=goToRegister">
                    Si no tienes cuenta, regístrate aquí..
                </a>
            </div>
            <div class="row red-text">${requestScope.msg}</div>
        </div>
    </div>
</form>
<%@include file="footer.jsp" %>