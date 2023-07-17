/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import view.viewempleados.PanelConsultarEmpleados;
import view.viewempleados.PanelEmpleado;
import view.viewempleados.PanelIngresarEmpleado;
import view.viewprincipal.SistemaPrincipal;
import model.modelempleado.Empleado;
import model.conexionBD.ConexionServidor;
import model.modelempleado.DatosPago;
import model.modelempleado.NominaPago;
import view.viewempleados.PanelConsultarNominas;
import view.viewempleados.PanelIngresarNomina;
import view.viewempleados.PanelNomina;
/**
 *
 * Desarrollo: Diego Vellojin | Intefaces: Estefany Torres
 */
public final class ControllerEmpleados implements ActionListener, ItemListener, KeyListener, ChangeListener{
    
    // Atributos del Controller Empleados de los paneles y frames(ventanas) que posee
    private final SistemaPrincipal panelSistema;
    private final PanelConsultarEmpleados panelConsultar = new PanelConsultarEmpleados();
    private final PanelIngresarEmpleado panelIngresar = new PanelIngresarEmpleado();
    private final PanelConsultarNominas panelConsultarN = new PanelConsultarNominas();
    private final PanelIngresarNomina panelIngresarN = new PanelIngresarNomina();
    
    //Objeto de tipo Empleado
    private final Empleado emp = new Empleado();
    private final NominaPago np = new NominaPago();
    private String indicadorCedula = "";
    
    //Objeto tipo lista de Empleados
    private static final ArrayList<Empleado>  listaEmpleados = new ArrayList<>();
    private static final ArrayList<NominaPago> listaNominas = new ArrayList<>();
    
    // Atributos de comandos para utilizar las tablas de la Base de Datos (BD):
    // Comandos para Tabla Empleado
    private final String SQL_INSERT = "INSERT INTO Empleado (cedula,nombre,apellido,telefono,correo,edad,ubicacion,sexo,rol,sueldo,banco,nrocuentabancaria,fechaingreso,icono) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private PreparedStatement PS;
    
    // Atributo de objeto para conectar al servidor de MySQL
    private final ConexionServidor CN;
    private final String SQL_SELECT = "SELECT * FROM Empleado";
    private DefaultTableModel DT;
    private ResultSet RS;
    private DefaultTableModel modeloEmpleado,modeloNomina;
    
    //Atributos para manejo de fechas
    private final Calendar calendar = Calendar.getInstance();
    private final Date fechaActual; 
    
