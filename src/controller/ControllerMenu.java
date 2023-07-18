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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.conexionBD.ConexionServidor;
import model.modelmenu.Bebida;
import model.modelmenu.Menu;
import model.modelmenu.Plato;
import view.viewmenu.PanelBebida;
import view.viewmenu.PanelConsultarMenu;
import view.viewmenu.PanelIngresarBebida;
import view.viewmenu.PanelIngresarMenu;
import view.viewmenu.PanelIngresarMenuS1;
import view.viewmenu.PanelIngresarMenuS2;
import view.viewmenu.PanelIngresarMenuS3;
import view.viewmenu.PanelIngresarMenuS4;
import view.viewmenu.PanelIngresarMenuS5;
import view.viewmenu.PanelIngresarMenuS6;
import view.viewmenu.PanelIngresarPlato;
import view.viewmenu.PanelPlato;
import view.viewprincipal.SistemaPrincipal;

/**
 *
 * @author emmez
 */
public class ControllerMenu implements ActionListener, ItemListener, KeyListener{
    
    private Plato plato = new Plato();
    private Bebida bebida = new Bebida();
    
    private int indicadorIDPlato = -1;
    private int indicadorIDBebida = -1;       
    
    private static final ArrayList<Plato> listaPlatos = new ArrayList<>();
    private static final ArrayList<Bebida> listaBebidas = new ArrayList<>();
    private static final ArrayList<Menu> listaMenus = new ArrayList<>();
    
    private static ArrayList<Plato> listaPlatosTemporal = new ArrayList<>();
    private static ArrayList<Bebida> listaBebidasTemporal = new ArrayList<>();
    
    private PreparedStatement PS;                                                                                                                   
    private final ConexionServidor CN;
    
    private final String SQL_INSERT_PLATOBEBIDA = "INSERT INTO PlatoBebida (id,nombre,categoria,descripcion,tipo) values (?,?,?,?,?)";
    private PreparedStatement PS_PLATOBEBIDA;
    
    private final String SQL_SELECT_PLATOBEBIDA = "SELECT * FROM PlatoBebida";
    private DefaultTableModel DT_PLATOBEBIDA;
    private ResultSet RS_PLATOBEBIDA;
    
    private final SistemaPrincipal panelSistema;
    private final PanelConsultarMenu panelConsultar = new PanelConsultarMenu();
    private final PanelIngresarPlato panelIngresarPlato = new PanelIngresarPlato();
    private final PanelIngresarBebida panelIngresarBebida = new PanelIngresarBebida();
    private final PanelIngresarMenu panelIngresarMenu = new PanelIngresarMenu();
    
    private final PanelIngresarMenuS1 panelMS1 = new PanelIngresarMenuS1();
    private final PanelIngresarMenuS2 panelMS2 = new PanelIngresarMenuS2();
    private final PanelIngresarMenuS3 panelMS3 = new PanelIngresarMenuS3();
    private final PanelIngresarMenuS4 panelMS4 = new PanelIngresarMenuS4();
    private final PanelIngresarMenuS5 panelMS5 = new PanelIngresarMenuS5();
    private final PanelIngresarMenuS6 panelMS6 = new PanelIngresarMenuS6();
    
    
    public ControllerMenu(SistemaPrincipal panelSistema) {
        //Ventana del sistema
        this.panelSistema = panelSistema;
        this.panelSistema.botonMenu.addActionListener(this);
        
        //Panel Consultar Menú
        panelConsultar.botonIngresar.addActionListener(this);
        panelConsultar.botonEditar.addActionListener(this);
        panelConsultar.comboBoxVista.addItemListener(this);
        
        //Panel Ingresar Menú
        panelIngresarMenu.botonRegresar.addActionListener(this);
        
        //Panel Ingresar Menú 1
        panelMS1.botonContinuar.addActionListener(this);
        
        //Panel Ingresar Menú 2
        panelMS2.botonContinuar.addActionListener(this);
        panelMS2.botonRegresar.addActionListener(this);
        
        //Panel Ingresar Menú 3
        panelMS3.botonContinuar.addActionListener(this);
        panelMS3.botonRegresar.addActionListener(this);
        
        //Panel Ingresar Menú 4
        panelMS4.botonContinuar.addActionListener(this);
        panelMS4.botonRegresar.addActionListener(this);
        
        //Panel Ingresar Menú 5
        panelMS5.botonContinuar.addActionListener(this);
        panelMS5.botonRegresar.addActionListener(this);
        
        //Panel Ingresar Menú 6
        panelMS6.botonRegistrar.addActionListener(this);
        panelMS6.botonRegresar.addActionListener(this);
        
        //Panel Ingresar Plato
        panelIngresarPlato.botonRegistrar.addActionListener(this);
        panelIngresarPlato.botonRegresar.addActionListener(this);
        panelIngresarPlato.botonGuardarCambios.addActionListener(this);

        //Panel Ingresar Bebida
        panelIngresarBebida.botonRegistrar.addActionListener(this);
        panelIngresarBebida.botonRegresar.addActionListener(this);
        panelIngresarBebida.botonGuardarCambios.addActionListener(this);
        
        PS = null;
        CN= new ConexionServidor();
        
        setDatosPlatoBebida();
        
    }
    
