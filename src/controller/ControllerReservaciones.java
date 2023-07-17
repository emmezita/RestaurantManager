/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import model.modelreservacion.Comensal;
import model.conexionBD.ConexionServidor;
import model.modelreservacion.Empresa;
import model.modelreservacion.EventoEjecutivo;
import model.modelreservacion.EventoFamiliar;
import model.modelreservacion.EventoPrivado;
import model.modelreservacion.Reservacion;
import model.modelreservacion.ReservacionGeneral;
import view.viewprincipal.SistemaPrincipal;
import view.viewreservaciones.FrameDetallesReservacion;
import view.viewreservaciones.FrameVerificarCupo;
import view.viewreservaciones.PanelComensal;
import view.viewreservaciones.PanelConsultarReservaciones;
import view.viewreservaciones.PanelEmpresa;
import view.viewreservaciones.PanelEvento;
import view.viewreservaciones.PanelRegistrarComensal;
import view.viewreservaciones.PanelRegistrarEmpresa;
import view.viewreservaciones.PanelRegistrarEvento;
import view.viewreservaciones.PanelRegistrarReservacion;
import view.viewreservaciones.PanelReservacion;
import view.viewreservaciones.PanelReservacionGeneral;

/**
 *
 * Desarrollo: Rafael Marcano | Intefaces: Estefany Torres
 */
public class ControllerReservaciones implements ActionListener, ChangeListener, ItemListener, PropertyChangeListener, KeyListener {
    
    // Atributos del Controller Reservaciones de los paneles y frames(ventanas) que posee
    private final SistemaPrincipal panelSistema;
    private final PanelConsultarReservaciones panelConsultar = new PanelConsultarReservaciones();
    private final FrameVerificarCupo frameVerificar = new FrameVerificarCupo();
    private final PanelRegistrarComensal panelRegistrarComensal = new PanelRegistrarComensal();
    private final PanelRegistrarEmpresa panelRegistrarEmpresa = new PanelRegistrarEmpresa();
    private final PanelRegistrarEvento panelRegistrarEvento = new PanelRegistrarEvento();
    private final PanelRegistrarReservacion panelRegistrarReservacion = new PanelRegistrarReservacion();
    private final PanelComensal panelComensal = new PanelComensal();
    private final PanelEmpresa panelEmpresa = new PanelEmpresa();
    private final PanelEvento panelEvento = new PanelEvento();
    private final PanelReservacionGeneral panelReservacionG = new PanelReservacionGeneral();
    private final FrameDetallesReservacion frameDetalles = new FrameDetallesReservacion();
    
    // Atributos de datos del local 
    private final String correoRestaurant = "salumificioivisconti@gmail.com";
    private final String claveRestaurant = "fcospszlvguzwerd";
    private final int capacidad = 100;
    // Establece identificacion de cada reservacion
    private int tipoReservacion = 0; // 1 Reservacion General | 2 Evento Familiar | 3 Evento Ejecutivo
    // Objetos para cada tipo de reservacion y quien las registre
    private ReservacionGeneral reservacionG = new ReservacionGeneral();
    private final EventoPrivado eventoP = new EventoPrivado();
    private EventoFamiliar eventoF = new EventoFamiliar();
    private EventoEjecutivo eventoE = new EventoEjecutivo();
    private Comensal comensal = new Comensal();
    private Empresa empresa = new Empresa();
    
    // Atributos relacionados con fechas
    private final Calendar calendar = Calendar.getInstance();
    private Date fechaActual;
    private final Date horaInicio;
    private final Date horaCierre;
    SpinnerModel horaModelIncio,horaModelCierre;
    
    // Objetos de listas para todas las reservaciones y quien las registre
    private static final ArrayList<Reservacion>  listaReservaciones = new ArrayList<>();
    private static final ArrayList<Empresa> listaEmpresas = new ArrayList<>();
    private static final ArrayList<Comensal> listaComensales = new ArrayList<>();
    
    // Atributo de objeto para conectar al servidor de MySQL
    private final ConexionServidor CN;
        
    // Atributos de comandos para utilizar las tablas de la Base de Datos (BD):
    // Comandos para Tabla Empresa
    private final String SQL_INSERT_EMPRESA = "INSERT INTO Empresa (rif,nombre,correo,telefono,direccion) values (?,?,?,?,?)";
    private PreparedStatement PS_EMPRESA;
    
    
    private final String SQL_SELECT_EMPRESA = "SELECT * FROM Empresa";
    private DefaultTableModel DT_EMPRESA;
    private ResultSet RS_EMPRESA;
    
    // Comandos para Tabla Comensal
    private final String SQL_INSERT_COMENSAL = "INSERT INTO Comensal (cedula,nombre,apellido,telefono,correo,edad,ubicacion,sexo) values (?,?,?,?,?,?,?,?)";
    private PreparedStatement PS_COMENSAL;
    
    private final String SQL_SELECT_COMENSAL = "SELECT * FROM Comensal";
    private DefaultTableModel DT_COMENSAL;
    private ResultSet RS_COMENSAL;
    
    // Comandos para Tabla Reservacion
    private final String SQL_INSERT_RESERVACION = "INSERT INTO Reservacion (fechareserva, metodopago, numAdulto, numNino, montoAdulto, montoNino, pagado, costoTotal,"
            + " motivoEvento, horainicio, horafin, cedulacomensal, rifempresa) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private PreparedStatement PS_RESERVACION;                                         
    
    private final String SQL_SELECT_RESERVACION = "SELECT * FROM Reservacion";
    private DefaultTableModel DT_RESERVACION;
    private ResultSet RS_RESERVACION;
    
    private String indicadorClave = "";
    private String indicadorFecha = "";
    private String indicadorReservacion = "";
    
    /* Constructor de clase Controller Reservaciones que le agrega todos sus atributos
       paneles, frames y spinner correspondientes sumado a anadirles habilitacion de acciones.
       Ademas, se establece valores para los atributos de fechas*/ 
    public ControllerReservaciones(SistemaPrincipal panelSistema) {
        // Valores predeterminados de tiempo y dia para atributos de fecha actual
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);  
        calendar.set(Calendar.MILLISECOND, 0);
        fechaActual = calendar.getTime();
        // Valores predeterminados de tiempo para atributos de hora de inicio de un evento
        calendar.set(Calendar.HOUR, 6);
        calendar.set(Calendar.MINUTE, 0);
        horaInicio = calendar.getTime();
        // Valores predeterminados de tiempo para atributos de hora de cierre de un veneto
        calendar.set(Calendar.HOUR, 12);
        calendar.set(Calendar.MINUTE, 0);
        horaCierre = calendar.getTime();
        // Creacion de Spinners con sus atributos de hora correspondientes
        horaModelIncio = new SpinnerDateModel(horaInicio, null, null, Calendar.MINUTE);
        horaModelCierre = new SpinnerDateModel(horaCierre, null, null, Calendar.MINUTE); 
        //Ventana principal del sistema
        this.panelSistema = panelSistema;
        this.panelSistema.botonReservaciones.addActionListener(this);
        //Panel consultar de reservaciones
        panelConsultar.jScrollPane.getViewport().setOpaque(false);
        panelConsultar.calendarioReservaciones.addPropertyChangeListener(this);
        panelConsultar.botonIngresarR.addActionListener(this);
        panelConsultar.botonFecha.addActionListener(this);
        panelConsultar.botonTipoR.addActionListener(this);
        panelConsultar.botonTodas.addActionListener(this);
        panelConsultar.comboBoxTipoR.addItemListener(this);
        panelConsultar.txtBuscar.addKeyListener(this);
        //Frame de verificar cupo
        frameVerificar.botonVerificar.addActionListener(this);
        //Panel de registrar comensal
        panelRegistrarComensal.botonRegistrar.addActionListener(this);
        panelRegistrarComensal.botonRegresar.addActionListener(this);
        //Panel de registrar empresa
        panelRegistrarEmpresa.botonRegistrar.addActionListener(this);
        panelRegistrarEmpresa.botonRegresar.addActionListener(this);
        //Panel de registrar reservacion
        panelRegistrarReservacion.spinnerMontoAdulto.addChangeListener(this);
        panelRegistrarReservacion.spinnerMontoNino.addChangeListener(this);
        panelRegistrarReservacion.botonRegistrar.addActionListener(this);
        panelRegistrarReservacion.botonRegresar.addActionListener(this);
        //Panel de registrar evento
        panelRegistrarEvento.spinnerMontoAdulto.addChangeListener(this);
        panelRegistrarEvento.spinnerMontoNino.addChangeListener(this);
        panelRegistrarEvento.botonRegistrar.addActionListener(this);
        panelRegistrarEvento.botonRegresar.addActionListener(this);
        //Frame ver detalles
        frameDetalles.botonEliminar.addActionListener(this);
        frameDetalles.botonGuardar.addActionListener(this);
        // Comandos de consulta de datos de la BD
        PS_EMPRESA = null;
        PS_COMENSAL = null;
        PS_RESERVACION = null;
        // Conexion al servidor MySQL
        CN = new ConexionServidor();
        
