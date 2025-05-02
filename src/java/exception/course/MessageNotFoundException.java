/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package exception.course;

public class MessageNotFoundException extends RuntimeException {
	public MessageNotFoundException() {
		super("Message not found!!!");
	}
}
