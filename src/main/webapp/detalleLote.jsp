<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Lote" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.ProyectoProductivo" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null || !usuario.nombreRol().equals("administrador")){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    Lote lote = (Lote) request.getAttribute("lote");

    if(lote == null){
        response.sendRedirect(request.getContextPath() + "/listadoLotes");
        return;
    }

    ProyectoProductivo proyecto = (ProyectoProductivo) request.getAttribute("proyecto");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle Lote</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/listadoLotes.css">
</head>

<body>

    <header class="header">
        <div class="header__overlay"></div>
        <div class="header__item--container">
            <h1>DETALLE DEL LOTE #<%= lote.id() %></h1>
            <p>Visualización y edición del lote seleccionado.</p>
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

        <div class="crear__lote--mini">

            <form action="${pageContext.request.contextPath}/editarLote" method="POST" class="crear__lote--formMini">

                <input type="hidden"
                       name="id"
                       value="<%= lote.id() %>">

                <input type="number"
                       name="latitud"
                       value="<%= lote.ubicacion().latitud() %>"
                       placeholder="Latitud"
                       required>

                <input type="number"
                       name="longitud"
                       value="<%= lote.ubicacion().longitud() %>"
                       placeholder="Longitud"
                       required>

                <input type="number"
                       name="altitud"
                       value="<%= lote.ubicacion().altitud() %>"
                       placeholder="Altitud"
                       required>

                <select name="superficie">

                    <option value="1200">1200 m² aprox</option>
                    <option value="1800">1800 m² aprox</option>
                    <option value="2500">2500 m² aprox</option>
                    <option value="3300">3300 m² aprox</option>
                    <option value="5000">5000 m² aprox</option>
                    <option value="6000">6000 m² aprox</option>

                </select>
                <select name="estado" required>
                    <option value="DISPONIBLE"
                            <%= lote.estado().equals("DISPONIBLE") ? "selected" : "" %>>
                        DISPONIBLE
                    </option>

                    <option value="OCUPADO"
                            <%= lote.estado().equals("OCUPADO") ? "selected" : "" %>>
                        OCUPADO
                    </option>
                </select>

                <input type="text"
                       name="infraestructura"
                       value="<%= lote.infraestructura() %>"
                       placeholder="Infraestructura"
                       required>

                <button type="submit">
                    Guardar cambios
                </button>

            </form>

        </div>

        <div class="lotes__container">

            <article class="lote__card">
                <div class="lote__content">

                    <h2>Lote #<%= lote.id() %></h2>

                    <p>Latitud: <%= lote.ubicacion().latitud() %></p>
                    <p>Longitud: <%= lote.ubicacion().longitud() %></p>
                    <p>Altitud: <%= lote.ubicacion().altitud() %></p>
                    <p>Superficie: <%= lote.superficie() %> m²</p>
                    <p>Infraestructura: <%= lote.infraestructura() %></p>

                    <span class="lote__state">
                        <%= lote.estado() %>
                    </span>

                </div>
            </article>
        </div>
        <div class="proyecto__container">

            <article class="project__card">

                <div class="project__content">

                    <h2>Proyecto productivo asociado</h2>

                    <%
                        if (proyecto != null) {
                    %>

                        <a href="${pageContext.request.contextPath}/representanteProyecto?id=<%= proyecto.idProyecto() %>"
                           class="project__link">

                            <h3><%= proyecto.nombre() %></h3>

                            <p><%= proyecto.descripcion() %></p>

                            <p>
                                Superficie: <%= proyecto.superficie() %> m²
                            </p>

                            <p>
                                Estado:
                                <%= proyecto.enEjecucion()
                                        ? "En ejecución"
                                        : "No comenzó su ejecución" %>
                            </p>

                        </a>

                    <%
                        } else {
                    %>

                        <div class="sin__proyectos">

                            <h3>
                                Este lote no tiene proyecto productivo asignado
                            </h3>

                        </div>

                    <%
                        }
                    %>

                </div>

            </article>

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