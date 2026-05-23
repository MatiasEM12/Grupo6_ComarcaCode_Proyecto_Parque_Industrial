<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>

<%
    Usuario usuario =
            (Usuario) session.getAttribute(
                    "usuarioLogueado"
            );

    // SI NO HAY SESIÓN
    if(usuario == null){

        response.sendRedirect(
                request.getContextPath()
                + "/perfiles"
        );

        return;
    }

    // SI NO ES ADMINISTRADOR
    if(!usuario.nombreRol().equals("administrador")){

        response.sendRedirect(
                request.getContextPath()
                + "/perfiles"
        );

        return;
    }
%>

<!DOCTYPE html>

<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Main Administrador</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/mainRepresentante.css">

</head>

<body>

<header class="header">

    <div class="header__overlay"></div>

    <div class="header__item--container">

        <h1>
            PARQUE INDUSTRIAL
            VIEDMA
        </h1>

        <p>
            Panel de administración del sistema.
        </p>

    </div>

</header>

<nav class="nav">

    <div class="nav__ul--container">

        <ul class="nav__ul">

            <li class="nav__item">

                <a href="#"
                   class="nav__link">

                    Usuarios

                </a>

            </li>

            <li class="nav__item">

                <a href="#"
                   class="nav__link">

                    Solicitudes

                </a>

            </li>

            <li class="nav__item">

                <a href="#"
                   class="nav__link">

                    Proyectos

                </a>

            </li>

        </ul>

    </div>

    <div class="nav__right">

        <img
            src="${pageContext.request.contextPath}/img/logo.png"
            alt="Logo"
            class="nav__logo"
        >

        <a href="${pageContext.request.contextPath}/logout"
           class="nav__link Link--Cerrar">

            Cerrar Sesión

        </a>

    </div>

</nav>

<main>

    <div class="main__container">

        <a href="${pageContext.request.contextPath}/usuariosRegistrados"
           class="card">

            <div class="card__content">
                <h2>Usuarios</h2>
                <p>Consultar usuarios.</p>
            </div>

        </a>

        <a href="${pageContext.request.contextPath}/solicitudesAdmin"
           class="card">
            <div class="card__content">
                <h2>Solicitudes</h2>
                <p>Consultar solicitudes de radicacion</p>
            </div>
        </a>

        <a href="${pageContext.request.contextPath}/proyectosEnEjecucion"
           class="card">
            <div class="card__content">
                <h2>Proyectos</h2>
                <p>Consultar información sobre los proyectos productivos del parque.</p>
            </div>
        </a>

    </div>

</main>

<footer>

    <div class="div__footer--container">

        <p>Parque Industrial</p>

        Sistema de gestión del Parque Industrial de Viedma.

    </div>

</footer>

</body>

</html>