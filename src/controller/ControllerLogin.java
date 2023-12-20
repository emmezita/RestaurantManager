/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.viewlogin.PanelCrearCuenta;
import view.viewlogin.PanelLogin;
import view.viewprincipal.SistemaPrincipal;
import view.viewprincipal.VentanaPrincipal;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.modelusuario.Gerente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import model.conexionBD.ConexionServidor;
import javax.swing.table.DefaultTableModel;

/**
 *
 * Desarrollo: Pedro Leal | Intefaces: Estefany Torres
 */
public class ControllerLogin implements ActionListener{
    
    private final VentanaPrincipal ventanaPrincipal;
    private final PanelLogin panelLogin = new PanelLogin();
    private final PanelCrearCuenta panelCrear = new PanelCrearCuenta();
    private final SistemaPrincipal panelSistema = new SistemaPrincipal();
    private final Gerente gerente = new Gerente();

    static final ArrayList <Gerente> listaGerentes = new ArrayList<>();
    static String indicadorUsuario = ""; 
    
    private final String correoRestaurant = "salumificioivisconti@gmail.com";
    private final String claveRestaurant = "fcospszlvguzwerd";
    
    private final String SQL_INSERT = "INSERT INTO Gerente (clave,cedula,nombre,apellido,telefono,edad,ubicacion,sexo,status,rol, correo, username, icono,reservacion, empleado, inventario, menu)"
                                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private PreparedStatement PS;
    private final ConexionServidor CN;
    
    private final String SQL_SELECT = "SELECT * FROM Gerente";
    private DefaultTableModel DT;
    private ResultSet RS;
     
      
    public ControllerLogin(VentanaPrincipal ventanaPrincipal) {
        //Ventana Principal
        this.ventanaPrincipal = ventanaPrincipal;
        //Panel de Inicio de Sesion
        panelLogin.botonIniciarSesion.addActionListener(this);
        panelLogin.botonCrearCuenta.addActionListener(this);
        panelLogin.botonRecuperarPass.addActionListener(this);
        //Panel de Crear Cuenta
        panelCrear.botonCrearCuenta.addActionListener(this);
        panelCrear.botonIniciar.addActionListener(this);
        //Panel del Sistema Principal
        panelSistema.botonSalir.addActionListener(this);
        ControllerUsuarios controlUsuarios = new ControllerUsuarios(panelSistema);
        ControllerEmpleados controlEmpleados = new ControllerEmpleados(panelSistema);
        ControllerInventario controlInventario = new ControllerInventario(panelSistema);
        ControllerReservaciones controlReservaciones = new ControllerReservaciones(panelSistema);
        ControllerMenu controlMenu = new ControllerMenu(panelSistema);
        
        PS = null;
        CN= new ConexionServidor();
        setDatos();
    }
    
    private DefaultTableModel setColumnas(){
        DT = new DefaultTableModel();
        DT.addColumn("clave");
        DT.addColumn("cedula");
        DT.addColumn("nombre");
        DT.addColumn("apellido");
        DT.addColumn("telefono");
        DT.addColumn("edad");
        DT.addColumn("ubicacion");
        DT.addColumn("sexo");
        DT.addColumn("status");
        DT.addColumn("rol");
        DT.addColumn("correo");
        DT.addColumn("username");
        DT.addColumn("icono");
        DT.addColumn("reservacion");
        DT.addColumn("empleado");
        DT.addColumn("inventario");
        DT.addColumn("menu");
        return DT;
    }
    
