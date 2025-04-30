/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package course.factory.repository;

import course.repository.impl.MessageRepositoryseImpl;
import course.repository.itf.MessageRepository;

public class MessageRepositoryFactory {
	private static final MessageRepository messageRepository = new MessageRepositoryseImpl();
	
	public static MessageRepository getMessageRepository() {
		return messageRepository;
	}
}
