<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Usuario" %>

<!DOCTYPE html>

<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Seleccionar Perfil</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/registro.css">

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

    <form action="registro" method="post">

        <input name="username" placeholder="Usuario" required>
        <input name="password" placeholder="Contraseña" required>
        <input name="gmail" placeholder="Email" required>

        <select name="rol" id="rol" onchange="mostrarCampos()" required>
            <option value="">-- Seleccione rol --</option>
            <option value="administrador">Administrador</option>
            <option value="organismo_publico">Organismo Público</option>
            <option value="representante">Representante Empresa</option>
        </select>

        <div id="extraCampos"></div>

        <button type="submit">Registrar</button>

    </form>
</main>

<footer>

    <div class="div__footer--container">

        <p>Parque Industrial</p>

          Comprometidos con el crecimiento productivo, la innovación y el desarrollo sostenible de la región. © 2026 Todos los derechos reservados.

    </div>

</footer>

    <script>
    function mostrarCampos() {

        let rol = document.getElementById("rol").value;
        let div = document.getElementById("extraCampos");

        div.innerHTML = "";

        if (rol === "administrador") {

            div.innerHTML = `
                <h3>Datos Administrador</h3>
                <input name="dni" placeholder="DNI" required>
                <input name="nombre" placeholder="Nombre" required>
            `;
        }

        else if (rol === "organismo_publico") {

            div.innerHTML = `
                <h3>Datos Organismo</h3>
                <input name="saf" placeholder="SAF" required>
                <input name="nombreOrganismo" placeholder="Nombre" required>
                <select name="tipoOrganismo" required>
                    <option value="">-- Tipo de organismo --</option>
                    <option value="MUNICIPAL">Municipal</option>
                    <option value="PROVINCIAL">Provincial</option>
                    <option value="NACIONAL">Nacional</option>
                </select>
            `;
        }

        else if (rol === "representante") {

            div.innerHTML = `
                <h3>Datos Representante</h3>

                <input name="dni" placeholder="DNI" required>

                <h3>Datos Empresa</h3>

                <input name="cuit" placeholder="CUIT" required>
                <input name="razonSocial" placeholder="Razón Social" required>
                <input name="contacto" placeholder="Contacto" required>
                <input name="contactoRep" placeholder="Contacto Representante" required>
            `;
        }
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