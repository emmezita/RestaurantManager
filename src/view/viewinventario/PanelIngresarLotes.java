/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.viewinventario;

/**
 *
 * @author emmez
 */
public class PanelIngresarLotes extends javax.swing.JPanel {

    /**
     * Creates new form PanelIngresarNomina
     */
    public PanelIngresarLotes() {
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

        botonConsultarSalidas = new javax.swing.JButton();
        botonConsultarEntradas = new javax.swing.JButton();
        fondoBotonConsultar = new javax.swing.JLabel();
        botonRegistrarS = new javax.swing.JButton();
        botonRegistrarE = new javax.swing.JButton();
        fondoBotonRegistrar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEntradas = new javax.swing.JTable();
        labelDocumento = new javax.swing.JLabel();
        labelResponsable = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        labelTItulo = new javax.swing.JLabel();
        txtCedula = new javax.swing.JLabel();
        labelInputDocumento = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JLabel();
        labelInputResponsable = new javax.swing.JLabel();
        txtFechaIngreso = new javax.swing.JLabel();
        labelInputFechaIngreso = new javax.swing.JLabel();
        botonRegresarI = new javax.swing.JButton();
        fondoBotonRegresar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(39, 45, 47));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonConsultarSalidas.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonConsultarSalidas.setForeground(new java.awt.Color(230, 231, 235));
        botonConsultarSalidas.setText("Consultar salidas");
        botonConsultarSalidas.setBorder(null);
        botonConsultarSalidas.setBorderPainted(false);
        botonConsultarSalidas.setContentAreaFilled(false);
        botonConsultarSalidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonConsultarSalidas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonConsultarSalidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonConsultarSalidasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonConsultarSalidasMouseExited(evt);
            }
        });
        add(botonConsultarSalidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 40, 180, 40));

        botonConsultarEntradas.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonConsultarEntradas.setForeground(new java.awt.Color(230, 231, 235));
        botonConsultarEntradas.setText("Consultar entradas");
        botonConsultarEntradas.setBorder(null);
        botonConsultarEntradas.setBorderPainted(false);
        botonConsultarEntradas.setContentAreaFilled(false);
        botonConsultarEntradas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonConsultarEntradas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonConsultarEntradas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonConsultarEntradasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonConsultarEntradasMouseExited(evt);
            }
        });
        add(botonConsultarEntradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 40, 180, 40));

        fondoBotonConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonConsultar.png"))); // NOI18N
        add(fondoBotonConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 40, -1, -1));

        botonRegistrarS.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonRegistrarS.setForeground(new java.awt.Color(39, 45, 47));
        botonRegistrarS.setText("Registrar");
        botonRegistrarS.setBorder(null);
        botonRegistrarS.setBorderPainted(false);
        botonRegistrarS.setContentAreaFilled(false);
        botonRegistrarS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegistrarS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRegistrarS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonRegistrarSMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonRegistrarSMouseExited(evt);
            }
        });
        add(botonRegistrarS, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 540, 142, 46));

        botonRegistrarE.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonRegistrarE.setForeground(new java.awt.Color(39, 45, 47));
        botonRegistrarE.setText("Registrar");
        botonRegistrarE.setBorder(null);
        botonRegistrarE.setBorderPainted(false);
        botonRegistrarE.setContentAreaFilled(false);
        botonRegistrarE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegistrarE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonRegistrarE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonRegistrarEMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonRegistrarEMouseExited(evt);
            }
        });
        add(botonRegistrarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 540, 142, 46));

        fondoBotonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonRegistrar.png"))); // NOI18N
        add(fondoBotonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 540, -1, -1));

        tablaEntradas.setBackground(new java.awt.Color(230, 231, 235));
        tablaEntradas.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        tablaEntradas.setForeground(new java.awt.Color(39, 45, 47));
        tablaEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Tipo", "Unidad", "Cantidad", "Fecha de Vencimiento"
            }
        ));
        jScrollPane1.setViewportView(tablaEntradas);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 578, 552));

        labelDocumento.setBackground(new java.awt.Color(0, 0, 0));
        labelDocumento.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelDocumento.setForeground(new java.awt.Color(230, 231, 235));
        labelDocumento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDocumento.setText("Documento de identidad");
        add(labelDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 400, 240, 50));

        labelResponsable.setBackground(new java.awt.Color(0, 0, 0));
        labelResponsable.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelResponsable.setForeground(new java.awt.Color(230, 231, 235));
        labelResponsable.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelResponsable.setText("Responsable");
        add(labelResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 280, 240, 50));

        labelFecha.setBackground(new java.awt.Color(0, 0, 0));
        labelFecha.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelFecha.setForeground(new java.awt.Color(230, 231, 235));
        labelFecha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelFecha.setText("Fecha de Ingreso");
        add(labelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 240, 50));

        labelTItulo.setBackground(new java.awt.Color(0, 0, 0));
        labelTItulo.setFont(new java.awt.Font("Open Sans", 1, 28)); // NOI18N
        labelTItulo.setForeground(new java.awt.Color(230, 231, 235));
        labelTItulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTItulo.setText("Entrada de Insumos");
        add(labelTItulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 530, 50));

        txtCedula.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(230, 231, 235));
        txtCedula.setText("V-28301361");
        add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 450, 220, 50));

        labelInputDocumento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/inputFechaNomina.png"))); // NOI18N
        add(labelInputDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 450, -1, -1));

        txtResponsable.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        txtResponsable.setForeground(new java.awt.Color(230, 231, 235));
        txtResponsable.setText("Italo Visconti");
        add(txtResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 330, 220, 50));

        labelInputResponsable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/inputFechaNomina.png"))); // NOI18N
        add(labelInputResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, -1, -1));

        txtFechaIngreso.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        txtFechaIngreso.setForeground(new java.awt.Color(230, 231, 235));
        txtFechaIngreso.setText("03/07/23 16:00");
        add(txtFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 220, 50));

        labelInputFechaIngreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/inputFechaNomina.png"))); // NOI18N
        add(labelInputFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, -1, -1));

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
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarEMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarEMouseEntered
        // TODO add your handling code here:
        fondoBotonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonRegistrarPresionado.png")));
    }//GEN-LAST:event_botonRegistrarEMouseEntered

    private void botonRegistrarEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarEMouseExited
        // TODO add your handling code here:
        fondoBotonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonRegistrar.png")));
    }//GEN-LAST:event_botonRegistrarEMouseExited

    private void botonRegresarIMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegresarIMouseEntered
        // TODO add your handling code here:
        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonRegresarPresionado.png"))); // NOI18N
    }//GEN-LAST:event_botonRegresarIMouseEntered

    private void botonRegresarIMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegresarIMouseExited
        // TODO add your handling code here:
        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonRegresar.png"))); // NOI18N
    }//GEN-LAST:event_botonRegresarIMouseExited

    private void botonConsultarEntradasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonConsultarEntradasMouseEntered
        // TODO add your handling code here:
        botonConsultarEntradas.setForeground(new java.awt.Color(39,45,47));
        fondoBotonConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonConsultarPresionado.png")));
    }//GEN-LAST:event_botonConsultarEntradasMouseEntered

    private void botonConsultarEntradasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonConsultarEntradasMouseExited
        // TODO add your handling code here:
        botonConsultarEntradas.setForeground(new java.awt.Color(230, 231, 235));
        fondoBotonConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonConsultar.png")));
    }//GEN-LAST:event_botonConsultarEntradasMouseExited

    private void botonConsultarSalidasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonConsultarSalidasMouseEntered
        // TODO add your handling code here:
        botonConsultarSalidas.setForeground(new java.awt.Color(39,45,47));
        fondoBotonConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonConsultarPresionado.png")));
    }//GEN-LAST:event_botonConsultarSalidasMouseEntered

    private void botonConsultarSalidasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonConsultarSalidasMouseExited
        // TODO add your handling code here:
        botonConsultarSalidas.setForeground(new java.awt.Color(230, 231, 235));
        fondoBotonConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewinventario/botonConsultar.png")));
    }//GEN-LAST:event_botonConsultarSalidasMouseExited

    private void botonRegistrarSMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarSMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRegistrarSMouseEntered

    private void botonRegistrarSMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarSMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRegistrarSMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonConsultarEntradas;
    public javax.swing.JButton botonConsultarSalidas;
    public javax.swing.JButton botonRegistrarE;
    public javax.swing.JButton botonRegistrarS;
    public javax.swing.JButton botonRegresarI;
    private javax.swing.JLabel fondoBotonConsultar;
    private javax.swing.JLabel fondoBotonRegistrar;
    private javax.swing.JLabel fondoBotonRegresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDocumento;
    public javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelInputDocumento;
    private javax.swing.JLabel labelInputFechaIngreso;
    private javax.swing.JLabel labelInputResponsable;
    private javax.swing.JLabel labelResponsable;
    public javax.swing.JLabel labelTItulo;
    public javax.swing.JTable tablaEntradas;
    public javax.swing.JLabel txtCedula;
    public javax.swing.JLabel txtFechaIngreso;
    public javax.swing.JLabel txtResponsable;
    // End of variables declaration//GEN-END:variables
}
