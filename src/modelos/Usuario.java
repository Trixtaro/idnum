
package modelos;

import static idnum.Idnum.conexion;
import java.sql.ResultSet;

public class Usuario implements DatabaseAble {
    int id_usuario;
    String cedula;
    String clave;

    public Usuario(String cedula, String clave) {
        this.cedula = cedula;
        this.clave = clave;
    }

    public Usuario(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public void ingresarBD() {
        
        String sentencia = "INSERT INTO usuario(cedula) VALUES('"+getCedula()+"')";
        
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
    public boolean borrarBD() {
        return false;
    }

    @Override
    public void consultarBD() {
        
        String sentencia;
        
        if(clave != null)
            sentencia = "SELECT * FROM usuario WHERE cedula = '"+getCedula()+"' AND clave = SHA2('"+getClave()+"',256)";
        else
            sentencia = "SELECT * FROM usuario WHERE cedula = '"+getCedula()+"'";
        
        ResultSet rs;
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
           
            if(rs.next()){
                
                this.setId_usuario(rs.getInt("id_usuario"));
                this.setCedula(rs.getString("cedula"));
                
            }
            
            conexion.cerrar_conexionBD();

        }catch(Exception ex){
            System.out.println(""+ex);
        }
        
    }
    
    public boolean isRegistered(){
        
        String sentencia = "SELECT * FROM usuario WHERE cedula = '"+getCedula()+"' AND clave = SHA2('"+getClave()+"',256)";
        
        ResultSet rs;
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
           
            if(rs.next()){
                
                conexion.cerrar_conexionBD();
                return true;
                
            } else {
                
                conexion.cerrar_conexionBD();
                return false;
                
            }

        }catch(Exception ex){
            System.out.println(""+ex);
            return false;
        }
        
    }
    
    public boolean isPlayer(){
        
        String sentencia = "SELECT * FROM jugador WHERE id_usuario = '"+getId_usuario()+"'";
        
        ResultSet rs;
        
        try{
            
            conexion.conectaBD();
            
            rs = idnum.Idnum.conexion.consultaBD(sentencia);
           
            if(rs.next()){
                
                conexion.cerrar_conexionBD();
                return true;
                
            } else {
                
                conexion.cerrar_conexionBD();
                return false;
                
            }

        }catch(Exception ex){
            System.out.println(""+ex);
            return false;
        }
        
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
}
