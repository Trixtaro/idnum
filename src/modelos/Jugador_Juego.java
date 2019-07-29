
package modelos;

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
