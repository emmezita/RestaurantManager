/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.viewusuarios;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import model.modelview.MyComboBoxRender;

/**
 *
 * @author Estefany Torres
 */
public class PanelEditarUsuario extends javax.swing.JPanel {

    public PanelEditarUsuario() {
        initComponents();
        /*comboBoxMunicipio.setOpaque(false);
        comboBoxMunicipio.setRenderer(new DefaultListCellRenderer(){
        @Override
        public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
                JComponent result = (JComponent)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                result.setOpaque(false);
                return result;
        }});*/
        comboBoxMunicipio .setOpaque(false);
        comboBoxMunicipio.setEditable(true);
        JTextField boxField = (JTextField)comboBoxMunicipio .getEditor().getEditorComponent();
        boxField.setBorder(BorderFactory.createEmptyBorder());
        boxField.setForeground(Color.white);
        boxField.setBackground(new Color(0, 0, 0, 0));
        boxField.setFocusable(false);
        comboBoxMunicipio.setRenderer(new MyComboBoxRender());
        
        comboBoxSexo .setOpaque(false);
        comboBoxSexo.setEditable(true);
        boxField = (JTextField)comboBoxSexo .getEditor().getEditorComponent();
        boxField.setBorder(BorderFactory.createEmptyBorder());
        boxField.setForeground(Color.white);
        boxField.setBackground(new Color(0, 0, 0, 0));
        boxField.setFocusable(false);
        comboBoxSexo.setRenderer(new MyComboBoxRender());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        labelInputNumero = new javax.swing.JLabel();
        labelCedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        labelInputCedula = new javax.swing.JLabel();
        labelSexo = new javax.swing.JLabel();
        comboBoxSexo = new javax.swing.JComboBox<>();
        labelInputSexo = new javax.swing.JLabel();
        labelEdad = new javax.swing.JLabel();
        spinnerEdad = new javax.swing.JSpinner();
        labelInputEdad = new javax.swing.JLabel();
        labelMunicipio = new javax.swing.JLabel();
        comboBoxMunicipio = new javax.swing.JComboBox<>();
        labelInputMunicipio = new javax.swing.JLabel();
        labelCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        labelInputCorreo = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        labelInputUsuario = new javax.swing.JLabel();
        labelApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        labelInputApellido = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        labelInputNombre = new javax.swing.JLabel();
        botonGestionarUsuarios = new javax.swing.JButton();
        fondoBotonGestion = new javax.swing.JLabel();
        botonEditarFoto = new javax.swing.JButton();
        fondoEditarFoto = new javax.swing.JLabel();
        fotoPerfil = new javax.swing.JLabel();
        labelNombreUsuario = new javax.swing.JLabel();
        labelRol = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        fondoBotonGuardar = new javax.swing.JLabel();
        botonReestablecer = new javax.swing.JButton();

        setBackground(new java.awt.Color(39, 45, 47));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelNumero.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelNumero.setForeground(new java.awt.Color(230, 231, 235));
        labelNumero.setText("Número de teléfono");
        add(labelNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, 250, -1));

