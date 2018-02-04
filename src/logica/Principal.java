package logica;
import java.sql.SQLException;
import vista.MPrincipal;
public class Principal {

    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException {
        Conexion_base objCon = new Conexion_base();
        MPrincipal principal = new MPrincipal();
    }

}
