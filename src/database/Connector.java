/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Clase que realiza la conexion a la base de datos.
 *
 * @author emanuel
 */
public class Connector {

    private static final Logger logger = Logger.getLogger(Connector.class.getName());

    // nombre de la base de datos.
    private static final String DATABASE = "users.db";

    /**
     * Crea una base de datos.
     */
    public static void createDatabase() {

        String url = "jdbc:sqlite:" + DATABASE;

        String statement = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id integer PRIMARY KEY, \n"
                + " username text NOT NULL, \n"
                + " password text NOT NULL);";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                stmt.execute(statement);
                logger.info("La base de datos fue creada correctamente");
            }

        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }

    /**
     * Retorna una conexion a la base de datos.
     *
     * @return una conexion establecida.
     */
    public static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + DATABASE;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            logger.info("Conexion establecida con exito");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

}
