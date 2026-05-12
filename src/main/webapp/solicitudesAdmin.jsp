<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>

<%
    Usuario usuario =
            (Usuario) session.getAttribute(
                    "usuarioLogueado"
            );

    // SI NO HAY SESIÓN
    if(usuario == null){

        response.sendRedirect(
                request.getContextPath()
                        + "/perfiles"
        );

        return;
    }

    // SI NO ES ADMINISTRADOR
    if(!usuario.rol().equals("administrador")){

        response.sendRedirect(
                request.getContextPath()
                        + "/perfiles"
        );

        return;
    }
%>

<!DOCTYPE html>

<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Solicitudes de Radicación</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/mainRepresentante.css">

</head>

<body>

<header class="header">

    <div class="header__overlay"></div>

    <div class="header__item--container">

        <h1>
            SOLICITUDES DE RADICACIÓN
        </h1>

        <p>
            Administración y revisión de solicitudes enviadas
            por las empresas del parque industrial.
        </p>

    </div>

</header>

<nav class="nav">

    <div class="nav__ul--container">

        <ul class="nav__ul">

            <li class="nav__item">

                <a href="${pageContext.request.contextPath}/mainAdm.jsp"
                   class="nav__link">

                    Inicio

                </a>

            </li>

            <li class="nav__item">

                <a href="${pageContext.request.contextPath}/usuarios.jsp"
                   class="nav__link">

                    Usuarios

                </a>

            </li>

            <li class="nav__item">

                <a href="${pageContext.request.contextPath}/proyectosEnEjecucion.jsp"
                   class="nav__link">

                    Proyectos

                </a>

            </li>

        </ul>

    </div>

    <div class="nav__right">

        <img
                src="${pageContext.request.contextPath}/img/logo.png"
                alt="Logo"
                class="nav__logo"
        >

        <a href="${pageContext.request.contextPath}/logout"
           class="nav__link Link--Cerrar">

            Cerrar Sesión

        </a>

    </div>

</nav>

<main>

    <div class="main__container">

        <!-- SOLICITUD -->

        <div class="card">

            <div class="card__content">

                <h2>Solicitud N° 001</h2>

                <p>
                    Empresa: Patagonia Industrial
                </p>

                <p>
                    Estado: Pendiente
                </p>

                <p>
                    Fecha: 10/05/2026
                </p>

                <div class="acciones__container">

                    <!-- BOTÓN APROBAR -->

                    <form action="${pageContext.request.contextPath}/aprobarSolicitud"
                          method="post">

                        <input type="hidden"
                               name="numeroTramite"
                               value="001">

                        <button type="submit"
                                class="btn__aprobar">

                            Aprobar

                        </button>

                    </form>

                    <!-- BOTÓN OBSERVAR -->

                    <button class="btn__observar"
                            onclick="mostrarFormulario('obs001')">

                        Observar

                    </button>

                </div>

                <!-- FORMULARIO OBSERVACIÓN -->

                <div id="obs001"
                     class="observacion__form">

                    <form action="${pageContext.request.contextPath}/observarSolicitud"
                          method="post">

                        <input type="hidden"
                               name="numeroTramite"
                               value="001">

                        <textarea
                                name="descripcion"
                                placeholder="Escriba la observación..."
                                required></textarea>

                        <button type="submit"
                                class="btn__enviar">

                            Enviar Observación

                        </button>

                    </form>

                </div>

            </div>

        </div>

    </div>

</main>

<footer>

    <div class="div__footer--container">

        <p>Parque Industrial</p>

        Sistema de gestión del Parque Industrial de Viedma.

    </div>

</footer>

<script>

    function mostrarFormulario(id){

        let form = document.getElementById(id);

        if(form.style.display === "block"){
            form.style.display = "none";
        } else {
            form.style.display = "block";
        }
    }

</script>

</body>

</html>