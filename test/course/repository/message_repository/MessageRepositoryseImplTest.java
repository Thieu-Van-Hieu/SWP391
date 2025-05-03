package course.repository.message_repository;

import course.factory.repository.MessageRepositoryFactory;
import course.repository.itf.MessageRepository;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import config.DBContext;
import course.dto.course.CourseRequestDTO;
import course.dto.message.MessageRequestDTO;
import course.entity.MessageEntity;
import exception.course.MessageNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MessageRepositoryseImplTest {

	DBContext db = DBContext.getInstance();

	@Before
	public void beginTransaction() throws SQLException {
		db.setDatabaseTest();
		db.getConnection().setAutoCommit(false);
	}

	@After
	public void rollbackTransaction() throws SQLException {
		if (db.getConnection() != null && !db.getConnection().isClosed()) {
			db.getConnection().rollback();
			db.getConnection().close();
		}
	}
	
	@Test
	public void testGetMessageByMessageId() {
		MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
		messageRequestDTO.setMessageId(1);
		
		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		MessageEntity messageEntity = messageRepository.getMessageByMessageId(messageRequestDTO);
		
		assertEquals(1, messageEntity.getMessageId());
		assertEquals(1, messageEntity.getCourseId());
		assertEquals(1, messageEntity.getMemberId());
		assertEquals("Welcome to the course!", messageEntity.getContent());
		assertEquals(Timestamp.valueOf("2025-04-01 08:00:00.000"), messageEntity.getSendAt());
	}

	@Test
	public void testCreateMessage() {
		MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
		messageRequestDTO.setCourseId(1);
		messageRequestDTO.setMemberId(1);
		messageRequestDTO.setContent("Xin chao");
		
		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		assertTrue(messageRepository.createMessage(messageRequestDTO));

		try {
			String sql = """
				select top 1
                from messages
                order by sendAt desc
                """;
			PreparedStatement statement = db.getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			assertTrue(rs.next());
			if (rs.next()) {
				assertEquals(1, rs.getInt("courseId"));
				assertEquals(1, rs.getInt("userId"));
				assertEquals("Xin chao", rs.getString("courseId"));
			}
		} catch (Exception e) {
		}
	}

	@Test
	public void testEditMessage() {
		MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
		messageRequestDTO.setMessageId(1);
		messageRequestDTO.setContent("Xin chao");
		
		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		assertTrue(messageRepository.editMessage(messageRequestDTO));
		MessageEntity messageEntity = messageRepository.getMessageByMessageId(messageRequestDTO);
		
		assertEquals("Xin chao", messageEntity.getContent());
	}

	@Test
	public void testDeleteMessage() {
		MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
		messageRequestDTO.setMessageId(1);
		
		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		assertTrue(messageRepository.deleteMessage(messageRequestDTO));
		
		assertThrows(MessageNotFoundException.class, () -> {
			messageRepository.getMessageByMessageId(messageRequestDTO);
		});
	}

	@Test
	public void testGetMessagesByCourseId() {
		CourseRequestDTO courseRequestDTO = new CourseRequestDTO();
		courseRequestDTO.setCourseId(1);
		
		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		ArrayList<MessageEntity> messageEntities = messageRepository.getMessagesByCourseId(courseRequestDTO);
	
		assertEquals(2, messageEntities.size());
		assertEquals(1, messageEntities.get(0).getMessageId());
		assertEquals(1, messageEntities.get(0).getCourseId());
		assertEquals(1, messageEntities.get(0).getMemberId());
		assertEquals("Welcome to the course!", messageEntities.get(0).getContent());
		assertEquals(Timestamp.valueOf("2025-04-01 08:00:00.000"), messageEntities.get(0).getSendAt());
		assertEquals(2, messageEntities.get(1).getMessageId());
		assertEquals(1, messageEntities.get(1).getCourseId());
		assertEquals(3, messageEntities.get(1).getMemberId());
		assertEquals("Thank you!", messageEntities.get(1).getContent());
		assertEquals(Timestamp.valueOf("2025-04-01 08:05:00.000"), messageEntities.get(1).getSendAt());
	}

}
