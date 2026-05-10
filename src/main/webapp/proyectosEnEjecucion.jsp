<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>

<%
    Usuario usuario =
            (Usuario) session.getAttribute("usuarioLogueado");

    // VALIDAR SESIÓN
    if(usuario == null){

        response.sendRedirect(
                request.getContextPath()
                + "/perfiles"
        );

        return;
    }

    // VALIDAR ROL
    if(!usuario.rol().equals("organismo_publico")){

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

    <title>Proyectos en Ejecución</title>

    <link rel="stylesheet"
              href="${pageContext.request.contextPath}/CSS/mainOrganismoPublico.css">

</head>

<body>

<header class="header">

    <div class="header__overlay"></div>

    <div class="header__container">

        <h1>
            PROYECTOS EN EJECUCIÓN
        </h1>

        <p>
            Consulte el estado actual de los proyectos productivos
            desarrollados dentro del Parque Industrial de Viedma.
        </p>

    </div>

</header>

<main class="main">

    <section class="cards-container">

        <!-- TARJETA PROYECTO -->

        <article class="card">

            <div class="card__header">

                <h2>
                    Fábrica Metalúrgica Patagónica
                </h2>

                <span class="estado estado--activo">
                    En ejecución
                </span>

            </div>

            <div class="card__body">

                <p>
                    Proyecto orientado a la fabricación de estructuras
                    metálicas industriales para la región patagónica.
                </p>

                <div class="info">

                    <p>
                        <strong>Superficie:</strong>
                        2500 m²
                    </p>

                    <p>
                        <strong>Empleabilidad:</strong>
                        35 empleados
                    </p>

                    <p>
                        <strong>Materia Prima:</strong>
                        Acero y aluminio
                    </p>

                </div>

            </div>

        </article>

        <!-- TARJETA PROYECTO -->

        <article class="card">

            <div class="card__header">

                <h2>
                    Planta de Alimentos Regionales
                </h2>

                <span class="estado estado--revision">
                    En revisión
                </span>

            </div>

            <div class="card__body">

                <p>
                    Producción y distribución de alimentos regionales
                    destinados al mercado provincial y nacional.
                </p>

                <div class="info">

                    <p>
                        <strong>Superficie:</strong>
                        1800 m²
                    </p>

                    <p>
                        <strong>Empleabilidad:</strong>
                        20 empleados
                    </p>

                    <p>
                        <strong>Materia Prima:</strong>
                        Productos agroalimentarios
                    </p>

                </div>

            </div>

        </article>

    </section>

    <div class="volver-container">

        <a href="${pageContext.request.contextPath}/mainOrganismoPublico.jsp"
           class="btn-volver">

            Volver al menú

        </a>

    </div>

</main>

</body>

</html>