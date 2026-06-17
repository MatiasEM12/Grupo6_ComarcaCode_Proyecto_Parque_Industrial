<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    if (!usuario.nombreRol().equals("organismo_publico")) {
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Reportes</title>

    <link rel="stylesheet"
             href="${pageContext.request.contextPath}/CSS/mainOrganismoPublico.css">
</head>

<body>

<header class="header">

    <div class="header__overlay"></div>

    <div class="header__item--container">

        <h1>REPORTES</h1>

        <p>
            Generar reportes generales, auditorías o informes
            relacionados con el Parque Industrial de Viedma.
        </p>

    </div>

</header>

<nav class="nav">

    <div class="nav__ul--container">

        <ul class="nav__ul">

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/mainOrganismoPublico.jsp"
                   class="nav__link">
                    Inicio
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/proyectosEnEjecucion.jsp"
                   class="nav__link">
                    Proyectos en Ejecución
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/evaluacionTecnica.jsp"
                   class="nav__link">
                    Evaluaciones Técnicas
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

    <section class="form__container">

        <h2>Nuevo Reporte</h2>

        <form action="${pageContext.request.contextPath}/guardarReporte"
              method="post"
              class="form">

            <label>Tipo de reporte</label>

            <select name="tipoReporte" required>
                <option value="">Seleccione una opción</option>
                <option value="AUDITORIA">Auditoría</option>
                <option value="DOCUMENTACION">Documentación</option>
                <option value="GENERAL">General</option>
            </select>

            <label>Descripción</label>

            <textarea name="descripcion"
                      placeholder="Ingrese la descripción del reporte..."
                      required></textarea>

            <button type="submit"
                    class="btn__form">
                Generar Reporte
            </button>

        </form>

    </section>

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