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

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/solicitudesAdmin.css">
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
                    <%= solicitud.representante() != null
                            ? solicitud.representante().dni()
                            : "Sin representante" %>
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
                    <strong>Personal a ocupar:</strong>
                    <%= solicitud.personal() %>
                </p>

                <p>
                    <strong>Empleabilidad:</strong>
                    <%= solicitud.empleabilidad() %>
                </p>

                <p>
                    <strong>Materia prima:</strong>
                    <%= solicitud.materiasPrimas() %>
                </p>

                <p>
                    <strong>Archivo adjunto:</strong>
                    <%= solicitud.nombreArchivoPDF() == null
                            || solicitud.nombreArchivoPDF().isBlank()
                            ? "Sin archivo"
                            : solicitud.nombreArchivoPDF() %>
                </p>

            </div>

            <div class="acciones__container">

                <form action="${pageContext.request.contextPath}/aprobarSolicitud"
                      method="post">

                    <input type="hidden"
                           name="idSolicitud"
                           value="<%= solicitud.id() %>">

                    <select name="idLote"
                            required
                            <%= solicitud.estadoSolicitud() == EstadoSolicitud.APROBADA
                                    ? "disabled"
                                    : "" %>>

                        <option value="">Seleccionar lote</option>

                        <%
                            if(lotes != null){
                                for(Lote lote : lotes){
                        %>

                        <option value="<%= lote.id() %>">
                            Lote <%= lote.id() %>
                            -
                            <%= lote.superficie() %> m²
                            -
                            <%= lote.infraestructura() %>
                        </option>

                        <%
                                }
                            }
                        %>

                    </select>

                    <button type="submit"
                            class="btn__aprobar"
                            <%= solicitud.estadoSolicitud() == EstadoSolicitud.APROBADA
                                    ? "disabled"
                                    : "" %>>
                        Confirmar y asignar lote
                    </button>
                </form>

                <form action="${pageContext.request.contextPath}/rechazarSolicitud"
                      method="post">

                    <input type="hidden"
                           name="idSolicitud"
                           value="<%= solicitud.id() %>">

                    <button type="submit"
                            class="btn__rechazar"
                            <%= solicitud.estadoSolicitud() == EstadoSolicitud.RECHAZADA
                                    ? "disabled"
                                    : "" %>>
                        Rechazar
                    </button>
                </form>

                <button class="btn__observar"
                        onclick="mostrarFormulario('obs<%= solicitud.id() %>')">
                    Observar documentación
                </button>

            </div>

            <div id="obs<%= solicitud.id() %>"
                 class="observacion__form"
                 style="display:none;">

                <form action="${pageContext.request.contextPath}/observarSolicitud"
                      method="post">

                    <input type="hidden"
                           name="idSolicitud"
                           value="<%= solicitud.id() %>">

                    <textarea name="descripcion"
                              placeholder="Escriba la observación para que la empresa corrija la documentación..."
                              required></textarea>

                    <button type="submit"
                            class="btn__enviar">
                        Enviar observación
                    </button>
                </form>

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