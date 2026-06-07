<%@ page import="model.ProyectoProductivo" %>
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

    ProyectoProductivo proyecto =
            (ProyectoProductivo) request.getAttribute("proyecto");

    Integer idProyecto =
            (Integer) request.getAttribute("idProyecto");

    if (proyecto == null || idProyecto == null) {
        response.sendRedirect(request.getContextPath() + "/proyectosEnEjecucion");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Evaluación Técnica</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/evaluacionTecnica.css">
</head>

<body>

<header class="header">
    <div class="header__overlay"></div>

    <div class="header__item--container">
        <h1>EVALUACIÓN TÉCNICA</h1>

        <p>
            Registrar evaluaciones técnicas sobre proyectos productivos
            en ejecución dentro del Parque Industrial.
        </p>
    </div>
</header>

<nav class="nav">

    <div class="nav__ul--container">

        <ul class="nav__ul">

            <li class="nav__item">
                <a href="<%= request.getContextPath() %>/mainOrganismoPublico.jsp" class="nav__link">
                    Inicio
                </a>
            </li>

            <li class="nav__item">
                <a href="<%= request.getContextPath() %>/proyectosEnEjecucion" class="nav__link">
                    Proyectos
                </a>
            </li>

        </ul>

    </div>

    <div class="nav__right">

        <img src="<%= request.getContextPath() %>/img/logo.png" alt="Logo" class="nav__logo">

        <a href="<%= request.getContextPath() %>/logout" class="nav__link Link--Cerrar">
            Cerrar Sesión
        </a>

    </div>

</nav>

<main>

    <section class="evaluacion__container">

        <article class="proyecto__card">

            <div class="proyecto__content">

                <h2>
                    <%= proyecto.nombre() %>
                </h2>

                <p>
                    <strong>Descripción:</strong>
                    <%= proyecto.descripcion() %>
                </p>

                <p>
                    <strong>Estado:</strong>
                    <%= proyecto.estado() %>
                </p>

                <span class="proyecto__state estado__ejecucion">
                    Proyecto seleccionado
                </span>

            </div>

        </article>

        <article class="form__card">

            <h2>Nueva Evaluación Técnica</h2>

            <form action="<%= request.getContextPath() %>/guardarEvaluacionTecnica"
                  method="post"
                  class="form">

                <input type="hidden" name="idProyecto" value="<%= idProyecto %>">

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

                <button type="submit" class="btn__form">
                    Guardar Evaluación
                </button>

            </form>

        </article>

    </section>

</main>

<footer>
    <div class="div__footer--container">
        <p>Parque Industrial</p>
        Comprometidos con el crecimiento productivo, la innovación y el desarrollo sostenible de la región. © 2026 Todos los derechos reservados.
    </div>
</footer>

</body>
</html>