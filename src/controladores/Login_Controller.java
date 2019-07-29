
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vistas.Login_Vista;

public class Login_Controller implements ActionListener{
    
    Login_Vista vista;
    
    public Login_Controller(){
        this.vista = new Login_Vista();
        this.vista.boton_ingresar.addActionListener(this);
        this.vista.boton_salir.addActionListener(this);
        this.vista.radio_administrador.addActionListener(this);
        this.vista.radio_jugador.addActionListener(this);
    }
    
    public void iniciar(){
        this.vista.setTitle("Sistema de identificación de numeros");
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
