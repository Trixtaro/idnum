
package modelos;


import static idnum.Idnum.conexion;
import java.awt.Image;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
    
    public Pregunta(int id_pregunta, Blob imagen, Literal literal_A, Literal literal_B, 
            Literal literal_C, Literal literal_D, char respuesta, String tipo, Contenido contenido) {
        
        this.id_pregunta = id_pregunta;
        this.imagen = imagen;
        this.literal_A = literal_A;
        this.literal_B = literal_B;
        this.literal_C = literal_C;
        this.literal_D = literal_D;
        this.respuesta = respuesta;
        this.tipo = tipo;
        this.contenido = contenido;
        
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
        Pregunta [] preguntas = new Pregunta[100];
        Pregunta auxiliar;
        
        ResultSet rs;
        
        String sentencia = "SELECT id_pregunta, imagen, literal_1, literal_2, literal_3, literal_4, "
                + "tipo, respuesta, id_contenido FROM pregunta WHERE id_contenido = '"+contenido.getId_contenido()+"'";
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            int contador = 0;
            
            while(rs.next()){
                
                Literal literal_1 = new Literal(rs.getInt("literal_1"));
                Literal literal_2 = new Literal(rs.getInt("literal_2"));
                Literal literal_3 = new Literal(rs.getInt("literal_3"));
                Literal literal_4 = new Literal(rs.getInt("literal_4"));
                
                literal_1.consultarBD();
                literal_2.consultarBD();
                literal_3.consultarBD();
                literal_4.consultarBD();
                
                auxiliar = new Pregunta(rs.getInt("id_pregunta"), 
                        rs.getBlob("imagen"),
                        literal_1,
                        literal_2,
                        literal_3,
                        literal_4,
                        rs.getString("respuesta").charAt(0),
                        rs.getString("tipo"), 
                        contenido);
                
                preguntas[contador] = auxiliar;
                contador++;
            }
            
            rs.close();
            
            conexion.cerrar_conexionBD();
            
            return preguntas;
        }catch(Exception ex){
            System.out.println("Pregunta - getPreguntas: "+ex);
        }
        return null;
    }
    
    public ImageIcon getImageAsIcon(){
        
        Image foto = null;
        ImageIcon icon;
        try{
            
            foto = javax.imageio.ImageIO.read(getImagen().getBinaryStream());
            foto = foto.getScaledInstance(96, 96, Image.SCALE_DEFAULT);                                
            icon = new ImageIcon(foto);
            
            return icon;
        
        } catch (Exception ex){
            
            System.out.println(""+ex);
            return null;
        }
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
    public boolean borrarBD() {
        String sentencia = "DELETE FROM pregunta WHERE id_pregunta="+getId_pregunta()+"";
       
       try{
            
            conexion.conectaBD();
            
            boolean resultado = idnum.Idnum.conexion.actualizaBD(sentencia);
            
            conexion.cerrar_conexionBD();
            
            return resultado;

        }catch(Exception ex){            
            System.out.println("Pregunta - borrarbd: "+ex);
            return false;
        }
    }

    @Override
    public void consultarBD() {
        
        ResultSet rs;
        
        String sentencia = "SELECT id_pregunta, imagen, literal_1, literal_2, literal_3, literal_4, "
                + "tipo, respuesta, id_contenido FROM pregunta WHERE id_pregunta = '"+getId_pregunta()+"'";
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            int contador = 0;
            
            if(rs.next()){
                
                Literal literal_1 = new Literal(rs.getInt("literal_1"));
                Literal literal_2 = new Literal(rs.getInt("literal_2"));
                Literal literal_3 = new Literal(rs.getInt("literal_3"));
                Literal literal_4 = new Literal(rs.getInt("literal_4"));
                
                literal_1.consultarBD();
                literal_2.consultarBD();
                literal_3.consultarBD();
                literal_4.consultarBD();
                
                setId_pregunta(rs.getInt("id_pregunta")); 
                setImagen(rs.getBlob("imagen"));
                setLiteral_A(literal_1);
                setLiteral_B(literal_2);
                setLiteral_C(literal_3);
                setLiteral_D(literal_4);
                setRespuesta(rs.getString("respuesta").charAt(0));
                setTipo(rs.getString("tipo"));

            }
            
            rs.close();
            
            conexion.cerrar_conexionBD();

        }catch(Exception ex){
            System.out.println("Pregunta - consultarbd: "+ex);
        }
        
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
