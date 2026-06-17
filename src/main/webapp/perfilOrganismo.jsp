<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.OrganismoPublico" %>
<%@ page import="model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    OrganismoPublico organismo = (OrganismoPublico) request.getAttribute("organismo");

    if(usuario == null || organismo == null){
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Perfil Organismo Público</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/perfilOrganismo.css">
</head>

<body>

<header class="header">
    <div class="header__overlay"></div>

    <div class="header__item--container">
        <h1>Parque Industrial</h1>
        <p>Datos registrados del organismo público.</p>
    </div>
</header>

<nav class="nav">

    <div class="nav__ul--container">
        <ul class="nav__ul">

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/mainOrganismoPublico.jsp" class="nav__link">
                    Inicio
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/perfilOrganismo" class="nav__link">
                    Perfil
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/informacionParquePublico" class="nav__link">
                    Información del Parque
                </a>
            </li>


        </ul>
    </div>

    <div class="nav__right">
        <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" class="nav__logo">

        <a href="${pageContext.request.contextPath}/logout" class="nav__link Link--Cerrar">
            Cerrar Sesión
        </a>
    </div>

</nav>

<main>

    <div class="perfil__container">

        <section class="perfil__card">

            <div class="perfil__titulo">
                <h2>Datos de Usuario</h2>
            </div>

            <form action="${pageContext.request.contextPath}/actualizarUsuario"
                  method="post"
                  class="perfil__form">

                <div class="input__group">
                    <label>Nombre de usuario</label>
                    <input type="text"
                           value="<%= usuario.UserName() %>"
                           readonly>
                </div>

                <div class="input__group">
                    <label>Correo electrónico</label>
                    <input type="email"
                           name="gmail"
                           value="<%= usuario.gmail() %>"
                           required>
                </div>

                <div class="input__group">
                    <label>Contraseña</label>
                    <input type="password"
                           name="contrasena"
                           value="<%= usuario.contrasena() %>"
                           required>
                </div>

                <div class="input__group">
                    <label>Rol</label>
                    <input type="text"
                           value="<%= usuario.nombreRol() %>"
                           readonly>
                </div>


            </form>

        </section>

        <section class="perfil__card">

            <div class="perfil__titulo">
                <h2>Datos del Organismo Público</h2>
            </div>

            <div class="perfil__form">

               <div class="input__group">
                   <label>SAF</label>
                   <input type="text"
                          value="<%= organismo.saf() %>"
                          readonly>
               </div>

               <div class="input__group">
                   <label>Nombre del organismo</label>
                   <input type="text"
                          value="<%= organismo.nombre() %>"
                          readonly>
               </div>

               <div class="input__group">
                   <label>Tipo de organismo</label>
                   <input type="text"
                          value="<%= organismo.tipoOrganismo() %>"
                          readonly>
               </div>

            </div>

        </section>

    </div>

</main>

<footer>
    <div class="div__footer--container">
        <p>Parque Industrial</p>
        Comprometidos con el crecimiento productivo, la innovación y el desarrollo sostenible de la región. © 2026 Todos los derechos reservados.
    </div>
</footer>

<%
    String error = (String) request.getAttribute("error");

    if (error != null) {
%>

<script>
    alert("<%= error %>");
</script>

<%
    }
%>

</body>
</html>