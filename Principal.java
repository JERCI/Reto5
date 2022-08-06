//Juan Estevan Rojas Carvajal | Reto 5 | Ciclo 2 | Grupo 17

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Principal {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
       
    }

    public static void menu() {
        try {
            System.out.println("************************\n" +
                    "***   CRUD  MySQL   ***\n" +
                    "1. Crear Tablas.\n" +
                    "2. Ingresar datos Proveedor.\n" +
                    "3. Ingresar datos Clientes.\n" +
                    "4. Ingresar datos Fabricantes.\n" +
                    "5. Ingresar datos Bicicletas.\n" +
                    "6. Ingresar datos Motocicletas.\n" +
                    "7. Ingresar datos Compras.\n" +
                    "8. Modificar Año de Bicicleta.\n" +
                    "9. Modificar Telefono de Cliente.\n" +
                    "10. Borrar intención de compra.\n" +
                    "11. Listado de Fabricantes.\n" +
                    "12. Mostrar fabricantes de bicicletas estrenadas desde 2019.\n" +
                    "13. Mostrar fabricantes de motocicletas con motor Auteco.\n" +
                    "14. Mostrar fabricante con intencion de Compra del cliente lucky.\n" +
                    "15. Mostrar Clientes que quieren comprar bicicleta Yeti.\n" +
                    "16. Cantidad bicicletas fabricadas desde \"x\" Año.\n" +
                    
                    "- Otro número para SALIR -\n" 
                    
            );
            System.out.print(">>> ");
            String input = sc.nextLine();
            byte opcion = Byte.parseByte(input);

            switch (opcion) {
                case 1:
                    Consultas.crearTablas();
                    break;
                case 2:
                    Proveedor.ingresarProveedor();
                    break;
                case 3:
                    Clientes.ingresarClientes();
                    break;
                case 4:
                    Fabricantes.ingresarFabricantes();
                    break;
                case 5:
                    Bicicletas.ingresarBicicletas();
                    break;
                case 6:
                    Motocicletas.ingresarMotocicletas();
                    break;
                case 7:
                    Compras.ingresarCompras();
                    break;
                case 8:
                    Consultas.modificarAño();
                    break;
                case 9:
                    Consultas.modificarTelCliente();
                    break;
                case 10:
                    Consultas.borrarCompra();
                    break;
                case 11:
                    Consultas.Fabricantes();
                    break;
                case 12:
                    Consultas.InfoBici();
                    break;
                case 13:
                    Consultas.FabricanteMoto();
                    break;
                case 14:
                    Consultas.CompBiciMoto();
                    break;
                case 15:
                    Consultas.CompBiCliente();
                    break;
                case 16:
                    Consultas.BiciFabricante();
                    break;
                default:
                    System.out.println("Nos vemos...Ten buen día");
            }
        } catch (Exception e) {
            System.out.println("Nos vemos...Ten buen día");
        }
    }

    public static Connection conectar() {
        String URL = "jdbc:mysql://127.0.0.1:3306/reto_4";
        String usuario = "root";
        String contraseña = "Isaac2018*";
        Connection con = null;
        try{
           con = DriverManager.getConnection(URL,usuario,contraseña);
           if (con != null){
               System.out.println("* Estás en linea *");
               System.out.println("-------------");
           }
        }catch (SQLException error){
            System.out.println("Error: "
                    + error.getErrorCode() + " " + error.getMessage());
        }
        return con;
    }
}