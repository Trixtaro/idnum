
package controladores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.Contenido;
import modelos.Juego;
import vistas.Nuevo_Juego_Vista;

public class Nuevo_Juego_Controlador implements ActionListener{
    
    Nuevo_Juego_Vista vista;
    
    public Nuevo_Juego_Controlador(){
        
        this.vista = new Nuevo_Juego_Vista();
        
        this.vista.boton_agregar.addActionListener(this);
        this.vista.boton_cancelar.addActionListener(this);
    }
    
    public void iniciar(){
        
        vista.setTitle("Nuevo Juego");
        vista.setLocationRelativeTo(null);
        vista.getContentPane().setBackground(Color.decode("#fcf9ea"));
        vista.setVisible(true);
        
        llenar_contenidos();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == vista.boton_agregar){
            
            if(vista.combo_primer_contenido.getSelectedItem().equals(
                    vista.combo_segundo_contenido.getSelectedItem()
            ) || vista.combo_primer_contenido.getSelectedItem().equals(
                    vista.combo_tercer_contenido.getSelectedItem()
            ) || vista.combo_segundo_contenido.getSelectedItem().equals(
                    vista.combo_tercer_contenido.getSelectedItem()
            )){
                
                JOptionPane.showMessageDialog(vista, "Se deben elegir contenidos diferentes.","Aviso", JOptionPane.WARNING_MESSAGE);
                return;
                
            }
            
            Juego juego = new Juego(vista.txt_nombre.getText());
            
            if(juego.isRegistered()){
                JOptionPane.showMessageDialog(vista, "Ya existe un juego registrado con este nombre.","Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            juego = new Juego(vista.txt_nombre.getText(), 
                    Contenido.getContenidoPorNombre(vista.combo_primer_contenido.getSelectedItem().toString()), 
                    Contenido.getContenidoPorNombre(vista.combo_segundo_contenido.getSelectedItem().toString()), 
                    Contenido.getContenidoPorNombre(vista.combo_tercer_contenido.getSelectedItem().toString()));
            
            juego.ingresarBD();
        
            vista.dispose();
            Juego_Controller.juego_Controller.vista.setVisible(true);
            
            Juego_Controller.juego_Controller.llenar_tabla();
            
        } else
        
        if(event.getSource() == vista.boton_cancelar){
        
            vista.dispose();
            Juego_Controller.juego_Controller.vista.setVisible(true);
            
        }
        
    }
    
    public void llenar_contenidos(){
        
        int contador = 0;
        
        Contenido [] contenidos = Contenido.getContenidos();
        
        while(contenidos[contador] != null){
            
            vista.combo_primer_contenido.addItem(contenidos[contador].getNombre_contenido());
            vista.combo_segundo_contenido.addItem(contenidos[contador].getNombre_contenido());
            vista.combo_tercer_contenido.addItem(contenidos[contador].getNombre_contenido());
            
            contador++;            
        }
        
    }
    
}
