
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.Jugador;
import vistas.Juego_Vista;

public class Juego_Controller implements ActionListener{
    
    Juego_Vista vista;
    
    Jugador jugador;
    
    public Juego_Controller(){
        
        this.vista = new Juego_Vista();
        
        this.vista.boton_agregar.addActionListener(this);
        this.vista.boton_borrar.addActionListener(this);
        this.vista.boton_jugar.addActionListener(this);
        this.vista.boton_salir.addActionListener(this);
        
        this.vista.boton_jugar.setEnabled(false);
        
    }
    
    public Juego_Controller(Jugador jugador){
        
        this.vista = new Juego_Vista();
        
        this.jugador = jugador;
        
        this.vista.boton_agregar.addActionListener(this);
        this.vista.boton_borrar.addActionListener(this);
        this.vista.boton_jugar.addActionListener(this);
        this.vista.boton_salir.addActionListener(this);
        
        this.vista.boton_agregar.setEnabled(false);
        this.vista.boton_borrar.setEnabled(false);
        
    }
    
    public void iniciar(){
        
        if(jugador != null)
            vista.setTitle(jugador.getNombre_ayudante()+" - Edad: "+jugador.getEdad()+" - JUEGOS");
        else
            vista.setTitle("PANEL DE CONTROL DE JUEGOS");
        
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == vista.boton_salir){
        
            System.exit(0);
            
        }
        
    }
    
}
