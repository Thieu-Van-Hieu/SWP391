/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package course.service.impl;

import course.converter.message.MessageResponseConverter;
import course.dto.course.CourseRequestDTO;
import course.dto.message.MessageRequestDTO;
import course.dto.message.MessageResponseDTO;
import course.entity.message.MessageEntity;
import course.factory.repository.MessageRepositoryFactory;
import course.service.itf.MessageService;
import java.util.ArrayList;

public class MessageServiceImpl implements MessageService {

	@Override
	public boolean sendMessage(MessageRequestDTO messageDTO) {
		return MessageRepositoryFactory.getMessageRepository().createMessage(messageDTO);
	}

	@Override
	public boolean editMessage(MessageRequestDTO messageDTO) {
		return MessageRepositoryFactory.getMessageRepository().editMessage(messageDTO);
	}

	@Override
	public boolean deleteMessage(MessageRequestDTO messageDTO) {
		return MessageRepositoryFactory.getMessageRepository().deleteMessage(messageDTO);
	}

	@Override
	public ArrayList<MessageResponseDTO> getMessagesByCourseId(CourseRequestDTO courseResponseDTO) {
		ArrayList<MessageEntity> messageEntities = MessageRepositoryFactory.getMessageRepository().getMessagesByCourseId(courseResponseDTO);
		
		ArrayList<MessageResponseDTO> messageResponseDTOs = new ArrayList<>();
		
		for (MessageEntity messageEntity:messageEntities) {
			MessageResponseDTO messageResponseDTO = MessageResponseConverter.toConverterMessage(messageEntity);
			messageResponseDTOs.add(messageResponseDTO);
		}
		
		return messageResponseDTOs;
	}

}
