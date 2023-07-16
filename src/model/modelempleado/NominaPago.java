/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelempleado;

import java.util.ArrayList;

/**
 *
 * @author emmez
 */
public class NominaPago {
    private String fechaInicio;
    private String fechaCierre;
    private ArrayList<DatosPago> listaPagos;

    public NominaPago(String fechaInicio, String fechaCierre, ArrayList<DatosPago> listaPagos) {
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
        this.listaPagos = listaPagos;
    }

    public NominaPago() {
        fechaInicio = "";
        fechaCierre = "";
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public ArrayList<DatosPago> getListaPagos() {
        return listaPagos;
    }

    public void setListaPagos(ArrayList<DatosPago> listaPagos) {
        this.listaPagos = listaPagos;
    }
    
    public boolean nominaRepetida(ArrayList<NominaPago> listaNominas, String fechaInicio, String fechaCierre){
        for(NominaPago np: listaNominas){
            if(np.getFechaInicio().equals(fechaInicio) && np.getFechaCierre().equals(fechaCierre)){
                return true;
            }
        }
        return false;
    }
    
}
