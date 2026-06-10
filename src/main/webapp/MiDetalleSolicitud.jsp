<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.SolicitudRadicacion" %>
<%@ page import="model.EstadoSolicitud" %>
<%@ page import="model.Documento" %>
<%@ page import="model.Observacion" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    SolicitudRadicacion solicitud =  (SolicitudRadicacion) request.getAttribute("solicitud");



    String claseEstado = "estado__pendiente";

    if(solicitud.estadoSolicitud() == EstadoSolicitud.APROBADA_PRIMER_INSTANCIA){
        claseEstado = "estado__aprobado_1";
    }

    if(solicitud.estadoSolicitud() == EstadoSolicitud.APROBADA_FINAL){
            claseEstado = "estado__aprobado_2";
    }

    else if(solicitud.estadoSolicitud() == EstadoSolicitud.OBSERVADA){
        claseEstado = "estado__revision";
    }

    else if(solicitud.estadoSolicitud() == EstadoSolicitud.RECHAZADA){
        claseEstado = "estado__rechazado";
    }
%>


<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ParqueIndustrialViedma</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/miDetalleSolicitud.css">
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
                <a href="${pageContext.request.contextPath}/mainRepresentante.jsp"  class="nav__link">
                    Inicio
                </a>
            </li>

            <li class="nav__item">
                <a href="" class="nav__link">
                    Perfil
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/representanteProyectos.jsp" class="nav__link">
                    Mis Proyectos
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/solicitudRadicacion.jsp" class="nav__link">
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

