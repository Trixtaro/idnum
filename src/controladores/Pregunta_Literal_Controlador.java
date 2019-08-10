
package controladores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Literal;
import modelos.Pregunta;
import vistas.Pregunta_Literal_Vista;

public class Pregunta_Literal_Controlador implements ActionListener{
    
    Pregunta pregunta;
    
    String tipo_literal;
    
    Pregunta_Literal_Vista vista;
    Pregunta_Controlador controlador_padre;
    
    char literal;
    
    public Pregunta_Literal_Controlador(Pregunta pregunta, Pregunta_Controlador controlador_padre, String tipo_literal){
        
        this.vista = new Pregunta_Literal_Vista();
        this.vista.boton_seleccionar.addActionListener(this);
        this.vista.boton_regresar.addActionListener(this);
        this.controlador_padre = controlador_padre;
        this.pregunta = pregunta;
        this.tipo_literal = tipo_literal;
        this.literal = 'A';
        
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.getContentPane().setBackground(Color.decode("#fcf9ea"));
        
        actualizarInterfaz();
        
        llenar_tabla();
    }
    
    public void llenar_tabla(){
        
        if(tipo_literal.equals("IMAGEN")){
                    
            DefaultTableModel modelo = new DefaultTableModel(){
            
                @Override 
                public Class getColumnClass(int column){
                    switch(column) {
                        case 0: return String.class;
                        case 1: return ImageIcon.class;
                        default: return Object.class;
                    }
                }
            };
        
            vista.tabla.setModel(modelo);
            
            modelo.addColumn("Código");
            modelo.addColumn("Imagen");
        
            vista.tabla.setRowHeight(100);
            
            vista.tabla.getColumnModel().getColumn(0).setPreferredWidth(64);
            vista.tabla.getColumnModel().getColumn(1).setPreferredWidth(480-64-1);
            
            modelo.setRowCount(0);
            
            int contador = 0;
        
            Literal [] literales = Literal.getLiterales();
        
            while(literales[contador] != null){
            
            
                if(literales[contador].getTipo_literal().equals("IMAGEN"))
                
                    modelo.addRow(new Object[]{ 
                        literales[contador].getId_literal(), 
                        literales[contador].getImageAsIcon()
                    });
       
                contador++;            
            }
        
            
        } else if(tipo_literal.equals("ORACION")){
            
            DefaultTableModel modelo = new DefaultTableModel(){
            
                @Override 
                public Class getColumnClass(int column){
                    switch(column) {
                        case 0: return String.class;
                        case 1: return String.class;
                        default: return Object.class;
                    }
                }
            };
            
            vista.tabla.setModel(modelo);
            
            modelo.addColumn("Código");
            modelo.addColumn("Oracion");
        
            vista.tabla.setRowHeight(100);
            
            vista.tabla.getColumnModel().getColumn(0).setPreferredWidth(64);
            vista.tabla.getColumnModel().getColumn(1).setPreferredWidth(480-64-1);
            
            modelo.setRowCount(0);
            
            int contador = 0;
        
            Literal [] literales = Literal.getLiterales();
        
            while(literales[contador] != null){
                
                System.out.println("GGG");
            
                if(literales[contador].getTipo_literal().equals("CARACTER"))
                
                    modelo.addRow(new Object[]{ 
                        literales[contador].getId_literal(), 
                        literales[contador].getCaracter()
                    });
       
                contador++;            
            }
            
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == vista.boton_seleccionar){
            
            int codigo;
            
            try {
                
                codigo = (int) vista.tabla.getValueAt(vista.tabla.getSelectedRow(), 0);
                
            } catch (Exception ex) {
                
                codigo = -1;
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un literal.", "Literal "+literal, JOptionPane.WARNING_MESSAGE);
                return;
            }
            
                    
            switch(literal){
                case 'A': 
                    pregunta.setLiteral_A(getLiteral(codigo));
                    break;
                case 'B':
                    
                    if(pregunta.getLiteral_A().getId_literal() == getLiteral(codigo).getId_literal()){
                        
                        JOptionPane.showMessageDialog(vista, "Debe seleccionar un literal diferente.", "Literal "+literal, JOptionPane.WARNING_MESSAGE);
                        return;
                        
                    }
                    
                    pregunta.setLiteral_B(getLiteral(codigo));
                    break;
                case 'C':
                    
                    if(pregunta.getLiteral_A().getId_literal() == getLiteral(codigo).getId_literal() || 
                            pregunta.getLiteral_B().getId_literal() == getLiteral(codigo).getId_literal()){
                        
                        JOptionPane.showMessageDialog(vista, "Debe seleccionar un literal diferente.", "Literal "+literal, JOptionPane.WARNING_MESSAGE);
                        return;
                        
                    }
                    
                    pregunta.setLiteral_C(getLiteral(codigo));
                    break;
                case 'D':
                    
                    if(pregunta.getLiteral_A().getId_literal() == getLiteral(codigo).getId_literal() || 
                            pregunta.getLiteral_B().getId_literal() == getLiteral(codigo).getId_literal()
                            || pregunta.getLiteral_C().getId_literal() == getLiteral(codigo).getId_literal()){
                        
                        JOptionPane.showMessageDialog(vista, "Debe seleccionar un literal diferente.", "Literal "+literal, JOptionPane.WARNING_MESSAGE);
                        return;
                        
                    }
                    
                    pregunta.setLiteral_D(getLiteral(codigo));
                    break;
                default:
                    
            }
            
            if(literal != 'D'){
                
                literal++;
                actualizarInterfaz();
                
            } else {
                String [] opciones = new String[4];
                opciones[0] = new String("A");
                opciones[1] = new String("B");
                opciones[2] = new String("C");
                opciones[3] = new String("D");
                
                String respuesta = (String) JOptionPane.showInputDialog(vista, "Seleccione el literal correcto.", "Respuesta", JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);
                
                if(respuesta.equals("")){
                    
                } else {
                    pregunta.setRespuesta(respuesta.charAt(0));
                    pregunta.ingresarBD();
                    vista.dispose();
                    controlador_padre.vista.setVisible(true);
                    controlador_padre.llenar_tabla();
                    
                   
                }
            }
            
        }else if(e.getSource()== vista.boton_regresar){
            vista.setVisible(false);
            controlador_padre.vista.setVisible(true);
        }
        
    }
    
    private Literal getLiteral(int codigo){
        Literal [] literales = Literal.getLiterales();
        
        int contador = 0;
        
        while(literales[contador] != null){
            
            if(literales[contador].getId_literal() == codigo)
                return literales[contador];
            
            contador++;
        }
        
        return null;
    }
    
    private void actualizarInterfaz(){
        vista.setTitle("Literal "+literal);
        JOptionPane.showMessageDialog(vista, "Seleccione el literal "+literal);
    }
    
}
