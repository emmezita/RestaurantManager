/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.viewmenu;

/**
 *
 * @author emmez
 */
public class PanelIngresarMenuS6 extends javax.swing.JPanel {

    /**
     * Creates new form PanelIngresarMenuS6
     */
    public PanelIngresarMenuS6() {
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

        labelNombre = new javax.swing.JLabel();
        botonRegresar = new javax.swing.JButton();
        fondoBotonRegresar = new javax.swing.JLabel();
        botonRegistrar = new javax.swing.JButton();
        fondoBotonRegistrar = new javax.swing.JLabel();
        comboBoxP6 = new javax.swing.JComboBox<>();
        labelInputP6 = new javax.swing.JLabel();
        comboBoxP5 = new javax.swing.JComboBox<>();
        labelInputP5 = new javax.swing.JLabel();
        comboBoxP4 = new javax.swing.JComboBox<>();
        labelInputP4 = new javax.swing.JLabel();
        comboBoxP3 = new javax.swing.JComboBox<>();
        labelInputP3 = new javax.swing.JLabel();
        comboBoxP2 = new javax.swing.JComboBox<>();
        labelInputP2 = new javax.swing.JLabel();
        comboBoxP1 = new javax.swing.JComboBox<>();
        labelInputP1 = new javax.swing.JLabel();
        labelTitulo = new javax.swing.JLabel();
        labelImagen = new javax.swing.JLabel();
        labelFondoTItulo = new javax.swing.JLabel();
        labelFondo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(39, 45, 47));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelNombre.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(230, 231, 235));
        labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNombre.setText("Postres");
        add(labelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 280, -1));

        botonRegresar.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonRegresar.setForeground(new java.awt.Color(230, 231, 235));
        botonRegresar.setBorder(null);
        botonRegresar.setBorderPainted(false);
        botonRegresar.setContentAreaFilled(false);
        botonRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonRegresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonRegresarMouseExited(evt);
            }
        });
        add(botonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 50, 50));

        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonIzquierda.png"))); // NOI18N
        add(fondoBotonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, -1, 50));

        botonRegistrar.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonRegistrar.setForeground(new java.awt.Color(39, 45, 47));
        botonRegistrar.setText("Registrar");
        botonRegistrar.setBorder(null);
        botonRegistrar.setBorderPainted(false);
        botonRegistrar.setContentAreaFilled(false);
        botonRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonRegistrarMouseExited(evt);
            }
        });
        add(botonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, 120, 50));

        fondoBotonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/botonRegistrarMenu.png"))); // NOI18N
        add(fondoBotonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, -1, 50));

        comboBoxP6.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxP6.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        comboBoxP6.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxP6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plato o Bebida" }));
        comboBoxP6.setBorder(null);
        comboBoxP6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxP6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 260, 40));

        labelInputP6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/inputMenu.png"))); // NOI18N
        add(labelInputP6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, -1));

        comboBoxP5.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxP5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        comboBoxP5.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxP5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plato o Bebida" }));
        comboBoxP5.setBorder(null);
        comboBoxP5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxP5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 260, 40));

        labelInputP5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/inputMenu.png"))); // NOI18N
        add(labelInputP5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, -1));

        comboBoxP4.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxP4.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        comboBoxP4.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxP4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plato o Bebida" }));
        comboBoxP4.setBorder(null);
        comboBoxP4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxP4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 260, 40));

        labelInputP4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/inputMenu.png"))); // NOI18N
        add(labelInputP4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        comboBoxP3.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxP3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        comboBoxP3.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxP3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plato o Bebida" }));
        comboBoxP3.setBorder(null);
        comboBoxP3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxP3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 260, 40));

        labelInputP3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/inputMenu.png"))); // NOI18N
        add(labelInputP3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        comboBoxP2.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxP2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        comboBoxP2.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxP2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plato o Bebida" }));
        comboBoxP2.setBorder(null);
        comboBoxP2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 260, 40));

        labelInputP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/inputMenu.png"))); // NOI18N
        add(labelInputP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        comboBoxP1.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxP1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        comboBoxP1.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxP1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plato o Bebida" }));
        comboBoxP1.setBorder(null);
        comboBoxP1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 260, 40));

        labelInputP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/inputMenu.png"))); // NOI18N
        add(labelInputP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        labelTitulo.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelTitulo.setText("DATOS DEL MENÚ");
        add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 16, 260, 40));

        labelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/imagenIngresarMenu.png"))); // NOI18N
        add(labelImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 20, -1, -1));

        labelFondoTItulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/fondoTituloDatosMenu.png"))); // NOI18N
        add(labelFondoTItulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        labelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewmenu/fondoIngresarMenu.png"))); // NOI18N
        add(labelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 385, 615));
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegresarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRegresarMouseEntered

    private void botonRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegresarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRegresarMouseExited

    private void botonRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRegistrarMouseEntered

    private void botonRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRegistrarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonRegistrar;
    public javax.swing.JButton botonRegresar;
    public javax.swing.JComboBox<String> comboBoxP1;
    public javax.swing.JComboBox<String> comboBoxP2;
    public javax.swing.JComboBox<String> comboBoxP3;
    public javax.swing.JComboBox<String> comboBoxP4;
    public javax.swing.JComboBox<String> comboBoxP5;
    public javax.swing.JComboBox<String> comboBoxP6;
    public javax.swing.JLabel fondoBotonRegistrar;
    public javax.swing.JLabel fondoBotonRegresar;
    private javax.swing.JLabel labelFondo;
    private javax.swing.JLabel labelFondoTItulo;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel labelInputP1;
    private javax.swing.JLabel labelInputP2;
    private javax.swing.JLabel labelInputP3;
    private javax.swing.JLabel labelInputP4;
    private javax.swing.JLabel labelInputP5;
    private javax.swing.JLabel labelInputP6;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
