/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import static controller.ControllerLogin.indicadorUsuario;
import static controller.ControllerLogin.listaGerentes;
import java.awt.Color;
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
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.modelinventario.Insumo;
import model.modelinventario.Inventario;
import view.viewinventario.FrameVerInsumo;
import view.viewinventario.PanelConsultarInventario;
import view.viewinventario.PanelIngresarInsumo;
import view.viewinventario.PanelInsumo;
import view.viewprincipal.SistemaPrincipal;
import model.conexionBD.ConexionServidor;
import model.modelinventario.Lote;
import model.modelinventario.Proveedor;
import model.modelinventario.Registro;
import model.modelusuario.Gerente;
import view.viewinventario.PanelBuscarInsumo;
import view.viewinventario.PanelConsultarLotes;
import view.viewinventario.PanelConsultarProveedores;
import view.viewinventario.PanelEntrada;
import view.viewinventario.PanelIngresarLotes;
import view.viewinventario.PanelIngresarProveedor;
import view.viewinventario.PanelProveedor;
import view.viewinventario.PanelSalida;

/**
 *
 * Desarrollo: Paola Ascanio | Intefaces: Estefany Torres
 */
public class ControllerInventario implements ActionListener, ItemListener, KeyListener, PropertyChangeListener {
    
    private final SistemaPrincipal panelSistema;
    private final PanelConsultarInventario panelConsultar = new PanelConsultarInventario();
    private final PanelIngresarInsumo panelIngresar = new PanelIngresarInsumo();
    private final FrameVerInsumo frameVer = new FrameVerInsumo();
    private final PanelBuscarInsumo panelBuscar = new PanelBuscarInsumo(); 
    private final PanelConsultarLotes panelConsultarLotes = new PanelConsultarLotes();
    private final PanelConsultarProveedores panelConsultarProveedores = new PanelConsultarProveedores();
    private final PanelIngresarLotes panelIngresarLotes = new PanelIngresarLotes();
    private final PanelIngresarProveedor panelIngresarProveedor = new PanelIngresarProveedor();
    
    private static final ArrayList<Proveedor> listaProveedores = new ArrayList<>();
    
    private final String SQL_INSERT_PROVEEDOR = "INSERT INTO Proveedor (nombre,direccion,telefono,correo,rif) values (?,?,?,?,?)";
    private PreparedStatement PS_PROVEEDOR;
    
    
    private final String SQL_SELECT_PROVEEDOR = "SELECT * FROM Proveedor";
    private DefaultTableModel DT_PROVEEDOR;
    private ResultSet RS_PROVEEDOR;
    
    private final String SQL_INSERT = "INSERT INTO Insumo (idInsumo,tipoinsumo,unidad,cantidad,nombre,proveedor) values (?,?,?,?,?,?)";
    private PreparedStatement PS;                                                                                                                   
    private final ConexionServidor CN;
    
    private final String SQL_SELECT = "SELECT * FROM Insumo";
    private DefaultTableModel DT;
    private ResultSet RS;
    
    private DefaultTableModel modeloEntrada, modeloSalida;
    
    private final Calendar calendar = Calendar.getInstance();
    private Date fechaActual;
    
    private final Inventario inventario = new Inventario();
    private final ArrayList <Registro> listaRegistros = new ArrayList<>();
    private Insumo i = new Insumo();
    private int indicadorID = -1;
    private Proveedor proveedor = new Proveedor();
    
