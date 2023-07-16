/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelempleado;

/**
 *
 * @author emmez
 */
public class DatosPago {
    
    private String cedula;
    private String nombre;
    private String rol;
    private double sueldoDiario;
    private int diasTrabajo;
    private double netoACobrar;

    public DatosPago(String cedula, String nombre, String rol, double sueldoDiario, int diasTrabajo, double netoACobrar) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.rol = rol;
        this.sueldoDiario = sueldoDiario;
        this.diasTrabajo = diasTrabajo;
        this.netoACobrar = netoACobrar;
    }

    public DatosPago() {
        cedula = "";
        nombre = "";
        rol = "";
        sueldoDiario = 0.0;
        diasTrabajo = 0;
        netoACobrar = 0.0;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public double getSueldoDiario() {
        return sueldoDiario;
    }

    public void setSueldoDiario(double sueldoDiario) {
        this.sueldoDiario = sueldoDiario;
    }

    public int getDiasTrabajo() {
        return diasTrabajo;
    }

    public void setDiasTrabajo(int diasTrabajo) {
        this.diasTrabajo = diasTrabajo;
    }

    public double getNetoACobrar() {
        return netoACobrar;
    }

    public void setNetoACobrar(double netoACobrar) {
        this.netoACobrar = netoACobrar;
    }
    
    public boolean validarDiasTrabajo(int dias){
        return dias <= 7;
    }
    
    public double calcularNetoACobrar(int dias, double sueldo){
        return dias*sueldo;
    }
    
}
