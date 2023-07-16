/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Willy
 */

public class ConexionServidor {
    
    // Datos de la cuenta a usar para el servidor Clever Cloud MySQL
    Connection conectar;
    String usuario = "ut6odo52fro9hmlh";
    String clave = "9URKTRAMq3g1ey3u4Ntz";
    String bd = "bbuibzrcrj0vkqk8kopj";
    String ip = "bbuibzrcrj0vkqk8kopj-mysql.services.clever-cloud.com";
    String puerto = "3306";
    String url = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
   
    // Constructor de la clase ConexionServidor
    public ConexionServidor() {
        conectar =null;
    }
    
    // Se conecta al servidor mediante datos de la cuenta y su url
    public Connection getConnection(){
        try{
            // Driver de libreria MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Comando para la conexion
            conectar= DriverManager.getConnection(url, usuario, clave);
            System.out.println("fino");
        }
        // Obtiene excepcion cuando no se puede conectar al servidor
        catch (ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, "No se logró conectar a la base de datos. Revise su conexión de internet", "Error", 0);
            System.exit(0);
        }
        return conectar;
    }
    
    // Cerrar conexion con el servidor
    public void desconectar(){
        try{
            conectar.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión con la base de datos: " + ex.getMessage(), "Error", 0);
        }
    }    
    
}