<main>

      <div class="detalle__container">

            <div class="detalle__card">

                    <div class="detalle__header">

                         <h2>

                             <%= solicitud.nombreProyecto() %>

                         </h2>

                         <span class="estado <%= claseEstado %>">

                             <%= solicitud.estadoSolicitud() %>

                         </span>

                    </div>

                     <div class="detalle__body">

                         <p><strong>N° trámite:</strong>
                             <%= solicitud.numeroTramite() %>
                         </p>

                         <p><strong>Representante:</strong>
                             <%= solicitud.representante().dni() %>
                         </p>

                         <p><strong>Objeto:</strong>
                             <%= solicitud.objeto() %>
                         </p>

                         <p><strong>Descripción:</strong>
                             <%= solicitud.descripcionServicio() %>
                         </p>

                         <p><strong>Emplazamiento:</strong>
                             <%= solicitud.emplazamiento() %>
                         </p>

                         <p><strong>Personal:</strong>
                             <%= solicitud.personal() %>
                         </p>

                         <p><strong>Tiempo radicación:</strong>
                             <%= solicitud.tiempoRadicacion() %>
                         </p>

                         <p><strong>m²:</strong>
                             <%= solicitud.m2() %>
                         </p>

                         <p><strong>Área trabajo:</strong>
                             <%= solicitud.areaTrabajo() %>
                         </p>

                         <p><strong>Área depósito:</strong>
                             <%= solicitud.areaDeposito() %>
                         </p>

                         <p><strong>Estacionamiento:</strong>
                             <%= solicitud.estacionamiento() %>
                         </p>

                         <p><strong>Planos:</strong>
                             <%= solicitud.planos() %>
                         </p>

                         <p><strong>Empleabilidad:</strong>
                             <%= solicitud.empleabilidad() %>
                         </p>

                         <p><strong>Materias primas:</strong>
                             <%= solicitud.materiasPrimas() %>
                         </p>

                         <p><strong>Destino producción:</strong>
                             <%= solicitud.destinoProduccion() %>
                         </p>

                         <p><strong>Tensión:</strong>
                             <%= solicitud.tension() %>
                         </p>

                         <p><strong>Potencia:</strong>
                             <%= solicitud.potencia() %>
                         </p>

                         <p><strong>Agua:</strong>
                             <%= solicitud.agua() %>
                         </p>

                         <p><strong>Gas:</strong>
                             <%= solicitud.gas() %>
                         </p>

                         <p><strong>Residuos:</strong>
                             <%= solicitud.residuos() %>
                         </p>

                         <p><strong>Tratamiento:</strong>
                             <%= solicitud.tratamiento() %>
                         </p>

                         <p><strong>Balanza:</strong>
                             <%= solicitud.balanza() %>
                         </p>

                         <p><strong>Comedor:</strong>
                             <%= solicitud.comedor() %>
                         </p>

                         <p><strong>Coworking:</strong>
                             <%= solicitud.coworking() %>
                         </p>

                         <p><strong>Archivo PDF:</strong>
                             <%= solicitud.nombreArchivoPDF() %>
                         </p>

                     </div>

                     <div class="acciones__container">


                           <form action="${pageContext.request.contextPath}/actualizarDatosPrincipales" method="post">

                               <input type="hidden"
                                  name="idSolicitud"
                                  value="<%= solicitud.id() %>">

                                   <button type="submit" class="btn__actualizarDatosPrincipales">
                                        Actualizar Datos Principales
                                   </button>
                           </form>
                          <% if (solicitud.estadoSolicitud() == EstadoSolicitud.APROBADA_PRIMER_INSTANCIA
                                      || solicitud.estadoSolicitud() == EstadoSolicitud.APROBADA_FINAL) { %>

                              <% if (solicitud.documentos().isEmpty()) { %>

                                  <!-- FORMULARIO PARA CARGAR DOCUMENTOS -->

                                  <h3>Documentación complementaria</h3>

                                  <form action="${pageContext.request.contextPath}/cargarArchivosSolicitud"
                                        method="post"
                                        enctype="multipart/form-data">

                                      <input type="hidden"
                                             name="idSolicitud"
                                             value="<%= solicitud.id() %>">

                                      <div>
                                          <label>Plano Implantación</label>
                                          <input type="file" name="PLANO_IMPLANTACION">
                                      </div>

                                      <div>
                                          <label>Impacto ambiental</label>
                                          <input type="file" name="IMPACTO_AMBIENTAL">
                                      </div>

                                      <div>
                                          <label>Estudio de mercado</label>
                                          <input type="file" name="ESTUDIO_MERCADO">
                                      </div>

                                      <div>
                                          <label>Memoria descriptiva</label>
                                          <input type="file" name="MEMORIA_DESCRIPTIVA">
                                      </div>

                                      <div>
                                          <label>Requerimientos de infraestructura</label>
                                          <input type="file" name="REQUERIMIENTOS_INFRAESTRUCTURA">
                                      </div>

                                      <button type="submit">
                                          Guardar documentos
                                      </button>

                                  </form>

                              <% } else { %>

                                  <!-- TABLA DE DOCUMENTOS YA CARGADOS -->

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

                                      <% for (Documento documento : solicitud.documentos()) { %>

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

                              <% } %>

                          <% } %>
                            <form action="${pageContext.request.contextPath}/proyectoDeSolicitud" method="post">

                                 <input type="hidden"
                                        name="idSolicitud"
                                        value="<%= solicitud.id() %>">


                                 <button type="submit" class="btn__verProyecto">
                                     ver Proyectos asociado

                                 </button>

                            </form>


                     </div>

                 </div>
                 <% if(!solicitud.obtenerObservaciones().isEmpty()) { %>

                     <h3>Observaciones Administrativas</h3>

                     <table class="tabla-observaciones">

                         <thead>
                             <tr>
                                 <th>Fecha</th>

                                 <th>Observación</th>
                             </tr>
                         </thead>

                         <tbody>

                         <% for(Observacion observacion : solicitud.obtenerObservaciones()) { %>

                             <tr>

                                 <td>
                                     <%= observacion.fechaCreacion() %>
                                 </td>


                                 <td>

                                     <details class="detalle-observacion">

                                         <summary>
                                             Ver observación
                                         </summary>

                                         <div class="contenido-observacion">

                                             <%= observacion.observacion() %>

                                         </div>

                                     </details>

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

</body>
</html>