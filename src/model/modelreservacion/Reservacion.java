/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.modelreservacion;
import model.modelreservacion.EventoPrivado;
import model.modelreservacion.EventoFamiliar;
import model.modelreservacion.EventoEjecutivo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */

public abstract class Reservacion {
    
    // Atributos de clase Reservacion
    protected String fechaDeReservacion;
    private int numeroAdultos;
    private int numeroNinos;
    private double costoTotal;
    private boolean pagado;
    private String metodoDePago;
    private int montoAdulto;
    private int montoNino;
    
    // Constructor de clase Reservacion con sus atributos
    public Reservacion(String fechaDeReservacion, int numeroAdultos, int numeroNinos, double costoTotal, 
                       boolean pagado, String metodoDePago, int montoAdulto, int montoNino) {
        this.fechaDeReservacion = fechaDeReservacion;
        this.numeroAdultos = numeroAdultos;
        this.numeroNinos = numeroNinos;
        this.costoTotal = costoTotal;
        this.pagado = pagado;
        this.metodoDePago = metodoDePago;
        this.montoAdulto = montoAdulto;
        this.montoNino = montoNino;
    }

    // Constructor vacio para objeto reutilizable
    public Reservacion() {
        fechaDeReservacion = null;
        numeroAdultos = 0;
        numeroNinos = 0;
        costoTotal = 0.0;
        pagado=false;
        metodoDePago = "";
        montoAdulto = 0;
        montoNino = 0;
    }
    
    // Getters y setters de clase Reservacion 
    public String getFechaDeReservacion() {
        return fechaDeReservacion;
    }

    public void setFechaDeReservacion(String fechaDeReservacion) {
        this.fechaDeReservacion = fechaDeReservacion;
    }

    public int getNumeroAdultos() {
        return numeroAdultos;
    }

    public void setNumeroAdultos(int numeroAdultos) {
        this.numeroAdultos = numeroAdultos;
    }

    public int getNumeroNinos() {
        return numeroNinos;
    }

    public void setNumeroNinos(int numeroNinos) {
        this.numeroNinos = numeroNinos;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public int getMontoAdulto() {
        return montoAdulto;
    }

    public void setMontoAdulto(int montoAdulto) {
        this.montoAdulto = montoAdulto;
    }

    public int getMontoNino() {
        return montoNino;
    }

    public void setMontoNino(int montoNino) {
        this.montoNino = montoNino;
    }    
     
    /* Calcula costo total de la reservacion mediante numero de adultos 
       y ninos y el costo de cada uno */
    public int calcularCostoReservacion( int numeroAdultos, int numeroNinos, int costoAdulto, int costoNino) {
        int montoTotal = ((numeroAdultos*costoAdulto)+(numeroNinos*costoNino));
        return montoTotal;
    }
    
    // Retorna true si el String pasado equivale a pago realizado
    public boolean conversionStringPagado(String p) {
        return p.equals("Si");
    }
    
    /* Calcula cantidad de personas reservadas para un cierto dia buscandolo
       en lista de reservaciones */
    public int calcularTotalPersonas(ArrayList<Reservacion> listaReservaciones, String fecha){
        int total = 0;
        for(Reservacion r: listaReservaciones){
            if(r.fechaDeReservacion.equals(fecha)){
                total = total + r.getNumeroNinos() + r.getNumeroAdultos();
            }
        }
        return total;
    }
    
    // Calcula costo total de reservaciones para un cierto dia 
    public int calcularTotalReservaciones(ArrayList<Reservacion> listaReservaciones, String fecha){
        int total = 0;
        for(Reservacion r: listaReservaciones){
            if(r.fechaDeReservacion.equals(fecha)){
                total++;
            }
        }
        return total;
    }
     
    // Valida si la capacidad maxima de una reservacion es menor o igual a la establecida por el local
    public boolean validarCantidadPersonas (int numeroNinos, int numeroAdultos){
        if((numeroNinos+numeroAdultos)<=100){
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Se excede la capacidad del local", "Error", 0);
            return false;
        }
    }
    
    // Valida si una reservacion general esta registrada para ciertos dias
    public boolean validarDia (Date fechaReserva){ 
        boolean correcto = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaReserva);
        int dia = calendar.get(Calendar.DAY_OF_WEEK);
        // Solo si esta entre jueves y sabado
        if (dia>=5 && dia<=7){
            correcto = true;
        }
        return correcto;
    }
    
