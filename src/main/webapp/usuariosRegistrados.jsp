<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="model.Usuario" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

    // VALIDAR SESIÓN

    if(usuarioLogueado == null){

        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    // VALIDAR ADMINISTRADOR

    if(!usuarioLogueado.nombreRol().equals("administrador")){

        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");

    String filtroRol = request.getParameter("rol");

    if(filtroRol == null){
        filtroRol = "todos";
    }
%>

<!DOCTYPE html>

<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Usuarios Registrados</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/usuariosRegistrados.css">

</head>

<body>

<header class="header">

    <div class="header__overlay"></div>

    <div class="header__item--container">

        <h1>
            USUARIOS REGISTRADOS
        </h1>

        <p>
            Consulta de usuarios registrados dentro
            del sistema del Parque Industrial.
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

    <div class="filtro__container">

        <form method="get" action="${pageContext.request.contextPath}/usuariosRegistrados">

            <select name="rol" class="filtro__select">

                <option value="todos"
                    <%= filtroRol.equals("todos") ? "selected" : "" %>>
                    Todos
                </option>

                <option value="administrador"
                    <%= filtroRol.equals("administrador") ? "selected" : "" %>>
                    Administrador
                </option>

                <option value="organismo_publico"
                    <%= filtroRol.equals("organismo_publico") ? "selected" : "" %>>
                    Organismo Público
                </option>

                <option value="representante"
                    <%= filtroRol.equals("representante") ? "selected" : "" %>>
                    Representante
                </option>

            </select>

            <button type="submit"
                    class="filtro__btn">
                Filtrar
            </button>

        </form>

    </div>

    <div class="usuarios__container">

        <%
            if(usuarios == null || usuarios.isEmpty()){
        %>

        <div class="card">

            <div class="card__content">
                <h2>
                    No hay usuarios registrados
                </h2>
            </div>

        </div>
        <%
            } else {

                for(Usuario u : usuarios){

                    boolean mostrar =filtroRol.equals("todos")|| u.nombreRol().equalsIgnoreCase(filtroRol);

                    if(!mostrar){
                        continue;
                    }
        %>

        <article class="usuario__card">

            <div class="usuario__header">

                <h2>
                    <%= u.UserName() %>
                </h2>

            </div>

            <div class="usuario__body">

                <p>
                    <strong>Email:</strong>
                    <%= u.gmail() %>
                </p>

                <p>
                    <strong>Rol:</strong>
                    <%= u.nombreRol() %>
                </p>

            </div>

        </article>

        <%
                }
            }
        %>

    </div>

</main>

<footer>

    <div class="div__footer--container">

        <p>
            Parque Industrial
        </p>

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