
package idnum;

import controladores.Contenido_Controlador;
import modelos.Conexion;

public class Idnum {
    
    public static Conexion conexion = new Conexion("localhost","idnumbd","root","");

    public static void main(String[] args) {
        
        Contenido_Controlador contenido_Controlador = new Contenido_Controlador();
        contenido_Controlador.iniciar();
    }
    
}
