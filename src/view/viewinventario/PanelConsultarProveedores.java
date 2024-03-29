/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.viewinventario;

import java.awt.GridLayout;

/**
 *
 * @author emmez
 */
public class PanelConsultarProveedores extends javax.swing.JPanel {

    /**
     * Creates new form PanelConsultarProveedores
     */
    public PanelConsultarProveedores() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonRegresarI = new javax.swing.JButton();
        fondoBotonRegresar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        labelBarraBuscar = new javax.swing.JLabel();
        labelTItulo = new javax.swing.JLabel();
        fondoTitulo = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        panelProveedores = new javax.swing.JPanel(new GridLayout(0, 1, 1, 1));
        fondoPanelProveedores = new javax.swing.JLabel();
        labelCirculo = new javax.swing.JLabel();
        labelImagen = new javax.swing.JLabel();
        labelLinea = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelCedula = new javax.swing.JLabel();
        labelRol = new javax.swing.JLabel();
        iconoMail = new javax.swing.JLabel();
        labelMail = new javax.swing.JLabel();
        iconoMunicipio = new javax.swing.JLabel();
        labelMunicipio = new javax.swing.JLabel();
        iconoTelefono = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        botonIngresar = new javax.swing.JButton();
        fondoBotonIngresar = new javax.swing.JLabel();
        fondoDetalleProveedor = new javax.swing.JLabel();
        labelTItulo1 = new javax.swing.JLabel();
        fondoTitulo1 = new javax.swing.JLabel();
        jScrollPaneInsumos = new javax.swing.JScrollPane();
        panelInsumos = new javax.swing.JPanel(new GridLayout(0, 1, 1, 1));
        fondoPanelInsumos = new javax.swing.JLabel();

