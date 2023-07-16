/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelempleado;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.modelusuario.Persona;


  public class Empleado extends Persona {
      
    // Atributos de clase Empleado
    private ImageIcon icono;
    private String rol;
    private double sueldo;
    private Date fechaDeIngreso;
    private String numeroCuentaBancaria;
    private String banco;

    // Constructor de Empleado con atributos que se extienden de Persona
    public Empleado(String rol, double sueldo, Date fechaDeIngreso, String numeroCuentaBancaria, String banco, String cedula, String nombre, String apellido, int edad, char sexo, String ubicacion, String correo, String telefono, ImageIcon icono) {
        super(cedula, nombre, apellido, edad, sexo, ubicacion, correo,telefono);
        this.rol = rol;
        this.sueldo = sueldo;
        this.fechaDeIngreso = fechaDeIngreso;
        this.numeroCuentaBancaria = numeroCuentaBancaria;
        this.banco = banco;
        this.icono = icono;
    }
    
    // Constructor vacio para objetos reutilizables
    public Empleado() {
        rol="";
        sueldo=0;
        numeroCuentaBancaria = "";
        banco = "";
    }

    // Getters y setters de Empleado
    
    public ImageIcon getIcono() {
        return icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }
    
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Date getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(Date fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public String getNumeroCuentaBancaria() {
        return numeroCuentaBancaria;
    }

    public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
        this.numeroCuentaBancaria = numeroCuentaBancaria;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
    
    // Busca si ya existe un correo perteneciente a un cierto empleado
    public boolean correoRepetido (String correo, ArrayList<Empleado> listaEmpleados){ 
        boolean repetido = false;
        int i = 0;
        while (!repetido && i<listaEmpleados.size()){
            if (listaEmpleados.get(i).getCorreo().equals(correo)) repetido = true;
            i++;
        }
        return repetido;        
    } 
    
    public boolean correoRepetido (String correo, ArrayList<Empleado> listaEmpleados, String correoA){
        boolean repetido = false;
        int i = 0;
        while (!repetido && i<listaEmpleados.size()){
            if (listaEmpleados.get(i).getCorreo().equals(correo)) 
                if(!listaEmpleados.get(i).getCorreo().equals(correoA)){
                    repetido = true;
                }
            i++;
        }
        return repetido;        
    } 
    
    // Busca si ya existe una cedula perteneciente a un cierto empleado
    public boolean cedulaRepetida (ArrayList<Empleado> listaEmpleados, String c) {
        boolean repetido = false;
        int i = 0;
        while (!repetido && i<listaEmpleados.size()){
            if (listaEmpleados.get(i).getCedula().equals(c))repetido = true;
            i++;
        }
        return repetido;        
    }
    
    public boolean cedulaRepetida (ArrayList<Empleado> listaEmpleados, String c, String cA) {
        boolean repetido = false;
        int i = 0;
        while (!repetido && i<listaEmpleados.size()){
            if (listaEmpleados.get(i).getCedula().equals(c))
                if (!listaEmpleados.get(i).getCedula().equals(cA))
                    repetido = true;
            i++;
        }
        return repetido;        
    }
      
    /* Valida longitud de numero de cuenta bancaria y si posee ciertos
       caracteres usando valores de (Tabla ASCII) */
    public boolean validarNumeroCuentaBancaria(String numeroCuentaBancaria) { 
        // valida cantidad de digitos
        if (numeroCuentaBancaria.length() != 20){
            JOptionPane.showMessageDialog(null, "Numero de cuenta inválido.\nCantidad de dígitos incorrecto.\nDebe contener 20 dígitos\n\n", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        } else {
            int i,as;
            Character a;
            // valida que no exista ningun caracter 
            for (i=0; numeroCuentaBancaria.length()>i;i++){
                a= numeroCuentaBancaria.charAt(i);
                as= (int)a;
                // valida si no tiene letras
                if(((as>=65 && 90>=as) ||  (as>=97 && as<=122))==true){
                    JOptionPane.showMessageDialog(null, "Numero de cuenta inválido.\nContiene algún carácter alfabético", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return false;
                }   
                // valida si no tiene ningun caracter especial
                if(( (as>=48 && as<=57)==false) ){
                    JOptionPane.showMessageDialog(null, "Numero de cuenta inválido.\nContiene algún carácter especial", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return false;
                }     
            }      
        }
        return true;
    }  
    
    /* Metodo que llama al resto de validaciones y verificaciones creacion 
       de nuevos empleados */
    public boolean validarDatosEmpleado (String nombre, String apellido, String cedula, String correo, String telefono, String cuenta,
                                        ArrayList<Empleado> listaEmpleados) {
        boolean correcto = false;   
            if (!correoRepetido(correo,listaEmpleados)){
                if (!cedulaRepetida(listaEmpleados,cedula)){
                    if (validarNombre(nombre) && validarApellido(apellido) && validarCedula(cedula)){
                        if (validarCorreo(correo) && validarNumeroDeTelefono(telefono) && validarNumeroCuentaBancaria(cuenta)){
                            correcto = true;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cédula repetida", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(null, "Correo repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        return correcto;
    }
    
    public boolean validarDatosEmpleado (String nombre, String apellido, String cedula, String correo, String telefono, String cuenta,
                                        ArrayList<Empleado> listaEmpleados, String cedulaA, String correoA) {
        boolean correcto = false;   
            if (!correoRepetido(correo,listaEmpleados, correoA)){
                if (!cedulaRepetida(listaEmpleados,cedula, cedulaA)){
                    if (validarNombre(nombre) && validarApellido(apellido) && validarCedula(cedula)){
                        if (validarCorreo(correo) && validarNumeroDeTelefono(telefono) && validarNumeroCuentaBancaria(cuenta)){
                            correcto = true;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cédula repetida", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(null, "Correo repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        return correcto;
    }
      
    // Busca un empleado mediante su cedula y devuelve su objeto
    public Empleado buscarEmpleado (String cedula, ArrayList<Empleado> listaEmpleados) {
        for (Empleado e: listaEmpleados){
            if (e.getCedula().equals(cedula)){
                return e;
            }    
        }
        return null;        
    }
    
    public void modificarEmpleado (String rol, double sueldo, Date fechaDeIngreso, String numeroCuentaBancaria, String banco, String cedula, 
                                   String nombre, String apellido, int edad, char sexo, String ubicacion, String correo, String telefono, ImageIcon icono,
                                   ArrayList<Empleado> listaEmpleados, String cedulaA) {
        for (Empleado e: listaEmpleados){
            if (e.getCedula().equals(cedulaA)){
                e.setRol(rol);
                e.setSueldo(sueldo);
                e.setFechaDeIngreso(fechaDeIngreso);
                e.setNumeroCuentaBancaria(numeroCuentaBancaria);
                e.setBanco(banco);
                e.setCedula(cedula);
                e.setNombre(nombre);
                e.setApellido(apellido);
                e.setEdad(edad);
                e.setSexo(sexo);
                e.setUbicacion(ubicacion);
                e.setCorreo(correo);
                e.setTelefono(telefono);
                e.setIcono(icono);
            }    
        }
    }
    
    public void eliminarEmpleado (String cedula, ArrayList<Empleado> listaEmpleados){
        Empleado em = null;
        for (Empleado e: listaEmpleados){
            if (e.getCedula().equals(cedula)){
                em = e;
            }    
        }
        listaEmpleados.remove(em);          
    }
    
}
