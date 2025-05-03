package course.converter.message;

import course.dto.message.MessageRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import utils.RequestMapper;

public class MessageRequestConverter {
	public static MessageRequestDTO toConverterMessage(HttpServletRequest request) {
		return RequestMapper.mapToObject(request, MessageRequestDTO.class);
	}
}
