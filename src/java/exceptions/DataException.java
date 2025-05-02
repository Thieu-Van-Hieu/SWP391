/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author hunggt1572004
 */
public class DataException extends RuntimeException{
    public DataException(String message){
        super(message);
    }
    
    public DataException(String message, Throwable cause){
        super(message, cause);
    }
}
