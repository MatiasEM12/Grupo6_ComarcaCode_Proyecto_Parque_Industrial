<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.ProyectoProductivo" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    if(!usuario.nombreRol().equals("organismo_publico") && !usuario.nombreRol().equals("administrador")){

        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    String paginaInicio = usuario.rol().equals("administrador")
            ? "/mainAdm.jsp"
            : "/mainOrganismoPublico.jsp";

    List<ProyectoProductivo> proyectos = (List<ProyectoProductivo>) request.getAttribute("proyectos");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proyectos en Ejecución</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/mainOrganismoPublico.css">
</head>

<body>

<header class="header">
    <div class="header__overlay"></div>

    <div class="header__item--container">
        <h1>PROYECTOS EN EJECUCIÓN</h1>
        <p>Proyectos productivos creados a partir de solicitudes aprobadas.</p>
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
    <section class="cards-container">

        <%
            if(proyectos == null || proyectos.isEmpty()){
        %>

        <article class="proyecto__card">
            <div class="card__header">
                <h2>No hay proyectos aprobados</h2>
            </div>

            <div class="card__body">
                <p>
                    Todavía no existen proyectos productivos en ejecución.
                    Primero el administrador debe aprobar una solicitud de radicación.
                </p>
            </div>
        </article>

        <%
            } else {
                for(ProyectoProductivo proyecto : proyectos){
        %>

        <article class="proyecto__card">
            <div class="card__header">
                <h2><%= proyecto.nombre() %></h2>

                <span class="estado estado--activo">
                    <%= proyecto.estado() %>
                </span>
            </div>

            <div class="card__body">
                <p><%= proyecto.descripcion() %></p>

                <div class="info">
                    <p><strong>Superficie:</strong> <%= proyecto.superficie() %> m²</p>
                    <p><strong>Empleabilidad:</strong> <%= proyecto.empleabilidad() %> empleados</p>
                    <p><strong>Materia Prima:</strong> <%= proyecto.materiaPrima() %></p>

                </div>
            </div>
        </article>

        <%
                }
            }
        %>

    </section>

    <div class="volver-container">
        <a href="${pageContext.request.contextPath}<%= paginaInicio %>"
           class="btn-volver">
            Volver al menú
        </a>
    </div>
</main>

<footer>
    <div class="div__footer--container">
        <p>Parque Industrial</p>
          Comprometidos con el crecimiento productivo, la innovación y el desarrollo sostenible de la región. © 2026 Todos los derechos reservados.
    </div>
</footer>

</body>
</html>