/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelinventario;

import model.modelinventario.Insumo;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Paola
 */
public class Inventario {
    
    // Atributo para guardar lista de objetos Insumo en Inventario
    private ArrayList<Insumo> listaInsumos;
    
    /* Constructor de Inventario con lista de Insumos y atributos que posee 
       esta clase para cada objeto*/
    public Inventario(ArrayList<Insumo> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    // Constructor vacio para objetos reutilizables
    public Inventario() {
        listaInsumos = new ArrayList<>();
    }
    
    // Getters y setters de Inventario 
    
    public ArrayList<Insumo> getListaInsumos() {
        return listaInsumos;
    }

    public void setListaInsumos(ArrayList<Insumo> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }
    
    // Busca si ya existe un cierto insumo en Inventario mediante su ID
    public boolean iDRepetido (int id) {
        boolean repetido = false;
        int i = 0;
        while (!repetido && i<listaInsumos.size()){
            if (listaInsumos.get(i).getId() == id) repetido = true;
            i++;
        }        
        return repetido;
    }

    // Busca si ya existe un cierto insumo en Inventario mediante su nombre
    public boolean nombreRepetido (String nombre) {
        boolean repetido = false;
        int i = 0;
        while (!repetido && i<listaInsumos.size()){
            if (listaInsumos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                repetido = true;
                return repetido;
            }
            i++;
        }       
        return repetido;
    }
    
    // Agrega un objeto Insumo al Inventario
    public void agregarInsumo (Insumo i){
        listaInsumos.add(i);
    }
    
    // Busca y devuelve un cierto Insumo de Inventario mediante su ID
    public Insumo buscarInsumo (int id) {
        for (Insumo i: listaInsumos){
            if (i.getId() == id){
                return i;
            }    
        }
        return null;        
    }
    
    /* Busca en la lista de Insumos del Inventario cuales estan vencidos, y agrega 
       los datos de cada uno a un mensaje de advertencia */
    public void mandarMensajeInsumosVencidos () {
        String mensaje = "Los insumos que están vencidos son: \n\n";
        for (Insumo i: listaInsumos){
            if (i.isVencido()){
                mensaje = mensaje + "ID: " +  i.getId() + ", Nombre:" + i.getNombre() +"\n";
            }
        }
        mensaje = mensaje +"\n";
        JOptionPane.showMessageDialog(null,mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    /* Busca y actualiza en la lista de Insumos del Inventario los vencidos comparando 
       su fecha de vencimiento con la actual */
    /*public void revisarInsumosVencidos(Date fechaActual){
        for (Insumo i: listaInsumos){
            if (i.getFechaVencimiento().equals(fechaActual) || i.getFechaVencimiento().before(fechaActual)){
                i.setVencido(true);
            } else {
                i.setVencido(false);
            }
        }
    }*/
    
    // Busca si existe un Insumo vencido en el Inventario
    public boolean revisarInsumosVencidos(){
        for (Insumo i: listaInsumos){
            if (i.isVencido()){
                return true;
            }
        }
        return false;
    }
  
}
