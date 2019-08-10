
package modelos;

import static idnum.Idnum.conexion;

public class Contestacion implements DatabaseAble{
    
    Pregunta pregunta;
    Juego juego;
    Jugador jugador;
    char contestacion_objetiva;
    String contestacion_escrita;
    String fecha;

    public Contestacion(Pregunta pregunta, Juego juego, Jugador jugador, char contestacion_objetiva, String contestacion_escrita) {
        this.pregunta = pregunta;
        this.juego = juego;
        this.jugador = jugador;
        this.contestacion_objetiva = contestacion_objetiva;
        this.contestacion_escrita = contestacion_escrita;
    }

    @Override
    public void ingresarBD() {
        
        String sentencia;
        
        if(getContestacion_objetiva() != 'X')
            sentencia = "INSERT INTO contestacion (id_pregunta, id_jugador, id_juego, contestacion_objetiva, fecha) "
                    + "VALUES ('"+getPregunta().getId_pregunta()+"','"+getJugador().getId_jugador()+"',"
                    + "'"+getJuego().getId_juego()+"','"+getContestacion_objetiva()+"','"+getFecha()+"')";
        else
            sentencia = "INSERT INTO contestacion (id_pregunta, id_jugador, id_juego, contestacion_escrita, fecha) "
                    + "VALUES ('"+getPregunta().getId_pregunta()+"','"+getJugador().getId_jugador()+"',"
                    + "'"+getJuego().getId_juego()+"','"+getContestacion_escrita()+"','"+getFecha()+"')";
        
        try{
            
            conexion.conectaBD();
            
            System.out.println("ingresar contestacion - fecha: "+getFecha());
            
            conexion.actualizaBD(sentencia);
            
            conexion.cerrar_conexionBD();

        }catch(Exception ex){
            System.out.println("Contestacion - ingresar: "+ex);
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
 
}
