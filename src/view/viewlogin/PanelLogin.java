/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.viewlogin;

import java.awt.Color;

/**
 *
 * @author Estefany Torres
 */
public class PanelLogin extends javax.swing.JPanel {


    public PanelLogin() {
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

        botonRecuperarPass = new javax.swing.JButton();
        imagenDecorativa = new javax.swing.JLabel();
        botonIniciarSesion = new javax.swing.JButton();
        fondoBotonIniciar = new javax.swing.JLabel();
        labelInicio = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        inputUser = new javax.swing.JLabel();
        InputPass = new javax.swing.JLabel();
        labelMiembro = new javax.swing.JLabel();
        botonCrearCuenta = new javax.swing.JButton();
        fondoBotonCrear = new javax.swing.JLabel();
        recuadroFondo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(39, 45, 47));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonRecuperarPass.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        botonRecuperarPass.setForeground(java.awt.Color.lightGray);
        botonRecuperarPass.setText("¿Olvidaste la contraseña?");
        botonRecuperarPass.setBorder(null);
        botonRecuperarPass.setBorderPainted(false);
        botonRecuperarPass.setContentAreaFilled(false);
        botonRecuperarPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRecuperarPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonRecuperarPassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonRecuperarPassMouseExited(evt);
            }
        });
        add(botonRecuperarPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 310, 20));

        imagenDecorativa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/ImagenDecorativaInicio.png"))); // NOI18N
        add(imagenDecorativa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 1030, -1));

        botonIniciarSesion.setFont(new java.awt.Font("Open Sans SemiBold", 0, 19)); // NOI18N
        botonIniciarSesion.setForeground(new java.awt.Color(230, 231, 235));
        botonIniciarSesion.setText("Iniciar");
        botonIniciarSesion.setBorder(null);
        botonIniciarSesion.setBorderPainted(false);
        botonIniciarSesion.setContentAreaFilled(false);
        botonIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonIniciarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonIniciarSesionMouseExited(evt);
            }
        });
        add(botonIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 368, 100, 50));

        fondoBotonIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonNaranjaInicio.png"))); // NOI18N
        add(fondoBotonIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, -1, -1));

        labelInicio.setFont(new java.awt.Font("Open Sans", 1, 31)); // NOI18N
        labelInicio.setForeground(new java.awt.Color(230, 231, 235));
        labelInicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelInicio.setText("Inicio Sesión");
        add(labelInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 130, 400, -1));

        txtUser.setBackground(new java.awt.Color(0,0,0,1));
        txtUser.setFont(new java.awt.Font("Open Sans Medium", 0, 14)); // NOI18N
        txtUser.setForeground(new java.awt.Color(204, 204, 204));
        txtUser.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUser.setText("Usuario o correo");
        txtUser.setBorder(null);
        txtUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUserMousePressed(evt);
            }
        });
        add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 260, 50));

        txtPass.setBackground(new java.awt.Color(0,0,0,1));
        txtPass.setFont(new java.awt.Font("Open Sans Medium", 0, 14)); // NOI18N
        txtPass.setForeground(new java.awt.Color(204, 204, 204));
        txtPass.setText("******");
        txtPass.setBorder(null);
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPassMousePressed(evt);
            }
        });
        add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, 260, 50));

        inputUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inputUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BarraInputnicioSesion.png"))); // NOI18N
        add(inputUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 340, -1));

        InputPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InputPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BarraInputnicioSesion.png"))); // NOI18N
        add(InputPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 340, -1));

        labelMiembro.setBackground(new java.awt.Color(153, 153, 153));
        labelMiembro.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        labelMiembro.setForeground(java.awt.Color.lightGray);
        labelMiembro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelMiembro.setText("¿No eres miembro?");
        add(labelMiembro, new org.netbeans.lib.awtextra.AbsoluteConstraints(588, 46, 230, 30));

        botonCrearCuenta.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonCrearCuenta.setForeground(new java.awt.Color(230, 231, 235));
        botonCrearCuenta.setText("Crear cuenta");
        botonCrearCuenta.setBorder(null);
        botonCrearCuenta.setBorderPainted(false);
        botonCrearCuenta.setContentAreaFilled(false);
        botonCrearCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonCrearCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonCrearCuentaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonCrearCuentaMouseExited(evt);
            }
        });
        add(botonCrearCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 140, 40));

        fondoBotonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonAmarilloCrearCuenta.png"))); // NOI18N
        add(fondoBotonCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 140, 40));

        recuadroFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recuadroFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/RecuadroFondoInicio.png"))); // NOI18N
        add(recuadroFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 420, 290));
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserMousePressed
        // TODO add your handling code here:
        if(txtUser.getText().equals("Usuario o correo")){
            txtUser.setText("");
            txtUser.setForeground(new Color(230,231,235));
        }
        
        if(String.valueOf(txtPass.getPassword()).isEmpty()){
            txtPass.setText("******");
            txtPass.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_txtUserMousePressed

    private void botonIniciarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIniciarSesionMouseEntered
        // TODO add your handling code here:
        fondoBotonIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonNaranjaClaroInicio.png"))); // NOI18N
    }//GEN-LAST:event_botonIniciarSesionMouseEntered

    private void botonIniciarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIniciarSesionMouseExited
        // TODO add your handling code here:
        fondoBotonIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonNaranjaInicio.png"))); // NOI18N
    }//GEN-LAST:event_botonIniciarSesionMouseExited

    private void botonCrearCuentaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCrearCuentaMouseEntered
        // TODO add your handling code here:
        fondoBotonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonPresionadoAmarilloCrearCuenta.png"))); // NOI18N
        botonCrearCuenta.setForeground(new Color(39,45,47));
    }//GEN-LAST:event_botonCrearCuentaMouseEntered

    private void botonCrearCuentaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCrearCuentaMouseExited
        // TODO add your handling code here:
        botonCrearCuenta.setForeground(new Color(230,231,235));
        fondoBotonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewlogin/BotonAmarilloCrearCuenta.png"))); // NOI18N
    }//GEN-LAST:event_botonCrearCuentaMouseExited

    private void txtPassMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMousePressed
        // TODO add your handling code here:
        if(txtUser.getText().isEmpty()){
            txtUser.setText("Usuario o correo");
            txtUser.setForeground(Color.LIGHT_GRAY);
        }
        
        if(String.valueOf(txtPass.getPassword()).equals("******")){
            txtPass.setText("");
            txtPass.setForeground(new Color(230,231,235));
        }
    }//GEN-LAST:event_txtPassMousePressed

    private void botonRecuperarPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRecuperarPassMouseEntered
        // TODO add your handling code here:
        botonRecuperarPass.setForeground(Color.white);
    }//GEN-LAST:event_botonRecuperarPassMouseEntered

    private void botonRecuperarPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRecuperarPassMouseExited
        // TODO add your handling code here:
        botonRecuperarPass.setForeground(Color.lightGray);
    }//GEN-LAST:event_botonRecuperarPassMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel InputPass;
    public javax.swing.JButton botonCrearCuenta;
    public javax.swing.JButton botonIniciarSesion;
    public javax.swing.JButton botonRecuperarPass;
    public javax.swing.JLabel fondoBotonCrear;
    public javax.swing.JLabel fondoBotonIniciar;
    private javax.swing.JLabel imagenDecorativa;
    private javax.swing.JLabel inputUser;
    private javax.swing.JLabel labelInicio;
    private javax.swing.JLabel labelMiembro;
    private javax.swing.JLabel recuadroFondo;
    public javax.swing.JPasswordField txtPass;
    public javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