        /* Metodos para pasar datos de cada tabla de la BD a sus
           listas correspondientes para el programa*/
        setDatosEmpresa();
        setDatosComensal();
        setDatosReservaciones();
    }
    
    //CONSULTAR BD EMPRESA
    // Establece columnas para la tabla de Empresa en el programa
    private DefaultTableModel setColumnasEmpresa(){
        DT_EMPRESA = new DefaultTableModel();
        DT_EMPRESA.addColumn("rif");
        DT_EMPRESA.addColumn("nombre");
        DT_EMPRESA.addColumn("correo");
        DT_EMPRESA.addColumn("telefono");
        DT_EMPRESA.addColumn("direccion");
        return DT_EMPRESA;
    }
    
    /* Obtiene los datos de cada Empresa de la tabla de la BD y los 
       registra en la lista de Empresas del programa */
    public DefaultTableModel setDatosEmpresa(){
        try{
            // Establece modelo de tabla
            setColumnasEmpresa();
            // Conexion a BD para obtener informacion de tabla Empresa
            PS_EMPRESA = CN.getConnection().prepareStatement(SQL_SELECT_EMPRESA);
            RS_EMPRESA = PS_EMPRESA.executeQuery();
            // Busca datos de cada columna
            while (RS_EMPRESA.next()){
                String rif = RS_EMPRESA.getString(1);
                String nombre = RS_EMPRESA.getString(2);
                String correo = RS_EMPRESA.getString(3);
                String telefono = RS_EMPRESA.getString(4);
                String direccion = RS_EMPRESA.getString(5);
                
                // Crea objeto Empresa y lo agrega a su lista
                Empresa empresa = new Empresa(nombre, direccion, telefono, correo, rif);
                listaEmpresas.add(empresa);
            }
        }
        // Excepcion si no se pudo listar las tablas
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar los datos de la empresa: " +e.getMessage(), "Error", 0);
        }
        // Finaliza conexion con BD
        finally{
            PS_EMPRESA=null;
            RS_EMPRESA=null;
            CN.desconectar();
        }
        return DT_EMPRESA;
    }
    
    //CONSULTAR BD COMENSAL
    // Establece columnas para la tabla de Comensal en el programa
    private DefaultTableModel setColumnasComensal(){
        DT_COMENSAL = new DefaultTableModel();
        DT_COMENSAL.addColumn("cedula");
        DT_COMENSAL.addColumn("nombre");
        DT_COMENSAL.addColumn("apellido");
        DT_COMENSAL.addColumn("telefono");
        DT_COMENSAL.addColumn("correo");
        DT_COMENSAL.addColumn("edad");
        DT_COMENSAL.addColumn("ubicacion");
        DT_COMENSAL.addColumn("sexo");
        return DT_COMENSAL;
    }
    
    /* Obtiene los datos de cada Comensal de la tabla de la BD y los 
       registra en la lista de Comensales del programa*/
    public DefaultTableModel setDatosComensal(){
        try{
            // Establece modelo de tabla
            setColumnasComensal();
            // Conexion a BD para obtener informacion de tabla Comensal
            PS_COMENSAL = CN.getConnection().prepareStatement(SQL_SELECT_COMENSAL);
            RS_COMENSAL = PS_COMENSAL.executeQuery();
            // Busca datos de cada columna
            while (RS_COMENSAL.next()){
                String cedula = RS_COMENSAL.getString(1);
                String nombre = RS_COMENSAL.getString(2);
                String apellido = RS_COMENSAL.getString(3);
                String telefono = RS_COMENSAL.getString(4);
                String correo = RS_COMENSAL.getString(5);
                int edad = RS_COMENSAL.getInt(6);
                String ubicacion = RS_COMENSAL.getString(7);
                String sSexo = RS_COMENSAL.getString(8);
                char sexo = sSexo.charAt(0);
                
                // Crea objeto Comensal y lo agrega a su lista
                Comensal comensal = new Comensal(cedula, nombre, apellido, edad, sexo, ubicacion, correo, telefono);
                listaComensales.add(comensal);
            }
        }
        // Excepcion si no se pudo listar las tablas
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar los datos del comensal: " +e.getMessage(), "Error", 0);
        }
        // Finalizar conexion a BD
        finally{
            PS_COMENSAL=null;
            RS_COMENSAL=null;
            CN.desconectar();
        }
        return DT_COMENSAL;
    }
    
    //CONSULTAR BD RESERVACIONES
    // Establece columnas para la tabla Reservaciones en el programa
    private DefaultTableModel setColumnasReservaciones(){
        DT_RESERVACION = new DefaultTableModel(); 
        DT_RESERVACION.addColumn("fechareserva");
        DT_RESERVACION.addColumn("metodopago");
        DT_RESERVACION.addColumn("numAdulto");
        DT_RESERVACION.addColumn("numNino");
        DT_RESERVACION.addColumn("montoAdulto");
        DT_RESERVACION.addColumn("montoNino");
        DT_RESERVACION.addColumn("pagado");
        DT_RESERVACION.addColumn("costoTotal");
        DT_RESERVACION.addColumn("motivoEvento");
        DT_RESERVACION.addColumn("horainicio");
        DT_RESERVACION.addColumn("horafin");
        DT_RESERVACION.addColumn("cedulacomensal");
        DT_RESERVACION.addColumn("rifempresa");
        return DT_RESERVACION;
    }
    
    /* Obtiene los datos de cada Reservacion de la tabla de la BD y los 
       registra en la lista de Reservaciones del programa*/
    public DefaultTableModel setDatosReservaciones() {
        try{
            // Establece modelo de tabla
            setColumnasReservaciones();
            // Conexion a BD para obtener informacion de tabla Reservaciones
            PS_RESERVACION = CN.getConnection().prepareStatement(SQL_SELECT_RESERVACION);
            RS_RESERVACION = PS_RESERVACION.executeQuery();
            // Busca datos de cada columna
            // Crea objetos respectivos de cada reservacion y los agrega a la lista del programa
            while (RS_RESERVACION.next()){
                String fechaReserva = RS_RESERVACION.getString(2);
                String metodoPago = RS_RESERVACION.getString(3);
                int numAdulto = RS_RESERVACION.getInt(4);
                int numNino = RS_RESERVACION.getInt(5);
                int montoAdulto = RS_RESERVACION.getInt(6);
                int montoNino = RS_RESERVACION.getInt(7);
                boolean pagado = RS_RESERVACION.getBoolean(8);
                double costoTotal = RS_RESERVACION.getDouble(9);
                // Reservaciones no ejecutivas
                if (RS_RESERVACION.getString(14).equals("")) {
                    // Reservaciones generales
                    if (RS_RESERVACION.getString(10).equals("")) {
                        String cedulaComensal = RS_RESERVACION.getString(13);
                        ReservacionGeneral reservacion = new ReservacionGeneral(cedulaComensal, fechaReserva, numAdulto, numNino,
                        costoTotal, pagado, metodoPago, montoAdulto, montoNino);
                        listaReservaciones.add(reservacion);
                    }
                    // Eventos familiares
                    else {
                        String motivoEvento = RS_RESERVACION.getString(10);
                        Date horaInicio = null;
                        Date horaFin = null;
                        if (RS_RESERVACION.getString(11) != null){
                            SimpleDateFormat formato = new SimpleDateFormat("hh:mm a"); 
                            try {
                                horaInicio  = formato.parse(RS_RESERVACION.getString(11));
                                horaFin = formato.parse(RS_RESERVACION.getString(12));
                            } catch (ParseException ex) {
                                Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        String cedulaComensal = RS_RESERVACION.getString(13);
                        EventoFamiliar reservacion = new EventoFamiliar(cedulaComensal, motivoEvento, horaInicio, horaFin, fechaReserva,
                        numAdulto, numNino, costoTotal, pagado, metodoPago, montoAdulto, montoNino);
                        listaReservaciones.add(reservacion);
                    }
                }
                // Eventos ejecutivos
                else {
                    String motivoEvento = RS_RESERVACION.getString(10);
                    Date horaInicio = null;
                    Date horaFin = null;
                    if (RS_RESERVACION.getString(11) != null){
                        SimpleDateFormat formato = new SimpleDateFormat("hh:mm a"); 
                        try {
                            horaInicio  = formato.parse(RS_RESERVACION.getString(11));
                            horaFin = formato.parse(RS_RESERVACION.getString(12)); 
                        } catch (ParseException ex) {
                            Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    String rifEmpresa = RS_RESERVACION.getString(14);
                    EventoEjecutivo reservacion = new EventoEjecutivo(rifEmpresa, motivoEvento, horaInicio, horaFin, fechaReserva,
                    numAdulto, numNino, costoTotal, pagado, metodoPago, montoAdulto, montoNino);
                    listaReservaciones.add(reservacion);
                }
            }
        }
        // Excepcion si no se pudo listar las tablas
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar los datos de las reservaciones: " +e.getMessage(), "Error", 0);
        }
        // Finaliza conexion con la BD
        finally{
            PS_RESERVACION=null;
            RS_RESERVACION=null;
            CN.desconectar();
        }
        return DT_RESERVACION;
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
    
    public int buscarMetodo (String metodo){
        switch (metodo){
            case "Efectivo": return 1;
            case "Zelle":return 2;
            case "Pago móvil": return 3;
            case "PayPal": return 4;
            default:
                return 0;
        }
    }
    
    // Metodo para que filtrar las reservaciones por una determinada fecha
    public void cargarReservacionesPorFecha (String fecha, String filtro){
         // Eliminar todos los paneles de reservaciones del panel de consulta
        panelConsultar.panelReservaciones.removeAll();
        
        // Establece datos del frame de detalles
        frameDetalles.setTitle("Detalles de la Reservación");
        frameDetalles.setSize(833,767);
        frameDetalles.setLocationRelativeTo(null);
        frameDetalles.setResizable(false);
        // Objeto para quien registra la reservacion
        Comensal c;
        Empresa e;
        String clave = "";
        String nombre = "";
        // Agregar los paneles de correspondientes al tipo de su reservacion
        for (Reservacion r : listaReservaciones) {
            // Busca el dia en especifico 
            if(r.getFechaDeReservacion().equals(fecha)){
                // Crea objeto del panel de la reservacion
                
                PanelReservacion panelR = new PanelReservacion();
                panelR.setSize(310,80);
                
                // RESERVACIONES GENERALES
                if (r instanceof ReservacionGeneral rg) {
                    // Busca en el dia el comensal segun su cedula registrada en reservacion
                    c = comensal.buscarComensal(listaComensales, rg.getCedula());
                    clave = c.getCedula();
                    nombre = (c.getNombre() + " " + c.getApellido()).toLowerCase();
                    // Establece las etiquetas de cada panel de cada reservacion
                    panelR.labelCantidadPersonas.setText(String.valueOf(rg.getNumeroNinos() + rg.getNumeroAdultos()));
                    panelR.labelCedula.setText(rg.getCedula());
                    panelR.labelCedula.setVisible(false);
                    if(rg.isPagado()){
                        panelR.labelEstatus.setForeground(new Color(152,255,163));
                        panelR.labelEstatus.setText("PAGADO");
                    } else{
                        panelR.labelEstatus.setForeground(new Color(255,152,152));
                        panelR.labelEstatus.setText("PENDIENTE");
                    }
                    panelR.labelFecha.setText(rg.getFechaDeReservacion());
                    panelR.labelNombreComensal.setText(c.getNombre() + " " +  c.getApellido());
                    panelR.labelTipoReservacion.setText("Reservación General");
                    
                    // Activa la accion del click del frame de detalles para poder mostrarlo
                    panelR.botonVer.addActionListener(new ActionListener() {
                        @Override
                        // Metodo que se ejecuta al presiona el frame de detalles
                        public void actionPerformed(ActionEvent e) {
                            // Busca comensal
                            Comensal c = comensal.buscarComensal(listaComensales, rg.getCedula());
                            indicadorClave = rg.getCedula();
                            indicadorFecha = rg.getFechaDeReservacion();
                            indicadorReservacion = "Reservación General";
                            // Establece datos del comensal en su panel en el frame de detalles
                            frameDetalles.labelTItuloP.setText("COMENSAL");
                            panelComensal.txtNombre.setText(c.getNombre());
                            panelComensal.txtApellido.setText(c.getApellido());
                            panelComensal.txtCorreo.setText(c.getCorreo());
                            panelComensal.txtCedula.setText(c.getCedula());
                            panelComensal.txtTelefono.setText(c.getTelefono());
                            panelComensal.txtMunicipio.setText(c.getUbicacion());
                            panelComensal.txtEdad.setText(Integer.toString(c.getEdad()));
                            if(c.getSexo() == 'F'){
                                panelComensal.txtSexo.setText("Femenino");
                            } else {
                                panelComensal.txtSexo.setText("Masculino"); 
                            }
                            // Limpieza, agregacion de panel de comensal y revalidacion del frame
                            panelComensal.setSize(330,460);
                            panelComensal.setLocation(0,0);
                            frameDetalles.panelDatosCoE.removeAll();
                            frameDetalles.panelDatosCoE.add(panelComensal);
                            frameDetalles.panelDatosCoE.revalidate();
                            frameDetalles.panelDatosCoE.repaint();
                            
                            // Establece datos de la reservacion general para su panel en el frame de detalles
                            frameDetalles.labelTItuloR.setText("RESERVACIÓN GENERAL");
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaR = null;
                            try {
                                fechaR = formatter.parse(rg.getFechaDeReservacion());
                            } catch (ParseException ex) {
                                Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            panelReservacionG.jDateFechaR.setDate(fechaR);
                            panelReservacionG.comboBoxMetodo.setSelectedIndex(buscarMetodo(rg.getMetodoDePago()));
                            if(rg.isPagado()){
                                panelReservacionG.comboBoxEstatus.setForeground(new Color(0,255,25));
                                panelReservacionG.comboBoxEstatus.setSelectedIndex(2);
                            } else{
                                panelReservacionG.comboBoxEstatus.setForeground(new Color(244,68,68));
                                panelReservacionG.comboBoxEstatus.setSelectedIndex(1);
                            }
                            panelReservacionG.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(rg.getMontoAdulto(), 0, 100, 1));
                            panelReservacionG.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(rg.getMontoNino(), 0, 100, 1));
                            panelReservacionG.spinnerNumAdultos.setModel(new javax.swing.SpinnerNumberModel(rg.getNumeroAdultos(), 0, 100, 1));
                            panelReservacionG.spinnerNumNinos.setModel(new javax.swing.SpinnerNumberModel(rg.getNumeroNinos(), 0, 99, 1));
                            int costoTotal = (int)rg.getCostoTotal();
                            panelReservacionG.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                            // Limpieza, agregacion de panel de reservacion general y revalidacion del frame
                            panelReservacionG.setSize(330,540);
                            panelReservacionG.setLocation(0,0);
                            frameDetalles.panelDatosR.removeAll();
                            frameDetalles.panelDatosR.add(panelReservacionG);
                            frameDetalles.panelDatosR.revalidate();
                            frameDetalles.panelDatosR.repaint();
                            frameDetalles.setVisible(true);
                        }
                    });
                }
                // EVENTOS FAMILIARES
                if(r instanceof EventoFamiliar ev){
                    // Busca en el dia el comensal segun su cedula registrada en evento 
                    c = comensal.buscarComensal(listaComensales, ev.getCedula());
                    clave = c.getCedula();
                    nombre = (c.getNombre() + " " + c.getApellido()).toLowerCase();
                    // Establece las etiquetas de cada panel de cada evento familiar
                    panelR.labelCantidadPersonas.setText(String.valueOf(ev.getNumeroNinos() + ev.getNumeroAdultos()));
                    panelR.labelCedula.setText(ev.getCedula());
                    panelR.labelCedula.setVisible(false);
                    if(ev.isPagado()){
                        panelR.labelEstatus.setForeground(new Color(152,255,163));
                        panelR.labelEstatus.setText("PAGADO");
                    } else{
                        panelR.labelEstatus.setForeground(new Color(255,152,152));
                        panelR.labelEstatus.setText("PENDIENTE");
                    }
                    panelR.labelFecha.setText(ev.getFechaDeReservacion());
                    panelR.labelNombreComensal.setText(c.getNombre() + " " +  c.getApellido());
                    panelR.labelTipoReservacion.setText("Evento Familiar");
                    
                    // Activa la accion del click del frame de detalles para poder mostrarlo
                    panelR.botonVer.addActionListener(new ActionListener() {
                    @Override
                    // Metodo que se ejecuta al presionar el frame de detalles
                    public void actionPerformed(ActionEvent e) {
                        // Busca comensal
                        Comensal c = comensal.buscarComensal(listaComensales, ev.getCedula());
                        indicadorClave = ev.getCedula();
                        indicadorFecha = ev.getFechaDeReservacion();
                        indicadorReservacion = "Evento Familiar";
                        // Establece datos del comensal en su panel en el frame de detalles
                        frameDetalles.labelTItuloP.setText("COMENSAL");
                        panelComensal.txtNombre.setText(c.getNombre());
                        panelComensal.txtApellido.setText(c.getApellido());
                        panelComensal.txtCorreo.setText(c.getCorreo());
                        panelComensal.txtCedula.setText(c.getCedula());
                        panelComensal.txtTelefono.setText(c.getTelefono());
                        panelComensal.txtMunicipio.setText(c.getUbicacion());
                        panelComensal.txtEdad.setText(Integer.toString(c.getEdad()));
                        if(c.getSexo() == 'F'){
                            panelComensal.txtSexo.setText("Femenino");
                        } else {
                            panelComensal.txtSexo.setText("Masculino"); 
                        }
                        // Limpieza, agregacion de panel de evento familiar y revalidacion del frame
                        panelComensal.setSize(330,460);
                        panelComensal.setLocation(0,0);
                        frameDetalles.panelDatosCoE.removeAll();
                        frameDetalles.panelDatosCoE.add(panelComensal);
                        frameDetalles.panelDatosCoE.revalidate();
                        frameDetalles.panelDatosCoE.repaint();
                        // Establece datos del evento familiar para su panel en el frame de detalles
                        frameDetalles.labelTItuloR.setText("EVENTO FAMILIAR");
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaR = null;
                        try {
                            fechaR = formatter.parse(ev.getFechaDeReservacion());
                        } catch (ParseException ex) {
                            Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        panelEvento.jDateFechaR.setDate(fechaR);
                        Date hInicio = ev.getHoraInicio();
                        Date hCierre = ev.getHoraCierre();
                        if((hInicio == null) || (hCierre ==null)){
                            panelEvento.spinnerHoraInicio.setModel(horaModelIncio);
                            panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraInicio,"hh:mm a"));
                            panelEvento.spinnerHoraCierre.setModel(horaModelCierre);
                            panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraCierre,"hh:mm a"));
                        } else {
                            SpinnerModel inicio = new SpinnerDateModel(hInicio, null, null, Calendar.MINUTE);
                            SpinnerModel cierre = new SpinnerDateModel(hCierre, null, null, Calendar.MINUTE);
                            panelEvento.spinnerHoraInicio.setModel(inicio);
                            panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraInicio,"hh:mm a"));
                            panelEvento.spinnerHoraCierre.setModel(cierre);
                            panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraCierre,"hh:mm a"));
                        }
                        panelEvento.comboBoxMetodo.setSelectedIndex(buscarMetodo(ev.getMetodoDePago()));
                        if(ev.isPagado()){
                            panelEvento.comboBoxEstatus.setForeground(new Color(0,255,25));
                            panelEvento.comboBoxEstatus.setSelectedIndex(2);
                        } else{
                            panelEvento.comboBoxEstatus.setForeground(new Color(244,68,68));
                            panelEvento.comboBoxEstatus.setSelectedIndex(1);
                        }
                        panelEvento.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoAdulto(), 0, 100, 1));
                        panelEvento.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoNino(), 0, 100, 1));
                        panelEvento.spinnerNumAdultos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroAdultos(), 0, 100, 1));
                        panelEvento.spinnerNumNinos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroNinos(), 0, 99, 1));
                        int costoTotal = (int)ev.getCostoTotal();
                        panelEvento.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                        panelEvento.txtMotivo.setText(ev.getMotivoDeEvento());
                        // Limpieza, agregacion de panel de evento familiar y revalidacion del frame
                        panelEvento.setSize(330,540);
                        panelEvento.setLocation(0,0);
                        frameDetalles.panelDatosR.removeAll();
                        frameDetalles.panelDatosR.add(panelEvento);
                        frameDetalles.panelDatosR.revalidate();
                        frameDetalles.panelDatosR.repaint();
                        frameDetalles.setVisible(true);
                    }
                });
                }
                // EVENTOS EJECUTIVOS
                if(r instanceof EventoEjecutivo ev){
                    // Busca en el dia la empresa segun su rif registrado en evento 
                    e = empresa.buscarEmpresa(listaEmpresas, ev.getRif());
                    clave = e.getRif();
                    nombre = e.getNombre().toLowerCase();
                    // Establece las etiquetas de cada panel de cada evento ejecutivo
                    panelR.labelCantidadPersonas.setText(String.valueOf(ev.getNumeroNinos() + ev.getNumeroAdultos()));
                    panelR.labelCedula.setText(ev.getRif());
                    panelR.labelCedula.setVisible(false);
                    if(ev.isPagado()){
                        panelR.labelEstatus.setForeground(new Color(152,255,163));
                        panelR.labelEstatus.setText("PAGADO");
                    } else{
                        panelR.labelEstatus.setForeground(new Color(255,152,152));
                        panelR.labelEstatus.setText("PENDIENTE");
                    }
                    panelR.labelFecha.setText(ev.getFechaDeReservacion());
                    panelR.labelNombreComensal.setText(e.getNombre());
                    panelR.labelTipoReservacion.setText("Evento Ejecutivo");
                    
                    // Activa la accion del click del frame de detalles para poder mostrarlo
                    panelR.botonVer.addActionListener(new ActionListener() {
                        @Override
                        //Metodo que se ejecuta al presionar el frame de detalles
                        public void actionPerformed(ActionEvent e) {
                            // Busca empresa
                            Empresa em = empresa.buscarEmpresa(listaEmpresas, ev.getRif());
                            indicadorClave = ev.getRif();
                            indicadorFecha = ev.getFechaDeReservacion();
                            indicadorReservacion = "Evento Ejecutivo";
                            // Establece datos de la empresa en su panel en el frame de detalles
                            frameDetalles.labelTItuloP.setText("EMPRESA");
                            frameDetalles.labelTItuloR.setText("EVENTO EJECUTIVO");
                            panelEmpresa.txtNombre.setText(em.getNombre());
                            panelEmpresa.txtCorreo.setText(em.getCorreo());
                            panelEmpresa.txtRIF.setText(em.getRif());
                            panelEmpresa.txtTelefono.setText(em.getTelefono());
                            panelEmpresa.txtMunicipio.setText(em.getDireccion());
                            // Limpieza, agregacion de panel de empresa y revalidacion del frame
                            panelEmpresa.setSize(330,460);
                            panelEmpresa.setLocation(0,0);
                            frameDetalles.panelDatosCoE.removeAll();
                            frameDetalles.panelDatosCoE.add(panelEmpresa);
                            frameDetalles.panelDatosCoE.revalidate();
                            frameDetalles.panelDatosCoE.repaint();
                            // Establece datos del evento ejecutivo para su panel en el frame de detalles
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaR = null;
                            try {
                                fechaR = formatter.parse(ev.getFechaDeReservacion());
                            } catch (ParseException ex) {
                                Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            panelEvento.jDateFechaR.setDate(fechaR);
                            Date hInicio = ev.getHoraInicio();
                            Date hCierre = ev.getHoraCierre();
                            if((hInicio == null) || (hCierre ==null)){
                                panelEvento.spinnerHoraInicio.setModel(horaModelIncio);
                                panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraInicio,"hh:mm a"));
                                panelEvento.spinnerHoraCierre.setModel(horaModelCierre);
                                panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraCierre,"hh:mm a"));
                            } else {
                                SpinnerModel inicio = new SpinnerDateModel(hInicio, null, null, Calendar.MINUTE);
                                SpinnerModel cierre = new SpinnerDateModel(hCierre, null, null, Calendar.MINUTE);
                                panelEvento.spinnerHoraInicio.setModel(inicio);
                                panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraInicio,"hh:mm a"));
                                panelEvento.spinnerHoraCierre.setModel(cierre);
                                panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraCierre,"hh:mm a"));
                            }
                            panelEvento.comboBoxMetodo.setSelectedIndex(buscarMetodo(ev.getMetodoDePago()));
                            if(ev.isPagado()){
                                panelEvento.comboBoxEstatus.setForeground(new Color(0,255,25));
                                panelEvento.comboBoxEstatus.setSelectedIndex(2);
                            } else{
                                panelEvento.comboBoxEstatus.setForeground(new Color(244,68,68));
                                panelEvento.comboBoxEstatus.setSelectedIndex(1);
                            }
                            panelEvento.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoAdulto(), 0, 100, 1));
                            panelEvento.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoNino(), 0, 100, 1));
                            panelEvento.spinnerNumAdultos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroAdultos(), 0, 100, 1));
                            panelEvento.spinnerNumNinos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroNinos(), 0, 99, 1));
                            int costoTotal = (int)ev.getCostoTotal();
                            panelEvento.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                            panelEvento.txtMotivo.setText(ev.getMotivoDeEvento());
                            // Limpieza, agregacion de panel de evento ejecutivo y revalidacion del frame
                            panelEvento.setSize(330,540);
                            panelEvento.setLocation(0,0);
                            frameDetalles.panelDatosR.removeAll();
                            frameDetalles.panelDatosR.add(panelEvento);
                            frameDetalles.panelDatosR.revalidate();
                            frameDetalles.panelDatosR.repaint();
                            frameDetalles.setVisible(true);
                        }
                    });
                }
                // Se agrega la reservacion en el panel de reservaciones en el panel de consulta 
                if(filtro.isEmpty()){
                    panelConsultar.panelReservaciones.add(panelR);
                }
                if(filtro.equals("Nombre")){
                    String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                    if(nombre.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
                if(filtro.equals("Cédula o RIF")){
                    String entrada = panelConsultar.txtBuscar.getText();
                    if(clave.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
                if(filtro.equals("Estado de pago")){
                    String pagado = panelR.labelEstatus.getText().toLowerCase();
                    String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                    if(pagado.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
            }
            panelConsultar.panelReservaciones.revalidate();
            panelConsultar.panelReservaciones.repaint();
        }    
    }
    
    // Metodo que muestra todas las reservaciones registradas en el sistema
    public void cargarTodasReservaciones (String filtro){
         // Eliminar todos los paneles de reservaciones del panel de consulta
        panelConsultar.panelReservaciones.removeAll();
        
        // Establece datos del frame de detalles
        frameDetalles.setTitle("Detalles de la Reservación");
        frameDetalles.setSize(833,717);
        frameDetalles.setLocationRelativeTo(null);
        frameDetalles.setResizable(false);
        // Objeto para quien registra la reservacion
        Comensal c;
        Empresa e;
        String clave = "";
        String nombre = "";
        // Agregar los paneles de correspondientes al tipo de su reservacion
        for (Reservacion r : listaReservaciones) {
            // Crea objeto del panel de reservacion
            PanelReservacion panelR = new PanelReservacion();
            panelR.setSize(310,80);
            
            // RESERVACION GENERAL
            if (r instanceof ReservacionGeneral rg) {
                // Buscar el comensal segun su cedula registrada en reservacion
                c = comensal.buscarComensal(listaComensales, rg.getCedula());
                clave = c.getCedula();
                nombre = (c.getNombre() + " " + c.getApellido()).toLowerCase();
                // Establece las etiquetas de cada panel de cada reservacion
                panelR.labelCantidadPersonas.setText(String.valueOf(rg.getNumeroNinos() + rg.getNumeroAdultos()));
                panelR.labelCedula.setText(rg.getCedula());
                panelR.labelCedula.setVisible(false);
                if(rg.isPagado()){
                    panelR.labelEstatus.setForeground(new Color(152,255,163));
                    panelR.labelEstatus.setText("PAGADO");
                } else{
                    panelR.labelEstatus.setForeground(new Color(255,152,152));
                    panelR.labelEstatus.setText("PENDIENTE");
                }
                panelR.labelFecha.setText(rg.getFechaDeReservacion());
                panelR.labelNombreComensal.setText(c.getNombre() + " " +  c.getApellido());
                panelR.labelTipoReservacion.setText("Reservación General");
                
                // Activa la accion del click del frame de detalles para poder mostrarlo
                panelR.botonVer.addActionListener(new ActionListener() {
                    @Override
                    // Metodo que se ejecuta al presionar el frame de detalles
                    public void actionPerformed(ActionEvent e) {
                        //Busca comensal
                        Comensal c = comensal.buscarComensal(listaComensales, rg.getCedula());
                        indicadorClave = rg.getCedula();
                        indicadorFecha = rg.getFechaDeReservacion();
                        indicadorReservacion = "Reservación General";
                        // Establece los datos del comensal en su panel en el frame de detalles
                        frameDetalles.labelTItuloP.setText("COMENSAL");
                        panelComensal.txtNombre.setText(c.getNombre());
                        panelComensal.txtApellido.setText(c.getApellido());
                        panelComensal.txtCorreo.setText(c.getCorreo());
                        panelComensal.txtCedula.setText(c.getCedula());
                        panelComensal.txtTelefono.setText(c.getTelefono());
                        panelComensal.txtMunicipio.setText(c.getUbicacion());
                        panelComensal.txtEdad.setText(Integer.toString(c.getEdad()));
                        if(c.getSexo() == 'F'){
                            panelComensal.txtSexo.setText("Femenino");
                        } else {
                            panelComensal.txtSexo.setText("Masculino"); 
                        }
                        // Limpieza, agregacion de panel del comensal y revalidacion del frame 
                        panelComensal.setSize(330,460);
                        panelComensal.setLocation(0,0);
                        frameDetalles.panelDatosCoE.removeAll();
                        frameDetalles.panelDatosCoE.add(panelComensal);
                        frameDetalles.panelDatosCoE.revalidate();
                        frameDetalles.panelDatosCoE.repaint();
                        
                        // Establece los datos de la reservacion general en su panel en el frame de detalles
                        frameDetalles.labelTItuloR.setText("RESERVACIÓN GENERAL");
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaR = null;
                        try {
                            fechaR = formatter.parse(rg.getFechaDeReservacion());
                        } catch (ParseException ex) {
                            Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        panelReservacionG.jDateFechaR.setDate(fechaR);
                        panelReservacionG.comboBoxMetodo.setSelectedIndex(buscarMetodo(rg.getMetodoDePago()));
                        if(rg.isPagado()){
                            panelReservacionG.comboBoxEstatus.setForeground(new Color(0,255,25));
                            panelReservacionG.comboBoxEstatus.setSelectedIndex(2);
                        } else{
                            panelReservacionG.comboBoxEstatus.setForeground(new Color(244,68,68));
                            panelReservacionG.comboBoxEstatus.setSelectedIndex(1);
                        }
                        panelReservacionG.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(rg.getMontoAdulto(), 0, 100, 1));
                        panelReservacionG.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(rg.getMontoNino(), 0, 100, 1));
                        panelReservacionG.spinnerNumAdultos.setModel(new javax.swing.SpinnerNumberModel(rg.getNumeroAdultos(), 0, 100, 1));
                        panelReservacionG.spinnerNumNinos.setModel(new javax.swing.SpinnerNumberModel(rg.getNumeroNinos(), 0, 99, 1));
                        int costoTotal = (int)rg.getCostoTotal();
                        panelReservacionG.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                        // Limpieza, agregacion de panel de reservacion general y revalidacion del frame
                        panelReservacionG.setSize(330,540);
                        panelReservacionG.setLocation(0,0);
                        frameDetalles.panelDatosR.removeAll();
                        frameDetalles.panelDatosR.add(panelReservacionG);
                        frameDetalles.panelDatosR.revalidate();
                        frameDetalles.panelDatosR.repaint();
                        frameDetalles.setVisible(true);
                    }
                });
            }
                // EVENTO FAMILIAR
            if(r instanceof EventoFamiliar ev){
                    // Busca el comensal segun su cedula registrada en evento
                    c = comensal.buscarComensal(listaComensales, ev.getCedula());
                    clave = c.getCedula();
                    nombre = (c.getNombre() + " " + c.getApellido()).toLowerCase();
                    // Establece las etiquetas de cada panel de evento familiar
                    panelR.labelCantidadPersonas.setText(String.valueOf(ev.getNumeroNinos() + ev.getNumeroAdultos()));
                    panelR.labelCedula.setText(ev.getCedula());
                    panelR.labelCedula.setVisible(false);
                    if(ev.isPagado()){
                        panelR.labelEstatus.setForeground(new Color(152,255,163));
                        panelR.labelEstatus.setText("PAGADO");
                    } else{
                        panelR.labelEstatus.setForeground(new Color(255,152,152));
                        panelR.labelEstatus.setText("PENDIENTE");
                    }
                    panelR.labelFecha.setText(ev.getFechaDeReservacion());
                    panelR.labelNombreComensal.setText(c.getNombre() + " " +  c.getApellido());
                    panelR.labelTipoReservacion.setText("Evento Familiar");
                    
                    // Activa la accion del click del frame de detalles para poder mostrarlo
                    panelR.botonVer.addActionListener(new ActionListener() {
                    @Override
                    // Metodo que se ejecuta al presionar el frame de detalles 
                    public void actionPerformed(ActionEvent e) {
                        // Busca comensal
                        Comensal c = comensal.buscarComensal(listaComensales, ev.getCedula());
                        indicadorClave = ev.getCedula();
                        indicadorFecha = ev.getFechaDeReservacion();
                        indicadorReservacion = "Evento Familiar";
                        // Establece datos del comensal en su panel en el frame de detalles
                        frameDetalles.labelTItuloP.setText("COMENSAL");
                        panelComensal.txtNombre.setText(c.getNombre());
                        panelComensal.txtApellido.setText(c.getApellido());
                        panelComensal.txtCorreo.setText(c.getCorreo());
                        panelComensal.txtCedula.setText(c.getCedula());
                        panelComensal.txtTelefono.setText(c.getTelefono());
                        panelComensal.txtMunicipio.setText(c.getUbicacion());
                        panelComensal.txtEdad.setText(Integer.toString(c.getEdad()));
                        if(c.getSexo() == 'F'){
                            panelComensal.txtSexo.setText("Femenino");
                        } else {
                            panelComensal.txtSexo.setText("Masculino"); 
                        }
                        // Limpieza, agregacion del panel de comensal y revalidacion del frame
                        panelComensal.setSize(330,460);
                        panelComensal.setLocation(0,0);
                        frameDetalles.panelDatosCoE.removeAll();
                        frameDetalles.panelDatosCoE.add(panelComensal);
                        frameDetalles.panelDatosCoE.revalidate();
                        frameDetalles.panelDatosCoE.repaint();
                        
                        // Establece los datos del evento familiar para su panel en el frame
                        frameDetalles.labelTItuloR.setText("EVENTO FAMILIAR");
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaR = null;
                        try {
                            fechaR = formatter.parse(ev.getFechaDeReservacion());
                        } catch (ParseException ex) {
                            Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        panelEvento.jDateFechaR.setDate(fechaR);
                        Date hInicio = ev.getHoraInicio();
                        Date hCierre = ev.getHoraCierre();
                        if((hInicio == null) || (hCierre ==null)){
                            panelEvento.spinnerHoraInicio.setModel(horaModelIncio);
                            panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraInicio,"hh:mm a"));
                            panelEvento.spinnerHoraCierre.setModel(horaModelCierre);
                            panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraCierre,"hh:mm a"));
                        } else {
                            SpinnerModel inicio = new SpinnerDateModel(hInicio, null, null, Calendar.MINUTE);
                            SpinnerModel cierre = new SpinnerDateModel(hCierre, null, null, Calendar.MINUTE);
                            panelEvento.spinnerHoraInicio.setModel(inicio);
                            panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraInicio,"hh:mm a"));
                            panelEvento.spinnerHoraCierre.setModel(cierre);
                            panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraCierre,"hh:mm a"));
                        }
                        panelEvento.comboBoxMetodo.setSelectedIndex(buscarMetodo(ev.getMetodoDePago()));
                        if(ev.isPagado()){
                            panelEvento.comboBoxEstatus.setForeground(new Color(0,255,25));
                            panelEvento.comboBoxEstatus.setSelectedIndex(2);
                        } else{
                            panelEvento.comboBoxEstatus.setForeground(new Color(244,68,68));
                            panelEvento.comboBoxEstatus.setSelectedIndex(1);
                        }
                        panelEvento.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoAdulto(), 0, 100, 1));
                        panelEvento.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoNino(), 0, 100, 1));
                        panelEvento.spinnerNumAdultos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroAdultos(), 0, 100, 1));
                        panelEvento.spinnerNumNinos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroNinos(), 0, 99, 1));
                        int costoTotal = (int)ev.getCostoTotal();
                        panelEvento.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                        panelEvento.txtMotivo.setText(ev.getMotivoDeEvento());
                        // Limpieza, agregacion del panel de evento familiar y revalidacion del frame
                        panelEvento.setSize(330,540);
                        panelEvento.setLocation(0,0);
                        frameDetalles.panelDatosR.removeAll();
                        frameDetalles.panelDatosR.add(panelEvento);
                        frameDetalles.panelDatosR.revalidate();
                        frameDetalles.panelDatosR.repaint();
                        frameDetalles.setVisible(true);
                    }
                });
            }
            // EVENTO EJECUTIVO
            if(r instanceof EventoEjecutivo ev){
                // Busca la empresa segun su rif registrado en evento 
                e = empresa.buscarEmpresa(listaEmpresas, ev.getRif());
                clave = e.getRif();
                nombre = e.getNombre().toLowerCase();
                // Establece las etiquetas de cada panel de evento ejecutivo
                panelR.labelCantidadPersonas.setText(String.valueOf(ev.getNumeroNinos() + ev.getNumeroAdultos()));
                panelR.labelCedula.setText(ev.getRif());
                panelR.labelCedula.setVisible(false);
                if(ev.isPagado()){
                    panelR.labelEstatus.setForeground(new Color(152,255,163));
                    panelR.labelEstatus.setText("PAGADO");
                } else{
                    panelR.labelEstatus.setForeground(new Color(255,152,152));
                    panelR.labelEstatus.setText("PENDIENTE");
                }
                panelR.labelFecha.setText(ev.getFechaDeReservacion());
                panelR.labelNombreComensal.setText(e.getNombre());
                panelR.labelTipoReservacion.setText("Evento Ejecutivo");
                
                // Activa la accion del click del frame de detalles para poder mostrarlo
                panelR.botonVer.addActionListener(new ActionListener() {
                    @Override
                    // Metodo que se ejecuta al presionar el frame de detalles
                    public void actionPerformed(ActionEvent e) {
                        // Busca empresa
                        Empresa em = empresa.buscarEmpresa(listaEmpresas, ev.getRif());
                        indicadorClave = ev.getRif();
                        indicadorFecha = ev.getFechaDeReservacion();
                        indicadorReservacion = "Evento Ejecutivo";
                        // Establece datos de la empresa en su panel en el frame de detalles
                        frameDetalles.labelTItuloP.setText("EMPRESA");
                        frameDetalles.labelTItuloR.setText("EVENTO EJECUTIVO");
                        panelEmpresa.txtNombre.setText(em.getNombre());
                        panelEmpresa.txtCorreo.setText(em.getCorreo());
                        panelEmpresa.txtRIF.setText(em.getRif());
                        panelEmpresa.txtTelefono.setText(em.getTelefono());
                        panelEmpresa.txtMunicipio.setText(em.getDireccion());
                        panelEmpresa.setSize(330,460);
                        panelEmpresa.setLocation(0,0);
                        // Limpieza, agregacion de panel de empresa y revalidacion del frame
                        frameDetalles.panelDatosCoE.removeAll();
                        frameDetalles.panelDatosCoE.add(panelEmpresa);
                        frameDetalles.panelDatosCoE.revalidate();
                        frameDetalles.panelDatosCoE.repaint();
                        
                        // Establece datos del evento ejecutivo para su panel en el frame de detalles
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaR = null;
                        try {
                            fechaR = formatter.parse(ev.getFechaDeReservacion());
                        } catch (ParseException ex) {
                            Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        panelEvento.jDateFechaR.setDate(fechaR);
                        Date hInicio = ev.getHoraInicio();
                        Date hCierre = ev.getHoraCierre();
                        if((hInicio == null) || (hCierre ==null)){
                            panelEvento.spinnerHoraInicio.setModel(horaModelIncio);
                            panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraInicio,"hh:mm a"));
                            panelEvento.spinnerHoraCierre.setModel(horaModelCierre);
                            panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraCierre,"hh:mm a"));
                        } else {
                            SpinnerModel inicio = new SpinnerDateModel(hInicio, null, null, Calendar.MINUTE);
                            SpinnerModel cierre = new SpinnerDateModel(hCierre, null, null, Calendar.MINUTE);
                            panelEvento.spinnerHoraInicio.setModel(inicio);
                            panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraInicio,"hh:mm a"));
                            panelEvento.spinnerHoraCierre.setModel(cierre);
                            panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraCierre,"hh:mm a"));
                        }
                        panelEvento.comboBoxMetodo.setSelectedIndex(buscarMetodo(ev.getMetodoDePago()));
                        if(ev.isPagado()){
                            panelEvento.comboBoxEstatus.setForeground(new Color(0,255,25));
                            panelEvento.comboBoxEstatus.setSelectedIndex(2);
                        } else{
                            panelEvento.comboBoxEstatus.setForeground(new Color(244,68,68));
                            panelEvento.comboBoxEstatus.setSelectedIndex(1);
                        }
                        panelEvento.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoAdulto(), 0, 100, 1));
                        panelEvento.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoNino(), 0, 100, 1));
                        panelEvento.spinnerNumAdultos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroAdultos(), 0, 100, 1));
                        panelEvento.spinnerNumNinos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroNinos(), 0, 99, 1));
                        int costoTotal = (int)ev.getCostoTotal();
                        panelEvento.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                        panelEvento.txtMotivo.setText(ev.getMotivoDeEvento());
                        // Limpieza, agregacion de panel de evento ejecutivo y revalidacion del frame
                        panelEvento.setSize(330,540);
                        panelEvento.setLocation(0,0);
                        frameDetalles.panelDatosR.removeAll();
                        frameDetalles.panelDatosR.add(panelEvento);
                        frameDetalles.panelDatosR.revalidate();
                        frameDetalles.panelDatosR.repaint();
                        frameDetalles.setVisible(true);
                    }
                });
            }
            // Se agrega la reservacion en el panel de reservaciones en el panel de consulta 
            if(filtro.isEmpty()){
                panelConsultar.panelReservaciones.add(panelR);
            }
            if(filtro.equals("Nombre")){
                String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                if(nombre.contains(entrada)){
                    panelConsultar.panelReservaciones.add(panelR);
                }
            }
            if(filtro.equals("Cédula o RIF")){
                String entrada = panelConsultar.txtBuscar.getText();
                if(clave.contains(entrada)){
                    panelConsultar.panelReservaciones.add(panelR);
                }
            }
            if(filtro.equals("Estado de pago")){
                String pagado = panelR.labelEstatus.getText().toLowerCase();
                String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                if(pagado.contains(entrada)){
                    panelConsultar.panelReservaciones.add(panelR);
                }
            }
        }
        panelConsultar.panelReservaciones.revalidate();
        panelConsultar.panelReservaciones.repaint();
    }
    
    // Metodo para filtrar solo las reservaciones generales
    public void cargarReservacionesGenerales (String filtro){
         // Eliminar todos los paneles de reservacion del panel de consulta
        panelConsultar.panelReservaciones.removeAll();
        
        // Establece datos del frame
        frameDetalles.setTitle("Detalles de la Reservación");
        frameDetalles.setSize(833,767);
        frameDetalles.setLocationRelativeTo(null);
        frameDetalles.setResizable(false);
        // Objeto para quien registra la reservacion
        Comensal c;
        // Agregar los paneles de correspondientes a las reservaciones generales
        for (Reservacion r : listaReservaciones) {
            if(r instanceof ReservacionGeneral rg){
                // Crea objeto del panel de reservacion
                PanelReservacion panelR = new PanelReservacion();
                panelR.setSize(310,80);
                // Busca el comensal segun su cedula registrada en reservacion
                c = comensal.buscarComensal(listaComensales, rg.getCedula());
                // Establece las etiquetas del panel de cada reservacion
                panelR.labelCantidadPersonas.setText(String.valueOf(rg.getNumeroNinos() + rg.getNumeroAdultos()));
                panelR.labelCedula.setText(rg.getCedula());
                panelR.labelCedula.setVisible(false);
                if(rg.isPagado()){
                    panelR.labelEstatus.setForeground(new Color(152,255,163));
                    panelR.labelEstatus.setText("PAGADO");
                } else{
                    panelR.labelEstatus.setForeground(new Color(255,152,152));
                    panelR.labelEstatus.setText("PENDIENTE");
                }
                panelR.labelFecha.setText(rg.getFechaDeReservacion());
                panelR.labelNombreComensal.setText(c.getNombre() + " " +  c.getApellido());
                panelR.labelTipoReservacion.setText("Reservación General");
                
                // Activa la accion del click del frame de detalles para poder mostrarlo
                panelR.botonVer.addActionListener(new ActionListener() {
                    @Override
                    // Metodo que se ejecuta al presionar el frame de detalles
                    public void actionPerformed(ActionEvent e) {
                        // Busca comensal
                        Comensal c = comensal.buscarComensal(listaComensales, rg.getCedula());
                        indicadorClave = rg.getCedula();
                        indicadorFecha = rg.getFechaDeReservacion();
                        indicadorReservacion = "Reservación General";
                        // Establece datos del comensal en su panel en el frame de detalles
                        frameDetalles.labelTItuloP.setText("COMENSAL");
                        panelComensal.txtNombre.setText(c.getNombre());
                        panelComensal.txtApellido.setText(c.getApellido());
                        panelComensal.txtCorreo.setText(c.getCorreo());
                        panelComensal.txtCedula.setText(c.getCedula());
                        panelComensal.txtTelefono.setText(c.getTelefono());
                        panelComensal.txtMunicipio.setText(c.getUbicacion());
                        panelComensal.txtEdad.setText(Integer.toString(c.getEdad()));
                        if(c.getSexo() == 'F'){
                            panelComensal.txtSexo.setText("Femenino");
                        } else {
                            panelComensal.txtSexo.setText("Masculino"); 
                        }
                        // Limpieza, agregacion de panel de comensal y revalidacion del frame
                        panelComensal.setSize(330,460);
                        panelComensal.setLocation(0,0);
                        frameDetalles.panelDatosCoE.removeAll();
                        frameDetalles.panelDatosCoE.add(panelComensal);
                        frameDetalles.panelDatosCoE.revalidate();
                        frameDetalles.panelDatosCoE.repaint();
                        
                        // Establece los datos de la reservacion general en el frame de detalles
                        frameDetalles.labelTItuloR.setText("RESERVACIÓN GENERAL");
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaR = null;
                        try {
                            fechaR = formatter.parse(rg.getFechaDeReservacion());
                        } catch (ParseException ex) {
                            Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        panelReservacionG.jDateFechaR.setDate(fechaR);
                        panelReservacionG.comboBoxMetodo.setSelectedIndex(buscarMetodo(rg.getMetodoDePago()));
                        if(rg.isPagado()){
                            panelReservacionG.comboBoxEstatus.setForeground(new Color(0,255,25));
                            panelReservacionG.comboBoxEstatus.setSelectedIndex(2);
                        } else{
                            panelReservacionG.comboBoxEstatus.setForeground(new Color(244,68,68));
                            panelReservacionG.comboBoxEstatus.setSelectedIndex(1);
                        }
                        panelReservacionG.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(rg.getMontoAdulto(), 0, 100, 1));
                        panelReservacionG.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(rg.getMontoNino(), 0, 100, 1));
                        panelReservacionG.spinnerNumAdultos.setModel(new javax.swing.SpinnerNumberModel(rg.getNumeroAdultos(), 0, 100, 1));
                        panelReservacionG.spinnerNumNinos.setModel(new javax.swing.SpinnerNumberModel(rg.getNumeroNinos(), 0, 99, 1));
                        int costoTotal = (int)rg.getCostoTotal();
                        panelReservacionG.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                        // Limpieza, agregacion de panel de reservacion general y revalidacion del frame
                        panelReservacionG.setSize(330,540);
                        panelReservacionG.setLocation(0,0);
                        frameDetalles.panelDatosR.removeAll();
                        frameDetalles.panelDatosR.add(panelReservacionG);
                        frameDetalles.panelDatosR.revalidate();
                        frameDetalles.panelDatosR.repaint();
                        frameDetalles.setVisible(true);
                    }
                });  
                // Se agrega la reservacion en el panel de reservaciones en el panel de consulta 
                if(filtro.isEmpty()){
                    panelConsultar.panelReservaciones.add(panelR);
                }
                if(filtro.equals("Nombre")){
                    String nombre = (c.getNombre() + " " + c.getApellido()).toLowerCase();
                    String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                    if(nombre.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
                if(filtro.equals("Cédula o RIF")){
                    String cedula = c.getCedula();
                    String entrada = panelConsultar.txtBuscar.getText();
                    if(cedula.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
                if(filtro.equals("Estado de pago")){
                    String pagado = panelR.labelEstatus.getText().toLowerCase();
                    String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                    if(pagado.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
            }
        }
        // Revalidacion del panel de reservacion
        panelConsultar.panelReservaciones.revalidate();
        panelConsultar.panelReservaciones.repaint();
    }
    
    // Metodo para filtrar solo los eventos privados
    public void cargarReservacionesPrivadas (String filtro){
         // Eliminar todos los paneles de reservaciones del panel de consulta
        panelConsultar.panelReservaciones.removeAll();
        
        // Establece datos del frame
        frameDetalles.setTitle("Detalles de la Reservación");
        frameDetalles.setSize(833,767);
        frameDetalles.setLocationRelativeTo(null);
        frameDetalles.setResizable(false);
        // Objetos para quien registra la reservacion
        Comensal c;
        Empresa e;
        // Agregar los paneles de correspondientes al tipo de evento privado
        for (Reservacion r : listaReservaciones) {
            
            // EVENTO FAMILIAR
            if(r instanceof EventoFamiliar ev){
                // Busca el comensal segun su cedula registrada en evento
                c = comensal.buscarComensal(listaComensales, ev.getCedula());
                // Establece las etiquetas de cada panel de cada evento
                PanelReservacion panelR = new PanelReservacion();
                panelR.setSize(310,80);
                panelR.labelCantidadPersonas.setText(String.valueOf(ev.getNumeroNinos() + ev.getNumeroAdultos()));
                panelR.labelCedula.setText(ev.getCedula());
                panelR.labelCedula.setVisible(false);
                if(ev.isPagado()){
                    panelR.labelEstatus.setForeground(new Color(152,255,163));
                    panelR.labelEstatus.setText("PAGADO");
                } else{
                    panelR.labelEstatus.setForeground(new Color(255,152,152));
                    panelR.labelEstatus.setText("PENDIENTE");
                }
                panelR.labelFecha.setText(ev.getFechaDeReservacion());
                panelR.labelNombreComensal.setText(c.getNombre() + " " +  c.getApellido());
                panelR.labelTipoReservacion.setText("Evento Familiar");
                
                // Activa la accion del click del frame de detalles para poder mostrarlo
                panelR.botonVer.addActionListener(new ActionListener() {
                    @Override
                    // Metodo que se ejecuta al presionar el frame de detalles
                    public void actionPerformed(ActionEvent e) {
                        // Busca comensal
                        Comensal c = comensal.buscarComensal(listaComensales, ev.getCedula());
                        indicadorClave = ev.getCedula();
                        indicadorFecha = ev.getFechaDeReservacion();
                        indicadorReservacion = "Evento Familiar";
                        // Establece datos del comensal en su panel en el frame de detalles
                        frameDetalles.labelTItuloP.setText("COMENSAL");
                        panelComensal.txtNombre.setText(c.getNombre());
                        panelComensal.txtApellido.setText(c.getApellido());
                        panelComensal.txtCorreo.setText(c.getCorreo());
                        panelComensal.txtCedula.setText(c.getCedula());
                        panelComensal.txtTelefono.setText(c.getTelefono());
                        panelComensal.txtMunicipio.setText(c.getUbicacion());
                        panelComensal.txtEdad.setText(Integer.toString(c.getEdad()));
                        if(c.getSexo() == 'F'){
                            panelComensal.txtSexo.setText("Femenino");
                        } else {
                            panelComensal.txtSexo.setText("Masculino"); 
                        }
                        // Limpieza, agregacion del panel de comensal y revalidacion del frame
                        panelComensal.setSize(330,460);
                        panelComensal.setLocation(0,0);
                        frameDetalles.panelDatosCoE.removeAll();
                        frameDetalles.panelDatosCoE.add(panelComensal);
                        frameDetalles.panelDatosCoE.revalidate();
                        frameDetalles.panelDatosCoE.repaint();
                        
                        // Establece los datos del evento familiar para su panel en el frame de detalles
                        frameDetalles.labelTItuloR.setText("EVENTO FAMILIAR");
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaR = null;
                        try {
                            fechaR = formatter.parse(ev.getFechaDeReservacion());
                        } catch (ParseException ex) {
                            Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        panelEvento.jDateFechaR.setDate(fechaR);
                        Date hInicio = ev.getHoraInicio();
                        Date hCierre = ev.getHoraCierre();
                        if((hInicio == null) || (hCierre ==null)){
                            panelEvento.spinnerHoraInicio.setModel(horaModelIncio);
                            panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraInicio,"hh:mm a"));
                            panelEvento.spinnerHoraCierre.setModel(horaModelCierre);
                            panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraCierre,"hh:mm a"));
                        } else {
                            SpinnerModel inicio = new SpinnerDateModel(hInicio, null, null, Calendar.MINUTE);
                            SpinnerModel cierre = new SpinnerDateModel(hCierre, null, null, Calendar.MINUTE);
                            panelEvento.spinnerHoraInicio.setModel(inicio);
                            panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraInicio,"hh:mm a"));
                            panelEvento.spinnerHoraCierre.setModel(cierre);
                            panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraCierre,"hh:mm a"));
                        }
                        panelEvento.comboBoxMetodo.setSelectedIndex(buscarMetodo(ev.getMetodoDePago()));
                        if(ev.isPagado()){
                            panelEvento.comboBoxEstatus.setForeground(new Color(0,255,25));
                            panelEvento.comboBoxEstatus.setSelectedIndex(2);
                        } else{
                            panelEvento.comboBoxEstatus.setForeground(new Color(244,68,68));
                            panelEvento.comboBoxEstatus.setSelectedIndex(1);
                        }
                        panelEvento.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoAdulto(), 0, 100, 1));
                        panelEvento.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoNino(), 0, 100, 1));
                        panelEvento.spinnerNumAdultos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroAdultos(), 0, 100, 1));
                        panelEvento.spinnerNumNinos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroNinos(), 0, 99, 1));
                        int costoTotal = (int)ev.getCostoTotal();
                        panelEvento.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                        panelEvento.txtMotivo.setText(ev.getMotivoDeEvento());
                        // Limpieza, agregacion del panel de evento familiar y revalidacion del frame
                        panelEvento.setSize(330,540);
                        panelEvento.setLocation(0,0);
                        frameDetalles.panelDatosR.removeAll();
                        frameDetalles.panelDatosR.add(panelEvento);
                        frameDetalles.panelDatosR.revalidate();
                        frameDetalles.panelDatosR.repaint();
                        frameDetalles.setVisible(true);
                    }
                });
                // Se agrega el evento familiar en el panel de reservaciones en el panel de consulta
                if(filtro.isEmpty()){
                    panelConsultar.panelReservaciones.add(panelR);
                }
                if(filtro.equals("Nombre")){
                    String nombre = (c.getNombre() + " " + c.getApellido()).toLowerCase();
                    String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                    if(nombre.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
                if(filtro.equals("Cédula o RIF")){
                    String cedula = c.getCedula();
                    String entrada = panelConsultar.txtBuscar.getText();
                    if(cedula.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
                if(filtro.equals("Estado de pago")){
                    String pagado = panelR.labelEstatus.getText().toLowerCase();
                    String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                    if(pagado.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
            }
            // EVENTO EJECUTIVO
            if(r instanceof EventoEjecutivo ev){
                // Busca empresa
                e = empresa.buscarEmpresa(listaEmpresas, ev.getRif());
                // Establece las etiquetas de cada panel de cada evento ejecutivo
                PanelReservacion panelR = new PanelReservacion();
                panelR.setSize(310,80);
                panelR.labelCantidadPersonas.setText(String.valueOf(ev.getNumeroNinos() + ev.getNumeroAdultos()));
                panelR.labelCedula.setText(ev.getRif());
                panelR.labelCedula.setVisible(false);
                if(ev.isPagado()){
                    panelR.labelEstatus.setForeground(new Color(152,255,163));
                    panelR.labelEstatus.setText("PAGADO");
                } else{
                    panelR.labelEstatus.setForeground(new Color(255,152,152));
                    panelR.labelEstatus.setText("PENDIENTE");
                }
                panelR.labelFecha.setText(ev.getFechaDeReservacion());
                panelR.labelNombreComensal.setText(e.getNombre());
                panelR.labelTipoReservacion.setText("Evento Ejecutivo");
                
                // Activa la accion del click del frame de detalles para poder mostrarlo
                panelR.botonVer.addActionListener(new ActionListener() {
                    @Override
                    // Metodo que se ejecuta al presionar el frame de detalels
                    public void actionPerformed(ActionEvent e) {
                        // Busca empresa
                        Empresa em = empresa.buscarEmpresa(listaEmpresas, ev.getRif());
                        indicadorClave = ev.getRif();
                        indicadorFecha = ev.getFechaDeReservacion();
                        indicadorReservacion = "Evento Ejecutivo";
                        // Establece datos de la empresa en su panel del frame de detalles
                        frameDetalles.labelTItuloP.setText("EMPRESA");
                        frameDetalles.labelTItuloR.setText("EVENTO EJECUTIVO");
                        panelEmpresa.txtNombre.setText(em.getNombre());
                        panelEmpresa.txtCorreo.setText(em.getCorreo());
                        panelEmpresa.txtRIF.setText(em.getRif());
                        panelEmpresa.txtTelefono.setText(em.getTelefono());
                        panelEmpresa.txtMunicipio.setText(em.getDireccion());
                        // Limpieza, agregacion del panel de empresa y revalidacion del frame
                        panelEmpresa.setSize(330,460);
                        panelEmpresa.setLocation(0,0);
                        frameDetalles.panelDatosCoE.removeAll();
                        frameDetalles.panelDatosCoE.add(panelEmpresa);
                        frameDetalles.panelDatosCoE.revalidate();
                        frameDetalles.panelDatosCoE.repaint();
                        
                        // Establece datos del evento ejecutivo en su panel en el frame de detalles
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaR = null;
                        try {
                            fechaR = formatter.parse(ev.getFechaDeReservacion());
                        } catch (ParseException ex) {
                            Logger.getLogger(ControllerReservaciones.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        panelEvento.jDateFechaR.setDate(fechaR);
                        Date hInicio = ev.getHoraInicio();
                        Date hCierre = ev.getHoraCierre();
                        if((hInicio == null) || (hCierre ==null)){
                            panelEvento.spinnerHoraInicio.setModel(horaModelIncio);
                            panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraInicio,"hh:mm a"));
                            panelEvento.spinnerHoraCierre.setModel(horaModelCierre);
                            panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraCierre,"hh:mm a"));
                        } else {
                            SpinnerModel inicio = new SpinnerDateModel(hInicio, null, null, Calendar.MINUTE);
                            SpinnerModel cierre = new SpinnerDateModel(hCierre, null, null, Calendar.MINUTE);
                            panelEvento.spinnerHoraInicio.setModel(inicio);
                            panelEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraInicio,"hh:mm a"));
                            panelEvento.spinnerHoraCierre.setModel(cierre);
                            panelEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelEvento.spinnerHoraCierre,"hh:mm a"));
                        }
                        panelEvento.comboBoxMetodo.setSelectedIndex(buscarMetodo(ev.getMetodoDePago()));
                        if(ev.isPagado()){
                            panelEvento.comboBoxEstatus.setForeground(new Color(0,255,25));
                            panelEvento.comboBoxEstatus.setSelectedIndex(2);
                        } else{
                            panelEvento.comboBoxEstatus.setForeground(new Color(244,68,68));
                            panelEvento.comboBoxEstatus.setSelectedIndex(1);
                        }
                        panelEvento.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoAdulto(), 0, 100, 1));
                        panelEvento.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(ev.getMontoNino(), 0, 100, 1));
                        panelEvento.spinnerNumAdultos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroAdultos(), 0, 100, 1));
                        panelEvento.spinnerNumNinos.setModel(new javax.swing.SpinnerNumberModel(ev.getNumeroNinos(), 0, 99, 1));
                        int costoTotal = (int)ev.getCostoTotal();
                        panelEvento.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                        panelEvento.txtMotivo.setText(ev.getMotivoDeEvento());
                        // Limpieza, agregacion del panel de evento ejecutivo y revalidacion del frame
                        panelEvento.setSize(330,540);
                        panelEvento.setLocation(0,0);
                        frameDetalles.panelDatosR.removeAll();
                        frameDetalles.panelDatosR.add(panelEvento);
                        frameDetalles.panelDatosR.revalidate();
                        frameDetalles.panelDatosR.repaint();
                        frameDetalles.setVisible(true);
                    }
                });
                // Se agrega el evento familiar en el panel de reservaciones en el panel de consulta
                if(filtro.isEmpty()){
                    panelConsultar.panelReservaciones.add(panelR);
                }
                if(filtro.equals("Nombre")){
                    String nombre = e.getNombre().toLowerCase();
                    String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                    if(nombre.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
                if(filtro.equals("Cédula o RIF")){
                    String rif = e.getRif();
                    String entrada = panelConsultar.txtBuscar.getText();
                    if(rif.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
                if(filtro.equals("Estado de pago")){
                    String pagado = panelR.labelEstatus.getText().toLowerCase();
                    String entrada = panelConsultar.txtBuscar.getText().toLowerCase();
                    if(pagado.contains(entrada)){
                        panelConsultar.panelReservaciones.add(panelR);
                    }
                }
                 
            }
        }
        // Revalidacion del panel de reservaciones
        panelConsultar.panelReservaciones.revalidate();
        panelConsultar.panelReservaciones.repaint();
    }
    
    //--------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------
    //Método que cargar las reservaciones según el filtro elegido, esto reúne los metodos desarrollados anteriormente
    public void cargarReservaciones(String filtro){
        //Carga las reservaciones según la fecha y quita los elementos que no le corresponden en la interfaz
        if(panelConsultar.botonFecha.isSelected()){
            panelConsultar.labelTotal1.setVisible(true);
            panelConsultar.labelTotal2.setVisible(true);
            panelConsultar.labelPersonas.setVisible(true);
            panelConsultar.labelReservaciones.setVisible(true);
            panelConsultar.labelCantidadPersonas.setVisible(true);
            panelConsultar.labelCantidadReservaciones.setVisible(true);
            panelConsultar.calendarioReservaciones.setVisible(true);
            panelConsultar.comboBoxTipoR.setVisible(false);
            panelConsultar.labelInputTipoR.setVisible(false);
            Date fechaR = panelConsultar.calendarioReservaciones.getDate();
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            String fechaReservacion = formatter.format(fechaR);
            panelConsultar.labelCantidadPersonas.setText(String.valueOf(reservacionG.calcularTotalPersonas(listaReservaciones, fechaReservacion)));
            panelConsultar.labelCantidadReservaciones.setText(String.valueOf(reservacionG.calcularTotalReservaciones(listaReservaciones, fechaReservacion)));
            cargarReservacionesPorFecha(fechaReservacion, filtro);
        }
        //Carga las reservación se´según el tipo de reservación
        if(panelConsultar.botonTipoR.isSelected()){
            panelConsultar.labelTotal1.setVisible(false);
            panelConsultar.labelTotal2.setVisible(false);
            panelConsultar.labelPersonas.setVisible(false);
            panelConsultar.labelReservaciones.setVisible(false);
            panelConsultar.labelCantidadPersonas.setVisible(false);
            panelConsultar.labelCantidadReservaciones.setVisible(false);
            panelConsultar.calendarioReservaciones.setVisible(true);
            panelConsultar.calendarioReservaciones.setVisible(false);
            panelConsultar.comboBoxTipoR.setVisible(true);
            panelConsultar.labelInputTipoR.setVisible(true);
            if(panelConsultar.comboBoxTipoR.getSelectedItem().toString().equals("Reservaciones generales")){
                cargarReservacionesGenerales(filtro);
            } else {
                cargarReservacionesPrivadas(filtro);
            }
        }
        //Carga todas las reservaciones registradas en el sistema
        if(panelConsultar.botonTodas.isSelected()){
            panelConsultar.labelTotal1.setVisible(false);
            panelConsultar.labelTotal2.setVisible(false);
            panelConsultar.labelPersonas.setVisible(false);
            panelConsultar.labelReservaciones.setVisible(false);
            panelConsultar.labelCantidadPersonas.setVisible(false);
            panelConsultar.labelCantidadReservaciones.setVisible(false);
            panelConsultar.calendarioReservaciones.setVisible(false);
            panelConsultar.comboBoxTipoR.setVisible(false);
            panelConsultar.labelInputTipoR.setVisible(false);
            cargarTodasReservaciones(filtro);
        }
    }
    
    //Este metodo verfica si hay campos vacios en el Frame Verificar cupo
    public boolean camposVaciosFrameVerificar(){
        return (frameVerificar.jDateFechaIngreso.getDate()==null)||(frameVerificar.comboBoxTipoReservacion.getSelectedIndex()==0)
                ||frameVerificar.txtCedula.getText().isEmpty() || frameVerificar.txtCedula.getText().equals("Cédula o RIF");
    }
    
    //Este metodo resetea los campos del frame Verficar cup
    public void resetearCamposFrameVerificar(){
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);  
        calendar.set(Calendar.MILLISECOND, 0);
        fechaActual = calendar.getTime();
        frameVerificar.jDateFechaIngreso.setDate(fechaActual);
        frameVerificar.comboBoxTipoReservacion.setSelectedIndex(0);
        frameVerificar.txtCedula.setForeground(Color.LIGHT_GRAY);
        frameVerificar.txtCedula.setText("Cédula o RIF");
        frameVerificar.spinnerNumNinos.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));
        frameVerificar.spinnerNumAdultos.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
    }
    
    //Método que verifica si hay campos vacios en el panel destinado a registrar el comensal
    public boolean camposVaciosPanelRegistrarComensal(){
        return panelRegistrarComensal.txtNombre.getText().isEmpty() || panelRegistrarComensal.txtNombre.getText().equals("Nombre")
                ||panelRegistrarComensal.txtApellido.getText().isEmpty() || panelRegistrarComensal.txtApellido.getText().equals("Apellido")
                ||panelRegistrarComensal.txtCorreo.getText().isEmpty() || panelRegistrarComensal.txtCorreo.getText().equals("Correo")
                ||panelRegistrarComensal.txtTelefono.getText().isEmpty() || panelRegistrarComensal.txtTelefono.getText().equals("Teléfono")
                ||panelRegistrarComensal.comboBoxMunicipio.getSelectedIndex()==0;
    }
    
    //Este metodo resetea los campos que se encuentran en el panel de registrar comensal
    public void resetearCamposPanelRegistrarComensal(){
        panelRegistrarComensal.txtNombre.setForeground(Color.LIGHT_GRAY);
        panelRegistrarComensal.txtApellido.setForeground(Color.LIGHT_GRAY);
        panelRegistrarComensal.txtCorreo.setForeground(Color.LIGHT_GRAY);
        panelRegistrarComensal.txtCedula.setForeground(Color.LIGHT_GRAY);
        panelRegistrarComensal.txtTelefono.setForeground(Color.LIGHT_GRAY);
        panelRegistrarComensal.txtNombre.setText("Nombre");
        panelRegistrarComensal.txtApellido.setText("Apellido");
        panelRegistrarComensal.txtCorreo.setText("Correo");
        panelRegistrarComensal.txtCedula.setText("Cédula");
        panelRegistrarComensal.txtTelefono.setText("Teléfono");
        panelRegistrarComensal.comboBoxMunicipio.setSelectedIndex(0);
        panelRegistrarComensal.spinnerEdad.setModel(new javax.swing.SpinnerNumberModel(30, 18, 90, 1));
        panelRegistrarComensal.comboBoxSexo.setSelectedIndex(0);
    }
    
    //Método que verifica si hay campos vacios en el panel destinado a registrar la empresa
    public boolean camposVaciosPanelRegistrarEmpresa(){
        return panelRegistrarEmpresa.txtNombre.getText().isEmpty() || panelRegistrarEmpresa.txtNombre.getText().equals("Nombre de la Empresa")
                ||panelRegistrarEmpresa.txtRIF.getText().isEmpty() || panelRegistrarEmpresa.txtRIF.getText().equals("RIF")
                ||panelRegistrarEmpresa.txtCorreo.getText().isEmpty() || panelRegistrarEmpresa.txtCorreo.getText().equals("Correo")
                ||panelRegistrarEmpresa.txtTelefono.getText().isEmpty() || panelRegistrarEmpresa.txtTelefono.getText().equals("Teléfono")
                ||panelRegistrarEmpresa.comboBoxMunicipio.getSelectedIndex()==0;
    }
    
    //Este metodo resetea los campos que se encuentran en el panel de registrar empresa
    public void resetearCamposPanelRegistrarEmpresa(){
        panelRegistrarEmpresa.txtNombre.setForeground(Color.LIGHT_GRAY);
        panelRegistrarEmpresa.txtRIF.setForeground(Color.LIGHT_GRAY);
        panelRegistrarEmpresa.txtCorreo.setForeground(Color.LIGHT_GRAY);
        panelRegistrarEmpresa.txtTelefono.setForeground(Color.LIGHT_GRAY);
        panelRegistrarEmpresa.txtNombre.setText("Nombre de la Empresa");
        panelRegistrarEmpresa.txtRIF.setText("RIF");
        panelRegistrarEmpresa.txtCorreo.setText("Correo");
        panelRegistrarEmpresa.comboBoxMunicipio.setSelectedIndex(0);
        panelRegistrarEmpresa.txtTelefono.setText("Teléfono");
    }
    
    //Método que verifica si hay campos vacios en el panel destinado a registrar la reservacion
    public boolean camposVaciosPanelRegistrarReservacion(){
        return panelRegistrarReservacion.comboBoxMetodo.getSelectedIndex()==0 ||
                panelRegistrarReservacion.comboBoxEstatus.getSelectedIndex()==0;
    }
    
    //Este metodo resetea los campos que se encuentran en el panel de registrar reservación
    public void resetearCamposPanelRegistrarReservacion(){
        panelRegistrarReservacion.comboBoxMetodo.setSelectedIndex(0);
        panelRegistrarReservacion.comboBoxEstatus.setSelectedIndex(0);
        panelRegistrarReservacion.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(15, 0, 100, 1));
        panelRegistrarReservacion.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(55, 0, 100, 1));
    }
    
    //Método que verifica si hay campos vacios en el panel destinado a registrar el evento
    public boolean camposVaciosPanelRegistrarEvento(){
        return panelRegistrarEvento.comboBoxMetodo.getSelectedIndex()==0 ||
                panelRegistrarEvento.comboBoxEstatus.getSelectedIndex()==0 ||
                panelRegistrarEvento.txtMotivo.getText().isEmpty() || panelRegistrarEvento.txtMotivo.getText().equals("Motivo del evento");
    }
    
    //Este metodo resetea los campos que se encuentran en el panel de registrar evento
    public void resetearCamposPanelRegistrarEvento(){
        panelRegistrarEvento.txtMotivo.setForeground(Color.LIGHT_GRAY);
        panelRegistrarEvento.txtMotivo.setText("Motivo del evento");
        panelRegistrarEvento.spinnerHoraInicio.setModel(horaModelIncio);
        panelRegistrarEvento.spinnerHoraInicio.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraInicio,"hh:mm a"));
        panelRegistrarEvento.spinnerHoraCierre.setModel(horaModelCierre);
        panelRegistrarEvento.spinnerHoraCierre.setEditor(new JSpinner.DateEditor(panelRegistrarEvento.spinnerHoraCierre,"hh:mm a"));
        panelRegistrarEvento.comboBoxMetodo.setSelectedIndex(0);
        panelRegistrarEvento.comboBoxEstatus.setSelectedIndex(0);
        panelRegistrarEvento.spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(15, 0, 100, 1));
        panelRegistrarEvento.spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(55, 0, 100, 1));
    }
    
    public boolean camposVaciosPanelReservacionGeneral(){
        return panelReservacionG.jDateFechaR.getDate() == null ||
               panelReservacionG.comboBoxEstatus.getSelectedIndex() == 0 ||
               panelReservacionG.comboBoxMetodo.getSelectedIndex() == 0;
    }
    
    public boolean camposVaciosPanelEvento(){
        return panelEvento.jDateFechaR.getDate() == null ||
               panelEvento.comboBoxEstatus.getSelectedIndex() == 0 ||
               panelEvento.comboBoxMetodo.getSelectedIndex() == 0 ||
               panelEvento.txtMotivo.getText().isEmpty();
    }
    
    /*
    Metodo que manda un correo de confirmación de la reservación a la persona o empresa que hizo la reservación, 
    Adjuntado dstos importantes como fecha, número de personas y costo
    */
    public void mandarCorreoConfirmacion(String correo, String fecha, String apellido, char sexo, int numeroTotal, int costoTotal){
        try {
            String mensaje;
            mensaje = switch (sexo) {
                case 'F' -> "Estimada Sra. " + apellido;
                case 'M' -> "Estimado Sr. " + apellido;
                default -> "Para: " + apellido;
            };
            mensaje= mensaje
                    + "\n\nNos complace informarle que la reservación solicitada ha sido confirmada, con los siguientes datos:" 
                    +"\n\nFecha: " + fecha
                    +"\nNro. de personas: " + numeroTotal
                    +"\nCosto: " + costoTotal +"$"
                    +"\n\nPuede enviar comprobante de pago a este mismo correo"
                    + "\n\n\nRestaurantManagemenet - Salumificio I Visconti"
                    +"\nReservaciones"
                    +"\nTelefono: 0412-3243511";            
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
                mail.addRecipient(Message.RecipientType.TO, new InternetAddress (correo));
                mail.setSubject("Confirmación de Reservación");
                mail.setText(mensaje);
                JOptionPane.showMessageDialog(null, "Espere un momento... Estamos procesando su solicitud", "Confirmación de Reservación", 1);
                try (Transport transportar = sesion.getTransport("smtp")) {
                    transportar.connect(correoRestaurant,claveRestaurant);
                    transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                }
                JOptionPane.showMessageDialog(null, "Listo! La confirmación ha sido enviada", "Confirmación de Reservación", 1);
            } catch (HeadlessException | MessagingException ex) {
                JOptionPane.showMessageDialog(null, "El correo no pudo ser enviado. Intente de nuevo", "Error", 0);
            }                
        } catch (NullPointerException ne){
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BOTONES DEL PANEL: panelSistema
        
        //Boton icono de Empleado
        if(e.getSource()==panelSistema.botonReservaciones){
            /*Resetea todos los paneles y se inicializan todos los objetos de la gestión de reservación, 
            ya que esto se usan para registrar los datos*/
            resetearCamposFrameVerificar();
            resetearCamposPanelRegistrarComensal();
            resetearCamposPanelRegistrarEmpresa();
            resetearCamposPanelRegistrarReservacion();
            resetearCamposPanelRegistrarEvento();
            tipoReservacion = 0;
            Comensal c = new Comensal();
            comensal = c;
            Empresa em = new Empresa();
            empresa = em;
            ReservacionGeneral rG = new ReservacionGeneral();
            reservacionG = rG;
            EventoFamiliar eF = new EventoFamiliar();
            eventoF = eF;
            EventoEjecutivo evE = new EventoEjecutivo();
            eventoE = evE;
            /*Luego se cargan las reservación según como haya quedado el filtro en el panel Consultar*/
            panelConsultar.botonFecha.setSelected(true);
            cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
            panelConsultar.txtBuscar.setText("");
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: Consultar Reservaciones
        
        //Boton de Ingresar Reservacion
        /*Aqui se abre el frame para verificar cupo, antes de tener que registrar todos los datos. Solo con algunos pocos 
        podemos determinar si hay suficiente espacio para la nueva reseravación*/
        if(e.getSource()==panelConsultar.botonIngresarR){
            frameVerificar.jDateFechaIngreso.setDate(panelConsultar.calendarioReservaciones.getDate());
            frameVerificar.setTitle("Verificar cupo");
            frameVerificar.setSize(358, 535);
            frameVerificar.setLocationRelativeTo(null);
            frameVerificar.setResizable(false);
            frameVerificar.setVisible(true);
        }
        
        /*Estos son los actionListener de los Radio Button, y es para filtrar las reservaciones*/
        
        //Filtra las reservaciones por fecha
        if(e.getSource()==panelConsultar.botonFecha){
            cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
            panelConsultar.txtBuscar.setText("");
        }
        
        //Filtra las reservaciones por tipo de reservación
        if(e.getSource()==panelConsultar.botonTipoR){
            cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
            panelConsultar.txtBuscar.setText("");
        }
        
        //No hace ningún filtro si no que muestra todas
        if(e.getSource()==panelConsultar.botonTodas){
            cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
            panelConsultar.txtBuscar.setText("");
        }
        
        
        //BOTONES DEL PANEL: Frame Verificar Cupo
        
        //Boton verificar
        if(e.getSource()==frameVerificar.botonVerificar){
            //Primero valida si los campos del Frame verificar están vacíos
            if(!camposVaciosFrameVerificar()){
                Date fechaR = frameVerificar.jDateFechaIngreso.getDate();
                Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                String fechaReservacion = formatter.format(fechaR);
                String identidad = frameVerificar.txtCedula.getText();
                int numeroNinos = (int) frameVerificar.spinnerNumNinos.getValue();
                int numeroAdultos = (int) frameVerificar.spinnerNumAdultos.getValue();
                //Comprueba que la fecha no sea anterior a la fecha actual
                if (!fechaR.before(fechaActual)){
                    //Luego se valida que la cantidad de personas no se exceda de la capacidad del local
                    if(reservacionG.validarCantidadPersonas(numeroNinos, numeroAdultos)){
                        //y se realiza un case con los distintos procedimientos a seeguir según el tipo de reservación
                        switch (frameVerificar.comboBoxTipoReservacion.getSelectedItem().toString()) {
                            case "Reservación General" -> { //en caso de una reservación general
                                //Se valida y verifica la cédula del comensal
                                if(comensal.validarCedula(identidad)){
                                    //Verifica que el día sea entre jueves y sábado
                                    if(reservacionG.validarDia(fechaR)){
                                        //Verifica que la cédula del comensal no esté repetida por día
                                        if(!reservacionG.cedulaRepetidaDia(listaReservaciones, fechaReservacion, identidad)){
                                            //Y finalmente valida si hay cupo para la cantidad de personas y fecha indicada
                                            if(reservacionG.validarCupo(numeroNinos, numeroAdultos, listaReservaciones, fechaReservacion, capacidad)){
                                                frameVerificar.dispose();
                                                resetearCamposFrameVerificar();
                                                tipoReservacion = 1;
                                                reservacionG.setCedula(identidad);
                                                reservacionG.setFechaDeReservacion(fechaReservacion);
                                                reservacionG.setNumeroAdultos(numeroAdultos);
                                                reservacionG.setNumeroNinos(numeroNinos);
                                                //Si el comensal está repetido no se le vuelve a pedir los datos del comensal
                                                if(comensal.comensalRepetido(listaComensales, identidad)){
                                                    comensal = comensal.buscarComensal(listaComensales, identidad);
                                                    panelRegistrarReservacion.labelNumeroNinos.setText(String.valueOf(numeroNinos));
                                                    panelRegistrarReservacion.labelNumeroAdultos.setText(String.valueOf(numeroAdultos));
                                                    int montoAdulto = (int) panelRegistrarReservacion.spinnerMontoAdulto.getValue();
                                                    int montoNino = (int)panelRegistrarReservacion.spinnerMontoNino.getValue();
                                                    int costoTotal = reservacionG.calcularCostoReservacion(numeroAdultos, numeroNinos, montoAdulto, montoNino);
                                                    panelRegistrarReservacion.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                                                    panelRegistrarReservacion.setSize(926,720);
                                                    panelRegistrarReservacion.setLocation(0,0);
                                                    panelSistema.panelPrincipal.removeAll();
                                                    panelSistema.panelPrincipal.add(panelRegistrarReservacion);
                                                    panelSistema.panelPrincipal.revalidate();
                                                    panelSistema.panelPrincipal.repaint();
                                                    JOptionPane.showMessageDialog(null, "Si hay cupo para el comensal "+ comensal.getNombre() + " " + comensal.getApellido() +".\nProceda a terminar el registro", "", 1);
                                                } else { //Si no, se les pide antes de registrar la reservación
                                                    comensal.setCedula(identidad);
                                                    panelRegistrarComensal.txtCedula.setEditable(false);
                                                    panelRegistrarComensal.txtCedula.setText(identidad);
                                                    panelRegistrarComensal.setSize(926,720);
                                                    panelRegistrarComensal.setLocation(0,0);
                                                    panelSistema.panelPrincipal.removeAll();
                                                    panelSistema.panelPrincipal.add(panelRegistrarComensal);
                                                    panelSistema.panelPrincipal.revalidate();
                                                    panelSistema.panelPrincipal.repaint();
                                                    JOptionPane.showMessageDialog(null, """
                                                                                        Si hay cupo.
                                                                                        Rellene los datos del comensal para poder registrar la reservaci\u00f3n""",  "", 1);
                                                }
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Las reservaciones generales sólo son entre jueves y sabado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                            } //En caso de un evento familiar
                            case "Evento Familiar" -> {
                                //Se valida y verifica de nuevo la cédula del comensal
                                if(comensal.validarCedula(identidad)){
                                    //Se verifica si no hay reservacions generales ese día o hay otro evento
                                    if(eventoP.validarCupo(listaReservaciones, fechaReservacion)){
                                        frameVerificar.dispose();
                                        resetearCamposFrameVerificar();
                                        tipoReservacion = 2;
                                        eventoF.setCedula(identidad);
                                        eventoF.setFechaDeReservacion(fechaReservacion);
                                        eventoF.setNumeroAdultos(numeroAdultos);
                                        eventoF.setNumeroNinos(numeroNinos);
                                        //Si el comensal está repetido no se le vuelve a pedir los datos del comensal
                                        if(comensal.comensalRepetido(listaComensales, identidad)){
                                            comensal = comensal.buscarComensal(listaComensales, identidad);
                                            panelRegistrarEvento.labelNumeroNinos.setText(String.valueOf(numeroNinos));
                                            panelRegistrarEvento.labelNumeroAdultos.setText(String.valueOf(numeroAdultos));
                                            int montoAdulto = (int) panelRegistrarEvento.spinnerMontoAdulto.getValue();
                                            int montoNino = (int)panelRegistrarEvento.spinnerMontoNino.getValue();
                                            int costoTotal = eventoP.calcularCostoReservacion(numeroAdultos, numeroNinos, montoAdulto, montoNino);
                                            panelRegistrarEvento.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                                            panelRegistrarEvento.setSize(926,720);
                                            panelRegistrarEvento.setLocation(0,0);
                                            panelSistema.panelPrincipal.removeAll();
                                            panelSistema.panelPrincipal.add(panelRegistrarEvento);
                                            panelSistema.panelPrincipal.revalidate();
                                            panelSistema.panelPrincipal.repaint();
                                            JOptionPane.showMessageDialog(null, "Si hay cupo para el comensal "+ comensal.getNombre() + " " + comensal.getApellido() +".\nProceda a terminar el registro", "", 1);
                                        } else { //Si no, se les pide antes de registrar la reservación
                                            comensal.setCedula(identidad);
                                            panelRegistrarComensal.txtCedula.setEditable(false);
                                            panelRegistrarComensal.txtCedula.setText(identidad);
                                            panelRegistrarComensal.setSize(926,720);
                                            panelRegistrarComensal.setLocation(0,0);
                                            panelSistema.panelPrincipal.removeAll();
                                            panelSistema.panelPrincipal.add(panelRegistrarComensal);
                                            panelSistema.panelPrincipal.revalidate();
                                            panelSistema.panelPrincipal.repaint();
                                            JOptionPane.showMessageDialog(null, """
                                                                                Si hay cupo.
                                                                                Rellene los datos del comensal para poder registrar la reservaci\u00f3n""", "", 1);
                                        }
                                    }
                                }
                            }
                            default -> { //en caso de un evento ejecutivo
                                //se valida y se verifica le rif
                                if(empresa.validarRif(identidad)){
                                    //Se verifica si no hay reservacions generales ese día o hay otro evento
                                    if(eventoP.validarCupo(listaReservaciones, fechaReservacion)){
                                        frameVerificar.dispose();
                                        resetearCamposFrameVerificar();
                                        tipoReservacion = 3;
                                        eventoE.setRif(identidad);
                                        eventoE.setFechaDeReservacion(fechaReservacion);
                                        eventoE.setNumeroAdultos(numeroAdultos);
                                        eventoE.setNumeroNinos(numeroNinos);
                                        if(empresa.empresaRepetida(listaEmpresas, identidad)){//Si la empresa está repetida no se le vuelve a pedir los datos de la empresa
                                            empresa.buscarEmpresa(listaEmpresas, identidad);
                                            panelRegistrarEvento.labelNumeroNinos.setText(String.valueOf(numeroNinos));
                                            panelRegistrarEvento.labelNumeroAdultos.setText(String.valueOf(numeroAdultos));
                                            int montoAdulto = (int) panelRegistrarEvento.spinnerMontoAdulto.getValue();
                                            int montoNino = (int)panelRegistrarEvento.spinnerMontoNino.getValue();
                                            int costoTotal = eventoP.calcularCostoReservacion(numeroAdultos, numeroNinos, montoAdulto, montoNino);
                                            panelRegistrarEvento.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                                            panelRegistrarEvento.setSize(926,720);
                                            panelRegistrarEvento.setLocation(0,0);
                                            panelSistema.panelPrincipal.removeAll();
                                            panelSistema.panelPrincipal.add(panelRegistrarEvento);
                                            panelSistema.panelPrincipal.revalidate();
                                            panelSistema.panelPrincipal.repaint();
                                            JOptionPane.showMessageDialog(null, "Si hay cupo para la empresa "+ empresa.getNombre() +".\nProceda a terminar el registro", "", 1);
                                        } else{ //Si no, se les pide antes de registrar la reservación
                                            empresa.setRif(identidad);
                                            panelRegistrarEmpresa.txtRIF.setEditable(false);
                                            panelRegistrarEmpresa.txtRIF.setText(identidad);
                                            panelRegistrarEmpresa.setSize(926,720);
                                            panelRegistrarEmpresa.setLocation(0,0);
                                            panelSistema.panelPrincipal.removeAll();
                                            panelSistema.panelPrincipal.add(panelRegistrarEmpresa);
                                            panelSistema.panelPrincipal.revalidate();
                                            panelSistema.panelPrincipal.repaint();
                                            JOptionPane.showMessageDialog(null, """
                                                                                Si hay cupo.
                                                                                Rellene los datos de la empresa para poder registrar la reservaci\u00f3n""", "", 1);
                                        }
                                    }
                                }
                            }
                        }                        
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La fecha de la reservación no puede ser una fecha anterior al dia de hoy", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos correctamente antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //BOTONES DEL PANEL: Panel Registrar Comensal
        
        //Boton regresar
        //Esto es en caso de que quiera regresarse y cancelar el proceso del registro de la reservación
        if(e.getSource()==panelRegistrarComensal.botonRegresar){
            int conf = JOptionPane.showOptionDialog(null, "¿Desea cancelar la reservación?","Cancelar Reservación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            if (conf == 0) {
                Comensal c = new Comensal();
                comensal = c;
                if(tipoReservacion == 1){
                    ReservacionGeneral rG = new ReservacionGeneral();
                    reservacionG = rG;
                } else {
                    EventoFamiliar eF = new EventoFamiliar();
                    eventoF = eF;
                }
                tipoReservacion = 0;
                resetearCamposPanelRegistrarComensal();
                cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                panelConsultar.setSize(926,720);
                panelConsultar.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelConsultar);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();
            } 
        }
        
        //Boton registrar
        /*En este botón se validan los datos del comensal para añadirlo a la lista de comensales y a la base de datos
        Luego de este se puede proceder a realizar la reservación, ya sea general o privada*/
        if(e.getSource()==panelRegistrarComensal.botonRegistrar){
            if(!camposVaciosPanelRegistrarComensal()){
                String nombre = panelRegistrarComensal.txtNombre.getText();
                String apellido = panelRegistrarComensal.txtApellido.getText();
                String correo = panelRegistrarComensal.txtCorreo.getText();
                String cedula = panelRegistrarComensal.txtCedula.getText();
                String telefono = panelRegistrarComensal.txtTelefono.getText();
                String ubicacion = panelRegistrarComensal.comboBoxMunicipio.getSelectedItem().toString();
                char sexo = panelRegistrarComensal.comboBoxSexo.getSelectedItem().toString().charAt(0);
                int edad = (int) panelRegistrarComensal.spinnerEdad.getValue();
                if(comensal.validarDatosPersona(nombre, apellido, cedula, correo, telefono)){
                    comensal.setNombre(nombre);
                    comensal.setApellido(apellido);
                    comensal.setCorreo(correo);
                    comensal.setCedula(cedula);
                    comensal.setTelefono(telefono);
                    comensal.setUbicacion(ubicacion);
                    comensal.setSexo(sexo);
                    comensal.setEdad(edad);
                    listaComensales.add(comensal);
                    Comensal c = new Comensal();
                    comensal = c;
                    try{ 
                        PS_COMENSAL=CN.getConnection().prepareStatement(SQL_INSERT_COMENSAL);
                        PS_COMENSAL.setString(1,cedula);
                        PS_COMENSAL.setString(2,nombre);
                        PS_COMENSAL.setString(3,apellido);
                        PS_COMENSAL.setString(4,telefono);
                        PS_COMENSAL.setString(5,correo);
                        PS_COMENSAL.setInt(6,edad);
                        PS_COMENSAL.setString(7,ubicacion);
                        PS_COMENSAL.setString(8,String.valueOf(sexo));
                                                
                        int res = PS_COMENSAL.executeUpdate();
                        if (res > 0){
                            JOptionPane.showMessageDialog(null, "El comensal ha sido registrado con éxito. Continúe con el registro", "", 1);
                        }
                    }catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Error al guardar los datos del empleado en la base de datos: " +ex.getMessage(), "Error", 0);
                    }finally{
                            PS_COMENSAL=null;
                            CN.desconectar();
                    }
                    //Según el tipo de reservación que se esté haciendo se muestran los paneles correspondientes
                    if(tipoReservacion==1){ //si es una reservación general
                        panelRegistrarReservacion.labelNumeroNinos.setText(String.valueOf(reservacionG.getNumeroNinos()));
                        panelRegistrarReservacion.labelNumeroAdultos.setText(String.valueOf(reservacionG.getNumeroAdultos()));
                        int montoAdulto = (int) panelRegistrarReservacion.spinnerMontoAdulto.getValue();
                        int montoNino = (int)panelRegistrarReservacion.spinnerMontoNino.getValue();
                        int costoTotal = reservacionG.calcularCostoReservacion(reservacionG.getNumeroAdultos(), reservacionG.getNumeroNinos(), montoAdulto, montoNino);
                        panelRegistrarReservacion.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                        panelRegistrarReservacion.setSize(926,720);
                        panelRegistrarReservacion.setLocation(0,0);
                        panelSistema.panelPrincipal.removeAll();
                        panelSistema.panelPrincipal.add(panelRegistrarReservacion);
                        panelSistema.panelPrincipal.revalidate();
                        panelSistema.panelPrincipal.repaint();
                    } else { //Si es un evento familiar
                        panelRegistrarEvento.labelNumeroNinos.setText(String.valueOf(eventoF.getNumeroNinos()));
                        panelRegistrarEvento.labelNumeroAdultos.setText(String.valueOf(eventoF.getNumeroAdultos()));
                        int montoAdulto = (int) panelRegistrarEvento.spinnerMontoAdulto.getValue();
                        int montoNino = (int)panelRegistrarEvento.spinnerMontoNino.getValue();
                        int costoTotal = eventoP.calcularCostoReservacion(eventoF.getNumeroAdultos(), eventoF.getNumeroNinos(), montoAdulto, montoNino);
                        panelRegistrarEvento.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                        panelRegistrarEvento.setSize(926,720);
                        panelRegistrarEvento.setLocation(0,0);
                        panelSistema.panelPrincipal.removeAll();
                        panelSistema.panelPrincipal.add(panelRegistrarEvento);
                        panelSistema.panelPrincipal.revalidate();
                        panelSistema.panelPrincipal.repaint();
                    }
                    resetearCamposPanelRegistrarComensal();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos correctamente antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                
            }
        }
        
        //BOTONES DEL PANEL: Panel Registrar Empresa
        
        //Boton regresar
        //Esto es en caso de que quiera regresarse y cancelar el proceso del registro de la reservación
        if(e.getSource()==panelRegistrarEmpresa.botonRegresar){
            int conf = JOptionPane.showOptionDialog(null, "¿Desea cancelar la reservación?","Cancelar Reservación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            if (conf == 0) {
                Empresa em = new Empresa();
                empresa = em;
                EventoEjecutivo evE = new EventoEjecutivo();
                eventoE = evE;
                tipoReservacion = 0;
                cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                panelConsultar.setSize(926,720);
                panelConsultar.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelConsultar);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();
                resetearCamposPanelRegistrarEmpresa();
            } 
        }
        
        //Boton registrar
        /*En este botón se validan los datos de la empresa para añadirlo a la lista de empresass y a la base de datos
        Luego de este se puede proceder a realizar la reservación, en este caso es ajuro un evento ejecutivo el que se está haciendo*/
        if(e.getSource()==panelRegistrarEmpresa.botonRegistrar){
            if(!camposVaciosPanelRegistrarEmpresa()){
                String nombre = panelRegistrarEmpresa.txtNombre.getText();
                String rif = panelRegistrarEmpresa.txtRIF.getText();
                String correo = panelRegistrarEmpresa.txtCorreo.getText();
                String direccion = panelRegistrarEmpresa.comboBoxMunicipio.getSelectedItem().toString();
                String telefono = panelRegistrarEmpresa.txtTelefono.getText();
                if(empresa.validarDatosEmpresa(nombre, rif, correo, telefono)){
                    empresa.setNombre(nombre);
                    empresa.setRif(rif);
                    empresa.setCorreo(correo);
                    empresa.setDireccion(direccion);
                    empresa.setTelefono(telefono);
                    listaEmpresas.add(empresa);
                    Empresa em = new Empresa();
                    empresa = em;
                    try{
                            PS_EMPRESA=CN.getConnection().prepareStatement(SQL_INSERT_EMPRESA);
                            PS_EMPRESA.setString(1, rif);
                            PS_EMPRESA.setString(2, nombre);
                            PS_EMPRESA.setString(3, correo);
                            PS_EMPRESA.setString(4, telefono);
                            PS_EMPRESA.setString(5, direccion);
                            int res = PS_EMPRESA.executeUpdate();
                            if (res > 0){
                                JOptionPane.showMessageDialog(null, "La empresa ha sido registrada con éxito. Continúe con el registro", "", 1);
                            }
                        }catch(SQLException ex){
                            JOptionPane.showMessageDialog(null, "Error al guardar los datos de la empresa en la base de datos: " +ex.getMessage(), "Error", 0);
                        }
                        finally{
                            PS_EMPRESA=null;
                            CN.desconectar();
                        }
                    //Luego de registrar correctamente a la empresa se carga el panel para registrar el evento ejecutivo
                    panelRegistrarEvento.labelNumeroNinos.setText(String.valueOf(eventoE.getNumeroNinos()));
                    panelRegistrarEvento.labelNumeroAdultos.setText(String.valueOf(eventoE.getNumeroAdultos()));
                    int montoAdulto = (int) panelRegistrarEvento.spinnerMontoAdulto.getValue();
                    int montoNino = (int)panelRegistrarEvento.spinnerMontoNino.getValue();
                    int costoTotal = eventoP.calcularCostoReservacion(eventoE.getNumeroAdultos(), eventoE.getNumeroNinos(), montoAdulto, montoNino);
                    panelRegistrarEvento.labelMontoTotal.setText(String.valueOf(costoTotal)+ "$");
                    panelRegistrarEvento.setSize(926,720);
                    panelRegistrarEvento.setLocation(0,0);
                    panelSistema.panelPrincipal.removeAll();
                    panelSistema.panelPrincipal.add(panelRegistrarEvento);
                    panelSistema.panelPrincipal.revalidate();
                    panelSistema.panelPrincipal.repaint();
                    resetearCamposPanelRegistrarEmpresa();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos correctamente antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        
        //BOTONES DEL PANEL: Panel Registrar Reservacion
        
        //Boton regresar
        if(e.getSource()==panelRegistrarReservacion.botonRegresar){
            int conf = JOptionPane.showOptionDialog(null, "¿Desea cancelar la reservación?","Cancelar Reservación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            if (conf == 0) {
                ReservacionGeneral rG = new ReservacionGeneral();
                reservacionG = rG;
                tipoReservacion = 0;
                cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                panelConsultar.setSize(926,720);
                panelConsultar.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelConsultar);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();
                resetearCamposPanelRegistrarReservacion();
            }
        }
        
        //Boton registrar
        if(e.getSource()==panelRegistrarReservacion.botonRegistrar){
            if(!camposVaciosPanelRegistrarReservacion()){
                String metodo = panelRegistrarReservacion.comboBoxMetodo.getSelectedItem().toString();
                String estatus = panelRegistrarReservacion.comboBoxEstatus.getSelectedItem().toString();
                boolean pagado;
                if(estatus.equals("Pagado")){
                    pagado = true;
                } else {
                    pagado = false;
                }
                int montoAdulto = (int) panelRegistrarReservacion.spinnerMontoAdulto.getValue();
                int montoNino = (int) panelRegistrarReservacion.spinnerMontoNino.getValue();
                int numeroAdultos = reservacionG.getNumeroAdultos();
                int numeroNinos = reservacionG.getNumeroNinos();
                double costoTotal = reservacionG.calcularCostoReservacion(numeroAdultos, numeroNinos, montoAdulto, montoNino);
                reservacionG.setCostoTotal(costoTotal);
                reservacionG.setPagado(pagado);
                reservacionG.setMetodoDePago(metodo);
                reservacionG.setMontoAdulto(montoAdulto);
                reservacionG.setMontoNino(montoNino);
                
                try{
                    PS_RESERVACION = CN.getConnection().prepareStatement(SQL_INSERT_RESERVACION);
                    PS_RESERVACION.setString(1, reservacionG.getFechaDeReservacion());
                    PS_RESERVACION.setString(2,reservacionG.getMetodoDePago());
                    PS_RESERVACION.setInt(3, numeroAdultos);
                    PS_RESERVACION.setInt(4, numeroNinos);
                    PS_RESERVACION.setInt(5, reservacionG.getMontoAdulto());
                    PS_RESERVACION.setInt(6, reservacionG.getMontoNino());
                    PS_RESERVACION.setBoolean(7, reservacionG.isPagado());
                    PS_RESERVACION.setDouble(8, costoTotal);
                    PS_RESERVACION.setString(9, "");
                    PS_RESERVACION.setDate(10, null);
                    PS_RESERVACION.setDate(11, null);
                    PS_RESERVACION.setString(12, reservacionG.getCedula());
                    PS_RESERVACION.setString(13, "");
                        
                    int res = PS_RESERVACION.executeUpdate();
                    if (res > 0){
                        listaReservaciones.add(reservacionG);
                        Comensal c = comensal.buscarComensal(listaComensales, reservacionG.getCedula());
                        int op = JOptionPane.showOptionDialog(null, "La reservación ha sido registrada con éxito.\n¿Desea enviar un correo de confirmación?","Confirmacion de Reservación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                        if (op==0){
                            int numeroTotal = numeroNinos + numeroAdultos ;
                            mandarCorreoConfirmacion(c.getCorreo(),reservacionG.getFechaDeReservacion(),c.getApellido(),c.getSexo(),numeroTotal,(int)costoTotal);
                        }
                    }
                }catch(SQLException ex){
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Error al guardar los datos del empleado en la base de datos: " +ex.getMessage(), "Error", 0);
                }
                finally{
                    PS_RESERVACION=null;
                    CN.desconectar();
                }
                ReservacionGeneral rG = new ReservacionGeneral();
                reservacionG = rG;
                tipoReservacion = 0;
                resetearCamposPanelRegistrarReservacion();
                cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                panelConsultar.setSize(926,720);
                panelConsultar.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelConsultar);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos correctamente antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

        //BOTONES DEL PANEL: Panel Registrar Reservacion
        
        //Boton regresar
        if(e.getSource()==panelRegistrarEvento.botonRegresar){
            int conf = JOptionPane.showOptionDialog(null, "¿Desea cancelar la reservación?","Cancelar Reservación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
            if (conf == 0) {
                if(tipoReservacion == 2){
                    EventoFamiliar eF = new EventoFamiliar();
                    eventoF = eF;
                } else {
                    EventoEjecutivo evE = new EventoEjecutivo();
                    eventoE = evE;
                }
                tipoReservacion = 0;
                cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                panelConsultar.setSize(926,720);
                panelConsultar.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelConsultar);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();
                resetearCamposPanelRegistrarEvento();
            }
        }
        
        //Boton registrar
        if(e.getSource()==panelRegistrarEvento.botonRegistrar){
            if(!camposVaciosPanelRegistrarEvento()){
                String motivo = panelRegistrarEvento.txtMotivo.getText();
                Date hIni = (Date) panelRegistrarEvento.spinnerHoraInicio.getValue();
                Date hCie = (Date) panelRegistrarEvento.spinnerHoraCierre.getValue();
                Format formatter = new SimpleDateFormat("hh:mm a");
                String hInicio = formatter.format(hIni);
                String hCierre = formatter.format(hCie);
                String metodo = panelRegistrarEvento.comboBoxMetodo.getSelectedItem().toString();
                String estatus = panelRegistrarEvento.comboBoxEstatus.getSelectedItem().toString();
                boolean pagado;
                pagado = estatus.equals("Pagado");
                int montoAdulto = (int) panelRegistrarEvento.spinnerMontoAdulto.getValue();
                int montoNino = (int) panelRegistrarEvento.spinnerMontoNino.getValue();
                int numeroAdultos;
                int numeroNinos ;
                double costoTotal;
                if(tipoReservacion == 2){
                    numeroAdultos = eventoF.getNumeroAdultos();
                    numeroNinos = eventoF.getNumeroNinos();
                    costoTotal = eventoF.calcularCostoReservacion(numeroAdultos, numeroNinos, montoAdulto, montoNino);
                    eventoF.setMotivoDeEvento(motivo);
                    eventoF.setHoraInicio(hIni);
                    eventoF.setHoraCierre(hCie);
                    eventoF.setCostoTotal(costoTotal);
                    eventoF.setPagado(pagado);
                    eventoF.setMetodoDePago(metodo);
                    eventoF.setMontoAdulto(montoAdulto);
                    eventoF.setMontoNino(montoNino);
                    try{
                        PS_RESERVACION = CN.getConnection().prepareStatement(SQL_INSERT_RESERVACION);
                        PS_RESERVACION.setString(1, eventoF.getFechaDeReservacion());
                        PS_RESERVACION.setString(2,eventoF.getMetodoDePago());
                        PS_RESERVACION.setInt(3, numeroAdultos);
                        PS_RESERVACION.setInt(4, numeroNinos);
                        PS_RESERVACION.setInt(5, eventoF.getMontoAdulto());
                        PS_RESERVACION.setInt(6, eventoF.getMontoNino());
                        PS_RESERVACION.setBoolean(7, eventoF.isPagado());
                        PS_RESERVACION.setDouble(8, costoTotal);
                        PS_RESERVACION.setString(9, eventoF.getMotivoDeEvento());
                        PS_RESERVACION.setString(10, hInicio);
                        PS_RESERVACION.setString(11, hCierre);
                        PS_RESERVACION.setString(12, eventoF.getCedula());
                        PS_RESERVACION.setString(13, "");
                        
                        int res = PS_RESERVACION.executeUpdate();
                        if (res > 0){
                            Comensal c = comensal.buscarComensal(listaComensales, eventoF.getCedula());
                            listaReservaciones.add(eventoF);
                            int op = JOptionPane.showOptionDialog(null, "El reservación ha sido registrada con éxito.\n¿Desea enviar un correo de confirmación?","Confirmacion de Reservación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                            if (op==0){
                                int numeroTotal = numeroNinos + numeroAdultos ;
                                mandarCorreoConfirmacion(c.getCorreo(), eventoF.getFechaDeReservacion(),c.getApellido(),c.getSexo(),numeroTotal,(int)costoTotal);
                            }
                        }
                    }catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Error al guardar los datos del empleado en la base de datos: " +ex.getMessage(), "Error", 0);
                    }
                    finally{
                        PS_RESERVACION=null;
                        CN.desconectar();
                    }
                    EventoFamiliar eF = new EventoFamiliar();
                    eventoF = eF;
                } else{
                    numeroAdultos = eventoE.getNumeroAdultos();
                    numeroNinos = eventoE.getNumeroNinos();
                    costoTotal = eventoE.calcularCostoReservacion(numeroAdultos, numeroNinos, montoAdulto, montoNino);
                    eventoE.setMotivoDeEvento(motivo);
                    eventoE.setHoraInicio(hIni);
                    eventoE.setHoraCierre(hCie);
                    eventoE.setCostoTotal(costoTotal);
                    eventoE.setPagado(pagado);
                    eventoE.setMetodoDePago(metodo);
                    eventoE.setMontoAdulto(montoAdulto);
                    eventoE.setMontoNino(montoNino);
                    try{
                        PS_RESERVACION = CN.getConnection().prepareStatement(SQL_INSERT_RESERVACION);
                        PS_RESERVACION.setString(1, eventoE.getFechaDeReservacion());
                        PS_RESERVACION.setString(2,eventoE.getMetodoDePago());
                        PS_RESERVACION.setInt(3, numeroAdultos);
                        PS_RESERVACION.setInt(4, numeroNinos);
                        PS_RESERVACION.setInt(5, eventoE.getMontoAdulto());
                        PS_RESERVACION.setInt(6, eventoE.getMontoNino());
                        PS_RESERVACION.setBoolean(7, eventoE.isPagado());
                        PS_RESERVACION.setDouble(8, costoTotal);
                        PS_RESERVACION.setString(9, eventoE.getMotivoDeEvento());
                        PS_RESERVACION.setString(10,hInicio);
                        PS_RESERVACION.setString(11,hCierre);
                        PS_RESERVACION.setString(12, "");
                        PS_RESERVACION.setString(13, eventoE.getRif());
                        
                        int res = PS_RESERVACION.executeUpdate();
                        if (res > 0){
                            Empresa em = empresa.buscarEmpresa(listaEmpresas, eventoE.getRif()); 
                            listaReservaciones.add(eventoE);
                            int op = JOptionPane.showOptionDialog(null, "El reservación ha sido registrada con éxito.\n¿Desea enviar un correo de confirmación?","Confirmacion de Reservación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                            if (op==0){
                                int numeroTotal = numeroNinos + numeroAdultos ;
                                mandarCorreoConfirmacion(em.getCorreo(),reservacionG.getFechaDeReservacion(),em.getNombre(),'e',numeroTotal,(int)costoTotal);
                            }
                        }
                    }catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Error al guardar los datos del empleado en la base de datos: " +ex.getMessage(), "Error", 0);
                    }
                    finally{
                        PS_RESERVACION=null;
                        CN.desconectar();
                    }
                    EventoEjecutivo evE = new EventoEjecutivo();
                    eventoE = evE;
                }
                tipoReservacion = 0;
                cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                panelConsultar.setSize(926,720);
                panelConsultar.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelConsultar);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();
                resetearCamposPanelRegistrarEvento();
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos correctamente antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //BOTONES DEL FRAME: frame Ver detalles
        
        //Boton guardar
        if(e.getSource() == frameDetalles.botonGuardar){
            if(indicadorReservacion.equals("Reservación General")){
                if(!camposVaciosPanelReservacionGeneral()){
                    ReservacionGeneral rg = (ReservacionGeneral) reservacionG.buscarReservacion(listaReservaciones, indicadorFecha, indicadorClave);
                    Date fechaR = panelReservacionG.jDateFechaR.getDate();
                    Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaReservacion = formatter.format(fechaR);
                    String metodo = panelReservacionG.comboBoxMetodo.getSelectedItem().toString();
                    boolean pagado = true;
                    if(panelReservacionG.comboBoxEstatus.getSelectedIndex() == 1){
                        pagado = false;
                    }
                    int montoA = (int) panelReservacionG.spinnerMontoAdulto.getValue();
                    int montoN = (int) panelReservacionG.spinnerMontoNino.getValue();
                    int numeroA = (int) panelReservacionG.spinnerNumAdultos.getValue();
                    int numeroN = (int) panelReservacionG.spinnerNumNinos.getValue();
                    double costoTotal = reservacionG.calcularCostoReservacion(numeroA, numeroN, montoA, montoN);
                    if(reservacionG.validarCantidadPersonas(numeroN, numeroA)){
                        if(reservacionG.validarDia(fechaR)){
                            if(!reservacionG.cedulaRepetidaDia(listaReservaciones, fechaReservacion, indicadorClave, rg.getFechaDeReservacion())){
                                int actual =  rg.getNumeroAdultos() + rg.getNumeroNinos();
                                if(reservacionG.validarCupo(numeroN, numeroA, listaReservaciones, fechaReservacion, capacidad, actual, indicadorFecha)){
                                    try{
                                        String SQL_UPDATE_RESERVACION = "UPDATE Reservacion SET fechareserva=?, metodopago=?, numAdulto=?, numNino=?, montoAdulto=?, montoNino=?, "
                                        + "pagado=?, costoTotal=?, motivoEvento=?, horainicio=?, horafin=?, rifempresa=? WHERE fechareserva ='"+rg.getFechaDeReservacion()+"'and cedulacomensal ='"+rg.getCedula()+"'";
                                        PS_RESERVACION = CN.getConnection().prepareStatement(SQL_UPDATE_RESERVACION);
                                        PS_RESERVACION.setString(1, fechaReservacion);
                                        PS_RESERVACION.setString(2, metodo);
                                        PS_RESERVACION.setInt(3, numeroA);
                                        PS_RESERVACION.setInt(4, numeroN);
                                        PS_RESERVACION.setInt(5, montoA);
                                        PS_RESERVACION.setInt(6, montoN);
                                        PS_RESERVACION.setBoolean(7, pagado);
                                        PS_RESERVACION.setDouble(8, costoTotal);
                                        PS_RESERVACION.setString(9, "");
                                        PS_RESERVACION.setDate(10, null);
                                        PS_RESERVACION.setDate(11, null);
                                        PS_RESERVACION.setString(12, "");
 
                                        int res = PS_RESERVACION.executeUpdate();
                                        if (res > 0){
                                            reservacionG.modificarReservacionGeneral(indicadorFecha, indicadorClave, fechaReservacion, numeroA, numeroN, costoTotal, pagado, metodo, montoA, montoN, listaReservaciones);
                                            JOptionPane.showMessageDialog(null, "La reservación ha sido modificada con éxito", "", 1);
                                        }
                                    }catch(SQLException ex){
                                            JOptionPane.showMessageDialog(null, "Error al modificar la reservacion en la base de datos: " +ex.getMessage(), "Error", 0);
                                    }finally{
                                            PS_RESERVACION = null;
                                            CN.desconectar();
                                    }
                                }
                                frameDetalles.dispose();
                                cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Las reservaciones generales sólo son entre jueves y sabado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese todos los campos correctamente antes de modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
            if(indicadorReservacion.equals("Evento Ejecutivo")){
                if(!camposVaciosPanelEvento()){
                    EventoEjecutivo eEjecutivo = (EventoEjecutivo) eventoE.buscarReservacion(listaReservaciones, indicadorFecha, indicadorClave);
                    Date fechaR = panelEvento.jDateFechaR.getDate();
                    Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaReservacion = formatter.format(fechaR);
                    Date hIni = (Date) panelEvento.spinnerHoraInicio.getValue();
                    Date hCie = (Date) panelEvento.spinnerHoraCierre.getValue();
                    formatter = new SimpleDateFormat("hh:mm a");
                    String hInicio = formatter.format(hIni);
                    String hCierre = formatter.format(hCie);
                    String metodo = panelEvento.comboBoxMetodo.getSelectedItem().toString();
                    boolean pagado = true;
                    if(panelEvento.comboBoxEstatus.getSelectedIndex() == 1){
                        pagado = false;
                    }
                    int montoA = (int) panelEvento.spinnerMontoAdulto.getValue();
                    int montoN = (int) panelEvento.spinnerMontoNino.getValue();
                    int numeroA = (int) panelEvento.spinnerNumAdultos.getValue();
                    int numeroN = (int) panelEvento.spinnerNumNinos.getValue();
                    String motivo = panelEvento.txtMotivo.getText();
                    double costoTotal = eventoE.calcularCostoReservacion(numeroA, numeroN, montoA, montoN);
                    if(eventoE.validarCantidadPersonas(numeroN, numeroA)){
                            if(!eventoE.cedulaRepetidaDia(listaReservaciones, fechaReservacion, indicadorClave, eEjecutivo.getFechaDeReservacion())){
                                int actual =  eEjecutivo.getNumeroAdultos() + eEjecutivo.getNumeroNinos();
                                if(eventoE.validarCupo(listaReservaciones, fechaReservacion, actual, indicadorFecha)){
                                    try{
                                        String SQL_UPDATE_RESERVACION = "UPDATE Reservacion SET fechareserva=?, metodopago=?, numAdulto=?, numNino=?, montoAdulto=?, montoNino=?, "
                                        + "pagado=?, costoTotal=?, motivoEvento=?, horainicio=?, horafin=?, cedulacomensal=? WHERE fechareserva ='"+eEjecutivo.getFechaDeReservacion()+"'and rifempresa ='"+eEjecutivo.getRif()+"'";
                                        PS_RESERVACION = CN.getConnection().prepareStatement(SQL_UPDATE_RESERVACION);
                                        PS_RESERVACION.setString(1, fechaReservacion);
                                        PS_RESERVACION.setString(2, metodo);
                                        PS_RESERVACION.setInt(3, numeroA);
                                        PS_RESERVACION.setInt(4, numeroN);
                                        PS_RESERVACION.setInt(5, montoA);
                                        PS_RESERVACION.setInt(6, montoN);
                                        PS_RESERVACION.setBoolean(7, pagado);
                                        PS_RESERVACION.setDouble(8, costoTotal);
                                        PS_RESERVACION.setString(9, motivo);
                                        PS_RESERVACION.setString(10, hInicio);
                                        PS_RESERVACION.setString(11, hCierre);
                                        PS_RESERVACION.setString(12, "");
 
                                        int res = PS_RESERVACION.executeUpdate();
                                        if (res > 0){
                                            eEjecutivo.modificarReservacionEjecutiva(indicadorFecha, indicadorClave, motivo, hIni, hCie, fechaReservacion, numeroA, numeroN, costoTotal, pagado, metodo, montoA, montoN, listaReservaciones);
                                            JOptionPane.showMessageDialog(null, "La reservación ha sido modificada con éxito", "", 1);
                                        }
                                    }catch(SQLException ex){
                                            JOptionPane.showMessageDialog(null, "Error al modificar la reservacion en la base de datos: " +ex.getMessage(), "Error", 0);
                                    }finally{
                                            PS_RESERVACION = null;
                                            CN.desconectar();
                                    }
                                    frameDetalles.dispose();
                                    cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                                }
                            }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese todos los campos correctamente antes de modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
            if(indicadorReservacion.equals("Evento Familiar")){
                 if(!camposVaciosPanelEvento()){
                    EventoFamiliar eFamiliar = (EventoFamiliar) eventoF.buscarReservacion(listaReservaciones, indicadorFecha, indicadorClave);
                    Date fechaR = panelEvento.jDateFechaR.getDate();
                    Format formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaReservacion = formatter.format(fechaR);
                    Date hIni = (Date) panelEvento.spinnerHoraInicio.getValue();
                    Date hCie = (Date) panelEvento.spinnerHoraCierre.getValue();
                    formatter = new SimpleDateFormat("hh:mm a");
                    String hInicio = formatter.format(hIni);
                    String hCierre = formatter.format(hCie);
                    String metodo = panelEvento.comboBoxMetodo.getSelectedItem().toString();
                    boolean pagado = true;
                    if(panelEvento.comboBoxEstatus.getSelectedIndex() == 1){
                        pagado = false;
                    }
                    int montoA = (int) panelEvento.spinnerMontoAdulto.getValue();
                    int montoN = (int) panelEvento.spinnerMontoNino.getValue();
                    int numeroA = (int) panelEvento.spinnerNumAdultos.getValue();
                    int numeroN = (int) panelEvento.spinnerNumNinos.getValue();
                    String motivo = panelEvento.txtMotivo.getText();
                    double costoTotal = eventoF.calcularCostoReservacion(numeroA, numeroN, montoA, montoN);
                    if(eventoF.validarCantidadPersonas(numeroN, numeroA)){
                            if(!eventoF.cedulaRepetidaDia(listaReservaciones, fechaReservacion, indicadorClave, eFamiliar.getFechaDeReservacion())){
                                int actual =  eFamiliar.getNumeroAdultos() + eFamiliar.getNumeroNinos();
                                if(eventoF.validarCupo(listaReservaciones, fechaReservacion, actual, indicadorFecha)){
                                    try{
                                        String SQL_UPDATE_RESERVACION = "UPDATE Reservacion SET fechareserva=?, metodopago=?, numAdulto=?, numNino=?, montoAdulto=?, montoNino=?, "
                                        + "pagado=?, costoTotal=?, motivoEvento=?, horainicio=?, horafin=?, rifempresa=? WHERE fechareserva ='"+eFamiliar.getFechaDeReservacion()+"'and cedulacomensal ='"+eFamiliar.getCedula()+"'";
                                        PS_RESERVACION = CN.getConnection().prepareStatement(SQL_UPDATE_RESERVACION);
                                        PS_RESERVACION.setString(1, fechaReservacion);
                                        PS_RESERVACION.setString(2, metodo);
                                        PS_RESERVACION.setInt(3, numeroA);
                                        PS_RESERVACION.setInt(4, numeroN);
                                        PS_RESERVACION.setInt(5, montoA);
                                        PS_RESERVACION.setInt(6, montoN);
                                        PS_RESERVACION.setBoolean(7, pagado);
                                        PS_RESERVACION.setDouble(8, costoTotal);
                                        PS_RESERVACION.setString(9, motivo);
                                        PS_RESERVACION.setString(10, hInicio);
                                        PS_RESERVACION.setString(11, hCierre);
                                        PS_RESERVACION.setString(12, "");
 
                                        int res = PS_RESERVACION.executeUpdate();
                                        if (res > 0){
                                            eFamiliar.modificarReservacionFamiliar(indicadorFecha, indicadorClave, motivo, hIni, hCie, fechaReservacion, numeroA, numeroN, costoTotal, pagado, metodo, montoA, montoN, listaReservaciones);
                                            JOptionPane.showMessageDialog(null, "La reservación ha sido modificada con éxito", "", 1);
                                        }
                                    }catch(SQLException ex){
                                            JOptionPane.showMessageDialog(null, "Error al modificar la reservacion en la base de datos: " +ex.getMessage(), "Error", 0);
                                    }finally{
                                            PS_RESERVACION = null;
                                            CN.desconectar();
                                    }
                                    frameDetalles.dispose();
                                    cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                                }
                            }                        
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese todos los campos correctamente antes de modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        //Boton eliminar
        if(e.getSource() == frameDetalles.botonEliminar){
            if(indicadorReservacion.equals("Reservación General")){
                ReservacionGeneral rg = (ReservacionGeneral) reservacionG.buscarReservacion(listaReservaciones, indicadorFecha, indicadorClave);
                Comensal c = comensal.buscarComensal(listaComensales, indicadorClave);
                int op = JOptionPane.showOptionDialog(null, "¿Desea eliminar la reservación de "+ c.getNombre()+" " + c.getApellido()+ "?","Gestión de Reservaciones",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if (op==0){
                    try {
                        String SQL = "DELETE from Reservacion WHERE fechareserva ='"+rg.getFechaDeReservacion()+ "' and cedulacomensal = '"+rg.getCedula()+"'";
                        int res = 0;
                        PS_RESERVACION = CN.getConnection().prepareStatement(SQL);
                        res = PS_RESERVACION.executeUpdate();
                        if (res>0){
                            listaReservaciones.remove(rg);
                            JOptionPane.showMessageDialog(null, "La reservación ha sido eliminada con éxito", "", 1);
                        }
                    }catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar la reservación en la base de datos: " +ex.getMessage(), "Error", 0);
                    } finally {
                    PS_RESERVACION = null;
                    CN.desconectar();
                    }
                    cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                    frameDetalles.dispose();
                }
            }
            if(indicadorReservacion.equals("Evento Ejecutivo")){
                EventoEjecutivo eEjecutivo = (EventoEjecutivo) eventoE.buscarReservacion(listaReservaciones, indicadorFecha, indicadorClave);
                Empresa em = empresa.buscarEmpresa(listaEmpresas, indicadorClave);
                int op = JOptionPane.showOptionDialog(null, "¿Desea eliminar la reservación de "+ em.getNombre()+ "?","Gestión de Reservaciones",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if (op==0){
                    try {
                        String SQL = "DELETE from Reservacion WHERE fechareserva ='"+eEjecutivo.getFechaDeReservacion()+"' and rifempresa = '"+eEjecutivo.getRif()+"'";
                        int res = 0;
                        PS_RESERVACION = CN.getConnection().prepareStatement(SQL);
                        res = PS_RESERVACION.executeUpdate();
                        if (res>0){
                            listaReservaciones.remove(eEjecutivo);
                            JOptionPane.showMessageDialog(null, "La reservación ha sido eliminada con éxito", "", 1);
                        }
                    }catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar la reservación en la base de datos: " +ex.getMessage(), "Error", 0);
                    } finally {
                    PS_RESERVACION = null;
                    CN.desconectar();
                    }
                    cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                    frameDetalles.dispose();
                }
            }
            if(indicadorReservacion.equals("Evento Familiar")){
                EventoFamiliar eFamiliar = (EventoFamiliar) eventoF.buscarReservacion(listaReservaciones, indicadorFecha, indicadorClave);
                Comensal c = comensal.buscarComensal(listaComensales, indicadorClave);
                int op = JOptionPane.showOptionDialog(null, "¿Desea eliminar la reservación de "+ c.getNombre()+" " + c.getApellido()+ "?","Gestión de Reservaciones",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
                if (op==0){
                    try {
                        String SQL = "DELETE from Reservacion WHERE fechareserva ='"+eFamiliar.getFechaDeReservacion()+"' and cedulacomensal = '"+eFamiliar.getCedula()+"'";
                        int res = 0;
                        PS_RESERVACION = CN.getConnection().prepareStatement(SQL);
                        res = PS_RESERVACION.executeUpdate();
                        if (res>0){
                            listaReservaciones.remove(eFamiliar);
                            JOptionPane.showMessageDialog(null, "La reservación ha sido eliminada con éxito", "", 1);
                        }
                    }catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar la reservación en la base de datos: " +ex.getMessage(), "Error", 0);
                    } finally {
                    PS_RESERVACION = null;
                    CN.desconectar();
                    }
                    cargarReservaciones(panelConsultar.comboBoxFiltro.getSelectedItem().toString());
                    frameDetalles.dispose();
                }
            }            
        }
        
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource()==panelRegistrarReservacion.spinnerMontoAdulto || e.getSource()==panelRegistrarReservacion.spinnerMontoNino){
            int numeroAdultos = reservacionG.getNumeroAdultos();
            int numeroNinos = reservacionG.getNumeroNinos();
            int montoAdulto = (int) panelRegistrarReservacion.spinnerMontoAdulto.getValue();
            int montoNino = (int)panelRegistrarReservacion.spinnerMontoNino.getValue();
            int costoTotal = reservacionG.calcularCostoReservacion(numeroAdultos, numeroNinos, montoAdulto, montoNino);
            panelRegistrarReservacion.labelMontoTotal.setText(String.valueOf(costoTotal) + "$");
            
        }
        
        if(e.getSource()==panelRegistrarEvento.spinnerMontoAdulto || e.getSource()==panelRegistrarEvento.spinnerMontoNino){
            int numeroAdultos;
            int numeroNinos;
            if(tipoReservacion == 2){
                numeroAdultos = eventoF.getNumeroAdultos();
                numeroNinos = eventoF.getNumeroNinos(); 
            } else {
                numeroAdultos = eventoE.getNumeroAdultos();
                numeroNinos = eventoE.getNumeroNinos();
            }
            int montoAdulto = (int) panelRegistrarEvento.spinnerMontoAdulto.getValue();
            int montoNino = (int)panelRegistrarEvento.spinnerMontoNino.getValue();
            int costoTotal = eventoP.calcularCostoReservacion(numeroAdultos, numeroNinos, montoAdulto, montoNino);
            panelRegistrarEvento.labelMontoTotal.setText(String.valueOf(costoTotal) + "$");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        //Con este evento se actualiza la lista de reservaciones
        if(evt.getSource()==panelConsultar.calendarioReservaciones){
            Date fechaR = panelConsultar.calendarioReservaciones.getDate();
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            String fechaReservacion = formatter.format(fechaR);
            cargarReservacionesPorFecha(fechaReservacion,"");
            panelConsultar.labelCantidadPersonas.setText(String.valueOf(reservacionG.calcularTotalPersonas(listaReservaciones, fechaReservacion)));
            panelConsultar.labelCantidadReservaciones.setText(String.valueOf(reservacionG.calcularTotalReservaciones(listaReservaciones, fechaReservacion)));
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if ( e.getStateChange() == ItemEvent.SELECTED ){
            if (e.getItem().toString().equals("Reservaciones generales")) {
                panelConsultar.txtBuscar.setText("");
                cargarReservacionesGenerales("");
            }
            
            if(e.getItem().toString().equals("Eventos privados")){
                panelConsultar.txtBuscar.setText("");
                cargarReservacionesPrivadas("");
            }
            
            if(e.getItem().toString().equals("Nombre")){
                panelConsultar.txtBuscar.setText("");
            }
            
            if(e.getItem().toString().equals("Cédula o RIF")){
                panelConsultar.txtBuscar.setText("");
            }
            
            if(e.getItem().toString().equals("Estado de pago")){
                panelConsultar.txtBuscar.setText("");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        panelConsultar.txtBuscar.addKeyListener(new KeyAdapter (){
            @Override
            public void keyReleased(KeyEvent e) {
                if(panelConsultar.comboBoxFiltro.getSelectedItem().toString().equals("Nombre")){
                    cargarReservaciones("Nombre");
                }
                if(panelConsultar.comboBoxFiltro.getSelectedItem().toString().equals("Cédula o RIF")){
                    cargarReservaciones("Cédula o RIF");
                }
                if(panelConsultar.comboBoxFiltro.getSelectedItem().toString().equals("Estado de pago")){
                    cargarReservaciones("Estado de pago");
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
