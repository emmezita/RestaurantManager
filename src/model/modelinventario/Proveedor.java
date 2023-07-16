/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelinventario;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Proveedor {
    
    //Atributos
    private String nombre;
    private String rif;
    private String correo;
    private String direccion;
    private String telefono;

    public Proveedor() {
    }

    
    public Proveedor(String nombre, String rif, String correo, String direccion, String telefono) {
        this.nombre = nombre;
        this.rif = rif;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public boolean validarNombreProveedor(String nombre) { 
        int i,as;
        Character a;
        for (i=0; nombre.length()>i;i++){
            a= nombre.charAt(i);
            as= (int)a;
            
            // Valida si contiene digitos
            if((as>=48 && 57>=as)==true){
                JOptionPane.showMessageDialog(null, "Nombre de Proveedor inválido.\nContiene algun dígito", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }  
            // Valida si contiene algun caracter especial al chequear si no posee letras
            if(( (as>=65 && as<=90) ||  (as>=97 && as<=122) )==false ){
                JOptionPane.showMessageDialog(null, "Nombre de Proveedor inválido.\nContiene algun carácter especial", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }      
        }            
        return true;
    }    
    
    public boolean validarTelefono (String telefono) {
        if(telefono.matches("[0-9]{11}")){
            return true;
        } else{
            // Valida la cantidad de digitos
            if (telefono.length()>=12){
                JOptionPane.showMessageDialog(null, "Número de teléfono inválido.\nDemasiado largo\nDebe contener 11 dígitos\n\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }else{
                if (telefono.length()<11){
                    JOptionPane.showMessageDialog(null, "Numero de teléfono inválido.Demasiado corto\nDebe contener 11 dígitos\n\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {    
                    // Validar los tipos de caracteres que posea
                    int i,as;
                    Character a;
                    for (i=0; telefono.length()>i;i++){
                        a= telefono.charAt(i);
                        as= (int)a;
                        // Valida si contiene letras
                        if((as>=65 && 90>=as  ||  (as>=97 && as<=122) )==true ){
                            i= telefono.length();
                            JOptionPane.showMessageDialog(null, "Numero de teléfono.\nContiene letras", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                        // Valida si contiene algun caracter especial al chequear si no posee numeros
                        if(( (as>=48 && as<=57) )==false ){
                            i= telefono.length();
                            JOptionPane.showMessageDialog(null, "Numero de teléfono.\nContiene algún carácter especial", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
            return false;
        }
    }
    
    public boolean validarCorreo (String correo) {
        //Establece un patrón
        Pattern pat = Pattern.compile("[A-Za-z0-9+_.-]+@(.+)$");
        //Verifica si ese patrón coincide
        Matcher mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El correo es inválido.\n Debe tener un @ y un .\n\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    public boolean validarRif (String rif) {
        // Valida la cantidad de caracteres
        if ((rif.length()>=11) || (rif.length()<10)){
            JOptionPane.showMessageDialog(null, "Numero de Rif inválido.\nDebe empezar por V,J, E, P o G, seguido por 9 números", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;    
        } else {    
            // Validar los tipos de caracteres que posea
            int i,as;
            Character a;
            for (i=0; rif.length()>i;i++){
                // Verificar primer caracter
                if (i == 0){
                    a= rif.toUpperCase().charAt(i);
                    as= (int)a;
                    // Valida si solo contiene de primer caracter aquellos permitidos
                    if(( (as==69) || (as==71) || (as==74) || (as==80) || (as==86) )==false){
                        JOptionPane.showMessageDialog(null, "Número de Rif inválido.\nSolo debe contener como letra la V,J, E, P o G, al inicio", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                } else {
                    // Verificar resto de la cadena
                    a= rif.charAt(i);
                    as= (int)a;
                    // Valida si tiene letras
                    if((as>=65 && 90>=as  ||  (as>=97 && as<=122) )==true ){
                        JOptionPane.showMessageDialog(null, "Número de Rif inválido.\nContiene letras después de la primera letra", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // Valida si contiene algun caracter especial al chequear si no posee numeros
                    if(( (as>=48 && as<=57) )==false){
                        JOptionPane.showMessageDialog(null, "Número de Rif inválido.\nContiene algun carácter especial", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                }
            }
        }
        return true;
    }    
    
    public boolean validarDatosProveedor(String nombre, String rif, String correo, String telefono) {
        boolean correcto = false;
        if (validarNombreProveedor(nombre)&& validarRif(rif) && validarCorreo(correo) && validarTelefono(telefono)){
            correcto = true;
        }
        return correcto;
    }
    
    public boolean rifProveedorRepetido (ArrayList<Proveedor> listaProveedores, String rif) {
        for(Proveedor p: listaProveedores){
            if(p.getRif().equals(rif)){
                JOptionPane.showMessageDialog(null, "Número de Rif repetido.\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
        return false;
    }
    
    public boolean nombreProveedorRepetido (ArrayList<Proveedor> listaProveedores, String nombre) {
        for(Proveedor p: listaProveedores){
            if(p.getNombre().equals(nombre)){
                JOptionPane.showMessageDialog(null, "Nombre del proveedor repetido.\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
        return false;
    }
    
    public boolean verificarProveedorRepetido(ArrayList<Proveedor> listaProveedores, String rif,String nombre){
        boolean correcto = false;
        if(rifProveedorRepetido(listaProveedores,rif) || nombreProveedorRepetido(listaProveedores,nombre)){
        correcto = true;}
        return correcto;
    }
    
}
