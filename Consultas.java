import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultas {
    public static void crearTablas(){
        try{
            String[] sqls = {
                    "create table proveedor (prov_id int primary key,prov_nombre char(20),prov_direccion char(45),prov_telefono char(20))",
                    "create table clientes (alias char(20) primary key,nombre char(20),apellidos char(20),email varchar(45),celular char(20) ,contraseña int ,f_nacimiento date)",
                    "create table fabricantes (  fabricante char(20) primary key )",
                    "create table bicicletas (  id int primary key,  fabricante char(20) ,  precio int,  año int,  FOREIGN KEY (fabricante) REFERENCES fabricantes (fabricante) )",
                    "create table motocicletas (  id int primary key,  fabricante char(20) ,  precio int,  autonomia int,  id_prov  int,  FOREIGN KEY (fabricante) REFERENCES fabricantes (fabricante),  FOREIGN KEY (id_prov) REFERENCES proveedor (prov_id)  )",
                    "create table compras (  id int primary key,  alias char(20),  fabricante char(20) ,  fecha_hora timestamp,  FOREIGN KEY (alias) REFERENCES clientes (alias),  FOREIGN KEY (fabricante) REFERENCES fabricantes (fabricante)  )"
            };
            for (String i:sqls){
                PreparedStatement sentencia = Principal.conectar().prepareStatement(i);
                sentencia.executeUpdate();
            }
        }catch (Exception e){
            System.out.println("Tabla ya creada");
        }
    }

    public static void modificarAño() throws SQLException {
        String sql = "update bicicletas set año=? where fabricante=?";
        PreparedStatement sentencia = Principal.conectar().prepareStatement(sql);
        System.out.println("Ingrese el fabricante de la bicicleta y el año a modificar. ");
        System.out.print("Fabricante: ");
        String nombre = Principal.sc.nextLine();
        sentencia.setString(2, nombre);

        System.out.print("Año: ");
        int year = Principal.sc.nextInt();
        sentencia.setInt(1, year);
        Principal.sc.nextLine();

        int filasIns = sentencia.executeUpdate();
        if (filasIns > 0) {
            System.out.println(" Cambio realizado ");
        }
    }
    public static void modificarTelCliente() throws SQLException {
        String sql = "UPDATE clientes SET celular=? WHERE alias=?";
        PreparedStatement sentencia = Principal.conectar().prepareStatement(sql);
        System.out.println("Por favor ingrese Alias del Cliente y el nuevo número de celular. ");
        System.out.print("Alias: ");
        String alias = Principal.sc.nextLine();
        sentencia.setString(2, alias);

        System.out.print("Celular: ");
        String cel = Principal.sc.nextLine();
        sentencia.setString(1, cel);

        int filasIns = sentencia.executeUpdate();
        if (filasIns > 0) {
            System.out.println(" Cambio realizado ");
         }
    }

    public static void borrarCompra() throws SQLException {
        String sql = "DELETE FROM compras WHERE alias=? AND fabricante=?";
        PreparedStatement sentencia = Principal.conectar().prepareStatement(sql);
        System.out.println("Por favor ingrese Alias del Cliente y Fabricante de la intención de compra a eliminar. ");
        System.out.print("Alias: ");
        String alias = Principal.sc.nextLine();
        sentencia.setString(1, alias);

        System.out.print("Fabricante: ");
        String fab = Principal.sc.nextLine();
        sentencia.setString(2, fab);

        int filasIns = sentencia.executeUpdate();
        if (filasIns > 0) {
            System.out.println(" Inteción de compra ELIMINADA satisfactoriamente");
        }
    }

    public static void Fabricantes() throws SQLException {
        String sql = "SELECT fabricante FROM fabricantes ORDER BY fabricante";
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()){
            String col1 = consulta.getNString(1);
            System.out.println(col1);
        }
    }

    public static void InfoBici() throws SQLException {
        String sql = "select fabricante,precio,año from bicicletas where año >= 2019 order by fabricante";
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()) {
            String col1 = consulta.getString(1);
            int col2 = consulta.getInt(2);
            String col3 = consulta.getString(3);
            System.out.println(col1 + " " + col2 + " " + col3);
        }
    }

    public static void FabricanteMoto() throws SQLException {
        String sql = "select fabricante from motocicletas where id_prov=101";
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()) {
            String col1 = consulta.getString(1);
            System.out.println(col1);
        }
    }

    public static void CompBiciMoto() throws SQLException {
        String sql = "select fabricante from compras where alias=\"lucky\" order by fabricante";
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()) {
            String col1 = consulta.getString(1);
            System.out.println(col1);
        }
    }

    public static void CompBiCliente() throws SQLException {
        String sql = "select c.alias, c.nombre, c.apellidos from clientes c,compras p where  p.alias=c.alias and p.fabricante=\"Yeti\" order by nombre";
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()) {
            String col1 = consulta.getString(1);
            String col2 = consulta.getString(2);
            String col3 = consulta.getString(3);
            System.out.println(col1 + " " + col2 + " " + col3);
        }
    }

    public static void BiciFabricante() throws SQLException {
        System.out.println("Por favor ingrese Año ");
        System.out.print("Año: ");
        int year = Principal.sc.nextInt();
        Principal.sc.nextLine();

        String sql = "select count(fabricante) from bicicletas where año >=" + year;
        Statement sentencia = Principal.conectar().createStatement();
        ResultSet consulta = sentencia.executeQuery(sql);

        while (consulta.next()) {
            int col1 = consulta.getInt(1);
            System.out.println(col1);
        }
    }
}