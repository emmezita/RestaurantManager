/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelusuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author emmez
 */
public abstract class Persona {
    
    //Atributos de la clase abstracta Persona
    protected String cedula;
    protected String nombre;
    protected String apellido;
    protected int edad;
    protected char sexo;
    protected String ubicacion;
    protected String correo;
    protected String telefono;

    //Constructor con todos los atributos como parametros
    public Persona(String cedula, String nombre, String apellido, int edad, char sexo, String ubicacion, String correo, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.ubicacion = ubicacion;
        this.correo = correo;
        this.telefono = telefono;
    }
    
    //Constructor por defecto
    public Persona() {
        cedula = "";
        nombre = "";
        apellido = "";
        edad = 0;
        sexo = 0;
        ubicacion = "";
        correo = "";
        telefono = "";
    }
    
    //Getter y Setter de los atributos

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
    
    //Metodo para validar el String Apellido
    public boolean validarApellido(String apellido) {
        String mensaje = "Apellido inválido.\n";
        return validarTexto(apellido, mensaje);
    }
    
    //Metodo para validar si el texto ingresado tiene letras o caracteres especiales
    public void validarCifra(String s, String mensaje) {
        int i,as;
        Character a;
        for (i=0; s.length()>i;i++){
            a= s.charAt(i);
            as= (int)a;
            if(((as>=91 && 96>=as) ||  (as>=33 && as<=47) || (as>=58 && 64>= as) || (as>=123 && 126>=as))==true ){
                mensaje = mensaje + "Contiene algún carácter especial";
                JOptionPane.showMessageDialog(null,mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            } 
            if((as>=65 && 90>=as) ||( as>=97 && 122>=as)){
                mensaje = mensaje + "Expresión alfanumérica";
                JOptionPane.showMessageDialog(null,mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }      
        }
    }
    
    //Metodo para validar el String Cedula mediante rangos predefinidos con el (Matches)
    public boolean validarCedula(String cedula) {
        String mensaje = "Cédula inválida.\n";
        if (!cedula.matches("[0-9]{7,8}")) {
            if (cedula.length()>8){
                JOptionPane.showMessageDialog(null, "Cédula inválida.\nNúmero demasiado largo.\n\nMáximo 8 dígitos\n\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (cedula.length()<7){
                JOptionPane.showMessageDialog(null, "Cédula inválida.\nNúmero demasiado corto.\n\nMínimo 7 dígitos\n\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else{
                validarCifra(cedula, mensaje);
            }
            return false;
        } else {
            return true;
        }
    }
    
    //Metodo para validar el String Telefono mediante rangos predefinidos con el (Matches)
    public boolean validarNumeroDeTelefono (String telefono) {
        String mensaje = "Número de teléfono inválido.\n";
        if(!telefono.matches("[0-9]{11}")){
            if (telefono.length()>=12){
                JOptionPane.showMessageDialog(null, "Número de teléfono inválido.\nDemasiado largo\nDebe contener 11 dígitos\n\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (telefono.length()<11){
                JOptionPane.showMessageDialog(null, "Numero de teléfono inválido.Demasiado corto\nDebe contener 11 dígitos\n\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                validarCifra(telefono, mensaje);
            }
            return false;
        } else {
            return true;
        }  
    }
    
    //Metodo para validar la edad
    public boolean validarEdad(int edad) { //Valida si la edad esta en el rango predefinido 
        return (edad>=18) && (edad<=90);
    }
    
    //Metodo para validar el String Correo
    public boolean validarCorreo (String correo) {
        //Establece un patrón
        Pattern pat = Pattern.compile("[A-Za-z0-9+_.-]+@(.+)$");
        //Verifica si ese patrón coincide
        Matcher mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El correo es inválido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    // _____METODO GENERAL PARA LLAMAR AL RESTO DE VALIDACIONES_____
    public boolean validarDatosPersona(String nombre, String apellido, String cedula, String correo, String telefono) {
        boolean correcto = false;
        if (validarNombre(nombre)&& validarApellido(apellido) && validarCedula(cedula) && validarCorreo(correo) && validarNumeroDeTelefono(telefono)){
            correcto = true;
        }
        return correcto;
    }    
    
}
