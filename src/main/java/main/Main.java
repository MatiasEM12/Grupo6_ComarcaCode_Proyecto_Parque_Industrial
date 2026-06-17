package main;

import database.persistencia.ParqueIndustrial;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Main implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {

            ParqueIndustrial sistema = new ParqueIndustrial();

            sce.getServletContext().setAttribute(
                    "sistema",
                    sistema
            );

            System.out.println("Sistema iniciado correctamente");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
