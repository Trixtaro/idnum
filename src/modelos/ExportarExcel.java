
package modelos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExportarExcel {

    public static void Exportar_FacturaXLS(Jugador_Juego jugador_Juego, String tabla[][]) throws IOException {
        //se declara una variable String con la ruta donde se van a alabergar los archivos XLS
        String rutaArchivo = "Examen/Examen " + jugador_Juego.getJugador().getNombre_jugador() + ""
                + " "+jugador_Juego.getJuego().getNombre()+".xls";
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
            //font y fon1 son las fuentes que van a tener los encabezados y la filas o informacion de los encabezados respectivamente
            HSSFFont font = (HSSFFont) libro.createFont();
            HSSFFont font1 = (HSSFFont) libro.createFont();

            font.setFontName("Arial");
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);

            font.setFontHeightInPoints((short) 12);
            style.setFont(font);
            font1.setFontName("Arial");

            font1.setFontHeightInPoints((short) 10);
            style1.setFont(font1);
            // el objeto sheet contedra el nombre de la hoja del libro XLS, que es proporcionado por el parametro num_facturas "número de factura en la tabla factura de la base de datos"
            Sheet hoja = libro.createSheet("Anexos");
           //for principal que ira añadiendo las filas al documento XLS en este caso solo seran 6, por que es el formato que se a establecido 
            for(int i=0; i<tabla.length; i++){
                Row fila = hoja.createRow(i);
                for(int j=0; j<tabla[i].length; j++){
                    Cell cell = fila.createCell(j);
                        cell.setCellValue(tabla[i][j]);
                        cell.setCellStyle(style);
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

}
