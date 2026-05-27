<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.SolicitudRadicacion" %>
<%@ page import="model.EstadoSolicitud" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario = Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    List<SolicitudRadicacion> solicitudes = (List<SolicitudRadicacion>) request.getAttribute("solicitudes");
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Mis Solicitudes</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/listadoLotes.css">
</head>

<body>

<header class="header">
    <div class="header__overlay"></div>

    <div class="header__item--container">
        <h1>MIS SOLICITUDES</h1>

        <p>
            Estado de tus solicitudes de radicación.
        </p>
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

    <div class="lotes__container">

        <%
            if(solicitudes != null && !solicitudes.isEmpty()){

                for(SolicitudRadicacion solicitud : solicitudes){

                    String claseEstado = "";

                    if(solicitud.estadoSolicitud() == EstadoSolicitud.PENDIENTE){
                        claseEstado = "estado__disponible";
                    } else if(solicitud.estadoSolicitud() == EstadoSolicitud.RECHAZADA){
                        claseEstado = "estado__ocupado";
                    } else if(solicitud.estadoSolicitud() == EstadoSolicitud.OBSERVADA){
                        claseEstado = "estado__ocupado";
                    }
        %>

        <article class="lote__card">

            <div class="lote__content">

                <h2>
                    <%= solicitud.nombreProyecto() %>
                </h2>

                <p>
                    N° trámite:
                    <%= solicitud.numeroTramite() %>
                </p>

                <p>
                    Fecha:
                    <%= solicitud.fechaCreacion() %>
                </p>

                <p>
                    Superficie:
                    <%= solicitud.m2() %> m²
                </p>

                <p>
                    Personal:
                    <%= solicitud.personal() %>
                </p>

                <p>
                    Materia prima:
                    <%= solicitud.materiasPrimas() %>
                </p>

                <p>
                    Archivo:
                    <%= solicitud.nombreArchivoPDF() == null
                            || solicitud.nombreArchivoPDF().isBlank()
                            ? "Sin archivo"
                            : solicitud.nombreArchivoPDF() %>
                </p>

                <span class="lote__state <%= claseEstado %>">
                    <%= solicitud.estadoSolicitud().name() %>
                </span>

            </div>

        </article>

        <%
                }

            } else {
        %>

        <div class="sin__Lotes">
            <h2>No tenés solicitudes cargadas</h2>

            <p>
                Cuando cargues una solicitud, aparecerá en esta sección.
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

        Sistema de gestión del Parque Industrial de Viedma.
    </div>
</footer>

</body>
</html>