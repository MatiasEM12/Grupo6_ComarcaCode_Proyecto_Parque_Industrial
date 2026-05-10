<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }

    if (!usuario.rol().equals("organismo_publico")) {
        response.sendRedirect(request.getContextPath() + "/perfiles");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Evaluación Técnica</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/reporte.css">
</head>

<body>

<header class="header">
    <h1>Evaluación Técnica</h1>
    <p>Registrar evaluaciones técnicas sobre proyectos, documentación o funcionamiento del parque.</p>
</header>

<main class="main">

    <section class="form-container">

        <h2>Nueva Evaluación Técnica</h2>

        <form action="${pageContext.request.contextPath}/guardarEvaluacionTecnica"
              method="post"
              class="form">

            <label>Descripción</label>
            <textarea name="descripcion"
                      placeholder="Describa la evaluación técnica..."
                      required></textarea>

            <label>Resultado</label>
            <select name="resultado" required>
                <option value="">Seleccione una opción</option>
                <option value="APROBADA">Aprobada</option>
                <option value="OBSERVADA">Observada</option>
                <option value="RECHAZADA">Rechazada</option>
            </select>

            <label>Observaciones</label>
            <textarea name="observaciones"
                      placeholder="Ingrese observaciones si corresponde..."></textarea>

            <button type="submit" class="btn">
                Guardar Evaluación
            </button>

        </form>

        <a href="${pageContext.request.contextPath}/mainOrganismoPublico.jsp"
           class="volver">
            Volver al menú
        </a>

    </section>

</main>

</body>
</html>