/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.modelinventario.Insumo;
import model.modelinventario.Inventario;
import view.viewinventario.FrameVerInsumo;
import view.viewinventario.PanelConsultarInventario;
import view.viewinventario.PanelIngresarInsumo;
import view.viewinventario.PanelInsumo;
import view.viewprincipal.SistemaPrincipal;
import model.conexionBD.ConexionServidor;
import model.modelinventario.Proveedor;
import view.viewinventario.PanelBuscarInsumo;
import view.viewinventario.PanelConsultarLotes;
import view.viewinventario.PanelConsultarProveedores;
import view.viewinventario.PanelIngresarLotes;
import view.viewinventario.PanelIngresarProveedor;

/**
 *
 * Desarrollo: Paola Ascanio | Intefaces: Estefany Torres
 */
public class ControllerInventario implements ActionListener, ItemListener {
    
    private final SistemaPrincipal panelSistema;
    private final PanelConsultarInventario panelConsultar = new PanelConsultarInventario();
    private final PanelIngresarInsumo panelIngresar = new PanelIngresarInsumo();
    private final FrameVerInsumo frameVer = new FrameVerInsumo();
    private final PanelBuscarInsumo panelBuscar = new PanelBuscarInsumo(); 
    private final PanelConsultarLotes panelConsultarLotes = new PanelConsultarLotes();
    private final PanelConsultarProveedores panelConsultarProveedores = new PanelConsultarProveedores();
    private final PanelIngresarLotes panelIngresarLotes = new PanelIngresarLotes();
    private final PanelIngresarProveedor panelIngresarProveedor = new PanelIngresarProveedor();
    private Proveedor proveedor = new Proveedor();
    
    private static final ArrayList<Proveedor> listaProveedores = new ArrayList<>();
    
    private final String SQL_INSERT_PROVEEDOR = "INSERT INTO Proveedor (nombre,direccion,telefono,correo,rif) values (?,?,?,?,?)";
    private PreparedStatement PS_PROVEEDOR;
    
    
    private final String SQL_SELECT_PROVEEDOR = "SELECT * FROM Proveedor";
    private DefaultTableModel DT_PROVEEDOR;
    private ResultSet RS_PROVEEDOR;
    
    private final String SQL_INSERT = "INSERT INTO Insumo (idInsumo,tipoinsumo,unidad,cantidad,vencido,fechavencimiento,nombre) values (?,?,?,?,?,?,?)";
    private PreparedStatement PS;                                                                                                                   
    private final ConexionServidor CN;
    
    private final String SQL_SELECT = "SELECT * FROM Insumo";
    private DefaultTableModel DT;
    private ResultSet RS;
    
    private final Calendar calendar = Calendar.getInstance();
    private Date fechaActual;
    
    private final Inventario inventario = new Inventario();
    private Insumo i = new Insumo();  
    
