
package modelos;

public class Contestacion implements DatabaseAble{
    
    int id_pregunta;
    int id_juego;
    int id_jugador;
    String fecha;
    char contestacion_objetiva;
    String contestacion_escrita;

    public Contestacion(int id_juego, int id_jugador, String fecha, char contestacion_objetiva) {
        this.id_juego = id_juego;
        this.id_jugador = id_jugador;
        this.fecha = fecha;
        this.contestacion_objetiva = contestacion_objetiva;
    }

    public Contestacion(int id_juego, int id_jugador, String fecha, String contestacion_escrita) {
        this.id_juego = id_juego;
        this.id_jugador = id_jugador;
        this.fecha = fecha;
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

    public int getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(int id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
