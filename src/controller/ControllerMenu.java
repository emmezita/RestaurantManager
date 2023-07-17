/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.conexionBD.ConexionServidor;
import model.modelmenu.Bebida;
import model.modelmenu.Plato;
import view.viewmenu.PanelBebida;
import view.viewmenu.PanelConsultarMenu;
import view.viewmenu.PanelIngresarBebida;
import view.viewmenu.PanelIngresarPlato;
import view.viewmenu.PanelPlato;
import view.viewprincipal.SistemaPrincipal;

/**
 *
 * @author emmez
 */
public class ControllerMenu implements ActionListener{
    
    private Plato plato = new Plato();
    private Bebida bebida = new Bebida();
    
    private String indicadorNombrePlato = "";
    private String indicadorNombreBebida = "";       
    
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
        panelConsultar.botonEditar.addActionListener(this);
        
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
    
    
    public void cargarPoB(String tipo){
        String opcionSeleccionada = tipo;
        
        panelConsultar.labelFondoDatosPoB.setVisible(false);
        panelConsultar.labelImagenPoB.setVisible(false);
        panelConsultar.labelLineaPoB.setVisible(false);
        panelConsultar.labelTipoPoB.setVisible(false);
        panelConsultar.labelNombrePoB.setVisible(false);
        panelConsultar.labelTituloDescripcion.setVisible(false);
        panelConsultar.txtDescripcion.setVisible(false);
        
        
        
        if (opcionSeleccionada.equals("Platos")){
            for (Plato pl : listaPlatos) {
                PanelPlato panelP = new PanelPlato();
                panelP.setSize(310,70);
                panelP.labelNombrePlato.setText(pl.getNombre());
                
                panelP.botonPlato.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Actualizar el texto del JLabel
                        //indicadorCedula = em.getCedula();
                        panelConsultar.labelFondoDatosPoB.setVisible(true);
                        panelConsultar.labelImagenPoB.setVisible(true);
                        panelConsultar.labelLineaPoB.setVisible(true);
                        panelConsultar.labelTipoPoB.setVisible(true);
                        panelConsultar.labelNombrePoB.setVisible(true);
                        panelConsultar.labelTituloDescripcion.setVisible(true);
                        panelConsultar.txtDescripcion.setVisible(true);
                        panelConsultar.labelNombrePoB.setText(pl.getNombre());
                        panelConsultar.labelTipoPoB.setText("Plato");
                        panelConsultar.txtDescripcion.setText(pl.getDescripcion());
                    }
                });
                panelConsultar.jScrollPane.add(panelP);
            }
        }else if (opcionSeleccionada.equals("Bebidas")){
            for (Bebida be : listaBebidas) {
                PanelBebida panelB = new PanelBebida();
                panelB.setSize(310,70);
                panelB.labelNombreBebida.setText(be.getNombre());
                
                panelB.botonBebida.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Actualizar el texto del JLabel
                        //indicadorCedula = em.getCedula();
                        panelConsultar.labelFondoDatosPoB.setVisible(true);
                        panelConsultar.labelImagenPoB.setVisible(true);
                        panelConsultar.labelLineaPoB.setVisible(true);
                        panelConsultar.labelTipoPoB.setVisible(true);
                        panelConsultar.labelNombrePoB.setVisible(true);
                        panelConsultar.labelTituloDescripcion.setVisible(true);
                        panelConsultar.txtDescripcion.setVisible(true);
                        panelConsultar.labelNombrePoB.setText(be.getNombre());
                        panelConsultar.labelTipoPoB.setText("Bebida");
                        panelConsultar.txtDescripcion.setText(be.getDescripcion());
                    }
                });
                panelConsultar.jScrollPane.add(panelB);
            }
        }
        panelConsultar.jScrollPane.revalidate();
        panelConsultar.jScrollPane.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //BOTONES DEL PANEL: panelSistema
        
        //Boton icono de menu
        if(e.getSource() == panelSistema.botonMenu){
            cargarPoB(panelConsultar.comboBoxVista.getSelectedItem().toString());
            panelConsultar.setSize(926,720);
            panelConsultar.setLocation(0,0);
            panelSistema.panelPrincipal.removeAll();
            panelSistema.panelPrincipal.add(panelConsultar);
            panelSistema.panelPrincipal.revalidate();
            panelSistema.panelPrincipal.repaint();
        }
        
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
        
        if(e.getSource()==panelIngresarPlato.botonGuardarCambios){          
            if (!camposVaciosPanelRegistrarPlato()) {
                String nombre = panelIngresarPlato.txtNombre.getText();
                String categoria = panelIngresarPlato.comboBoxCategoria.getSelectedItem().toString();
                String descripcion = panelIngresarPlato.txtDescripcion.getText();
                
                try {
                    // Actualiza el plato en la base de datos
                    String SQL = "UPDATE PlatoBebida SET nombre=?, categoria=?, descripcion=?, tipo=? WHERE nombre=?";
                    PS_PLATOBEBIDA = CN.getConnection().prepareStatement(SQL);
                    PS_PLATOBEBIDA.setString(1, nombre);
                    PS_PLATOBEBIDA.setString(2, categoria);
                    PS_PLATOBEBIDA.setString(3, descripcion);
                    PS_PLATOBEBIDA.setString(4, "p");
                    PS_PLATOBEBIDA.setString(5, indicadorNombrePlato);
                    int res = PS_PLATOBEBIDA.executeUpdate();
                    if (res > 0) {
                        plato.modificarPlato(nombre, categoria, descripcion, listaPlatos, indicadorNombrePlato);
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
                    String SQL = "UPDATE PlatoBebida SET nombre=?, categoria=?, descripcion=?, tipo=? WHERE nombre=?";
                    PS_PLATOBEBIDA = CN.getConnection().prepareStatement(SQL);
                    PS_PLATOBEBIDA.setString(1, nombre);
                    PS_PLATOBEBIDA.setString(2, categoria);
                    PS_PLATOBEBIDA.setString(3, descripcion);
                   PS_PLATOBEBIDA.setString(4, "b");
                    PS_PLATOBEBIDA.setString(5, indicadorNombreBebida);
                    int res = PS_PLATOBEBIDA.executeUpdate();
                    if (res > 0) {
                        bebida.modificarBebida(nombre, categoria, descripcion, listaBebidas, indicadorNombreBebida);
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
                Plato pl = plato.buscarPlato(panelConsultar.labelNombrePoB.getText(), listaPlatos);
                indicadorNombrePlato = panelConsultar.labelNombrePoB.getText();
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
                Bebida b = bebida.buscarBebida(panelConsultar.labelNombrePoB.getText(), listaBebidas);
                indicadorNombreBebida = panelConsultar.labelNombrePoB.getText();
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
    
}
