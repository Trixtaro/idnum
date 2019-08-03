
package modelos;

import static idnum.Idnum.conexion;
import java.sql.ResultSet;

public class Juego implements DatabaseAble{

    int id_juego;
    String nombre;
    Contenido contenido_1;
    Contenido contenido_2;
    Contenido contenido_3;
    
    int n_veces_jugado;

    public Juego(String nombre) {
        this.nombre = nombre;
    }

    public Juego(String nombre, Contenido contenido_1, Contenido contenido_2, Contenido contenido_3) {
        this.nombre = nombre;
        this.contenido_1 = contenido_1;
        this.contenido_2 = contenido_2;
        this.contenido_3 = contenido_3;
    }
    
    
    @Override
    public void ingresarBD() {

        String sentencia = "INSERT INTO juego(nombre, contenido_1, contenido_2, contenido_3) "
                + "VALUES('"+getNombre()+"','"+getContenido_1().getId_contenido()+"',"
                + "'"+getContenido_2().getId_contenido()+"',"
                + "'"+getContenido_3().getId_contenido()+"')";

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
    public boolean borrarBD() {
        return false;
    }

    @Override
    public void consultarBD() {
        
    }

    public boolean isRegistered(){
        
        String sentencia = "SELECT * FROM juego WHERE nombre = '"+getNombre()+"'";
        
        ResultSet rs;
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
           
            if(rs.next()){
                
                conexion.cerrar_conexionBD();
                return true;
                
            } else {
                
                conexion.cerrar_conexionBD();
                return false;
                
            }

        }catch(Exception ex){
            System.out.println(""+ex);
            return false;
        }
        
    }
    
    public static Juego [] getJuegos(){
        
        Juego [] contenidos = new Juego[10];
        Juego auxiliar;
        
        ResultSet rs;
        
        String sentencia = "SELECT juego.*, COUNT(jugador_juego.id_jugador) AS \"Veces jugado\" FROM juego "
                + "LEFT JOIN jugador_juego ON juego.id_juego = jugador_juego.id_juego "
                + "GROUP BY juego.id_juego ORDER BY juego.id_juego ASC";
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            int contador = 0;
            
            while(rs.next()){
                auxiliar = new Juego(rs.getString("nombre"), new Contenido(rs.getInt("contenido_1")), new Contenido(rs.getInt("contenido_2")), new Contenido(rs.getInt("contenido_3")));
                auxiliar.setId_juego(rs.getInt("id_juego"));
                auxiliar.setN_veces_jugado(rs.getInt("Veces jugado"));
                contenidos[contador] = auxiliar;
                contador++;
            }
            
            rs.close();
            
            conexion.cerrar_conexionBD();
            
            return contenidos;
        }catch(Exception ex){
            System.out.println(""+ex);
        }
        return null;
    }
    
    public static Juego getJuegoPorNombre(String nombre_juego){
        
        int contador = 0;
        
        Juego [] juegos = Juego.getJuegos();
        
        while(juegos[contador] != null){
            
            if(juegos[contador].getNombre().equals(nombre_juego))
                return juegos[contador];
            
            contador++;            
        }
        
        return null;
        
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Contenido getContenido_1() {
        return contenido_1;
    }

    public void setContenido_1(Contenido contenido_1) {
        this.contenido_1 = contenido_1;
    }

    public Contenido getContenido_2() {
        return contenido_2;
    }

    public void setContenido_2(Contenido contenido_2) {
        this.contenido_2 = contenido_2;
    }

    public Contenido getContenido_3() {
        return contenido_3;
    }

    public void setContenido_3(Contenido contenido_3) {
        this.contenido_3 = contenido_3;
    }

    public int getN_veces_jugado() {
        return n_veces_jugado;
    }

    public void setN_veces_jugado(int n_veces_jugador) {
        this.n_veces_jugado = n_veces_jugador;
    }
    
    
}
