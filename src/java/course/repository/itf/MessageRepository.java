package course.repository.itf;

import course.dto.course.CourseRequestDTO;
import course.dto.message.MessageRequestDTO;
import course.entity.MessageEntity;
import java.util.ArrayList;

public interface MessageRepository {
	public boolean createMessage(MessageRequestDTO messageRequestDTO);
	public boolean editMessage(MessageRequestDTO messageRequestDTO);
	public boolean deleteMessage(MessageRequestDTO messageRequestDTO);
	public ArrayList<MessageEntity> getMessagesByCourseId(CourseRequestDTO courseResponseDTO);
	public MessageEntity getMessageByMessageId(MessageRequestDTO messageRequestDTO);
}
