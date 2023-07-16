/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.ControllerLogin.indicadorUsuario;
import static controller.ControllerLogin.listaGerentes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import model.modelusuario.Gerente;
import view.viewprincipal.SistemaPrincipal;
import view.viewusuarios.PanelConsultarUsuarios;
import view.viewusuarios.PanelEditarUsuario;
import view.viewusuarios.PanelUsuarioActivo;
import view.viewusuarios.PanelUsuarioPendiente;
import view.viewusuarios.FrameAsignarRol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.conexionBD.ConexionServidor;
import javax.swing.table.DefaultTableModel;
import view.viewusuarios.FrameReestablecer;

/**
 *
 * Desarrollo: Pedro Leal | Intefaces: Estefany Torres
 */
public class ControllerUsuarios implements ActionListener, ItemListener, KeyListener{
    
    // Atributos del Controller Usuarios de los paneles y frames(ventanas) que posee
    private final SistemaPrincipal panelSistema;
    private final PanelEditarUsuario panelEditarU = new PanelEditarUsuario();
    private final PanelConsultarUsuarios panelConsultar = new PanelConsultarUsuarios();
    private final FrameAsignarRol frameAsignar = new FrameAsignarRol();
    private final FrameReestablecer frameRes = new FrameReestablecer();
    
    private final Gerente gerente = new Gerente();
    
    private String usuarioPendiente = "";
    private String usuarioActivo = "";
    
    private final String correoRestaurant = "salumificioivisconti@gmail.com";
    private final String claveRestaurant = "fcospszlvguzwerd";
    
    private final String SQL_INSERT = "INSERT INTO Gerente (clave,cedula,nombre,apellido,telefono,edad,ubicacion,sexo,status,rol, correo, username,icono,reservacion, empleado, inventario, menu)"
                                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private PreparedStatement PS;
    private final ConexionServidor CN;
    
    private final String SQL_SELECT = "SELECT * FROM Gerente";
    private DefaultTableModel DT;
    private ResultSet RS;
    