        txtNumero.setBackground(new java.awt.Color(0,0,0,1));
        txtNumero.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        txtNumero.setForeground(java.awt.Color.lightGray);
        txtNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumero.setText("Número");
        txtNumero.setBorder(null);
        txtNumero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtNumeroMousePressed(evt);
            }
        });
        add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 510, 230, 50));

        labelInputNumero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInputNumero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/inputNumero.png"))); // NOI18N
        add(labelInputNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 510, -1, -1));

        labelCedula.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelCedula.setForeground(new java.awt.Color(230, 231, 235));
        labelCedula.setText("Cédula de identidad");
        add(labelCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, 230, -1));

        txtCedula.setBackground(new java.awt.Color(0,0,0,1));
        txtCedula.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        txtCedula.setForeground(java.awt.Color.lightGray);
        txtCedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCedula.setText("Cédula");
        txtCedula.setBorder(null);
        txtCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCedulaMousePressed(evt);
            }
        });
        add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 510, 210, 50));

        labelInputCedula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInputCedula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/inputCedula.png"))); // NOI18N
        add(labelInputCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 510, 230, -1));

        labelSexo.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelSexo.setForeground(new java.awt.Color(230, 231, 235));
        labelSexo.setText("Sexo");
        add(labelSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 470, 140, -1));

        comboBoxSexo.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxSexo.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        comboBoxSexo.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino" }));
        comboBoxSexo.setBorder(null);
        comboBoxSexo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 510, 120, 50));

        labelInputSexo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInputSexo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/inputSexo.png"))); // NOI18N
        add(labelInputSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 510, 150, -1));

        labelEdad.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelEdad.setForeground(new java.awt.Color(230, 231, 235));
        labelEdad.setText("Edad");
        add(labelEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 100, -1));

        spinnerEdad.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        spinnerEdad.setModel(new javax.swing.SpinnerNumberModel(30, 18, 90, 1));
        add(spinnerEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, 90, 50));

        labelInputEdad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInputEdad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/inputEdad.png"))); // NOI18N
        add(labelInputEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 110, -1));

        labelMunicipio.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelMunicipio.setForeground(new java.awt.Color(230, 231, 235));
        labelMunicipio.setText("Municipio");
        add(labelMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 370, -1));

        comboBoxMunicipio.setBackground(new java.awt.Color(86, 92, 94));
        comboBoxMunicipio.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        comboBoxMunicipio.setForeground(new java.awt.Color(255, 255, 255));
        comboBoxMunicipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona el municipio", "Baruta", "Chacao", "El Hatillo", "Libertador", "Sucre" }));
        comboBoxMunicipio.setBorder(null);
        comboBoxMunicipio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboBoxMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 380, 290, 50));

        labelInputMunicipio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInputMunicipio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/inputCorreo.png"))); // NOI18N
        add(labelInputMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, 380, -1));

        labelCorreo.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelCorreo.setForeground(new java.awt.Color(230, 231, 235));
        labelCorreo.setText("Correo");
        add(labelCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 370, -1));

        txtCorreo.setBackground(new java.awt.Color(0,0,0,1));
        txtCorreo.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        txtCorreo.setForeground(java.awt.Color.lightGray);
        txtCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreo.setText("Correo");
        txtCorreo.setBorder(null);
        txtCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCorreoMousePressed(evt);
            }
        });
        add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 360, 50));

        labelInputCorreo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInputCorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/inputCorreo.png"))); // NOI18N
        add(labelInputCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 380, -1));

        labelUsuario.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(230, 231, 235));
        labelUsuario.setText("Usuario");
        add(labelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 210, 280, -1));

        txtUsuario.setBackground(new java.awt.Color(0,0,0,1));
        txtUsuario.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setText("Username");
        txtUsuario.setBorder(null);
        txtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUsuarioMousePressed(evt);
            }
        });
        add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 270, 50));

        labelInputUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInputUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/inputUser.png"))); // NOI18N
        add(labelInputUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 290, -1));

        labelApellido.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelApellido.setForeground(new java.awt.Color(230, 231, 235));
        labelApellido.setText("Apellido");
        add(labelApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 220, -1));

        txtApellido.setBackground(new java.awt.Color(0,0,0,1));
        txtApellido.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        txtApellido.setForeground(java.awt.Color.lightGray);
        txtApellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApellido.setText("Apellido");
        txtApellido.setBorder(null);
        txtApellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtApellidoMousePressed(evt);
            }
        });
        add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 210, 50));

        labelInputApellido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInputApellido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/inputNyA.png"))); // NOI18N
        add(labelInputApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 230, -1));

        labelNombre.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(230, 231, 235));
        labelNombre.setText("Nombre");
        add(labelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 220, -1));

        txtNombre.setBackground(new java.awt.Color(0,0,0,1));
        txtNombre.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        txtNombre.setForeground(java.awt.Color.lightGray);
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setText("Nombre");
        txtNombre.setBorder(null);
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtNombreMousePressed(evt);
            }
        });
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 251, 210, 50));

        labelInputNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInputNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/inputNyA.png"))); // NOI18N
        add(labelInputNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 230, -1));

        botonGestionarUsuarios.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonGestionarUsuarios.setForeground(new java.awt.Color(230, 231, 235));
        botonGestionarUsuarios.setText("Gestionar usuarios");
        botonGestionarUsuarios.setBorder(null);
        botonGestionarUsuarios.setBorderPainted(false);
        botonGestionarUsuarios.setContentAreaFilled(false);
        botonGestionarUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonGestionarUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGestionarUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonGestionarUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonGestionarUsuariosMouseExited(evt);
            }
        });
        add(botonGestionarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 170, 40));

        fondoBotonGestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonGestion.png"))); // NOI18N
        add(fondoBotonGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, -1, -1));

        botonEditarFoto.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonEditarFoto.setForeground(new java.awt.Color(230, 231, 235));
        botonEditarFoto.setBorder(null);
        botonEditarFoto.setBorderPainted(false);
        botonEditarFoto.setContentAreaFilled(false);
        botonEditarFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonEditarFoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEditarFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonEditarFotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonEditarFotoMouseExited(evt);
            }
        });
        add(botonEditarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 30, 30));

        fondoEditarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/BotonEditarFoto.png"))); // NOI18N
        add(fondoEditarFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, -1, -1));

        fotoPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fotoPerfil.png"))); // NOI18N
        add(fotoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 130, 130));

        labelNombreUsuario.setFont(new java.awt.Font("Open Sans", 1, 28)); // NOI18N
        labelNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelNombreUsuario.setText("Nombre Apellido");
        add(labelNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 81, 440, -1));

        labelRol.setFont(new java.awt.Font("Open Sans Medium", 0, 18)); // NOI18N
        labelRol.setForeground(java.awt.Color.lightGray);
        labelRol.setText("Rol");
        add(labelRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 440, -1));

        botonGuardar.setFont(new java.awt.Font("Open Sans SemiBold", 0, 15)); // NOI18N
        botonGuardar.setForeground(new java.awt.Color(230, 231, 235));
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
        add(botonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(363, 630, 200, 50));

        fondoBotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonGuardar.png"))); // NOI18N
        add(fondoBotonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 630, -1, 50));

        botonReestablecer.setFont(new java.awt.Font("Open Sans", 0, 13)); // NOI18N
        botonReestablecer.setForeground(java.awt.Color.lightGray);
        botonReestablecer.setText("Reestablecer Contraseña");
        botonReestablecer.setBorder(null);
        botonReestablecer.setBorderPainted(false);
        botonReestablecer.setContentAreaFilled(false);
        botonReestablecer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonReestablecer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonReestablecerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonReestablecerMouseExited(evt);
            }
        });
        add(botonReestablecer, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 590, 180, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void botonGestionarUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGestionarUsuariosMouseEntered
        // TODO add your handling code here:
        botonGestionarUsuarios.setForeground(new Color(39,45,47));
        fondoBotonGestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonGestionPresionado.png"))); // NOI18N
    }//GEN-LAST:event_botonGestionarUsuariosMouseEntered

    private void botonGestionarUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGestionarUsuariosMouseExited
        // TODO add your handling code here:
        botonGestionarUsuarios.setForeground(new Color(230,231,235));
        fondoBotonGestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonGestion.png"))); // NOI18N
    }//GEN-LAST:event_botonGestionarUsuariosMouseExited

    private void botonEditarFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEditarFotoMouseEntered
        // TODO add your handling code here:
        fondoEditarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/BotonEditarFotoPresionado.png"))); // NOI18N
    }//GEN-LAST:event_botonEditarFotoMouseEntered

    private void botonEditarFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEditarFotoMouseExited
        // TODO add your handling code here:
        fondoEditarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/BotonEditarFoto.png"))); // NOI18N
    }//GEN-LAST:event_botonEditarFotoMouseExited

    private void botonGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarMouseEntered
        // TODO add your handling code here:
        fondoBotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonGuardarPresionado.png")));
    }//GEN-LAST:event_botonGuardarMouseEntered

    private void botonGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarMouseExited
        // TODO add your handling code here:
        fondoBotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagesviewusuarios/fondoBotonGuardar.png")));
    }//GEN-LAST:event_botonGuardarMouseExited

    private void botonReestablecerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonReestablecerMouseEntered
        // TODO add your handling code here:
        botonReestablecer.setForeground(Color.white);
    }//GEN-LAST:event_botonReestablecerMouseEntered

    private void botonReestablecerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonReestablecerMouseExited
        // TODO add your handling code here:
        botonReestablecer.setForeground(Color.lightGray);
    }//GEN-LAST:event_botonReestablecerMouseExited

    private void txtNombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMousePressed
        // TODO add your handling code here:
        if(txtNombre.getText().equals("Nombre")){
            txtNombre.setForeground(Color.white);
            txtNombre.setText("");
        }
        if(txtApellido.getText().equals("")){
            txtApellido.setForeground(new Color(153,153,153));
            txtApellido.setText("Apellido");
        }
        if(txtUsuario.getText().equals("")){
            txtUsuario.setForeground(new Color(153,153,153));
            txtUsuario.setText("Username");
        }
        if(txtCorreo.getText().equals("")){
            txtCorreo.setForeground(new Color(153,153,153));
            txtCorreo.setText("Correo");
        }
        if(txtCedula.getText().equals("")){
            txtCedula.setForeground(new Color(153,153,153));
            txtCedula.setText("Cédula");
        }
        if(txtNumero.getText().equals("")){
            txtNumero.setForeground(new Color(153,153,153));
            txtNumero.setText("Número");
        }
    }//GEN-LAST:event_txtNombreMousePressed

    private void txtApellidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtApellidoMousePressed
        // TODO add your handling code here:
        if(txtNombre.getText().equals("")){
            txtNombre.setForeground(new Color(153,153,153));
            txtNombre.setText("Nombre");
        }
        if(txtApellido.getText().equals("Apellido")){
            txtApellido.setForeground(Color.white);
            txtApellido.setText("");
        }
        if(txtUsuario.getText().equals("")){
            txtUsuario.setForeground(new Color(153,153,153));
            txtUsuario.setText("Username");
        }
        if(txtCorreo.getText().equals("")){
            txtCorreo.setForeground(new Color(153,153,153));
            txtCorreo.setText("Correo");
        }
        if(txtCedula.getText().equals("")){
            txtCedula.setForeground(new Color(153,153,153));
            txtCedula.setText("Cédula");
        }
        if(txtNumero.getText().equals("")){
            txtNumero.setForeground(new Color(153,153,153));
            txtNumero.setText("Número");
        }
    }//GEN-LAST:event_txtApellidoMousePressed

    private void txtUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMousePressed
        // TODO add your handling code here:
        if(txtNombre.getText().equals("")){
            txtNombre.setForeground(new Color(153,153,153));
            txtNombre.setText("Nombre");
        }
        if(txtApellido.getText().equals("")){
            txtApellido.setForeground(new Color(153,153,153));
            txtApellido.setText("Apellido");
        }
        if(txtUsuario.getText().equals("Username")){
            txtUsuario.setForeground(Color.white);
            txtUsuario.setText("");
        }
        if(txtCorreo.getText().equals("")){
            txtCorreo.setForeground(new Color(153,153,153));
            txtCorreo.setText("Correo");
        }
        if(txtCedula.getText().equals("")){
            txtCedula.setForeground(new Color(153,153,153));
            txtCedula.setText("Cédula");
        }
        if(txtNumero.getText().equals("")){
            txtNumero.setForeground(new Color(153,153,153));
            txtNumero.setText("Número");
        }
    }//GEN-LAST:event_txtUsuarioMousePressed

    private void txtCorreoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCorreoMousePressed
        // TODO add your handling code here:
        if(txtNombre.getText().equals("")){
            txtNombre.setForeground(new Color(153,153,153));
            txtNombre.setText("Nombre");
        }
        if(txtApellido.getText().equals("")){
            txtApellido.setForeground(new Color(153,153,153));
            txtApellido.setText("Apellido");
        }
        if(txtUsuario.getText().equals("")){
            txtUsuario.setForeground(new Color(153,153,153));
            txtUsuario.setText("Username");
        }
        if(txtCorreo.getText().equals("Correo")){
            txtCorreo.setForeground(Color.white);
            txtCorreo.setText("");
        }
        if(txtCedula.getText().equals("")){
            txtCedula.setForeground(new Color(153,153,153));
            txtCedula.setText("Cédula");
        }
        if(txtNumero.getText().equals("")){
            txtNumero.setForeground(new Color(153,153,153));
            txtNumero.setText("Número");
        }
    }//GEN-LAST:event_txtCorreoMousePressed

    private void txtCedulaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCedulaMousePressed
        // TODO add your handling code here:
        if(txtNombre.getText().equals("")){
            txtNombre.setForeground(new Color(153,153,153));
            txtNombre.setText("Nombre");
        }
        if(txtApellido.getText().equals("")){
            txtApellido.setForeground(new Color(153,153,153));
            txtApellido.setText("Apellido");
        }
        if(txtUsuario.getText().equals("")){
            txtUsuario.setForeground(new Color(153,153,153));
            txtUsuario.setText("Username");
        }
        if(txtCorreo.getText().equals("")){
            txtCorreo.setForeground(new Color(153,153,153));
            txtCorreo.setText("Correo");
        }
        if(txtCedula.getText().equals("Cédula")){
            txtCedula.setForeground(Color.white);
            txtCedula.setText("");
        }
        if(txtNumero.getText().equals("")){
            txtNumero.setForeground(new Color(153,153,153));
            txtNumero.setText("Número");
        }
    }//GEN-LAST:event_txtCedulaMousePressed

    private void txtNumeroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNumeroMousePressed
        // TODO add your handling code here:
        if(txtNombre.getText().equals("")){
            txtNombre.setForeground(new Color(153,153,153));
            txtNombre.setText("Nombre");
        }
        if(txtApellido.getText().equals("")){
            txtApellido.setForeground(new Color(153,153,153));
            txtApellido.setText("Apellido");
        }
        if(txtUsuario.getText().equals("")){
            txtUsuario.setForeground(new Color(153,153,153));
            txtUsuario.setText("Username");
        }
        if(txtCorreo.getText().equals("")){
            txtCorreo.setForeground(new Color(153,153,153));
            txtCorreo.setText("Correo");
        }
        if(txtCedula.getText().equals("")){
            txtCedula.setForeground(new Color(153,153,153));
            txtCedula.setText("Cédula");
        }
        if(txtNumero.getText().equals("Número")){
            txtNumero.setForeground(Color.white);
            txtNumero.setText("");
        }
    }//GEN-LAST:event_txtNumeroMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonEditarFoto;
    public javax.swing.JButton botonGestionarUsuarios;
    public javax.swing.JButton botonGuardar;
    public javax.swing.JButton botonReestablecer;
    public javax.swing.JComboBox<String> comboBoxMunicipio;
    public javax.swing.JComboBox<String> comboBoxSexo;
    public javax.swing.JLabel fondoBotonGestion;
    public javax.swing.JLabel fondoBotonGuardar;
    public javax.swing.JLabel fondoEditarFoto;
    public javax.swing.JLabel fotoPerfil;
    private javax.swing.JLabel labelApellido;
    private javax.swing.JLabel labelCedula;
    private javax.swing.JLabel labelCorreo;
    private javax.swing.JLabel labelEdad;
    private javax.swing.JLabel labelInputApellido;
    private javax.swing.JLabel labelInputCedula;
    private javax.swing.JLabel labelInputCorreo;
    private javax.swing.JLabel labelInputEdad;
    private javax.swing.JLabel labelInputMunicipio;
    private javax.swing.JLabel labelInputNombre;
    private javax.swing.JLabel labelInputNumero;
    private javax.swing.JLabel labelInputSexo;
    private javax.swing.JLabel labelInputUsuario;
    private javax.swing.JLabel labelMunicipio;
    private javax.swing.JLabel labelNombre;
    public javax.swing.JLabel labelNombreUsuario;
    private javax.swing.JLabel labelNumero;
    public javax.swing.JLabel labelRol;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JLabel labelUsuario;
    public javax.swing.JSpinner spinnerEdad;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtCedula;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtNumero;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