        setBackground(new java.awt.Color(39, 45, 47));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonRegresarI.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonRegresarI.setForeground(new java.awt.Color(230, 231, 235));
        botonRegresarI.setBorder(null);
        botonRegresarI.setBorderPainted(false);
        botonRegresarI.setContentAreaFilled(false);
        botonRegresarI.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegresarI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRegresarI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonRegresarIMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonRegresarIMouseExited(evt);
            }
        });
        add(botonRegresarI, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 50, 50));

        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonRegresar.png"))); // NOI18N
        add(fondoBotonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 50));

        txtBuscar.setBackground(new java.awt.Color(0,0,0,1));
        txtBuscar.setFont(new java.awt.Font("Open Sans Medium", 0, 16)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(230, 231, 235));
        txtBuscar.setBorder(null);
        add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 44, 240, 42));

        labelBarraBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/barraBuscarProveedor.png"))); // NOI18N
        add(labelBarraBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 310, 50));

        labelTItulo.setBackground(new java.awt.Color(0, 0, 0));
        labelTItulo.setFont(new java.awt.Font("Open Sans Medium", 1, 16)); // NOI18N
        labelTItulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTItulo.setText("PROVEEDORES");
        add(labelTItulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 310, 50));

        fondoTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/fondoTItuloProveedor.png"))); // NOI18N
        add(fondoTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelProveedores.setBackground(new java.awt.Color(58, 64, 65));
        jScrollPane.setViewportView(panelProveedores);

        add(jScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 170, 308, 490));

        fondoPanelProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/fondoPanelProveedores.png"))); // NOI18N
        add(fondoPanelProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 310, 510));

        labelCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/circuloRojo.png"))); // NOI18N
        add(labelCirculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 130, 130));

        labelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/imagenProveedor.png"))); // NOI18N
        add(labelImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(471, 62, -1, -1));

        labelLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/lineaRoja.png"))); // NOI18N
        add(labelLinea, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 120, -1, 20));

        labelNombre.setFont(new java.awt.Font("Open Sans SemiBold", 0, 21)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(230, 231, 235));
        labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNombre.setText("Nombre Proveedor");
        add(labelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 70, 240, -1));

        labelCedula.setFont(new java.awt.Font("Open Sans SemiBold", 0, 16)); // NOI18N
        labelCedula.setForeground(new java.awt.Color(230, 231, 235));
        labelCedula.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCedula.setText("RIF: 12456789");
        add(labelCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 240, -1));

        labelRol.setBackground(new java.awt.Color(254, 114, 76));
        labelRol.setFont(new java.awt.Font("Open Sans", 2, 14)); // NOI18N
        labelRol.setForeground(new java.awt.Color(254, 114, 76));
        labelRol.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelRol.setText("Proveedor");
        add(labelRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 140, 220, -1));

        iconoMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/iconMail.png"))); // NOI18N
        add(iconoMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, -1, -1));

        labelMail.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        labelMail.setForeground(new java.awt.Color(230, 231, 235));
        labelMail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMail.setText("Correo");
        add(labelMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 240, 30));

        iconoMunicipio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/iconMunicipio.png"))); // NOI18N
        add(iconoMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, -1, 30));

        labelMunicipio.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        labelMunicipio.setForeground(new java.awt.Color(230, 231, 235));
        labelMunicipio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMunicipio.setText("Municipio");
        add(labelMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 240, 30));

        iconoTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/iconTelefono.png"))); // NOI18N
        add(iconoTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, -1, 30));

        labelTelefono.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        labelTelefono.setForeground(new java.awt.Color(230, 231, 235));
        labelTelefono.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTelefono.setText("Número");
        add(labelTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 240, 30));

        botonIngresar.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonIngresar.setForeground(new java.awt.Color(39, 45, 47));
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
        add(botonIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 600, 60, 60));

        fondoBotonIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonIngresarProveedor.png"))); // NOI18N
        add(fondoBotonIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 600, -1, -1));

        fondoDetalleProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/fondoDetalleProveedor.png"))); // NOI18N
        add(fondoDetalleProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, -1));

        labelTItulo1.setBackground(new java.awt.Color(0, 0, 0));
        labelTItulo1.setFont(new java.awt.Font("Open Sans Medium", 1, 16)); // NOI18N
        labelTItulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTItulo1.setText("INSUMOS");
        add(labelTItulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, 340, 50));

        fondoTitulo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/fondoTituloInsumos.png"))); // NOI18N
        add(fondoTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, -1, -1));

        jScrollPaneInsumos.setBorder(null);
        jScrollPaneInsumos.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneInsumos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPaneInsumos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        panelInsumos.setBackground(new java.awt.Color(58, 64, 65));
        jScrollPaneInsumos.setViewportView(panelInsumos);

        add(jScrollPaneInsumos, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, 340, 220));

        fondoPanelInsumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/fondoPanelInsumosP.png"))); // NOI18N
        add(fondoPanelInsumos, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, -1, 280));
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarIMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegresarIMouseEntered
        // TODO add your handling code here:
        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonRegresarPresionado.png"))); // NOI18N
    }//GEN-LAST:event_botonRegresarIMouseEntered

    private void botonRegresarIMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegresarIMouseExited
        // TODO add your handling code here:
        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonRegresar.png"))); // NOI18N
    }//GEN-LAST:event_botonRegresarIMouseExited

    private void botonIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIngresarMouseEntered
        // TODO add your handling code here:
        fondoBotonIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonIngresarProveedorPresionado.png")));
    }//GEN-LAST:event_botonIngresarMouseEntered

    private void botonIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIngresarMouseExited
        // TODO add your handling code here:
        fondoBotonIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonIngresarProveedor.png")));
    }//GEN-LAST:event_botonIngresarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonIngresar;
    public javax.swing.JButton botonRegresarI;
    private javax.swing.JLabel fondoBotonIngresar;
    private javax.swing.JLabel fondoBotonRegresar;
    private javax.swing.JLabel fondoDetalleProveedor;
    private javax.swing.JLabel fondoPanelInsumos;
    private javax.swing.JLabel fondoPanelProveedores;
    private javax.swing.JLabel fondoTitulo;
    private javax.swing.JLabel fondoTitulo1;
    public javax.swing.JLabel iconoMail;
    public javax.swing.JLabel iconoMunicipio;
    public javax.swing.JLabel iconoTelefono;
    public javax.swing.JScrollPane jScrollPane;
    public javax.swing.JScrollPane jScrollPaneInsumos;
    private javax.swing.JLabel labelBarraBuscar;
    public javax.swing.JLabel labelCedula;
    public javax.swing.JLabel labelCirculo;
    private javax.swing.JLabel labelImagen;
    public javax.swing.JLabel labelLinea;
    public javax.swing.JLabel labelMail;
    public javax.swing.JLabel labelMunicipio;
    public javax.swing.JLabel labelNombre;
    public javax.swing.JLabel labelRol;
    public javax.swing.JLabel labelTItulo;
    public javax.swing.JLabel labelTItulo1;
    public javax.swing.JLabel labelTelefono;
    public javax.swing.JPanel panelInsumos;
    public javax.swing.JPanel panelProveedores;
    public javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
