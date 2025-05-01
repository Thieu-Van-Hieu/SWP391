/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package course.repository.impl;

import config.DBContext;
import course.dto.course.CourseRequestDTO;
import course.dto.message.MessageRequestDTO;
import course.entity.message.MessageEntity;
import course.repository.itf.MessageRepository;
import exception.course.MessageNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class MessageRepositoryseImpl implements MessageRepository {

	@Override
	public boolean createMessage(MessageRequestDTO messageRequestDTO) {
		DBContext db = DBContext.getInstance();
		try {
			String sql = """
                insert into messages (courseId, memberId, content, sendAt)
                values (?, ?, ?, ?)
                """;
			PreparedStatement statement = db.getConnection().prepareStatement(sql);
			statement.setInt(1, messageRequestDTO.getCourseId());
			statement.setInt(2, messageRequestDTO.getMemberId());
			statement.setString(3, messageRequestDTO.getContent());
			statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean editMessage(MessageRequestDTO messageRequestDTO) {
		DBContext db = DBContext.getInstance();
		try {
			String sql = """
                update messages
                set content = ?
                where id = ?
                """;
			PreparedStatement statement = db.getConnection().prepareStatement(sql);
			statement.setString(1, messageRequestDTO.getContent());
			statement.setInt(2, messageRequestDTO.getMessageId());

			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteMessage(MessageRequestDTO messageRequestDTO) {
		DBContext db = DBContext.getInstance();
		try {
			String sql = """
                delete from messages
                where id = ? 
                """;
			PreparedStatement statement = db.getConnection().prepareStatement(sql);
			statement.setInt(1, messageRequestDTO.getMessageId());

			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ArrayList<MessageEntity> getMessagesByCourseId(CourseRequestDTO courseRequestDTO) {
		DBContext db = DBContext.getInstance();
		ArrayList<MessageEntity> messages = new ArrayList<>();
		try {
			String sql = """
                select *
                from messages
                where courseId = ?
                """;
			PreparedStatement statement = db.getConnection().prepareStatement(sql);
			statement.setInt(1, courseRequestDTO.getCourseId());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int courseId = rs.getInt("courseId");
				int memberId = rs.getInt("memberId");
				String content = rs.getString("content");
				Timestamp sendAt = rs.getTimestamp("sendAt");

				MessageEntity message = new MessageEntity(id, courseId, memberId, content, sendAt);
				messages.add(message);
			}
		} catch (Exception e) {
		}
		return messages;
	}

	@Override
	public MessageEntity getMessageByMessageId(MessageRequestDTO messageRequestDTO) throws MessageNotFoundException {
		DBContext db = DBContext.getInstance();
		try {
			String sql = """
				select *
                from messages
                where id = ?
                """;
			PreparedStatement statement = db.getConnection().prepareStatement(sql);
			statement.setInt(1, messageRequestDTO.getMessageId());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int messageId = rs.getInt("id");
				int courseId = rs.getInt("courseId");
				int memberId = rs.getInt("memberId");
				String content = rs.getString("content");
				Timestamp sendAt = rs.getTimestamp("sendAt");
				
				MessageEntity messageEntity = new MessageEntity(messageId, courseId, memberId, content, sendAt);
				return messageEntity;
			}
		} catch (Exception e) {
			
		}
		
		throw new MessageNotFoundException();
	}

}
