package course.facade;

import course.converter.course.CourseRequestConverter;
import course.converter.message.MessageRequestConverter;
import course.dto.course.CourseRequestDTO;
import course.dto.message.MessageRequestDTO;
import course.dto.message.MessageResponseDTO;
import course.factory.service.MessageServiceFactory;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class MessageFacade {

	public static boolean sendMessage(HttpServletRequest request) {
		MessageRequestDTO messageRequestDTO = MessageRequestConverter.toConverterMessage(request);
		return MessageServiceFactory.getMessageService().sendMessage(messageRequestDTO);
	}

	public static boolean editMessage(HttpServletRequest request) {
		MessageRequestDTO messageRequestDTO = MessageRequestConverter.toConverterMessage(request);
		return MessageServiceFactory.getMessageService().editMessage(messageRequestDTO);
	}

	public static boolean deleteMessage(HttpServletRequest request) {
		MessageRequestDTO messageRequestDTO = MessageRequestConverter.toConverterMessage(request);
		return MessageServiceFactory.getMessageService().deleteMessage(messageRequestDTO);
	}

	public static ArrayList<MessageResponseDTO> getMessagesByCourseId(HttpServletRequest request) {
		CourseRequestDTO courseRequestDTO = CourseRequestConverter.toConverterCourse(request);
		return MessageServiceFactory.getMessageService().getMessagesByCourseId(courseRequestDTO);
	}
}
