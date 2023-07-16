/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.modelusuario;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author Pedro Leal
 */
public class Gerente extends Persona{
    
    // Atributos de la clase Gerente
    private ImageIcon icono;
    private String username;
    private String clave;
    private boolean status;
    private String rol;
    private boolean resp [] = new boolean[4]; //Reservaciones, Empleados, Inventario, Menu

    // Constructor completo de Gerente con todos sus atributos y los que se extienden de Persona

    public Gerente(String username, String clave, boolean status, ImageIcon icono, String rol, String cedula, String nombre, String apellido, int edad, char sexo, String ubicacion, String correo, String telefono) {
        super(cedula, nombre, apellido, edad, sexo, ubicacion, correo, telefono);
        this.username = username;
        this.clave = clave;
        this.status = status;
        this.icono = icono;
        this.rol = rol;
    }

    public Gerente(String username, String clave, boolean status, ImageIcon icono, String rol) {
        this.username = username;
        this.clave = clave;
        this.status = status;
        this.icono = icono;
        this.rol = rol;
    }

    // Constructor para los Gerentes que se crean pero no han sido aceptados en el sistema
    public Gerente(String clave, boolean status, String rol, String correo, String nombre, String apellido) {
        super();
        this.username = "";
        this.clave = clave;
        this.status = status;
        icono = null;
        this.rol = rol;
        this.resp[0] = false;
        this.resp[1] = false;
        this.resp[2] = false;
        this.resp[3] = false;
        super.correo = correo;
        super.nombre = nombre;
        super.apellido = apellido;
    }
    
    // Constructor vacio para objetos que se puedan reutilizar
    public Gerente (){
        super();
        username = "";
        clave = "";
        status = false;
        icono = null;
        rol = "";
        resp[0] = false;
        resp[1] = false;
        resp[2] = false;
        resp[3] = false;
    }
    
    // Getters y Setters de los atributos
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean[] getResp() {
        return resp;
    }

    public void setResp(boolean[] resp) {
        this.resp = resp;
    }
    
    /*Recibe como parametro el usuario a buscar y la lista de usuarios
      en el sistema para comparar y encontrar*/
    public boolean existeUsuario (String user, ArrayList<Gerente> listaUser) {
        boolean existe = false;
        for (Gerente u: listaUser){
            if ((user.equals(u.getUsername())) || (user.equals(u.getCorreo()))){
                return true;
            }
        }
        return existe;
    }
    
    public boolean existeUsuario (String userA, String user, ArrayList<Gerente> listaUser) {
        boolean existe = false;
        for (Gerente u: listaUser){
            if (user.equals(u.getUsername())){
                if(!user.equals(userA) && !user.equals("")){
                    return true;
                }
            }
        }
        return existe;
    }
    
     /*Recibe como parametro el correo a buscar y la lista de usuarios
       en el sistema para comparar y encontrar si el correo ya esta en el sistema*/
    public boolean correoRepetido (String correo, ArrayList<Gerente> listaUser){
        boolean repetido = false;
        for(Gerente u: listaUser){
            if(correo.equals(u.getCorreo())){
                return true;
            }
        }
       
        return repetido;        
    }    
    
    public boolean correoRepetido (String correoA, String correo, ArrayList<Gerente> listaUser){
        boolean repetido = false;
        for(Gerente u: listaUser){
            if(correo.equals(u.getCorreo())){
                if(!correo.equals(correoA) && !correo.equals("")){
                    return true;
                }
            }
        }
       
        return repetido;        
    }  
    
     
     /*Metodo usado en iniciar sesion, donde busca usuario ingresado en el sistema
       y compara si la contrasena introducida es igual a la guardado del usuario */
    public boolean claveCorrecta (String user, String clave, ArrayList<Gerente> listaUser){
        boolean correcto = false;
        
        for(Gerente u: listaUser){
            if(user.equals(u.getUsername()) || user.equals(u.getCorreo())){
                if(clave.equals(u.getClave())){
                        
                    return true;
                }
            }   
        }
        return correcto;
    } 
     
    /* Compara si una cedula introducida pertenece a uno de los Gerentes del sistema
       para efectos de verificar repeticion */  
    public boolean cedulaRepetida (ArrayList<Gerente> listaUser, String c) {
        boolean repetido = false;
        for(Gerente u: listaUser){
            if(c.equals(u.getCedula())){
                return true;
            }
        }
        return repetido;        
    }
    
    public boolean cedulaRepetida (String cA, ArrayList<Gerente> listaUser, String c) {
        boolean repetido = false;
        for(Gerente u: listaUser){
            if(c.equals(u.getCedula())){
                if(!c.equals(cA) && !c.equals("")){
                    return true;
                }
            }
        }
        return repetido;        
    }   
    