    //Constructor del ControllerEmpleados
    public ControllerEmpleados(SistemaPrincipal panelSistema) {
        // Valores predeterminados de tiempo y dia para atributos de fecha actual
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);  
        calendar.set(Calendar.MILLISECOND, 0);
        fechaActual = calendar.getTime();
        //Ventana del sistema
        this.panelSistema = panelSistema;
        this.panelSistema.botonEmpleados.addActionListener(this);
        //Panel de Consultar Empleados
        panelConsultar.jScrollPane.getViewport().setOpaque(false);
        panelConsultar.botonIngresar.addActionListener(this);
        panelConsultar.botonEditar.addActionListener(this);
        panelConsultar.botonNomina.addActionListener(this);
        panelConsultar.botonEliminar.addActionListener(this);
        panelConsultar.comboBoxRolEmpleado.addItemListener(this);
        panelConsultar.txtBuscar.addKeyListener(this);
        panelConsultar.labelTItulo.setText("COCINEROS");  
        panelConsultar.panelEmpleados.removeAll();
        //Panel de Ingresar Empleado
        panelIngresar.jDateFechaIngreso.setDate(fechaActual);
        panelIngresar.botonRegistrar.addActionListener(this);
        panelIngresar.botonGuardarCambios.addActionListener(this);
        panelIngresar.botonRegresar.addActionListener(this);
        panelIngresar.botonCambiarFoto.addActionListener(this);
        //Panel de Consultar Nominas
        panelConsultarN.botonGenerar.addActionListener(this);
        panelConsultarN.botonRegresar.addActionListener(this);
        panelConsultarN.comboBoxMes.addItemListener(this);
        panelConsultarN.spinnerAgno.addChangeListener(this);
        //Panel de Ingresar Nomina
        panelIngresarN.botonCrear.addActionListener(this);
        panelIngresarN.botonRegresar.addActionListener(this);
        // Comandos de consulta de datos de la BD
        PS = null;
        CN= new ConexionServidor();
        //Método para ingresar los datos de la BD al programa
        setDatos();
        
    }
    //Método que vacia los campos del panel consultar
    public void resetearCamposConsultarUsuario(){
        panelConsultar.labelNombre.setText("");
        panelConsultar.labelCedula.setText("");
        panelConsultar.labelMail.setText("");
        panelConsultar.labelMunicipio.setText("");
        panelConsultar.labelSexo.setText("");
        panelConsultar.labelEdad.setText("");
        panelConsultar.labelTelefono.setText("");
        panelConsultar.labelSueldo.setText("");
        panelConsultar.labelFechaIngreso.setText("");
        panelConsultar.labelBanco.setText("");
        panelConsultar.labelNumeroCuenta.setText("");
        panelConsultar.labelFoto.setIcon(null);
    }
    //Método que verifica si hay empleados de un rol específico
    public boolean existenEmpleados(String rol){
        for (Empleado em : listaEmpleados) {
            if (em.getRol().equals(rol)) {
                return true;
            }
        }
        return false;
    }
    
    //Consultar Base de Datos
    // Establece columnas para la tabla de Empleado en el programa
    private DefaultTableModel setColumnas(){
        DT = new DefaultTableModel();
        DT.addColumn("cedula");
        DT.addColumn("nombre");
        DT.addColumn("apellido");
        DT.addColumn("telefono");
        DT.addColumn("correo");
        DT.addColumn("edad");
        DT.addColumn("ubicacion");
        DT.addColumn("sexo");
        DT.addColumn("rol");
        DT.addColumn("sueldo");
        DT.addColumn("banco");
        DT.addColumn("nrocuentabancaria");
        DT.addColumn("fechaingreso");
        DT.addColumn("icono");
        return DT;
    }
    /* Obtiene los datos de cada Empleado de la tabla de la BD y los 
       registra en la lista de Empleados del programa */
    public DefaultTableModel setDatos(){
        try{
            // Establece modelo de tabla
            setColumnas();
            // Conexion a BD para obtener informacion de tabla Empleado
            PS = CN.getConnection().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            // Busca datos de cada columna
            while (RS.next()){
                String cedula = RS.getString(1);
                String nombre = RS.getString(2);
                String apellido = RS.getString(3);
                String telefono = RS.getString(4);
                String correo = RS.getString(5);
                int edad = RS.getInt(6);
                String ubicacion = RS.getString(7);
                String sSexo = RS.getString(8);
                char sexo = sSexo.charAt(0);
                String rol = RS.getString(9);
                Double sueldo = RS.getDouble(10);
                String banco = RS.getString(11);               
                String numeroCuentaBancaria = RS.getString(12);
                Date fechaIngreso = new java.util.Date(RS.getDate(13).getTime());
                byte[] imagedata = RS.getBytes(14);
                ImageIcon icono = new ImageIcon("/images/imagesviewusuarios/fotoPerfil.png"); // valor predeterminado
                if (imagedata != null && imagedata.length > 0) {
                    icono = new ImageIcon(imagedata); // valor correcto
                } else {
                    icono = null; // asignar null si no hay datos de imagen disponibles
                }
                // Crea objeto Empleado y lo agrega a su lista
                Empleado empleado = new Empleado(rol, sueldo, fechaIngreso, numeroCuentaBancaria, banco, cedula, nombre, apellido, edad, sexo, ubicacion, correo, telefono, icono);
                listaEmpleados.add(empleado);
            }
        }
        // Excepcion si no se pudo listar las tablas
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar los datos del empleado: " +e.getMessage(), "Error", 0);
        }
        // Finaliza conexion con BD
        finally{
            PS=null;
            RS=null;
            CN.desconectar();
        }
        return DT;
    }
    
    public void cargarNominas(int monthA, int yearA){
        panelConsultarN.jScrollPane1.setVisible(false);
        panelConsultarN.tablaNominas.setVisible(false);
        String datos[][] = {};
        String columna [] = {"Cédula","Nombre","Rol","Sueldo diario","Días trabajados","Neto a cobrar"};
        modeloNomina = new DefaultTableModel(datos, columna){
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex) {return false;}
        };
        panelConsultarN.tablaNominas.setModel(modeloNomina);
        panelConsultarN.tablaNominas.getTableHeader().setReorderingAllowed(false);
        panelConsultarN.panelNóminas.removeAll();
        for (NominaPago np: listaNominas){
            PanelNomina panelN = new PanelNomina();
            panelN.setSize(246, 80);
            String fechaInicio = np.getFechaInicio();
            String fechaCierre = np.getFechaCierre();
            String fechaNomina = fechaInicio + " al " + fechaCierre;
            panelN.labelFecha.setText(fechaNomina);
            panelN.botonNomina.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modeloNomina.setRowCount(0);
                    panelConsultarN.jScrollPane1.setVisible(true);
                    panelConsultarN.tablaNominas.setVisible(true);
                    ArrayList<DatosPago> listaPagos = np.getListaPagos();
                    for (int i=0; i<listaPagos.size();i++) {
                        DatosPago dp = listaPagos.get(i);
                        modeloNomina.insertRow(0, columna);
                        modeloNomina.setValueAt(dp.getCedula(), 0, 0);
                        modeloNomina.setValueAt(dp.getNombre(), 0, 1);
                        modeloNomina.setValueAt(dp.getRol(), 0, 2);
                        modeloNomina.setValueAt(dp.getSueldoDiario(), 0, 3); 
                        modeloNomina.setValueAt(dp.getDiasTrabajo(), 0, 4);
                        modeloNomina.setValueAt(dp.getNetoACobrar(), 0, 5);
                    }
                }
            });
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fInicio = null;
            try {
                fInicio = formato.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(ControllerEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
            Date fCierre = null;
            try {
                fCierre = formato.parse(fechaCierre);
            } catch (ParseException ex) {
                Logger.getLogger(ControllerEmpleados.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Creating a calendar object
            Calendar cal = new GregorianCalendar();
            cal.setTime(fInicio);
            int monthI = cal.get(Calendar.MONTH) + 1;
            int yearI = cal.get(Calendar.YEAR);
            cal.setTime(fCierre);
            int monthC = cal.get(Calendar.MONTH) + 1;
            int yearC = cal.get(Calendar.YEAR);
            if(yearI == yearA || yearC == yearA){
                if(monthI == monthA || monthC == monthA){
                    panelConsultarN.panelNóminas.add(panelN);
                }
                if(monthA == 0){
                    panelConsultarN.panelNóminas.add(panelN);
                }
            }
        }
        panelConsultarN.panelNóminas.revalidate();
        panelConsultarN.panelNóminas.repaint();
    }
    
    // Metodo para que filtrar los Empleados por rol
    public void cargarEmpleados(String rol){
        resetearCamposConsultarUsuario();
        String opcionSeleccionada = rol;
        
        // Eliminar todos los paneles de empleado del contenedor
        panelConsultar.panelEmpleados.removeAll();
        
        panelConsultar.fondoDetalleEmpleado.setVisible(false);
        panelConsultar.jSeparator1.setVisible(false);
        panelConsultar.labelCirculo.setVisible(false);
        panelConsultar.labelLinea.setVisible(false);
        panelConsultar.labelRol.setVisible(false);
        panelConsultar.iconoMail.setVisible(false);
        panelConsultar.iconoMunicipio.setVisible(false);
        panelConsultar.iconoSexo.setVisible(false);
        panelConsultar.iconoEdad.setVisible(false);
        panelConsultar.iconoTelefono.setVisible(false);
        panelConsultar.iconoSueldo.setVisible(false);
        panelConsultar.iconoFechaIngreso.setVisible(false);
        panelConsultar.iconoBanco.setVisible(false);
        panelConsultar.botonEliminar.setVisible(false);
        panelConsultar.fondoBotonEliminar.setVisible(false);
        panelConsultar.botonEditar.setVisible(false);
        panelConsultar.fondoBotonEditar.setVisible(false);
        
        // Agregar los paneles de empleado correspondientes a la nueva opción seleccionada
        for (Empleado em : listaEmpleados) {
            if (em.getRol().equals(opcionSeleccionada)) {
                PanelEmpleado panelE = new PanelEmpleado();
                panelE.setSize(310,80);
                panelE.labelCedula.setText(em.getCedula());
                panelE.labelCedula.setVisible(false);
                panelE.botonEmpleado.setText(em.getNombre()+" "+em.getApellido());
                // Agregar un ActionListener al botón
                panelE.botonEmpleado.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Actualizar el texto del JLabel
                        indicadorCedula = em.getCedula();
                        panelConsultar.fondoDetalleEmpleado.setVisible(true);
                        panelConsultar.botonEliminar.setVisible(true);
                        panelConsultar.fondoBotonEliminar.setVisible(true);
                        panelConsultar.botonEditar.setVisible(true);
                        panelConsultar.fondoBotonEditar.setVisible(true);
                        panelConsultar.jSeparator1.setVisible(true);
                        panelConsultar.panelEmpleados.setVisible(true);
                        panelConsultar.labelCirculo.setVisible(true);
                        panelConsultar.labelLinea.setVisible(true);
                        panelConsultar.labelRol.setVisible(true);
                        panelConsultar.iconoMail.setVisible(true);
                        panelConsultar.iconoMunicipio.setVisible(true);
                        panelConsultar.iconoSexo.setVisible(true);
                        panelConsultar.iconoEdad.setVisible(true);
                        panelConsultar.iconoTelefono.setVisible(true);
                        panelConsultar.iconoSueldo.setVisible(true);
                        panelConsultar.iconoFechaIngreso.setVisible(true);
                        panelConsultar.iconoBanco.setVisible(true);
                        panelConsultar.labelNombre.setText(em.getNombre()+" "+em.getApellido());
                        panelConsultar.labelCedula.setText("CI: "+em.getCedula());
                        panelConsultar.labelRol.setText(em.getRol());
                        panelConsultar.labelMail.setText(em.getCorreo());
                        panelConsultar.labelMunicipio.setText(em.getUbicacion());                
                        if (em.getSexo() == 'M') {
                            panelConsultar.labelSexo.setText("Masculino");
                        }else{
                        panelConsultar.labelSexo.setText("Femenino");
                        }
                        panelConsultar.labelEdad.setText(String.valueOf(em.getEdad()));
                        panelConsultar.labelTelefono.setText(em.getTelefono());
                        panelConsultar.labelSueldo.setText(String.valueOf(em.getSueldo()));
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                        panelConsultar.labelFechaIngreso.setText(formatoFecha.format(em.getFechaDeIngreso()));
                        panelConsultar.labelBanco.setText(em.getBanco());
                        panelConsultar.labelNumeroCuenta.setText(em.getNumeroCuentaBancaria());
                        if(!(em.getIcono() == null)){
                            ImageIcon iconoOriginal = em.getIcono();
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
        
                            panelConsultar.labelFoto.setIcon(iconoFinal); // Asignar el icono al JLabel
                        }else{
                            panelConsultar.labelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fotoPerfil.png")));
                        }
                    }
                });
                String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                String nombre = (em.getNombre() + " " + em.getApellido()).toLowerCase();
                if(nombre.contains(entrada)){
                    panelConsultar.panelEmpleados.add(panelE);
                }
            }
        }
        // Actualizar la ventana
        panelConsultar.panelEmpleados.revalidate();
        panelConsultar.panelEmpleados.repaint();
    }
    
    public void actualizaNetoACobrar(TableModelEvent evento, int fila){
        if(evento.getColumn() != 4) {
          return;
        }
        if(panelIngresarN.tablaNominas.getValueAt(fila, 4).toString().matches("[0-9]+")){
            int dias = Integer.parseInt( panelIngresarN.tablaNominas.getValueAt(fila, 4).toString());
            double sueldo = Double.parseDouble(panelIngresarN.tablaNominas.getValueAt(fila, 3).toString()) ;
            double neto= dias * sueldo; 
            modeloEmpleado.setValueAt(neto, fila , 5);    
        } else {
            JOptionPane.showMessageDialog(null, "Sólo puede ingresar números en esta celda", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public void cargarEmpleadosNomina(){
        String datos[][] = {};
        String columna [] = {"Cédula","Nombre","Rol","Sueldo diario","Días trabajados","Neto a cobrar"};
        modeloEmpleado = new DefaultTableModel(datos, columna){
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        panelIngresarN.tablaNominas.setModel(modeloEmpleado);
        panelIngresarN.tablaNominas.getTableHeader().setReorderingAllowed(false);
        for (int i=0; i<listaEmpleados.size();i++){
            Empleado em = listaEmpleados.get(i);
            modeloEmpleado.insertRow(0, columna);
            modeloEmpleado.setValueAt(em.getCedula(), 0, 0); 
            modeloEmpleado.setValueAt(em.getNombre() + " " + em.getApellido(), 0, 1); 
            modeloEmpleado.setValueAt(em.getRol(), 0, 2);
            modeloEmpleado.setValueAt(em.getSueldo(), 0, 3);
            modeloEmpleado.setValueAt(0, 0, 4);
            modeloEmpleado.setValueAt(0.0, 0, 5);
        }
        modeloEmpleado.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evento) {
                int fila = panelIngresarN.tablaNominas.getSelectedRow();
                actualizaNetoACobrar(evento,fila);
            }
        });
        
        
    }
    
    //Método que valida si los campos en el panel Ingresar están vacios
     public boolean camposVaciosPanelIngresar () {
        return  (panelIngresar.txtNumeroCuenta.getText().equals("Número de cuenta") || panelIngresar.txtNumeroCuenta.getText().isEmpty() ||
                 panelIngresar.txtTelefono.getText().equals("Teléfono") || panelIngresar.txtTelefono.getText().isEmpty() ||
                 panelIngresar.txtNombre.getText().equals("Nombre") || panelIngresar.txtNombre.getText().isEmpty() ||
                 panelIngresar.txtApellido.getText().equals("Apellido") || panelIngresar.txtApellido.getText().isEmpty() ||
                 panelIngresar.txtCedula.getText().equals("Cédula") || panelIngresar.txtCedula.getText().isEmpty() || 
                 panelIngresar.txtCorreo.getText().equals("Correo") || panelIngresar.txtCorreo.getText().isEmpty()||
                 panelIngresar.jDateFechaIngreso.getDate() == null);
    }
    //Método que devuelve los valores a los campos del panel Ingresar
    public void resetearCamposPanelIngresar(){
        panelIngresar.txtNumeroCuenta.setForeground(Color.LIGHT_GRAY);
        panelIngresar.txtTelefono.setForeground(Color.LIGHT_GRAY);
        panelIngresar.txtNombre.setForeground(Color.LIGHT_GRAY);
        panelIngresar.txtApellido.setForeground(Color.LIGHT_GRAY);
        panelIngresar.txtCedula.setForeground(Color.LIGHT_GRAY);
        panelIngresar.txtCorreo.setForeground(Color.LIGHT_GRAY);
        panelIngresar.txtNumeroCuenta.setText("Número de cuenta");
        panelIngresar.txtTelefono.setText("Teléfono");
        panelIngresar.txtNombre.setText("Nombre");
        panelIngresar.txtApellido.setText("Apellido");
        panelIngresar.txtCedula.setText("Cédula");
        panelIngresar.txtCorreo.setText("Correo");
        panelIngresar.spinnerSueldo.setModel(new javax.swing.SpinnerNumberModel(20.0d, 20.0d, 100.0d, 1.0d));
        panelIngresar.jDateFechaIngreso.setDate(fechaActual);
        panelIngresar.spinnerEdad.setModel(new javax.swing.SpinnerNumberModel(30, 18, 90, 1));
        panelIngresar.comboBoxSexo.setSelectedIndex(0);
        panelIngresar.comboBoxMunicipio.setSelectedIndex(0);
        panelIngresar.comboBoxRol.setSelectedIndex(0);
        panelIngresar.comboBoxBanco.setSelectedIndex(0);
        panelIngresar.labelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/imagenUser.png")));                       
    }
    
    public void reestablecerColoresPanelConsultar(){
        panelConsultar.fondoBotonIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/botonIngresar.png")));
        panelConsultar.fondoBotonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/botonEditar.png")));
        panelConsultar.fondoBotonNomina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/botonNomina.png")));
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
    
    public int buscarRol (String rol){
        switch (rol){
            case "Cocinero": return 1;
            case "Bartender":return 2;
            case "Mesonero": return 3;
            case "Personal de servicio": return 4;
            default:
                return 0;
        }
    }

    public int buscarBanco (String banco){
        switch (banco) {
            case "BANCO OCCIDENTAL DE DESCUENTO CA.": return 1;
            case "BANCO SOFITASA." : return 2;
            case "BANCO PLAZA.": return 3;
            case "DELSUR BANCO UNIVERSAL.": return 4;
            case "FONDO COMUN BANCO UNIVERSAL.": return 5;
            case "BANCO NACIONAL DE CREDITO.": return 6;
            case "BANCO MERCANTIL.": return 7;
            case "BANCO VENEZOLANO DE CREDITO.": return 8;
            case "BANCO PROVINCIAL.": return 9;
            case "BANCO EXTERIOR.": return 10;
            case "BANESCO. BANCARIBE.": return 11;
            case "BANCO CARONI.": return 12;
            case "BANCO BICENTENARIO. ": return 13;
            case "100 POR CIENTO BANCO.": return 14;
            case "BANCO DEL TESORO.": return 15;
            case "BANPLUS BANCO COMERCIAL.": return 16;
            case "MIBANCO BANCO DE DESARROLLO.": return 17;
            case "BANCO ACTIVO.": return 18;
            case "BANCO DE VENEZUELA.": return 19;
            case "BANCRECER.": return 20;
            case "BANCAMIGA BANCO MICROFINANCIERO.": return 21;
            case "BANCO DE LA FUERZAS ARMADAS BANFANB": return 22;
            default:
                return 0;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BOTONES DEL PANEL: panelSistema
        
        //Boton icono de Empleado
        if(e.getSource()==panelSistema.botonEmpleados){
            reestablecerColoresPanelConsultar();
            cargarEmpleados(panelConsultar.comboBoxRolEmpleado.getSelectedItem().toString());
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: Consultar Empleados
        
        //Boton de Ingresar Empleado
        if (e.getSource()==panelConsultar.botonIngresar){
            panelIngresar.botonGuardarCambios.setVisible(false);
            panelIngresar.botonRegistrar.setVisible(true);
            panelIngresar.setSize(926,720);
            panelIngresar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelIngresar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource()==panelConsultar.botonNomina){
            int year = Integer.parseInt(panelConsultarN.spinnerAgno.getValue().toString());
            int mes = panelConsultarN.comboBoxMes.getSelectedIndex();
            cargarNominas(mes, year);
            panelConsultarN.setSize(926,720);
            panelConsultarN.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultarN);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource()==panelConsultar.botonEditar){
            Empleado empleado = emp.buscarEmpleado(indicadorCedula, listaEmpleados);
            panelIngresar.txtNombre.setForeground(new Color(230,231,235));
            panelIngresar.txtApellido.setForeground(new Color(230,231,235));
            panelIngresar.txtCorreo.setForeground(new Color(230,231,235));
            panelIngresar.txtCedula.setForeground(new Color(230,231,235));
            panelIngresar.txtTelefono.setForeground(new Color(230,231,235));
            panelIngresar.txtNumeroCuenta.setForeground(new Color(230,231,235));
            panelIngresar.txtNombre.setText(empleado.getNombre());
            panelIngresar.txtApellido.setText(empleado.getApellido());
            panelIngresar.txtCorreo.setText(empleado.getCorreo());
            panelIngresar.txtCedula.setText(empleado.getCedula());
            panelIngresar.txtTelefono.setText(empleado.getTelefono());
            panelIngresar.comboBoxMunicipio.setSelectedIndex(buscarMunicipio(empleado.getUbicacion()));
            panelIngresar.spinnerEdad.setModel(new javax.swing.SpinnerNumberModel(empleado.getEdad(), 18, 90, 1));
            if(!(empleado.getIcono() == null)){
                ImageIcon iconoOriginal = empleado.getIcono();
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
        
                panelIngresar.labelImagen.setIcon(iconoFinal); // Asignar el icono al JLabel
            }else{
                panelIngresar.labelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/imagenUser.png")));
            }
            if(empleado.getSexo() == 'F'){
                panelIngresar.comboBoxSexo.setSelectedIndex(0);
            } else {
                panelIngresar.comboBoxSexo.setSelectedIndex(1);
            }
            
            panelIngresar.comboBoxRol.setSelectedIndex(buscarRol(empleado.getRol()));
            panelIngresar.spinnerSueldo.setModel(new javax.swing.SpinnerNumberModel(empleado.getSueldo(), 20.0d, 100.0d, 1.0d));
            panelIngresar.jDateFechaIngreso.setDate(empleado.getFechaDeIngreso());
            panelIngresar.comboBoxBanco.setSelectedIndex(buscarBanco(empleado.getBanco()));
            panelIngresar.txtNumeroCuenta.setText(empleado.getNumeroCuentaBancaria());
            panelIngresar.botonGuardarCambios.setVisible(true);
            panelIngresar.botonRegistrar.setVisible(false);
            panelIngresar.setSize(926,720);
            panelIngresar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelIngresar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if (e.getSource() == panelConsultar.botonEliminar) {
            Empleado empleado = emp.buscarEmpleado(indicadorCedula, listaEmpleados);
            int op = JOptionPane.showOptionDialog(null, "¿Desea eliminar al empleado " + empleado.getNombre() + " " + empleado.getApellido() + "?", "Gestión de Empleados", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (op == 0) {
                try {
                    String SQL = "DELETE FROM Empleado WHERE cedula = ?";
                    PS = CN.getConnection().prepareStatement(SQL);
                    PS.setString(1, empleado.getCedula());
                    int res = PS.executeUpdate();
                    if (res > 0) {
                        listaEmpleados.remove(empleado);
                        cargarEmpleados(panelConsultar.comboBoxRolEmpleado.getSelectedItem().toString());
                        JOptionPane.showMessageDialog(null, "El empleado ha sido eliminado con éxito", "", 1);
            }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el empleado de la base de datos: " + ex.getMessage(), "Error", 0);
                } finally {
                    PS = null;
                    CN.desconectar();
                }
            }
        }
        
        //BOTONES DEL PANEL: Ingresar Empleado
        
        //Boton Registrar
        if(e.getSource()==panelIngresar.botonRegistrar){
            //Valida que los campos estén vacios y si no lo están crea las variables de los campos
            if(!camposVaciosPanelIngresar()){
                String nombre = panelIngresar.txtNombre.getText();
                String apellido = panelIngresar.txtApellido.getText();
                String correo = panelIngresar.txtCorreo.getText();
                String cedula = panelIngresar.txtCedula.getText();
                String telefono = panelIngresar.txtTelefono.getText();
                String cuenta = panelIngresar.txtNumeroCuenta.getText();
                //Valida si los campos ingresados están correctos
                if (emp.validarDatosEmpleado(nombre, apellido, cedula, correo, telefono, cuenta, listaEmpleados)){
                    Date fechaIngreso = panelIngresar.jDateFechaIngreso.getDate();
                    //Valida que la fecha de ingreso del empleado sea anterior a la fecha actual
                    if (fechaIngreso.after(fechaActual)){
                        JOptionPane.showMessageDialog(null, "La fecha de ingreso no puede ser una fecha posterior al dia de hoy", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String rol = panelIngresar.comboBoxRol.getSelectedItem().toString();
                        double sueldo = (double) panelIngresar.spinnerSueldo.getValue();
                        String banco = panelIngresar.comboBoxBanco.getSelectedItem().toString();
                        int edad = (int) panelIngresar.spinnerEdad.getValue();
                        char sexo = panelIngresar.comboBoxSexo.getSelectedItem().toString().charAt(0);
                        String ubicacion = panelIngresar.comboBoxMunicipio.getSelectedItem().toString();
                        Icon icono = panelIngresar.labelImagen.getIcon();
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
                        //Se crea el objeto tipo empleado                  
                        Empleado em = new Empleado(rol, sueldo, fechaIngreso, cuenta, banco, cedula, nombre, apellido, edad, sexo, ubicacion, correo, telefono, fPerfil);
                        //Registro a la Base de Datos
                        try{
                            java.sql.Date sqlDate = new java.sql.Date(fechaIngreso.getTime());
                            PS=CN.getConnection().prepareStatement(SQL_INSERT);
                            PS.setString(1, cedula);
                            PS.setString(2, nombre);
                            PS.setString(3, apellido);
                            PS.setString(4, telefono);
                            PS.setString(5, correo);
                            PS.setInt(6, edad);
                            PS.setString(7, ubicacion);
                            PS.setString(8,String.valueOf(sexo));
                            PS.setString(9, rol);
                            PS.setDouble(10, sueldo);
                            PS.setString(11,banco);
                            PS.setString(12, cuenta);
                                PS.setDate(13, sqlDate);
                            PS.setBytes(14, imagenBytes);
                            int res = PS.executeUpdate();
                            if (res > 0){
                                //Añade el objeto empleado a la lista, si logró cargar los atributos a la BD
                                listaEmpleados.add(em);
                                JOptionPane.showMessageDialog(null, "El empleado ha sido registrado con éxito", "", 1);
                            }
                        //Excepción de error al guaradar los datos
                        }catch(SQLException ex){
                            JOptionPane.showMessageDialog(null, "Error al guardar los datos del empleado en la base de datos: " +ex.getMessage(), "Error", 0);
                        }
                        // Finaliza conexion con BD
                        finally{
                            PS=null;
                            CN.desconectar();
                        }
                        cargarEmpleados(panelConsultar.comboBoxRolEmpleado.getSelectedItem().toString());
                        panelConsultar.setSize(926,720);
                        panelConsultar.setLocation(0,0);
                        panelSistema.panelPrincipal.removeAll();
                        panelSistema.panelPrincipal.add(panelConsultar);
                        panelSistema.panelPrincipal.revalidate();
                        panelSistema.panelPrincipal.repaint();
                        resetearCamposPanelIngresar();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }           
        }
        
        if(e.getSource()==panelIngresar.botonGuardarCambios){
            if(!camposVaciosPanelIngresar()){
                Empleado empleado = emp.buscarEmpleado(indicadorCedula, listaEmpleados);
                String nombre = panelIngresar.txtNombre.getText();
                String apellido = panelIngresar.txtApellido.getText();
                String correo = panelIngresar.txtCorreo.getText();
                String cedula = panelIngresar.txtCedula.getText();
                String telefono = panelIngresar.txtTelefono.getText();
                String cuenta = panelIngresar.txtNumeroCuenta.getText();
                if (emp.validarDatosEmpleado(nombre, apellido, cedula, correo, telefono, cuenta, listaEmpleados,empleado.getCedula(),empleado.getCorreo())){
                    Date fechaIngreso = panelIngresar.jDateFechaIngreso.getDate();
                    if (fechaIngreso.after(fechaActual)){
                        JOptionPane.showMessageDialog(null, "La fecha de ingreso no puede ser una fecha posterior al dia de hoy", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else { 
                        String rol = panelIngresar.comboBoxRol.getSelectedItem().toString();
                        double sueldo = (double) panelIngresar.spinnerSueldo.getValue();
                        String banco = panelIngresar.comboBoxBanco.getSelectedItem().toString();
                        int edad = (int) panelIngresar.spinnerEdad.getValue();
                        char sexo = panelIngresar.comboBoxSexo.getSelectedItem().toString().charAt(0);
                        String ubicacion = panelIngresar.comboBoxMunicipio.getSelectedItem().toString();
                        Icon icono = panelIngresar.labelImagen.getIcon();
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
                        // Actualizar el registro del empleado en la base de datos
                            String SQL = "UPDATE Empleado SET cedula=?, nombre=?, apellido=?, telefono=?, correo=?, edad=?, ubicacion=?, sexo=?, rol=?, sueldo=?, banco=?, nrocuentabancaria=?, fechaingreso=?, icono=? WHERE cedula=?";
                        try{
                            java.sql.Date sqlDate = new java.sql.Date(fechaIngreso.getTime());
                            PS=CN.getConnection().prepareStatement(SQL);
                            PS.setString(1, cedula);
                            PS.setString(2, nombre);
                            PS.setString(3, apellido);
                            PS.setString(4, telefono);
                            PS.setString(5, correo);
                            PS.setInt(6, edad);
                            PS.setString(7, ubicacion);
                            PS.setString(8,String.valueOf(sexo));
                            PS.setString(9, rol);
                            PS.setDouble(10, sueldo);
                            PS.setString(11,banco);
                            PS.setString(12, cuenta);
                            PS.setDate(13, sqlDate);
                            PS.setBytes(14, imagenBytes);
                            PS.setString(15, indicadorCedula);
                            int res = PS.executeUpdate();
                            if (res > 0){
                                emp.modificarEmpleado(rol, sueldo, fechaIngreso, cuenta, banco, cedula, nombre, apellido, edad, sexo, ubicacion, correo, telefono,fPerfil, listaEmpleados, indicadorCedula);
                                JOptionPane.showMessageDialog(null, "El empleado ha sido modificado con éxito", "", 1);
                            }
                        //Excepción de error al guaradar los datos
                        }catch(SQLException ex){
                            JOptionPane.showMessageDialog(null, "Error al guardar los nuevos datos del empleado en la base de datos: " +ex.getMessage(), "Error", 0);
                        }
                        // Finaliza conexion con BD
                        finally{
                            PS=null;
                            CN.desconectar();
                        }
                    }   
                }
            }
        }
        
        //Boton Subir foto
        if (e.getSource()==panelIngresar.botonCambiarFoto){
          JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);
            Component frame = null;
            int returnVal = fileChooser.showOpenDialog(frame); // Mostrar ventana para seleccionar archivo

            if(returnVal == JFileChooser.APPROVE_OPTION){ // Si el usuario selecciona un archivo
                File archivo = fileChooser.getSelectedFile();
                ImageIcon iconoOriginal = new ImageIcon(archivo.getPath());
        
                // Escalar la imagen a un tamaño específico
                int ancho = panelIngresar.labelImagen.getWidth();
                int alto = panelIngresar.labelImagen.getHeight();
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
        
                panelIngresar.labelImagen.setIcon(iconoFinal); // Asignar el icono al JLabel
            }  
        }
        
        //Boton Regresar
        if(e.getSource()==panelIngresar.botonRegresar){
            resetearCamposPanelIngresar();
            reestablecerColoresPanelConsultar();
            cargarEmpleados(panelConsultar.comboBoxRolEmpleado.getSelectedItem().toString());
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: Ingresar Nomina
        
        if(e.getSource()==panelIngresarN.botonCrear){
            if(panelIngresarN.jDateFechaInicio == null || panelIngresarN.jDateFechaCierre == null){
                JOptionPane.showMessageDialog(null, "Coloque las fechas correctamente antes de crear", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                Date fechaInicio = panelIngresarN.jDateFechaInicio.getDate();
                Date fechaCierre = panelIngresarN.jDateFechaCierre.getDate();
                Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                String fIni = formatter.format(fechaInicio);
                String fCie = formatter.format(fechaCierre);
                if(!np.nominaRepetida(listaNominas, fIni,fCie)){
                    if(fechaInicio.after(fechaCierre) || fechaInicio.equals(fechaCierre)){
                        JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser igual o posterior a la fecha de Cierre", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else{
                        Calendar c = Calendar.getInstance();
                        c.setTime(fechaInicio);
                        int dia = c.get(Calendar.DAY_OF_WEEK);
                        if(dia == 1){
                            c.add(Calendar.DAY_OF_YEAR, 7);
                            Date fechaEsperada = c.getTime();
                            String fechaE = formatter.format(fechaEsperada);
                            if(fechaEsperada.equals(fechaCierre)){
                                boolean flag = false, seguir = false;
                                for (int i = 0; i<listaEmpleados.size(); i++){
                                    if(Integer.parseInt(panelIngresarN.tablaNominas.getValueAt(i, 4).toString()) > 7){
                                        flag = true;
                                        break;
                                    }
                                } 
                                if(flag){
                                    JOptionPane.showMessageDialog(null, "Los días trabajados no pueden pasar de 7", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    boolean vacio = false;
                                    int cantidad = 0;
                                    for (int i = 0; i<listaEmpleados.size(); i++){
                                        if(Integer.parseInt(panelIngresarN.tablaNominas.getValueAt(i, 4).toString()) == 0){
                                            cantidad++;
                                        }
                                    }
                                    if(cantidad == listaEmpleados.size()){
                                        JOptionPane.showMessageDialog(null, "Debe haber al menos un empleado con días trabajados", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        boolean flag2 = false;
                                        for (int i = 0; i<listaEmpleados.size(); i++){
                                            if(panelIngresarN.tablaNominas.getValueAt(i, 4).toString().equals("0")){
                                                flag2=true;
                                                break;
                                            }
                                        }
                                        if(flag2){
                                            int conf = JOptionPane.showOptionDialog(null, "¿Desea crear la Nómina de Pago con algunos empleados con días trabajados igual a 0?","Manejo de Nóminas de Pago",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                                            if (conf == 0) {
                                                seguir = true;
                                            } else {
                                                seguir = false;
                                            }                                        
                                        }else {
                                            seguir = true;
                                        }
                                        if(seguir){
                                            ArrayList<DatosPago> listaPagos = new ArrayList<>();
                                            for (int i = 0; i<listaEmpleados.size(); i++){
                                                int j = 0;
                                                String cedula = panelIngresarN.tablaNominas.getValueAt(i, j).toString(); j++;
                                                String nombre = panelIngresarN.tablaNominas.getValueAt(i, j).toString();j++;
                                                String rol = panelIngresarN.tablaNominas.getValueAt(i, j).toString();j++;
                                                double sueldoDiario = Double.parseDouble(panelIngresarN.tablaNominas.getValueAt(i, j).toString());j++;
                                                int diasTrabajo = Integer.parseInt(panelIngresarN.tablaNominas.getValueAt(i, j).toString());j++;
                                                double netoACobrar = Double.parseDouble(panelIngresarN.tablaNominas.getValueAt(i, j).toString());
                                                DatosPago dp = new DatosPago(cedula,nombre, rol, sueldoDiario, diasTrabajo, netoACobrar);
                                                listaPagos.add(dp);
                                            }
                                            NominaPago np = new NominaPago(fIni,fCie,listaPagos);
                                            listaNominas.add(np);
                                            JOptionPane.showMessageDialog(null, "La Nómina de Pago ha sido registrada con éxito", "", 1);
                                            int year = Integer.parseInt(panelConsultarN.spinnerAgno.getValue().toString());
                                            int mes = panelConsultarN.comboBoxMes.getSelectedIndex();
                                            cargarNominas(mes, year);
                                            panelConsultarN.setSize(926,720);
                                            panelConsultarN.setLocation(0,0);
                                            panelSistema.panelPrincipal.removeAll();
                                            panelSistema.panelPrincipal.add(panelConsultarN);
                                            panelSistema.panelPrincipal.revalidate();
                                            panelSistema.panelPrincipal.repaint();
                                        }                                         
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "La fecha de cierre debe ser el siguiente domingo " + fechaE, "Advertencia", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La fecha de inicio debe corresponder a un domingo", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }                    
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe una Nómina de Pago con estas fechas", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        if(e.getSource()==panelIngresarN.botonRegresar){
            int year = Integer.parseInt(panelConsultarN.spinnerAgno.getValue().toString());
            int mes = panelConsultarN.comboBoxMes.getSelectedIndex();
            cargarNominas(mes, year);
            panelConsultarN.setSize(926,720);
            panelConsultarN.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultarN);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: Consultar Nominas
        
        if(e.getSource()==panelConsultarN.botonGenerar){
            cargarEmpleadosNomina();
            panelIngresarN.jDateFechaInicio.setDate(fechaActual);
            Calendar c = Calendar.getInstance();
            c.setTime(fechaActual); // Configuramos la fecha que se recibe
            c.add(Calendar.DAY_OF_YEAR, 7);
            Date fechaCierre = c.getTime();
            panelIngresarN.jDateFechaCierre.setDate(fechaCierre);
            panelIngresarN.setSize(926,720);
            panelIngresarN.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelIngresarN);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource()==panelConsultarN.botonRegresar){
            reestablecerColoresPanelConsultar();
            cargarEmpleados(panelConsultar.comboBoxRolEmpleado.getSelectedItem().toString());
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
    }
    
    //Método que cambia el título del panel consultar según el rol y carga los empleados según esto
    @Override
    public void itemStateChanged(ItemEvent e) {
        if ( e.getStateChange() == ItemEvent.SELECTED ){
            if (e.getItem().toString().equals("Cocinero")) {
                panelConsultar.labelTItulo.setText("COCINEROS");
                cargarEmpleados("Cocinero");
            }
            
            if (e.getItem().toString().equals("Bartender")) {
                panelConsultar.labelTItulo.setText("BARTENDERS");
                cargarEmpleados("Bartender");
            }
            
            if (e.getItem().toString().equals("Mesonero")) {
                panelConsultar.labelTItulo.setText("MESONEROS");
                cargarEmpleados("Mesonero");
            }
            
            if (e.getItem().toString().equals("Personal de servicio")) {
                panelConsultar.labelTItulo.setText("PERSONAL DE SERVICIO");
                cargarEmpleados("Personal de servicio");
            }
            if(e.getItem().toString().equals("Selecciona el mes") ||
               e.getItem().toString().equals("Enero") ||
               e.getItem().toString().equals("Febrero") ||
               e.getItem().toString().equals("Marzo") ||
               e.getItem().toString().equals("Abril") ||
               e.getItem().toString().equals("Mayo") ||
               e.getItem().toString().equals("Junio") ||
               e.getItem().toString().equals("Julio") ||
               e.getItem().toString().equals("Agosto") ||
               e.getItem().toString().equals("Septiembre") ||
               e.getItem().toString().equals("Octubre") ||
               e.getItem().toString().equals("Noviembre") ||
               e.getItem().toString().equals("Diciembre")){
                int year = Integer.parseInt(panelConsultarN.spinnerAgno.getValue().toString());
                int mes = panelConsultarN.comboBoxMes.getSelectedIndex();
                cargarNominas(mes, year);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        panelConsultar.txtBuscar.addKeyListener(new KeyAdapter (){
            @Override
            public void keyReleased(KeyEvent e) {
                cargarEmpleados(panelConsultar.comboBoxRolEmpleado.getSelectedItem().toString());
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == panelConsultarN.spinnerAgno){
            int year = Integer.parseInt(panelConsultarN.spinnerAgno.getValue().toString());
            int mes = panelConsultarN.comboBoxMes.getSelectedIndex();
            cargarNominas(mes, year);
        }
    }
    
}
