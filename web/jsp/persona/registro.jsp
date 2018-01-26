<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../header.jsp" %>
<h2 class="center">Registro</h2>
<form action="${pageContext.servletContext.contextPath}/PersonaServlet" method="post">
    <br><br>
    <div class="row">
        <div class="col s6 offset-s3 z-depth-2">
            <div class="row">
                <div class="input-field col s6 offset-s2">
                    <input id="rut" type="text" class="validate" readonly="readonly" name="rut" value="${requestScope.persona.rut}">
                    <label for="rut">Rut</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6 offset-s2">
                    <input id="nombre" type="text" class="validate" name="nombre" value="${requestScope.persona.nombre}">
                    <label for="nombre">Nombre</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6 offset-s2">
                    <input id="mail" type="text" class="validate" name="mail" value="${requestScope.persona.mail}">
                    <label for="mail">Mail</label>
                </div>
            </div>
            <c:choose>
                <c:when test="${requestScope.persona == null && sessionScope.persona == null}">
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <input id="clave" type="password" class="validate" name="clave" value="${requestScope.persona.clave}">
                            <label for="clave">Clave</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <input id="claveConfirm" type="password" class="validate" name="claveConfirm" value="${requestScope.persona.rut}">
                            <label for="claveConfirm">Confirmar Clave</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <input type="submit" value="Registrar" name="action" class="btn btn-flat blue white-text right-align"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <a href="${pageContext.servletContext.contextPath}/index.jsp">
                                Volver..
                            </a>
                        </div>
                    </div>
                    <div class="row red-text">${requestScope.msg}</div>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <select name="perfil">
                                <option value="" disabled>Choose your option</option>
                                <option value="Administrador" ${requestScope.persona.perfil == "Administrador" ? "selected" : ""}>Administrador</option>
                                <option value="Persona" ${requestScope.persona.perfil == "Persona" ? "selected" : ""}>Persona</option>
                            </select>
                            <label for="mail">Perfil</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <select name="activo">
                                <option value="" disabled>Choose your option</option>
                                <option value="true" ${requestScope.persona.activo ? "selected" : ""}>Sí</option>
                                <option value="false" ${!requestScope.persona.activo ? "selected" : ""}>No</option>
                            </select>
                            <label for="mail">Activo</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s6 offset-s2">
                            <input type="submit" value="Actualizar" name="action" class="btn btn-flat blue white-text right-align"/>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</form>
<%@include file="../../footer.jsp" %>