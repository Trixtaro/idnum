
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Contenido;
import vistas.Contenido_Vista;

public class Contenido_Controlador implements ActionListener{
    
    public static Contenido_Controlador contenido_Controlador;
    
    Contenido_Vista vista;
    
    public Contenido_Controlador(){
        
        this.vista = new Contenido_Vista();
        
        contenido_Controlador = this;
        
        this.vista.boton_agregar.addActionListener(this);
        this.vista.boton_borrar.addActionListener(this);
        this.vista.boton_seleccionar.addActionListener(this);
        this.vista.boton_salir.addActionListener(this);
        this.vista.boton_configurar_literal.addActionListener(this);
        
    }
    
    public void iniciar(){
        vista.setTitle("Contenidos");
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        llenar_tabla();
    }
    
    public void llenar_tabla(){
        
        DefaultTableModel modelo = (DefaultTableModel) vista.tabla_contenidos.getModel();
        
        modelo.setRowCount(0);
        
        int contador = 0;
        
        Contenido [] contenidos = Contenido.getContenidos();
        
        while(contenidos[contador] != null){
            
            modelo.addRow(new Object[]{ 
                contenidos[contador].getId_contenido(), 
                contenidos[contador].getNombre_contenido(), 
                contenidos[contador].getN_preguntas() 
            });
            
            contador++;            
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        int codigo;
                
        if(e.getSource() == vista.boton_agregar){
            
            String nombre_contenido = JOptionPane.showInputDialog(vista, "Ingrese el nombre del nuevo contenido.", "Agregar contenido", JOptionPane.DEFAULT_OPTION);
            
            if(nombre_contenido.equals("")){
                JOptionPane.showMessageDialog(vista, "Debe escribir un nombre de contenido.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Contenido nuevo_contenido = new Contenido(nombre_contenido);
            System.out.println(""+nuevo_contenido.getNombre_contenido());
            nuevo_contenido.ingresarBD();
            
            llenar_tabla();
            
        }
        
        if(e.getSource()== vista.boton_borrar){
            
            try {
                codigo = (int) vista.tabla_contenidos.getValueAt(vista.tabla_contenidos.getSelectedRow(), 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un contenido", "Aviso", JOptionPane.WARNING_MESSAGE);
               return ;
            }
            
            Contenido contenido = new Contenido(codigo);
            
            if(contenido.borrarBD() == false)
                return;
            
            DefaultTableModel modelo = (DefaultTableModel) vista.tabla_contenidos.getModel();
            
            modelo.removeRow(vista.tabla_contenidos.getSelectedRow());
            
            llenar_tabla();
            
            
        }
        
        if(e.getSource()== vista.boton_seleccionar){
            
            try {
                codigo = (int) vista.tabla_contenidos.getValueAt(vista.tabla_contenidos.getSelectedRow(), 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un contenido", "Aviso", JOptionPane.WARNING_MESSAGE);
                return ;
            }
            
            Contenido contenido = new Contenido(codigo);
            contenido.consultarBD();
                        
            Pregunta_Controlador pregunta_Controlador = new Pregunta_Controlador(contenido);
            pregunta_Controlador.iniciar();
            
            vista.setVisible(false);
            
        } else
            
        if(e.getSource() == vista.boton_configurar_literal){
        
            Literal_Controlador literal_Controlador = new Literal_Controlador();
            literal_Controlador.iniciar();
            
            vista.setVisible(false);
            
        } else
        
        if(e.getSource()== vista.boton_salir){
            vista.dispose();
            Juego_Controller.juego_Controller.vista.setVisible(true);
        }
        
    }
    
    
}
