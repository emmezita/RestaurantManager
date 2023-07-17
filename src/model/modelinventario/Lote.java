/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelinventario;

/**
 *
 * @author emmez
 */
public class Lote {
    private int idInsummo;
    private int idLote;
    private String nombre;
    private String tipo;
    private String unidad;
    private double cantidad;
    private String fechaVencimiento;

    public Lote(int idInsummo, int idLote, String nombre, String tipo, String unidad, double cantidad, String fechaVencimiento) {
        this.idInsummo = idInsummo;
        this.idLote = idLote;
        this.nombre = nombre;
        this.tipo = tipo;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
    }
    
    public Lote(){
        idInsummo = 0;
        idLote = 0;
        nombre = "";
        tipo = "";
        unidad = "";
        cantidad = 0.0;
        fechaVencimiento = "";
    }

    public int getIdInsummo() {
        return idInsummo;
    }

    public void setIdInsummo(int idInsummo) {
        this.idInsummo = idInsummo;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
    
    
}
