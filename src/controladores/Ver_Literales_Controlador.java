
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.Pregunta;
import vistas.Responder_Pregunta_Vista;

public class Ver_Literales_Controlador implements ActionListener{
    
    Responder_Pregunta_Vista vista;
    
    Pregunta pregunta;
    
    public Ver_Literales_Controlador(Pregunta pregunta){
        
        this.vista = new Responder_Pregunta_Vista();
        
        this.pregunta = pregunta;
        
        this.vista.boton_salir.addActionListener(this);
        
    }
    
    public void iniciar(){
        
        this.vista.setTitle("Literales");
        
        this.vista.setLocationRelativeTo(null);
        
        this.vista.etiqueta_titulo.setText("Literales de la pregunta");
        this.vista.boton_salir.setText("REGRESAR");
        
        this.vista.etiqueta_pregunta.setIcon(pregunta.getImageAsIcon());
        
        if(pregunta.getTipo().equals("IMAGEN")){
            
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
        
        this.vista.setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == vista.boton_salir){
            
            vista.dispose();
            Pregunta_Controlador.pregunta_Controlador.vista.setVisible(true);
            
        }
        
    }
    
}
