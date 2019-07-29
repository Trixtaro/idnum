
package controladores;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Blob;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelos.Contenido;
import modelos.Literal;
import modelos.Pregunta;
import vistas.Pregunta_Vista;

public class Pregunta_Controlador implements ActionListener, WindowListener{
    
    Contenido contenido;
    
    Contenido_Controlador controlador_padre;
    
    Pregunta_Vista vista;

    public Pregunta_Controlador(Contenido contenido, Contenido_Controlador controlador_padre){
        
        this.controlador_padre = controlador_padre;
        this.contenido = contenido;
        this.vista= new vistas.Pregunta_Vista();
        this.vista.boton_agregar.addActionListener(this);
        this.vista.boton_eliminar.addActionListener(this);
        this.vista.boton_seleccionar.addActionListener(this);
        this.vista.boton_retroceder.addActionListener(this);
        
        this.vista.addWindowListener(this);
    }   
    
    
    public void iniciar(){
        vista.setTitle("Preguntas - "+contenido.getNombre_contenido());
        vista.etiqueta_contenido.setText(contenido.getNombre_contenido());
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        
        DefaultTableModel modelo = new DefaultTableModel(){
            
           @Override 
            public Class getColumnClass(int column){
                switch(column) {
                    case 0: return String.class;
                    case 1: return ImageIcon.class;
                    case 2: return String.class;
                    default: return Object.class;
                }
            }
        };
        
        vista.tabla_pregunta.setModel(modelo);
        
        modelo.addColumn("Código");
        modelo.addColumn("Imagen");
        modelo.addColumn("Tipo");
        
        vista.tabla_pregunta.setRowHeight(100);
        
        llenar_tabla();
    }
    
    public void llenar_tabla(){
        
        DefaultTableModel modelo = (DefaultTableModel) vista.tabla_pregunta.getModel();
        
        modelo.setRowCount(0);
        
        int contador = 0;
        
        Pregunta [] preguntas = Pregunta.getPreguntas(contenido);
        
        while(preguntas[contador] != null){
            
            Image foto = null;
            ImageIcon icon;
            Blob b = preguntas[contador].getImagen();
            try{
                foto = javax.imageio.ImageIO.read(b.getBinaryStream());
                foto = foto.getScaledInstance(96, 96, Image.SCALE_DEFAULT);                                
                icon = new ImageIcon(foto);
                
                modelo.addRow(new Object[]{ 
                preguntas[contador].getId_pregunta(), 
                icon, 
                preguntas[contador].getTipo()
            });
                
            }catch(Exception ex){
                System.out.println("Fallo"+ex);
            }
            
            contador++;            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == vista.boton_retroceder){
        
            vista.setVisible(false);
            controlador_padre.vista.setVisible(true);
            controlador_padre.llenar_tabla();
        
        } else if (e.getSource() == vista.boton_agregar){
                    
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos: png,jpg","png","jpg");
            JFileChooser archivo = new JFileChooser();
            archivo.setFileFilter(filtro);
            if(archivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                
                String ruta = archivo.getSelectedFile().toString();
               
                String [] opciones = new String[2];
                opciones[0] = new String("CERRADA");
                opciones[1] = new String("ABIERTA");
                
                String opcion_seleccionada = (String) JOptionPane.showInputDialog(vista, "Seleccione el tipo de pregunta.", "Tipo de pregunta", JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);
                
                Pregunta pregunta = new Pregunta(ruta, opcion_seleccionada, contenido);
                
                if(opcion_seleccionada.equals("CERRADA")){
                    
                    String [] tipo_literales = new String[2];
                    tipo_literales[0] = new String("IMAGEN");
                    tipo_literales[1] = new String("ORACION");
                
                    String tipo_literal = (String) JOptionPane.showInputDialog(vista, "Seleccione el tipo de literal.", "Tipo de pregunta", JOptionPane.DEFAULT_OPTION, null, tipo_literales, opciones[0]);
                    
                    if(tipo_literal.equals("IMAGEN")){
                        
                        Pregunta_Literal_Controlador pregunta_Literal_Controlador = new Pregunta_Literal_Controlador(pregunta, this, "IMAGEN");
                        pregunta_Literal_Controlador.iniciar();
                        vista.setVisible(false);
                        
                    }else if(tipo_literal.equals("ORACION")){
                        String nombre_literal = JOptionPane.showInputDialog(vista, "Ingrese el nuevo literal.", "Agregar literal", JOptionPane.DEFAULT_OPTION);
            
                        if(nombre_literal.equals("")){
                        JOptionPane.showMessageDialog(vista, "Debe escribir un nuevo literal.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                        }       
                        
                        Literal nuevo_literal = new Literal(nombre_literal);
                        System.out.println(""+nuevo_literal.getCaracter());
                        //nuevo_literal.ingresarBD();

                        //llenar_tabla();
                    
                        }
                    
                } else if (opcion_seleccionada.equals("ABIERTA")){
                    
                }
                
            }
        }else if(e.getSource() == vista.boton_eliminar){
            int codigo;
            try {
                codigo = (int) vista.tabla_pregunta.getValueAt(vista.tabla_pregunta.getSelectedRow(), 0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un contenido", "Aviso", JOptionPane.WARNING_MESSAGE);
               return ;
            }
            
            Pregunta pregunta = new Pregunta(codigo);
            
            pregunta.borrarBD();
            
            DefaultTableModel modelo = (DefaultTableModel) vista.tabla_pregunta.getModel();
            
            modelo.removeRow(vista.tabla_pregunta.getSelectedRow());            
        }
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        controlador_padre.vista.setVisible(true);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
    
}
