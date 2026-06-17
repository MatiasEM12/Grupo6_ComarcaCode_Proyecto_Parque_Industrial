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

    String paginaInicio = usuario.nombreRol().equals("administrador")
            ? "/mainAdm.jsp"
            : "/mainOrganismoPublico.jsp";

    List<ProyectoProductivo> proyectos =
            (List<ProyectoProductivo>) request.getAttribute("proyectos");
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proyectos en Ejecución</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/proyectosEnEjecucion.css">
</head>

<body>

<header class="header">
    <div class="header__overlay"></div>

    <div class="header__item--container">
        <h1>PROYECTOS EN EJECUCIÓN</h1>

        <p>
            Visualización de proyectos productivos activos
            dentro del Parque Industrial.
        </p>
    </div>
</header>

<nav class="nav">

    <div class="nav__ul--container">

        <ul class="nav__ul">

            <li class="nav__item">
                <a href="<%= request.getContextPath() + paginaInicio %>" class="nav__link">
                    Inicio
                </a>
            </li>


            <% if(usuario.nombreRol().equals("administrador")) { %>

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
                         <a href="${pageContext.request.contextPath}/listadoLotes" class="nav__link">
                             Lotes
                         </a>
                     </li>


                     <li class="nav__item">
                           <a href="<%= request.getContextPath() %>/Informes" class="nav__link">
                                Informes
                           </a>
                    </li>

            <% } %>

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

    <div class="proyectos__container">

        <% if(proyectos != null && !proyectos.isEmpty()) { %>

            <% for(ProyectoProductivo proyecto : proyectos) { %>

                <div class="proyecto__card">

                   <% if(usuario.nombreRol().equals("organismo_publico")) { %>

                       <div class="proyecto__content">

                   <% } else { %>

                       <a class="proyecto__link"
                          href="<%= request.getContextPath() %>/detalleProyecto?idProyecto=<%= proyecto.idProyecto() %>">

                           <div class="proyecto__content">

                   <% } %>
                        <div class="proyecto__content">

                            <h2><%= proyecto.nombre() %></h2>

                            <p>
                                <strong>Descripción:</strong>
                                <%= proyecto.descripcion() %>
                            </p>

                            <p>
                                <strong>Superficie:</strong>
                                <%= proyecto.superficie() %> m²
                            </p>

                             <% if(!usuario.nombreRol().equals("organismo_publico")) { %>

                                  <p>
                                      <strong>Empleabilidad:</strong>
                                      <%= proyecto.empleabilidad() %> empleados
                                  </p>

                                  <p>
                                      <strong>Materia prima:</strong>
                                      <%= proyecto.materiaPrima() %>
                                  </p>

                            <% } %>
                            <span class="proyecto__state estado__ejecucion">
                                <%= proyecto.estado() %>
                            </span>

                        </div>

                    </a>

                    <% if(usuario.nombreRol().equals("organismo_publico")) { %>

                        <div class="acciones__proyecto">

                            <a class="btn__evaluacion"
                               href="<%= request.getContextPath() %>/evaluacionTecnica?idProyecto=<%= proyecto.idProyecto() %>">
                                Realizar evaluación técnica
                            </a>

                            <a class="btn__evaluacion btn__secundario"
                               href="<%= request.getContextPath() %>/evaluacionesTecnicas?idProyecto=<%= proyecto.idProyecto() %>">
                                Ver evaluaciones técnicas
                            </a>
                        </div>
                    <% } %>
                </div>
            <% } %>

        <% } else { %>

            <div class="sin__Proyectos">

                <h2>No hay proyectos en ejecución</h2>

                <p>
                    Todavía no existen proyectos productivos activos dentro del parque.
                </p>

            </div>

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