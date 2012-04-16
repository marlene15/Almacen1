/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package almacen;

/**
 *
 * @author Marlene Alejandra Maciel Torres 
 */
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.view.save.JRPdfSaveContributor.*;
import net.sf.jasperreports.view.JRViewer.*;
import net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor.*;

public class iniciarReportesDocumentos {
    Connection conn=null;
    
    public iniciarReportesDocumentos() {     
        
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
     public void ejecutarReporte(String rep, String titulo,String fecha){         
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
            parametros.put("fecha",fecha);
            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, parametros,conn);
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
