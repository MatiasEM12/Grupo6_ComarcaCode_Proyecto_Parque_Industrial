<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.AdministradorDelParque" %>
<%@ page import="model.Usuario" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    AdministradorDelParque adm = (AdministradorDelParque) request.getAttribute("admin");

    if(usuario == null || adm==null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

%>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <title>Parque Industrial</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/perfilAdministrador.css">

</head>

<body>

    <header class="header">

        <div class="header__overlay"></div>

        <div class="header__item--container">

            <h1>

                Parque Industrial

            </h1>

            <p>

                Información completa de la solicitud de radicación.

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

    <div class="perfil__container">

        <!-- DATOS DE USUARIO -->
        <section class="perfil__card">

            <div class="perfil__titulo">
                <h2>Datos de Usuario</h2>
            </div>

            <div class="perfil__form">

                <div class="input__group">
                    <label>Nombre de usuario</label>

                    <input type="text"
                           value="<%= usuario.UserName() %>"
                           readonly>
                </div>

                <div class="input__group">
                    <label>Correo electrónico</label>

                    <input type="email"
                           value="<%= usuario.gmail() %>"
                           readonly>
                </div>

                <div class="input__group">
                    <label>Contraseña</label>

                    <input type="password"
                           value="<%= usuario.contrasena() %>"
                           readonly>
                </div>

                <a href="${pageContext.request.contextPath}/editarUsuario"
                   class="btn__actualizar">

                    Actualizar datos de usuario

                </a>

            </div>

        </section>

        <!-- DATOS PERSONALES -->
        <section class="perfil__card">

            <div class="perfil__titulo">
                <h2>Datos Personales</h2>
            </div>

            <div class="perfil__form">

                <div class="input__group">
                    <label>Nombre completo</label>

                    <input type="text"
                           value="<%= adm.nombre() %>"
                           readonly>
                </div>

                <div class="input__group">
                    <label>DNI</label>

                    <input type="text"
                           value="<%= adm.dni() %>"
                           readonly>
                </div>

                <div class="input__group">
                    <label>Rol</label>

                    <input type="text"
                           value="<%= usuario.nombreRol() %>"
                           readonly>
                </div>

                <a href="${pageContext.request.contextPath}/editarDatosPersonales"
                   class="btn__actualizar">

                    Actualizar datos personales

                </a>

            </div>

        </section>

    </div>

</main>

    </main>
    <footer>
            <div class="div__footer--container">
                <p>Parque Industrial</p>
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo qui laborum, hic corporis odit porro, adipisci minus harum aut maiores odio. Totam, autem. Obcaecati, molestias ullam voluptas harum vel corporis.
            </div>
        </footer>
</body>
</html>