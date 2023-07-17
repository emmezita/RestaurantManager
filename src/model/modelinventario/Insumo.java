/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelinventario;

import java.util.Date;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author Paola
 */
    
 public class Insumo {
     
    // Atributos de clase Insumo
    private String tipoInsumo;
    private int id;
    private String nombre;
    private String unidad;
    private double cantidad;
    private boolean vencido;
    private String proveedor;

    // Constructor de Insumos que provienen de la Base de Datos para agregarlos al inventario del sistema
    public Insumo(String tipoInsumo,int id, String nombre, String unidad, double cantidad, boolean vencido, String proveedor) {
        this.tipoInsumo = tipoInsumo;
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.vencido = vencido;
        this.proveedor = proveedor;
    }   
    
    // Constructor para un Insumo nuevo 
    public Insumo(String tipoInsumo, int id, String nombre, String unidad, String proveedor) {
        this.tipoInsumo = tipoInsumo;
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.proveedor = proveedor;
        vencido = false;
    }
    
    // Constructor vacio para objeto reutilizable
    public Insumo() {
        tipoInsumo = "";
        id = 0;
        nombre = "";
        unidad = "";
        cantidad = 0.0;
        proveedor = "";
        vencido = false;
    }
    
    // Getters y setters de clase Insumo

    public String getTipoInsumo() {
        return tipoInsumo;
    }

    public void setTipoInsumo(String tipoInsumo) {
        this.tipoInsumo = tipoInsumo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isVencido() {
        return vencido;
    }

    public void setVencido(boolean vencido) {
        this.vencido = vencido;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
    
    /*Método para validar si el nombre del insumo ingresado tiene números o caracteres especiales 
      usando valores de (Tabla ASCII) */
    public boolean validarInsumo(String nombre) { 
        int i,as;
        Character a;
        for (i=0; nombre.length()>i;i++){
            a= nombre.charAt(i);
            as= (int)a;
            // Si posee caracteres especiales
            if(((as>=91 && 96>=as) ||  (as>=33 && as<=47) || (as>=58 && 64>= as) || (as>=123 && 126>=as))==true ){
                JOptionPane.showMessageDialog(null, "Nombre de insumo inválido.\nContiene algún carácter especial", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            // Si posee numeros
            if((as>=48 && 57>=as)==true){
                JOptionPane.showMessageDialog(null, "Nombre de insumo inválido.\nContiene algún dígito", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }      
        }            
        return true;
    } 
    
}
