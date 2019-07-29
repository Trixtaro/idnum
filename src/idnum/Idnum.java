
package idnum;

import controladores.Contenido_Controlador;
import controladores.Login_Controller;
import modelos.Conexion;

public class Idnum {
    
    public static Conexion conexion = new Conexion("localhost","idnumbd","root","");

    public static void main(String[] args) {
        
        Login_Controller login_Controller = new Login_Controller();
        login_Controller.iniciar();
    }
    
}
