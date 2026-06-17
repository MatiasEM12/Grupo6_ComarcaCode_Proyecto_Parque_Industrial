<%@ page import="java.util.List" %>
<%@ page import="model.DTO.EvaluacionTecnicaDTO" %>
<%@ page import="model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if (usuario == null || !usuario.nombreRol().equals("organismo_publico")) {
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    List<EvaluacionTecnicaDTO> evaluaciones =
            (List<EvaluacionTecnicaDTO>) request.getAttribute("evaluaciones");

    Integer idProyecto = (Integer) request.getAttribute("idProyecto");
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Evaluaciones Técnicas</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/evaluacionesTecnicas.css">
</head>

<body>

<header class="header">
    <div class="header__overlay"></div>

    <div class="header__item--container">
        <h1>EVALUACIONES TÉCNICAS</h1>
        <p>Listado de evaluaciones realizadas sobre el proyecto seleccionado.</p>
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
        <img src="<%= request.getContextPath() %>/img/logo.png" class="nav__logo">

        <a href="<%= request.getContextPath() %>/logout" class="nav__link Link--Cerrar">
            Cerrar Sesión
        </a>
    </div>
</nav>

<main>

    <div class="tabla__container">

        <div class="tabla__acciones">

            <% if (idProyecto != null) { %>
                <a href="<%= request.getContextPath() %>/evaluacionTecnica?idProyecto=<%= idProyecto %>"
                   class="btn__descargar">
                    Nueva evaluación
                </a>
            <% } %>

        </div>

        <% if (evaluaciones == null || evaluaciones.isEmpty()) { %>

            <div class="sin__evaluaciones">
                <h2>No hay evaluaciones cargadas</h2>
                <p>Todavía no se registraron evaluaciones técnicas para este proyecto.</p>
            </div>

        <% } else { %>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Proyecto</th>
                        <th>Descripción</th>
                        <th>Resultado</th>
                        <th>Observaciones</th>
                        <th>Fecha</th>
                        <th>Descargar</th>
                    </tr>
                </thead>

                <tbody>
                    <% for (EvaluacionTecnicaDTO e : evaluaciones) { %>

                        <%
                            String claseEstado = "";

                            if ("APROBADA".equals(e.resultado())) {
                                claseEstado = "estado-aprobada";
                            } else if ("OBSERVADA".equals(e.resultado())) {
                                claseEstado = "estado-observada";
                            } else if ("RECHAZADA".equals(e.resultado())) {
                                claseEstado = "estado-rechazada";
                            }
                        %>

                        <tr>
                            <td><%= e.id() %></td>

                            <td><%= e.nombreProyecto() %></td>

                            <td><%= e.descripcion() %></td>

                            <td>
                                <span class="estado <%= claseEstado %>">
                                    <%= e.resultado() %>
                                </span>
                            </td>

                            <td>
                                <%= e.observaciones() == null || e.observaciones().isBlank()
                                        ? "Sin observaciones"
                                        : e.observaciones() %>
                            </td>

                            <td><%= e.fecha() %></td>

                            <td>
                                <a class="btn__pdf"
                                   href="<%= request.getContextPath() %>/descargarEvaluacionTecnicaPDF?idEvaluacion=<%= e.id() %>">
                                    Descargar PDF
                                </a>
                            </td>
                        </tr>

                    <% } %>
                </tbody>
            </table>

        <% } %>

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