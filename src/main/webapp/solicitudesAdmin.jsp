<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.SolicitudRadicacion" %>
<%@ page import="model.EstadoSolicitud" %>
<%@ page import="model.Lote" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    if(!usuario.nombreRol().equals("administrador")){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

%>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Solicitudes de Radicación</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/representanteProyectos.css">
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
                <a href="${pageContext.request.contextPath}/mainAdm.jsp" class="nav__link">
                    Inicio
                </a>
            </li>

            <li class="nav__item">
                <a href="#" class="nav__link">
                    Perfil
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/usuariosRegistrados" class="nav__link">
                    Usuarios
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/solicitudesAdmin" class="nav__link">
                    Solicitudes
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/proyectosEnEjecucion" class="nav__link">
                    Proyectos
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/listadoLotes" class="nav__link">
                    Lotes
                </a>
            </li>

            <li class="nav__item">
                <a href="#" class="nav__link">
                    Inventario
                </a>
            </li>

            <li class="nav__item">
                <a href="#" class="nav__link">
                    Reportes
                </a>
            </li>

        </ul>

    </div>

    <div class="nav__right">

        <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" class="nav__logo" >

        <a href="${pageContext.request.contextPath}/logout" class="nav__link Link--Cerrar">
            Cerrar Sesión
        </a>

    </div>

</nav>
<main>
 <div class="projects__container">

        <%
            List<SolicitudRadicacion> solicitudes =(List<SolicitudRadicacion>)request.getAttribute("solicitudes");

            if (solicitudes != null && !solicitudes.isEmpty()) {

                for (SolicitudRadicacion solicitud : solicitudes) {

                    String claseEstado = "";

                    if (solicitud.estadoSolicitud().toString().equals("PENDIENTE")) {

                        claseEstado = "estado__pendiente";
                    }

                    else if (solicitud.estadoSolicitud().toString().equals("APROBADA_PRIMERA_INSTANCIA")) {

                        claseEstado = "estado__aprobado_1";
                    }

                    else if (solicitud.estadoSolicitud().toString().equals("APROBADA_FINAL")) {

                         claseEstado = "estado__aprobado_2";
                    }

                    else if (solicitud.estadoSolicitud().toString().equals("OBSERVADA")) {

                        claseEstado = "estado__revision";
                    }
        %>

        <a href="${pageContext.request.contextPath}/solicitudDetalle?id=<%= solicitud.id() %>" class="project__card">

            <div class="project__content">

                <h2>

                    <%= solicitud.nombreProyecto() %>

                </h2>

                <p>

                    <%= solicitud.descripcionServicio() %>

                </p>

                    <p class="project__date">

                       Última actualización: <%= solicitud.fechaActualizacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) %>

                    </p>

                <span class="project__state <%= claseEstado %>">

                    <%= solicitud.estadoSolicitud() %>

                </span>

            </div>

        </a>

        <%
                }

            } else {
        %>

        <div class="sin__solicitudes">

            <h2>

                No hay solicitudes cargadas

            </h2>

            <p>

                Todavía no  hay solicitudes cargadas

            </p>

        </div>

        <%
            }
        %>

    </div>
</main>

<footer>
    <div class="div__footer--container">
        <p>Parque Industrial</p>
          Comprometidos con el crecimiento productivo, la innovación y el desarrollo sostenible de la región. © 2026 Todos los derechos reservados.
    </div>
</footer>
<%
    String error = (String) session.getAttribute("error");

    if (error != null) {
%>

<script>
    alert("<%= error %>");
</script>

<%
        session.removeAttribute("error");
    }
%>

</body>
</html>