package almacen;

/*
 * Nombre: ConexionDB.java
 * Objetivo: Realizar la conexión con una base de datos desde java
 * Autor: alumnos de TAP....
 * Fecha: 05/03/2012 ........
 */

import java.sql.*;
import java.io.*;

public class Conexion {
    static String bd="almacen";
    static String login="root";
    static String password="marlene";
    static String url="jdbc:mysql://localhost/"+bd;
    
    ResultSet Sentencia;
    Statement stSentencia;
    Connection conn=null;
    
    PreparedStatement psPrepararSentencias;
    
    //Constructor de la clase
    public Conexion(){
        try{
            //Obtenemos el drive de para mysql
            Class.forName("com.mysql.jdbc.Driver");
            //Obtenemos la conexion
            conn=DriverManager.getConnection(url,login,password);
            if(conn!=null)
               System.out.println("Intento de Conexión a base de datos "+bd+" Correcto");
        }catch(SQLException e){
            System.out.println(e.getCause());
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    //Permite retornar la conexion
    public Connection getConnection(){
        return conn;
    }
    public void desconectar(){
        conn=null;
    }
    public ResultSet consulta(String sql) throws SQLException{
        
        try{
            Sentencia=stSentencia.executeQuery(sql);
        }
        catch(SQLException ex){
            throw ex;
        }
        return Sentencia;
    }
            
}
