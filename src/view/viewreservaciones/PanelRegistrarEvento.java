/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.viewreservaciones;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import model.modelview.MyComboBoxRender;

/**
 *
 * @author emmez
 */
public class PanelRegistrarEvento extends javax.swing.JPanel {

    /**
     * Creates new form PanelConsultarReservaciones
     */
    public PanelRegistrarEvento() {
        initComponents();
        comboBoxMetodo .setOpaque(false);
        comboBoxMetodo.setEditable(true);
        JTextField boxField = (JTextField)comboBoxMetodo .getEditor().getEditorComponent();
        boxField.setBorder(BorderFactory.createEmptyBorder());
        boxField.setForeground(new Color(230,231,235));
        boxField.setBackground(new Color(0, 0, 0, 0));
        boxField.setFocusable(false);
        comboBoxMetodo.setRenderer(new MyComboBoxRender());
        
        comboBoxEstatus .setOpaque(false);
        comboBoxEstatus.setEditable(true);
        boxField = (JTextField)comboBoxEstatus .getEditor().getEditorComponent();
        boxField.setBorder(BorderFactory.createEmptyBorder());
        boxField.setForeground(new Color(230,231,235));
        boxField.setBackground(new Color(0, 0, 0, 0));
        boxField.setFocusable(false);
        comboBoxEstatus.setRenderer(new MyComboBoxRender());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonRegistrar = new javax.swing.JButton();
        fondoBotonRegistrar = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        labelInputMotivo = new javax.swing.JLabel();
        labelHoraInicio = new javax.swing.JLabel();
        spinnerHoraInicio = new javax.swing.JSpinner();
        labelInputHoraInicio = new javax.swing.JLabel();
        labelHoraCierre = new javax.swing.JLabel();
        spinnerHoraCierre = new javax.swing.JSpinner();
        labelInputHoraCierre = new javax.swing.JLabel();
        labelMontoTotal = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        labelNumeroNinos = new javax.swing.JLabel();
        labelInputNumeroN = new javax.swing.JLabel();
        labelNumeroAdultos = new javax.swing.JLabel();
        labelInputNumeroA = new javax.swing.JLabel();
        labelTituloAdulto = new javax.swing.JLabel();
        labelTituloNino = new javax.swing.JLabel();
        spinnerMontoNino = new javax.swing.JSpinner();
        labelInputMontoN = new javax.swing.JLabel();
        labelTituloMontoNino = new javax.swing.JLabel();
        spinnerMontoAdulto = new javax.swing.JSpinner();
        labelInputMontoA = new javax.swing.JLabel();
        labelTituloMontoAdulto = new javax.swing.JLabel();
        comboBoxEstatus = new javax.swing.JComboBox<>();
        labelInputEstatus = new javax.swing.JLabel();
        comboBoxMetodo = new javax.swing.JComboBox<>();
        labelInputMetodo = new javax.swing.JLabel();
        labelImagen = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        labelTItulo = new javax.swing.JLabel();
        labelFondoTitulo = new javax.swing.JLabel();
        labelFondoPanel = new javax.swing.JLabel();
        botonRegresar = new javax.swing.JButton();
        fondoBotonRegresar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(39, 45, 47));
        setForeground(new java.awt.Color(230, 231, 235));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        add(botonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 570, 140, 60));

        fondoBotonRegistrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondoBotonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/botonRojo.png"))); // NOI18N
        add(fondoBotonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 570, 160, 60));

        txtMotivo.setBackground(new java.awt.Color(0,0,0,1));
        txtMotivo.setFont(new java.awt.Font("Open Sans Medium", 0, 14)); // NOI18N
        txtMotivo.setForeground(java.awt.Color.lightGray);
        txtMotivo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMotivo.setText("Motivo del evento");
        txtMotivo.setBorder(null);
        txtMotivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMotivoMousePressed(evt);
            }
        });
        add(txtMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 310, 40));

        labelInputMotivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/inputGrandeR.png"))); // NOI18N
        add(labelInputMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, -1, -1));

        labelHoraInicio.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        labelHoraInicio.setForeground(new java.awt.Color(230, 231, 235));
        labelHoraInicio.setText("Hora de Inicio");
        add(labelHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        spinnerHoraInicio.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        spinnerHoraInicio.setModel(new javax.swing.SpinnerNumberModel(55, 0, 100, 1));
        add(spinnerHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 140, 40));

        labelInputHoraInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/inputPequeR.png"))); // NOI18N
        add(labelInputHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, -1, -1));

        labelHoraCierre.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        labelHoraCierre.setForeground(new java.awt.Color(230, 231, 235));
        labelHoraCierre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHoraCierre.setText("Hora de Cierre");
        add(labelHoraCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, 120, -1));

        spinnerHoraCierre.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        spinnerHoraCierre.setModel(new javax.swing.SpinnerNumberModel(15, 0, 100, 1));
        add(spinnerHoraCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 140, 40));

        labelInputHoraCierre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelInputHoraCierre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/inputPequeR.png"))); // NOI18N
        add(labelInputHoraCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 170, -1));

        labelMontoTotal.setFont(new java.awt.Font("Open Sans SemiBold", 0, 18)); // NOI18N
        labelMontoTotal.setForeground(new java.awt.Color(254, 114, 76));
        labelMontoTotal.setText("115$");
        add(labelMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 530, 90, 30));

        labelTotal.setFont(new java.awt.Font("Open Sans SemiBold", 0, 18)); // NOI18N
        labelTotal.setForeground(new java.awt.Color(230, 231, 235));
        labelTotal.setText("Total");
        add(labelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 530, 60, 30));

        labelNumeroNinos.setFont(new java.awt.Font("Open Sans SemiBold", 0, 14)); // NOI18N
        labelNumeroNinos.setForeground(new java.awt.Color(230, 231, 235));
        labelNumeroNinos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNumeroNinos.setText("0");
        add(labelNumeroNinos, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 470, 130, 40));

        labelInputNumeroN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/inputPequeR.png"))); // NOI18N
        add(labelInputNumeroN, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 160, -1));

        labelNumeroAdultos.setFont(new java.awt.Font("Open Sans SemiBold", 0, 14)); // NOI18N
        labelNumeroAdultos.setForeground(new java.awt.Color(230, 231, 235));
        labelNumeroAdultos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNumeroAdultos.setText("0");
        add(labelNumeroAdultos, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 130, 40));

        labelInputNumeroA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/inputPequeR.png"))); // NOI18N
        add(labelInputNumeroA, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, -1, -1));

        labelTituloAdulto.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        labelTituloAdulto.setForeground(new java.awt.Color(230, 231, 235));
        labelTituloAdulto.setText("Número de Adultos");
        add(labelTituloAdulto, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, -1, -1));

        labelTituloNino.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        labelTituloNino.setForeground(new java.awt.Color(230, 231, 235));
        labelTituloNino.setText("Número de Niños");
        add(labelTituloNino, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 440, -1, -1));

        spinnerMontoNino.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        spinnerMontoNino.setModel(new javax.swing.SpinnerNumberModel(15, 0, 100, 1));
        add(spinnerMontoNino, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 140, 40));

        labelInputMontoN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelInputMontoN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/inputPequeR.png"))); // NOI18N
        add(labelInputMontoN, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 380, 170, -1));

        labelTituloMontoNino.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        labelTituloMontoNino.setForeground(new java.awt.Color(230, 231, 235));
        labelTituloMontoNino.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloMontoNino.setText("Monto Niño ($)");
        add(labelTituloMontoNino, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 350, 130, -1));

        spinnerMontoAdulto.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        spinnerMontoAdulto.setModel(new javax.swing.SpinnerNumberModel(55, 0, 100, 1));
        add(spinnerMontoAdulto, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 140, 40));

        labelInputMontoA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/inputPequeR.png"))); // NOI18N
        add(labelInputMontoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, -1, -1));

        labelTituloMontoAdulto.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        labelTituloMontoAdulto.setForeground(new java.awt.Color(230, 231, 235));
        labelTituloMontoAdulto.setText("Monto Adulto ($)");
        add(labelTituloMontoAdulto, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, -1, -1));

        comboBoxEstatus.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxEstatus.setFont(new java.awt.Font("Open Sans Medium", 0, 14)); // NOI18N
        comboBoxEstatus.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona estatus", "Pendiente por pagar", "Pagado" }));
        comboBoxEstatus.setBorder(null);
        comboBoxEstatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 310, 40));

        labelInputEstatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/inputGrandeR.png"))); // NOI18N
        add(labelInputEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, -1, -1));

        comboBoxMetodo.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxMetodo.setFont(new java.awt.Font("Open Sans Medium", 0, 14)); // NOI18N
        comboBoxMetodo.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxMetodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona método de pago", "Efectivo", "Zelle", "Pago móvil", "PayPal" }));
        comboBoxMetodo.setBorder(null);
        comboBoxMetodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxMetodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 310, 40));

        labelInputMetodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/inputGrandeR.png"))); // NOI18N
        add(labelInputMetodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

        labelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/imagenReservacion.png"))); // NOI18N
        add(labelImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 158, -1, -1));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 290, 10, 220));

        labelTItulo.setBackground(new java.awt.Color(0, 0, 0));
        labelTItulo.setFont(new java.awt.Font("Open Sans Medium", 1, 18)); // NOI18N
        labelTItulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTItulo.setText("EVENTO PRIVADO");
        add(labelTItulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 440, 50));

        labelFondoTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/fondoTituloRegistrarE.png"))); // NOI18N
        add(labelFondoTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

        labelFondoPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/fondoRegistrarE.png"))); // NOI18N
        add(labelFondoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

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
        add(botonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 50, 50));

        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/botonRegresar.png"))); // NOI18N
        add(fondoBotonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void txtMotivoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMotivoMousePressed
        // TODO add your handling code here:
        if(txtMotivo.getText().equals("Motivo del evento")){
            txtMotivo.setText("");
            txtMotivo.setForeground(new Color(230,231,235));
        }
    }//GEN-LAST:event_txtMotivoMousePressed

    private void botonRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarMouseEntered
        // TODO add your handling code here:
        fondoBotonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/botonRojoPresionado.png"))); // NOI18N
    }//GEN-LAST:event_botonRegistrarMouseEntered

    private void botonRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarMouseExited
        // TODO add your handling code here:
        fondoBotonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/botonRojo.png"))); // NOI18N
    }//GEN-LAST:event_botonRegistrarMouseExited

    private void botonRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegresarMouseEntered
        // TODO add your handling code here:
        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/botonRegresarPresionado.png"))); // NOI18N
    }//GEN-LAST:event_botonRegresarMouseEntered

    private void botonRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegresarMouseExited
        // TODO add your handling code here:
        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/botonRegresar.png"))); // NOI18N
    }//GEN-LAST:event_botonRegresarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonRegistrar;
    public javax.swing.JButton botonRegresar;
    public javax.swing.JComboBox<String> comboBoxEstatus;
    public javax.swing.JComboBox<String> comboBoxMetodo;
    private javax.swing.JLabel fondoBotonRegistrar;
    private javax.swing.JLabel fondoBotonRegresar;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelFondoPanel;
    private javax.swing.JLabel labelFondoTitulo;
    private javax.swing.JLabel labelHoraCierre;
    private javax.swing.JLabel labelHoraInicio;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel labelInputEstatus;
    private javax.swing.JLabel labelInputHoraCierre;
    private javax.swing.JLabel labelInputHoraInicio;
    private javax.swing.JLabel labelInputMetodo;
    private javax.swing.JLabel labelInputMontoA;
    private javax.swing.JLabel labelInputMontoN;
    private javax.swing.JLabel labelInputMotivo;
    private javax.swing.JLabel labelInputNumeroA;
    private javax.swing.JLabel labelInputNumeroN;
    public javax.swing.JLabel labelMontoTotal;
    public javax.swing.JLabel labelNumeroAdultos;
    public javax.swing.JLabel labelNumeroNinos;
    public javax.swing.JLabel labelTItulo;
    private javax.swing.JLabel labelTituloAdulto;
    private javax.swing.JLabel labelTituloMontoAdulto;
    private javax.swing.JLabel labelTituloMontoNino;
    private javax.swing.JLabel labelTituloNino;
    private javax.swing.JLabel labelTotal;
    public javax.swing.JSpinner spinnerHoraCierre;
    public javax.swing.JSpinner spinnerHoraInicio;
    public javax.swing.JSpinner spinnerMontoAdulto;
    public javax.swing.JSpinner spinnerMontoNino;
    public javax.swing.JTextField txtMotivo;
    // End of variables declaration//GEN-END:variables
}
