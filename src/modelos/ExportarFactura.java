
package modelos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExportarFactura {

    public void Exportar_FacturaXLS(JTable anexos, JTable Facturas, String year) throws IOException {
        //se declara una variable String con la ruta donde se van a alabergar los archivos XLS
        String rutaArchivo = "Examen/factura-" + year + ".xls";
        // se crea un aobjeto de tipo File el cual contendra la ruta del los archivos XLS
        File archivoXLS = new File(rutaArchivo);
        //si el archivo exites se procede a borrarlo y a crear uno nuevo
        if (archivoXLS.exists()) {
            archivoXLS.delete();
        }
        archivoXLS.createNewFile();
        //se crea un objeto tipo Workbook con el nombre de libro
        Workbook libro = new HSSFWorkbook();
       //si se crea correctamente el objeto archivo, intentar
        try (FileOutputStream archivo = new FileOutputStream(archivoXLS)) {
            //style y style1 son estilos, el style1 es el estilo  que va a tener los encabezados, y el style1 el que va tener la información correspondiente 
            //a ese ecabezado
            HSSFCellStyle style = (HSSFCellStyle) libro.createCellStyle();
            HSSFCellStyle style1 = (HSSFCellStyle) libro.createCellStyle();
            //estilo encabezado tendra un fondo azul 
            style.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
             //estilo encabezado tendra un fondo gris 
            style1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            //font y fon1 son las fuentes que van a tener los encabezados y la filas o informacion de los encabezados respectivamente
            HSSFFont font = (HSSFFont) libro.createFont();
            HSSFFont font1 = (HSSFFont) libro.createFont();
            //el color de font es blanco, los encabezados tendra una fuente de color blanco
            font.setColor(HSSFColor.WHITE.index);
            font.setFontName("Arial");
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            //font tendra un tamaño de letra de 12
            font.setFontHeightInPoints((short) 12);
            style.setFont(font);
            font1.setFontName("Arial");
            //font tendra un tamaño de letra de 10
            font1.setFontHeightInPoints((short) 10);
            style1.setFont(font1);
            // el objeto sheet contedra el nombre de la hoja del libro XLS, que es proporcionado por el parametro num_facturas "número de factura en la tabla factura de la base de datos"
            Sheet hoja = libro.createSheet("Anexos");
           //for principal que ira añadiendo las filas al documento XLS en este caso solo seran 6, por que es el formato que se a establecido 
            for(int i=0; i<anexos.getRowCount()+1; i++){
                Row fila = hoja.createRow(i);
                for(int j=0; j<anexos.getColumnCount(); j++){
                    Cell cell = fila.createCell(j);
                    if(i == 0){
                        cell.setCellValue(anexos.getColumnName(j));
                        cell.setCellStyle(style);
                    } else {
                            cell.setCellValue(anexos.getValueAt(i-1, j).toString());
                            cell.setCellStyle(style1);
                    }
                }
            }
            
            hoja = libro.createSheet("Facturas");
           //for principal que ira añadiendo las filas al documento XLS en este caso solo seran 6, por que es el formato que se a establecido 
            for(int i=0; i<Facturas.getRowCount()+1; i++){
                Row fila = hoja.createRow(i);
                for(int j=0; j<Facturas.getColumnCount(); j++){
                    Cell cell = fila.createCell(j);
                    if(i == 0){
                        cell.setCellValue(Facturas.getColumnName(j));
                        cell.setCellStyle(style);
                    } else {
                            cell.setCellValue(Facturas.getValueAt(i-1, j).toString());
                            cell.setCellStyle(style1);
                    }
                }
            }
            
            libro.write(archivo);
            /*Cerramos el flujo de datos*/
            archivo.close();

            //quitar el comentario si se desea abrir el archivo una vez creado            
            Desktop.getDesktop().open(archivoXLS);
            System.out.println("¡¡¡Archivo creado Correctamente!!!");
        }
    }
//funcion para agregar  las columnas al archivo XLS
    public void agregaFila1(String celda1, String celda2, String celda3, String celda4,
            String celda5, String celda6, String celda7, String celda8, Row fila, HSSFCellStyle style,HSSFCellStyle style1, int i) {
      //for para agregar las columnas 
        for (int j = 0; j < 8; j++) {
            //se agrega la columna de la posicion j
            Cell celda = fila.createCell(j);
            //si el parametro i tinen un mod de "0" entoces nos encontramos en la fila de un encabezado
            if (i % 2 == 0) {
            //procedemos a poner el estilo de los encabezados
                celda.setCellStyle(style);
            }else{
            //caso contrario sei el mod de i es diferente de "0" no encotramos en la fila de información, se agrega el estilo de las filas de información
                celda.setCellStyle(style1);
            }
            //switch de j
            switch (j) {
                //depenediendo del valor de "j" se agrega la información a  esa columna o celda 
                case 0:

                    celda.setCellValue(celda1);

                    break;
                case 1:

                    celda.setCellValue(celda2);
                    break;
                case 2:

                    celda.setCellValue(celda3);
                    break;
                case 3:

                    celda.setCellValue(celda4);
                    break;
                case 4:

                    celda.setCellValue(celda5);
                    break;
                case 5:

                    celda.setCellValue(celda6);
                    break;
                case 6:

                    celda.setCellValue(celda7);
                    break;
                case 7:

                    celda.setCellValue(celda8);
                    break;
            }
        }
    }

}
