package construirsa;

import java.sql.*;

public class ConstruirSA {
    
    public static void main(String[] args) {
        try {
//  1. Cargar el driver MariaDB 

            Class.forName("com.mysql.jdbc.Driver");

//  2. Establecer la conexión a la base de datos previamente creada.
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/obrador", "root" , "");
            
////  3. Insertar 3 empleados.
//
//            String empleadoSQL = "INSERT INTO empleado(dni, apellido, nombre, acceso, estado) "
//                    + "VALUES(132456, 'gomez', 'maximo', 2, 1),"
//                    + "(1324256, 'villegas', 'guillermo', 6, 2),"
//                    + "(1324546, 'diaz', 'gonzalo', 4, 1)";
//            
//            PreparedStatement ps = conexion.prepareStatement(empleadoSQL);
//            int filas = ps.executeUpdate();
//            
//            if(filas>0){
//                System.out.println("Carga de empleados correcta");
//            }
            
////  4. Insertar 2 herramientas.
//
//            String herramienta = "INSERT INTO herramienta(nombre, stock, descripcion, estado)"
//                    + "VALUES('Llave', 10, 'sirve para ajustar o aflojar tuercas', 1),"
//                    + "('Alicate', 8, 'sirve para cortar o pelar cables', 2)";
//            
//            PreparedStatement ps = conexion.prepareStatement(herramienta);            
//            int filas = ps.executeUpdate();
//            
//            if(filas>0){
//                System.out.println("Carga de herramientas correcta");
//            }
         
////  5. Listar todas las herramientas con stock superior a 10.
//         
//            String consultaSQL = "SELECT * FROM herramienta WHERE stock > 10";
//
//            PreparedStatement ps = conexion.prepareStatement(consultaSQL);
//            ResultSet datos = ps.executeQuery();
//
//            while(datos.next()) {
//                int id_herramienta = datos.getInt("id_Herramienta");
//                String nombre = datos.getString("nombre");
//                String descripcion = datos.getString("descripcion");
//                int stock = datos.getInt("stock");
//                
//                System.out.println("Nombre: " + nombre);
//                System.out.println("Id Herramienta: " + id_herramienta);
//                System.out.println("Descripcion: " + descripcion);
//                System.out.println("Stock: " + stock);
//                System.out.println("------------------------------------------------------------------");
//            }
        
//  6. Dar de baja al primer empleado ingresado a la base de datos.
        
            String consultaSQL = "SELECT * FROM empleado ORDER BY id_Empleado ASC LIMIT 1";
            PreparedStatement ps = conexion.prepareStatement(consultaSQL);
            ResultSet datos= ps.executeQuery();
            int idEmpleado = 0;

            while(datos.next()) {
                idEmpleado = datos.getInt("id_Empleado");
            }

            String bajaEmpleadoSQL = "UPDATE empleado SET estado = 1 WHERE id_Empleado = ?";
            ps = conexion.prepareStatement(bajaEmpleadoSQL);
            ps.setInt(1, idEmpleado);
            
            int filas = ps.executeUpdate();
            
            if(filas>0){
                System.out.println("Modificación correcta");
            }
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver");
            ex.printStackTrace();
            
        } catch(SQLSyntaxErrorException ex){
            System.out.println("Error de sintaxis en sentencia SQL");
            ex.printStackTrace();
            
        } catch (SQLException ex) {
            switch (ex.getErrorCode()) {    //Codigo de tipo de error
                case 1062:
                    System.out.println("Entrada duplicada");
                    break;
                case 1049:
                    System.out.println("Base de datos desconocida");
                    break;
                default:
                    System.out.println("Error de conexión");
                    break;
            }
            ex.printStackTrace();
        }
    }
}
    

