
package modelos;

import static idnum.Idnum.conexion;

public class Jugador implements DatabaseAble{
    
    int id_jugador;
    String grado;
    String nombre_jugador;
    String nombre_ayudante;
    int edad;
    Usuario usuario;

    public Jugador(String grado, String nombre_jugador, String nombre_ayudante, int edad, Usuario usuario) {
        this.grado = grado;
        this.nombre_jugador = nombre_jugador;
        this.nombre_ayudante = nombre_ayudante;
        this.edad = edad;
        this.usuario = usuario;
    }
    
    
    
    
    @Override
    public void ingresarBD() {
        
        if(usuario.isRegistered() == false){
                usuario.ingresarBD();
                usuario.consultarBD();
        }
        
        String sentencia = "INSERT INTO jugador(grado, nombres, nombres_ayudante, edad, id_usuario) "
                + "VALUES('"+getGrado()+"','"+getNombre_jugador()+"','"+getNombre_ayudante()+"',"
                + "'"+getEdad()+"','"+getUsuario().getId_usuario()+"')";
        
        System.out.println(""+sentencia);

       try{
            
            conexion.conectaBD();
            
            
            
            idnum.Idnum.conexion.actualizaBD(sentencia);
            
            conexion.cerrar_conexionBD();

        }catch(Exception ex){
            System.out.println(""+ex);
        }
        
    }

    @Override
    public void actualizarBD() {
        
    }

    @Override
    public void borrarBD() {
        
    }

    @Override
    public void consultarBD() {
       
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getNombre_jugador() {
        return nombre_jugador;
    }

    public void setNombre_jugador(String nombre_jugador) {
        this.nombre_jugador = nombre_jugador;
    }

    public String getNombre_ayudante() {
        return nombre_ayudante;
    }

    public void setNombre_ayudante(String nombre_ayudante) {
        this.nombre_ayudante = nombre_ayudante;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
