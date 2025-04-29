/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myexception;

public class RequestMappingException extends RuntimeException {

    public RequestMappingException(String message) {
        super(message);
    }

    public RequestMappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
