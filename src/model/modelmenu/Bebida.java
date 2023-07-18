/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelmenu;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Bebida {
    
    private int ID;
    private String nombre;
    private String categoria;
    private String descripcion;

    public Bebida() {
    }

    public Bebida(int ID, String nombre, String categoria, String descripcion) {
        this.ID = ID;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean validarNombreBebida(String nombre) { 
        int i, as;
        Character a;
        for (i=0; nombre.length()>i; i++){
            a = nombre.charAt(i);
            as = (int) a;
            
            // Valida si contiene digitos
            if ((as >= 48 && 57 >= as) == true) {
                JOptionPane.showMessageDialog(null, "Nombre de la bebida inválido.\nContiene algún dígito", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }  
            
            // Valida si contiene algún caracter especial al chequear si no posee letras o espacios en blanco
            if ((as >= 65 && as <= 90) || (as >= 97 && as <= 122) || (as == 32)) {
                continue; // Si es una letra o un espacio en blanco, continuar con el siguiente carácter
            } else {
                JOptionPane.showMessageDialog(null, "Nombre de la bebida inválido.\nContiene algún carácter especial", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }      
        }            
        return true;
    }
    
    public boolean nombreBebidaRepetida(ArrayList<Bebida> listaBebidas, String nombre) {
        for (Bebida b : listaBebidas) {
            if (b.getNombre().equals(nombre)) {
                JOptionPane.showMessageDialog(null, "Nombre de la bebida repetido.\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
        return false;
    }
    
    // Busca una bebida mediante su nombre y devuelve su objeto
    public Bebida buscarBebida(int ID, ArrayList<Bebida> listaBebidas) {
    for (Bebida b : listaBebidas) {
        if(b.getID() == ID){
            return b;
        }
    }
    return null;
    }
    
    // Modifica la información de una bebida en una lista en memoria
    public void modificarBebida(String nombre, String categoria, String descripcion, ArrayList<Bebida> listaBebidas, int indicadorID) {
    for (Bebida b : listaBebidas) {
        if (b.getID() == indicadorID) {
            b.setNombre(nombre);
            b.setCategoria(categoria);
            b.setDescripcion(descripcion);
        }
    }
}
    
}
