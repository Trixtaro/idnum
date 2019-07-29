
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import modelos.Usuario;
import modelos.Validacion;
import vistas.Login_Vista;

public class Login_Controller implements ActionListener, KeyListener{
    
    Login_Vista vista;
    
    public Login_Controller(){
        this.vista = new Login_Vista();
        
        this.vista.boton_ingresar.addActionListener(this);
        this.vista.boton_salir.addActionListener(this);
        this.vista.radio_administrador.addActionListener(this);
        this.vista.radio_jugador.addActionListener(this);
        
        this.vista.txt_usuario.addKeyListener(this);
        this.vista.txt_clave.addKeyListener(this);
        
    }
    
    public void iniciar(){
        this.vista.setTitle("Sistema de identificación de numeros");
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == vista.radio_jugador){
            
            vista.txt_clave.setEditable(false);
            
        } else
        
        if(event.getSource() == vista.radio_administrador){
            
            vista.txt_clave.setEditable(true);
            
        } else
            
        if(event.getSource() == vista.boton_ingresar){
        
            Usuario usuario = new Usuario(vista.txt_usuario.getText(), vista.txt_clave.getText());
            
            if(vista.radio_administrador.isSelected()){
            
                if(usuario.isRegistered()){
                
                    vista.dispose();
                    
                    Contenido_Controlador contenido_controlador = new Contenido_Controlador();
                    contenido_controlador.iniciar();
                
                } else {
                
                    JOptionPane.showMessageDialog(vista, "El cedula y la contraseña no coinciden.", "Aviso", JOptionPane.WARNING_MESSAGE);
                
                }
            
            } else
                
            if(vista.radio_jugador.isSelected()){
                
                
                
            }
        
        }
        
        if(event.getSource() == vista.boton_salir){
        
            System.exit(0);
            
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
        if(ke.getSource() == vista.txt_usuario){
        
            if(Validacion.isNumber(ke.getKeyChar()) == false)
                ke.consume();
            
            if(Validacion.isMaxLength(vista.txt_usuario.getText(), 10))
                ke.consume();
            
        }
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
