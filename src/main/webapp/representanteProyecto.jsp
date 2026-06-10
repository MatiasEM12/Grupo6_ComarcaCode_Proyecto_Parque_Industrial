<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ProyectoProductivo" %>
<%@ page import="model.Lote" %>
<%@ page import="model.AvanceDeProyecto" %>
<%@ page import="model.Documento" %>
<%@ page import="model.DTO.EvaluacionTecnicaDTO" %>

<%
    ProyectoProductivo proyecto = (ProyectoProductivo) request.getAttribute("proyecto");
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

<main>

    <div class="project__container">

        <h2>Proyecto Productivo</h2>

        <!-- FORMULARIO DEL PROYECTO -->

        <form class="project__form"
              action="${pageContext.request.contextPath}/actualizarProyecto"
              method="post">

            <input type="hidden"
                   name="idProyecto"
                   value="<%= proyecto.idProyecto() %>">

            <div class="form__group">

                <label>Nombre del Proyecto</label>

                <input type="text"
                       name="nombreProyecto"
                       value="<%= proyecto.nombre() %>">

            </div>

            <div class="form__group">

                <label>Descripción</label>

                <textarea name="descripcion"><%= proyecto.descripcion() %></textarea>

            </div>

            <div class="form__group">

                <label>Superficie</label>

                <input type="number"
                       step="0.01"
                       name="superficie"
                       value="<%= proyecto.superficie() %>">

            </div>

            <div class="form__group">

                <label>Necesidades</label>

                <input type="text"
                       name="necesidades"
                       value="<%= proyecto.necesidades() %>">

            </div>

            <div class="form__group">

                <label>Empleabilidad</label>

                <input type="number"
                       name="empleabilidad"
                       value="<%= proyecto.empleabilidad() %>">

            </div>

            <div class="form__group">

                <label>Materia Prima</label>

                <input type="text"
                       name="materiaPrima"
                       value="<%= proyecto.materiaPrima() %>"

            <div class="form__group">

                <label>Estado del Proyecto</label>

                <input type="text"
                       value="<%= proyecto.estado() %>"
                       readonly>

            </div>

            <div class="form__group">

                <label>Lote Asignado</label>

                <input type="text"
                       value="Lote Nº <%= proyecto.idLote() %>"
                       readonly>

            </div>




            <!-- BOTONES -->

            <div class="buttons__container">

                <button type="submit" class="btn">

                    Actualizar

                </button>
                <a href="${pageContext.request.contextPath}/loteProyecto?idProyecto=<%= proyecto.idProyecto() %>"
                   class="btn btn__secondary">
                    Ver Lote

                </a>

            </div>

        </form>
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
        <div class="avance__container">

            <h3>Registrar Avance del Proyecto</h3>

            <form class="avance__form"
                  action="${pageContext.request.contextPath}/subirAvance"
                  method="post"
                  enctype="multipart/form-data">

                <input type="hidden"
                       name="idProyecto"
                       value="<%= proyecto.idProyecto() %>">

                <div class="form__group">

                    <label>Descripción del avance</label>

                    <textarea name="descripcionAvance"
                              required></textarea>

                </div>

                <div class="form__group">

                    <label>Estado del Proyecto</label>


            </div>
                    <select name="estado"
                            required>

                        <option value="EN_EJECUCION">
                            En ejecución
                        </option>

                        <option value="SUSPENDIDO">
                            Suspendido
                        </option>

                        <option value="FINALIZADO">
                            Finalizado
                        </option>

                    </select>

                </div>

                <hr>

                <h4>Documentación Adjunta</h4>

                <div id="documentosContainer">

                    <div class="documentoItem">

                        <div class="form__group">

                            <label>Tipo de Documento</label>

                            <select name="tipoDocumento">

                                <option value="PLANO_IMPLANTACION">
                                    Plano de Implantación
                                </option>

                                <option value="MEMORIA_DESCRIPTIVA">
                                    Memoria Descriptiva
                                </option>

                                <option value="ESTUDIO_MERCADO">
                                    Estudio de Mercado
                                </option>

                                <option value="IMPACTO_AMBIENTAL">
                                    Impacto Ambiental
                                </option>

                                <option value="REQUERIMIENTOS_INFRAESTRUCTURA">
                                    Requerimientos de Infraestructura
                                </option>

                                <option value="OTRO">
                                    Otro
                                </option>

                            </select>

                        </div>

                        <div class="form__group">

                            <label>Archivo PDF</label>

                            <input type="file"
                                   name="archivoDocumento"
                                   accept=".pdf"
                                   required>

                        </div>
                        <button type="button"  class="btn btn__danger"  onclick="eliminarDocumento(this)">

                             Eliminar Documento

                        </button>
                    </div>

                </div>

                <div class="buttons__container">

                    <button type="button"
                            class="btn btn__secondary"
                            onclick="agregarDocumento()">

                        Agregar Documento

                    </button>

                    <button type="submit"
                            class="btn">

                        Registrar Avance

                    </button>

                </div>

            </form>

        </div>

    </div>

</main>

<footer>

    <div class="div__footer--container">

        <p>Parque Industrial</p>

          Comprometidos con el crecimiento productivo, la innovación y el desarrollo sostenible de la región. © 2026 Todos los derechos reservados.

    </div>

</footer>


<script>

function agregarDocumento() {

    const container =
        document.getElementById("documentosContainer");

    const nuevoDocumento =
        document.createElement("div");

    nuevoDocumento.className = "documentoItem";

    nuevoDocumento.innerHTML = `
        <hr>

        <div class="form__group">

            <label>Tipo de Documento</label>

            <select name="tipoDocumento">

                <option value="PLANO_IMPLANTACION">
                    Plano de Implantación
                </option>

                <option value="MEMORIA_DESCRIPTIVA">
                    Memoria Descriptiva
                </option>

                <option value="ESTUDIO_MERCADO">
                    Estudio de Mercado
                </option>

                <option value="IMPACTO_AMBIENTAL">
                    Impacto Ambiental
                </option>

                <option value="REQUERIMIENTOS_INFRAESTRUCTURA">
                    Requerimientos de Infraestructura
                </option>

                <option value="OTRO">
                    Otro
                </option>

            </select>

        </div>

        <div class="form__group">

            <label>Archivo PDF</label>

            <input type="file"
                   name="archivoDocumento"
                   accept=".pdf"
                   required>

        </div>

        <button type="button"
                class="btn btn__danger"
                onclick="eliminarDocumento(this)">

            Eliminar Documento

        </button>
    `;

    container.appendChild(nuevoDocumento);
}

function eliminarDocumento(boton) {

    const documentos = document.querySelectorAll(".documentoItem");

    if (documentos.length === 1) {

        alert("Debe existir al menos un documento.");

        return;
    }

    boton.closest(".documentoItem").remove();
}

</script>

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