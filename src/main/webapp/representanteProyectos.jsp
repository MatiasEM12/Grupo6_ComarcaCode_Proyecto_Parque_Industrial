<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ParqueIndustrialViedma</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/representanteProyectos.css">
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
                <a href="${pageContext.request.contextPath}/mainRepresentante.jsp"
                   class="nav__link">
                    Inicio
                </a>
            </li>

            <li class="nav__item">
                <a href="" class="nav__link">
                    Perfil
                </a>
            </li>

            <li class="nav__item">
                <a href="" class="nav__link">
                    Mis Proyectos
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/solicitudRadicacion.jsp"
                   class="nav__link">
                    Enviar Solicitud
                </a>
            </li>

        </ul>

    </div>

    <div class="nav__right">

        <img src="${pageContext.request.contextPath}/img/logo.png"
             alt="Logo"
             class="nav__logo">

        <a href=""
           class="nav__link Link--Cerrar">
            Cerrar Sesión
        </a>

    </div>

</nav>

<main>

    <div class="projects__container">

        <!-- PROYECTO 1 -->

        <a href="${pageContext.request.contextPath}/representanteProyecto.jsp"
           class="project__card">

            <div class="project__content">

                <h2>Proyecto Metalúrgico</h2>

                <p>
                    Producción y fabricación de estructuras metálicas.
                </p>

                <span class="project__state estado__pendiente">
                    Pendiente
                </span>

            </div>

        </a>

        <!-- PROYECTO 2 -->

        <a href="${pageContext.request.contextPath}/proyecto2.jsp"
           class="project__card">

            <div class="project__content">

                <h2>Proyecto Alimenticio</h2>

                <p>
                    Elaboración y distribución de productos regionales.
                </p>

                <span class="project__state estado__aprobado">
                    Aprobado
                </span>

            </div>

        </a>

        <!-- PROYECTO 3 -->

        <a href="${pageContext.request.contextPath}/proyecto3.jsp"
           class="project__card">

            <div class="project__content">

                <h2>Proyecto Logístico</h2>

                <p>
                    Centro de distribución y almacenamiento.
                </p>

                <span class="project__state estado__revision">
                    En revisión
                </span>

            </div>

        </a>

    </div>

</main>

<footer>

    <div class="div__footer--container">

        <p>Parque Industrial</p>

        Lorem ipsum dolor sit amet consectetur adipisicing elit.
        Explicabo qui laborum, hic corporis odit porro, adipisci
        minus harum aut maiores odio. Totam, autem. Obcaecati,
        molestias ullam voluptas harum vel corporis.

    </div>

</footer>

</body>
</html>