
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelos.Literal;
import modelos.Pregunta;
import vistas.Literal_Vista;

public class Literal_Controlador implements ActionListener, ItemListener{
    
    Literal_Vista vista;
    
    public Literal_Controlador(){
    
        this.vista = new Literal_Vista();
        
        this.vista.boton_agregar.addActionListener(this);
        this.vista.boton_borrar.addActionListener(this);
        this.vista.boton_retroceder.addActionListener(this);
        
        this.vista.combo_tipo.addItemListener(this);
        
        
    }
    
    public void iniciar(){
        
        this.vista.setTitle("Literales");
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        
        llenar_tabla();
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        if(event.getSource() == vista.boton_agregar){
        
            String [] opciones = new String[2];
            opciones[0] = new String("IMAGEN");
            opciones[1] = new String("ORACION");
                
            String opcion_seleccionada = (String) JOptionPane.showInputDialog(vista, "Seleccione el tipo de literal.", "Tipo de literal", JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);
            
            if(opcion_seleccionada.equals("IMAGEN")){
                
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos: png,jpg","png","jpg");
                JFileChooser archivo = new JFileChooser();
                archivo.setFileFilter(filtro);
                
                if(archivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    
                    String ruta = archivo.getSelectedFile().toString();
                    
                    Literal literal = new Literal(ruta, true);
                    literal.ingresarBD();
                    
                    llenar_tabla();
                    
                }
                
            } else
                
            if(event.getSource() == vista.boton_borrar){
                
                int codigo;
                try {
                    codigo = (int) vista.tabla.getValueAt(vista.tabla.getSelectedRow(), 0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Debe seleccionar un literal", "Aviso", JOptionPane.WARNING_MESSAGE);
                return ;
                }
            
                Literal literal = new Literal(codigo);
            
                if(literal.borrarBD() == false)
                    return;
            
                DefaultTableModel modelo = (DefaultTableModel) vista.tabla.getModel();
            
                modelo.removeRow(vista.tabla.getSelectedRow()); 
                
            }
            
        } else
        
        if(event.getSource() == vista.boton_retroceder){
        
            vista.dispose();
            
            Contenido_Controlador.contenido_Controlador.vista.setVisible(true);
            
        }
        
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        
        if(ie.getStateChange() == 1){
        
            llenar_tabla();
            
        }
        
    }
    
    public void llenar_tabla(){
        
        if(vista.combo_tipo.getSelectedItem().toString().equals("IMAGEN")){
            
            DefaultTableModel modelo = new DefaultTableModel(){
            
           @Override 
            public Class getColumnClass(int column){
                switch(column) {
                    case 0: return String.class;
                    case 1: return ImageIcon.class;
                    case 2: return String.class;
                    default: return Object.class;
                }
            }};
        
            vista.tabla.setModel(modelo);
        
            modelo.addColumn("Código");
            modelo.addColumn("Imagen");
            modelo.addColumn("Tipo");
        
            vista.tabla.setRowHeight(100);
            
        } else 
            
        if(vista.combo_tipo.getSelectedItem().toString().equals("ORACION")){
            
            DefaultTableModel modelo = new DefaultTableModel(){
            
           @Override 
            public Class getColumnClass(int column){
                switch(column) {
                    case 0: return String.class;
                    case 1: return String.class;
                    case 2: return String.class;
                    default: return Object.class;
                }
            }};
        
            vista.tabla.setModel(modelo);
        
            modelo.addColumn("Código");
            modelo.addColumn("Oración");
            modelo.addColumn("Tipo");
        
            vista.tabla.setRowHeight(100);
            
        }
        
        DefaultTableModel modelo = (DefaultTableModel) vista.tabla.getModel();
        
        modelo.setRowCount(0);
        
        int contador = 0;
        
        Literal [] literales = Literal.getLiterales();
        
        while(literales[contador] != null){
            
            if(vista.combo_tipo.getSelectedItem().toString().equals("IMAGEN")){
            
                if(literales[contador].getTipo_literal().equals("IMAGEN"))
                    modelo.addRow(new Object[]{ 
                        literales[contador].getId_literal(), 
                        literales[contador].getImageAsIcon(),
                        literales[contador].getTipo_literal()
                    });
                
            } else {
                
                if(literales[contador].getTipo_literal().equals("CARACTER"))
                    modelo.addRow(new Object[]{ 
                        literales[contador].getId_literal(), 
                        literales[contador].getCaracter(),
                        literales[contador].getTipo_literal()
                    });
                
            }

            
            contador++;            
        }
        
    }
    
}