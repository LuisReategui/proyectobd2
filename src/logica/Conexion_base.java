package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion_base {
     public static Connection coneccion;
     public static Statement stm;
    public Conexion_base() throws InstantiationException, IllegalAccessException{
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            coneccion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","EVENTOS","PROBD2");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR AL CONECTAR");   
        }
    }    
}