    // Valida el cupo de un cierto dia para poder registrar una reservacion general
    public boolean validarCupo (int numeroNinos, int numeroAdultos, ArrayList<Reservacion> listaReservaciones, 
                                String fecha, int capacidad){
        boolean existe = false;
        int numeroTotal = numeroNinos + numeroAdultos;
        int cantidadTotal = 0;
        int desocupados;
        for(Reservacion r: listaReservaciones){
            // Calcula la cantidad de personas ingresadas para el dia
            if (r instanceof ReservacionGeneral reservacion) {
                if(reservacion.getFechaDeReservacion().equals(fecha)){
                    cantidadTotal = cantidadTotal + reservacion.getNumeroAdultos() + reservacion.getNumeroNinos();
                }
            // Verifica si ya el dia esta ocupado para un evento privado
            } else if (r instanceof EventoPrivado reservacion) {
                if(reservacion.getFechaDeReservacion().equals(fecha)){
                    JOptionPane.showMessageDialog(null, "No hay suficientes cupos para esa fecha", "Error", 0);
                    return false;
                }
            }            
        }
        /* Verifica si hay suficiente capacidad disponible para la reservacion general. Calculo respecto a la 
           capacidad maxima del restaurante, cantidad de personas de la reservacion nueva y cantidad de personas con 
           reservaciones ya registradas */ 
        desocupados = capacidad - numeroTotal - cantidadTotal;
        if (desocupados <0) {
            JOptionPane.showMessageDialog(null, "No hay suficientes cupos para esa fecha", "Error", 0);
        } else {
            existe = true;
        }
        return existe;
    }
    
    public boolean validarCupo (int numeroNinos, int numeroAdultos, ArrayList<Reservacion> listaReservaciones, 
                                String fecha, int capacidad, int actual, String fechaA){
        boolean existe = false;
        int numeroTotal = numeroNinos + numeroAdultos;
        int cantidadTotal = 0;
        int desocupados;
        for(Reservacion r: listaReservaciones){
            // Calcula la cantidad de personas ingresadas para el dia
            if (r instanceof ReservacionGeneral reservacion) {
                if(reservacion.getFechaDeReservacion().equals(fecha)){
                    cantidadTotal = cantidadTotal + reservacion.getNumeroAdultos() + reservacion.getNumeroNinos();
                }
            // Verifica si ya el dia esta ocupado para un evento privado
            } else if (r instanceof EventoPrivado reservacion) {
                if(reservacion.getFechaDeReservacion().equals(fecha)){
                    JOptionPane.showMessageDialog(null, "No hay suficientes cupos para esa fecha", "Error", 0);
                    return false;
                }
            }            
        }
        if(fecha.equals(fechaA)){
            desocupados = capacidad - numeroTotal - cantidadTotal + actual;
        } else {
            desocupados = capacidad - numeroTotal - cantidadTotal;
        }
        /* Verifica si hay suficiente capacidad disponible para la reservacion general. Calculo respecto a la 
           capacidad maxima del restaurante, cantidad de personas de la reservacion nueva y cantidad de personas con 
           reservaciones ya registradas */ 
        if (desocupados <0) {
            JOptionPane.showMessageDialog(null, "No hay suficientes cupos para esa fecha", "Error", 0);
        } else {
            existe = true;
        }
        return existe;
    }
    
    // Valida si el dia esta libre de reservaciones para poder registrar un evento privado
    public boolean validarCupo (ArrayList<Reservacion> listaReservaciones, String fecha){
        boolean existe = false;
        int cantidadTotal = 0;
        /* Busca la fecha de cierto dia y calcula en base a las personas 
           reservadas y la capacidad total del local */
        for(Reservacion r: listaReservaciones){
            if(r.getFechaDeReservacion().equals(fecha)){
                cantidadTotal = cantidadTotal + r.getNumeroAdultos() + r.getNumeroNinos();
             }
        }
        // Verifica si hay personas con reservacion para el dia
        if (cantidadTotal == 0 ){
            existe = true;
        } else{
            JOptionPane.showMessageDialog(null, "No hay disponibilidad completa para esa fecha", "Error", 0);
        }
        return existe;
    }
    
