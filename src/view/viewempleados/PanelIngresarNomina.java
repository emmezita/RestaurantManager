/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.viewempleados;

/**
 *
 * @author emmez
 */
public class PanelIngresarNomina extends javax.swing.JPanel {

    /**
     * Creates new form PanelIngresarNomina
     */
    public PanelIngresarNomina() {
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

        botonRegresar = new javax.swing.JButton();
        fondoBotonRegresar = new javax.swing.JLabel();
        botonCrear = new javax.swing.JButton();
        fondoBotonRegistrar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNominas = new javax.swing.JTable();
        labelFechaCierre = new javax.swing.JLabel();
        labelFechaInicio = new javax.swing.JLabel();
        labelTItulo = new javax.swing.JLabel();
        jDateFechaCierre = new com.toedter.calendar.JDateChooser();
        labelInputFechaCierre = new javax.swing.JLabel();
        jDateFechaInicio = new com.toedter.calendar.JDateChooser();
        labelInputFechaInicio = new javax.swing.JLabel();

        setBackground(new java.awt.Color(39, 45, 47));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        add(botonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 50, 50));

        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/botonRegresar.png"))); // NOI18N
        add(fondoBotonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 50));

        botonCrear.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonCrear.setForeground(new java.awt.Color(39, 45, 47));
        botonCrear.setText("Crear");
        botonCrear.setBorder(null);
        botonCrear.setBorderPainted(false);
        botonCrear.setContentAreaFilled(false);
        botonCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonCrear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonCrearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonCrearMouseExited(evt);
            }
        });
        add(botonCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 470, 150, 46));

        fondoBotonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/botonCrear.png"))); // NOI18N
        add(fondoBotonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 470, -1, -1));

        tablaNominas.setBackground(new java.awt.Color(230, 231, 235));
        tablaNominas.setFont(new java.awt.Font("Open Sans", 0, 15)); // NOI18N
        tablaNominas.setForeground(new java.awt.Color(39, 45, 47));
        tablaNominas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cedula", "Nombre", "Rol", "Sueldo diario", "Dias trabajados", "Neto a Cobrar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaNominas);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 578, 552));

        labelFechaCierre.setBackground(new java.awt.Color(0, 0, 0));
        labelFechaCierre.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelFechaCierre.setForeground(new java.awt.Color(230, 231, 235));
        labelFechaCierre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelFechaCierre.setText("Fecha de Cierre");
        add(labelFechaCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 240, 50));

        labelFechaInicio.setBackground(new java.awt.Color(0, 0, 0));
        labelFechaInicio.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelFechaInicio.setForeground(new java.awt.Color(230, 231, 235));
        labelFechaInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelFechaInicio.setText("Fecha de Inicio");
        add(labelFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, 240, 50));

        labelTItulo.setBackground(new java.awt.Color(0, 0, 0));
        labelTItulo.setFont(new java.awt.Font("Open Sans", 1, 28)); // NOI18N
        labelTItulo.setForeground(new java.awt.Color(230, 231, 235));
        labelTItulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTItulo.setText("Crear Nómina de Pago");
        add(labelTItulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 530, 50));

        jDateFechaCierre.setFont(new java.awt.Font("Open Sans Medium", 0, 13)); // NOI18N
        add(jDateFechaCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, 260, 50));

        labelInputFechaCierre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/inputFechaNomina.png"))); // NOI18N
        add(labelInputFechaCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 380, -1, -1));

        jDateFechaInicio.setFont(new java.awt.Font("Open Sans Medium", 0, 13)); // NOI18N
        add(jDateFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, 260, 50));

        labelInputFechaInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/inputFechaNomina.png"))); // NOI18N
        add(labelInputFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegresarMouseEntered
        // TODO add your handling code here:
        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/botonRegresarPresionado.png"))); // NOI18N
    }//GEN-LAST:event_botonRegresarMouseEntered

    private void botonRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegresarMouseExited
        // TODO add your handling code here:
        fondoBotonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/botonRegresar.png"))); // NOI18N
    }//GEN-LAST:event_botonRegresarMouseExited

    private void botonCrearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCrearMouseEntered
        // TODO add your handling code here:
        fondoBotonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/botonCrearPresionado.png")));
    }//GEN-LAST:event_botonCrearMouseEntered

    private void botonCrearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCrearMouseExited
        // TODO add your handling code here:
        fondoBotonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewempleados/botonCrear.png")));
    }//GEN-LAST:event_botonCrearMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonCrear;
    public javax.swing.JButton botonRegresar;
    private javax.swing.JLabel fondoBotonRegistrar;
    private javax.swing.JLabel fondoBotonRegresar;
    public com.toedter.calendar.JDateChooser jDateFechaCierre;
    public com.toedter.calendar.JDateChooser jDateFechaInicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFechaCierre;
    private javax.swing.JLabel labelFechaInicio;
    private javax.swing.JLabel labelInputFechaCierre;
    private javax.swing.JLabel labelInputFechaInicio;
    private javax.swing.JLabel labelTItulo;
    public javax.swing.JTable tablaNominas;
    // End of variables declaration//GEN-END:variables
}
