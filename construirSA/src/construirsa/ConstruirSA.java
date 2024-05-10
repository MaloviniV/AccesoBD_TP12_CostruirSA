package construirsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConstruirSA {
    
    public static void main(String[] args) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/obrador", "root" , "");
            
            
            //Ingresar herramientas y empleados
        /*   String empl = "INSERT INTO empleado(dni, apellido, nombre, acceso, estado) "
                    + "VALUES(132456, 'gomez', 'maximo', 2, 1),"
                    + "(1324256, 'villegas', 'guillermo', 6, 2),"
                    + "(1324546, 'diaz', 'gonzalo', 4, 1)";
            String herr = "INSERT INTO herramienta(nombre, stock, descripcion, estado)"
                    + "VALUES('Llave', 10, 'sirve para ajustar o aflojar tuercas', 1),"
                    + "('Alicate', 8, 'sirve para cortar o pelar cables', 2)";
            
            PreparedStatement ps1 = con.prepareStatement(herr);
            PreparedStatement ps2 = con.prepareStatement(empl);
            
           ps1.executeUpdate();
           ps2.executeUpdate();
          */
         
         //Listar herramientas con stock mayor a 10
         
        /* String sql = "SELECT * FROM herramienta WHERE stock > 10";
         
         PreparedStatement ps = con.prepareStatement(sql);
         //ps.executeQuery();
         
         ResultSet datos = ps.executeQuery();
         
         while(datos.next()) {
             int id_herramienta = datos.getInt("idHerramienta");
             String nombre = datos.getString("nombre");
             String descripcion = datos.getString("descripcion");
             int stock = datos.getInt("stock");
             
             System.out.println("Id Herramienta: " + id_herramienta);
             System.out.println("Nombre: " + nombre);
             System.out.println("Descripcion: " + descripcion);
             System.out.println("Stock: " + stock);
         }
         */
        
        //Dar de baja al primer empleado ingresado a la base de datos.
        
        String sql = "SELECT * FROM empleado ORDER BY idEmpleado asc LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet datos= ps.executeQuery();
        int idEmpleado = 0;
     
        while(datos.next()) {
            idEmpleado = datos.getInt("idEmpleado");
        }
        
        String sql1 = "UPDATE empleado SET estado= 0 WHERE idEmpleado = ?";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps1.setInt(1, idEmpleado);
        ps1.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConstruirSA.class.getName()).log(Level.SEVERE, null, ex);
           
            System.out.println("Error de conexion " + ex);
        }
        
       
       
        }
        
        
    }
    

