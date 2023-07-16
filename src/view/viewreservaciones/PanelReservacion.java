/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.viewreservaciones;

/**
 *
 * @author emmez
 */
public class PanelReservacion extends javax.swing.JPanel {

    /**
     * Creates new form PanelReservacion
     */
    public PanelReservacion() {
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

        jSeparator1 = new javax.swing.JSeparator();
        botonVer = new javax.swing.JButton();
        labelBotonVerMas = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        labelEstatus = new javax.swing.JLabel();
        labelIconoPersonas = new javax.swing.JLabel();
        labelCantidadPersonas = new javax.swing.JLabel();
        labelNombreComensal = new javax.swing.JLabel();
        labelTipoReservacion = new javax.swing.JLabel();
        labelIcono = new javax.swing.JLabel();
        labelFondo = new javax.swing.JLabel();
        labelCedula = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(230, 231, 235));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 300, 10));

        botonVer.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonVer.setForeground(new java.awt.Color(230, 231, 235));
        botonVer.setBorder(null);
        botonVer.setBorderPainted(false);
        botonVer.setContentAreaFilled(false);
        botonVer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonVer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(botonVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 30, 30));

        labelBotonVerMas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelBotonVerMas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/botonVerMas.png"))); // NOI18N
        add(labelBotonVerMas, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 20, 30));

        labelFecha.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        labelFecha.setForeground(new java.awt.Color(230, 231, 235));
        labelFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFecha.setText("STATUS");
        add(labelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 90, 30));

        labelEstatus.setFont(new java.awt.Font("Open Sans", 1, 15)); // NOI18N
        labelEstatus.setForeground(new java.awt.Color(230, 231, 235));
        labelEstatus.setText("ESTATUS");
        add(labelEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 90, 30));

        labelIconoPersonas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/iconoCantidadPersonas.png"))); // NOI18N
        add(labelIconoPersonas, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, 40));

        labelCantidadPersonas.setFont(new java.awt.Font("Open Sans", 1, 30)); // NOI18N
        labelCantidadPersonas.setForeground(new java.awt.Color(230, 231, 235));
        labelCantidadPersonas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCantidadPersonas.setText("100");
        add(labelCantidadPersonas, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 60, -1));

        labelNombreComensal.setFont(new java.awt.Font("Open Sans SemiBold", 0, 20)); // NOI18N
        labelNombreComensal.setForeground(new java.awt.Color(230, 231, 235));
        labelNombreComensal.setText("Nombre");
        add(labelNombreComensal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 180, -1));

        labelTipoReservacion.setFont(new java.awt.Font("Open Sans SemiBold", 0, 12)); // NOI18N
        labelTipoReservacion.setForeground(new java.awt.Color(230, 231, 235));
        labelTipoReservacion.setText("Tipo de Reservacion");
        add(labelTipoReservacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 180, -1));

        labelIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/iconoCarita.png"))); // NOI18N
        add(labelIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 50, 60));

        labelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewreservaciones/fondoReservacion.png"))); // NOI18N
        add(labelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 350, 150));
        add(labelCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 30, 20));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonVer;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelBotonVerMas;
    public javax.swing.JLabel labelCantidadPersonas;
    public javax.swing.JLabel labelCedula;
    public javax.swing.JLabel labelEstatus;
    public javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelFondo;
    private javax.swing.JLabel labelIcono;
    private javax.swing.JLabel labelIconoPersonas;
    public javax.swing.JLabel labelNombreComensal;
    public javax.swing.JLabel labelTipoReservacion;
    // End of variables declaration//GEN-END:variables
}
