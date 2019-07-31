
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelos.Contestacion;
import modelos.Juego;
import modelos.Jugador;
import modelos.Jugador_Juego;
import modelos.Pregunta;
import vistas.Iniciar_Juego_Vista;

public class Iniciar_Juego_Controlador implements ActionListener{
    
    Iniciar_Juego_Vista vista;
    
    Jugador jugador;
    Juego juego;

    
    public static ArrayList <Contestacion> contestaciones;
    
    public Iniciar_Juego_Controlador(Jugador jugador, Juego juego){
        
        this.vista = new Iniciar_Juego_Vista();
        
        contestaciones = new ArrayList<>();
        
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
            
            Jugador_Juego jugador_juego = new Jugador_Juego(jugador, juego, null);
            
            int contador = 0;
            
            Responder_Pregunta_Controlador responder_Pregunta_Controlador = new Responder_Pregunta_Controlador(jugador, juego, 0);
            responder_Pregunta_Controlador.iniciar();
            
        } else
            
        if(event.getSource() == vista.boton_salir){
            
            vista.dispose();
            Juego_Controller.juego_Controller.vista.setVisible(true);
            
        }
        
    }
    
}
