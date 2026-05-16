<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.SolicitudRadicacion" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>ParqueIndustrialViedma</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/representanteProyectos.css">

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
            Lorem ipsum dolor, sit amet consectetur adipisicing elit.
            Ex doloremque, fuga sit porro alias praesentium iste tenetur
            nesciunt facilis suscipit tempora fugit distinctio exercitationem
            perferendis at vitae provident molestias modi.
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

                <a href=""
                   class="nav__link">

                    Perfil

                </a>

            </li>

            <li class="nav__item">

                <a href="${pageContext.request.contextPath}/misProyectos"
                   class="nav__link">

                    Mis Proyectos

                </a>

            </li>

            <li class="nav__item">

                <a href="${pageContext.request.contextPath}/solicitudRadicacion.jsp"
                   class="nav__link">

                    Enviar Solicitud

                </a>

            </li>

        </ul>

    </div>

    <div class="nav__right">

        <img src="${pageContext.request.contextPath}/img/logo.png"
             alt="Logo"
             class="nav__logo">

        <a href=""
           class="nav__link Link--Cerrar">

            Cerrar Sesión

        </a>

    </div>

</nav>

<main>

    <div class="projects__container">

        <%
            List<SolicitudRadicacion> solicitudes =
                    (List<SolicitudRadicacion>)
                            request.getAttribute("solicitudes");

            if (solicitudes != null && !solicitudes.isEmpty()) {

                for (SolicitudRadicacion solicitud : solicitudes) {

                    String claseEstado = "";

                    if (solicitud.estadoSolicitud()
                            .toString()
                            .equals("PENDIENTE")) {

                        claseEstado = "estado__pendiente";
                    }

                    else if (solicitud.estadoSolicitud()
                            .toString()
                            .equals("APROBADA")) {

                        claseEstado = "estado__aprobado";
                    }

                    else if (solicitud.estadoSolicitud()
                            .toString()
                            .equals("OBSERVADA")) {

                        claseEstado = "estado__revision";
                    }
        %>

        <a href="${pageContext.request.contextPath}/representanteProyecto?id=<%= solicitud.id() %>"
           class="project__card">

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

        <div class="sin__proyectos">

            <h2>

                No hay proyectos cargados

            </h2>

            <p>

                Todavía no enviaste ninguna solicitud de radicación.

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

        Lorem ipsum dolor sit amet consectetur adipisicing elit.
        Explicabo qui laborum, hic corporis odit porro, adipisci
        minus harum aut maiores odio. Totam, autem. Obcaecati,
        molestias ullam voluptas harum vel corporis.

    </div>

</footer>

</body>
</html>