    public ControllerInventario(SistemaPrincipal panelSistema) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);  
        calendar.set(Calendar.MILLISECOND, 0);
        fechaActual = calendar.getTime();
        
        /*inventario.revisarInsumosVencidos(fechaActual);
        if(inventario.revisarInsumosVencidos()){
            panelConsultar.botonAdvertencia.setEnabled(true);
            panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
        } else {
            panelConsultar.botonAdvertencia.setEnabled(false);
            panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
        }*/
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
        panelBuscar.txtBuscar.addKeyListener(this);
        
        //Panel Consultar Lotes
        panelConsultarLotes.botonRegresarE.addActionListener(this);
        panelConsultarLotes.botonRegresarS.addActionListener(this);
        panelConsultarLotes.jDateFiltro.addPropertyChangeListener(this);
        
        //Panel Consultar Proveedores
        panelConsultarProveedores.botonIngresar.addActionListener(this);
        panelConsultarProveedores.botonRegresarI.addActionListener(this);
        panelConsultarProveedores.txtBuscar.addKeyListener(this);
        
        //Panel Ingresar Lotes
        panelIngresarLotes.botonConsultarEntradas.addActionListener(this);
        panelIngresarLotes.botonConsultarSalidas.addActionListener(this);
        panelIngresarLotes.botonRegistrarE.addActionListener(this);
        panelIngresarLotes.botonRegistrarS.addActionListener(this);
        panelIngresarLotes.botonRegresarI.addActionListener(this);
        panelIngresarLotes.botonCambiar.addActionListener(this);
        
        //Panel Ingresar Proveedor
        panelIngresarProveedor.botonRegistrar.addActionListener(this);
        panelIngresarProveedor.botonRegresarI.addActionListener(this);

        PS = null;
        CN= new ConexionServidor();
        
        setDatosInventario();
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
                 ||panelIngresar.comboBoxUnidad.getSelectedIndex()==0 
                 ||panelIngresar.comboBoxProveedor.getSelectedIndex()==0);
    }
    
    public boolean camposOpcionVer() {
        return !(  frameVer.txtNombreInsumo.getText().isEmpty()
                 ||frameVer.txtNombreInsumo.getText().equals("Nombre")
                 //||panelIngresar.jDateFechaVencimiento.getDate()==null
                 ||frameVer.comboBoxTipoInsumo.getSelectedIndex()==0
                 ||frameVer.comboBoxUnidad.getSelectedIndex()==0 
                 ||frameVer.comboBoxProveedor.getSelectedIndex()==0);
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
        panelIngresar.comboBoxProveedor.setSelectedIndex(0);
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
    private DefaultTableModel setColumnasInventario(){
        DT = new DefaultTableModel();
        DT.addColumn("idInsumo");
        DT.addColumn("tipoinsumo");
        DT.addColumn("unidad");
        DT.addColumn("cantidad");
        DT.addColumn("vencido");
        DT.addColumn("nombre");
        DT.addColumn("proveedor");
        return DT;
    }
    
    public DefaultTableModel setDatosInventario(){
        try{
            setColumnasInventario();
            PS = CN.getConnection().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            while (RS.next()){
                int idInsumo = RS.getInt(2);
                String   tipoInsumo = RS.getString(3);
                String unidad = RS.getString(4);
                Double cantidad = RS.getDouble(5);
                boolean vencido = RS.getBoolean(6);
                String nombre = RS.getString(7);
                String proveedor = RS.getString(8);
                Insumo insumo = new Insumo(tipoInsumo,idInsumo,nombre,unidad,cantidad,vencido,proveedor);
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
                Proveedor proveedor = new Proveedor(nombre, direccion, telefono, correo, rif);
                listaProveedores.add(proveedor);
                panelIngresar.comboBoxProveedor.addItem(nombre);
                frameVer.comboBoxProveedor.addItem(nombre);
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
    
    public void mostrarCamposConsultarProveedores(boolean visible){
        panelConsultarProveedores.fondoDetalleProveedor.setVisible(visible);
        panelConsultarProveedores.labelImagen.setVisible(visible);
        panelConsultarProveedores.labelCirculo.setVisible(visible);
        panelConsultarProveedores.labelLinea.setVisible(visible);
        panelConsultarProveedores.labelNombre.setVisible(visible);
        panelConsultarProveedores.labelCedula.setVisible(visible);
        panelConsultarProveedores.labelRol.setVisible(visible);
        panelConsultarProveedores.iconoMail.setVisible(visible);
        panelConsultarProveedores.iconoMunicipio.setVisible(visible);
        panelConsultarProveedores.iconoTelefono.setVisible(visible);
        panelConsultarProveedores.labelMail.setVisible(visible);
        panelConsultarProveedores.labelMunicipio.setVisible(visible);
        panelConsultarProveedores.labelTelefono.setVisible(visible);
        panelConsultarProveedores.labelTItuloInsumos.setVisible(visible);
        panelConsultarProveedores.jScrollPaneInsumos.setVisible(visible);
        panelConsultarProveedores.panelInsumos.setVisible(visible);
        panelConsultarProveedores.fondoTitulo1.setVisible(visible);
        panelConsultarProveedores.fondoPanelInsumos.setVisible(false);
    }
    
    public void cargarInventarioProveedor(String nombre){
        panelConsultarProveedores.panelInsumos.removeAll();
        for(Insumo i: inventario.getListaInsumos()){
            if(i.getProveedor().equals(nombre)){
                PanelInsumo panel = new PanelInsumo();
                panel.setSize(310,80);
                panel.labelNombreInsumo.setText(i.getNombre());
                panel.labelCantidad.setText(String.valueOf(i.getCantidad()));
                panel.labelID.setText(String.valueOf(i.getId()));
                panel.labelID.setVisible(false);
                panel.botonVerInsumo.addActionListener(new ActionListener() {
                    @Override
                        public void actionPerformed(ActionEvent e) {
                            frameVer.setTitle("Restaurant Manager");
                            frameVer.setSize(355,547);
                            frameVer.setLocationRelativeTo(null);
                            frameVer.setResizable(false);
                            frameVer.labelTItulo.setText("Datos del Insumo");
                            frameVer.txtNombreInsumo.setText(i.getNombre());
                            frameVer.comboBoxUnidad.setSelectedItem(i.getUnidad());
                            frameVer.comboBoxTipoInsumo.setSelectedItem(i.getTipoInsumo());
                            frameVer.comboBoxProveedor.setSelectedItem(i.getProveedor());
                            frameVer.setVisible(true);
                        }
                });
                panelConsultarProveedores.panelInsumos.add(panel);
            }
        }
        panelConsultarProveedores.panelInsumos.revalidate();
        panelConsultarProveedores.panelInsumos.repaint();
    }
    
    public void cargarProveedores(){
        panelConsultarProveedores.panelProveedores.removeAll();
        mostrarCamposConsultarProveedores(false);
        for(Proveedor p: listaProveedores){
            PanelProveedor panelP = new PanelProveedor();
            panelP.setSize(308, 80);
            panelP.labelNombreProveedor.setText(p.getNombre());
            panelP.botonProveedor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarCamposConsultarProveedores(true);
                    panelConsultarProveedores.labelNombre.setText(p.getNombre());
                    panelConsultarProveedores.labelCedula.setText(p.getTelefono());
                    panelConsultarProveedores.labelMail.setText(p.getCorreo());
                    panelConsultarProveedores.labelMunicipio.setText(p.getDireccion());
                    panelConsultarProveedores.labelTelefono.setText(p.getTelefono());
                    cargarInventarioProveedor(p.getNombre());
                }
            });
            String entrada = panelConsultarProveedores.txtBuscar.getText();
            String nombre = p.getNombre().toLowerCase();
            if(nombre.contains(entrada)){
                panelConsultarProveedores.panelProveedores.add(panelP);  
            }
        }
        panelConsultarProveedores.panelProveedores.revalidate();
        panelConsultarProveedores.panelProveedores.repaint();
    }
    
    public final void cargarInventarioBuscar () {
        panelBuscar.panelInsumos.removeAll();
        // Busca los insumos correspondientes en inventario por su ID y su tipo
        
        for (Insumo i: inventario.getListaInsumos()) {
            
            // Crea los datos generales para todos los paneles
            PanelInsumo panel = new PanelInsumo();
            panel.setSize(310,80);
            panel.labelNombreInsumo.setText(i.getNombre());
            panel.labelCantidad.setText(String.valueOf(i.getCantidad()));
            panel.labelID.setText(String.valueOf(i.getId()));
            panel.labelID.setVisible(false);
            // Verifica los datos del insumo para saber en que panel colocarlos
            // Panel de vencidos
            panel.botonVerInsumo.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {
                        frameVer.setTitle("Restaurant Manager");
                        frameVer.setSize(355,547);
                        frameVer.setLocationRelativeTo(null);
                        frameVer.setResizable(false);

                        frameVer.labelTItulo.setText("Datos del Insumo");
                        frameVer.txtNombreInsumo.setText(i.getNombre());
                        frameVer.comboBoxUnidad.setSelectedItem(i.getUnidad());
                        frameVer.comboBoxTipoInsumo.setSelectedItem(i.getTipoInsumo());
                        frameVer.comboBoxProveedor.setSelectedItem(i.getProveedor());
                        
                        frameVer.setVisible(true);
                    }
             });
            String entrada = panelBuscar.txtBuscar.getText();
            String nombre = i.getNombre().toLowerCase();
            if(nombre.contains(entrada)){
                panelBuscar.panelInsumos.add(panel);  
            }
        }
        panelBuscar.panelInsumos.revalidate();
        panelBuscar.panelInsumos.repaint();
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
                            frameVer.setSize(355,547);
                            frameVer.setLocationRelativeTo(null);
                            frameVer.setResizable(false);
                            indicadorID = i.getId();
                            frameVer.labelTItulo.setText("Datos del Insumo");
                            frameVer.txtNombreInsumo.setText(i.getNombre());
                            frameVer.comboBoxUnidad.setSelectedItem(i.getUnidad());
                            frameVer.comboBoxTipoInsumo.setSelectedItem(i.getTipoInsumo());
                            frameVer.comboBoxProveedor.setSelectedItem(i.getProveedor());

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
    
    public int generarIDLote(){
        int numMax = 0;
        for (int i = 0; i<listaRegistros.size();i++){
            int num=listaRegistros.get(i).getListaLotes().get(0).getIdLote();
            if(num > numMax){
                numMax = num;
            }
        }
        return numMax + 1;
    }
    
    public void validarCeldaCantidad(TableModelEvent evento, int fila){
        if(evento.getColumn()!=4){
            return;
        }
        if(panelIngresarLotes.tablaEntradas.getValueAt(fila, 4).toString().equals("")){
            panelIngresarLotes.tablaEntradas.setValueAt(0.0, fila, 4);
            JOptionPane.showMessageDialog(null, "Ingrese todos los campos antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void cargarInsumosEntrada(){
        panelIngresarLotes.botonCambiar.setVisible(true);
        panelIngresarLotes.botonCambiar.setEnabled(true);
        panelIngresarLotes.jDateFechaV.setVisible(true);
        panelIngresarLotes.fondoBotonCambiar.setVisible(true);
        panelIngresarLotes.labelFechaV.setVisible(true);
       
        Gerente gerente = null, g = new Gerente();
        gerente = g.buscarGerente(listaGerentes,indicadorUsuario);
        String fecha = "";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        Date fechaA = cal.getTime();
        fecha = format.format(fechaA);
        
        panelIngresarLotes.txtFechaIngreso.setText(fecha);
        panelIngresarLotes.txtResponsable.setText(gerente.getNombre() + " " + gerente.getApellido());
        panelIngresarLotes.txtCedula.setText(gerente.getCedula());
        
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        fecha = format2.format(fechaActual);
        
        String datos[][] = {};
        String columna [] = {"id","Nombre","Tipo","Unidad","Cantidad","Fecha de vencimiento"};
        modeloEntrada = new DefaultTableModel(datos, columna){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class
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
        panelIngresarLotes.tablaEntradas.setModel(modeloEntrada);
        panelIngresarLotes.tablaEntradas.getTableHeader().setReorderingAllowed(false);
        for (int i = 0; i<inventario.getListaInsumos().size();i++){
            Insumo insumo = inventario.getListaInsumos().get(i);
            modeloEntrada.insertRow(0, columna);
            modeloEntrada.setValueAt(generarIDLote(),0 ,0);
            modeloEntrada.setValueAt(insumo.getNombre(), 0, 1);
            modeloEntrada.setValueAt(insumo.getTipoInsumo(), 0, 2);
            modeloEntrada.setValueAt(insumo.getUnidad(), 0, 3);
            modeloEntrada.setValueAt(0, 0, 4);
            modeloEntrada.setValueAt(fecha, 0, 5);
        }
        modeloEntrada.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evento) {
                int fila = panelIngresarLotes.tablaEntradas.getSelectedRow();
                validarCeldaCantidad(evento,fila);
            }
        });
        
    }
    
    public void cargarInsumosSalida(){
        panelIngresarLotes.botonCambiar.setVisible(false);
        panelIngresarLotes.botonCambiar.setEnabled(false);
        panelIngresarLotes.jDateFechaV.setVisible(false);
        panelIngresarLotes.fondoBotonCambiar.setVisible(false);
        panelIngresarLotes.labelFechaV.setVisible(false);
        
        Gerente gerente = null, g = new Gerente();
        gerente = g.buscarGerente(listaGerentes,indicadorUsuario);
        String fecha = "";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        Date fechaA = cal.getTime();
        fecha = format.format(fechaA);
        
        panelIngresarLotes.txtFechaIngreso.setText(fecha);
        panelIngresarLotes.txtResponsable.setText(gerente.getNombre() + " " + gerente.getApellido());
        panelIngresarLotes.txtCedula.setText(gerente.getCedula());
        
        String datos[][] = {};
        String columna [] = {"id","Nombre","Tipo","Unidad","Cantidad"};
        modeloSalida = new DefaultTableModel(datos, columna){
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
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
        panelIngresarLotes.tablaEntradas.setModel(modeloSalida);
        panelIngresarLotes.tablaEntradas.getTableHeader().setReorderingAllowed(false);
        for (int i = 0; i<inventario.getListaInsumos().size();i++){
            Insumo insumo = inventario.getListaInsumos().get(i);
            modeloSalida.insertRow(0, columna);
            modeloSalida.setValueAt(generarIDLote(),0 ,0);
            modeloSalida.setValueAt(insumo.getNombre(), 0, 1);
            modeloSalida.setValueAt(insumo.getTipoInsumo(), 0, 2);
            modeloSalida.setValueAt(insumo.getUnidad(), 0, 3);
            modeloSalida.setValueAt(0, 0, 4);
        }
        modeloSalida.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evento) {
                int fila = panelIngresarLotes.tablaEntradas.getSelectedRow();
                validarCeldaCantidad(evento,fila);
            }
        });
        
    }
    
    public int generarID(){
        int numMax = 0;
        for (int i = 0; i<inventario.getListaInsumos().size();i++){
            int num=inventario.getListaInsumos().get(i).getId();
            if(num > numMax){
                numMax = num;
            }
        }
        return numMax + 1;
    }
    
    public boolean validarFechasIngresarLoteEntrada(){
        boolean flag = false;
        for(int i = 0; i<inventario.getListaInsumos().size();i++){
            String fechaV = panelIngresarLotes.tablaEntradas.getValueAt(i, 5).toString();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaVencimiento = null;
            try {
                fechaVencimiento = formato.parse(fechaV);
            } catch (ParseException ex) {
                Logger.getLogger(ControllerInventario.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(fechaVencimiento.equals(fechaActual) || fechaVencimiento.before(fechaActual)){
                flag = true;
            }
        }
        return flag;
    }
    
    public boolean validarCantidadesIngresarLote(){
        boolean flag = true;
        for(int i = 0; i<inventario.getListaInsumos().size();i++){
            double cantidad = Double.parseDouble(panelIngresarLotes.tablaEntradas.getValueAt(i, 4).toString());
            if(cantidad > 0){
                flag = false;
            }
        }
        return flag;
    }
    
    public void cargarEntradas(String fechaFiltro){
        panelConsultarLotes.tablaRegistros.setVisible(false);
        panelConsultarLotes.jScrollPaneRegistros.setVisible(false);
        panelConsultarLotes.txtResponsable.setText("");
        panelConsultarLotes.labelResponsable.setVisible(false);
        String datos[][] = {};
        String columna [] = {"id","Nombre","Tipo","Unidad","Cantidad","Fecha de vencimiento"};
        modeloEntrada = new DefaultTableModel(datos, columna){
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex) {return false;}
        };
        panelConsultarLotes.tablaRegistros.setModel(modeloEntrada);
        panelConsultarLotes.tablaRegistros.getTableHeader().setReorderingAllowed(false);
        
        panelConsultarLotes.panelRegistros.removeAll();
        for(Registro r: listaRegistros){
            if(r.getTipo().equals("entrada")){
                PanelEntrada panelE = new PanelEntrada();
                panelE.setSize(246, 80);
                String fechaRegistro = r.getFechaRegistro();
                String responsable = r.getResponsable();
                panelE.labelFechaEntrada.setText(fechaRegistro);
                panelE.botonEntrada.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        modeloEntrada.setRowCount(0);
                        panelConsultarLotes.tablaRegistros.setVisible(true);
                        panelConsultarLotes.jScrollPaneRegistros.setVisible(true);
                        panelConsultarLotes.txtResponsable.setText(responsable);
                        panelConsultarLotes.labelResponsable.setVisible(true);
                        ArrayList<Lote> listaLotes = r.getListaLotes();
                        for (int i=0; i<listaLotes.size();i++) {
                            Lote lote = listaLotes.get(i);
                            modeloEntrada.insertRow(0, columna);
                            modeloEntrada.setValueAt(lote.getIdLote(),0 ,0);
                            modeloEntrada.setValueAt(lote.getNombre(), 0, 1);
                            modeloEntrada.setValueAt(lote.getTipo(), 0, 2);
                            modeloEntrada.setValueAt(lote.getUnidad(), 0, 3);
                            modeloEntrada.setValueAt(lote.getCantidad(), 0, 4);
                            modeloEntrada.setValueAt(lote.getFechaVencimiento(), 0, 5);
                        }
                    }
                }); 
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String fechaNueva = "";
                try {
                    Date fechaN = formato.parse(fechaRegistro);
                    fechaNueva = formato.format(fechaN);
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerInventario.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Creating a calendar object
                if(fechaNueva.equals(fechaFiltro)){
                    panelConsultarLotes.panelRegistros.add(panelE);
                }
            }
        }
        panelConsultarLotes.panelRegistros.revalidate();
        panelConsultarLotes.panelRegistros.repaint();
    }
    
    public void cargarSalidas(String fechaFiltro){
        panelConsultarLotes.tablaRegistros.setVisible(false);
        panelConsultarLotes.jScrollPaneRegistros.setVisible(false);
        panelConsultarLotes.txtResponsable.setText("");
        panelConsultarLotes.labelResponsable.setVisible(false);
        String datos[][] = {};
        String columna [] = {"id","Nombre","Tipo","Unidad","Cantidad"};
        modeloSalida = new DefaultTableModel(datos, columna){
            @Override
            public boolean isCellEditable(int rowIndex,int columnIndex) {return false;}
        };
        panelConsultarLotes.tablaRegistros.setModel(modeloSalida);
        panelConsultarLotes.tablaRegistros.getTableHeader().setReorderingAllowed(false);
        
        panelConsultarLotes.panelRegistros.removeAll();
        for(Registro r: listaRegistros){
            if(r.getTipo().equals("salida")){
                PanelSalida panelS = new PanelSalida();
                panelS.setSize(246, 80);
                String fechaRegistro = r.getFechaRegistro();
                String responsable = r.getResponsable();
                panelS.labelFechaSalida.setText(fechaRegistro);
                panelS.botonSalida.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        modeloSalida.setRowCount(0);
                        panelConsultarLotes.tablaRegistros.setVisible(true);
                        panelConsultarLotes.jScrollPaneRegistros.setVisible(true);
                        panelConsultarLotes.txtResponsable.setText(responsable);
                        panelConsultarLotes.labelResponsable.setVisible(true);
                        ArrayList<Lote> listaLotes = r.getListaLotes();
                        for (int i=0; i<listaLotes.size();i++) {
                            Lote lote = listaLotes.get(i);
                            modeloSalida.insertRow(0, columna);
                            modeloSalida.setValueAt(lote.getIdLote(),0 ,0);
                            modeloSalida.setValueAt(lote.getNombre(), 0, 1);
                            modeloSalida.setValueAt(lote.getTipo(), 0, 2);
                            modeloSalida.setValueAt(lote.getUnidad(), 0, 3);
                            modeloSalida.setValueAt(lote.getCantidad(), 0, 4);
                        }
                    }
                }); 
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String fechaNueva = "";
                try {
                    Date fechaN = formato.parse(fechaRegistro);
                    fechaNueva = formato.format(fechaN);
                } catch (ParseException ex) {
                    Logger.getLogger(ControllerInventario.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(fechaNueva.equals(fechaFiltro)){
                    panelConsultarLotes.panelRegistros.add(panelS);
                }
            }
        }
        panelConsultarLotes.panelRegistros.revalidate();
        panelConsultarLotes.panelRegistros.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BOTONES DEL PANEL: panelSistema
        
        //Boton icono de Inventario
        if(e.getSource()==panelSistema.botonInventario){
            cargarInventario (panelConsultar.comboBoxTipoInsumo.getSelectedItem().toString());
            /*inventario.revisarInsumosVencidos(fechaActual);
            if(inventario.revisarInsumosVencidos()){
                panelConsultar.botonAdvertencia.setEnabled(true);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
            } else {
                panelConsultar.botonAdvertencia.setEnabled(false);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
            }*/
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL FRAME: Ver insumo
        
        if(e.getSource()==frameVer.botonGuardar){
            boolean validado = true;
            
            if (camposOpcionVer()){
                String nombre = frameVer.txtNombreInsumo.getText();
                if (i.validarInsumo(nombre)){
                    String tipo = frameVer.comboBoxTipoInsumo.getSelectedItem().toString();
                    String unidad =  frameVer.comboBoxUnidad.getSelectedItem().toString();
                    String proveedor = frameVer.comboBoxProveedor.getSelectedItem().toString();
                    try{
                        String SQL = "UPDATE Insumo SET tipoinsumo=?, unidad=?, nombre=?, proveedor=? WHERE idInsumo=?";
                        PS = CN.getConnection().prepareStatement(SQL);
                        PS.setString(1, tipo);
                        PS.setString(2, unidad);
                        PS.setString(3, nombre);
                        PS.setString(4, proveedor);
                        PS.setInt(5, indicadorID);
                    int res = PS.executeUpdate();
                    if (res > 0) {
                        inventario.modificarInsumo(tipo, unidad, nombre, proveedor, indicadorID);
                        JOptionPane.showMessageDialog(null, "El Insumo ha sido modificado con éxito.", "", 1);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar los datos del insumo en la base de datos: " + ex.getMessage(), "Error", 0);
                } finally {
                    PS = null;
                    CN.desconectar();
                }
                   
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos antes de modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if(e.getSource()==frameVer.botonEliminar){
            int op = JOptionPane.showOptionDialog(null, "¿Desea eliminar el insumo " + frameVer.txtNombreInsumo.getText() + "?", "Inventario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (op == 0) {
                try {
                    String SQL = "DELETE FROM Insumo WHERE idInsumo = ?";
                    PS = CN.getConnection().prepareStatement(SQL);
                    PS.setInt(1, indicadorID);
                    int res = PS.executeUpdate();
                    if (res > 0) {
                        inventario.eliminarInsumo(indicadorID);
                        cargarInventario(panelConsultar.comboBoxTipoInsumo.getSelectedItem().toString());
                        JOptionPane.showMessageDialog(null, "El Insumo ha sido eliminado con éxito", "", 1);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el Insumo de la base de datos: " + ex.getMessage(), "Error", 0);
                } finally {
                    PS = null;
                    CN.desconectar();
                }
            }
            
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
            inventario.mandarMensajeInsumosVencidos();
        }
        
        if(e.getSource()== panelConsultar.botonEntrada){
            cargarInsumosEntrada();
            panelIngresarLotes.jDateFechaV.setDate(fechaActual);
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
            cargarInsumosSalida();
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
            cargarInventarioBuscar () ;
            panelBuscar.setSize(926,720);
            panelBuscar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelBuscar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource()==panelConsultar.botonProveedor){
            cargarProveedores();
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
            /*inventario.revisarInsumosVencidos(fechaActual);
            if(inventario.revisarInsumosVencidos()){
                panelConsultar.botonAdvertencia.setEnabled(true);
                panelConsultar.fondoBotonAdvertencia.setEnabled(true);
            } else {
                panelConsultar.botonAdvertencia.setEnabled(false);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
            }*/
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
            resetearCamposIngresar();
        }        
        
        if (e.getSource()==panelIngresar.botonRegistrar){
            
            boolean validado = true;
            // Campos vacios
            if (camposOpcionIngresar()){
                String nombre = panelIngresar.txtNombreInsumo.getText();
                int ID = generarID();
                if (!inventario.iDRepetido(ID)) {
                    if(i.validarInsumo(nombre)){
                        if(!(inventario.nombreRepetido(nombre))){
                            String tipo = panelIngresar.comboBoxTipoInsumo.getSelectedItem().toString();
                            String unidad = panelIngresar.comboBoxUnidad.getSelectedItem().toString();
                            String proveedor = panelIngresar.comboBoxProveedor.getSelectedItem().toString();
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
                                Insumo in = new Insumo(tipo, ID, nombre, unidad, proveedor);
                                    try{
                                        PS=CN.getConnection().prepareStatement(SQL_INSERT);
                                        PS.setInt(1, ID);
                                        PS.setString(2, tipo);
                                        PS.setString(3, unidad);
                                        PS.setDouble(4, 0);
                                        PS.setString(5, nombre); 
                                        PS.setString(6, proveedor);
                                        int res = PS.executeUpdate();
                                        if (res > 0){
                                            inventario.agregarInsumo(in);
                                            JOptionPane.showMessageDialog(null, "El insumo ha sido registrado con éxito", "", 1);
                                        }
                                    } catch(SQLException ex){   
                                        JOptionPane.showMessageDialog(null, "Error al guardar los datos del insumo en la base de datos: " +ex.getMessage(), "Error", 0);
                                    } finally{
                                        PS=null;
                                        CN.desconectar();
                                    }
                                //JOptionPane.showMessageDialog(null, "El insumo ha sido registrado con éxito", "", 1);
                                // Crear panel de insumo 
                                cargarInventario (panelConsultar.comboBoxTipoInsumo.getSelectedItem().toString());
                                /*inventario.revisarInsumosVencidos(fechaActual);
                                if(inventario.revisarInsumosVencidos()){
                                    panelConsultar.botonAdvertencia.setEnabled(true);
                                    panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
                                } else {
                                    panelConsultar.botonAdvertencia.setEnabled(false);
                                    panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
                                }*/
                                panelConsultar.setSize(926,720);
                                panelConsultar.setLocation(0,0);
                                panelSistema.panelPrincipal.removeAll();
                                panelSistema.panelPrincipal.add(panelConsultar);
                                panelSistema.panelPrincipal.revalidate();
                                panelSistema.panelPrincipal.repaint();
                                resetearCamposIngresar();   
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
        }
        
        //BOTONES DEL PANEL: panelBuscarInsumo
        
        if(e.getSource() == panelBuscar.botonRegresarI){
            cargarInventario (panelConsultar.comboBoxTipoInsumo.getSelectedItem().toString());
            /*inventario.revisarInsumosVencidos(fechaActual);
            if(inventario.revisarInsumosVencidos()){
                panelConsultar.botonAdvertencia.setEnabled(true);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
            } else {
                panelConsultar.botonAdvertencia.setEnabled(false);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
            }*/
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
            /*inventario.revisarInsumosVencidos(fechaActual);
            if(inventario.revisarInsumosVencidos()){
                panelConsultar.botonAdvertencia.setEnabled(true);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
            } else {
                panelConsultar.botonAdvertencia.setEnabled(false);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
            }*/
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: Ingresar Lotes
        
        if(e.getSource() == panelIngresarLotes.botonCambiar){
            int fila = panelIngresarLotes.tablaEntradas.getSelectedRow();
            if(fila >= 0){
                Date fechaVencimiento = panelIngresarLotes.jDateFechaV.getDate();
                if(fechaVencimiento != null){
                    if(fechaVencimiento.equals(fechaActual) || fechaVencimiento.before(fechaActual)){
                        JOptionPane.showMessageDialog(null, "La fecha de vencimiento no puede ser igual o anterior a la fecha de hoy", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        String fechaV = format.format(fechaVencimiento);
                        panelIngresarLotes.tablaEntradas.setValueAt(fechaV, fila, 5);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Coloque una fecha de vencimiento válida", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un insumo de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if(e.getSource() == panelIngresarLotes.botonRegistrarE){
            if(!validarFechasIngresarLoteEntrada()){
                if(!validarCantidadesIngresarLote()){
                    ArrayList<Lote> listaLotes = new ArrayList<>();
                    for (int i = 0; i<inventario.getListaInsumos().size(); i++){
                        int j = 0;
                        int id = Integer.parseInt(panelIngresarLotes.tablaEntradas.getValueAt(i, j).toString()); j++;
                        String nombre = panelIngresarLotes.tablaEntradas.getValueAt(i, j).toString();j++;
                        String tipo = panelIngresarLotes.tablaEntradas.getValueAt(i, j).toString();j++;
                        String unidad = panelIngresarLotes.tablaEntradas.getValueAt(i, j).toString();j++;
                        double cantidad = Double.parseDouble(panelIngresarLotes.tablaEntradas.getValueAt(i,j).toString());j++;
                        String fechaV = panelIngresarLotes.tablaEntradas.getValueAt(i, j).toString();
                         
                        int largo = inventario.getListaInsumos().size();
                        int posicion = largo - i - 1;
                        Lote lote = new Lote(inventario.getListaInsumos().get(posicion).getId(), id,nombre,tipo,unidad,cantidad, fechaV);
                        listaLotes.add(lote);
                    }
                    String fechaRegistro = panelIngresarLotes.txtFechaIngreso.getText();
                    String responsable = panelIngresarLotes.txtResponsable.getText();
                    String documento = panelIngresarLotes.txtCedula.getText();
                    Registro registro = new Registro("entrada",fechaRegistro,responsable,documento,listaLotes);
                    listaRegistros.add(registro);
                    JOptionPane.showMessageDialog(null, "El registro de entrada de insumos ha sido guardado satisfactoriamente", "", 1);
                    cargarInsumosEntrada();
                } else {
                    JOptionPane.showMessageDialog(null, "Debe haber al menos un insumo con una cantidad mayor a 0", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Las fechas de vencimiento no pueden ser iguales o anteriores a la fecha de hoy", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if(e.getSource() == panelIngresarLotes.botonRegistrarS){
            if(!validarCantidadesIngresarLote()){
                ArrayList<Lote> listaLotes = new ArrayList<>();
                for (int i = 0; i<inventario.getListaInsumos().size(); i++){
                    int j = 0;
                    int id = Integer.parseInt(panelIngresarLotes.tablaEntradas.getValueAt(i, j).toString()); j++;
                    String nombre = panelIngresarLotes.tablaEntradas.getValueAt(i, j).toString();j++;
                    String tipo = panelIngresarLotes.tablaEntradas.getValueAt(i, j).toString();j++;
                    String unidad = panelIngresarLotes.tablaEntradas.getValueAt(i, j).toString();j++;
                    double cantidad = Double.parseDouble(panelIngresarLotes.tablaEntradas.getValueAt(i,j).toString());
                         
                    int largo = inventario.getListaInsumos().size();
                    int posicion = largo - i - 1;
                    Lote lote = new Lote(inventario.getListaInsumos().get(posicion).getId(), id,nombre,tipo,unidad,cantidad, "");
                    listaLotes.add(lote);
                } 
                String fechaRegistro = panelIngresarLotes.txtFechaIngreso.getText();
                String responsable = panelIngresarLotes.txtResponsable.getText();
                String documento = panelIngresarLotes.txtCedula.getText();
                Registro registro = new Registro("salida",fechaRegistro,responsable,documento,listaLotes);
                listaRegistros.add(registro);
                cargarInsumosSalida();
                JOptionPane.showMessageDialog(null, "El registro de entrada de insumos ha sido guardado satisfactoriamente", "", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Debe haber al menos un insumo con una cantidad mayor a 0", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if(e.getSource() == panelIngresarLotes.botonConsultarEntradas){
            panelConsultarLotes.jDateFiltro.setDate(fechaActual);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String fechaF = format.format(fechaActual);
            cargarEntradas(fechaF);
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
            panelConsultarLotes.jDateFiltro.setDate(fechaActual);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String fechaF = format.format(fechaActual);
            cargarSalidas(fechaF);
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
            /*inventario.revisarInsumosVencidos(fechaActual);
            if(inventario.revisarInsumosVencidos()){
                panelConsultar.botonAdvertencia.setEnabled(true);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertenciaActivado.png")));
            } else {
                panelConsultar.botonAdvertencia.setEnabled(false);
                panelConsultar.fondoBotonAdvertencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonAdvertencia.png")));
            }*/
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
                if(proveedor.validarDatosEmpresa(nombre, rif, correo, telefono)&&!(proveedor.verificarProveedorRepetido(listaProveedores,rif,nombre))){
                    Proveedor p = new Proveedor(nombre, direccion, telefono, correo ,rif);
                    listaProveedores.add(p);
                    panelIngresar.comboBoxProveedor.addItem(nombre);
                    frameVer.comboBoxProveedor.addItem(nombre);
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
            cargarProveedores();
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

    @Override
    public void keyTyped(KeyEvent e) {
        panelConsultarProveedores.txtBuscar.addKeyListener(new KeyAdapter (){
            @Override
            public void keyReleased(KeyEvent e) {
                cargarProveedores();
            }
        });
        panelBuscar.txtBuscar.addKeyListener(new KeyAdapter (){
            @Override
            public void keyReleased(KeyEvent e) {
                cargarInventarioBuscar ();
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
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getSource()==panelConsultarLotes.jDateFiltro){
            Date fechaR = panelConsultarLotes.jDateFiltro.getDate();
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            String fechaRegistro = formatter.format(fechaR);
            if(panelConsultarLotes.labelTItulo.getText().equalsIgnoreCase("entradas")){
                cargarEntradas(fechaRegistro);
            } else {
                cargarSalidas(fechaRegistro);
            }
        }
    }
    
}