    public ControllerInventario(SistemaPrincipal panelSistema) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);  
        calendar.set(Calendar.MILLISECOND, 0);
        fechaActual = calendar.getTime();
        Insumo in = new Insumo("Bebidas",17 , "Vino blanco", "Botella",15,fechaActual);
        inventario.agregarInsumo(in);
        
        inventario.revisarInsumosVencidos(fechaActual);
        if(inventario.revisarInsumosVencidos()){
            panelConsultar.botonAdvertencia.setEnabled(true);
            panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
        } else {
            panelConsultar.botonAdvertencia.setEnabled(false);
            panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
        }
        //Ventana del sistema
        this.panelSistema = panelSistema;
        this.panelSistema.botonInventario.addActionListener(this);
      
        //Panel consultar inventario
        panelConsultar.jScrollPaneD.getViewport().setOpaque(false);
        panelConsultar.jScrollPanePR.getViewport().setOpaque(false);
        panelConsultar.jScrollPaneV.getViewport().setOpaque(false);
        panelConsultar.comboBoxTipoInsumo.addItemListener(this);
        panelConsultar.botonIngresar.addActionListener(this); 
        panelConsultar.botonAdvertencia.addActionListener(this);
        panelConsultar.botonEntrada.addActionListener(this);
        panelConsultar.botonSalida.addActionListener(this);
        panelConsultar.botonBuscar.addActionListener(this);
        panelConsultar.botonProveedor.addActionListener(this);
        panelConsultar.labelTItuloD.setText("CARNES DISPONIBLES");
        panelConsultar.labelTItuloPR.setText("CARNES POR REPONER");
        panelConsultar.labelTItuloV.setText("CARNES VENCIDAS");
                
        cargarInventario("Carnes");
            
        //Agregando botones de panel Ingresar Insumo
        panelIngresar.botonRegistrar.addActionListener(this);
        panelIngresar.botonRegresarI.addActionListener(this);
        panelIngresar.comboBoxTipoInsumo.addActionListener(this);
        panelIngresar.comboBoxUnidad.addActionListener(this);
        //panelIngresar.jDateFechaVencimiento.getJCalendar().setPreferredSize(new Dimension (380,180)); 
       
        //Frame Ver Insumo
        frameVer.botonGuardar.addActionListener(this);
        frameVer.botonEliminar.addActionListener(this);
        
        //Panel Buscar Insumo
        panelBuscar.botonRegresarI.addActionListener(this);
        
        //Panel Consultar Lotes
        panelConsultarLotes.botonRegresarE.addActionListener(this);
        panelConsultarLotes.botonRegresarS.addActionListener(this);
        
        //Panel Consultar Proveedores
        panelConsultarProveedores.botonIngresar.addActionListener(this);
        panelConsultarProveedores.botonRegresarI.addActionListener(this);
        
        //Panel Ingresar Lotes
        panelIngresarLotes.botonConsultarEntradas.addActionListener(this);
        panelIngresarLotes.botonConsultarSalidas.addActionListener(this);
        panelIngresarLotes.botonRegistrarE.addActionListener(this);
        panelIngresarLotes.botonRegistrarS.addActionListener(this);
        panelIngresarLotes.botonRegresarI.addActionListener(this);
        
        //Panel Ingresar Proveedor
        panelIngresarProveedor.botonRegistrar.addActionListener(this);
        panelIngresarProveedor.botonRegresarI.addActionListener(this);

        PS = null;
        CN= new ConexionServidor();
        
        setDatos();
        setDatosProveedor();
        
        /*inventario.revisarInsumoVencido(fechaActual, inventario.getListaInsumos());
        inventario.mandarMensajeInsumosVencidos();*/
    }

    //Método que verifica si hay campos vacios en el panel destinado a registrar la proveedor
    public boolean camposVaciosPanelRegistrarProveedor(){
        return panelIngresarProveedor.txtNombre.getText().isEmpty() || panelIngresarProveedor.txtNombre.getText().equals("Nombre del Proveedor")
                ||panelIngresarProveedor.txtRIF.getText().isEmpty() || panelIngresarProveedor.txtRIF.getText().equals("RIF")
                ||panelIngresarProveedor.txtCorreo.getText().isEmpty() || panelIngresarProveedor.txtCorreo.getText().equals("Correo")
                ||panelIngresarProveedor.txtTelefono.getText().isEmpty() || panelIngresarProveedor.txtTelefono.getText().equals("Teléfono")
                ||panelIngresarProveedor.comboBoxMunicipio.getSelectedIndex()==0;
    }
    
    // Verifica si hay campos vacios al ingresar insumos
    public boolean camposOpcionIngresar () {
        return !(  panelIngresar.txtNombreInsumo.getText().isEmpty()
                 ||panelIngresar.txtNombreInsumo.getText().equals("Nombre")
                 //||panelIngresar.jDateFechaVencimiento.getDate()==null
                 ||panelIngresar.comboBoxTipoInsumo.getSelectedIndex()==0
                 ||panelIngresar.comboBoxUnidad.getSelectedIndex()==0);       
    }
    
    // Limpia el panel de ingresar
    public void resetearCamposIngresar(){
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);  
        calendar.set(Calendar.MILLISECOND, 0);
        fechaActual = calendar.getTime();
        //panelIngresar.jDateFechaVencimiento.setDate(fechaActual);
        panelIngresar.txtNombreInsumo.setForeground(Color.LIGHT_GRAY);
        panelIngresar.txtNombreInsumo.setText("Nombre");
        panelIngresar.comboBoxTipoInsumo.setSelectedIndex(0);
        panelIngresar.comboBoxUnidad.setSelectedIndex(0);
        //panelIngresar.spinnerID.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));
        //panelIngresar.spinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(1.0d, 1.0d, 1000.0d, 1.0d));
    }
    
        //Este metodo resetea los campos que se encuentran en el panel de registrar comensal
    public void resetearCamposPanelIngresarProveedor(){
        panelIngresarProveedor.txtNombre.setForeground(Color.LIGHT_GRAY);
        panelIngresarProveedor.txtRIF.setForeground(Color.LIGHT_GRAY);
        panelIngresarProveedor.txtCorreo.setForeground(Color.LIGHT_GRAY);
        panelIngresarProveedor.txtTelefono.setForeground(Color.LIGHT_GRAY);
        panelIngresarProveedor.txtNombre.setText("Nombre del Proveedor");
        panelIngresarProveedor.txtRIF.setText("RIF");
        panelIngresarProveedor.txtCorreo.setText("Correo");
        panelIngresarProveedor.txtTelefono.setText("Teléfono");
        panelIngresarProveedor.comboBoxMunicipio.setSelectedIndex(0);
    }
   
     //Consultar Base de Datos
    private DefaultTableModel setColumnas(){
        DT = new DefaultTableModel();
        DT.addColumn("idInsumo");
        DT.addColumn("tipoinsumo");
        DT.addColumn("unidad");
        DT.addColumn("cantidad");
        DT.addColumn("vencido");
        DT.addColumn("fechavencimiento");
        DT.addColumn("nombre");
       
        return DT;
    }
    
    public DefaultTableModel setDatos(){
        try{
            setColumnas();
            PS = CN.getConnection().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            while (RS.next()){
                int idInsumo = RS.getInt(2);
                String   tipoInsumo = RS.getString(3);
                String unidad = RS.getString(4);
                Double cantidad = RS.getDouble(5);
                boolean vencido = RS.getBoolean(6);
                Date fechaVencimiento = new java.util.Date(RS.getDate(7).getTime());
                String nombre = RS.getString(8);               
                Insumo insumo = new Insumo(tipoInsumo,idInsumo,nombre,unidad,cantidad,fechaVencimiento,vencido);
                inventario.agregarInsumo(insumo);
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar los datos de los insumos: " +e.getMessage(), "Error", 0);
        }
        finally{
            PS=null;
            RS=null;
            CN.desconectar();
        }
        return DT;
    }
    
    private DefaultTableModel setColumnasProveedor(){
        DT_PROVEEDOR = new DefaultTableModel();
        DT_PROVEEDOR.addColumn("nombre");
        DT_PROVEEDOR.addColumn("direccion");
        DT_PROVEEDOR.addColumn("telefono");
        DT_PROVEEDOR.addColumn("correo");
        DT_PROVEEDOR.addColumn("rif");
        return DT_PROVEEDOR;
    }
    
    /* Obtiene los datos de cada Proveedor de la tabla de la BD y los 
       registra en la lista de Proveedores del programa */
    public DefaultTableModel setDatosProveedor(){
        try{
            // Establece modelo de tabla
            setColumnasProveedor();
            // Conexion a BD para obtener informacion de tabla Empresa
            PS_PROVEEDOR = CN.getConnection().prepareStatement(SQL_SELECT_PROVEEDOR);
            RS_PROVEEDOR = PS_PROVEEDOR.executeQuery();
            // Busca datos de cada columna
            while (RS_PROVEEDOR.next()){
                String nombre = RS_PROVEEDOR.getString(1);
                String direccion = RS_PROVEEDOR.getString(2);
                String telefono = RS_PROVEEDOR.getString(3);
                String correo = RS_PROVEEDOR.getString(4);
                String rif = RS_PROVEEDOR.getString(5);
                
                // Crea objeto Empresa y lo agrega a su lista
                Proveedor proveedor = new Proveedor(nombre, rif, correo, direccion, telefono);
                listaProveedores.add(proveedor);
                panelIngresar.comboBoxProveedor.addItem(nombre);
            }
        }
        // Excepcion si no se pudo listar las tablas
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar los datos del proveedor: " +e.getMessage(), "Error", 0);
        }
        // Finaliza conexion con BD
        finally{
            PS_PROVEEDOR=null;
            RS_PROVEEDOR=null;
            CN.desconectar();
        }
        return DT_PROVEEDOR;
    }
    
    /* Limpia todos los paneles y dependiendo del valor del combobox de filtrado, 
       muestra ciertos insumos o no en base a su tipo */ 
    public final void cargarInventario (String tipoInsumoPanel) {
        panelConsultar.panelInsumosDisponibles.removeAll();
        panelConsultar.panelInsumosPorReponer.removeAll();
        panelConsultar.panelInsumosVencidos.removeAll();
        // Busca los insumos correspondientes en inventario por su ID y su tipo
        
        for (Insumo i: inventario.getListaInsumos()) {
            
            if(i.getTipoInsumo().equals( tipoInsumoPanel)){
                // Crea los datos generales para todos los paneles
                PanelInsumo panel = new PanelInsumo();
                panel.setSize(310,80);
                panel.labelNombreInsumo.setText(i.getNombre());
                panel.labelCantidad.setText(String.valueOf(i.getCantidad()));
                panel.labelID.setText(String.valueOf(i.getId()));
                panel.labelID.setVisible(false);

                // Verifica los datos del insumo para saber en que panel colocarlos
                // Panel de vencidos
                if (i.isVencido()) {
                    panelConsultar.panelInsumosVencidos.add(panel);
                }
                // Panel por reponer
                else if (i.getCantidad() == 0) {
                    panelConsultar.panelInsumosPorReponer.add(panel);
                }
                // Panel de disponibles
                else {
                    panelConsultar.panelInsumosDisponibles.add(panel);
                }

                panel.botonVerInsumo.addActionListener(new ActionListener() {
                    @Override
                        public void actionPerformed(ActionEvent e) {
                            frameVer.setTitle("Restaurant Manager");
                            frameVer.setSize(355,525);
                            frameVer.setLocationRelativeTo(null);
                            frameVer.setResizable(false);

                            frameVer.labelTItulo.setText("Datos del Insumo");
                            //frameVer.spinnerID.setValue(i.getId());
                            //frameVer.spinnerCantidad.setValue(i.getCantidad());
                            frameVer.txtNombreInsumo.setText(i.getNombre());
                            frameVer.txtNombreInsumo.setEditable(false);
                            frameVer.comboBoxUnidad.setSelectedItem(i.getUnidad());
                            frameVer.comboBoxTipoInsumo.setSelectedItem(i.getTipoInsumo());
                            //frameVer.jDateFechaVencimiento.setDate(i.getFechaVencimiento());

                            frameVer.setVisible(true);
                        }
                });
            }
        }
        panelConsultar.panelInsumosDisponibles.revalidate();
        panelConsultar.panelInsumosDisponibles.repaint();
        panelConsultar.panelInsumosPorReponer.revalidate();
        panelConsultar.panelInsumosPorReponer.repaint();
        panelConsultar.panelInsumosVencidos.revalidate();
        panelConsultar.panelInsumosVencidos.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BOTONES DEL PANEL: panelSistema
        
        //Boton icono de Inventario
        if(e.getSource()==panelSistema.botonInventario){
            inventario.revisarInsumosVencidos(fechaActual);
            cargarInventario (panelConsultar.comboBoxTipoInsumo.getSelectedItem().toString());
            if(inventario.revisarInsumosVencidos()){
                panelConsultar.botonAdvertencia.setEnabled(true);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
            } else {
                panelConsultar.botonAdvertencia.setEnabled(false);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
            }
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL FRAME: Ver insumo
        
        if(e.getSource()==frameVer.botonGuardar){
            
        }
        
        if(e.getSource()==frameVer.botonEliminar){
            
        } 
        
        //BOTONES DEL PANEL: Consultar Inventario
        
        //Boton de Ingresar Insumo
        if(e.getSource()==panelConsultar.botonIngresar){
            panelIngresar.setSize(926,720);
            panelIngresar.setLocation(0,0);
            //panelIngresar.jDateFechaVencimiento.setDate(fechaActual);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelIngresar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();    
        }
        
        if(e.getSource() == panelConsultar.botonAdvertencia){
            inventario.revisarInsumosVencidos(fechaActual);
            inventario.mandarMensajeInsumosVencidos();
        }
        
        if(e.getSource()== panelConsultar.botonEntrada){
            panelIngresarLotes.botonConsultarEntradas.setVisible(true);
            panelIngresarLotes.botonConsultarSalidas.setVisible(false);
            panelIngresarLotes.botonRegistrarE.setVisible(true);
            panelIngresarLotes.botonRegistrarS.setVisible(false);
            panelIngresarLotes.labelTItulo.setText("Entrada de Insumos");
            panelIngresarLotes.labelFecha.setText("Fecha de Ingreso");
            panelIngresarLotes.setSize(926,720);
            panelIngresarLotes.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelIngresarLotes);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource()==panelConsultar.botonSalida){
            panelIngresarLotes.botonConsultarEntradas.setVisible(false);
            panelIngresarLotes.botonConsultarSalidas.setVisible(true);
            panelIngresarLotes.botonRegistrarE.setVisible(false);
            panelIngresarLotes.botonRegistrarS.setVisible(true);
            panelIngresarLotes.labelTItulo.setText("Salida de Insumos");
            panelIngresarLotes.labelFecha.setText("Fecha de Salida");
            panelIngresarLotes.setSize(926,720);
            panelIngresarLotes.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelIngresarLotes);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource()==panelConsultar.botonBuscar){
            panelBuscar.setSize(926,720);
            panelBuscar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelBuscar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource()==panelConsultar.botonProveedor){
            panelConsultarProveedores.setSize(926,720);
            panelConsultarProveedores.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultarProveedores);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
           
        //BOTONES DEL PANEL: Ingresar Insumo
        
        if(e.getSource()==panelIngresar.botonRegresarI){
            cargarInventario (panelConsultar.comboBoxTipoInsumo.getSelectedItem().toString());
            inventario.revisarInsumosVencidos(fechaActual);
            if(inventario.revisarInsumosVencidos()){
                panelConsultar.botonAdvertencia.setEnabled(true);
                panelConsultar.fondoBotonAdvertencia.setEnabled(true);
            } else {
                panelConsultar.botonAdvertencia.setEnabled(false);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
            }
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
            resetearCamposIngresar();
        }        
        
        /*if (e.getSource()==panelIngresar.botonRegistrar){
            
            boolean validado = true;
            // Campos vacios
            if (camposOpcionIngresar()){
                String nombre = panelIngresar.txtNombreInsumo.getText();
                int ID = (int) panelIngresar.spinnerID.getValue();
                if (!inventario.iDRepetido(ID)) {
                    if(i.validarInsumo(nombre)){
                        if(!(inventario.nombreRepetido(nombre))){
                            Date fechaVencimiento = panelIngresar.jDateFechaVencimiento.getDate();
                            if (fechaVencimiento.after(fechaActual)) {
                                String tipo = panelIngresar.comboBoxTipoInsumo.getSelectedItem().toString();
                                double cantidad = (double) panelIngresar.spinnerCantidad.getValue();
                                String unidad = panelIngresar.comboBoxUnidad.getSelectedItem().toString();
                                // Verifica que los conjuntos de unidades y tipo de insumo introducidas tengan cierta logica
                                if (unidad.equals("Litro") || unidad.equals("Botella")){
                                    if (tipo.equals("Carnes") || tipo.equals("Frutas") || tipo.equals("Vegetales")) {
                                        JOptionPane.showMessageDialog(null,"Este tipo de producto no puede tener la unidad en "+unidad, "Advertencia", JOptionPane.WARNING_MESSAGE);
                                        validado = false;
                                    }
                                }
                                if (unidad.equals("Kilogramo")){
                                    if(tipo.equals("Bebidas")) {
                                        JOptionPane.showMessageDialog(null,"Este tipo de producto no puede tener la unidad en Kilogramo", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                        validado = false;
                                    }
                                }
                                   if (validado) {
                                    Insumo in = new Insumo(tipo, ID, nombre, unidad,cantidad,fechaVencimiento);
                                    try{
                                                java.sql.Date sqlDate = new java.sql.Date(fechaVencimiento.getTime());
                                                PS=CN.getConnection().prepareStatement(SQL_INSERT);
                                                PS.setInt(1, ID);
                                                PS.setString(2, tipo);
                                                PS.setString(3, unidad);
                                                PS.setDouble(4, cantidad);
                                                PS.setBoolean(5, false);
                                                PS.setDate(6, sqlDate); 
                                                PS.setString(7, nombre);
                                                
                                                int res = PS.executeUpdate();
                                                if (res > 0){
                                                    inventario.agregarInsumo(in);
                                                    JOptionPane.showMessageDialog(null, "El insumo ha sido registrado con éxito", "", 1);
                                                }
                                            } catch(SQLException ex){   
                                                JOptionPane.showMessageDialog(null, "Error al guardar los datos del insumo en la base de datos: " +ex.getMessage(), "Error", 0);
                                            }
                                            finally{
                                                PS=null;
                                                CN.desconectar();
                                            }
                                    //JOptionPane.showMessageDialog(null, "El insumo ha sido registrado con éxito", "", 1);
                                    // Crear panel de insumo 
                                    cargarInventario (panelConsultar.comboBoxTipoInsumo.getSelectedItem().toString());
                                    inventario.revisarInsumosVencidos(fechaActual);
                                    if(inventario.revisarInsumosVencidos()){
                                        panelConsultar.botonAdvertencia.setEnabled(true);
                                        panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
                                    } else {
                                        panelConsultar.botonAdvertencia.setEnabled(false);
                                        panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
                                    }
                                    panelConsultar.setSize(926,720);
                                    panelConsultar.setLocation(0,0);
                                    panelSistema.panelPrincipal.removeAll();
                                    panelSistema.panelPrincipal.add(panelConsultar);
                                    panelSistema.panelPrincipal.revalidate();
                                    panelSistema.panelPrincipal.repaint();
                                    resetearCamposIngresar();   
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El insumo no puede vencerse en una fecha anterior o igual al dia de hoy", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El nombre de éste insumo ya está registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } 
                } else {
                    JOptionPane.showMessageDialog(null, "El ID de éste insumo ya está registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else 
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } */
        
        //BOTONES DEL PANEL: panelBuscarInsumo
        
        if(e.getSource() == panelBuscar.botonRegresarI){
            cargarInventario (panelConsultar.comboBoxTipoInsumo.getSelectedItem().toString());
            inventario.revisarInsumosVencidos(fechaActual);
            if(inventario.revisarInsumosVencidos()){
                panelConsultar.botonAdvertencia.setEnabled(true);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
            } else {
                panelConsultar.botonAdvertencia.setEnabled(false);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
            }
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: Consultar Lotes
        
        if(e.getSource()==panelConsultarLotes.botonRegresarE){
            panelIngresarLotes.botonConsultarEntradas.setVisible(true);
            panelIngresarLotes.botonConsultarSalidas.setVisible(false);
            panelIngresarLotes.botonRegistrarE.setVisible(true);
            panelIngresarLotes.botonRegistrarS.setVisible(false);
            panelIngresarLotes.labelTItulo.setText("Entrada de Insumos");
            panelIngresarLotes.labelFecha.setText("Fecha de Ingreso");
            panelIngresarLotes.setSize(926,720);
            panelIngresarLotes.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelIngresarLotes);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource()==panelConsultarLotes.botonRegresarS){
            panelIngresarLotes.botonConsultarEntradas.setVisible(false);
            panelIngresarLotes.botonConsultarSalidas.setVisible(true);
            panelIngresarLotes.botonRegistrarE.setVisible(false);
            panelIngresarLotes.botonRegistrarS.setVisible(true);
            panelIngresarLotes.labelTItulo.setText("Salida de Insumos");
            panelIngresarLotes.labelFecha.setText("Fecha de Salida");
            panelIngresarLotes.setSize(926,720);
            panelIngresarLotes.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelIngresarLotes);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: Consultar Proveedores
        
        if(e.getSource() == panelConsultarProveedores.botonIngresar){
            panelIngresarProveedor.setSize(926,720);
            panelIngresarProveedor.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelIngresarProveedor);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource() == panelConsultarProveedores.botonRegresarI){
            cargarInventario (panelConsultar.comboBoxTipoInsumo.getSelectedItem().toString());
            inventario.revisarInsumosVencidos(fechaActual);
            if(inventario.revisarInsumosVencidos()){
                panelConsultar.botonAdvertencia.setEnabled(true);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
            } else {
                panelConsultar.botonAdvertencia.setEnabled(false);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
            }
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: Ingresar Lotes
        
        if(e.getSource() == panelIngresarLotes.botonRegistrarE){
            
        }
        
        if(e.getSource() == panelIngresarLotes.botonRegistrarS){
            
        }
        
        if(e.getSource() == panelIngresarLotes.botonConsultarEntradas){
            panelConsultarLotes.labelTItulo.setText("ENTRADAS");
            panelConsultarLotes.botonRegresarE.setVisible(true);
            panelConsultarLotes.botonRegresarS.setVisible(false);
            panelConsultarLotes.setSize(926,720);
            panelConsultarLotes.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultarLotes);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource() == panelIngresarLotes.botonConsultarSalidas){
            panelConsultarLotes.labelTItulo.setText("SALIDAS");
            panelConsultarLotes.botonRegresarE.setVisible(false);
            panelConsultarLotes.botonRegresarS.setVisible(true);
            panelConsultarLotes.setSize(926,720);
            panelConsultarLotes.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultarLotes);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource() == panelIngresarLotes.botonRegresarI){
            cargarInventario (panelConsultar.comboBoxTipoInsumo.getSelectedItem().toString());
            inventario.revisarInsumosVencidos(fechaActual);
            if(inventario.revisarInsumosVencidos()){
                panelConsultar.botonAdvertencia.setEnabled(true);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
            } else {
                panelConsultar.botonAdvertencia.setEnabled(false);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
            }
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: Ingresar Proveedor
        
        if(e.getSource()==panelIngresarProveedor.botonRegistrar){
            if(!camposVaciosPanelRegistrarProveedor()){
                String nombre = panelIngresarProveedor.txtNombre.getText();
                String rif = panelIngresarProveedor.txtRIF.getText();
                String correo = panelIngresarProveedor.txtCorreo.getText();
                String direccion = panelIngresarProveedor.comboBoxMunicipio.getSelectedItem().toString();
                String telefono = panelIngresarProveedor.txtTelefono.getText();
                if(proveedor.validarDatosProveedor(nombre, rif, correo, telefono)&&!(proveedor.verificarProveedorRepetido(listaProveedores,rif,nombre))){
                    proveedor.setNombre(nombre);
                    proveedor.setRif(rif);
                    proveedor.setCorreo(correo);
                    proveedor.setDireccion(direccion);
                    proveedor.setTelefono(telefono);
                    listaProveedores.add(proveedor);
                    panelIngresar.comboBoxProveedor.addItem(nombre);
                    try{
                            PS_PROVEEDOR=CN.getConnection().prepareStatement(SQL_INSERT_PROVEEDOR);
                            PS_PROVEEDOR.setString(1, nombre);
                            PS_PROVEEDOR.setString(2, direccion);
                            PS_PROVEEDOR.setString(3, telefono);
                            PS_PROVEEDOR.setString(4, correo);
                            PS_PROVEEDOR.setString(5, rif);
                            int res = PS_PROVEEDOR.executeUpdate();
                            if (res > 0){
                                JOptionPane.showMessageDialog(null, "El proveedor ha sido registrado con éxito.", "", 1);
                            }
                        }catch(SQLException ex){
                            JOptionPane.showMessageDialog(null, "Error al guardar los datos del proveedor en la base de datos: " +ex.getMessage(), "Error", 0);
                        }
                        finally{
                            PS_PROVEEDOR=null;
                            CN.desconectar();
                        }
                    resetearCamposPanelIngresarProveedor();
                }
            }
        }
        
        if(e.getSource()==panelIngresarProveedor.botonRegresarI){
            panelConsultarProveedores.setSize(926,720);
            panelConsultarProveedores.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultarProveedores);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
    }
    
    

    
    // BOTONES DEL SISTEMA: Boton de filtrado de busqueda
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItem().toString().equals("Carnes")) {
            panelConsultar.labelTItuloD.setText("CARNES DISPONIBLES");
            panelConsultar.labelTItuloPR.setText("CARNES POR REPONER");
            panelConsultar.labelTItuloV.setText("CARNES VENCIDAS");
                
            cargarInventario("Carnes");
        }
       
        if (e.getItem().toString().equals("Frutas")) {
            panelConsultar.labelTItuloD.setText("FRUTAS DISPONIBLES");
            panelConsultar.labelTItuloPR.setText("FRUTAS POR REPONER");
            panelConsultar.labelTItuloV.setText("FRUTAS VENCIDAS");
            
            cargarInventario("Frutas");
        }
            
        if (e.getItem().toString().equals("Vegetales")) {
            panelConsultar.labelTItuloD.setText("VEGETALES DISPONIBLES");
            panelConsultar.labelTItuloPR.setText("VEGETALES POR REPONER");
            panelConsultar.labelTItuloV.setText("VEGETALES VENCIDOS");
            
            cargarInventario("Vegetales");
        }
            
        if (e.getItem().toString().equals("Bebidas")) {
            panelConsultar.labelTItuloD.setText("BEBIDAS DISPONIBLES");
            panelConsultar.labelTItuloPR.setText("BEBIDAS POR REPONER");
            panelConsultar.labelTItuloV.setText("BEBIDAS VENCIDAS");
            
            cargarInventario("Bebidas");
        }

        if (e.getItem().toString().equals("Otros")) {
            panelConsultar.labelTItuloD.setText("OTROS DISPONIBLES");
            panelConsultar.labelTItuloPR.setText("OTROS POR REPONER");
            panelConsultar.labelTItuloV.setText("OTROS VENCIDOS");
            
            cargarInventario("Otros");
        }
    }
    
}
