
package modelos;

import static idnum.Idnum.conexion;
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
    
    public String getAciertos(){
        
        String sentencia = "SELECT pregunta.id_contenido AS Contenido, SUM(IF (pregunta.respuesta= contestacion.contestacion_objetiva,1,0)) AS Resultado FROM contestacion, pregunta, literal, jugador_juego"
                + " WHERE jugador_juego.fecha = contestacion.fecha AND pregunta.id_pregunta = contestacion.id_pregunta"
                + " AND jugador_juego.fecha = '2019-08-10 15:37:15'"
                + " AND  ((pregunta.literal_1 = literal.id_literal AND contestacion.contestacion_objetiva='A')"
                + " OR  (pregunta.literal_2 = literal.id_literal AND contestacion.contestacion_objetiva='B') "
                + " OR  (pregunta.literal_3 = literal.id_literal AND contestacion.contestacion_objetiva='C') "
                + " OR  (pregunta.literal_4 = literal.id_literal AND contestacion.contestacion_objetiva='D')) "
                + " GROUP BY pregunta.id_contenido ORDER BY contestacion.fecha ASC";
        
        ResultSet rs;
        
        try {
            
            idnum.Idnum.conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            String valor = "";
            
            while(rs.next()){
                
                valor += rs.getInt("Resultado")+"-";
                
            }
                
            
            idnum.Idnum.conexion.cerrar_conexionBD();
            
            return valor;
            
        } catch (Exception ex) {
            
            System.out.println("Jugador_Juego - getAciertos: "+ex);
            return null;
            
        }
    }
    
    public String [][] getRespuestasExamen(){
        
        String respuestas [][] = new String[100][4];
        
        String sentencia = "SELECT pregunta.id_contenido AS contenido, contestacion_objetiva AS Respuesta, IF (pregunta.respuesta= contestacion.contestacion_objetiva,'Correcta','Incorrecta') AS Resultado FROM contestacion, pregunta, literal\n" +
            "WHERE contestacion.id_pregunta = pregunta.id_pregunta\n" +
            "AND contestacion.fecha = '"+ getFecha() +"'" +
            "AND  ((pregunta.literal_1 = literal.id_literal AND contestacion.contestacion_objetiva='A')\n" +
            "OR  (pregunta.literal_2 = literal.id_literal AND contestacion.contestacion_objetiva='B')\n" +
            "OR  (pregunta.literal_3 = literal.id_literal AND contestacion.contestacion_objetiva='C')\n" +
            "OR  (pregunta.literal_4 = literal.id_literal AND contestacion.contestacion_objetiva='D'))";
        
        ResultSet rs;
        
        try {
            
            idnum.Idnum.conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            int contador = 0;
            
            int id_contenido = 0;
            String nombre_contenido = "Primer contenido";
            
            while(rs.next()){
                
                if(contador == 0){
                    
                    respuestas[contador][0] = "";
                    respuestas[contador][1] = "Respuesta";
                    respuestas[contador][2] = "Contenido";
                    respuestas[contador][3] = "Resultado";
                    
                    id_contenido = rs.getInt("contenido");
                    
                }
                
                if(id_contenido != rs.getInt("contenido")){
                    id_contenido = rs.getInt("contenido");
                    
                    switch(nombre_contenido){
                        
                        case "Primer contenido": nombre_contenido = "Segundo contenido";
                        break;
                        case "Segundo contenido": nombre_contenido = "Tercer contenido";
                        
                        
                    }
                }

                    respuestas[contador+1][0] = ""+(contador+1);
                    respuestas[contador+1][1] = rs.getString("Respuesta");
                    respuestas[contador+1][2] = nombre_contenido;
                    respuestas[contador+1][3] = rs.getString("Resultado");
                
                contador++;
                
            }
                     
            idnum.Idnum.conexion.cerrar_conexionBD();
            
            return respuestas;
            
        } catch (Exception ex) {
            
            System.out.println("Jugador_Juego - getAciertos: "+ex);
            return null;
            
        }
        
    }
    
    public static Jugador_Juego [] getJugador_Juegos(){
        Jugador_Juego [] jugador_juegos = new Jugador_Juego[20];
        
        ResultSet rs;
        
        String sentencia = "SELECT id_jugador, id_juego, fecha FROM jugador_juego";
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            int contador = 0;
            
            while(rs.next()){
      
                Jugador jugador = new Jugador(rs.getInt("id_jugador"));
                Juego juego = new Juego(rs.getInt("id_juego"));

                jugador.consultarBD();

                juego.consultarBD();

                jugador_juegos[contador] = new Jugador_Juego(jugador, juego, rs.getString("fecha"));

                contador++;
            }
            
            rs.close();
            
            conexion.cerrar_conexionBD();
            
            return jugador_juegos;
        }catch(Exception ex){
            System.out.println("Jugador_Juego - getJugador_Juegos: "+ex);
        }
        return null;
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
    
    public void consultarBD(String fecha) {
        
        String sentencia = "SELECT * FROM jugador_juego WHERE id_jugador = '"+getJugador().getId_jugador()+"'"
                + "AND id_juego = '"+getJuego().getId_juego()+"' AND fecha = '"+fecha+"' ORDER BY fecha DESC LIMIT 1";
        
        ResultSet rs;
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
           
            if(rs.next()){
                
                Jugador jugador_aux = new Jugador(rs.getInt("id_jugador"));
                Juego juego_aux = new Juego(rs.getInt("id_juego"));

                jugador_aux.consultarBD();

                juego_aux.consultarBD();
                
                this.setJugador(jugador_aux);
                this.setJuego(juego_aux);
    
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
