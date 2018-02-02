package principal;
import java.sql.SQLException;
import proyecto_interfaz.MenuPrincipal;
public class Principal {

    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException {
        Conexion_base objCon = new Conexion_base();
        MenuPrincipal principal = new MenuPrincipal();
        principal.show();
    }

}
