/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package course.repository.member_information_repository;

import config.DBContext;
import course.dto.member_information.MemberInformationRequestDTO;
import course.entity.MemberInformationEntity;
import course.factory.repository.MemberInformationRepositoryFactory;
import course.repository.itf.MemberInformationRepository;
import exception.course.MemberInformationNotFoundException;
import java.sql.SQLException;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 *
 * @author quann
 */
public class MemberInformationRepositoryImplTest {

	DBContext db = DBContext.getInstance().getInstance();

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
	public void testGetMemberInformationByMemberInformationId() {
		MemberInformationRequestDTO memberInformationRequestDTO = new MemberInformationRequestDTO();
		memberInformationRequestDTO.setMemberId(1);
		
		MemberInformationRepository memberInformationRepository = MemberInformationRepositoryFactory.getMemberInformationRepository();
		MemberInformationEntity memberInformationEntity = memberInformationRepository.getMemberInformationByMemberInformationId(memberInformationRequestDTO);
		
		assertEquals(1, memberInformationEntity.getMemberInfomationId());
		assertEquals(1, memberInformationEntity.getMemberId());
		assertEquals("Alice Nguyen", memberInformationEntity.getName());
	
		memberInformationRequestDTO.setMemberId(4);
		assertThrows(MemberInformationNotFoundException.class, () -> {
			memberInformationRepository.getMemberInformationByMemberInformationId(memberInformationRequestDTO);
		});
	}
	
}
