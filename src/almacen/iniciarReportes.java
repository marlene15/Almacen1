package almacen;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marlene
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.view.save.JRPdfSaveContributor.*;
import net.sf.jasperreports.view.JRViewer.*;
import net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor.*;

import java.sql.*;

public class iniciarReportes {
    Connection conn=null;
    
    public iniciarReportes() {     
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); //se carga el driver
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen","root","marlene");
            System.out.println("Conexiónestablecida");
            //JOptionPane.showMessageDialog(null,"Conexiónestablecida");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
                
    }    
     public void ejecutarReporte(String Clave){
        try{ 
            String archivo = "Productos.jasper";
            System.out.println("Cargando desde: " + archivo);
            if(archivo == null){
                System.out.println("No se encuentra el archivo.");
                System.exit(2);
            }
            JasperReport masterReport=null;
            try {// Cargas tu reporte maestro
                masterReport=(JasperReport) JRLoader.loadObject(archivo);
            } 
            catch (JRException e){
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }
            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put
            Map parametro= new HashMap();
            parametro.put("tablacatalogoproductos_Clave",Clave);
            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint= JasperFillManager.fillReport(masterReport,parametro,conn);
            //Se lanza el Viewerde Jasper, no termina aplicación al salir
            JasperViewer jviewer= new JasperViewer(jasperPrint,false);
            jviewer.setTitle("REPORTE DE PRODUCTOS");
            jviewer.setVisible(true);
        }
        catch (Exception j){
            System.out.println("Mensaje de Error:"+j.getMessage());
        }
    }
    public void cerrar()
    {
        try {
            conn.close();
        } 
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
    

