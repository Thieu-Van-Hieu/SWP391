/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package course.service.itf;

import course.dto.course.CourseRequestDTO;
import course.dto.message.MessageRequestDTO;
import course.dto.message.MessageResponseDTO;
import java.util.ArrayList;

/**
 *
 * @author quann
 */
public interface MessageService {
	public boolean sendMessage(MessageRequestDTO messageDTO);
	public boolean editMessage(MessageRequestDTO messageDTO);
	public boolean deleteMessage(MessageRequestDTO messageDTO);
	public ArrayList<MessageResponseDTO> getMessagesByCourseId(CourseRequestDTO courseResponseDTO);
}
