
package modelos;

import java.sql.Blob;
import static idnum.Idnum.conexion;
import java.sql.ResultSet;

public class Literal implements DatabaseAble{
     
    int id_literal;
    
    String tipo_literal;
    
    String caracter;
    
    Blob imagen;

    
    // Constructor para preguntas caracter
    public Literal(String caracter) {
        this.tipo_literal = "CARACTER";
        this.caracter = caracter;
    }
    // Constructor para preguntas imagenes
    public Literal(Blob imagen) {
        this.tipo_literal = "IMAGEN";
        this.imagen = imagen;
    }
    
    public Literal(int id_literal, Blob imagen){
        this.id_literal = id_literal;
        this.imagen = imagen;
        this.tipo_literal = "IMAGEN";
    }
    
    public Literal(int id_literal, String caracter){
        this.id_literal = id_literal;
        this.caracter = caracter;
        this.tipo_literal = "CARACTER";
    }
    
    public static Literal [] getLiterales(){
        Literal [] literales = new Literal[10];
        Literal auxiliar;
        
        ResultSet rs;
        
        String sentencia = "SELECT id_literal as Codigo, imagen FROM literal";
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            int contador = 0;
            
            while(rs.next()){
                auxiliar = new Literal(rs.getInt("Codigo"), rs.getBlob("imagen"));
                literales[contador] = auxiliar;
                contador++;
            }
            
            // conexion.cerrar_conexionBD();
            
            return literales;
        }catch(Exception ex){
            System.out.println(""+ex);
        }
        return null;
    
    }

    public int getId_literal() {
        return id_literal;
    }

    public void setId_literal(int id_literal) {
        this.id_literal = id_literal;
    }

    public String getTipo_literal() {
        return tipo_literal;
    }

    public void setTipo_literal(String tipo_literal) {
        this.tipo_literal = tipo_literal;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
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
    
}
