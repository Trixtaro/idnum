
package controladores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import modelos.Jugador;
import modelos.Usuario;
import modelos.Validacion;
import vistas.Login_Vista;

public class Login_Controller implements ActionListener, KeyListener{
    
    public static Login_Controller login_Controller;
    
    Login_Vista vista;
    
    public Login_Controller(){
        
        this.login_Controller = this;
        
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
        vista.getContentPane().setBackground(Color.decode("#fcf9ea"));
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
            
            if(vista.txt_usuario.getText().length() < 10){
                JOptionPane.showMessageDialog(vista, "La cédula debe tener 10 dígitos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
            Usuario usuario = new Usuario(vista.txt_usuario.getText(), null);
            
            if(vista.radio_administrador.isSelected()){
                
                usuario.setClave(vista.txt_clave.getText());
            
                if(usuario.isRegistered()){
                
                    vista.dispose();
                    
                    Juego_Controller juego_Controller = new Juego_Controller();
                    juego_Controller.iniciar();
                
                } else {
                
                    JOptionPane.showMessageDialog(vista, "La cédula y la contraseña no coinciden.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    vista.txt_clave.setText("");
                    
                }
            
            } else
                
            if(vista.radio_jugador.isSelected()){
                
                
                
                usuario.consultarBD();
                
                if(usuario.isPlayer()){
                    
                    vista.dispose();
                    
                    Jugador jugador = new Jugador(usuario);
                    jugador.consultarBD();
                    
                    Juego_Controller juego_Controller = new Juego_Controller(jugador);
                    juego_Controller.iniciar();
                
                } else {
                    
                    vista.setVisible(false);
                
                    Nuevo_Jugador_Controlador nuevo_Jugador_Controlador = new Nuevo_Jugador_Controlador(usuario);
                    nuevo_Jugador_Controlador.iniciar();
                
                }
                
            }
        
        } else
        
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
        
        if(ke.getSource() == vista.txt_usuario && vista.radio_administrador.isSelected()){
            
            if(ke.getKeyCode() == KeyEvent.VK_ENTER)
                vista.txt_clave.requestFocus();
            
        } else
            
        if(ke.getSource() == vista.txt_clave || vista.radio_jugador.isSelected()){
            
            if(ke.getKeyCode() == KeyEvent.VK_ENTER)
                this.actionPerformed(new ActionEvent(vista.boton_ingresar, 0, ""));
            
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
