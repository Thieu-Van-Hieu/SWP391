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
	public boolean createMessage(MessageRequestDTO messageDTO);
	public boolean editMessage(MessageRequestDTO messageDTO);
	public boolean deleteMessage(MessageRequestDTO messageDTO);
	public ArrayList<MessageEntity> getMessagesByCourseId(CourseRequestDTO courseResponseDTO);
}
