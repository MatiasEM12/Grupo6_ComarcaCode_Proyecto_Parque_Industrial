<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>


<%
    Usuario usuario =(Usuario) session.getAttribute("usuarioLogueado");

    // SI NO HAY SESIÓN
    if(usuario == null){

        response.sendRedirect(request.getContextPath()+ "/perfiles"
        );

        return;
    }

    // SI NO ES REPRESENTANTE
    if(!usuario.nombreRol().equals("organismo_publico")){

        response.sendRedirect( request.getContextPath()+ "/perfiles"
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

    <title>ParqueIndustrialViedma</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/mainRepresentante.css">

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
                <a href="${pageContext.request.contextPath}/perfil"
                   class="nav__link">
                    Perfil
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/proyectosEnEjecucion"
                   class="nav__link">
                    Proyectos
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/evaluacionesTecnicas"
                   class="nav__link">
                    Evaluaciones Realizadas
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

        <a href="${pageContext.request.contextPath}/proyectosEnEjecucion"
           class="card">
            <div class="card__content">

                <h2>Proyectos en ejecución</h2>

                <p>
                    Consultar proyectos productivos y registrar
                    evaluaciones técnicas.
                </p>

            </div>
        </a>

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