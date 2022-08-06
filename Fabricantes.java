import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Fabricantes {
    public static void ingresarFabricantes() throws SQLException {
        String sqlFab = "insert into fabricantes values (?)";
        PreparedStatement senFab = Principal.conectar().prepareStatement(sqlFab);

        System.out.print("Nombre Fabricante: ");
        String nombre = Principal.sc.nextLine();
        senFab.setString(1, nombre);

        int filasIns = senFab.executeUpdate();
        if (filasIns > 0) {
            System.out.println(" Fabricante ingresado con éxito ");
        }
    }
}
