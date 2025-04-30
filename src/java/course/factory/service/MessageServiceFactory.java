/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package course.factory.service;

import course.service.impl.MessageServiceImpl;
import course.service.itf.MessageService;

public class MessageServiceFactory {
	private static MessageService messageService = new MessageServiceImpl();
	
	public static MessageService getMessageService() {
		return messageService;
	}
}
