
package controladores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelos.Contestacion;
import modelos.Juego;
import modelos.Jugador;
import modelos.Pregunta;
import vistas.Responder_Pregunta_Vista;

public class Responder_Pregunta_Controlador implements ActionListener, MouseListener{
    
    Responder_Pregunta_Vista vista;
    
    Juego juego;
    Jugador jugador;
    
    int indice;
    
    public Responder_Pregunta_Controlador(Jugador jugador, Juego juego, int indice){
        
        this.vista = new Responder_Pregunta_Vista();
        
      
        this.indice = indice;
        this.juego = juego;
        this.jugador = jugador;
        
        this.vista.etiqueta_a.addMouseListener(this);
        this.vista.etiqueta_b.addMouseListener(this);
        this.vista.etiqueta_c.addMouseListener(this);
        this.vista.etiqueta_d.addMouseListener(this);
        
        this.vista.boton_salir.addActionListener(this);
        
    }
    
    public void iniciar(){
        
        if(getPregunta() == null){
            
            JOptionPane.showMessageDialog(null, "Prueba finalizada");
            Iniciar_Juego_Controlador.controlador.terminar_prueba();
            return;
            
        }
        
        Pregunta pregunta = getPregunta();
        
        pregunta.getContenido().consultarBD();
        
        vista.setTitle(pregunta.getContenido().getNombre_contenido()+" - Pregunta "+(indice+1));
        
        vista.etiqueta_pregunta.setIcon(getPregunta().getImageAsIcon());
        
        if(pregunta.getLiteral_A().getTipo_literal().equals("IMAGEN")){
            
            this.vista.etiqueta_a.setIcon(pregunta.getLiteral_A().getImageAsIcon());
            this.vista.etiqueta_b.setIcon(pregunta.getLiteral_B().getImageAsIcon());
            this.vista.etiqueta_c.setIcon(pregunta.getLiteral_C().getImageAsIcon());
            this.vista.etiqueta_d.setIcon(pregunta.getLiteral_D().getImageAsIcon());
            
        } else {
            
            this.vista.etiqueta_a.setText(pregunta.getLiteral_A().getCaracter());
            this.vista.etiqueta_b.setText(pregunta.getLiteral_B().getCaracter());
            this.vista.etiqueta_c.setText(pregunta.getLiteral_C().getCaracter());
            this.vista.etiqueta_d.setText(pregunta.getLiteral_D().getCaracter());
            
        }
        
        vista.setLocationRelativeTo(null);
        vista.getContentPane().setBackground(Color.decode("#fcf9ea"));
        vista.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        vista.dispose();
        
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        
        Contestacion contestacion;
        
        if(event.getSource() == vista.etiqueta_a){
        
            contestacion = new Contestacion(getPregunta(), juego, jugador, 'A', null);
            
        } else
            
        if(event.getSource() == vista.etiqueta_b){
            
            contestacion = new Contestacion(getPregunta(), juego, jugador, 'B', null);
            
        } else
            
        if(event.getSource() == vista.etiqueta_c){
            
            contestacion = new Contestacion(getPregunta(), juego, jugador, 'C', null);
            
        } else {
            
            contestacion = new Contestacion(getPregunta(), juego, jugador, 'D', null);
            
        }

        Iniciar_Juego_Controlador.controlador.contestaciones.add(contestacion);
        Iniciar_Juego_Controlador.controlador.preguntas_respondidas++;
        Iniciar_Juego_Controlador.controlador.actualizarVista();
        
        vista.dispose();
        
        Responder_Pregunta_Controlador siguiente_pregunta = new Responder_Pregunta_Controlador(jugador, juego, indice+1);
        siguiente_pregunta.iniciar();
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
    private Pregunta getPregunta(){
        
        int contador = 0;
        
        int indice_auxiliar = indice + 0;
        
        Pregunta [] preguntas1 = Pregunta.getPreguntas(juego.getContenido_1());
        Pregunta [] preguntas2 = Pregunta.getPreguntas(juego.getContenido_2());
        Pregunta [] preguntas3 = Pregunta.getPreguntas(juego.getContenido_3());
        
        while(preguntas1[contador] != null){
            
            if(contador == indice_auxiliar)
                return preguntas1[contador];
            
            contador++;
        }
        
        indice_auxiliar = indice_auxiliar - contador;
        
        contador = 0;
        
        while(preguntas2[contador] != null){
            
            if(contador == indice_auxiliar)
                return preguntas2[contador];
            
            contador++;
        }
        
        indice_auxiliar = indice_auxiliar - contador;
        
        contador = 0;
        
        while(preguntas3[contador] != null){
            
            if(contador == indice_auxiliar)
                return preguntas3[contador];
            
            contador++;
        }
        
        return null;
        
    }
}
