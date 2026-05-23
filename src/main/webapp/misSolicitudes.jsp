<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.SolicitudRadicacion" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario =
            (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    List<SolicitudRadicacion> solicitudes =
            (List<SolicitudRadicacion>) request.getAttribute("solicitudes");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mis Solicitudes</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/mainRepresentante.css">
</head>

<body>

<header class="header">
    <div class="header__overlay"></div>

    <div class="header__item--container">
        <h1>MIS SOLICITUDES</h1>
        <p>Estado de tus solicitudes de radicación.</p>
    </div>
</header>

<nav class="nav">
    <div class="nav__ul--container">
        <ul class="nav__ul">
            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/mainRepresentante.jsp"
                   class="nav__link">
                    Inicio
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/misProyectos"
                   class="nav__link">
                    Mis proyectos
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/solicitudRadicacion.jsp"
                   class="nav__link">
                    Nueva solicitud
                </a>
            </li>
        </ul>
    </div>

    <div class="nav__right">
        <img src="${pageContext.request.contextPath}/img/logo.png"
             alt="Logo"
             class="nav__logo">

        <a href="${pageContext.request.contextPath}/logout"
           class="nav__link Link--Cerrar">
            Cerrar Sesión
        </a>
    </div>
</nav>

<main>
    <div class="main__container">

        <%
            if(solicitudes == null || solicitudes.isEmpty()){
        %>

        <article class="card">
            <div class="card__content">
                <h2>No tenés solicitudes pendientes</h2>
                <p>Cuando cargues una solicitud, aparecerá en esta sección.</p>
            </div>
        </article>

        <%
            } else {
                for(SolicitudRadicacion solicitud : solicitudes){
        %>

        <article class="card">
            <div class="card__content">

                <h2><%= solicitud.nombreProyecto() %></h2>

                <p>
                    <strong>N° trámite:</strong>
                    <%= solicitud.numeroTramite() %>
                </p>

                <p>
                    <strong>Estado:</strong>
                    <%= solicitud.estadoSolicitud().name() %>
                </p>

                <p>
                    <strong>Fecha de creación:</strong>
                    <%= solicitud.fechaCreacion() %>
                </p>

                <p>
                    <strong>Última actualización:</strong>
                    <%= solicitud.fechaActualizacion() %>
                </p>

                <p>
                    <strong>Descripción:</strong>
                    <%= solicitud.descripcionServicio() %>
                </p>

                <p>
                    <strong>Superficie solicitada:</strong>
                    <%= solicitud.m2() %> m²
                </p>

                <p>
                    <strong>Personal:</strong>
                    <%= solicitud.personal() %>
                </p>

                <p>
                    <strong>Materia prima:</strong>
                    <%= solicitud.materiasPrimas() %>
                </p>

                <p>
                    <strong>Archivo:</strong>
                    <%= solicitud.nombreArchivoPDF() == null || solicitud.nombreArchivoPDF().isBlank()
                            ? "Sin archivo"
                            : solicitud.nombreArchivoPDF() %>
                </p>

            </div>
        </article>

        <%
                }
            }
        %>

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