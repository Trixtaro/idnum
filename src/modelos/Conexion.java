
package modelos;


import java.sql.*;
import java.sql.ResultSet;
public class Conexion {
    
    private final String host;
    private final String bd;
    private final String userbd;
    private final String passwdbd;
    
    private Connection conexion;

    public Conexion(String host, String bd, String userbd, String passwdbd) {
        this.host = host;
        this.bd = bd;
        this.userbd = userbd;
        this.passwdbd = passwdbd;
    }

    public String getHost() {
        return host;
    }

    public String getBd() {
        return bd;
    }

    public String getUserbd() {
        return userbd;
    }

    public String getPasswdbd() {
        return passwdbd;
    }
    
    public Connection get_conexion(){
        return conexion;
    }
    
    public void conectaBD() throws Exception{
        try {
            Driver driver = (Driver)
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
            String cadena="jdbc:mysql://"+getHost()+"/"+getBd();
            conexion=DriverManager.getConnection(cadena,getUserbd(),getPasswdbd());
            //System.out.println("la conexion es exitosa.....");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la BD"+e.toString());
        }
    }
    public ResultSet consultaBD(String sql) throws Exception{
        ResultSet cursor;
        Statement stm=conexion.createStatement();
        cursor=stm.executeQuery(sql);
        return cursor;
    }
    public void actualizaBD(String sql) throws Exception{
        try {
            Statement stm=conexion.createStatement();
            stm.executeUpdate(sql);
            //System.out.println("Transaccion exitosa !commit!");
        } catch (Exception e) {
            System.out.println("Error en la transaccion !Rollback!"+e.toString());
        }
    }
    public void cerrar_conexionBD()throws Exception{
        conexion.close();
    }
    
    public boolean estaConectado() throws Exception{
        if(conexion.isValid(0)){
            return true;
        } else
            return false;
    }
    
}
