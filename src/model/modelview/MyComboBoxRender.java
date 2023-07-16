/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelview;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author Estefany Torres
 */
public class MyComboBoxRender extends BasicComboBoxRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Aquí definimos los colores que queramos.
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        // Cambiamos color de fondo y letra del item del combo desplegado.
        if (isSelected) {
            this.setForeground(new Color(39,45,47));
            this.setBackground(new Color(254,114,76));
        } else {
            this.setBackground(new Color(230,231,235));
            this.setForeground(new Color(39,45,47));
        }
        // list dibuja el elemento visible del combo cuando no está desplegado.
        list.setSelectionBackground(new Color(86,92,94));
        list.setSelectionForeground(new Color(230,231,235));

        return this;
    }
}
