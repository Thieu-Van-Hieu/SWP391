/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package course.repository.itf;

import course.dto.course.CourseRequestDTO;
import course.dto.message.MessageRequestDTO;
import course.entity.message.MessageEntity;
import java.util.ArrayList;

/**
 *
 * @author quann
 */
public interface MessageRepository {
	public boolean createMessage(MessageRequestDTO messageRequestDTO);
	public boolean editMessage(MessageRequestDTO messageRequestDTO);
	public boolean deleteMessage(MessageRequestDTO messageRequestDTO);
	public ArrayList<MessageEntity> getMessagesByCourseId(CourseRequestDTO courseResponseDTO);
	public MessageEntity getMessageByMessageId(MessageRequestDTO messageRequestDTO);
}
