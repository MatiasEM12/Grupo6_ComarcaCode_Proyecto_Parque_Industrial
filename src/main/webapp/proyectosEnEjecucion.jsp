<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.ProyectoProductivo" %>
<%@ page import="java.util.List" %>

<%
    Usuario usuario =
            (Usuario) session.getAttribute("usuarioLogueado");

    if(usuario == null){
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    if(!usuario.rol().equals("organismo_publico")
            && !usuario.rol().equals("administrador")){

        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    List<ProyectoProductivo> proyectos =
            (List<ProyectoProductivo>) request.getAttribute("proyectos");
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

        <h1>PROYECTOS EN EJECUCIÓN</h1>

        <p>
            Consulte el estado actual de los proyectos productivos
            aprobados dentro del Parque Industrial de Viedma.
        </p>

    </div>

</header>

<main class="main">

    <section class="cards-container">

        <%
            if(proyectos == null || proyectos.isEmpty()){
        %>

            <article class="card">
                <div class="card__header">
                    <h2>No hay proyectos aprobados</h2>
                </div>

                <div class="card__body">
                    <p>
                        Todavía no existen proyectos productivos en ejecución.
                        Primero el administrador debe aprobar una solicitud de radicación.
                    </p>
                </div>
            </article>

        <%
            } else {
                for(ProyectoProductivo proyecto : proyectos){
        %>

            <article class="card">

                <div class="card__header">

                    <h2>
                        <%= proyecto.getNombre() %>
                    </h2>

                    <span class="estado estado--activo">
                        En ejecución
                    </span>

                </div>

                <div class="card__body">

                    <p>
                        <%= proyecto.getDescripcion() %>
                    </p>

                    <div class="info">

                        <p>
                            <strong>Superficie:</strong>
                            <%= proyecto.getSuperficie() %> m²
                        </p>

                        <p>
                            <strong>Empleabilidad:</strong>
                            <%= proyecto.getEmpleabilidad() %> empleados
                        </p>

                        <p>
                            <strong>Materia Prima:</strong>
                            <%= proyecto.getMateriaPrima() %>
                        </p>

                    </div>

                </div>

            </article>

        <%
                }
            }
        %>

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