    public boolean validarCupo (ArrayList<Reservacion> listaReservaciones, String fecha, int actual, String fechaA){
        boolean existe = false;
        int cantidadTotal = 0;
        /* Busca la fecha de cierto dia y calcula en base a las personas 
           reservadas y la capacidad total del local */
        for(Reservacion r: listaReservaciones){
            if(r.getFechaDeReservacion().equals(fecha)){
                cantidadTotal = cantidadTotal + r.getNumeroAdultos() + r.getNumeroNinos();
             }
        }
        if(fecha.equals(fechaA)){
            existe = true;
        } else {
            if(cantidadTotal == 0){
                existe = true;
            } else {
                JOptionPane.showMessageDialog(null, "No hay disponibilidad completa para esa fecha", "Error", 0);
            }
        }
        return existe;
    }
    
    // Valida si un comensal ya posee reservacion para un mismo dia
    public boolean cedulaRepetidaDia (ArrayList<Reservacion> listaReservaciones, String fecha, String c) {
        boolean repetida = false;
        for(Reservacion r: listaReservaciones){
            /* Busca en lista si ya existe reservacion que coincida con la fecha 
               y la cedula del comensal que registra la reservacion */
            if (r instanceof ReservacionGeneral reservacion) {
                if(reservacion.getFechaDeReservacion().equals(fecha)){
                    if(reservacion.getCedula().equals(c)){
                        repetida = true;
                        JOptionPane.showMessageDialog(null, "Ya existe una reservación bajo esa cédula para ese día", "Error", 0);
                        break;
                    }
                }
            }
        }
        return repetida;
    }
    
    public boolean cedulaRepetidaDia (ArrayList<Reservacion> listaReservaciones, String fecha, String c, String fA) {
        boolean repetida = false;
        for(Reservacion r: listaReservaciones){
            /* Busca en lista si ya existe reservacion que coincida con la fecha 
               y la cedula del comensal que registra la reservacion */
            if (r instanceof ReservacionGeneral reservacion) {
                if(reservacion.getFechaDeReservacion().equals(fecha)){
                    if(reservacion.getCedula().equals(c)){
                        if(!fecha.equals(fA)){
                            repetida = true;
                            JOptionPane.showMessageDialog(null, "Ya existe una reservación bajo esa cédula para ese día", "Error", 0);
                            break; 
                        }
                    }
                }
            }
        }
        return repetida;
    } 

    /* Busca una reservacion ya registrada en el sistema mediante la fecha y 
       la cedula del comensal si es reservacion general o evento familiar, o el 
       rif de la empresa si es evento ejecutivo */
    public Reservacion buscarReservacion (ArrayList<Reservacion> listaReservaciones, String fecha, String c) {
        for(Reservacion r: listaReservaciones){
            // Si es reservacion general
            if (r instanceof ReservacionGeneral reservacion){
                if(reservacion.getCedula().equals(c) && reservacion.getFechaDeReservacion().equals(fecha)){
                    return reservacion;
                }
            }
            // Si es evento ejecutivo
            if (r instanceof EventoEjecutivo reservacion){
                if(reservacion.getRif().equals(c) && reservacion.getFechaDeReservacion().equals(fecha)){
                    return reservacion;
                }
            }
            // Si es evento familiar
            if (r instanceof EventoFamiliar reservacion){
                if(reservacion.getCedula().equals(c) && reservacion.getFechaDeReservacion().equals(fecha)){
                    return reservacion;
                }
            }
        }
        return null;
    }
    
    // Devuelve cantidad total de personas reservadas para un cierto dia
    public int cantidadPersonas (ArrayList<Reservacion> listaReservaciones, String fecha) {
        int cantidadTotal = 0;
        for(Reservacion r: listaReservaciones){
            if(r.getFechaDeReservacion().equals(fecha)){
                cantidadTotal =  cantidadTotal + r.getNumeroAdultos() + r.getNumeroNinos();
            }
        }
        return cantidadTotal;
    }
          
}
