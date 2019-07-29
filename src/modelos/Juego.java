
package modelos;

public class Juego implements DatabaseAble{

    int id_juego;
    char nombre;
    Contenido contenido_1;
    Contenido contenido_2;
    Contenido contenido_3;

    public Juego(char nombre, Contenido contenido_1, Contenido contenido_2, Contenido contenido_3) {
        this.nombre = nombre;
        this.contenido_1 = contenido_1;
        this.contenido_2 = contenido_2;
        this.contenido_3 = contenido_3;
    }
    
    
    @Override
    public void ingresarBD() {
        
    }

    @Override
    public void actualizarBD() {
        
    }

    @Override
    public void borrarBD() {
        
    }

    @Override
    public void consultarBD() {
        
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public char getNombre() {
        return nombre;
    }

    public void setNombre(char nombre) {
        this.nombre = nombre;
    }

    public Contenido getContenido_1() {
        return contenido_1;
    }

    public void setContenido_1(Contenido contenido_1) {
        this.contenido_1 = contenido_1;
    }

    public Contenido getContenido_2() {
        return contenido_2;
    }

    public void setContenido_2(Contenido contenido_2) {
        this.contenido_2 = contenido_2;
    }

    public Contenido getContenido_3() {
        return contenido_3;
    }

    public void setContenido_3(Contenido contenido_3) {
        this.contenido_3 = contenido_3;
    }
    
}
