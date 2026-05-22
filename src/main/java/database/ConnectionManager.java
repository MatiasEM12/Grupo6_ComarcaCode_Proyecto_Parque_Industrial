package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL_DB =
            "jdbc:mysql://localhost:3306/parque_industrial_2026?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL_DB, USER, PASS);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el driver MySQL", e);

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo conectar a la base de datos", e);
        }
    }
}
