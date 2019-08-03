
package idnum;

import controladores.Contenido_Controlador;
import controladores.Login_Controller;
import javax.swing.UIManager;
import modelos.Conexion;

public class Idnum {
    
    public static Conexion conexion = new Conexion("localhost","idnumbd","root","");

    public static void main(String[] args) {
        
        try{
            
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            
        } catch (Exception ex){
            System.out.println("No estas en Windows");
        }
        
        Login_Controller login_Controller = new Login_Controller();
        login_Controller.iniciar();
    }
    
}
