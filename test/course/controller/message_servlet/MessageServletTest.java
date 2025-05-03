package course.controller.message_servlet;

import config.DBContext;
import course.controller.MessageServlet;
import course.dto.message.MessageRequestDTO;
import course.dto.message.MessageResponseDTO;
import course.entity.MessageEntity;
import course.factory.repository.MessageRepositoryFactory;
import course.repository.itf.MessageRepository;
import exception.common.UnknownActionException;
import exception.course.MessageNotFoundException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import testkit.HttpServletRequestSimulator;
import testkit.HttpServletResponseSimulator;

public class MessageServletTest {

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
	public void testUnknownActionException() throws Exception {
		HttpServletRequestSimulator httpServletRequestSimulator = HttpServletRequestSimulator.builder().build();
		HttpServletResponseSimulator httpServletResponseSimulator = HttpServletResponseSimulator.builder().build();

		MessageServlet messageServlet = new MessageServlet();
		assertThrows(UnknownActionException.class, () -> {
			messageServlet.doGet(httpServletRequestSimulator.getRequest(), httpServletResponseSimulator.getResponse());
		});

		assertThrows(UnknownActionException.class, () -> {
			messageServlet.doPost(httpServletRequestSimulator.getRequest(), httpServletResponseSimulator.getResponse());
		});

	}

	@Test
	public void testDoGetWrongParam() throws Exception {
		HttpServletRequestSimulator httpServletRequestSimulator = HttpServletRequestSimulator.builder().addParam("action", "getAll").build();
		HttpServletResponseSimulator httpServletResponseSimulator = HttpServletResponseSimulator.builder().build();

		new MessageServlet().doGet(httpServletRequestSimulator.getRequest(), httpServletResponseSimulator.getResponse());

		ArrayList<MessageResponseDTO> messageResponseDTOs = (ArrayList<MessageResponseDTO>) httpServletRequestSimulator.getRequest().getAttribute("messageResponseDTOs");

		assertEquals(0, messageResponseDTOs.size());
	}

	@Test
	public void testDoGetTrueParam() throws Exception {
		HttpServletRequestSimulator httpServletRequestSimulator = HttpServletRequestSimulator.builder().addParam("action", "getAll").addParam("courseId", "1").build();
		HttpServletResponseSimulator httpServletResponseSimulator = HttpServletResponseSimulator.builder().build();

		new MessageServlet().doGet(httpServletRequestSimulator.getRequest(), httpServletResponseSimulator.getResponse());

		ArrayList<MessageResponseDTO> messageResponseDTOs = (ArrayList<MessageResponseDTO>) httpServletRequestSimulator.getRequest().getAttribute("messageResponseDTOs");

		assertEquals(2, messageResponseDTOs.size());
		assertEquals(1, messageResponseDTOs.get(0).getMessageId());
		assertEquals("Alice Nguyen", messageResponseDTOs.get(0).getSenderName());
		assertEquals("Welcome to the course!", messageResponseDTOs.get(0).getContent());
		assertEquals(Timestamp.valueOf("2025-04-01 08:00:00.000"), messageResponseDTOs.get(0).getSendAt());
		assertEquals(2, messageResponseDTOs.get(1).getMessageId());
		assertEquals("Bob Tran", messageResponseDTOs.get(1).getSenderName());
		assertEquals("Thank you!", messageResponseDTOs.get(1).getContent());
		assertEquals(Timestamp.valueOf("2025-04-01 08:05:00.000"), messageResponseDTOs.get(1).getSendAt());
	}

	@Test
	public void testDoPostWrongParam() throws Exception {
		HttpServletRequestSimulator httpServletRequestSimulator = HttpServletRequestSimulator.builder().addParam("action", "sendMessage").build();
		HttpServletResponseSimulator httpServletResponseSimulator = HttpServletResponseSimulator.builder().build();

		new MessageServlet().doPost(httpServletRequestSimulator.getRequest(), httpServletResponseSimulator.getResponse());
		assertNotNull(httpServletRequestSimulator.getRequest().getAttribute("error"));

		httpServletRequestSimulator = HttpServletRequestSimulator.builder().addParam("action", "editMessage").build();

		new MessageServlet().doPost(httpServletRequestSimulator.getRequest(), httpServletResponseSimulator.getResponse());
		assertNotNull(httpServletRequestSimulator.getRequest().getAttribute("error"));

		httpServletRequestSimulator = HttpServletRequestSimulator.builder().addParam("action", "deleteMessage").build();

		new MessageServlet().doPost(httpServletRequestSimulator.getRequest(), httpServletResponseSimulator.getResponse());
		assertNotNull(httpServletRequestSimulator.getRequest().getAttribute("error"));
	}

	@Test
	public void testDoPostSendMessage() throws Exception {
		HttpServletRequestSimulator httpServletRequestSimulator = HttpServletRequestSimulator.builder().addParam("action", "sendMessage").addParam("courseId", "1").addParam("memberId", "1").addParam("content", "Xin chao").build();
		HttpServletResponseSimulator httpServletResponseSimulator = HttpServletResponseSimulator.builder().build();

		new MessageServlet().doPost(httpServletRequestSimulator.getRequest(), httpServletResponseSimulator.getResponse());

		assertNotNull(httpServletRequestSimulator.getRequest().getAttribute("success"));

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
	public void testDoPostEditMessage() throws Exception {
		HttpServletRequestSimulator httpServletRequestSimulator = HttpServletRequestSimulator.builder().addParam("action", "editMessage").addParam("messageId", "1").addParam("content", "Xin chao").build();
		HttpServletResponseSimulator httpServletResponseSimulator = HttpServletResponseSimulator.builder().build();

		new MessageServlet().doPost(httpServletRequestSimulator.getRequest(), httpServletResponseSimulator.getResponse());

		assertNotNull(httpServletRequestSimulator.getRequest().getAttribute("success"));

		MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
		messageRequestDTO.setMessageId(1);

		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		MessageEntity messageEntity = messageRepository.getMessageByMessageId(messageRequestDTO);

		assertEquals("Xin chao", messageEntity.getContent());
	}

	@Test
	public void testDoPostDeleteMessage() throws Exception {
		HttpServletRequestSimulator httpServletRequestSimulator = HttpServletRequestSimulator.builder().addParam("action", "deleteMessage").addParam("messageId", "1").build();
		HttpServletResponseSimulator httpServletResponseSimulator = HttpServletResponseSimulator.builder().build();

		new MessageServlet().doPost(httpServletRequestSimulator.getRequest(), httpServletResponseSimulator.getResponse());

		assertNotNull(httpServletRequestSimulator.getRequest().getAttribute("success"));

		MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
		messageRequestDTO.setMessageId(1);

		MessageRepository messageRepository = MessageRepositoryFactory.getMessageRepository();
		assertThrows(MessageNotFoundException.class, () -> {
			messageRepository.getMessageByMessageId(messageRequestDTO);
		});
	}

}
