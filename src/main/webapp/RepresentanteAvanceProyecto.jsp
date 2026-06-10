<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ProyectoProductivo" %>
<%@ page import="model.Lote" %>
<%@ page import="model.AvanceDeProyecto" %>
<%@ page import="model.Documento" %>

<%
    AvanceDeProyecto avance= (AvanceDeProyecto) request.getAttribute("avance");
%>
<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ParqueIndustrialViedma</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/representanteProyecto.css">

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

                <a href="" class="nav__link">

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

<main class="main">

    <section class="avance">

        <h2>Datos del Avance</h2>

        <div class="avance__info">

            <p>
                <strong>ID:</strong>
                <%= avance.id() %>
            </p>

            <p>
                <strong>Fecha:</strong>
                <%= avance.fechaCreacion() %>
            </p>

            <p>
                <strong>Estado:</strong>
                <%= avance.estado() %>
            </p>

            <p>
                <strong>Descripción:</strong>
                <%= avance.descripcion() %>
            </p>

        </div>

    </section>

    <section class="documentos">

        <h2>Documentos del Avance</h2>

        <table class="tabla-documentos">

            <thead>

            <tr>
                <th>Tipo</th>
                <th>Nombre</th>
                <th>Archivo</th>
                <th>Descargar</th>
            </tr>

            </thead>

            <tbody>

            <% for(Documento documento : avance.documentos()) { %>

            <tr>

                <td><%= documento.tipo() %></td>

                <td><%= documento.nombreArchivo() %></td>

                <td>
                    <%= documento.rutaArchivo() %>
                </td>

                <td>

                    <a href="${pageContext.request.contextPath}/descargarDocumento?id=<%= documento.id() %>">
                        Descargar
                    </a>

                </td>

            </tr>

            <% } %>

            </tbody>

        </table>

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