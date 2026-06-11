<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Reporte" %>
<%@ page import="java.util.List" %>

<%
    List<Reporte> reportes = (List<Reporte>) request.getAttribute("reportes");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reportes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/reportes.css">
</head>

<body>

<header class="header">
    <div class="header__overlay"></div>

    <div class="header__item--container">
        <h1>Generación de Reportes</h1>
        <p>Administre y consulte los reportes del Parque Industrial.</p>
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

    <div class="reportes__container">

        <div class="reportes__card">

            <h2>Nuevo Reporte</h2>
            <p>Complete los datos para generar un nuevo reporte.</p>

            <% if(request.getAttribute("mensaje") != null) { %>
                <div class="mensaje__exito">
                    <%= request.getAttribute("mensaje") %>
                </div>
            <% } %>

            <% if(request.getAttribute("error") != null) { %>
                <div class="mensaje__error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/reportes"
                  method="post"
                  enctype="multipart/form-data"
                  class="form__reporte">

                <div class="form__campo">
                    <label>Tipo de reporte</label>
                    <select name="tipo" required>
                        <option value="">Seleccione una opción</option>
                        <option value="CONSUMO_ELECTRICO">Consumo eléctrico</option>
                        <option value="AVANCE_PROYECTOS">Avance de proyectos</option>
                        <option value="INFRAESTRUCTURA">Infraestructura</option>
                        <option value="GENERAL">General</option>
                    </select>
                </div>

                <div class="form__campo">
                    <label>Descripción</label>
                    <textarea name="descripcion" rows="4" required></textarea>
                </div>
                <div class="form__campo">
                    <label>Documento adjunto</label>
                    <input type="file" name="documento">
                </div>
                <button type="submit" class="btn__generar">
                    Generar Reporte
                </button>

            </form>

        </div>

        <br>

        <div class="reportes__card">

            <h2>Reportes Generados</h2>

            <% if(reportes != null && !reportes.isEmpty()) { %>

                <table class="tabla__reportes">
                    <thead>
                    <tr>
                        <th>Tipo</th>
                        <th>Descripción</th>
                        <th>Fecha</th>
                        <th>Usuario</th>
                    </tr>
                    </thead>

                    <tbody>
                    <% for(Reporte reporte : reportes) { %>
                        <tr>
                            <td>
                                <span class="reporte__tipo">
                                    <%= reporte.tipo() %>
                                </span>
                            </td>

                            <td><%= reporte.descripcion() %></td>
                            <td><%= reporte.fecha() %></td>
                            <td><%= reporte.usuario().UserName() %></td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>

            <% } else { %>

                <div class="sin__reportes">
                    <h2>No hay reportes generados</h2>
                    <p>Todavía no se cargaron reportes en el sistema.</p>
                </div>

            <% } %>

            <br>

            <a href="${pageContext.request.contextPath}/perfiles"
               class="btn__volver">
                Volver
            </a>

        </div>

    </div>

</main>

<footer>
    <div class="div__footer--container">
        <p>Parque Industrial de Viedma</p>
        Sistema de gestión del parque industrial.
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