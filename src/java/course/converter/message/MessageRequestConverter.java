/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package course.converter.message;

import course.dto.message.MessageRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import utils.RequestMapper;

public class MessageRequestConverter {
	public static MessageRequestDTO toConverterMessage(HttpServletRequest request) {
		return RequestMapper.mapToObject(request, MessageRequestDTO.class);
	}
}
