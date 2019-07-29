
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import modelos.Jugador;
import modelos.Usuario;
import modelos.Validacion;
import vistas.Nuevo_Jugador_Vista;

public class Nuevo_Jugador_Controlador implements ActionListener, KeyListener{
    
    Nuevo_Jugador_Vista vista;
    
    Usuario usuario;
    
    public Nuevo_Jugador_Controlador(Usuario usuario){
        
        this.vista = new Nuevo_Jugador_Vista();
        
        this.usuario = usuario;
        
        this.vista.boton_registrar.addActionListener(this);
        this.vista.boton_regresar.addActionListener(this);
        
        this.vista.txt_nombres_jugador.addKeyListener(this);
        this.vista.txt_nombres_ayudante.addKeyListener(this);
        
        
    }
    
    public void iniciar(){
        
        vista.setTitle("Nuevo jugador");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == vista.boton_registrar){
            
            if(vista.txt_nombres_jugador.getText().equals("") || vista.txt_nombres_ayudante.getText().equals("")){
                JOptionPane.showMessageDialog(vista, "Llene todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        
            Jugador jugador = new Jugador(vista.combo_grado_autismo.getSelectedItem().toString(), 
                    vista.txt_nombres_jugador.getText(), vista.txt_nombres_ayudante.getText(), 
                    (int) vista.spinner_edad.getValue(), usuario);
            
            jugador.ingresarBD();
            
            //TODO: controlador de juego
            
        } else
            
        if(event.getSource() == vista.boton_regresar){
        
            vista.dispose();
            Login_Controller.login_Controller.vista.setVisible(true);
            
        }
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
        if(ke.getSource() == vista.txt_nombres_jugador || ke.getSource() == vista.txt_nombres_ayudante){
        
            if(Validacion.isLetterOrSpace(ke.getKeyChar()) == false)
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
