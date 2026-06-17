<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.AvanceDeProyecto" %>
<%@ page import="model.Documento" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null || !usuario.nombreRol().equals("administrador")){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    AvanceDeProyecto avance =(AvanceDeProyecto) request.getAttribute("avance");

    if(avance == null){
        response.sendRedirect(request.getContextPath() + "/proyectosEnEjecucion");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle Lote</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/representanteAvanceProyecto.css">
</head>

<body>

 <header class="header">

     <div class="header__overlay"></div>

     <div class="header__item--container">

         <h1>
             DETALLE AVANCE #<%= avance.id() %>
         </h1>

         <p>
             Visualización de la información y documentación asociada al avance.
         </p>

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

            <div class="avance__descripcion">

                <strong>Descripción:</strong>

                <details class="detalle-observacion">

                    <summary>
                        Ver descripción
                    </summary>

                    <div class="contenido-observacion">

                        <%= avance.descripcion() %>

                    </div>

                </details>

            </div>

        </div>

    </section>

    <section class="documentos">

        <h2>Documentos del Avance</h2>

        <table class="tabla-documentos">

            <thead>

            <tr>
                <th>Tipo</th>
                <th>Nombre</th>
                <th>Descargar</th>
            </tr>

            </thead>

            <tbody>

            <% for(Documento documento : avance.documentos()) { %>

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