<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.RepresentanteEmpresa" %>
<%@ page import="model.Empresa" %>
<%@ page import="model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    RepresentanteEmpresa representante = (RepresentanteEmpresa) request.getAttribute("representante");
    Empresa empresa = (Empresa) request.getAttribute("empresa");

    if(usuario == null || representante == null || empresa == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Perfil Representante</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/perfilRepresentante.css">
</head>

<body>

<header class="header">
    <div class="header__overlay"></div>

    <div class="header__item--container">
        <h1>Parque Industrial</h1>
        <p>Datos registrados del representante de empresa.</p>
    </div>
</header>

<nav class="nav">
    <div class="nav__ul--container">
        <ul class="nav__ul">

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/mainRepresentante.jsp" class="nav__link">
                    Inicio
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/perfilRepresentante" class="nav__link">
                    Perfil
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/misProyectos" class="nav__link">
                    Mis Proyectos
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/misSolicitudes" class="nav__link">
                    Mis Solicitudes
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/subirSolicitud" class="nav__link">
                    Enviar Solicitud
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
                           required>
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

                <button type="submit" class="btn__actualizar">
                    Guardar datos de usuario
                </button>

            </form>

        </section>

        <section class="perfil__card">

            <div class="perfil__titulo">
                <h2>Datos del Representante</h2>
            </div>

            <div class="perfil__form">

                <div class="input__group">
                    <label>DNI</label>
                    <input type="text"
                           value="<%= representante.dni() %>"
                           readonly>
                </div>

                <div class="input__group">
                    <label>Rol</label>
                    <input type="text"
                           value="<%= usuario.nombreRol() %>"
                           readonly>
                </div>

                <a href="${pageContext.request.contextPath}/editarDatosRepresentante"
                   class="btn__actualizar">
                    Actualizar datos personales
                </a>

            </div>

        </section>

        <section class="perfil__card">

            <div class="perfil__titulo">
                <h2>Datos de la Empresa</h2>
            </div>

            <form action="${pageContext.request.contextPath}/actualizarEmpresaRepresentante"
                  method="post"
                  class="perfil__form">

                <div class="input__group">
                    <label>CUIT</label>
                    <input type="text"
                           name="cuit"
                           value="<%= empresa.cuit() %>"
                           readonly>
                </div>

                <div class="input__group">
                    <label>Razón social</label>
                    <input type="text"
                           value="<%= empresa.razonSocial() %>"
                           readonly>
                </div>

                <div class="input__group">
                    <label>Contacto de la empresa</label>
                    <input type="text"
                           name="contacto"
                           value="<%= empresa.contacto() %>"
                           required>
                </div>

                <div class="input__group">
                    <label>Contacto del representante</label>
                    <input type="text"
                           name="contactoRepresentante"
                           value="<%= empresa.contactoRepresentante() %>"
                           required>
                </div>

                <div class="input__group">
                    <label>Estado de radicación</label>
                    <input type="text"
                           value="<%= empresa.esRadicada() ? "Radicada" : "No radicada" %>"
                           readonly>
                </div>

                <button type="submit" class="btn__actualizar">
                    Guardar cambios de contacto
                </button>

            </form>

        </section>

    </div>

</main>

<footer>
    <div class="div__footer--container">
        <p>Parque Industrial</p>
        Comprometidos con el crecimiento productivo, la innovación y el desarrollo sostenible de la región. © 2026 Todos los derechos reservados.
    </div>
</footer>

</body>
</html>