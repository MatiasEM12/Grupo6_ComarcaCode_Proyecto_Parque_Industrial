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
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ex doloremque, fuga sit porro alias praesentium iste tenetur nesciunt facilis suscipit tempora fugit distinctio exercitationem perferendis at vitae provident molestias modi.
            </P>
        </div>
    </header>
    <nav class="nav">
        <div class="nav__ul--container">
            <ul class="nav__ul">
                <li class="nav__item"><a href="#inicio" class="nav__link">Inicio</a></li>
                <li class="nav__item"><a href="#quienes-somos" class="nav__link">Quienes Somos</a></li>
                <li class="nav__item"><a href="#contacto" class="nav__link">Contacto</a></li>
                <li class="nav__item"><a href="" class="nav__link Link--login">Log In</a></li>
            </ul>
        </div>
        <div class="nav__logo--container">
            <img src="logo.png" alt="" class="nav__logo">
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
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Vel at delectus eaque nobis,
                    molestias distinctio eius, facere repellendus ut, accusantium quae atque! Velit autem nulla,
                    aspernatur reprehenderit dolores animi non?
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
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Explicabo qui laborum, hic corporis odit porro, adipisci minus harum aut maiores odio. Totam, autem. Obcaecati, molestias ullam voluptas harum vel corporis.
        </div>
    </footer>
</body>
</html>