     /*valida que los caracteres que ingrese el usuario que use el sistema
       esten dentro de los parametros dado en el patron (regex) que se usa
       y si posee ciertos caracteres de la (Tabla ASCII)*/
    public boolean validarNombreUsuario (String usuario) {
        if(usuario.matches("[a-z0-9]{4,10}")){
            return true;
        } else{
            // Verifica tamano del String nombre de usuario
            if (usuario.length()>=9){
                JOptionPane.showMessageDialog(null, "Nombre de usuario inválido.Demasiado largo. \nIntente un rango entre [4-10] caracteres", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }else{
                if (usuario.length()<=3){
                    JOptionPane.showMessageDialog(null, "Nombre de usuario inválido.Demasiado corto. \nIntente un rango entre [4-10] caracteres", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {    
                    int i,as;
                    Character a;
                    for (i=0; usuario.length()>i;i++){
                        a= usuario.charAt(i);
                        as= (int)a;
                        // Si posee caracteres especiales
                        if(((as>=91 && 96>=as) ||  (as>=33 && as<=47) || (as>=58 && 64>= as) || (as>=123 && 126>=as))==true ){
                            i= usuario.length();
                            JOptionPane.showMessageDialog(null, "Usuario inválido.\nContiene algún carácter especial", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                        // Si posee mayusculas
                        if((as>=65 && 90>=as)==true ){
                            i= usuario.length();
                            JOptionPane.showMessageDialog(null, "Usuario inválido.\nContiene mayúsculas", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
            return false;
        }
    }
    
    // Llama al resto de metodos de validacion y verificacion de datos del Gerente
    public boolean validarDatosUsuario(String user, String correo, String clave, String cedula, String nombre, int edad, 
                                        char sexo, String ubicacion, String apellido,
                                        ArrayList<Gerente> listaUser) {
        boolean correcto = false;   
        if (!existeUsuario(user,listaUser)){
            if (!correoRepetido(correo,listaUser)){
                if (!cedulaRepetida(listaUser,cedula)){
                    if (validarNombre(nombre) && validarApellido(apellido) && validarCedula(cedula)){
                        if (validarEdad(edad)){
                            if (validarCorreo(correo)&& validarNombreUsuario(user)){
                                correcto = true;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La edad debe estar comprendida entre 18 y 90 años", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cédula repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(null, "Correo repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nombre de usuario repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);       
        }
        return correcto;
    } 
    
// Llama al resto de metodos de validacion y verificacion de datos del Gerente
    public boolean validarDatosUsuario(String user, String correo, String cedula, String nombre, int edad, 
                                        char sexo, String ubicacion, String apellido, String telefono,
                                        ArrayList<Gerente> listaUser, String userA, String correoA, String cedulaA) {
        boolean correcto = false;
        if (!existeUsuario(userA,user,listaUser)){
            if (!correoRepetido(correoA, correo,listaUser)){
                if (!cedulaRepetida(cedulaA, listaUser,cedula)){
                    if (validarNombre(nombre) && validarApellido(apellido) && validarCedula(cedula) && validarNumeroDeTelefono(telefono)){
                        if (validarEdad(edad)){
                            if (validarCorreo(correo)&& validarNombreUsuario(user)){
                                correcto = true;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La edad debe estar comprendida entre 18 y 90 años", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cédula repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else{
                JOptionPane.showMessageDialog(null, "Correo repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nombre de usuario repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        return correcto;
    }
         
    //Valida todos los datos que el gerente proporciona al crear una cuenta en el sistema
    public boolean validarCrearCuenta(String nombre,String apellido,String correo,String clave,ArrayList<Gerente> listaUser){
        boolean correcto=false;
        if(!correoRepetido(correo,listaUser)){
            if (validarNombre(nombre) && validarApellido(apellido)){
                if (validarCorreo(correo)){
                    correcto = true;
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Correo repetido", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        return correcto;
    }
         
    // Retorna el status contrario al pasado como parametro
    /*public boolean cambiarStatus(boolean status){
        return !status;
    }*/
    
   // Busca un Gerente en el sistema mediante sus datos y verifica si su status es aceptado
    public boolean verificarStatus(String user,String clave,ArrayList<Gerente> listaUsuarios){
        for(Gerente u: listaUsuarios){
            if(user.equals(u.getUsername()) || user.equals(u.getCorreo())){
                if(clave.equals(u.getClave())){
                    if(u.isStatus()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    // Busca un Gerente en el sistema para el inicio de sesion, mediante su nombre de usuario o correo
    public Gerente buscarGerente (ArrayList<Gerente> listaGerentes, String user) {
        for(Gerente g: listaGerentes){
            if(g.getUsername().equals(user) || g.getCorreo().equals(user)){
                return g;
            }
        }
        return null;
    } 
    
    
    public void eliminarGerente(ArrayList<Gerente> listaGerentes, String user){
        Gerente ge = null;
        for(Gerente g: listaGerentes){
            if(g.getUsername().equals(user) || g.getCorreo().equals(user)){
                ge = g;
            }
        }
        listaGerentes.remove(ge);
    }
    
    public void modificarGerente(String claveAnterior, ArrayList<Gerente> listaGerentes, String username, ImageIcon icono, String cedula, String nombre, String apellido, int edad, char sexo, String ubicacion, String correo, String telefono) {
        for(Gerente g: listaGerentes){
            if(g.getUsername().equals(claveAnterior) || g.getCorreo().equals(claveAnterior)){
                g.setUsername(username);
                g.setIcono(icono);
                g.setCedula(cedula);
                g.setNombre(nombre);
                g.setApellido(apellido);
                g.setEdad(edad);
                g.setSexo(sexo);
                g.setUbicacion(ubicacion);
                g.setCorreo(correo);
                g.setTelefono(telefono);
            }
        }
    }
    
    public void modificarResp(String clave, ArrayList<Gerente> listaGerentes, boolean resp[]){
        for(Gerente g: listaGerentes){
            if(g.getUsername().equals(clave) || g.getCorreo().equals(clave)){
                g.setResp(resp);
            }
        }
    }
    
    public void modificarClave(String clave, ArrayList<Gerente> listaGerentes, String claveN){
        for(Gerente g: listaGerentes){
            if(g.getUsername().equals(clave) || g.getCorreo().equals(clave)){
                g.setClave(claveN);
            }
        }
    }
       
}