    public ControllerUsuarios(SistemaPrincipal panelSistema) {
        //Ventana del sistema
        this.panelSistema = panelSistema;
        this.panelSistema.botonUsuario.addActionListener(this);
        //Panel de Consultar y Editar Usuario
        panelEditarU.botonGuardar.addActionListener(this);
        panelEditarU.botonGestionarUsuarios.addActionListener(this);
        panelEditarU.botonEditarFoto.addActionListener(this);
        panelEditarU.botonReestablecer.addActionListener(this);
        //Panel de Consultar y Gestioanr Usuarios
        panelConsultar.labelTItulo.setText("USUARIOS ACTIVOS");
        panelConsultar.labelCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/circuloVerde.png")));
        panelConsultar.labelLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/lineaVerde.png")));
        panelConsultar.labelStatus.setForeground(new java.awt.Color(152, 255, 163));
        panelConsultar.labelStatus.setText("Usuario Activo");
        panelConsultar.txtBuscar.addKeyListener(this);
        panelConsultar.botonAceptar.addActionListener(this);
        panelConsultar.botonRechazar.addActionListener(this);
        panelConsultar.botonRegresar.addActionListener(this);
        panelConsultar.comboBoxUsuario.addItemListener(this);
        panelConsultar.botonAsignar.addActionListener(this);
        panelConsultar.botonEliminar.addActionListener(this);
        //Frame Asignar rol
        frameAsignar.botonGuardar.addActionListener(this);
        //Frame Reestablecer
        frameRes.botonCambiar.addActionListener(this);
        PS = null;
        CN= new ConexionServidor();
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
       
    public boolean gerentesPendientes(){
        for (Gerente g: listaGerentes){
            if(!g.isStatus()){
                return true;
            }
        }
        return false;
    }
    
    public boolean gerentesActivos(){
        for (Gerente g: listaGerentes){
            if(g.isStatus()){
                return true;
            }
        }
        return false;
    }
    
    public int buscarMunicipio (String municipio){
        switch (municipio){
            case "Baruta": return 1;
            case "Chacao":return 2;
            case "El Hatillo": return 3;
            case "Libertador": return 4;
            case "Sucre": return 5;
            default:
                return 0;
        }
    }
    
    public void cargarDatosUsuario(){
        Gerente g = gerente.buscarGerente(listaGerentes, indicadorUsuario);
        //panelEditarU.fotoPerfil.setIcon(g.getIcono());
        if(!(g.getIcono() == null)){
            ImageIcon iconoOriginal = g.getIcono();
            Image imagenOriginal = iconoOriginal.getImage();
            // Escalar la imagen a un tamaño específico
            int ancho = 130;
            int alto = 130;
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
               
                // Crear una imagen de máscara circular
                BufferedImage imagenMascara = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2dMascara = imagenMascara.createGraphics();
                g2dMascara.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2dMascara.setColor(Color.WHITE);
                g2dMascara.fill(new Ellipse2D.Float(0, 0, ancho, alto));
                g2dMascara.setColor(Color.BLACK);
                g2dMascara.setStroke(new BasicStroke(1));
                g2dMascara.draw(new Ellipse2D.Float(0, 0, ancho, alto));
                g2dMascara.dispose();
        
                // Crear un nuevo ImageIcon a partir de la imagen escalada con la máscara circular
                BufferedImage imagenFinal = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2dFinal = imagenFinal.createGraphics();
                g2dFinal.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2dFinal.setClip(new Ellipse2D.Float(0, 0, ancho, alto));
                iconoEscalado.paintIcon(null, g2dFinal, 0, 0);
                g2dFinal.dispose();
                ImageIcon iconoFinal = new ImageIcon(imagenFinal);
        
                panelEditarU.fotoPerfil.setIcon(iconoFinal); // Asignar el icono al JLabel
        }else{
            panelEditarU.fotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fotoPerfil.png")));
        }
        if(!g.getNombre().isEmpty()) {
            panelEditarU.txtNombre.setForeground(Color.white);
            panelEditarU.txtNombre.setText(g.getNombre());
        }
        if(!g.getApellido().isEmpty()){
            panelEditarU.txtApellido.setForeground(Color.white);
            panelEditarU.txtApellido.setText(g.getApellido());
        }
        if(!g.getUsername().isEmpty()){
            panelEditarU.txtUsuario.setForeground(Color.white);
            panelEditarU.txtUsuario.setText(g.getUsername());
        }
        if(!g.getCorreo().isEmpty()){
            panelEditarU.txtCorreo.setForeground(Color.white);
            panelEditarU.txtCorreo.setText(g.getCorreo());
        }
        if(!g.getUbicacion().isEmpty()){
            panelEditarU.comboBoxMunicipio.setSelectedIndex(buscarMunicipio(g.getUbicacion()));
        }
        if(g.getEdad() != 0 ){
            panelEditarU.spinnerEdad.setModel(new javax.swing.SpinnerNumberModel(g.getEdad(), 18, 90, 1));
        }
        if(g.getSexo()!=0){
            if(g.getSexo()=='F'){
                panelEditarU.comboBoxSexo.setSelectedIndex(0);
            } else{
                panelEditarU.comboBoxSexo.setSelectedIndex(1);
            }
        }
        if(!g.getCedula().isEmpty()){
            panelEditarU.txtCedula.setForeground(Color.white);
            panelEditarU.txtCedula.setText(g.getCedula());
        }
        if(!g.getTelefono().isEmpty()){
            panelEditarU.txtNumero.setForeground(Color.white);
            panelEditarU.txtNumero.setText(g.getTelefono());
        }
    }
    
    public void cargarGerentesActivos(){
        resetearCamposConsultarUsuario();
        usuarioActivo = "";
        
        panelConsultar.iconoUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoUserV.png")));
        panelConsultar.iconoMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoMailV.png")));
        panelConsultar.iconoMunicipio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoLocationV.png")));
        panelConsultar.iconoSexo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoSexoV.png")));
        panelConsultar.iconoEdad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoEdadV.png")));
        panelConsultar.iconoTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoTelefonoV.png")));
        
        panelConsultar.fondoDetalleUsuarioP.setVisible(false);
        panelConsultar.fondoDetalleUsuarioA.setVisible(false);
        panelConsultar.labelCirculo.setVisible(false);
        panelConsultar.labelLinea.setVisible(false);
        panelConsultar.labelStatus.setVisible(false);
        panelConsultar.labelResponsable.setVisible(false);
        panelConsultar.iconoUser.setVisible(false);
        panelConsultar.iconoMail.setVisible(false);
        panelConsultar.iconoMunicipio.setVisible(false);
        panelConsultar.iconoSexo.setVisible(false);
        panelConsultar.iconoEdad.setVisible(false);
        panelConsultar.iconoTelefono.setVisible(false);
        panelConsultar.iconoRoles.setVisible(false);
        panelConsultar.botonAceptar.setVisible(false);
        panelConsultar.iconoAceptar.setVisible(false);
        panelConsultar.fondoBotonAceptar.setVisible(false);
        panelConsultar.botonRechazar.setVisible(false);
        panelConsultar.iconoRechazar.setVisible(false);
        panelConsultar.fondoBotonRechazar.setVisible(false);
        panelConsultar.botonAsignar.setVisible(false);
        panelConsultar.iconoAsignar.setVisible(false);
        panelConsultar.fondoBotonAsignar.setVisible(false);
        panelConsultar.botonEliminar.setVisible(false);
        panelConsultar.fondoBotonEliminar.setVisible(false);
        
        panelConsultar.labelTItulo.setText("USUARIOS ACTIVOS");
        panelConsultar.panelUsuarios.removeAll();

        // Agregar los paneles de empleado correspondientes a la nueva opción seleccionada
        for (Gerente g : listaGerentes) {
            if (g.isStatus() && g.getRol().equals("Gerente")) {
                String nombre = g.getNombre() + " " + g.getApellido();
                nombre = nombre.toLowerCase();
                if(nombre.contains(panelConsultar.txtBuscar.getText().toLowerCase())){
                    PanelUsuarioActivo panelUA = new PanelUsuarioActivo();
                    panelUA.setSize(310,80);
                    panelUA.labelUser.setText(g.getCorreo());
                    panelUA.labelUser.setVisible(false);
                    panelUA.botonUsuario.setText(g.getNombre() + " " + g.getApellido());
                    // Agregar un ActionListener al botón
                    panelUA.botonUsuario.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Actualizar el texto del JLabel
                            if(!(g.getIcono() == null)){
                                ImageIcon iconoOriginal = g.getIcono();
                                Image imagenOriginal = iconoOriginal.getImage();
                                // Escalar la imagen a un tamaño específico
                                int ancho = 120;
                                int alto = 120;
                                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                                ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
               
                                    // Crear una imagen de máscara circular
                                    BufferedImage imagenMascara = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
                                    Graphics2D g2dMascara = imagenMascara.createGraphics();
                                    g2dMascara.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                    g2dMascara.setColor(Color.WHITE);
                                    g2dMascara.fill(new Ellipse2D.Float(0, 0, ancho, alto));
                                    g2dMascara.setColor(Color.BLACK);
                                    g2dMascara.setStroke(new BasicStroke(1));
                                    g2dMascara.draw(new Ellipse2D.Float(0, 0, ancho, alto));
                                    g2dMascara.dispose();
        
                                    // Crear un nuevo ImageIcon a partir de la imagen escalada con la máscara circular
                                    BufferedImage imagenFinal = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
                                    Graphics2D g2dFinal = imagenFinal.createGraphics();
                                    g2dFinal.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                    g2dFinal.setClip(new Ellipse2D.Float(0, 0, ancho, alto));
                                    iconoEscalado.paintIcon(null, g2dFinal, 0, 0);
                                    g2dFinal.dispose();
                                    ImageIcon iconoFinal = new ImageIcon(imagenFinal);
        
                                    panelConsultar.labelFotoPerfil.setIcon(iconoFinal); // Asignar el icono al JLabel
                            }else{
                                panelConsultar.labelFotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fotoPerfil.png")));
                            }
                            panelConsultar.labelCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/circuloVerde.png")));
                            panelConsultar.labelLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/lineaVerde.png")));
                            panelConsultar.labelStatus.setForeground(new java.awt.Color(152, 255, 163));
                            panelConsultar.labelStatus.setText("Usuario Activo");
                            panelConsultar.labelResponsable.setVisible(true);
                            panelConsultar.fondoDetalleUsuarioA.setVisible(true);
                            panelConsultar.labelCirculo.setVisible(true);
                            panelConsultar.labelLinea.setVisible(true);
                            panelConsultar.labelStatus.setVisible(true);
                            panelConsultar.iconoUser.setVisible(true);
                            panelConsultar.iconoMail.setVisible(true);
                            panelConsultar.iconoMunicipio.setVisible(true);
                            panelConsultar.iconoSexo.setVisible(true);
                            panelConsultar.iconoEdad.setVisible(true);
                            panelConsultar.iconoTelefono.setVisible(true);
                            panelConsultar.iconoRoles.setVisible(true);
                            panelConsultar.botonAsignar.setVisible(true);
                            panelConsultar.iconoAsignar.setVisible(true);
                            panelConsultar.fondoBotonAsignar.setVisible(true);
                            panelConsultar.botonEliminar.setVisible(true);
                            panelConsultar.fondoBotonEliminar.setVisible(true);
                            panelConsultar.labelNombre.setText(g.getNombre() + " " + g.getApellido());
                            panelConsultar.labelCedula.setText("CI: "+g.getCedula());
                            panelConsultar.labelUser.setText(g.getUsername());
                            panelConsultar.labelMail.setText(g.getCorreo());
                            panelConsultar.labelMunicipio.setText(g.getUbicacion());
                            if(g.getSexo()=='F'){
                                panelConsultar.labelSexo.setText("Femenino");
                            } else {
                                panelConsultar.labelSexo.setText("Masculino");
                            }
                            panelConsultar.labelEdad.setText(String.valueOf(g.getEdad()));
                            panelConsultar.labelTelefono.setText(g.getTelefono());
                            String responsabilidades = "";
                            boolean resp [] = g.getResp();
                            String respo [] = new String [4];
                            respo[0] = "Reservaciones"; 
                            respo[1] = "Empleados";
                            respo[2] = "Inventario"; 
                            respo[3] = "Menús";
                            for(int i = 0; i<4; i++){
                                if(resp[i]){
                                    responsabilidades = responsabilidades + respo[i]+ ", ";
                                }
                            }
                            String responsabilidad = "";
                            if(!responsabilidades.isEmpty()){
                                responsabilidad = responsabilidades.substring(0, responsabilidades.length() - 2);
                            }
                            panelConsultar.labelRoles.setText(responsabilidad);
                            usuarioActivo = g.getCorreo();
                        }
                    });
                    panelConsultar.panelUsuarios.add(panelUA);    
                }
            }
        }
        // Actualizar la ventana
        panelConsultar.panelUsuarios.revalidate();
        panelConsultar.panelUsuarios.repaint();      
    }
    
    public void cargarGerentesPendientes(){
        resetearCamposConsultarUsuario();
        usuarioPendiente = "";
        
        panelConsultar.iconoUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoUserR.png")));
        panelConsultar.iconoMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoMailR.png")));
        panelConsultar.iconoMunicipio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoLocationR.png")));
        panelConsultar.iconoSexo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoSexoR.png")));
        panelConsultar.iconoEdad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoEdadR.png")));
        panelConsultar.iconoTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/iconoTelefonoR.png")));
                
        panelConsultar.fondoDetalleUsuarioP.setVisible(false);
        panelConsultar.fondoDetalleUsuarioA.setVisible(false);
        panelConsultar.labelCirculo.setVisible(false);
        panelConsultar.labelLinea.setVisible(false);
        panelConsultar.labelStatus.setVisible(false);
        panelConsultar.labelResponsable.setVisible(false);
        panelConsultar.iconoUser.setVisible(false);
        panelConsultar.iconoMail.setVisible(false);
        panelConsultar.iconoMunicipio.setVisible(false);
        panelConsultar.iconoSexo.setVisible(false);
        panelConsultar.iconoEdad.setVisible(false);
        panelConsultar.iconoTelefono.setVisible(false);
        panelConsultar.iconoRoles.setVisible(false);
        panelConsultar.botonAceptar.setVisible(false);
        panelConsultar.iconoAceptar.setVisible(false);
        panelConsultar.fondoBotonAceptar.setVisible(false);
        panelConsultar.botonRechazar.setVisible(false);
        panelConsultar.iconoRechazar.setVisible(false);
        panelConsultar.fondoBotonRechazar.setVisible(false);
        panelConsultar.botonAsignar.setVisible(false);
        panelConsultar.iconoAsignar.setVisible(false);
        panelConsultar.fondoBotonAsignar.setVisible(false);
        panelConsultar.botonEliminar.setVisible(false);
        panelConsultar.fondoBotonEliminar.setVisible(false);
        
        panelConsultar.labelTItulo.setText("USUARIOS PENDIENTES");
        panelConsultar.panelUsuarios.removeAll();
        for (Gerente g : listaGerentes) {
            if (!g.isStatus()) {
                String nombre = g.getNombre() + " " + g.getApellido();
                nombre = nombre.toLowerCase();
                if(nombre.contains(panelConsultar.txtBuscar.getText().toLowerCase())){
                    PanelUsuarioPendiente panelUP = new PanelUsuarioPendiente();
                    panelUP.setSize(310,80);
                    panelUP.labelUser.setText(g.getCorreo());
                    panelUP.labelUser.setVisible(false);
                    panelUP.botonUsuario.setText(g.getNombre() + " " + g.getApellido());
                    // Agregar un ActionListener al botón
                    panelUP.botonUsuario.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Actualizar el texto del JLabel
                            panelConsultar.labelCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/circuloRojo.png")));
                            panelConsultar.labelLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/lineaRoja.png")));
                            panelConsultar.labelStatus.setForeground(new java.awt.Color(249,94,94));
                            panelConsultar.labelStatus.setText("Esperando por ingresar");
                            panelConsultar.fondoDetalleUsuarioP.setVisible(true);
                            panelConsultar.labelCirculo.setVisible(true);
                            panelConsultar.labelLinea.setVisible(true);
                            panelConsultar.labelStatus.setVisible(true);
                            panelConsultar.iconoMail.setVisible(true);
                            panelConsultar.botonAceptar.setVisible(true);
                            panelConsultar.iconoAceptar.setVisible(true);
                            panelConsultar.fondoBotonAceptar.setVisible(true);
                            panelConsultar.botonRechazar.setVisible(true);
                            panelConsultar.iconoRechazar.setVisible(true);
                            panelConsultar.fondoBotonRechazar.setVisible(true);
                            usuarioPendiente = g.getCorreo();
                            panelConsultar.labelNombre.setText(g.getNombre() + " " + g.getApellido());
                            panelConsultar.labelCedula.setText(g.getCedula());
                            panelConsultar.labelMail.setText(g.getCorreo());
                        }
                    });
                    panelConsultar.panelUsuarios.add(panelUP);    
                }
            }
        }
        panelConsultar.panelUsuarios.revalidate();
        panelConsultar.panelUsuarios.repaint();
        panelConsultar.labelTItulo.setText("USUARIOS PENDIENTES");
    }
    
    public void resetearCamposEditarUsuario(){
        panelEditarU.txtNombre.setForeground(new Color(153,153,153));
        panelEditarU.txtNombre.setText("Nombre");
        panelEditarU.txtApellido.setForeground(new Color(153,153,153));
        panelEditarU.txtApellido.setText("Apellido");
        panelEditarU.txtUsuario.setForeground(new Color(153,153,153));
        panelEditarU.txtUsuario.setText("Username");
        panelEditarU.txtCorreo.setForeground(new Color(153,153,153));
        panelEditarU.txtCorreo.setText("Correo");
        panelEditarU.comboBoxMunicipio.setSelectedIndex(0);
        panelEditarU.spinnerEdad.setModel(new javax.swing.SpinnerNumberModel(18, 18, 90, 1));
        panelEditarU.comboBoxSexo.setSelectedIndex(1);
        panelEditarU.txtCedula.setForeground(new Color(153,153,153));
        panelEditarU.txtCedula.setText("Cédula");
        panelEditarU.txtNumero.setForeground(new Color(153,153,153));
        panelEditarU.txtNumero.setText("Número");
    }
    
    public void resetearCamposConsultarUsuario(){
        panelConsultar.labelUser.setText("");
        panelConsultar.labelNombre.setText("");
        panelConsultar.labelCedula.setText("");
        panelConsultar.labelMail.setText("");
        panelConsultar.labelMunicipio.setText("");
        panelConsultar.labelSexo.setText("");
        panelConsultar.labelEdad.setText("");
        panelConsultar.labelTelefono.setText("");
        panelConsultar.labelRoles.setText("");
        panelConsultar.labelFotoPerfil.setIcon(null);
    }
    
    public void mandarCorreoRespuestaSolicitud(boolean aceptado, Gerente g){
        String respuesta;
        if(aceptado){
            respuesta = "aceptada";
        } else {
            respuesta = "recchazada";
        }
        try {  
            String mensaje="Hola " + g.getNombre() + " " + g.getApellido() + "! Tu solicitud fue "+ respuesta +" por el administrador."
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
                JOptionPane.showMessageDialog(null, "Espere un momento... Se enviará un correo al gerente para notificar su decisión","Mensaje", 1);
                try (Transport transportar = sesion.getTransport("smtp")) {
                    transportar.connect(correoRestaurant,claveRestaurant);
                    transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                }
                JOptionPane.showMessageDialog(null, "Correo enviado con éxito. Puede continuar con sus procesos en la aplicación","Mensaje", 1);
            } catch (HeadlessException | MessagingException ex) {
            }     
        } catch (NullPointerException ne){

        }
    }
    
    public boolean camposVaciosEditarUsuario(){
        return panelEditarU.txtNombre.getText().equals("Nombre") || panelEditarU.txtNombre.equals("")
                ||panelEditarU.txtApellido.getText().equals("Apellido") || panelEditarU.txtApellido.getText().equals("")
                ||panelEditarU.txtUsuario.getText().equals("Username") || panelEditarU.txtUsuario.getText().equals("")
                ||panelEditarU.txtNombre.getText().equals("Correo") || panelEditarU.txtCorreo.getText().equals("")
                ||panelEditarU.comboBoxMunicipio.getSelectedIndex() == 0
                ||panelEditarU.txtCedula.getText().equals("Cédula") || panelEditarU.txtCedula.getText().equals("")
                ||panelEditarU.txtNumero.getText().equals("Número") || panelEditarU.txtNumero.getText().equals("");
    }
    
    public void reestablecerColoresPanelEditar(){
        panelEditarU.fondoEditarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/BotonEditarFoto.png")));
        panelEditarU.botonGestionarUsuarios.setForeground(new Color(230,231,235));
        panelEditarU.fondoBotonGestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonGestion.png")));
        panelEditarU.botonReestablecer.setForeground(Color.lightGray);
        panelEditarU.fondoBotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonGuardar.png")));
    }
    
    public void reestablecerColoresPanelConsultar(){
        panelConsultar.fondoBotonRechazar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonRechazar.png")));
        panelConsultar.fondoBotonAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonAceptar.png")));
        panelConsultar.fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoRegresar.png")));
        panelConsultar.fondoBotonAsignar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonAsignar.png")));
        panelConsultar.fondoBotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/BotonEliminarPresionado.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BOTONES DEL PANEL: panelSistema
        
        //Boton icono de Usuario
        if(e.getSource()==panelSistema.botonUsuario){
            resetearCamposEditarUsuario();
            reestablecerColoresPanelEditar();
            cargarDatosUsuario();
            Gerente g = gerente.buscarGerente(listaGerentes, indicadorUsuario);
            gerente.setCorreo(g.getCorreo());
            panelEditarU.labelNombreUsuario.setText(g.getNombre() + " " + g.getApellido());
            panelEditarU.labelRol.setText(g.getRol());
            if(g.getRol().equals("Administrador")){
                panelEditarU.fondoBotonGestion.setVisible(true);
                panelEditarU.botonGestionarUsuarios.setVisible(true);
            } else {
                panelEditarU.fondoBotonGestion.setVisible(false);
                panelEditarU.botonGestionarUsuarios.setVisible(false);
            }
            panelEditarU.setSize(926,720);
            panelEditarU.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelEditarU);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: panelEditarU
        
        //Boton gestionar usuarios
        if(e.getSource()==panelEditarU.botonGestionarUsuarios){
            resetearCamposConsultarUsuario();
            if(panelConsultar.comboBoxUsuario.getSelectedItem().toString().equals("Usuarios Activos")){
                cargarGerentesActivos();
            }
            if(panelConsultar.comboBoxUsuario.getSelectedItem().toString().equals("Usuarios Pendientes")){
                cargarGerentesPendientes();
            }
            panelConsultar.jScrollPane.getViewport().setOpaque(false);
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource() == panelEditarU.botonEditarFoto){
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);
            Component frame = null;
            int returnVal = fileChooser.showOpenDialog(frame); // Mostrar ventana para seleccionar archivo

            if(returnVal == JFileChooser.APPROVE_OPTION){ // Si el usuario selecciona un archivo
                File archivo = fileChooser.getSelectedFile();
                ImageIcon iconoOriginal = new ImageIcon(archivo.getPath());
        
                // Escalar la imagen a un tamaño específico
                int ancho = panelEditarU.fotoPerfil.getWidth();
                int alto = panelEditarU.fotoPerfil.getHeight();
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        
                // Crear una imagen de máscara circular
                BufferedImage imagenMascara = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2dMascara = imagenMascara.createGraphics();
                g2dMascara.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2dMascara.setColor(Color.WHITE);
                g2dMascara.fill(new Ellipse2D.Float(0, 0, ancho, alto));
                g2dMascara.setColor(Color.BLACK);
                g2dMascara.setStroke(new BasicStroke(1));
                g2dMascara.draw(new Ellipse2D.Float(0, 0, ancho, alto));
                g2dMascara.dispose();
        
                // Crear un nuevo ImageIcon a partir de la imagen escalada con la máscara circular
                BufferedImage imagenFinal = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2dFinal = imagenFinal.createGraphics();
                g2dFinal.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2dFinal.setClip(new Ellipse2D.Float(0, 0, ancho, alto));
                iconoEscalado.paintIcon(null, g2dFinal, 0, 0);
                g2dFinal.dispose();
                ImageIcon iconoFinal = new ImageIcon(imagenFinal);
        
                panelEditarU.fotoPerfil.setIcon(iconoFinal); // Asignar el icono al JLabel
            }
        }
        
        if(e.getSource()==panelEditarU.botonReestablecer){
            frameRes.fondoBotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonGuardarA.png")));
            frameRes.setTitle("Restaurant Manager");
            frameRes.setSize(355,485); 
            frameRes.setLocationRelativeTo(null);
            frameRes.setResizable(false);
            frameRes.setVisible(true);
        }
        
        if(e.getSource()==panelEditarU.botonGuardar){
            if(!camposVaciosEditarUsuario()){
                Gerente ge = gerente.buscarGerente(listaGerentes, gerente.getCorreo());
                String claveAnterior = ge.getCorreo();
                String userAnterior = ge.getUsername();
                String cedulaAnterior = ge.getCedula();
                String user = panelEditarU.txtUsuario.getText();
                String cedula = panelEditarU.txtCedula.getText();
                String nombre = panelEditarU.txtNombre.getText();
                String apellido = panelEditarU.txtApellido.getText();
                String ubicacion = panelEditarU.comboBoxMunicipio.getSelectedItem().toString();
                String correo = panelEditarU.txtCorreo.getText();
                indicadorUsuario = correo;
                String telefono = panelEditarU.txtNumero.getText();
                int edad = (int) panelEditarU.spinnerEdad.getValue();
                Icon icono = panelEditarU.fotoPerfil.getIcon();
                Image imagen = ((ImageIcon) icono).getImage();
                BufferedImage imagenBuf = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_RGB);
                Graphics g = imagenBuf.createGraphics();
                g.drawImage(imagen,0,0,null);
                g.dispose();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    ImageIO.write(imagenBuf, "jpg", baos);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                byte[] imagenBytes = baos.toByteArray();
                ImageIcon fPerfil = new ImageIcon(imagen);
                char sexo = panelEditarU.comboBoxSexo.getSelectedItem().toString().charAt(0);
                if(gerente.validarDatosUsuario(user, correo, cedula, nombre, edad, sexo, ubicacion, apellido, telefono,listaGerentes, userAnterior, claveAnterior, cedulaAnterior)){
                    gerente.modificarGerente(claveAnterior, listaGerentes, user, fPerfil, cedula, nombre, apellido, edad, sexo, ubicacion, correo, telefono);
                    indicadorUsuario = correo;
                    String SQL = "UPDATE Gerente SET cedula=?, nombre=?, apellido=?, telefono=?, edad=?, ubicacion=?, sexo=?, correo=?, username=?, icono=? WHERE correo=?";
                    try{
                        PS=CN.getConnection().prepareStatement(SQL);                       
                        PS.setString(1, cedula);
                        PS.setString(2, nombre);
                        PS.setString(3, apellido);
                        PS.setString(4, telefono);
                        PS.setInt(5, edad);
                        PS.setString(6, ubicacion);
                        PS.setString(7, String.valueOf(sexo));
                        PS.setString(8, correo);
                        PS.setString(9, user);
                        PS.setBytes(10, imagenBytes);
                        PS.setString(11, claveAnterior);
                        int res = PS.executeUpdate();
                        if (res > 0){
                            JOptionPane.showMessageDialog(null, "El usuario ha sido modificado con éxito", "", 1);
                        }
                    }catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Error al modificar el usuario en la base de datos: " +ex.getMessage(), "Error", 0);
                    }finally{
                        PS=null;
                        CN.desconectar();
                    }
                    resetearCamposEditarUsuario();
                    reestablecerColoresPanelEditar();
                    cargarDatosUsuario();    
                }    
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos correctamente antes de modficar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //BOTONES DEL PANEL: panelConsultarUsuarios
        
        //Boton Aceptar
        if(e.getSource()==panelConsultar.botonAceptar){
            if(usuarioPendiente.isEmpty()){
                JOptionPane.showMessageDialog(null, "Seleccione a un gerente para aceptar su ingreso", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                Gerente g = gerente.buscarGerente(listaGerentes, usuarioPendiente);
                int conf = JOptionPane.showOptionDialog(null, "¿Acepta el ingreso de "+ g.getNombre() + " " + g.getApellido() +" al sistema?","Gestión de usuarios",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if (conf == 0) {
                    for (Gerente ge: listaGerentes){
                        if(ge.equals(g)){
                            ge.setStatus(true);
                            try{
                                String gerenteUbicado = g.getCorreo();
                                String SQL = "UPDATE Gerente SET status='"+1+"' WHERE correo='"+gerenteUbicado+"'";
                                PS=CN.getConnection().prepareStatement(SQL);                       
                                int res = PS.executeUpdate();
                                if (res > 0){
                                    JOptionPane.showMessageDialog(null, "El usuario ha sido modificado con éxito", "", 1);
                                }
                                mandarCorreoRespuestaSolicitud(true,g);
                            } catch(SQLException ex){
                                                    System.out.println(ex);
                                JOptionPane.showMessageDialog(null, "Error al modificar el usuario en la base de datos: " +ex.getMessage(), "Error", 0);
                            }
                            finally{
                                PS=null;
                                CN.desconectar();
                            }
                            break;
                        }
                    }
                    cargarGerentesPendientes();
                }
            }
        }
        
        //Boton Rechazar
        if(e.getSource()==panelConsultar.botonRechazar){
            if(usuarioPendiente.isEmpty()){
                JOptionPane.showMessageDialog(null, "Seleccione a un gerente para rechazar su ingreso", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                Gerente g = gerente.buscarGerente(listaGerentes, usuarioPendiente);
                int conf = JOptionPane.showOptionDialog(null, "¿Rechaza el ingreso de "+ g.getNombre() + " " + g.getApellido() +" al sistema?","Gestión de usuarios",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if (conf == 0) {
                    String gerenteUbicado = g.getCorreo();
                    try {
                        String SQL = "DELETE from Gerente WHERE correo = '" + gerenteUbicado +"'";
                        int res = 0;
                            PS = CN.getConnection().prepareStatement(SQL);
                            res = PS.executeUpdate();
                            if (res>0){
                                JOptionPane.showMessageDialog(null, "El empleado ha sido eliminado con éxito", "", 1);
                            }
                            mandarCorreoRespuestaSolicitud(true,g);
                            listaGerentes.remove(g);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el empleado en la base de datos: " +ex.getMessage(), "Error", 0);
                    } finally {
                        PS = null;
                        CN.desconectar();
                    }
                    cargarGerentesPendientes();
                }
            }
        }
        
        if(e.getSource()==panelConsultar.botonEliminar){
            if(usuarioActivo.isEmpty()){
                JOptionPane.showMessageDialog(null, "Seleccione a un gerente para eliminarlo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                Gerente g = gerente.buscarGerente(listaGerentes, usuarioActivo);
                int conf = JOptionPane.showOptionDialog(null, "¿Desea eliminar a "+ g.getNombre() + " " + g.getApellido() +" del sistema?","Gestión de usuarios",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if (conf == 0) {
                    try{
                        String gerenteUbicado = g.getCorreo();
                        gerente.eliminarGerente(listaGerentes, gerenteUbicado);
                        String SQL = "DELETE from Gerente WHERE correo='"+gerenteUbicado+"'";
                        PS=CN.getConnection().prepareStatement(SQL);                       
                        int res = PS.executeUpdate();
                        if (res > 0){
                            JOptionPane.showMessageDialog(null, "El gerente ha sido eliminado satisfactoriamente", "", 1);
                        }
                    } catch(SQLException ex){
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(null, "Error al eliminar el usuario en la base de datos: " +ex.getMessage(), "Error", 0);
                    } finally{
                        PS=null;
                        CN.desconectar();
                    }
                    cargarGerentesActivos();
                }
            }
        }
        
        if(e.getSource()==panelConsultar.botonAsignar){
            
            if(usuarioActivo.isEmpty()){
                JOptionPane.showMessageDialog(null, "Seleccione a un gerente para asignarle un o varios roles", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                Gerente g = gerente.buscarGerente(listaGerentes, usuarioActivo);
                boolean resp[] = g.getResp();
                frameAsignar.botonReservaciones.setSelected(resp[0]);
                frameAsignar.botonEmpleados.setSelected(resp[1]);
                frameAsignar.botonInventario.setSelected(resp[2]);
                frameAsignar.botonMenu.setSelected(resp[3]);
                frameAsignar.fondoBotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonGuardarA.png")));
                frameAsignar.setTitle("Restaurant Manager");
                frameAsignar.setSize(355,485); 
                frameAsignar.setLocationRelativeTo(null);
                frameAsignar.setResizable(false);
                frameAsignar.setVisible(true);    
            }
            
        }
        
        //Boton Regresar
        if(e.getSource()==panelConsultar.botonRegresar){
            resetearCamposEditarUsuario();
            reestablecerColoresPanelEditar();
            cargarDatosUsuario();
            panelEditarU.setSize(926,720);
            panelEditarU.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelEditarU);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL FRAME: frameRes
        
        if(e.getSource()==frameRes.botonCambiar){
            Gerente g = gerente.buscarGerente(listaGerentes, indicadorUsuario);
            if(String.valueOf(frameRes.txtPassActual.getPassword()).equals(g.getClave())){
                if(String.valueOf(frameRes.txtPassActual.getPassword()).equals(String.valueOf(frameRes.txtPassNueva.getPassword()))){
                    JOptionPane.showMessageDialog(null, "La clave nueva no puede ser igual a la anterior", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    if(String.valueOf(frameRes.txtPassNueva.getPassword()).equals(String.valueOf(frameRes.txtPassConfirmada.getPassword()))){
                        String claveNueva = String.valueOf(frameRes.txtPassConfirmada.getPassword());
                        gerente.modificarClave(g.getCorreo(), listaGerentes, claveNueva);
                        try{
                            String SQL = "UPDATE Gerente SET clave='"+claveNueva+"' WHERE correo='"+g.getCorreo()+"'";
                            PS=CN.getConnection().prepareStatement(SQL);                       
                            int res = PS.executeUpdate();
                            if (res > 0){
                                JOptionPane.showMessageDialog(null, "Se ha modificado la clave satisfactoriamente", "", 1);
                                frameRes.dispose();
                            }
                        } catch(SQLException ex){
                            System.out.println(ex);
                            JOptionPane.showMessageDialog(null, "Error al modificar la clave en la base de datos: " +ex.getMessage(), "Error", 0);
                        } finally{
                            PS=null;
                            CN.desconectar();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La confirmación de la clave no coincide con la nueva clave", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "La clave actual no coincide con su clave", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //BOTONES DEL FRAME: frameAsignar
        
        if(e.getSource() == frameAsignar.botonGuardar){
            if(frameAsignar.botonReservaciones.isSelected() || frameAsignar.botonEmpleados.isSelected() ||
               frameAsignar.botonInventario.isSelected() || frameAsignar.botonMenu.isSelected()){
                boolean resp[] = new boolean[4];
                int reservacion = 0;
                int empleado = 0;
                int inventario = 0;
                int menu = 0;
                resp[0] = frameAsignar.botonReservaciones.isSelected();
                if(resp[0]){
                    reservacion = 1;
                } 
                resp[1] = frameAsignar.botonEmpleados.isSelected();
                if(resp[1]){
                    empleado = 1;
                }
                resp[2] = frameAsignar.botonInventario.isSelected();
                if(resp[2]){
                    inventario = 1;
                }
                resp[3] = frameAsignar.botonMenu.isSelected();
                if(resp[3]){
                    menu = 1;
                }
                gerente.modificarResp(usuarioActivo, listaGerentes, resp);
                try{
                    String SQL = "UPDATE Gerente SET reservacion='"+reservacion+"',empleado='"+empleado+"',inventario='"+inventario+"',menu='"+menu+"' WHERE correo='"+usuarioActivo+"'";
                    PS=CN.getConnection().prepareStatement(SQL);                       
                    int res = PS.executeUpdate();
                    if (res > 0){
                        JOptionPane.showMessageDialog(null, "Se ha modificado el(los) rol(es) satisfactoriamente", "", 1);
                        frameAsignar.dispose();
                    }
                } catch(SQLException ex){
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Error al modificar el(los) rol(es) en la base de datos: " +ex.getMessage(), "Error", 0);
                } finally{
                    PS=null;
                    CN.desconectar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione al menos un rol", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if ( e.getStateChange() == ItemEvent.SELECTED ){
            if (e.getItem().toString().equals("Usuarios Activos")) {
                panelConsultar.txtBuscar.setText("");
                cargarGerentesActivos();
            }
            
            if (e.getItem().toString().equals("Usuarios Pendientes")) {
                panelConsultar.txtBuscar.setText("");
                cargarGerentesPendientes();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        panelConsultar.txtBuscar.addKeyListener(new KeyAdapter (){
            @Override
            public void keyReleased(KeyEvent e) {
                if(panelConsultar.comboBoxUsuario.getSelectedItem().toString().equals("Usuarios Activos")){
                    cargarGerentesActivos();
                }
                if(panelConsultar.comboBoxUsuario.getSelectedItem().toString().equals("Usuarios Pendientes")){
                    cargarGerentesPendientes();
                }
            }      
        }); 
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
