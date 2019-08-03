
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Juego;
import modelos.Jugador;
import vistas.Juego_Vista;

public class Juego_Controller implements ActionListener{
    
    public static Juego_Controller juego_Controller;
    
    Juego_Vista vista;
    
    Jugador jugador;
    
    public Juego_Controller(){
        
        juego_Controller = this;
        
        this.vista = new Juego_Vista();
        
        this.vista.boton_agregar.addActionListener(this);
        this.vista.boton_borrar.addActionListener(this);
        this.vista.boton_jugar.addActionListener(this);
        this.vista.boton_agregar_contenido.addActionListener(this);
        this.vista.boton_salir.addActionListener(this);
        this.vista.boton_historial.addActionListener(this);
        
        this.vista.boton_jugar.setEnabled(false);
        
    }
    
    public Juego_Controller(Jugador jugador){
        
        juego_Controller = this;
        
        this.vista = new Juego_Vista();
        
        this.jugador = jugador;
        
        this.vista.boton_agregar.addActionListener(this);
        this.vista.boton_borrar.addActionListener(this);
        this.vista.boton_jugar.addActionListener(this);
        this.vista.boton_salir.addActionListener(this);
        
        this.vista.boton_agregar.setEnabled(false);
        this.vista.boton_borrar.setEnabled(false);
        this.vista.boton_agregar_contenido.setEnabled(false);
        this.vista.boton_historial.setEnabled(false);
        
    }
    
    public void iniciar(){
        
        if(jugador != null)
            vista.setTitle(jugador.getNombre_ayudante()+" - Edad: "+jugador.getEdad()+" - JUEGOS");
        else
            vista.setTitle("PANEL DE CONTROL DE JUEGOS");
        
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
        llenar_tabla();
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == vista.boton_agregar){
            
            vista.setVisible(false);
            Nuevo_Juego_Controlador nuevo_Juego_Controlador = new Nuevo_Juego_Controlador();
            nuevo_Juego_Controlador.iniciar();
            
        } else
            
        if(event.getSource() == vista.boton_agregar_contenido){
            
            vista.setVisible(false);
            Contenido_Controlador contenido_Controlador = new Contenido_Controlador();
            contenido_Controlador.iniciar();
            
        } else
            
        if(event.getSource() == vista.boton_jugar){
            
            int codigo = 0;
            
            try {
                codigo = (int) vista.tabla.getValueAt(vista.tabla.getSelectedRow(), 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un juego", "Aviso", JOptionPane.WARNING_MESSAGE);
               return ;
            }
            
            Juego juego = Juego.getJuegoPorNombre(vista.tabla.getValueAt(vista.tabla.getSelectedRow(), 1).toString());
            
            vista.setVisible(false);
            Iniciar_Juego_Controlador iniciar_Juego_Controlador = new Iniciar_Juego_Controlador(jugador, juego);
            iniciar_Juego_Controlador.iniciar();
            
            
        } else
            
        if(event.getSource() == vista.boton_historial){
    
            vista.setVisible(false);
            
            Juegos_Jugados_Controller juegos_Jugados_Controller = new Juegos_Jugados_Controller();
            juegos_Jugados_Controller.iniciar();
            
        } else 
        
        if(event.getSource() == vista.boton_salir){
        
            System.exit(0);
            
        }
        
    }
    
    public void llenar_tabla(){
        
        DefaultTableModel modelo = (DefaultTableModel) vista.tabla.getModel();
        
        modelo.setRowCount(0);
        
        int contador = 0;
        
        Juego [] juegos = Juego.getJuegos();
        
        while(juegos[contador] != null){
            
            modelo.addRow(new Object[]{ 
                juegos[contador].getId_juego(),
                juegos[contador].getNombre(),
                juegos[contador].getN_veces_jugado()
            });
            
            contador++;            
        }
        
    }
    
}
