<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.SolicitudRadicacion" %>
<%@ page import="model.EstadoSolicitud" %>
<%@ page import="model.Lote" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario =
            (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    if(!usuario.nombreRol().equals("administrador")){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    List<SolicitudRadicacion> solicitudes =
            (List<SolicitudRadicacion>) request.getAttribute("solicitudes");

    List<Lote> lotes =
            (List<Lote>) request.getAttribute("lotes");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Solicitudes de Radicación</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/solicitudesAdmin.css">
</head>

<body>

<header class="header">
    <div class="header__overlay"></div>

    <div class="header__item--container">
        <h1>SOLICITUDES DE RADICACIÓN</h1>
        <p>Administración y revisión de solicitudes enviadas por las empresas.</p>
    </div>
</header>

<nav class="nav">
    <div class="nav__ul--container">
        <ul class="nav__ul">
            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/mainAdm.jsp"
                   class="nav__link">
                    Inicio
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/usuariosRegistrados"
                   class="nav__link">
                    Usuarios
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/proyectosEnEjecucion"
                   class="nav__link">
                    Proyectos
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
    <div class="solicitudes__container">

        <%
            if(solicitudes == null || solicitudes.isEmpty()){
        %>

        <div class="solicitud__card">
            <h2>No hay solicitudes cargadas</h2>
            <p>Todavía ningún representante envió una solicitud de radicación.</p>
        </div>

        <%
            } else {
                for(SolicitudRadicacion solicitud : solicitudes){

                    String claseEstado = "estado--pendiente";

                    if(solicitud.estadoSolicitud() == EstadoSolicitud.APROBADA){
                        claseEstado = "estado--aprobada";
                    } else if(solicitud.estadoSolicitud() == EstadoSolicitud.OBSERVADA){
                        claseEstado = "estado--observada";
                    } else if(solicitud.estadoSolicitud() == EstadoSolicitud.RECHAZADA){
                        claseEstado = "estado--rechazada";
                    }
        %>

        <article class="solicitud__card">

            <div class="solicitud__header">

                <h2><%= solicitud.nombreProyecto() %></h2>

                <span class="estado <%= claseEstado %>">
                    <%= solicitud.estadoSolicitud().name() %>
                </span>

            </div>

            <div class="solicitud__body">

                <p>
                    <strong>N° trámite:</strong>
                    <%= solicitud.numeroTramite() %>
                </p>

                <p>
                    <strong>Representante:</strong>
                    <%= solicitud.representante().dni() %>
                </p>

                <p>
                    <strong>Fecha:</strong>
                    <%= solicitud.fechaCreacion() %>
                </p>

            </div>

            <a href="${pageContext.request.contextPath}/detalleSolicitud?id=<%= solicitud.id() %>"
               class="btn__detalle">

                Ver solicitud completa

            </a>

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

<script>
    function mostrarFormulario(id){
        const form = document.getElementById(id);

        if(form.style.display === "block"){
            form.style.display = "none";
        } else {
            form.style.display = "block";
        }
    }
</script>

</body>
</html>