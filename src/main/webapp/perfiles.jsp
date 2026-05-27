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

    <section class="perfil__section">

        <div class="perfil__container">

            <h2>
                Elegir Perfil
            </h2>

            <form action="${pageContext.request.contextPath}/seleccionarPerfil" method="post" class="perfil__form">

                <label for="usuario">

                    Usuarios del Sistema

                </label>

                <select name="username" id="usuario" required>

                    <option value="">
                        -- Seleccione un usuario --
                    </option>

                    <%
                        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");

                        if (usuarios != null) {
                            for (Usuario usuario : usuarios) {
                    %>

                    <option value="<%= usuario.UserName() %>">

                        <%= usuario.UserName() %>
                        -
                        <%= usuario.rol() %>

                    </option>

                    <%
                            }
                        }
                    %>

                </select>

                <button type="submit">

                    Ingresar

                </button>

            </form>

        </div>

    </section>

</main>

<footer>

    <div class="div__footer--container">

        <p>Parque Industrial</p>

        Sistema de gestión del Parque Industrial de Viedma.

    </div>

</footer>

</body>

</html>