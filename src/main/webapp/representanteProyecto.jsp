<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ParqueIndustrialViedma</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/representanteProyecto.css">
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
                <a href="${pageContext.request.contextPath}/mainRepresentante.jsp"  class="nav__link">
                    Inicio
                </a>
            </li>

            <li class="nav__item">
                <a href="" class="nav__link">
                    Perfil
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/representanteProyectos.jsp" class="nav__link">
                    Mis Proyectos
                </a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/solicitudRadicacion.jsp" class="nav__link">
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

    <div class="project__container">

        <h2>Proyecto Productivo</h2>

        <!-- FORMULARIO DEL PROYECTO -->

        <form class="project__form" action="${pageContext.request.contextPath}/actualizarProyecto" method="post">

            <!-- DATOS DEL PROYECTO -->

            <div class="form__group">

                <label>Nombre del Proyecto</label>

                <input type="text" name="nombreProyecto" value="Proyecto Metalúrgico">

            </div>

            <div class="form__group">

                <label>Descripción</label>
                <textarea name="descripcion">
                    Producción de estructuras metálicas industriales.
                </textarea>

            </div>

            <div class="form__group">

                <label>Superficie</label>

                <input type="text"
                       name="superficie"
                       value="2500">

            </div>

            <div class="form__group">

                <label>Necesidades</label>

                <input type="text" name="necesidades" value="Gas Industrial - Energía Trifásica">

            </div>

            <div class="form__group">

                <label>Empleabilidad</label>

                <input type="text" name="empleabilidad" value="35">

            </div>

            <div class="form__group">

                <label>Materia Prima</label>

                <input type="text" name="materiaPrima"  value="Acero y aluminio">

            </div>

            <div class="form__group">

                <label>Estado</label>

                <select name="estado">

                    <option>Pendiente</option>
                    <option>En Revisión</option>
                    <option>Aprobado</option>

                </select>

            </div>

            <!-- BOTONES -->

            <div class="buttons__container">

                <button type="submit" class="btn">

                    Actualizar

                </button>

                <a href="${pageContext.request.contextPath}/detalleLote.jsp"
                   class="btn btn__secondary">

                    Ver Lote

                </a>

            </div>

        </form>

        <!-- AVANCES -->

        <div class="avance__container">

            <h3>Subir avance del proyecto productivo</h3>

            <form class="avance__form"
                  action="${pageContext.request.contextPath}/subirAvance"
                  method="post"
                  enctype="multipart/form-data">

                <div class="form__group">

                    <label>Descripción del avance</label>

                    <textarea name="descripcionAvance"></textarea>

                </div>

                <div class="form__group">

                    <label>Subir PDF</label>

                    <input type="file"
                           name="archivoPDF"
                           accept=".pdf">

                </div>

                <button type="submit"
                        class="btn">

                    Subir Avance

                </button>

            </form>

        </div>

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