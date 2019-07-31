
package modelos;

public class Contestacion implements DatabaseAble{
    
    Pregunta pregunta;
    Juego juego;
    Jugador jugador;
    char contestacion_objetiva;
    String contestacion_escrita;

    public Contestacion(Pregunta pregunta, Juego juego, Jugador jugador, char contestacion_objetiva, String contestacion_escrita) {
        this.pregunta = pregunta;
        this.juego = juego;
        this.jugador = jugador;
        this.contestacion_objetiva = contestacion_objetiva;
        this.contestacion_escrita = contestacion_escrita;
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

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public char getContestacion_objetiva() {
        return contestacion_objetiva;
    }

    public void setContestacion_objetiva(char contestacion_objetiva) {
        this.contestacion_objetiva = contestacion_objetiva;
    }

    public String getContestacion_escrita() {
        return contestacion_escrita;
    }

    public void setContestacion_escrita(String contestacion_escrita) {
        this.contestacion_escrita = contestacion_escrita;
    }
    
    
 
}
