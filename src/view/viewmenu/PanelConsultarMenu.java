/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.viewmenu;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import model.modelview.MyComboBoxRender;
import java.awt.GridLayout;

/**
 *
 * @author Estefany Torres
 */
public class PanelConsultarMenu extends javax.swing.JPanel {

    public PanelConsultarMenu() {
        initComponents();
        
        comboBoxVista.setOpaque(false);
        comboBoxVista.setEditable(true);
        JTextField boxField = (JTextField)comboBoxVista .getEditor().getEditorComponent();
        boxField.setBorder(BorderFactory.createEmptyBorder());
        boxField.setForeground(new Color(39,45,47));
        boxField.setBackground(new Color(0, 0, 0, 0));
        boxField.setFocusable(false);
        comboBoxVista.setRenderer(new MyComboBoxRender());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonEliminar = new javax.swing.JButton();
        fondoBotonEliminar = new javax.swing.JLabel();
        botonEditar = new javax.swing.JButton();
        fondoBotonEditar = new javax.swing.JLabel();
        botonIngresar = new javax.swing.JButton();
        fondoBotonIngresar = new javax.swing.JLabel();
        labelTItulo1 = new javax.swing.JLabel();
        comboBoxVista = new javax.swing.JComboBox<>();
        fondoComboMenu = new javax.swing.JLabel();
        labelTItulo = new javax.swing.JLabel();
        fondoTitulo = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        panelMenu = new javax.swing.JPanel(new GridLayout(0, 1, 1, 1));
        fondoPanelMenus = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPaneDescripcion = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        labelBarraBuscar = new javax.swing.JLabel();
        labelTipoPoB = new javax.swing.JLabel();
        labelNombrePoB = new javax.swing.JLabel();
        labelLineaPoB = new javax.swing.JLabel();
        labelImagenPoB = new javax.swing.JLabel();
        labelFondoDatosPoB = new javax.swing.JLabel();
        labelTipoMenu = new javax.swing.JLabel();
        labelNombreMenu = new javax.swing.JLabel();
        labelLineaMenu = new javax.swing.JLabel();
        labelImagenMenu = new javax.swing.JLabel();
        labelFondoDetalleMenu = new javax.swing.JLabel();
        jScrollPaneTiempos = new javax.swing.JScrollPane();
        panelTiempos = new javax.swing.JPanel(new GridLayout(0, 1, 1, 1));
        labelFondoPanelTiempos = new javax.swing.JLabel();
        labelTituloDescripcion1 = new javax.swing.JLabel();
        labelID = new javax.swing.JLabel();

        setBackground(new java.awt.Color(39, 45, 47));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonEliminar.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(230, 231, 235));
        botonEliminar.setBorder(null);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setContentAreaFilled(false);
        botonEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonEliminarMouseExited(evt);
            }
        });
        add(botonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, 70, 60));

        fondoBotonEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondoBotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonEliminar.png"))); // NOI18N
        add(fondoBotonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, 70, 60));

        botonEditar.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonEditar.setForeground(new java.awt.Color(230, 231, 235));
        botonEditar.setBorder(null);
        botonEditar.setBorderPainted(false);
        botonEditar.setContentAreaFilled(false);
        botonEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonEditarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonEditarMouseExited(evt);
            }
        });
        add(botonEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 540, 70, 60));

        fondoBotonEditar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondoBotonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonEditar.png"))); // NOI18N
        add(fondoBotonEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 540, 70, 60));

        botonIngresar.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonIngresar.setForeground(new java.awt.Color(230, 231, 235));
        botonIngresar.setBorder(null);
        botonIngresar.setBorderPainted(false);
        botonIngresar.setContentAreaFilled(false);
        botonIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonIngresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonIngresarMouseExited(evt);
            }
        });
        add(botonIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 620, 70, 60));

        fondoBotonIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonIngresar.png"))); // NOI18N
        add(fondoBotonIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 620, 70, 60));

        labelTItulo1.setBackground(new java.awt.Color(0, 0, 0));
        labelTItulo1.setFont(new java.awt.Font("Open Sans", 1, 28)); // NOI18N
        labelTItulo1.setForeground(new java.awt.Color(230, 231, 235));
        labelTItulo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTItulo1.setText("Menú");
        add(labelTItulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 310, 50));

        comboBoxVista.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxVista.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        comboBoxVista.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxVista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Menús", "Platos", "Bebidas" }));
        comboBoxVista.setBorder(null);
        comboBoxVista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxVista, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 108, 260, 35));

        fondoComboMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/barraFiltro.png"))); // NOI18N
        add(fondoComboMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 310, 50));

        labelTItulo.setBackground(new java.awt.Color(0, 0, 0));
        labelTItulo.setFont(new java.awt.Font("Open Sans Medium", 1, 16)); // NOI18N
        labelTItulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTItulo.setText("MENÚS");
        add(labelTItulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 310, 50));

        fondoTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/fondoTitulo.png"))); // NOI18N
        add(fondoTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        jScrollPane.setBackground(new java.awt.Color(58, 64, 65));
        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelMenu.setBackground(new java.awt.Color(58, 64, 65));
        jScrollPane.setViewportView(panelMenu);

        add(jScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 310, 410));

        fondoPanelMenus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/fondoPanelMenus.png"))); // NOI18N
        add(fondoPanelMenus, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 310, 430));

        txtBuscar.setBackground(new java.awt.Color(0,0,0,1));
        txtBuscar.setFont(new java.awt.Font("Open Sans Medium", 0, 16)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(230, 231, 235));
        txtBuscar.setBorder(null);
        add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 156, 240, 42));

        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(new java.awt.Color(67, 73, 75));
        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(230, 231, 235));
        txtDescripcion.setRows(5);
        jScrollPaneDescripcion.setViewportView(txtDescripcion);

        add(jScrollPaneDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 330, 110));

        labelBarraBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/barraBuscar.png"))); // NOI18N
        add(labelBarraBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 152, 310, 50));

        labelTipoPoB.setFont(new java.awt.Font("Open Sans", 2, 14)); // NOI18N
        labelTipoPoB.setForeground(new java.awt.Color(255, 197, 41));
        labelTipoPoB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTipoPoB.setText("Menu");
        add(labelTipoPoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 220, -1));

        labelNombrePoB.setFont(new java.awt.Font("Open Sans SemiBold", 1, 16)); // NOI18N
        labelNombrePoB.setForeground(new java.awt.Color(230, 231, 235));
        labelNombrePoB.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNombrePoB.setText("Nombre del Menu");
        add(labelNombrePoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 240, 40));

        labelLineaPoB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/lineaAmarilla.png"))); // NOI18N
        add(labelLineaPoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, -1, -1));

        labelImagenPoB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/imagenPlato.png"))); // NOI18N
        add(labelImagenPoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, -1, -1));

        labelFondoDatosPoB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/fondoDetallePoB.png"))); // NOI18N
        add(labelFondoDatosPoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, 300));

        labelTipoMenu.setFont(new java.awt.Font("Open Sans", 2, 14)); // NOI18N
        labelTipoMenu.setForeground(new java.awt.Color(255, 197, 41));
        labelTipoMenu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTipoMenu.setText("Menu");
        add(labelTipoMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 220, -1));

        labelNombreMenu.setFont(new java.awt.Font("Open Sans SemiBold", 1, 16)); // NOI18N
        labelNombreMenu.setForeground(new java.awt.Color(230, 231, 235));
        labelNombreMenu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNombreMenu.setText("Nombre del Menu");
        add(labelNombreMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 240, 40));

        labelLineaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/lineaAmarilla.png"))); // NOI18N
        add(labelLineaMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, -1, -1));

        labelImagenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/imagenMenu.png"))); // NOI18N
        add(labelImagenMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        labelFondoDetalleMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/fondoDetalleMenu.png"))); // NOI18N
        add(labelFondoDetalleMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, 150));

        jScrollPaneTiempos.setBorder(null);
        jScrollPaneTiempos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneTiempos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPaneTiempos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelTiempos.setBackground(new java.awt.Color(58, 64, 65));
        jScrollPaneTiempos.setViewportView(panelTiempos);

        add(jScrollPaneTiempos, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 410, 470));

        labelFondoPanelTiempos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/fondoPanelTiempos.png"))); // NOI18N
        add(labelFondoPanelTiempos, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, -1, -1));

        labelTituloDescripcion1.setFont(new java.awt.Font("Open Sans Medium", 0, 15)); // NOI18N
        labelTituloDescripcion1.setForeground(new java.awt.Color(255, 197, 41));
        labelTituloDescripcion1.setText("Descripción:");
        add(labelTituloDescripcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 240, -1));

        labelID.setFont(new java.awt.Font("Open Sans Medium", 0, 15)); // NOI18N
        labelID.setForeground(new java.awt.Color(255, 197, 41));
        add(labelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 100, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void botonIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIngresarMouseEntered
        // TODO add your handling code here:
        fondoBotonIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonIngresarPresionado.png")));
    }//GEN-LAST:event_botonIngresarMouseEntered

    private void botonIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIngresarMouseExited
        // TODO add your handling code here:
        fondoBotonIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonIngresar.png")));
    }//GEN-LAST:event_botonIngresarMouseExited

    private void botonEditarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEditarMouseEntered
        // TODO add your handling code here:
        fondoBotonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonEditarPresionado.png")));
    }//GEN-LAST:event_botonEditarMouseEntered

    private void botonEditarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEditarMouseExited
        // TODO add your handling code here:
        fondoBotonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonEditar.png")));
    }//GEN-LAST:event_botonEditarMouseExited

    private void botonEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarMouseEntered
        // TODO add your handling code here:
        fondoBotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonEliminarPresionado.png")));
    }//GEN-LAST:event_botonEliminarMouseEntered

    private void botonEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarMouseExited
        // TODO add your handling code here:
        fondoBotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonEliminar.png")));
    }//GEN-LAST:event_botonEliminarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonEditar;
    public javax.swing.JButton botonEliminar;
    public javax.swing.JButton botonIngresar;
    public javax.swing.JComboBox<String> comboBoxVista;
    public javax.swing.JLabel fondoBotonEditar;
    public javax.swing.JLabel fondoBotonEliminar;
    public javax.swing.JLabel fondoBotonIngresar;
    private javax.swing.JLabel fondoComboMenu;
    private javax.swing.JLabel fondoPanelMenus;
    private javax.swing.JLabel fondoTitulo;
    public javax.swing.JScrollPane jScrollPane;
    public javax.swing.JScrollPane jScrollPaneDescripcion;
    public javax.swing.JScrollPane jScrollPaneTiempos;
    private javax.swing.JLabel labelBarraBuscar;
    public javax.swing.JLabel labelFondoDatosPoB;
    public javax.swing.JLabel labelFondoDetalleMenu;
    public javax.swing.JLabel labelFondoPanelTiempos;
    public javax.swing.JLabel labelID;
    public javax.swing.JLabel labelImagenMenu;
    public javax.swing.JLabel labelImagenPoB;
    public javax.swing.JLabel labelLineaMenu;
    public javax.swing.JLabel labelLineaPoB;
    public javax.swing.JLabel labelNombreMenu;
    public javax.swing.JLabel labelNombrePoB;
    public javax.swing.JLabel labelTItulo;
    public javax.swing.JLabel labelTItulo1;
    public javax.swing.JLabel labelTipoMenu;
    public javax.swing.JLabel labelTipoPoB;
    public javax.swing.JLabel labelTituloDescripcion1;
    public javax.swing.JPanel panelMenu;
    public javax.swing.JPanel panelTiempos;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextArea txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
