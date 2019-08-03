
package modelos;

import static idnum.Idnum.conexion;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Jugador_Juego implements DatabaseAble{
    
    Jugador jugador;
    Juego juego;
    String fecha;

    public Jugador_Juego(Jugador jugador, Juego juego, String fecha) {
        this.jugador = jugador;
        this.juego = juego;
        this.fecha = fecha;
    }
    
    @Override
    public void ingresarBD() {
        
        String sentencia = "INSERT INTO jugador_juego (id_jugador, id_juego, fecha) "
                + "VALUES ('"+getJugador().getId_jugador()+"','"+getJuego().getId_juego()+"',NOW())";
       
       try{
            
            conexion.conectaBD();
            
            conexion.actualizaBD(sentencia);
            
            System.out.println("Llego");
            
            conexion.cerrar_conexionBD();

        }catch(Exception ex){
            System.out.println("Jugador_Juego juego - ingresar: "+ex);
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
        
        String sentencia = "SELECT * FROM jugador_juego WHERE id_jugador = '"+getJugador().getId_jugador()+"'"
                + "AND id_juego = '"+getJuego().getId_juego()+"' ORDER BY fecha DESC LIMIT 1";
        
        ResultSet rs;
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
           
            if(rs.next()){
                
                this.setFecha(rs.getString("fecha"));
                
            }
            
            conexion.cerrar_conexionBD();

        }catch(Exception ex){
            System.out.println("Jugador_Juego - consultar: "+ex);
        }
        
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego id_juego) {
        this.juego = juego;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
      
}
