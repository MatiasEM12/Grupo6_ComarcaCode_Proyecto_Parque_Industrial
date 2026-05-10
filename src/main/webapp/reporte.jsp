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
    <title>Reportes</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/reporte.css">
</head>

<body>

<header class="header">
    <h1>Reportes del Parque Industrial</h1>
    <p>Generar reportes generales, auditorías o informes de documentación.</p>
</header>

<main class="main">

    <section class="form-container">

        <h2>Nuevo Reporte</h2>

        <form action="${pageContext.request.contextPath}/guardarReporte"
              method="post"
              class="form">

            <label>Tipo de reporte</label>
            <select name="tipoReporte" required>
                <option value="">Seleccione una opción</option>
                <option value="AUDITORIA">Auditoría</option>
                <option value="DOCUMENTACION">Documentación</option>
                <option value="GENERAL">General</option>
            </select>

            <label>Descripción</label>
            <textarea name="descripcion"
                      placeholder="Ingrese la descripción del reporte..."
                      required></textarea>

            <button type="submit" class="btn">
                Generar Reporte
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