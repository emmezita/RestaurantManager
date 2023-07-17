/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.viewinventario;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import model.modelview.MyComboBoxRender;

/**
 *
 * @author emmez
 */
public class FrameVerInsumo extends javax.swing.JFrame {

    /**
     * Creates new form FrameVerInsumo
     */
    public FrameVerInsumo() {
        initComponents();
        comboBoxTipoInsumo.setOpaque(false);
        comboBoxTipoInsumo.setEditable(true);
        JTextField boxField = (JTextField)comboBoxTipoInsumo .getEditor().getEditorComponent();
        boxField.setBorder(BorderFactory.createEmptyBorder());
        boxField.setForeground(new Color(230,231,235));
        boxField.setBackground(new Color(0, 0, 0, 0));
        boxField.setFocusable(false);
        comboBoxTipoInsumo.setRenderer(new MyComboBoxRender());
        
        comboBoxUnidad.setOpaque(false);
        comboBoxUnidad.setEditable(true);
        boxField = (JTextField)comboBoxUnidad .getEditor().getEditorComponent();
        boxField.setBorder(BorderFactory.createEmptyBorder());
        boxField.setForeground(new Color(230,231,235));
        boxField.setBackground(new Color(0, 0, 0, 0));
        boxField.setFocusable(false);
        comboBoxUnidad.setRenderer(new MyComboBoxRender());
        
        comboBoxProveedor.setOpaque(false);
        comboBoxProveedor.setEditable(true);
        boxField = (JTextField)comboBoxProveedor.getEditor().getEditorComponent();
        boxField.setBorder(BorderFactory.createEmptyBorder());
        boxField.setForeground(new Color(230,231,235));
        boxField.setBackground(new Color(0, 0, 0, 0));
        boxField.setFocusable(false);
        comboBoxProveedor.setRenderer(new MyComboBoxRender());
        
        setIconImage(getIconImage());
    }
    