    private DefaultTableModel setColumnasPlatoBebida(){
        DT_PLATOBEBIDA = new DefaultTableModel();
        DT_PLATOBEBIDA.addColumn("id");
        DT_PLATOBEBIDA.addColumn("nombre");
        DT_PLATOBEBIDA.addColumn("categoria");
        DT_PLATOBEBIDA.addColumn("descripcion");
        DT_PLATOBEBIDA.addColumn("tipo");
        return DT_PLATOBEBIDA;
    }
    
    /* Obtiene los datos de cada Proveedor de la tabla de la BD y los 
       registra en la lista de Proveedores del programa */
    public DefaultTableModel setDatosPlatoBebida(){
        try{
            // Establece modelo de tabla
            setColumnasPlatoBebida();
            // Conexion a BD para obtener informacion de tabla Empresa
            PS_PLATOBEBIDA = CN.getConnection().prepareStatement(SQL_SELECT_PLATOBEBIDA);
            RS_PLATOBEBIDA = PS_PLATOBEBIDA.executeQuery();
            // Busca datos de cada columna
            while (RS_PLATOBEBIDA.next()){
                int ID = RS_PLATOBEBIDA.getInt(1);
                String nombre = RS_PLATOBEBIDA.getString(2);
                String categoria = RS_PLATOBEBIDA.getString(3);
                String descripcion = RS_PLATOBEBIDA.getString(4);
                String tipo = RS_PLATOBEBIDA.getString(5);
                
                if (tipo.equals("p")){
                    Plato p = new Plato(ID,nombre,categoria,descripcion);
                    listaPlatos.add(p);
                }else if(tipo.equals("b")){
                    Bebida b = new Bebida(ID,nombre,categoria,descripcion);
                    listaBebidas.add(b);
                }
                
            }
        }
        // Excepcion si no se pudo listar las tablas
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar los datos del proveedor: " +e.getMessage(), "Error", 0);
        }
        // Finaliza conexion con BD
        finally{
            PS_PLATOBEBIDA=null;
            RS_PLATOBEBIDA=null;
            CN.desconectar();
        }
        return DT_PLATOBEBIDA;
    }
    
    public boolean camposVaciosPanelRegistrarPlato() {
    return panelIngresarPlato.txtNombre.getText().isEmpty() || panelIngresarPlato.txtNombre.getText().equals("Nombre")
        || panelIngresarPlato.txtDescripcion.getText().isEmpty() || panelIngresarPlato.txtDescripcion.getText().equals("")
        || panelIngresarPlato.comboBoxCategoria.getSelectedIndex() == 0;
    }
    
    public boolean camposVaciosPanelRegistrarBebida(){
    return panelIngresarBebida.txtNombre.getText().isEmpty() || panelIngresarBebida.txtNombre.getText().equals("Nombre")
            ||panelIngresarBebida.txtDescripcion.getText().isEmpty() || panelIngresarBebida.txtDescripcion.getText().equals("")
            ||panelIngresarBebida.comboBoxCategoria.getSelectedIndex()==0;
    }
    
    public int generarIDPlato(){
        int numMax = 0;
        for (int i = 0; i<listaPlatos.size();i++){
            int num=listaPlatos.get(i).getID();
            if(num > numMax){
                numMax = num;
            }
        }
        return numMax + 1;
    }  
    
        public int generarIDBebida(){
        int numMax = 0;
        for (int i = 0; i<listaBebidas.size();i++){
            int num=listaBebidas.get(i).getID();
            if(num > numMax){
                numMax = num;
            }
        }
        return numMax + 1;
    }  
    
    public int buscarCategoria (String categoria){
        switch (categoria){
            case "Entrada": return 1;
            case "Primer Tiempo":return 2;
            case "Segundo Tiempo": return 3;
            case "Tercer Tiempo": return 4;
            case "Postre": return 5;
            default:
                return 0;
        }
    }
    
    public void mostrarDatosBedidaoPlato(boolean visible){
        panelConsultar.labelFondoDatosPoB.setVisible(visible);
        panelConsultar.labelImagenPoB.setVisible(visible);
        panelConsultar.labelLineaPoB.setVisible(visible);
        panelConsultar.labelTipoPoB.setVisible(visible);
        panelConsultar.labelNombrePoB.setVisible(visible);
        panelConsultar.labelID.setVisible(visible);
        panelConsultar.txtDescripcion.setVisible(visible);
        panelConsultar.jScrollPaneDescripcion.setVisible(visible);
    }
    
    public void mostrarDatosMenu(boolean visible){
        panelConsultar.labelFondoDetalleMenu.setVisible(visible);
        panelConsultar.labelImagenMenu.setVisible(visible);
        panelConsultar.labelLineaMenu.setVisible(visible);
        panelConsultar.labelTipoMenu.setVisible(visible);
        panelConsultar.labelFondoPanelTiempos.setVisible(visible);
        panelConsultar.jScrollPaneTiempos.setVisible(visible);
        panelConsultar.panelTiempos.setVisible(visible);
        panelConsultar.labelNombreMenu.setVisible(visible);
    }
    
    public void cargarPlatos(){
        mostrarDatosBedidaoPlato(false);
        mostrarDatosMenu(false);
        panelConsultar.panelMenu.removeAll();
        for (Plato pl : listaPlatos) {
            PanelPlato panelP = new PanelPlato();
            panelP.setSize(310,70);
            panelP.labelNombrePlato.setText(pl.getNombre());
            panelP.botonPlato.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Actualizar el texto del JLabel
                    //indicadorCedula = em.getCedula();
                    panelConsultar.labelImagenPoB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/imagenPlato.png")));
                    mostrarDatosBedidaoPlato(true);    
                    panelConsultar.labelID.setText(Integer.toString(pl.getID()));
                    panelConsultar.labelNombrePoB.setText(pl.getNombre());
                    panelConsultar.labelTipoPoB.setText("Plato");
                    panelConsultar.txtDescripcion.setText(pl.getDescripcion());
                }
            });
            panelConsultar.panelMenu.add(panelP);
            
        }
        panelConsultar.panelMenu.revalidate();
        panelConsultar.panelMenu.repaint();
    }
    
    public void cargarBebidas(){
        mostrarDatosBedidaoPlato(false);
        mostrarDatosMenu(false);
        panelConsultar.panelMenu.removeAll();
        for (Bebida be : listaBebidas) {
            PanelBebida panelB = new PanelBebida();
            panelB.setSize(310,70);
            panelB.labelNombreBebida.setText(be.getNombre());
            panelB.botonBebida.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Actualizar el texto del JLabel
                    //indicadorCedula = em.getCedula();
                    panelConsultar.labelImagenPoB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/imagenBebida.png")));
                    mostrarDatosBedidaoPlato(true);
                    panelConsultar.labelID.setText(Integer.toString(be.getID()));
                    panelConsultar.labelNombrePoB.setText(be.getNombre());
                    panelConsultar.labelTipoPoB.setText("Bebida");
                    panelConsultar.txtDescripcion.setText(be.getDescripcion());
                }
            });
            panelConsultar.panelMenu.add(panelB);
        }
        panelConsultar.panelMenu.revalidate();
        panelConsultar.panelMenu.repaint();
    }
    
    public void cargarMenus(){
        mostrarDatosBedidaoPlato(false);
        mostrarDatosMenu(false);
        panelConsultar.panelMenu.removeAll();
        panelConsultar.panelMenu.revalidate();
        panelConsultar.panelMenu.repaint();
    }
    
    public void cargarPaneles(String tipo){
        if(tipo.equals("Menús")){
            cargarMenus();
        }
        if(tipo.equals("Platos")){
            cargarPlatos();
        }
        if(tipo.equals("Bebidas")){
            cargarBebidas();
        }
    }
    
    public boolean camposVaciosMS1(){
        return panelMS1.txtNombre.getText().equals("Nombre") || panelMS1.txtNombre.equals("");
    }

    public boolean camposVaciosMS2(){
        return panelMS2.comboBoxP1.getSelectedIndex() == 0 &&
               panelMS2.comboBoxP2.getSelectedIndex() == 0 &&
               panelMS2.comboBoxP3.getSelectedIndex() == 0 &&
               panelMS2.comboBoxP4.getSelectedIndex() == 0 &&
               panelMS2.comboBoxP5.getSelectedIndex() == 0 &&
               panelMS2.comboBoxP6.getSelectedIndex() == 0 ;
    }
    
    public boolean camposVaciosMS3(){
        return panelMS3.comboBoxP1.getSelectedIndex() == 0 &&
               panelMS3.comboBoxP2.getSelectedIndex() == 0 &&
               panelMS3.comboBoxP3.getSelectedIndex() == 0 &&
               panelMS3.comboBoxP4.getSelectedIndex() == 0 &&
               panelMS3.comboBoxP5.getSelectedIndex() == 0 &&
               panelMS3.comboBoxP6.getSelectedIndex() == 0 ;
    }
    
    public boolean camposVaciosMS4(){
        return panelMS4.comboBoxP1.getSelectedIndex() == 0 &&
               panelMS4.comboBoxP2.getSelectedIndex() == 0 &&
               panelMS4.comboBoxP3.getSelectedIndex() == 0 &&
               panelMS4.comboBoxP4.getSelectedIndex() == 0 &&
               panelMS4.comboBoxP5.getSelectedIndex() == 0 &&
               panelMS4.comboBoxP6.getSelectedIndex() == 0 ;
    }

    public boolean camposVaciosMS5(){
        return panelMS5.comboBoxP1.getSelectedIndex() == 0 &&
               panelMS5.comboBoxP2.getSelectedIndex() == 0 &&
               panelMS5.comboBoxP3.getSelectedIndex() == 0 &&
               panelMS5.comboBoxP4.getSelectedIndex() == 0 &&
               panelMS5.comboBoxP5.getSelectedIndex() == 0 &&
               panelMS5.comboBoxP6.getSelectedIndex() == 0 ;
    }
    
    public boolean camposVaciosMS6(){
        return panelMS6.comboBoxP1.getSelectedIndex() == 0 &&
               panelMS6.comboBoxP2.getSelectedIndex() == 0 &&
               panelMS6.comboBoxP3.getSelectedIndex() == 0 &&
               panelMS6.comboBoxP4.getSelectedIndex() == 0 &&
               panelMS6.comboBoxP5.getSelectedIndex() == 0 &&
               panelMS6.comboBoxP6.getSelectedIndex() == 0 ;
    }
    
    public boolean bebidaPlatoRepetido(String nombre){
        for(Plato p: listaPlatosTemporal){
            if(p.getNombre().equals(nombre)){
                return true;
            }
        }
        for(Bebida b: listaBebidasTemporal){
            if(b.getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BOTONES DEL PANEL: panelSistema
        
        //Boton icono de menu
        if(e.getSource() == panelSistema.botonMenu){
            cargarPaneles(panelConsultar.comboBoxVista.getSelectedItem().toString());
            panelConsultar.labelID.setVisible(false);
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
       //BOTONES DEL PANEL: Panel Consultar
        
        if(e.getSource()==panelConsultar.botonIngresar){
            if (panelConsultar.comboBoxVista.getSelectedItem().equals("Platos")){
                panelIngresarPlato.botonGuardarCambios.setVisible(false);
                panelIngresarPlato.botonRegistrar.setVisible(true);
                panelIngresarPlato.setSize(926,720);
                panelIngresarPlato.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelIngresarPlato);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();  
            }
            if (panelConsultar.comboBoxVista.getSelectedItem().equals("Bebidas")){
                panelIngresarBebida.botonGuardarCambios.setVisible(false);
                panelIngresarBebida.botonRegistrar.setVisible(true);
                panelIngresarBebida.setSize(926,720);
                panelIngresarBebida.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelIngresarBebida);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();  
            }
            if(panelConsultar.comboBoxVista.getSelectedItem().equals("Menús")){
                panelMS1.setSize(385,615);
                panelMS1.setLocation(0,0);
                panelIngresarMenu.setSize(926,720);
                panelIngresarMenu.setLocation(0,0);
                panelIngresarMenu.panelIngresar.removeAll();
                panelIngresarMenu.panelIngresar.add(panelMS1);
                panelIngresarMenu.panelIngresar.revalidate();
                panelIngresarMenu.panelIngresar.repaint();
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelIngresarMenu);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint(); 
            }
              
        }
        
        
        //BOTONES DEL PANEL: Panel Ingresar Menu
        
        if(e.getSource() == panelIngresarMenu.botonRegresar){
            cargarPaneles(panelConsultar.comboBoxVista.getSelectedItem().toString());
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        //BOTONES DEL PANEL: Panel Ingresar Menu S1
        
        if(e.getSource()==panelMS1.botonContinuar){
            if(!camposVaciosMS1()){
                panelMS2.setSize(385,615);
                panelMS2.setLocation(0,0);
                panelIngresarMenu.panelIngresar.removeAll();
                panelIngresarMenu.panelIngresar.add(panelMS2);
                panelIngresarMenu.panelIngresar.revalidate();
                panelIngresarMenu.panelIngresar.repaint();
                
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            
        }
        
        //BOTONES DEL PANEL: Panel Ingresar Menu S2
        
        if(e.getSource()==panelMS2.botonRegresar){
            panelMS1.setSize(385,615);
            panelMS1.setLocation(0,0);
            panelIngresarMenu.panelIngresar.removeAll();
            panelIngresarMenu.panelIngresar.add(panelMS1);
            panelIngresarMenu.panelIngresar.revalidate();
            panelIngresarMenu.panelIngresar.repaint();
        }
        
        if(e.getSource()==panelMS2.botonContinuar){
            if(!camposVaciosMS2()){
                panelMS3.setSize(385,615);
                panelMS3.setLocation(0,0);
                panelIngresarMenu.panelIngresar.removeAll();
                panelIngresarMenu.panelIngresar.add(panelMS3);
                panelIngresarMenu.panelIngresar.revalidate();
                panelIngresarMenu.panelIngresar.repaint();    
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar al menos un plato o bebida", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //BOTONES DEL PANEL: Panel Ingresar Menu S3
        
        if(e.getSource()==panelMS3.botonRegresar){
            panelMS2.setSize(385,615);
            panelMS2.setLocation(0,0);
            panelIngresarMenu.panelIngresar.removeAll();
            panelIngresarMenu.panelIngresar.add(panelMS2);
            panelIngresarMenu.panelIngresar.revalidate();
            panelIngresarMenu.panelIngresar.repaint();
        }
        
        if(e.getSource()==panelMS3.botonContinuar){
            if(!camposVaciosMS3()){
                panelMS4.setSize(385,615);
                panelMS4.setLocation(0,0);
                panelIngresarMenu.panelIngresar.removeAll();
                panelIngresarMenu.panelIngresar.add(panelMS4);
                panelIngresarMenu.panelIngresar.revalidate();
                panelIngresarMenu.panelIngresar.repaint();    
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar al menos un plato o bebida", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //BOTONES DEL PANEL: Panel Ingresar Menu S4
        
        if(e.getSource()==panelMS4.botonRegresar){
            panelMS3.setSize(385,615);
            panelMS3.setLocation(0,0);
            panelIngresarMenu.panelIngresar.removeAll();
            panelIngresarMenu.panelIngresar.add(panelMS3);
            panelIngresarMenu.panelIngresar.revalidate();
            panelIngresarMenu.panelIngresar.repaint();
        }
        
        if(e.getSource()==panelMS4.botonContinuar){
            if(!camposVaciosMS4()){
                panelMS5.setSize(385,615);
                panelMS5.setLocation(0,0);
                panelIngresarMenu.panelIngresar.removeAll();
                panelIngresarMenu.panelIngresar.add(panelMS5);
                panelIngresarMenu.panelIngresar.revalidate();
                panelIngresarMenu.panelIngresar.repaint();    
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar al menos un plato o bebida", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //BOTONES DEL PANEL: Panel Ingresar Menu S5
        
        if(e.getSource()==panelMS5.botonRegresar){
            panelMS4.setSize(385,615);
            panelMS4.setLocation(0,0);
            panelIngresarMenu.panelIngresar.removeAll();
            panelIngresarMenu.panelIngresar.add(panelMS4);
            panelIngresarMenu.panelIngresar.revalidate();
            panelIngresarMenu.panelIngresar.repaint();
        }
        
        if(e.getSource()==panelMS5.botonContinuar){
            if(!camposVaciosMS5()){
                panelMS6.setSize(385,615);
                panelMS6.setLocation(0,0);
                panelIngresarMenu.panelIngresar.removeAll();
                panelIngresarMenu.panelIngresar.add(panelMS6);
                panelIngresarMenu.panelIngresar.revalidate();
                panelIngresarMenu.panelIngresar.repaint();    
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar al menos un plato o bebida", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        //BOTONES DEL PANEL: Panel Ingresar Menu S6
        
        if(e.getSource()==panelMS6.botonRegresar){
            panelMS5.setSize(385,615);
            panelMS5.setLocation(0,0);
            panelIngresarMenu.panelIngresar.removeAll();
            panelIngresarMenu.panelIngresar.add(panelMS5);
            panelIngresarMenu.panelIngresar.revalidate();
            panelIngresarMenu.panelIngresar.repaint();
        }
        
        if(e.getSource()==panelMS6.botonRegistrar){
            if(!camposVaciosMS6()){
                
            }else {
                JOptionPane.showMessageDialog(null, "Debe ingresar al menos un plato o bebida", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
            
        if (e.getSource() == panelIngresarPlato.botonRegistrar) {
            if (!camposVaciosPanelRegistrarPlato()) {
                String nombre = panelIngresarPlato.txtNombre.getText();
                String categoria = panelIngresarPlato.comboBoxCategoria.getSelectedItem().toString();
                String descripcion = panelIngresarPlato.txtDescripcion.getText();
                if (!(plato.nombrePlatoRepetido(listaPlatos, nombre))) {
                    Plato pl = new Plato();
                    int ID = generarIDPlato();
                    pl.setID(ID);
                    pl.setNombre(nombre);
                    pl.setCategoria(categoria);
                    pl.setDescripcion(descripcion);
                    listaPlatos.add(pl);
                    try{
                            PS_PLATOBEBIDA=CN.getConnection().prepareStatement(SQL_INSERT_PLATOBEBIDA);
                            PS_PLATOBEBIDA.setInt(1, ID);
                            PS_PLATOBEBIDA.setString(2, nombre);
                            PS_PLATOBEBIDA.setString(3, categoria);
                            PS_PLATOBEBIDA.setString(4, descripcion);
                            PS_PLATOBEBIDA.setString(5, "p");
                            int res = PS_PLATOBEBIDA.executeUpdate();
                            if (res > 0){
                                JOptionPane.showMessageDialog(null, "El plato ha sido registrado con éxito.", "", 1);
                            }
                        }catch(SQLException ex){
                            JOptionPane.showMessageDialog(null, "Error al guardar los datos del plato en la base de datos: " +ex.getMessage(), "Error", 0);
                        }
                        finally{
                            PS_PLATOBEBIDA=null;
                            CN.desconectar();
                        }

                    // Limpia los campos del panel
                    panelIngresarPlato.txtNombre.setText("");
                    panelIngresarPlato.comboBoxCategoria.setSelectedIndex(0);
                    panelIngresarPlato.txtDescripcion.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "Nombre repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if (e.getSource() == panelIngresarBebida.botonRegistrar) {
            if (!camposVaciosPanelRegistrarBebida()) {
                String nombre = panelIngresarBebida.txtNombre.getText();
                String categoria = panelIngresarBebida.comboBoxCategoria.getSelectedItem().toString();
                String descripcion = panelIngresarBebida.txtDescripcion.getText();
                if (!(bebida.nombreBebidaRepetida(listaBebidas, nombre))) {
                    Bebida b = new Bebida();
                    int ID = generarIDBebida();
                    b.setID(ID);
                    b.setNombre(nombre);
                    b.setCategoria(categoria);
                    b.setDescripcion(descripcion);
                    listaBebidas.add(b);
                    try{
                            PS_PLATOBEBIDA=CN.getConnection().prepareStatement(SQL_INSERT_PLATOBEBIDA);
                            PS_PLATOBEBIDA.setInt(1, ID);
                            PS_PLATOBEBIDA.setString(2, nombre);
                            PS_PLATOBEBIDA.setString(3, categoria);
                            PS_PLATOBEBIDA.setString(4, descripcion);
                            PS_PLATOBEBIDA.setString(5, "b");
                            int res = PS_PLATOBEBIDA.executeUpdate();
                            if (res > 0){
                                JOptionPane.showMessageDialog(null, "La bebida ha sido registrada con éxito.", "", 1);
                            }
                        }catch(SQLException ex){
                            JOptionPane.showMessageDialog(null, "Error al guardar los datos de la bebida en la base de datos: " +ex.getMessage(), "Error", 0);
                        }
                        finally{
                            PS_PLATOBEBIDA=null;
                            CN.desconectar();
                        }
                        
                    // Limpia los campos del panel
                    panelIngresarBebida.txtNombre.setText("");
                    panelIngresarBebida.comboBoxCategoria.setSelectedIndex(0);
                    panelIngresarBebida.txtDescripcion.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "Nombre repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);   
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos antes de registrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if(e.getSource()==panelIngresarPlato.botonGuardarCambios){          
            if (!camposVaciosPanelRegistrarPlato()) {
                String nombre = panelIngresarPlato.txtNombre.getText();
                String categoria = panelIngresarPlato.comboBoxCategoria.getSelectedItem().toString();
                String descripcion = panelIngresarPlato.txtDescripcion.getText();
                
                try {
                    // Actualiza el plato en la base de datos
                    String SQL = "UPDATE PlatoBebida SET nombre=?, categoria=?, descripcion=?, tipo=? WHERE id=?";
                    PS_PLATOBEBIDA = CN.getConnection().prepareStatement(SQL);
                    PS_PLATOBEBIDA.setString(1, nombre);
                    PS_PLATOBEBIDA.setString(2, categoria);
                    PS_PLATOBEBIDA.setString(3, descripcion);
                    PS_PLATOBEBIDA.setString(4, "p");
                    PS_PLATOBEBIDA.setInt(5, indicadorIDPlato);
                    int res = PS_PLATOBEBIDA.executeUpdate();
                    if (res > 0) {
                        plato.modificarPlato(nombre, categoria, descripcion, listaPlatos, indicadorIDPlato);
                        JOptionPane.showMessageDialog(null, "El plato ha sido modificado con éxito.", "", 1);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar los datos del plato en la base de datos: " + ex.getMessage(), "Error", 0);
                } finally {
                    PS_PLATOBEBIDA = null;
                    CN.desconectar();
                }

                // Limpia los campos del panel
                panelIngresarPlato.txtNombre.setText("");
                panelIngresarPlato.comboBoxCategoria.setSelectedIndex(0);
                panelIngresarPlato.txtDescripcion.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos antes de modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if(e.getSource()==panelIngresarBebida.botonGuardarCambios){          
            if (!camposVaciosPanelRegistrarBebida()) {
                String nombre = panelIngresarBebida.txtNombre.getText();
                String categoria = panelIngresarBebida.comboBoxCategoria.getSelectedItem().toString();
                String descripcion = panelIngresarBebida.txtDescripcion.getText();

                try {
                    // Actualiza la bebida en la base de datos
                    String SQL = "UPDATE PlatoBebida SET nombre=?, categoria=?, descripcion=?, tipo=? WHERE id=?"; 
                    PS_PLATOBEBIDA = CN.getConnection().prepareStatement(SQL);
                    PS_PLATOBEBIDA.setString(1, nombre);
                    PS_PLATOBEBIDA.setString(2, categoria);
                    PS_PLATOBEBIDA.setString(3, descripcion);
                   PS_PLATOBEBIDA.setString(4, "b");
                    PS_PLATOBEBIDA.setInt(5, indicadorIDBebida);
                    int res = PS_PLATOBEBIDA.executeUpdate();
                    if (res > 0) {
                        bebida.modificarBebida(nombre, categoria, descripcion, listaBebidas, indicadorIDBebida);
                        JOptionPane.showMessageDialog(null, "La bebida ha sido modificada con éxito.", "", 1);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar los datos de la bebida en la base de datos: " + ex.getMessage(), "Error", 0);
                } finally {
                    PS_PLATOBEBIDA = null;
                    CN.desconectar();
                }

                // Limpia los campos del panel
                panelIngresarBebida.txtNombre.setText("");
                panelIngresarBebida.comboBoxCategoria.setSelectedIndex(0);
                panelIngresarBebida.txtDescripcion.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese todos los campos antes de modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        if(e.getSource()==panelConsultar.botonEditar){
            if(panelConsultar.comboBoxVista.getSelectedItem().toString().equals("Platos")){
                panelIngresarPlato.txtNombre.setForeground(new Color(230,231,235));
                panelIngresarPlato.txtDescripcion.setForeground(new Color(230,231,235));
                Plato pl = plato.buscarPlato(Integer.parseInt(panelConsultar.labelID.getText()), listaPlatos);
                indicadorIDPlato = Integer.parseInt(panelConsultar.labelID.getText());
                panelIngresarPlato.txtNombre.setText(pl.getNombre());
                panelIngresarPlato.comboBoxCategoria.setSelectedIndex(buscarCategoria(pl.getCategoria()));
                panelIngresarPlato.txtDescripcion.setText(pl.getDescripcion());
                panelIngresarPlato.botonGuardarCambios.setVisible(true);
                panelIngresarPlato.botonRegistrar.setVisible(false);
                panelIngresarPlato.setSize(926,720);
                panelIngresarPlato.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelIngresarPlato);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();             
            }else if(panelConsultar.comboBoxVista.getSelectedItem().toString().equals("Bebidas")){
                panelIngresarBebida.txtNombre.setForeground(new Color(230,231,235));
                panelIngresarBebida.txtDescripcion.setForeground(new Color(230,231,235));
                Bebida b = bebida.buscarBebida(Integer.parseInt(panelConsultar.labelID.getText()), listaBebidas);
                indicadorIDBebida = Integer.parseInt(panelConsultar.labelID.getText());
                panelIngresarBebida.txtNombre.setText(b.getNombre());
                panelIngresarBebida.comboBoxCategoria.setSelectedIndex(buscarCategoria(b.getCategoria()));
                panelIngresarBebida.txtDescripcion.setText(b.getDescripcion());
                panelIngresarBebida.botonGuardarCambios.setVisible(true);
                panelIngresarBebida.botonRegistrar.setVisible(false);
                panelIngresarBebida.setSize(926,720);
                panelIngresarBebida.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelIngresarBebida);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();
            }
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if ( e.getStateChange() == ItemEvent.SELECTED ){
            if(e.getItem().toString().equals("Menús") || 
               e.getItem().toString().equals("Platos") ||
               e.getItem().toString().equals("Bebidas")){
                cargarPaneles(panelConsultar.comboBoxVista.getSelectedItem().toString());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
