
package modelos;

import static idnum.Idnum.conexion;
import java.sql.Blob;
import java.awt.Image;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;

public class Literal implements DatabaseAble{
     
    int id_literal;
    
    String tipo_literal;
    
    String caracter;
    
    Blob imagen;
    
    String ruta;

    
    public Literal(int id_literal) {
        this.id_literal = id_literal;
    }

    
    // Constructor para ingresos
    public Literal(String valor, boolean isImage) {
        if (isImage){
        
            this.ruta = valor;
            this.tipo_literal = "IMAGEN";
            
        } else {
            
            this.caracter = valor;
            this.tipo_literal = "CARACTER";
            
        }

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
        Literal [] literales = new Literal[200];
        Literal auxiliar;
        
        ResultSet rs;
        
        String sentencia = "SELECT id_literal as Codigo, imagen, caracter, tipo FROM literal";
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            int contador = 0;
            
            while(rs.next()){
                
                if(rs.getString("tipo").equals("IMAGEN"))
                    auxiliar = new Literal(rs.getInt("Codigo"), rs.getBlob("imagen"));
                else
                    auxiliar = new Literal(rs.getInt("Codigo"), rs.getString("caracter"));
                literales[contador] = auxiliar;
                contador++;
            }
            
            rs.close();
            
            conexion.cerrar_conexionBD();
            
            return literales;
        }catch(Exception ex){
            System.out.println("Literal - getLiterales: "+ex);
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
    
        String sentencia;
        
        if(caracter != null)
            sentencia = "INSERT INTO literal(tipo, caracter) "
                + "VALUES('"+getTipo_literal()+"','"+getCaracter()+"')";
        else
            sentencia = "INSERT INTO literal(tipo, imagen) "
                + "VALUES('"+getTipo_literal()+"',?)";

       try{
            
            conexion.conectaBD();
            if(caracter != null){
                
                idnum.Idnum.conexion.actualizaBD(sentencia);
                
            } else {
            
                PreparedStatement statement = idnum.Idnum.conexion.get_conexion().prepareStatement(sentencia);
            
                statement.setBlob(1, new FileInputStream(ruta));
                statement.executeUpdate();
                
            }

            conexion.cerrar_conexionBD();

        }catch(Exception ex){
            System.out.println("Literal - ingresarbd: "+ex);
        }
        
    }

    @Override
    public void actualizarBD() {
       
    }

    @Override
    public boolean borrarBD() {
        String sentencia = "DELETE FROM literal WHERE id_literal ="+getId_literal()+"";
       
       try{
            
            conexion.conectaBD();
            
            boolean resultado = idnum.Idnum.conexion.actualizaBD(sentencia);
            
            conexion.cerrar_conexionBD();
            
            return resultado;

        }catch(Exception ex){
            System.out.println("Literal - borrarbd: "+ex);
            return false;
        }
    }

    @Override
    public void consultarBD() {
        
        String sentencia = "SELECT * FROM literal WHERE id_literal = '"+getId_literal()+"' OR"
                + " caracter = '"+getCaracter()+"'";
        
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
}
