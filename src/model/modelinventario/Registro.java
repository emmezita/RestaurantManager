/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelinventario;

import java.util.ArrayList;

/**
 *
 * @author emmez
 */
public class Registro {
    private String fechaRegistro;
    private String responsable;
    private String documentoIdentidad;
    private ArrayList<Lote> listaLotes;

    public Registro(String fechaRegistro, String responsable, String documentoIdentidad, ArrayList<Lote> listaLotes) {
        this.fechaRegistro = fechaRegistro;
        this.responsable = responsable;
        this.documentoIdentidad = documentoIdentidad;
        this.listaLotes = listaLotes;
    }
    
    public Registro(){
        fechaRegistro = "";
        responsable = "";
        documentoIdentidad = "";
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public ArrayList<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(ArrayList<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }
    
    
    
}
