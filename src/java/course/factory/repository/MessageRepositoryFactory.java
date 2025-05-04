package course.factory.repository;

import course.repository.impl.MessageRepositoryseImpl;
import course.repository.itf.MessageRepository;

public class MessageRepositoryFactory {
	private static final MessageRepository messageRepository = new MessageRepositoryseImpl();
	
	public static MessageRepository getMessageRepository() {
		return messageRepository;
	}
}
