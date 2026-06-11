<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ParqueIndustrialViedma</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/styless.css">
</head>
<body>
    <header class="header">
        <div class="header__overlay">


        </div>
        <div class="header__item--container">

            <h1>PARQUE INDUSTRIAL
                VIEDMA</h1>
            <P>
               <P>
                   Un espacio pensado para impulsar el desarrollo industrial y productivo de la región,
                   brindando infraestructura, servicios y oportunidades para empresas, emprendedores
                   e inversores que buscan crecer en la ciudad de Viedma.
               </P>
            </P>
        </div>
    </header>
    <nav class="nav">
        <div class="nav__ul--container">
            <ul class="nav__ul">
                <li class="nav__item"><a href="#inicio" class="nav__link">Inicio</a></li>
                <li class="nav__item"><a href="#quienes-somos" class="nav__link">Quienes Somos</a></li>
                <li class="nav__item"><a href="#contacto" class="nav__link">Contacto</a></li>
                <li class="nav__item"><a href="${pageContext.request.contextPath}//login" class="nav__link Link--login">Log In</a></li>
                <li class="nav__item"><a href="${pageContext.request.contextPath}//registro" class="nav__link Link--registro" >Registrarse</a></li>
            </ul>
        </div>

    </nav>
    <main>
    <section id="inicio" class="main__section main__section--inicio">
        <div class="main__content">
            <h2>Inicio</h2>
            <p>
                El Parque Industrial de Viedma es un espacio estratégico...
            </p>
        </div>
    </section>

    <section id="quienes-somos" class="main__section main__section--quienes-somos">
        <div class="main__content">
            <h2>Quienes Somos</h2>
            <p>
                Somos una organización dedicada al desarrollo industrial y al crecimiento
                productivo de la región, impulsando oportunidades para empresas y emprendedores.
            </p>
            <div class="contenedor_presentacion">
                <div class="img_presentacion">

                </div>
                <div class="text_presentacion">
                     El Parque Industrial de Viedma promueve la instalación de nuevas empresas,
                       fomentando la generación de empleo y el crecimiento económico local.
                       Nuestro objetivo es ofrecer un entorno organizado, moderno y sustentable
                       para el desarrollo de actividades industriales y comerciales.
                </div>
            </div>
        </div>
    </section>


    <section id="contacto" class="main__section main__section--contacto">
        <div class="main__content">
            <h2>Formulario de Contacto</h2>
            <form action="#" method="post" class="contact-form">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" placeholder="Tu nombre">

                <label for="email">Correo electrónico</label>
                <input type="email" id="email" name="email" placeholder="Tu email">

                <label for="mensaje">Mensaje</label>
                <textarea id="mensaje" name="mensaje" rows="5" placeholder="Escribí tu mensaje"></textarea>

                <button type="submit">Enviar</button>
            </form>
        </div>
    </section>
    </main>
    <footer>
        <div class="div__footer--container">
            <p>Parque Industrial</p>
             Comprometidos con el crecimiento productivo, la innovación y el desarrollo sostenible de la región. © 2026 Todos los derechos reservados.
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