    @Override
    public Image getIconImage () {  
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/imagesviewprincipal/logo.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboBoxUnidad = new javax.swing.JComboBox<>();
        inputUnidadInsumo = new javax.swing.JLabel();
        comboBoxTipoInsumo = new javax.swing.JComboBox<>();
        inputTipoInsumo = new javax.swing.JLabel();
        txtNombreInsumo = new javax.swing.JTextField();
        inputNombre = new javax.swing.JLabel();
        labelTItulo = new javax.swing.JLabel();
        labelFondoITitulo = new javax.swing.JLabel();
        comboBoxProveedor = new javax.swing.JComboBox<>();
        inputProveedor = new javax.swing.JLabel();
        botonEliminar = new javax.swing.JButton();
        fondoBotonEliminar = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        fondoBotonGuardar = new javax.swing.JLabel();
        labelCantidadExistente = new javax.swing.JLabel();
        labelCantidad = new javax.swing.JLabel();
        fondo1 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(39, 45, 47));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboBoxUnidad.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxUnidad.setFont(new java.awt.Font("Open Sans Medium", 0, 14)); // NOI18N
        comboBoxUnidad.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxUnidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona unidad", "Kilogramo", "Botella", "Litro" }));
        comboBoxUnidad.setBorder(null);
        comboBoxUnidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(comboBoxUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 260, 40));

        inputUnidadInsumo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inputUnidadInsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/inputDatosInsumo.png"))); // NOI18N
        getContentPane().add(inputUnidadInsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 280, 40));

        comboBoxTipoInsumo.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxTipoInsumo.setFont(new java.awt.Font("Open Sans Medium", 0, 14)); // NOI18N
        comboBoxTipoInsumo.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxTipoInsumo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona tipo de insumo", "Carnes", "Frutas", "Vegetales", "Bebidas", "Otros" }));
        comboBoxTipoInsumo.setBorder(null);
        comboBoxTipoInsumo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(comboBoxTipoInsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 260, 40));

        inputTipoInsumo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inputTipoInsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/inputDatosInsumo.png"))); // NOI18N
        getContentPane().add(inputTipoInsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 280, 40));

        txtNombreInsumo.setBackground(new java.awt.Color(0,0,0,1));
        txtNombreInsumo.setFont(new java.awt.Font("Open Sans Medium", 0, 14)); // NOI18N
        txtNombreInsumo.setForeground(new java.awt.Color(230, 231, 235));
        txtNombreInsumo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNombreInsumo.setText("Nombre");
        txtNombreInsumo.setBorder(null);
        getContentPane().add(txtNombreInsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 260, 40));

        inputNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inputNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/inputDatosInsumo.png"))); // NOI18N
        getContentPane().add(inputNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 280, 40));

        labelTItulo.setBackground(new java.awt.Color(0, 0, 0));
        labelTItulo.setFont(new java.awt.Font("Open Sans Medium", 1, 16)); // NOI18N
        labelTItulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTItulo.setText("DATOS DEL INSUMO");
        getContentPane().add(labelTItulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 50));

        labelFondoITitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/fondoTituloDatos.png"))); // NOI18N
        getContentPane().add(labelFondoITitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        comboBoxProveedor.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxProveedor.setFont(new java.awt.Font("Open Sans Medium", 0, 14)); // NOI18N
        comboBoxProveedor.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Proveedor" }));
        comboBoxProveedor.setBorder(null);
        comboBoxProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(comboBoxProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 260, 40));

        inputProveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inputProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/inputDatosInsumo.png"))); // NOI18N
        getContentPane().add(inputProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 280, 40));

        botonEliminar.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(39, 45, 47));
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
        getContentPane().add(botonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 46, 46));

        fondoBotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonEliminarAmarillo.png"))); // NOI18N
        getContentPane().add(fondoBotonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        botonGuardar.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonGuardar.setForeground(new java.awt.Color(39, 45, 47));
        botonGuardar.setText("Guardar cambios");
        botonGuardar.setBorder(null);
        botonGuardar.setBorderPainted(false);
        botonGuardar.setContentAreaFilled(false);
        botonGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonGuardarMouseExited(evt);
            }
        });
        getContentPane().add(botonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, 175, 46));

        fondoBotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonGuardarCambios.png"))); // NOI18N
        getContentPane().add(fondoBotonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 420, -1, -1));

        labelCantidadExistente.setFont(new java.awt.Font("Open Sans", 1, 16)); // NOI18N
        labelCantidadExistente.setForeground(new java.awt.Color(255, 197, 41));
        labelCantidadExistente.setText("0");
        getContentPane().add(labelCantidadExistente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 120, 40));

        labelCantidad.setFont(new java.awt.Font("Open Sans Medium", 0, 15)); // NOI18N
        labelCantidad.setForeground(new java.awt.Color(255, 255, 255));
        labelCantidad.setText("Cantidad existente: ");
        getContentPane().add(labelCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        fondo1.setBackground(new java.awt.Color(39, 45, 47));
        fondo1.setForeground(new java.awt.Color(39, 45, 47));
        fondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/fondoIngresarDatos.png"))); // NOI18N
        getContentPane().add(fondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 340, 470));

        fondo.setBackground(new java.awt.Color(39, 45, 47));
        fondo.setForeground(new java.awt.Color(39, 45, 47));
        fondo.setOpaque(true);
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarMouseEntered
        // TODO add your handling code here:
        fondoBotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonGuardarCambiosPresionado.png")));
    }//GEN-LAST:event_botonGuardarMouseEntered

    private void botonGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarMouseExited
        // TODO add your handling code here:
        fondoBotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonGuardarCambios.png")));
    }//GEN-LAST:event_botonGuardarMouseExited

    private void botonEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarMouseEntered
        // TODO add your handling code here:
        fondoBotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonEliminarAmarilloPresionado.png")));
    }//GEN-LAST:event_botonEliminarMouseEntered

    private void botonEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarMouseExited
        // TODO add your handling code here:
        fondoBotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonEliminarAmarillo.png")));
    }//GEN-LAST:event_botonEliminarMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameVerInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameVerInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameVerInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameVerInsumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameVerInsumo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonEliminar;
    public javax.swing.JButton botonGuardar;
    public javax.swing.JComboBox<String> comboBoxProveedor;
    public javax.swing.JComboBox<String> comboBoxTipoInsumo;
    public javax.swing.JComboBox<String> comboBoxUnidad;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel fondo1;
    private javax.swing.JLabel fondoBotonEliminar;
    private javax.swing.JLabel fondoBotonGuardar;
    private javax.swing.JLabel inputNombre;
    private javax.swing.JLabel inputProveedor;
    private javax.swing.JLabel inputTipoInsumo;
    private javax.swing.JLabel inputUnidadInsumo;
    private javax.swing.JLabel labelCantidad;
    public javax.swing.JLabel labelCantidadExistente;
    private javax.swing.JLabel labelFondoITitulo;
    public javax.swing.JLabel labelTItulo;
    public javax.swing.JTextField txtNombreInsumo;
    // End of variables declaration//GEN-END:variables
}
