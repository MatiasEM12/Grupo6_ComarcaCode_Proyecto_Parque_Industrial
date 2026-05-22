package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static String URL_DB =
            "jdbc:mysql://localhost:3306/parque_industrial_2026?useSSL=false&serverTimezone=UTC";

    protected static String user = "root";
    protected static String pass = "";

    protected static Connection conn = null;

    public static void connect() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    URL_DB,
                    user,
                    pass
            );

        } catch (Exception e) {

            System.out.println(
                    "Error de conexión: "
                            + e.getMessage()
            );
        }
    }

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL_DB,
                user,
                pass
        );
    }

    public static void disconnect() {

        if (conn != null) {

            try {

                conn.close();
                conn = null;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void reconnect() {
        disconnect();
        connect();
    }
}