    public DefaultTableModel setDatos(){
    try{
        setColumnas();
        PS = CN.getConnection().prepareStatement(SQL_SELECT);
        RS = PS.executeQuery();
        while (RS.next()){
            String clave = RS.getString(1);
            String cedula = RS.getString(2);
            String nombre = RS.getString(3);
            String apellido = RS.getString(4);
            String telefono = RS.getString(5);
            int edad = RS.getInt(6);
            String ubicacion = RS.getString(7);
            String sexoS = RS.getString(8);
            char sexo = sexoS.charAt(0);
            if (sexo == '.')
                sexo = '\0';
            boolean status = RS.getBoolean(9);
            String rol = RS.getString(10);
            String correo = RS.getString(11);
            String username = RS.getString(12); 
            byte[] imagedata = RS.getBytes(13);
            ImageIcon icono = new ImageIcon("/images/imagesviewusuarios/fotoPerfil.png"); // valor predeterminado
            if (imagedata != null && imagedata.length > 0) {
                icono = new ImageIcon(imagedata); // valor correcto
            } else {
                icono = null; // asignar null si no hay datos de imagen disponibles
            }
            boolean resp[] = new boolean[4];
            resp[0] = RS.getBoolean(14);
            resp[1] = RS.getBoolean(15);
            resp[2] = RS.getBoolean(16);
            resp[3] = RS.getBoolean(17);
            
            Gerente ge = new Gerente (username,clave,status,icono,rol,cedula,nombre,apellido,edad,sexo,ubicacion,correo,telefono);
            ge.setResp(resp);
            listaGerentes.add(ge);              
        }
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(null, "Error al listar los datos de los usuarios: " +e.getMessage(), "Error", 0);
    }
    finally{
        PS=null;
        RS=null;
        CN.desconectar();
    }
    return DT;
}
    
    public void IniciarVentana(){
        ventanaPrincipal.setTitle("Restaurant Manager");
        ventanaPrincipal.setSize(1026, 767);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setResizable(false);
        panelLogin.setSize(1008,720);
        panelLogin.setLocation(0,0);
        ventanaPrincipal.panelPrincipal.removeAll();
        ventanaPrincipal.panelPrincipal.add(panelLogin);
        ventanaPrincipal.panelPrincipal.revalidate();
        ventanaPrincipal.panelPrincipal.repaint();
    }
    
    public boolean camposVaciosCrearCuenta(){
        return (panelCrear.txtNombre.getText().isEmpty() || panelCrear.txtApellido.getText().isEmpty() || panelCrear.txtCorreo.getText().isEmpty() ||
                String.valueOf(panelCrear.txtPass.getPassword()).isEmpty() || panelCrear.txtNombre.getText().equals("Nombre") || panelCrear.txtApellido.getText().equals("Apellido") ||
                panelCrear.txtCorreo.getText().equals("Correo") || String.valueOf(panelCrear.txtPass.getPassword()).equals("******"));
              
    }
     
    public void resetearCamposCrearCuenta(){
        panelCrear.txtNombre.setForeground(Color.LIGHT_GRAY);
        panelCrear.txtApellido.setForeground(Color.LIGHT_GRAY);
        panelCrear.txtCorreo.setForeground(Color.LIGHT_GRAY);
        panelCrear.txtPass.setForeground(Color.LIGHT_GRAY);        
        panelCrear.txtNombre.setText("Nombre");
        panelCrear.txtApellido.setText("Apellido");
        panelCrear.txtCorreo.setText("Correo");
        panelCrear.txtPass.setText("******");
    }
    
     
    public boolean camposVaciosIniciarSesion(){
        return (panelLogin.txtUser.getText().isEmpty() || String.valueOf(panelLogin.txtPass.getPassword()).isEmpty() ||
                panelLogin.txtUser.getText().equals("Usuario o Correo") || String.valueOf(panelLogin.txtPass.getPassword()).equals("******"));
    }
     
