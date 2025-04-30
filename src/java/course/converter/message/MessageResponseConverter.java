/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package course.converter.message;

import course.dto.message.MessageResponseDTO;
import course.entity.message.MessageEntity;
import utils.Mapper;

public class MessageResponseConverter {
	public static MessageResponseDTO toConverterMessage(MessageEntity messageEntity) {
		return Mapper.mapToObject(messageEntity, MessageResponseDTO.class);
	}
}
