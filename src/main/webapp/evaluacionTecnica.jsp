<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    if (!usuario.rol().equals("organismo_publico")) {
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

    <title>Evaluación Técnica</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/mainOrganismoPublico.css">
</head>

<body>

<header class="header">

    <div class="header__overlay"></div>

    <div class="header__item--container">

        <h1>EVALUACIÓN TÉCNICA</h1>

        <p>
            Registrar evaluaciones técnicas sobre proyectos,
            documentación o funcionamiento del Parque Industrial.
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
                <a href="${pageContext.request.contextPath}/reporte.jsp"
                   class="nav__link">
                    Reportes
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

        <h2>Nueva Evaluación Técnica</h2>

        <form action="${pageContext.request.contextPath}/guardarEvaluacionTecnica"
              method="post"
              class="form">

            <label>Descripción</label>

            <textarea name="descripcion"
                      placeholder="Describa la evaluación técnica..."
                      required></textarea>

            <label>Resultado</label>

            <select name="resultado" required>
                <option value="">Seleccione una opción</option>
                <option value="APROBADA">Aprobada</option>
                <option value="OBSERVADA">Observada</option>
                <option value="RECHAZADA">Rechazada</option>
            </select>

            <label>Observaciones</label>

            <textarea name="observaciones"
                      placeholder="Ingrese observaciones si corresponde..."></textarea>

            <button type="submit"
                    class="btn__form">
                Guardar Evaluación
            </button>

        </form>

    </section>

</main>

<footer>

    <div class="div__footer--container">

        <p>Parque Industrial</p>

        Sistema de gestión del Parque Industrial de Viedma.

    </div>

</footer>

</body>
</html>