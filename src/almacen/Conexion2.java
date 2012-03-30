package almacen;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marlene
 */
import java.sql.*;
import javax.swing.JOptionPane;
public class Conexion2 {// este es el nombre de la clase
    Connection miconexion;
     Statement stSetencias;
     ResultSet rsDatos;
     PreparedStatement psPrepararSentencia;
    
     public  Conexion2() throws SQLException, ClassNotFoundException
     {
         try
         {
             System.out.println("entro");
             Class.forName("com.mysql.jdbc.Driver");
             String url="jdbc:mysql://localhost/almacen";
             miconexion=DriverManager.getConnection(url,"root","marlene");
             stSetencias=miconexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
         }
         catch (ClassCastException ex)
         {
             JOptionPane.showMessageDialog(null, " entro");
             throw ex;
             
         }
         catch(SQLException ex1)
         {
             JOptionPane.showMessageDialog(null, " entro");
             throw ex1;
         }
         
     }
     public ResultSet consulta(String sql) throws SQLException
     {
         try
         {
             rsDatos=stSetencias.executeQuery(sql);
         }
         catch(SQLException ex)
         {
             throw ex; 
         }
         return rsDatos;
     }
     public void desconectar()
     {
         miconexion=null;
     }   
     
 }



     
