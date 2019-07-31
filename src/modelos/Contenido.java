
package modelos;

import static idnum.Idnum.conexion;
import java.sql.ResultSet;

public class Contenido implements DatabaseAble{
    int id_contenido;
    String nombre_contenido;
    int n_preguntas;

    
    //Constructor para nombre de contenido
    public Contenido(String nombre_contenido) {
        this.nombre_contenido = nombre_contenido;
    }

    public Contenido(int id_contenido, String nombre_contenido, int n_preguntas) {
        this.id_contenido = id_contenido;
        this.nombre_contenido = nombre_contenido;
        this.n_preguntas = n_preguntas;
    }
    
    
    
    public static Contenido [] getContenidos(){
        
        Contenido [] contenidos = new Contenido[10];
        Contenido auxiliar;
        
        ResultSet rs;
        
        String sentencia = "SELECT contenido.id_contenido, nombre_contenido, "
                + "COUNT(id_pregunta) AS n_preguntas FROM contenido "
                + "LEFT JOIN pregunta ON contenido.id_contenido = pregunta.id_contenido "
                + "GROUP BY contenido.id_contenido ORDER BY id_contenido ASC";
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            int contador = 0;
            
            while(rs.next()){
                auxiliar = new Contenido(rs.getInt("id_contenido"), rs.getString("nombre_contenido"), rs.getInt("n_preguntas"));
                contenidos[contador] = auxiliar;
                contador++;
            }
            
            conexion.cerrar_conexionBD();
            
            return contenidos;
        }catch(Exception ex){
            System.out.println(""+ex);
        }
        return null;
    }
    
    public static Contenido getContenidoPorNombre(String nombre_contenido){
        
        int contador = 0;
        
        Contenido [] contenidos = Contenido.getContenidos();
        
        while(contenidos[contador] != null){
            
            if(contenidos[contador].getNombre_contenido().equals(nombre_contenido))
                return contenidos[contador];
            
            contador++;            
        }
        
        return null;
        
    }

    public Contenido(int id_contenido) {
        this.id_contenido = id_contenido;
    }
    
    
       
    @Override
    public void ingresarBD() {
       String sentencia = "INSERT INTO contenido(nombre_contenido) VALUES('"+getNombre_contenido()+"') ";
       
        System.out.println(""+sentencia);
       
       try{
            
            conexion.conectaBD();
            
            idnum.Idnum.conexion.actualizaBD(sentencia);
            
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
        
        String sentencia = "DELETE FROM contenido WHERE id_contenido="+getId_contenido()+"";
       
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
        
        ResultSet rs;
        
        String sentencia = "SELECT * FROM contenido WHERE id_contenido="+getId_contenido()+"";
       
       try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
            
            if(rs.next()){
                
                this.nombre_contenido = rs.getString("nombre_contenido");
                
            }
            
            rs.close();
            
            conexion.cerrar_conexionBD();

        }catch(Exception ex){
            System.out.println(""+ex);
        }
        
    }

    public int getId_contenido() {
        return id_contenido;
    }

    public void setId_contenido(int id_contenido) {
        this.id_contenido = id_contenido;
    }

    public String getNombre_contenido() {
        return nombre_contenido;
    }

    public void setNombre_contenido(String nombre_contenido) {
        this.nombre_contenido = nombre_contenido;
    }

    public int getN_preguntas() {
        return n_preguntas;
    }

    public void setN_preguntas(int n_preguntas) {
        this.n_preguntas = n_preguntas;
    }
    
    
    
    
}
