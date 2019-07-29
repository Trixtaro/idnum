
package modelos;


import static idnum.Idnum.conexion;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.PreparedStatement;

public class Pregunta implements DatabaseAble{
    
    int id_pregunta;
    Blob imagen;
    String ruta;
    
    Literal literal_A;
    Literal literal_B;
    Literal literal_C;
    Literal literal_D;
    
    String tipo;
    char respuesta;
    
    Contenido contenido;
    
    //Constructor para preguntas cerradas

    public Pregunta(int id_pregunta, Literal literal_A, Literal literal_B, Literal literal_C, Literal literal_D, char respuesta) {
        
        this.id_pregunta = id_pregunta;
        this.literal_A = literal_A;
        this.literal_B = literal_B;
        this.literal_C = literal_C;
        this.literal_D = literal_D;
        this.respuesta = respuesta;
        
    }
    
    //Constructor para preguntas abiertas

    public Pregunta(Blob imagen, String tipo, Contenido contenido) {
        this.imagen = imagen;
        this.tipo = tipo;
        this.contenido = contenido;
    }
    
    //Constructor para mostrar el listado de preguntas
    
    public Pregunta(int id_pregunta, Blob imagen, String tipo, Contenido contenido){
        this.id_pregunta = id_pregunta;
        this.imagen = imagen;
        this.tipo = tipo;
    }

    public Pregunta(String ruta, String tipo, Contenido contenido) {
        this.ruta = ruta;
        this.tipo = tipo;
        this.contenido = contenido;
    }
    
    public Pregunta(int id_pregunta){
        this.id_pregunta = id_pregunta;
    }

    public static Pregunta [] getPreguntas(Contenido contenido){
        Pregunta [] preguntas = new Pregunta[10];
        Pregunta auxiliar;
        
        ResultSet rs;
        
        String sentencia = "SELECT id_pregunta, imagen, tipo "
                + "FROM pregunta WHERE id_contenido = "+contenido.getId_contenido();
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            int contador = 0;
            
            while(rs.next()){
                auxiliar = new Pregunta(rs.getInt("id_pregunta"), rs.getBlob("imagen"), rs.getString("tipo"), contenido);
                preguntas[contador] = auxiliar;
                contador++;
            }
            
            conexion.cerrar_conexionBD();
            
            return preguntas;
        }catch(Exception ex){
            System.out.println(""+ex);
        }
        return null;
    }
    
    @Override
    public void ingresarBD(){
      String sentencia = "INSERT INTO pregunta "
              + "(imagen, literal_1, literal_2, literal_3, literal_4, "
              + "tipo, respuesta, id_contenido) "
              + "VALUES (?, '"+getLiteral_A().getId_literal()+"','"+getLiteral_B().getId_literal()+"','"
              +getLiteral_C().getId_literal()+"','"+getLiteral_D().getId_literal()+"','"
              +getTipo()+"','"+getRespuesta()+"','"+getContenido().getId_contenido()+"')";
       
       try{
            
            conexion.conectaBD();
            
            PreparedStatement statement = idnum.Idnum.conexion.get_conexion().prepareStatement(sentencia);
            
            statement.setBlob(1, new FileInputStream(ruta));
            statement.executeUpdate();
            
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
        String sentencia = "DELETE FROM pregunta WHERE id_pregunta="+getId_pregunta()+"";
       
       try{
            
            conexion.conectaBD();
            
            idnum.Idnum.conexion.actualizaBD(sentencia);
            
            conexion.cerrar_conexionBD();

        }catch(Exception ex){
            System.out.println(""+ex);
        }
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

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    public Literal getLiteral_A() {
        return literal_A;
    }

    public void setLiteral_A(Literal literal_A) {
        this.literal_A = literal_A;
    }

    public Literal getLiteral_B() {
        return literal_B;
    }

    public void setLiteral_B(Literal literal_B) {
        this.literal_B = literal_B;
    }

    public Literal getLiteral_C() {
        return literal_C;
    }

    public void setLiteral_C(Literal literal_C) {
        this.literal_C = literal_C;
    }

    public Literal getLiteral_D() {
        return literal_D;
    }

    public void setLiteral_D(Literal literal_D) {
        this.literal_D = literal_D;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public char getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(char respuesta) {
        this.respuesta = respuesta;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
    
    
    
}
