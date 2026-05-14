<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ParqueIndustrialViedma</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/CSS/solicitud.css">
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
                <a href="" class="nav__link">Perfil</a>
            </li>

            <li class="nav__item">
                <a href="${pageContext.request.contextPath}/misProyectos" class="nav__link">Mis Proyectos</a>
            </li>

            <li class="nav__item">
                <a href="" class="nav__link">Enviar Solicitud</a>
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

    <div class="form__container">

        <h2>Solicitud de Radicación</h2>

        <form action="${pageContext.request.contextPath}/subirSolicitud"
              method="post"
              enctype="multipart/form-data">

            <div class="form__group">

                <label>Nombre del Proyecto*</label>

                <input type="text"
                       name="nombreProyecto"
                       placeholder="Ingrese el nombre del proyecto">

            </div>
            <!-- Proyecto -->

            <div class="form__group">

                <p>
                    En caso de ser una empresa existente indicar si el proyecto tiene por objeto*
                </p>

                <label>
                    <input type="radio" name="objeto" value="traslado">
                    Traslado total o parcial de la empresa
                </label>

                <label>
                    <input type="radio" name="objeto" value="nuevosProductos">
                    Elaborar nuevos productos
                </label>

                <label>
                    <input type="radio" name="objeto" value="incrementarProduccion">
                    Incrementar la producción total
                </label>

            </div>

            <div class="form__group">
                <label>Descripción del Servicio o Bien OFRECIDO*</label>
                <textarea name="descripcionServicio"></textarea>
            </div>

            <div class="form__group">

                <p>Emplazamiento Actual</p>

                <label>
                    <input type="radio" name="emplazamiento" value="propio">
                    Propio
                </label>

                <label>
                    <input type="radio" name="emplazamiento" value="alquilado">
                    Alquilado
                </label>

            </div>

            <div class="form__group">

                <p>Personal Ocupado</p>

                <label>
                    <input type="radio" name="personal" value="jerarquico">
                    Jerárquico
                </label>

                <label>
                    <input type="radio" name="personal" value="produccion">
                    Producción
                </label>

                <label>
                    <input type="radio" name="personal" value="administrativo">
                    Administrativo
                </label>

            </div>

            <div class="form__group">

                <p>Tiempo de Radicación</p>

                <label>
                    <input type="radio" name="tiempo" value="6">
                    6 Meses
                </label>

                <label>
                    <input type="radio" name="tiempo" value="12">
                    12 Meses
                </label>

                <label>
                    <input type="radio" name="tiempo" value="24">
                    24 Meses
                </label>

                <label>
                    <input type="radio" name="tiempo" value="36">
                    36 o Más
                </label>

            </div>

            <div class="form__group">

                <label>Necesidad de M2*</label>

                <select name="m2">

                   <option value="1200">1200 aprox</option>
                   <option value="1800">1800 aprox</option>
                   <option value="2500">2500 aprox</option>
                   <option value="3300">3300 aprox</option>
                   <option value="5000">5000 aprox</option>
                   <option value="6000">6000 aprox</option>

                </select>

            </div>

            <div class="form__group">
                <label>Que sup. cubierta estima por Áreas de Trabajo (M2)*</label>
                <input type="text" name="areaTrabajo">
            </div>

            <div class="form__group">
                <label>Que sup. cubierta estima por Área de Depósito (M2)*</label>
                <input type="text" name="areaDeposito">
            </div>

            <div class="form__group">
                <label>Sup. para estacionamiento para vehículos propios y empleados (M2)</label>
                <input type="text" name="estacionamiento">
            </div>

            <div class="form__group">

                <p>Tiene confeccionado planos*</p>

                <label>
                    <input type="radio" name="planos" value="si">
                    SI
                </label>

                <label>
                    <input type="radio" name="planos" value="no">
                    NO
                </label>

            </div>

            <div class="form__group">
                <label>Personal a Ocupar*</label>
                <input type="text" name="personalOcupar">
            </div>

            <div class="form__group">
                <label>Materias Primas: Indicar tipo y calidad de origen</label>
                <textarea name="materiasPrimas"></textarea>
            </div>

            <div class="form__group">
                <label>Destino de la Producción</label>
                <textarea name="destinoProduccion"></textarea>
            </div>

            <div class="form__group">

                <p>Energía Eléctrica - Tensión de Alimentación</p>

                <label>
                    <input type="radio" name="tension" value="media">
                    Media
                </label>

                <label>
                    <input type="radio" name="tension" value="baja">
                    Baja
                </label>

            </div>

            <div class="form__group">
                <label>Energía Eléctrica - Potencia instalada simultánea (kw)</label>
                <input type="text" name="potencia">
            </div>

            <div class="form__group">
                <label>Agua (Lts./Mes)</label>
                <input type="text" name="agua">
            </div>

            <div class="form__group">

                <p>Gas</p>

                <label>
                    <input type="radio" name="gas" value="si">
                    Sí
                </label>

                <label>
                    <input type="radio" name="gas" value="no">
                    No
                </label>

            </div>

            <div class="form__group">
                <label>Residuos / Efluentes</label>
                <textarea name="residuos"></textarea>
            </div>

            <div class="form__group">

                <p>¿Prevée hacer tratamiento en la planta?</p>

                <label>
                    <input type="radio" name="tratamiento" value="si">
                    SI
                </label>

                <label>
                    <input type="radio" name="tratamiento" value="no">
                    NO
                </label>

            </div>

            <div class="form__group">

                <p>Necesidad de Balanza Pública</p>

                <label>
                    <input type="radio" name="balanza" value="si">
                    SI
                </label>

                <label>
                    <input type="radio" name="balanza" value="no">
                    NO
                </label>

            </div>

            <div class="form__group">

                <p>Necesidad de comedor comunitario</p>

                <label>
                    <input type="radio" name="comedor" value="si">
                    SI
                </label>

                <label>
                    <input type="radio" name="comedor" value="no">
                    NO
                </label>

            </div>

            <div class="form__group">

                <p>Necesidad de salón de usos múltiples o coworking</p>

                <label>
                    <input type="radio" name="coworking" value="si">
                    SI
                </label>

                <label>
                    <input type="radio" name="coworking" value="no">
                    NO
                </label>

            </div>

            <!-- SUBIDA DE ARCHIVO PDF -->

            <div class="form__group">

                <h3>Documentación Adjunta</h3>

                <label>Descripción del archivo PDF</label>

                <textarea
                        name="descripcionArchivo"
                        placeholder="Describa el contenido del archivo PDF">
                </textarea>

            </div>

            <div class="form__group">

                <label>Subir archivo PDF</label>

                <input type="file"
                       name="archivoPDF"
                       accept=".pdf">

            </div>

            <button type="submit" class="btn">
                Guardar
            </button>

        </form>

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