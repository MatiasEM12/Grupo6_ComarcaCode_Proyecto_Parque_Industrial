<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.ProyectoProductivo" %>
<%@ page import="java.util.List" %>
<%@ page import="model.AvanceDeProyecto" %>
<%@ page import="model.Documento" %>
<%@ page import="model.DTO.EvaluacionTecnicaDTO" %>

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
            : "/mainOrganismoPublico.jsp";


    ProyectoProductivo proyecto =(ProyectoProductivo) request.getAttribute("proyecto");


    List<AvanceDeProyecto> avances =proyecto.avances();

%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proyectos en Ejecución</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/miDetalleProyecto.css">
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

    <div class="project__container">

        <h2>Proyecto Productivo</h2>

        <div class="project__form">

            <div class="form__group">
                <label>Nombre del Proyecto</label>
                <input type="text"
                       value="<%= proyecto.nombre() %>"
                       readonly>
            </div>

            <div class="form__group">
                <label>Descripción</label>
                <textarea readonly><%= proyecto.descripcion() %></textarea>
            </div>

            <div class="form__group">
                <label>Superficie</label>
                <input type="text"
                       value="<%= proyecto.superficie() %>"
                       readonly>
            </div>

            <div class="form__group">
                <label>Necesidades</label>
                <input type="text"
                       value="<%= proyecto.necesidades() %>"
                       readonly>
            </div>

            <div class="form__group">
                <label>Empleabilidad</label>
                <input type="text"
                       value="<%= proyecto.empleabilidad() %>"
                       readonly>
            </div>

            <div class="form__group">
                <label>Materia Prima</label>
                <input type="text"
                       value="<%= proyecto.materiaPrima() %>"
                       readonly>
            </div>

            <div class="form__group">
                <label>Estado</label>
                <input type="text"
                       value="<%= proyecto.estado() %>"
                       readonly>
            </div>

            <div class="form__group">
                <label>Empresa</label>
                <input type="text"
                       value="<%= proyecto.empresa().razonSocial() %>"
                       readonly>
            </div>

            <div class="form__group">
                <label>Lote Asignado</label>
                <input type="text"
                       value="Lote Nº <%= proyecto.idLote() %>"
                       readonly>
            </div>

            <div class="buttons__container">

                <a href="${pageContext.request.contextPath}/loteProyecto?idProyecto=<%= proyecto.idProyecto() %>"
                   class="btn btn__secondary">

                    Ver Lote

                </a>

            </div>

        </div>

    </div><div class="project__container">

              <h2>Proyecto Productivo</h2>

              <div class="project__form">

                  <div class="form__group">
                      <label>Nombre del Proyecto</label>
                      <input type="text"
                             value="<%= proyecto.nombre() %>"
                             readonly>
                  </div>

                  <div class="form__group">
                      <label>Descripción</label>
                      <textarea readonly><%= proyecto.descripcion() %></textarea>
                  </div>

                  <div class="form__group">
                      <label>Superficie</label>
                      <input type="text"
                             value="<%= proyecto.superficie() %>"
                             readonly>
                  </div>

                  <div class="form__group">
                      <label>Necesidades</label>
                      <input type="text"
                             value="<%= proyecto.necesidades() %>"
                             readonly>
                  </div>

                  <div class="form__group">
                      <label>Empleabilidad</label>
                      <input type="text"
                             value="<%= proyecto.empleabilidad() %>"
                             readonly>
                  </div>

                  <div class="form__group">
                      <label>Materia Prima</label>
                      <input type="text"
                             value="<%= proyecto.materiaPrima() %>"
                             readonly>
                  </div>

                  <div class="form__group">
                      <label>Estado</label>
                      <input type="text"
                             value="<%= proyecto.estado() %>"
                             readonly>
                  </div>

                  <div class="form__group">
                      <label>Empresa</label>
                      <input type="text"
                             value="<%= proyecto.empresa().razonSocial() %>"
                             readonly>
                  </div>

                  <div class="form__group">
                      <label>Lote Asignado</label>
                      <input type="text"
                             value="Lote Nº <%= proyecto.idLote() %>"
                             readonly>
                  </div>

                  <div class="buttons__container">

                      <a href="${pageContext.request.contextPath}/loteProyecto?idProyecto=<%= proyecto.idProyecto() %>"
                         class="btn btn__secondary">

                          Ver Lote

                      </a>

                  </div>

              </div>

          </div>

   <div class="avances__container">
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
          </div>

          <div class="avances__container">
              <h3>Evaluaciones Técnicas</h3>

              <table class="tabla-documentos">

                  <thead>
                      <tr>
                          <th>Fecha</th>
                          <th>Resultado</th>
                          <th>Descripción</th>
                          <th>Observaciones</th>
                      </tr>
                  </thead>

                  <tbody>

                  <%
                      if (proyecto.evaluaciones() != null &&
                          !proyecto.evaluaciones().isEmpty()) {

                          for (EvaluacionTecnicaDTO evaluacion : proyecto.evaluaciones()) {
                  %>

                      <tr>
                          <td><%= evaluacion.fecha() %></td>
                          <td><%= evaluacion.resultado() %></td>
                          <td><%= evaluacion.descripcion() %></td>
                          <td><%= evaluacion.observaciones() %></td>
                      </tr>

                  <%
                          }
                      } else {
                  %>

                      <tr>
                          <td colspan="4">
                              No existen evaluaciones técnicas registradas.
                          </td>
                      </tr>

                  <%
                      }
                  %>

                  </tbody>
              </table>
          </div>
          <!-- AVANCES -->
          <div class="avances__container">

              <h3>Listado de Avances</h3>

              <table class="tabla__avances">

                  <thead>

                      <tr>
                          <th>Fecha</th>
                          <th>Descripción</th>
                          <th>Estado</th>
                          <th>Detalle</th>
                      </tr>

                  </thead>

                  <tbody>

                  <%
                      for(AvanceDeProyecto avance : proyecto.avances()){
                  %>

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

                              <a href="${pageContext.request.contextPath}/detalleAvance?idAvance=<%= avance.id() %>"
                                 class="btn__detalle">

                                  Ver

                              </a>

                          </td>

                      </tr>

                  <%
                      }
                  %>

                  </tbody>

              </table>

          </div>

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