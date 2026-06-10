<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ProyectoProductivo" %>
<%@ page import="model.Lote" %>
<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ParqueIndustrialViedma</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/representanteProyectos.css">

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

       <a href="${pageContext.request.contextPath}/logout"
                class="nav__link Link--Cerrar">
            Cerrar Sesión

        </a>

    </div>

</nav>

<main>

    <div class="projects__container">

        <%
            List<ProyectoProductivo> proyectos = (List<ProyectoProductivo>) request.getAttribute("proyectos");

            if (proyectos != null && !proyectos.isEmpty()) {

                for (ProyectoProductivo proyecto : proyectos) {
        %>

        <a href="${pageContext.request.contextPath}/representanteProyecto?id=<%= proyecto.idProyecto() %>"
           class="project__card">

            <div class="project__content">

                <h2>

                    <%= proyecto.nombre() %>

                </h2>

                <p>

                    <%= proyecto.descripcion() %>

                </p>

                <p class="project__date">

                    Superficie:
                    <%= proyecto.superficie() %> m²

                </p>

                <p class="project__date">

                    Estado:
                    <%= proyecto.enEjecucion()
                            ? "En ejecución"
                            : "No comenzo su ejecución" %>

                </p>

                 <% if (proyecto.lote() != null) { %>

                       <form action="${pageContext.request.contextPath}/loteProyecto"
                                  method="get">

                                <input type="hidden"
                                       name="idProyecto"
                                       value="<%= proyecto.idProyecto() %>">

                                <button type="submit" class="btn__verLote">

                                    Ver lote

                                </button>

                       </form>

                  <% } %>
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

                Todavía no tenés proyectos productivos.

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