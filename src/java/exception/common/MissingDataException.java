/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package exception.common;

public class MissingDataException extends RuntimeException {

	public MissingDataException() {
		super("Missing data");
	}
	
	public MissingDataException(String msg) {
		super(msg);
	}
	
}
