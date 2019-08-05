
package controladores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.ExportarExcel;
import modelos.Juego;
import modelos.Jugador;
import modelos.Jugador_Juego;
import vistas.Juegos_Jugados_Vista;

public class Juegos_Jugados_Controller implements ActionListener{
    
    Juegos_Jugados_Vista vista;
    
    public Juegos_Jugados_Controller(){
        
        this.vista = new Juegos_Jugados_Vista();
        
        this.vista.boton_excel.addActionListener(this);
        this.vista.boton_regresar.addActionListener(this);
        
    }
    
    public void iniciar(){
        
        this.vista.setTitle("Historial de juegos");
        
        this.vista.setLocationRelativeTo(null);
        vista.getContentPane().setBackground(Color.decode("#fcf9ea"));
        this.vista.setVisible(true);
        
        llenar_tabla();
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == vista.boton_excel){
        
            String fecha = "";
            
            try {
                
                fecha = (String) vista.tabla.getValueAt(vista.tabla.getSelectedRow(), 2);
                
                Juego juego = new Juego((int) vista.tabla.getValueAt(vista.tabla.getSelectedRow(), 0));
                Jugador jugador = new Jugador((int) vista.tabla.getValueAt(vista.tabla.getSelectedRow(), 1));
                
                juego.consultarBD();
                jugador.consultarBD();
                       
                Jugador_Juego jugador_Juego = new Jugador_Juego(jugador, juego, null);
                jugador_Juego.setFecha(fecha);
                
                ExportarExcel.Exportar_FacturaXLS(jugador_Juego, jugador_Juego.getRespuestasExamen());
                
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una pregunta", "Aviso", JOptionPane.WARNING_MESSAGE);
                System.out.println("Juegos_Jugador_Controller - actionPerfomed: "+ex);
               return;
            }
        
        } else
        
        if(event.getSource() == vista.boton_regresar){
            
            vista.dispose();
            Juego_Controller.juego_Controller.vista.setVisible(true);
            
        }
        
    }
    
    public void llenar_tabla(){
        
        DefaultTableModel modelo = (DefaultTableModel) vista.tabla.getModel();
        
        vista.tabla.getColumnModel().getColumn(0).setPreferredWidth(10);
        vista.tabla.getColumnModel().getColumn(1).setPreferredWidth(25);
        
        modelo.setRowCount(0);
        
        int contador = 0;
        
        Jugador_Juego [] jugador_Juegos = Jugador_Juego.getJugador_Juegos();
        
        while(jugador_Juegos[contador] != null){
            
            modelo.addRow(new Object[]{ 
                jugador_Juegos[contador].getJuego().getId_juego(), 
                jugador_Juegos[contador].getJugador().getId_jugador(), 
                jugador_Juegos[contador].getFecha()
            });
            
            contador++;            
        }
        
    }
    
}
