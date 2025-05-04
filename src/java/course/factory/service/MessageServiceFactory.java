package course.factory.service;

import course.service.impl.MessageServiceImpl;
import course.service.itf.MessageService;

public class MessageServiceFactory {
	private static MessageService messageService = new MessageServiceImpl();
	
	public static MessageService getMessageService() {
		return messageService;
	}
}