    public void resetearCamposIniciarSesion(){
        panelLogin.txtUser.setForeground(Color.LIGHT_GRAY);
        panelLogin.txtPass.setForeground(Color.LIGHT_GRAY);
        panelLogin.txtUser.setText("Usuario o correo");
        panelLogin.txtPass.setText("******");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BOTONES DEL PANEL: panelLogin
        
        //Boton Crear Cuenta
        if (e.getSource()==panelLogin.botonCrearCuenta){
            panelCrear.fondoBotonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonNaranjaInicio.png")));
            panelCrear.botonIniciar.setForeground(new Color(230,231,235));
            panelCrear.fondoBotonIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonAmarilloCrearCuenta.png")));
            resetearCamposCrearCuenta();
            panelCrear.setSize(1008,720);
            panelCrear.setLocation(0,0);
            ventanaPrincipal.panelPrincipal.removeAll();
            ventanaPrincipal.panelPrincipal.add(panelCrear);
            ventanaPrincipal.panelPrincipal.revalidate();
            ventanaPrincipal.panelPrincipal.repaint();
        }
        
        //Boton Iniciar
        if (e.getSource()==panelLogin.botonIniciarSesion){
            if(!camposVaciosIniciarSesion()){ 
                if(gerente.existeUsuario(panelLogin.txtUser.getText(), listaGerentes)) {
                    if(gerente.claveCorrecta(panelLogin.txtUser.getText(), String.valueOf(panelLogin.txtPass.getPassword()), listaGerentes)){
                        Gerente g = gerente.buscarGerente(listaGerentes,panelLogin.txtUser.getText());
                        if(g.isStatus()){
                            indicadorUsuario = g.getCorreo();
                            panelSistema.botonReservaciones.setVisible(true);
                            panelSistema.iconoReservaciones.setVisible(true);
                            panelSistema.botonEmpleados.setVisible(true);
                            panelSistema.iconoEmpleados.setVisible(true);
                            panelSistema.botonInventario.setVisible(true);
                            panelSistema.iconoInventario.setVisible(true);
                            panelSistema.botonMenu.setVisible(false);
                            panelSistema.iconoIMenu.setVisible(false);
                            for(int i = 0; i<4 ; i++){
                                if(g.getRol().equals("Gerente")){
                                    if(!g.getResp()[i]){
                                        if(i==0){
                                            panelSistema.botonReservaciones.setVisible(false);
                                            panelSistema.iconoReservaciones.setVisible(false);
                                        }
                                        if(i==1){
                                            panelSistema.botonEmpleados.setVisible(false);
                                            panelSistema.iconoEmpleados.setVisible(false);
                                        }
                                        if(i==2){
                                            panelSistema.botonInventario.setVisible(false);
                                            panelSistema.iconoInventario.setVisible(false);
                                        }
                                        if(i==3){
                                            panelSistema.botonMenu.setVisible(false);
                                            panelSistema.iconoIMenu.setVisible(false);
                                        }
                                    }    
                                }
                            }
                            panelSistema.setSize(1008,720);
                            panelSistema.setLocation(0,0);
                            panelSistema.panelPrincipal.removeAll();
                            panelSistema.panelPrincipal.revalidate();
                            panelSistema.panelPrincipal.repaint();
                            ventanaPrincipal.panelPrincipal.removeAll();
                            ventanaPrincipal.panelPrincipal.add(panelSistema);
                            ventanaPrincipal.panelPrincipal.revalidate();
                            ventanaPrincipal.panelPrincipal.repaint();
                            resetearCamposIniciarSesion();
                        } else {
                            JOptionPane.showMessageDialog(null, "Su cuenta no ha sido aceptada por el administrador.\nNo puede entrar", "Error", 0);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "La clave es incorrecta", "Error", 0);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "El usuario no existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }       
            }else{
              JOptionPane.showMessageDialog(null,"Ingrese todos sus datos antes de iniciar","Adverencia",JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //Boton Recuperar contraseña
        if (e.getSource()==panelLogin.botonRecuperarPass){
            try {  
                String usuario= JOptionPane.showInputDialog(null, "Introduzca su usuario o correo");
                String correoReceptor = "";
                String claveUsuario = "";
                String nombreUsuario = "";
                if (gerente.existeUsuario(usuario, listaGerentes) ) {
                    Gerente g = gerente.buscarGerente(listaGerentes, usuario);
                    correoReceptor = g.getCorreo();
                    claveUsuario = g.getClave();
                    nombreUsuario = g.getNombre();
                    String mensaje="Hola " + nombreUsuario + "! La clave de tu usuario es: " + claveUsuario + "\n\nEsperamos poder haber ayudado y pueda seguir usando nuestro sistema."
                            + "\n\n\nRestaurantManager - Salumificio I Visconti";            
                    Properties propiedad = new Properties();
                    propiedad.put("mail.smtp.host", "smtp.gmail.com");
                    propiedad.setProperty("mail.smtp.starttls.enable", "true");
                    propiedad.put("mail.smtp.port", "587");
                    propiedad.setProperty("mail.smtp.port", "587");
                    propiedad.put("mail.smtp.user",correoRestaurant);
                    propiedad.setProperty("mail.smtp.auth", "true");
                    Session sesion = Session.getDefaultInstance(propiedad);
                    MimeMessage mail = new MimeMessage(sesion);
                    try {
                        mail.setFrom(new InternetAddress (correoRestaurant));
                        mail.addRecipient(Message.RecipientType.TO, new InternetAddress (correoReceptor));
                        mail.setSubject("Recuperación de clave");
                        mail.setText(mensaje);
                        JOptionPane.showMessageDialog(null, "Espere un momento... Estamos procesando su solicitud", "Recuperación de clave", 1);
                        try (Transport transportar = sesion.getTransport("smtp")) {
                            transportar.connect(correoRestaurant,claveRestaurant);
                            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                        }
                        JOptionPane.showMessageDialog(null, "Listo! Su clave ha sido enviada a su correo", "Recuperación de clave", 1);
                    } catch (HeadlessException | MessagingException ex) {
                        JOptionPane.showMessageDialog(null, "El correo no pudo ser enviado. Intente de nuevo", "Error", 0);
                    }     
                }else{
                    JOptionPane.showMessageDialog(null, "El usuario no existe", "Error", 0);
                }
            } catch (NullPointerException ne){
                
            }
        }
        
        //BOTONES DEL PANEL: panelCrearCuenta
        
        //Boton Crear Cuenta
        if (e.getSource()==panelCrear.botonCrearCuenta){
            if(!camposVaciosCrearCuenta()){
                String nombre =  panelCrear.txtNombre.getText();
                String apellido = panelCrear.txtApellido.getText();
                String correo = panelCrear.txtCorreo.getText();
                String clave = String.valueOf(panelCrear.txtPass.getPassword());
                if(gerente.validarCrearCuenta(nombre,apellido,correo,clave,listaGerentes)){
                    try{
                        PS=CN.getConnection().prepareStatement(SQL_INSERT);
                        PS.setString(1, clave);
                        PS.setString(2, "");
                        PS.setString(3, nombre);
                        PS.setString(4, apellido);
                        PS.setString(5, "");
                        PS.setInt(6, 0);
                        PS.setString(7, "");
                        PS.setString(8, ".");
                        PS.setBoolean(9, false);
                        PS.setString(10, "Gerente");
                        PS.setString(11, correo);
                        PS.setString(12, "");
                        PS.setString(13, "");
                        PS.setBoolean(14, false);
                        PS.setBoolean(15, false);
                        PS.setBoolean(16, false);
                        PS.setBoolean(17, false);
              
                        int res = PS.executeUpdate();
                        if (res > 0){
                            JOptionPane.showMessageDialog(null, "El gerente ha sido ingresado con éxito", "", 1);
                        }
                        Gerente user = new Gerente(clave,false,"Gerente",correo,nombre,apellido);
                        listaGerentes.add(user);
                        try {  
                            Gerente g = gerente.buscarGerente(listaGerentes, "admin");
                            String mensaje="Hola " + g.getNombre() + " " + g.getApellido() + "! Tienes una solicitud de " + nombre + " " + apellido +" para ingresar al sistema."
                                        + "\n ¡Revísala pronto!"
                                        + "\n\n\nRestaurantManager - Salumificio I Visconti";            
                            Properties propiedad = new Properties();
                            propiedad.put("mail.smtp.host", "smtp.gmail.com");
                            propiedad.setProperty("mail.smtp.starttls.enable", "true");
                            propiedad.put("mail.smtp.port", "587");
                            propiedad.setProperty("mail.smtp.port", "587");
                            propiedad.put("mail.smtp.user",correoRestaurant);
                            propiedad.setProperty("mail.smtp.auth", "true");
                            Session sesion = Session.getDefaultInstance(propiedad);
                            MimeMessage mail = new MimeMessage(sesion);
                            try {
                                mail.setFrom(new InternetAddress (correoRestaurant));
                                mail.addRecipient(Message.RecipientType.TO, new InternetAddress (g.getCorreo()));
                                mail.setSubject("Nueva solicitud para ingresar al sistema");
                                mail.setText(mensaje);
                                JOptionPane.showMessageDialog(null, "Espere un momento... Se enviará un correo al administrador para notificar su solicitud","Mensaje", 1);
                                try (Transport transportar = sesion.getTransport("smtp")) {
                                    transportar.connect(correoRestaurant,claveRestaurant);
                                    transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                                }
                                JOptionPane.showMessageDialog(null, "Cuenta creada con éxito. Se enviará un correo cuando su cuenta haya sido creada","Mensaje", 1);
                            } catch (HeadlessException | MessagingException ex) {}     
                        } catch (NullPointerException ne){}
                    }
                    catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Error al guardar los datos del gerente en la base de datos: " +ex.getMessage(), "Error", 0);
                    }
                    finally{
                        PS=null;
                        CN.desconectar();
                    }
                    resetearCamposIniciarSesion();
                    resetearCamposCrearCuenta();
                }
            }else{
              JOptionPane.showMessageDialog(null,"Ingrese todos los datos antes de crear su cuenta","Adverencia",JOptionPane.WARNING_MESSAGE);
          }
        }
        
        //Boton Iniciar
        if (e.getSource()==panelCrear.botonIniciar){
            panelLogin.botonRecuperarPass.setForeground(Color.lightGray);
            panelLogin.fondoBotonIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonNaranjaInicio.png")));
            panelLogin.botonCrearCuenta.setForeground(new Color(230,231,235));
            panelLogin.fondoBotonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonAmarilloCrearCuenta.png")));
            resetearCamposIniciarSesion();
            panelLogin.setSize(1008,720);
            panelLogin.setLocation(0,0);
            ventanaPrincipal.panelPrincipal.removeAll();
            ventanaPrincipal.panelPrincipal.add(panelLogin);
            ventanaPrincipal.panelPrincipal.revalidate();
            ventanaPrincipal.panelPrincipal.repaint();
         }
        
        //BOTONES DEL PANEL: panel Sistema
        if(e.getSource()==panelSistema.botonSalir){
            int conf = JOptionPane.showOptionDialog(null, "¿Deseas cerrar sesion?","Salir del Sistema",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            if (conf == 0) {
                panelLogin.botonRecuperarPass.setForeground(Color.lightGray);
                panelLogin.fondoBotonIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonNaranjaInicio.png")));
                panelLogin.botonCrearCuenta.setForeground(new Color(230,231,235));
                panelLogin.fondoBotonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonAmarilloCrearCuenta.png")));
                panelLogin.setSize(1008,720);
                panelLogin.setLocation(0,0);
                ventanaPrincipal.panelPrincipal.removeAll();
                ventanaPrincipal.panelPrincipal.add(panelLogin);
                ventanaPrincipal.panelPrincipal.revalidate();
                ventanaPrincipal.panelPrincipal.repaint();
            }            
        }
    
    }
}

