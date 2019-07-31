
package modelos;

import java.sql.Blob;
import static idnum.Idnum.conexion;
import java.awt.Image;
import java.sql.ResultSet;
import javax.swing.ImageIcon;

public class Literal implements DatabaseAble{
     
    int id_literal;
    
    String tipo_literal;
    
    String caracter;
    
    Blob imagen;

    
    public Literal(int id_literal) {
        this.id_literal = id_literal;
    }
    
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
            
            rs.close();
            
            conexion.cerrar_conexionBD();
            
            return literales;
        }catch(Exception ex){
            System.out.println(""+ex);
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
        
        String sentencia = "SELECT * FROM literal WHERE id_literal = '"+getId_literal()+"'";
        
        ResultSet rs;
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
           
            if(rs.next()){
                
                this.setTipo_literal(rs.getString("tipo"));
                this.setCaracter(rs.getString("caracter"));
                this.setImagen(rs.getBlob("imagen"));
                
            }
            
            conexion.cerrar_conexionBD();

        }catch(Exception ex){
            System.out.println("Literal - consultarBD: "+ex);
        }
        
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
    
}
