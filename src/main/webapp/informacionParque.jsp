<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.ProyectoProductivo" %>
<%@ page import="model.DTO.InformeParqueDTO" %>
<%@ page import="model.Informe" %>
<%@ page import="model.Documento" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    InformeParqueDTO informeParque = (InformeParqueDTO) request.getAttribute("informe");
    List<Informe> informesAdmin = (List<Informe>) request.getAttribute("informeAdmin");

    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    if (!usuario.nombreRol().equals("organismo_publico") && !usuario.nombreRol().equals("administrador")) {
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    String paginaInicio = "/mainAdm.jsp";
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Información del Parque</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/informacionParque.css">
</head>
<body>

<header class="header">
    <div class="header__overlay"></div>
    <div class="header__item--container">
        <h1>INFORMACIÓN DEL PARQUE</h1>
        <p>Reporte general sobre el desarrollo productivo, la actividad industrial y los proyectos en ejecución.</p>
    </div>
</header>

<nav class="nav">
    <div class="nav__ul--container">
        <ul class="nav__ul">
            <li class="nav__item">
                <a href="<%= request.getContextPath() + paginaInicio %>" class="nav__link">Inicio</a>
            </li>
        </ul>
    </div>

    <div class="nav__right">
        <img src="<%= request.getContextPath() %>/img/logo.png" alt="Logo" class="nav__logo">
        <a href="<%= request.getContextPath() %>/logout" class="nav__link Link--Cerrar">Cerrar Sesión</a>
    </div>
</nav>

<main>

    <% if (informeParque != null) { %>

        <section class="reporte__header">
            <div>
                <h2>Reporte completo del Parque Industrial</h2>
                <p>Fecha de generación: <%= informeParque.fechaGeneracion() %></p>
            </div>

            <a class="btn__descargar" href="<%= request.getContextPath() %>/descargarReporteParque">
                Descargar reporte
            </a>
        </section>

        <section class="metricas__container">
            <div class="metrica__card">
                <span>Total proyectos</span>
                <strong><%= informeParque.totalProyectos() %></strong>
            </div>

            <div class="metrica__card">
                <span>En ejecución</span>
                <strong><%= informeParque.proyectosEnEjecucion() %></strong>
            </div>

            <div class="metrica__card">
                <span>Lotes disponibles</span>
                <strong><%= informeParque.lotesDisponibles() %></strong>
            </div>

            <div class="metrica__card">
                <span>Lotes ocupados</span>
                <strong><%= informeParque.lotesOcupados() %></strong>
            </div>

            <div class="metrica__card">
                <span>Evaluaciones técnicas</span>
                <strong><%= informeParque.totalEvaluacionesTecnicas() %></strong>
            </div>

            <div class="metrica__card">
                <span>Empleabilidad estimada</span>
                <strong><%= informeParque.empleabilidadTotal() %></strong>
            </div>
        </section>

        <section class="detalle__container">
            <h2>Actividad industrial</h2>

            <div class="detalle__grid">
                <p><strong>Proyectos finalizados:</strong> <%= informeParque.proyectosFinalizados() %></p>
                <p><strong>Proyectos suspendidos:</strong> <%= informeParque.proyectosSuspendidos() %></p>
                <p><strong>Proyectos sin iniciar:</strong> <%= informeParque.proyectosSinIniciar() %></p>
                <p><strong>Total de lotes:</strong> <%= informeParque.totalLotes() %></p>
                <p><strong>Superficie total de proyectos:</strong> <%= informeParque.superficieTotalProyectos() %> m²</p>
            </div>
        </section>

        <section class="tabla__container">
            <h2>Proyectos productivos</h2>

            <table>
                <thead>
                    <tr>
                        <th>Proyecto</th>
                        <th>Estado</th>
                        <th>Superficie</th>
                        <th>Empleabilidad</th>
                        <th>Materia prima</th>
                    </tr>
                </thead>
                <tbody>
                <% for (ProyectoProductivo proyecto : informeParque.proyectos()) { %>
                    <tr>
                        <td><%= proyecto.nombre() %></td>
                        <td><%= proyecto.estado() %></td>
                        <td><%= proyecto.superficie() %> m²</td>
                        <td><%= proyecto.empleabilidad() %></td>
                        <td><%= proyecto.materiaPrima() %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </section>

    <% } else { %>
        <section class="sin__datos">
            <h2>No se pudo generar el reporte</h2>
            <p>Intente nuevamente más tarde.</p>
        </section>
    <% } %>
    <section class="tabla__container">
        <h2>Informes cargados por administración</h2>

        <% if (informesAdmin != null && !informesAdmin.isEmpty()) { %>

            <table>
                <thead>
                    <tr>
                        <th>Tipo</th>
                        <th>Descripción</th>
                        <th>Fecha</th>
                        <th>Generado por</th>
                        <th>Documentos</th>
                    </tr>
                </thead>

                <tbody>
                <% for (Informe inf : informesAdmin) { %>
                    <tr>
                        <td><%= inf.tipo() %></td>
                        <td><%= inf.descripcion() %></td>
                        <td><%= inf.fecha() %></td>
                        <td><%= inf.usuario().UserName() %></td>
                        <td>
                            <% if (inf.documentos() != null && !inf.documentos().isEmpty()) { %>
                                <% for (Documento doc : inf.documentos()) { %>
                                    <a class="btn__descargar"
                                       href="<%= request.getContextPath() %>/descargarDocumento?id=<%= doc.id() %>">
                                        Descargar
                                    </a>
                                <% } %>
                            <% } else { %>
                                Sin documento
                            <% } %>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>

        <% } else { %>

            <p>No hay reportes administrativos cargados.</p>

        <% } %>
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
