<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.ProyectoProductivo" %>
<%@ page import="java.util.List" %>
<%@ page import="model.AvanceDeProyecto" %>
<%@ page import="model.Documento" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    if( !usuario.nombreRol().equals("administrador")){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    String paginaInicio = usuario.nombreRol().equals("administrador")
            ? "/mainAdm.jsp"


    ProyectoProductivo proyecto =(ProyectoProductivo) request.getAttribute("proyecto");


    List<AvanceProyecto> avances =proyecto.avances();

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
        <h1>PROYECTO </h1>

        <p>
            Visualización de proyecto productivo
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

            <li class="nav__item">
                <a href="#" class="nav__link">
                    Perfil
                </a>
            </li>

            <% if(usuario.nombreRol().equals("administrador")) { %>

                <li class="nav__item">
                    <a href="<%= request.getContextPath() %>/usuariosRegistrados" class="nav__link">
                        Usuarios
                    </a>
                </li>

                <li class="nav__item">
                    <a href="<%= request.getContextPath() %>/solicitudesAdmin" class="nav__link">
                        Solicitudes
                    </a>
                </li>

                <li class="nav__item">
                    <a href="<%= request.getContextPath() %>/listadoLotes" class="nav__link">
                        Lotes
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



<main class="main">

    <section class="proyecto-container">

        <h2>Datos del Proyecto</h2>

        <div class="datos-proyecto">

            <div class="dato">
                <span class="titulo">Nombre:</span>
                <span><%= proyecto.getNombre() %></span>
            </div>

            <div class="dato">
                <span class="titulo">Descripción:</span>
                <span><%= proyecto.getDescripcion() %></span>
            </div>

            <div class="dato">
                <span class="titulo">Estado:</span>
                <span><%= proyecto.getEstado() %></span>
            </div>

            <div class="dato">
                <span class="titulo">Inversión:</span>
                <span>$ <%= proyecto.getMontoInversion() %></span>
            </div>

            <div class="dato">
                <span class="titulo">Empresa:</span>
                <span><%= proyecto.getEmpresa().getRazonSocial() %></span>
            </div>

        </div>

    </section>

    <section class="avances-container">

        <h2>Avances del Proyecto</h2>

        <table class="tabla-avances">

            <thead>
                <tr>
                       <th>Fecha</th>
                       <th>Descripción</th>
                       <th>Estado</th>
                       <th>Detalle</th>

                </tr>
            </thead>

            <tbody>

            <% if(avances != null && !avances.isEmpty()) { %>

                <% for(AvanceProyecto avance : avances) { %>

                    <tr>
                         <td>
                            <%= avance.fechaCreacion() %>
                         </td>

                         <td>
                            <%= avance.descripcion() %>
                         </td>

                         <td>
                            <%= avance.estado() %>
                         </td>

                         <td>

                            <a href="${pageContext.request.contextPath}/detalleAvance?idAvance=<%= avance.id() %>"class="btn__detalle">

                                Ver

                            </a>

                         </td>

                    </tr>

                <% } %>

            <% } else { %>

                <tr>
                    <td colspan="4">
                        No existen avances registrados.
                    </td>
                </tr>

            <% } %>

            </tbody>

        </table>

    </section>

    <section class="documentos-container">
        <h3>Documentos cargados</h3>

        <table class="tabla-documentos">

                                      <thead>
                                          <tr>
                                              <th>Tipo</th>
                                              <th>Nombre</th>

                                              <th>Descargar</th>
                                          </tr>
                                      </thead>

                                      <tbody>

                                      <% for (Documento documento : proyecto.documentos()) { %>

                                          <tr>

                                              <td><%= documento.tipo() %></td>

                                              <td><%= documento.nombreArchivo() %></td>



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

</main>

<footer>
    <div class="div__footer--container">
        <p>Parque Industrial</p>
        Comprometidos con el crecimiento productivo, la innovación y el desarrollo sostenible de la región. © 2026 Todos los derechos reservados.
    </div>
</footer>

</body>
</html>