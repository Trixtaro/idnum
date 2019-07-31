
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
    
    public static Iniciar_Juego_Controlador controlador;
    
    Iniciar_Juego_Vista vista;
    
    Jugador jugador;
    Juego juego;

    int preguntas_respondidas;
    int max_preguntas;

    ArrayList <Contestacion> contestaciones;
    
    public Iniciar_Juego_Controlador(Jugador jugador, Juego juego){
        
        this.vista = new Iniciar_Juego_Vista();
        
        controlador = this;
        
        contestaciones = new ArrayList<>();
        
        this.jugador = jugador;
        this.juego = juego;
        
        preguntas_respondidas = 0;
        max_preguntas = getCantidadDePreguntas();
        
        this.vista.boton_empezar.addActionListener(this);
        this.vista.boton_salir.addActionListener(this);

    }
    
    public void iniciar(){
        
        this.vista.setTitle("JUGAR "+juego.getNombre());
        this.vista.etiqueta_n_preguntas.setText(0+" / "+max_preguntas);
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
    
    public int getCantidadDePreguntas (){
        
        int contador = 0;
        
        int i = 0;
        
        while(Pregunta.getPreguntas(juego.getContenido_1())[i] != null){
            
            contador++;
            i++;
        }
        
        i = 0;
        
        while(Pregunta.getPreguntas(juego.getContenido_2())[i] != null){
            
            contador++;
            i++;
            
        }
        
        i = 0;
        
        while(Pregunta.getPreguntas(juego.getContenido_3())[i] != null){
            
            contador++;
            i++;
            
        }
        
        return contador;
        
    }
    
    public void actualizarVista(){
        
        this.vista.etiqueta_n_preguntas.setText(preguntas_respondidas+" / "+max_preguntas);
        
        int contador = 0;
        
        int indice_auxiliar = preguntas_respondidas + 0;
        
        Pregunta [] preguntas1 = Pregunta.getPreguntas(juego.getContenido_1());
        Pregunta [] preguntas2 = Pregunta.getPreguntas(juego.getContenido_2());
        Pregunta [] preguntas3 = Pregunta.getPreguntas(juego.getContenido_3());
        
        while(preguntas1[contador] != null){

            if(contador == indice_auxiliar)
                return;
            
            contador++;
        }
        
        this.vista.check_contenido_1.setSelected(true);
        
        indice_auxiliar = indice_auxiliar - contador;
        
        contador = 0;
        
        while(preguntas2[contador] != null){
            
            if(contador == indice_auxiliar)
                return;
          
            contador++;
        }
        
        this.vista.check_contenido_2.setSelected(true);
        
        indice_auxiliar = indice_auxiliar - contador;
        
        contador = 0;
        
        while(preguntas3[contador] != null){
            
            if(contador == indice_auxiliar)
                return;
          
            contador++;
        }
        
        this.vista.check_contenido_3.setSelected(true);
       
    }
    
    public void terminar_prueba(){
    
        Jugador_Juego jugador_Juego = new Jugador_Juego(jugador, juego, null);
        jugador_Juego.ingresarBD();
        jugador_Juego.consultarBD();
        
        for(int i = 0; i < contestaciones.size(); i++){
            
            contestaciones.get(i).setFecha(jugador_Juego.getFecha());
            contestaciones.get(i).ingresarBD();
            
        }
        
    }
    
}
