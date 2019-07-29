
package modelos;

public abstract class Validacion {
    
    public static boolean isNumber(char character){
        
        if(character <= '9' && character >= '0')
            return true;
        else
            return false;
        
    }
    
    public static boolean isMaxLength(String text, int max_length){
    
        if(text.length() == max_length)
            return true;
        else
            return false;
        
    }
    
}
