/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelmenu;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author emmez
 */
public class Menu {
    
    private String nombre;
    private ArrayList<Plato> listaPlatos;
    private ArrayList<Bebida> listaBebidas;

    public Menu(String nombre, ArrayList<Plato> listaPlatos, ArrayList<Bebida> listaBebidas) {
        this.nombre = nombre;
        this.listaPlatos = listaPlatos;
        this.listaBebidas = listaBebidas;
    }

    public Menu() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Plato> getListaPlatos() {
        return listaPlatos;
    }

    public void setListaPlatos(ArrayList<Plato> listaPlatos) {
        this.listaPlatos = listaPlatos;
    }

    public ArrayList<Bebida> getListaBebidas() {
        return listaBebidas;
    }

    public void setListaBebidas(ArrayList<Bebida> listaBebidas) {
        this.listaBebidas = listaBebidas;
    }
    
    /*Metodo para validar si el texto ingresado tiene numeros o caracteres especiales
      utilizando los valores de la (Tabla ASCII)*/
    public boolean validarTexto(String s, String mensaje) { //Valida nombre 
        int i,as;
        Character a;
        for (i=0; s.length()>i;i++){
            a= s.charAt(i);
            as= (int)a;
            if(((as>=91 && 96>=as) ||  (as>=33 && as<=47) || (as>=58 && 64>= as) || (as>=123 && 126>=as))==true ){
                mensaje = mensaje + "Contiene algún carácter especial";
                JOptionPane.showMessageDialog(null,mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            } 
            if((as>=48 && 57>=as)==true){
                mensaje = mensaje + "Contiene algún dígito";
                JOptionPane.showMessageDialog(null,mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }       
        }            
        return true;
    } 
    
    //Metodo para validar el String Nombre
    public boolean validarNombre(String nombre) {
        String mensaje = "Nombre inválido.\n";
        return validarTexto(nombre, mensaje);
    }
    
    public boolean nombreRepetido(ArrayList<Menu> listaMenus, String nombre){
        for (Menu m : listaMenus) {
            if (m.getNombre().equals(nombre)) {
                JOptionPane.showMessageDialog(null, "Nombre del menu repetido.\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
        return false;
    }
    
}
