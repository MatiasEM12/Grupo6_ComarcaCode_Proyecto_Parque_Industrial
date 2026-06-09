<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Usuario" %>

<!DOCTYPE html>

<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Seleccionar Perfil</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/perfiles.css">

</head>

<body>

<header class="header">

    <div class="header__overlay"></div>

    <div class="header__item--container">

        <h1>
            PARQUE INDUSTRIAL VIEDMA
        </h1>

        <p>
            Seleccione el perfil con el que desea ingresar al sistema.
        </p>

    </div>

</header>

<nav class="nav">

    <div class="nav__ul--container">

        <ul class="nav__ul">

            <li class="nav__item">

                <a href="${pageContext.request.contextPath}/index.jsp"class="nav__link">
                    Inicio
                </a>

            </li>

        </ul>

    </div>

    <div class="nav__right">

        <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" class="nav__logo">

    </div>

</nav>

<main>

    <form action="${pageContext.request.contextPath}/login" method="post">

        <h2>Iniciar Sesión</h2>

        <label>Usuario</label>
        <input type="text" name="username" required>

        <label>Contraseña</label>
        <input type="password" name="password" required>

        <button type="submit">Ingresar</button>

    </form>

</main>

<footer>

    <div class="div__footer--container">

        <p>Parque Industrial</p>

          Comprometidos con el crecimiento productivo, la innovación y el desarrollo sostenible de la región. © 2026 Todos los derechos reservados.

    </div>

</footer>

</body>

</html>