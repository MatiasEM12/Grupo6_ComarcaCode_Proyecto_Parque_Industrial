package main;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Main implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Sistema sistema = new Sistema();

        sce.getServletContext().setAttribute(
                "sistema",
                sistema
        );
    }
}
