<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Lote" %>

<!DOCTYPE html>
<html lang="es">

    <head>

        <meta charset="UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>ParqueIndustrialViedma</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/listadoLotes.css">

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
                Administración y visualización de lotes
                del parque industrial.
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

    <div class="crear__lote--mini">

        <form action="${pageContext.request.contextPath}/crearLote"
              method="POST"
              class="crear__lote--formMini">

            <input type="number"
                   name="latitud"
                   placeholder="Latitud"
                   required>

            <input type="number"
                   name="longitud"
                   placeholder="Longitud"
                   required>

            <input type="number"
                   name="altitud"
                   placeholder="Altitud"
                   required>

            <div class="form__group">

                <label>Superficie*</label>

                <select name="superficie">

                    <option value="1200">1200 m² aprox</option>
                    <option value="1800">1800 m² aprox</option>
                    <option value="2500">2500 m² aprox</option>
                    <option value="3300">3300 m² aprox</option>
                    <option value="5000">5000 m² aprox</option>
                    <option value="6000">6000 m² aprox</option>

                </select>

            </div>
            <input type="text"
                   name="infraestructura"
                   placeholder="Infraestructura"
                   required>

            <button type="submit">

                Crear Lote

            </button>

        </form>

    </div>

        <div class="lotes__container">

            <%

                List<Lote> lotes = (List<Lote>) request.getAttribute("lotes");

                if (lotes != null && !lotes.isEmpty()) {

                    for (Lote lote : lotes) {

                        String claseEstado = "";

                        if (lote.estado().toString().equals("DISPONIBLE")) {

                            claseEstado = "estado__disponible";
                        }

                        else if (lote.estado().toString().equals("OCUPADO")) {

                            claseEstado = "estado__ocupado";
                        }

            %>

            <a href="${pageContext.request.contextPath}/detalleLote?id=<%= lote.id() %>" class="lote__card">

                <div class="lote__content">

                    <h2>

                        Lote #<%= lote.id() %>

                    </h2>

                    <p>

                        Superficie:
                        <%= lote.superficie() %> m²

                    </p>

                    <p>

                        Infraestructura:
                        <%= lote.infraestructura() %>

                    </p>

                    <span class="lote__state <%= claseEstado %>">

                        <%= lote.estado() %>

                    </span>

                </div>

            </a>

            <%

                    }

                } else {

            %>

            <div class="sin__Lotes">

                <h2>

                    No hay lotes cargados

                </h2>

                <p>

                    Todavía no se han cargado lotes.

                </p>

            </div>

            <%

                }

            %>

        </div>

    </main>

    <footer>

        <div class="div__footer--container">

            <p>Parque Industrial</p>

            Sistema de administración del Parque Industrial Viedma.

        </div>

    </footer>

</body>

</html>