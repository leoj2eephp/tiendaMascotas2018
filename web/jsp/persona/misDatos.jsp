<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../header.jsp" %>
<%@include file="../../menu.jsp" %>
<h2 class="center">Mis Datos</h2>
<form action="${pageContext.servletContext.contextPath}/UsuarioServlet" method="post">
    <br><br>
    <div class="row">
        <div class="col s6 offset-s3 z-depth-2">

            <c:if test="${sessionScope.admin != null}">
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="rut" type="text" class="validate" readonly="readonly" name="rutUser" value="${sessionScope.admin.rutUser}">
                        <label for="rut">Rut</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="nombre" type="text" class="validate" name="nombreUser" value="${sessionScope.admin.nombreUser}">
                        <label for="nombre">Nombre</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="apellido" type="text" class="validate" name="apellidoUser" value="${sessionScope.admin.apellidoUser}">
                        <label for="apellido">Apellido</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="mail" type="text" class="validate" name="emailUser" value="">
                        <label for="mail">Correo actual: ${sessionScope.admin.emailUser}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="clave" type="password" class="validate" name="clave">
                        <label for="clave">Clave</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="claveConfirm" type="password" class="validate" name="claveConfirm" >
                        <label for="claveConfirm">Confirmar Clave</label>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.persona != null}">
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="rut" type="text" class="validate" readonly="readonly" name="rut" value="${sessionScope.persona.rutUser}">
                        <label for="rut">Rut</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="nombre" type="text" class="validate" name="nombre" value="${sessionScope.persona.nombreUser}">
                        <label for="nombre">Nombre</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="apellido" type="text" class="validate" name="apellido" value="${sessionScope.persona.apellidoUser}">
                        <label for="apellido">Apellido</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="mail" type="text" class="validate" name="mail" value="">
                        <label for="mail">Correo actual: ${sessionScope.persona.emailUser}</label>
                    </div>
                </div>

                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="clave" type="password" class="validate" name="clave">
                        <label for="clave">Clave</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6 offset-s2">
                        <input id="claveConfirm" type="password" class="validate" name="claveConfirm" >
                        <label for="claveConfirm">Confirmar Clave</label>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="input-field col s6 offset-s2">
                    <button type="submit" value="editarUsuario" name="action" class="btn btn-flat blue white-text right-align">Actualizar</button>
                </div>
            </div>
            <div class="row green-text">${requestScope.msg}</div>
            <div class="row red-text">${requestScope.errors}</div>
        </div>
    </div>
</form>
<%@include file="../../footer.jsp" %>