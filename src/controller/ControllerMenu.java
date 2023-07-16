/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.conexionBD.ConexionServidor;
import model.modelmenu.Bebida;
import model.modelmenu.Plato;
import view.viewmenu.PanelConsultarMenu;
import view.viewmenu.PanelIngresarBebida;
import view.viewmenu.PanelIngresarPlato;
import view.viewprincipal.SistemaPrincipal;

/**
 *
 * @author emmez
 */
public class ControllerMenu implements ActionListener{
    
    private Plato plato = new Plato();
    private Bebida bebida = new Bebida();
    
    private static final ArrayList<Plato> listaPlatos = new ArrayList<>();
    private static final ArrayList<Bebida> listaBebidas = new ArrayList<>();
    
    private PreparedStatement PS;                                                                                                                   
    private final ConexionServidor CN;
    
    private final String SQL_INSERT_PLATOBEBIDA = "INSERT INTO PlatoBebida (nombre,categoria,descripcion,tipo) values (?,?,?,?)";
    private PreparedStatement PS_PLATOBEBIDA;
    
    private final String SQL_SELECT_PLATOBEBIDA = "SELECT * FROM PlatoBebida";
    private DefaultTableModel DT_PLATOBEBIDA;
    private ResultSet RS_PLATOBEBIDA;
    
    private final SistemaPrincipal panelSistema;
    private final PanelConsultarMenu panelConsultar = new PanelConsultarMenu();
    private final PanelIngresarPlato panelIngresarPlato = new PanelIngresarPlato();
    private final PanelIngresarBebida panelIngresarBebida = new PanelIngresarBebida();
    
    public ControllerMenu(SistemaPrincipal panelSistema) {
        //Ventana del sistema
        this.panelSistema = panelSistema;
        this.panelSistema.botonMenu.addActionListener(this);
        
        //Panel Consultar Menú
        panelConsultar.botonIngresar.addActionListener(this); 
        
        //Panel Ingresar Plato
        panelIngresarPlato.botonRegistrar.addActionListener(this);
        panelIngresarPlato.botonRegresar.addActionListener(this);

        //Panel Ingresar Bebida
        panelIngresarBebida.botonRegistrar.addActionListener(this);
        panelIngresarBebida.botonRegresar.addActionListener(this);
        
        PS = null;
        CN= new ConexionServidor();
        
        setDatosPlatoBebida();
        
    }
    
    private DefaultTableModel setColumnasPlatoBebida(){
        DT_PLATOBEBIDA = new DefaultTableModel();
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
                String nombre = RS_PLATOBEBIDA.getString(1);
                String categoria = RS_PLATOBEBIDA.getString(2);
                String descripcion = RS_PLATOBEBIDA.getString(3);
                String tipo = RS_PLATOBEBIDA.getString(4);
                
                if (tipo.equals("p")){
                    Plato p = new Plato(nombre,categoria,descripcion);
                    listaPlatos.add(p);
                }else if(tipo.equals("b")){
                    Bebida b = new Bebida(nombre,categoria,descripcion);
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BOTONES DEL PANEL: panelSistema
        
        //Boton icono de menu
        if(e.getSource() == panelSistema.botonMenu){
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
        if(e.getSource()==panelConsultar.botonIngresar){
            if (panelConsultar.comboBoxVista.getSelectedItem().equals("Platos")){
                panelIngresarPlato.setSize(926,720);
                panelIngresarPlato.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelIngresarPlato);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();  
            }
            if (panelConsultar.comboBoxVista.getSelectedItem().equals("Bebidas")){
                panelIngresarBebida.setSize(926,720);
                panelIngresarBebida.setLocation(0,0);
                panelSistema.panelPrincipal.removeAll();
                panelSistema.panelPrincipal.add(panelIngresarBebida);
                panelSistema.panelPrincipal.revalidate();
                panelSistema.panelPrincipal.repaint();  
            }
              
        }
        
        if (e.getSource() == panelIngresarPlato.botonRegistrar) {
            if (!camposVaciosPanelRegistrarPlato()) {
                String nombre = panelIngresarPlato.txtNombre.getText();
                String categoria = panelIngresarPlato.comboBoxCategoria.getSelectedItem().toString();
                String descripcion = panelIngresarPlato.txtDescripcion.getText();
                if (!(plato.nombrePlatoRepetido(listaPlatos, nombre))) {
                    Plato pl = new Plato();
                    pl.setNombre(nombre);
                    pl.setCategoria(categoria);
                    pl.setDescripcion(descripcion);
                    listaPlatos.add(pl);
                    try{
                            PS_PLATOBEBIDA=CN.getConnection().prepareStatement(SQL_INSERT_PLATOBEBIDA);
                            PS_PLATOBEBIDA.setString(1, nombre);
                            PS_PLATOBEBIDA.setString(2, categoria);
                            PS_PLATOBEBIDA.setString(3, descripcion);
                            PS_PLATOBEBIDA.setString(4, "p");
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
                    b.setNombre(nombre);
                    b.setCategoria(categoria);
                    b.setDescripcion(descripcion);
                    listaBebidas.add(b);
                    try{
                            PS_PLATOBEBIDA=CN.getConnection().prepareStatement(SQL_INSERT_PLATOBEBIDA);
                            PS_PLATOBEBIDA.setString(1, nombre);
                            PS_PLATOBEBIDA.setString(2, categoria);
                            PS_PLATOBEBIDA.setString(3, descripcion);
                            PS_PLATOBEBIDA.setString(4, "b");
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
        
        
        
    }
    
}
