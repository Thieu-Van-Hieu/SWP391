/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package course.repository.message_repository;

import course.factory.repository.MessageRepositoryFactory;
import course.repository.itf.MessageRepository;
import org.junit.*;
import static org.junit.Assert.*;
import config.DBContext;
import course.dto.message.MessageRequestDTO;
import java.sql.*;

/**
 *
 * @author quann
 */
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
	public void testCreateMessage() {
		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		MessageRequestDTO messageRequestDTO = new MessageRequestDTO(0, 1, 1, "Xin chao");
		messageRepository.createMessage(messageRequestDTO);

		try {
			String sql = """
				select top 1
                from messages
                order by sendAt
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
		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		MessageRequestDTO messageRequestDTO = new MessageRequestDTO(0, 1, 1, "Xin chao");
		messageRepository.createMessage(messageRequestDTO);

		try {
			String sql = """
				select top 1
                from messages
                order by sendAt
                """;
			PreparedStatement statement = db.getConnection().prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				if (rs.getInt("courseId") == 1 && rs.getInt("memberId") == 1 && "Xin chao".equals(rs.getString("content"))) {
					messageRequestDTO.setMessageId(rs.getInt("id"));
				}
			}
		} catch (Exception e) {
		}
	}

	@Test
	public void testDeleteMessage() {
	}

	@Test
	public void testGetMessagesByCourseId() {
	}

}
