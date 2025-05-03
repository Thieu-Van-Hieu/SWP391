/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package course.converter.message;

import course.dto.member_information.MemberInformationRequestDTO;
import course.dto.message.MessageResponseDTO;
import course.entity.MemberInformationEntity;
import course.entity.MessageEntity;
import course.factory.repository.MemberInformationRepositoryFactory;
import utils.Mapper;

public class MessageResponseConverter {
	public static MessageResponseDTO toConverterMessage(MessageEntity messageEntity) {
		MemberInformationRequestDTO memberInformationRequestDTO = new MemberInformationRequestDTO();
		memberInformationRequestDTO.setMemberId(messageEntity.getMemberId());
		
		MemberInformationEntity memberInformationEntity = MemberInformationRepositoryFactory.getMemberInformationRepository().getMemberInformationByMemberInformationId(memberInformationRequestDTO);
		MessageResponseDTO messageResponseDTO = Mapper.mapToObject(messageEntity, MessageResponseDTO.class);
		messageResponseDTO.setSenderName(memberInformationEntity.getName());
		return messageResponseDTO;
	}
}
