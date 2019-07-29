
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.Juego;
import modelos.Jugador;
import vistas.Iniciar_Juego_Vista;

public class Iniciar_Juego_Controlador implements ActionListener{
    
    Iniciar_Juego_Vista vista;
    
    Jugador jugador;
    Juego juego;
    
    public Iniciar_Juego_Controlador(Jugador jugador, Juego juego){
        
        this.vista = new Iniciar_Juego_Vista();
        
        this.jugador = jugador;
        this.juego = juego;
        
        this.vista.boton_empezar.addActionListener(this);
        this.vista.boton_salir.addActionListener(this);
        
    }
    
    public void iniciar(){
        
        this.vista.setTitle("JUGAR "+juego.getNombre());
        this.vista.setLocationRelativeTo(null);
        this.vista.etiqueta_nombre_juego.setText("JUEGO "+juego.getNombre());
        this.vista.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == vista.boton_empezar){
            
            //TODO: empezar juego
            
        } else
            
        if(event.getSource() == vista.boton_salir){
            
            vista.dispose();
            
            
        }
        
    }
    
}
