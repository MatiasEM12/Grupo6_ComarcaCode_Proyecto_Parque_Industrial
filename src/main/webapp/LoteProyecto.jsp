<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.ProyectoProductivo" %>
<%@ page import="model.Lote" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    Lote lote = (Lote) request.getAttribute("lote");
    ProyectoProductivo proyecto =
            (ProyectoProductivo) request.getAttribute("proyecto");

    if(lote == null){
        response.sendRedirect(request.getContextPath() + "/misProyectos");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Mis Lote_Proyecto</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/representanteProyectos.css">
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

        <article class="lote__card">

            <div class="lote__content">

                <h2>
                    Lote #<%= lote.id() %>
                </h2>

                <p>
                    Latitud:
                    <%= lote.ubicacion().latitud() %>
                </p>

                <p>
                    Longitud:
                    <%= lote.ubicacion().longitud() %>
                </p>

                <p>
                    Altitud:
                    <%= lote.ubicacion().altitud() %>
                </p>

                <p>
                    Superficie:
                    <%= lote.superficie() %> m²
                </p>

                <p>
                    Infraestructura:
                    <%= lote.infraestructura() %>
                </p>

                <span class="lote__state">
                    <%= lote.estado() %>
                </span>

            </div>

        </article>

    </div>

    <div class="proyecto__container">

        <article class="project__card">

            <div class="project__content">

                <h2>
                    Proyecto asociado
                </h2>

                <h3>
                    <%= proyecto.nombre() %>
                </h3>

                <p>
                    <%= proyecto.descripcion() %>
                </p>

                <p>
                    Superficie requerida:
                    <%= proyecto.superficie() %> m²
                </p>

                <p>
                    Empleabilidad:
                    <%= proyecto.empleabilidad() %>
                </p>

                <p>
                    Materia prima:
                    <%= proyecto.materiaPrima() %>
                </p>

                <p>
                    Estado:
                    <%= proyecto.enEjecucion()
                        ? "En ejecución"
                        : "No comenzó su ejecución" %>
                </p>

                <a href="${pageContext.request.contextPath}/representanteProyecto?id=<%= proyecto.idProyecto() %>"
                   class="project__link">

                    Ver proyecto completo

                </a>

            </div>

        </article>

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