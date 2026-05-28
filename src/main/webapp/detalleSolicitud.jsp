<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="model.Usuario" %>
<%@ page import="model.SolicitudRadicacion" %>
<%@ page import="model.EstadoSolicitud" %>
<%@ page import="model.Lote" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    SolicitudRadicacion solicitud =  (SolicitudRadicacion) request.getAttribute("solicitud");

    List<Lote> lotes = (List<Lote>) request.getAttribute("lotes");

    String claseEstado = "estado__pendiente";

    if(solicitud.estadoSolicitud() == EstadoSolicitud.APROBADA){
        claseEstado = "estado__aprobado";
    }

    else if(solicitud.estadoSolicitud() == EstadoSolicitud.OBSERVADA){
        claseEstado = "estado__revision";
    }

    else if(solicitud.estadoSolicitud() == EstadoSolicitud.RECHAZADA){
        claseEstado = "estado__rechazado";
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Detalle Solicitud</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/detalleSolicitud.css">

</head>

<body>

    <header class="header">

        <div class="header__overlay"></div>

        <div class="header__item--container">

            <h1>

                DETALLE DE SOLICITUD

            </h1>

            <p>

                Información completa de la solicitud de radicación.

            </p>

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

        <div class="detalle__container">

            <div class="detalle__card">

                <div class="detalle__header">

                    <h2>

                        <%= solicitud.nombreProyecto() %>

                    </h2>

                    <span class="estado <%= claseEstado %>">

                        <%= solicitud.estadoSolicitud() %>

                    </span>

                </div>

                <div class="detalle__body">

                    <p><strong>N° trámite:</strong>
                        <%= solicitud.numeroTramite() %>
                    </p>

                    <p><strong>Representante:</strong>
                        <%= solicitud.representante().dni() %>
                    </p>

                    <p><strong>Objeto:</strong>
                        <%= solicitud.objeto() %>
                    </p>

                    <p><strong>Descripción:</strong>
                        <%= solicitud.descripcionServicio() %>
                    </p>

                    <p><strong>Emplazamiento:</strong>
                        <%= solicitud.emplazamiento() %>
                    </p>

                    <p><strong>Personal:</strong>
                        <%= solicitud.personal() %>
                    </p>

                    <p><strong>Tiempo radicación:</strong>
                        <%= solicitud.tiempoRadicacion() %>
                    </p>

                    <p><strong>m²:</strong>
                        <%= solicitud.m2() %>
                    </p>

                    <p><strong>Área trabajo:</strong>
                        <%= solicitud.areaTrabajo() %>
                    </p>

                    <p><strong>Área depósito:</strong>
                        <%= solicitud.areaDeposito() %>
                    </p>

                    <p><strong>Estacionamiento:</strong>
                        <%= solicitud.estacionamiento() %>
                    </p>

                    <p><strong>Planos:</strong>
                        <%= solicitud.planos() %>
                    </p>

                    <p><strong>Empleabilidad:</strong>
                        <%= solicitud.empleabilidad() %>
                    </p>

                    <p><strong>Materias primas:</strong>
                        <%= solicitud.materiasPrimas() %>
                    </p>

                    <p><strong>Destino producción:</strong>
                        <%= solicitud.destinoProduccion() %>
                    </p>

                    <p><strong>Tensión:</strong>
                        <%= solicitud.tension() %>
                    </p>

                    <p><strong>Potencia:</strong>
                        <%= solicitud.potencia() %>
                    </p>

                    <p><strong>Agua:</strong>
                        <%= solicitud.agua() %>
                    </p>

                    <p><strong>Gas:</strong>
                        <%= solicitud.gas() %>
                    </p>

                    <p><strong>Residuos:</strong>
                        <%= solicitud.residuos() %>
                    </p>

                    <p><strong>Tratamiento:</strong>
                        <%= solicitud.tratamiento() %>
                    </p>

                    <p><strong>Balanza:</strong>
                        <%= solicitud.balanza() %>
                    </p>

                    <p><strong>Comedor:</strong>
                        <%= solicitud.comedor() %>
                    </p>

                    <p><strong>Coworking:</strong>
                        <%= solicitud.coworking() %>
                    </p>

                    <p><strong>Archivo PDF:</strong>
                        <%= solicitud.nombreArchivoPDF() %>
                    </p>

                </div>

                <div class="acciones__container">

                    <form action="${pageContext.request.contextPath}/aprobarSolicitud" method="post">

                        <input type="hidden"
                               name="idSolicitud"
                               value="<%= solicitud.id() %>">

                        <select name="idLote" required>

                            <option value="">
                                Seleccionar lote
                            </option>

                            <%
                                for(Lote lote : lotes){
                            %>

                            <option value="<%= lote.id() %>">

                                Lote <%= lote.id() %>
                                -
                                <%= lote.superficie() %> m²

                            </option>

                            <%
                                }
                            %>

                        </select>

                        <button type="submit" class="btn__aprobar">
                            Aprobar y asignar lote

                        </button>

                    </form>

                    <form action="${pageContext.request.contextPath}/rechazarSolicitud" method="post">

                        <input type="hidden"
                               name="idSolicitud"
                               value="<%= solicitud.id() %>">

                        <button type="submit"
                                class="btn__rechazar">

                            Rechazar

                        </button>

                    </form>

                </div>

            </div>

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