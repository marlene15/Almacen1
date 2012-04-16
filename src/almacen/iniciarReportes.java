package almacen;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marlene Alejandra Maciel Torres 
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
            conn= DriverManager.getConnection("jdbc:mysql://localhost/almacen","root","marlene");
            System.out.println("Conexión establecida");
            //JOptionPane.showMessageDialog(null,"Conexiónestablecida");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }   
        
    }    
     public void ejecutarReporte(String rep, String titulo){         
         String dir=rep;
        try{ 
            String archivo = dir;
            System.out.println("Cargando desde: " + archivo);
            if(archivo == null){
                System.out.println("No se encuentra el archivo.");
                System.exit(2);
            }
            JasperReport masterReport=null;
            try {// Cargas tu reporte maestro
                masterReport=(JasperReport)JRLoader.loadObject(archivo);                
            } 
            catch (JRException e){
                System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                System.exit(3);
            }
            //este es el parámetro, se pueden agregar más parámetros
            //basta con poner mas parametro.put
            Map parametros= new HashMap();
           // parametro.put("clave",clave);
            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametros, conn);
            //Se lanza el Viewerde Jasper, no termina aplicación al salir
            JasperViewer jviewer= new JasperViewer(jasperPrint,false);
            jviewer.setTitle(titulo);
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
    

