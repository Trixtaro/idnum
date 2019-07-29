
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Contenido;
import modelos.Juego;
import modelos.Jugador;
import vistas.Juego_Vista;

public class Juego_Controller implements ActionListener{
    
    public static Juego_Controller juego_Controller;
    
    Juego_Vista vista;
    
    Jugador jugador;
    
    public Juego_Controller(){
        
        this.juego_Controller = this;
        
        this.vista = new Juego_Vista();
        
        this.vista.boton_agregar.addActionListener(this);
        this.vista.boton_borrar.addActionListener(this);
        this.vista.boton_jugar.addActionListener(this);
        this.vista.boton_salir.addActionListener(this);
        
        this.vista.boton_jugar.setEnabled(false);
        
    }
    
    public Juego_Controller(Jugador jugador){
        
        this.juego_Controller = this;
        
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
        
        llenar_tabla();
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == vista.boton_agregar){
            
            vista.setVisible(false);
            Nuevo_Juego_Controlador nuevo_Juego_Controlador = new Nuevo_Juego_Controlador();
            nuevo_Juego_Controlador.iniciar();
            
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