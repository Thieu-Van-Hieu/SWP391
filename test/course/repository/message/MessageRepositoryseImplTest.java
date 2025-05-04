package course.repository.message;

import config.DBContext;
import course.dto.course.CourseRequestDTO;
import course.dto.message.MessageRequestDTO;
import course.entity.MessageEntity;
import course.factory.repository.MessageRepositoryFactory;
import course.repository.itf.MessageRepository;
import exception.course.MessageNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		
		MessageEntity messageEntityExpected = new MessageEntity(1, 1, 1, "Welcome to the course!", Timestamp.valueOf("2025-04-01 08:00:00.000"));
		
		Assert.assertEquals(messageEntityExpected, messageEntity);
	}

	@Test
	public void testCreateMessage() {
		MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
		messageRequestDTO.setCourseId(1);
		messageRequestDTO.setMemberId(1);
		messageRequestDTO.setContent("Xin chao");
		
		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		Assert.assertTrue(messageRepository.createMessage(messageRequestDTO));

		try {
			String sql = """
				select top 1
                from messages
                order by sendAt desc
                """;
			PreparedStatement statement = db.getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			Assert.assertTrue(rs.next());
			if (rs.next()) {
				Assert.assertEquals(1, rs.getInt("courseId"));
				Assert.assertEquals(1, rs.getInt("userId"));
				Assert.assertEquals("Xin chao", rs.getString("courseId"));
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
		Assert.assertTrue(messageRepository.editMessage(messageRequestDTO));
		MessageEntity messageEntity = messageRepository.getMessageByMessageId(messageRequestDTO);
		
		Assert.assertEquals("Xin chao", messageEntity.getContent());
	}

	@Test
	public void testDeleteMessage() {
		MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
		messageRequestDTO.setMessageId(1);
		
		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		Assert.assertTrue(messageRepository.deleteMessage(messageRequestDTO));
		
		Assert.assertThrows(MessageNotFoundException.class, () -> {
			messageRepository.getMessageByMessageId(messageRequestDTO);
		});
	}

	@Test
	public void testGetMessagesByCourseId() {
		CourseRequestDTO courseRequestDTO = new CourseRequestDTO();
		courseRequestDTO.setCourseId(1);
		
		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		ArrayList<MessageEntity> messageEntities = messageRepository.getMessagesByCourseId(courseRequestDTO);
	
		ArrayList<MessageEntity> messageEntitesExpected = new ArrayList<>(List.of(
				new MessageEntity(1, 1, 1, "Welcome to the course!", Timestamp.valueOf("2025-04-01 08:00:00.000")),
				new MessageEntity(2, 1, 3, "Thank you!", Timestamp.valueOf("2025-04-01 08:05:00.000"))
		));
		
		Assert.assertEquals(messageEntitesExpected, messageEntities);
	}

}
