package course.repository.memberinformation;

import config.DBContext;
import course.dto.member_information.MemberInformationRequestDTO;
import course.entity.MemberInformationEntity;
import course.factory.repository.MemberInformationRepositoryFactory;
import course.repository.itf.MemberInformationRepository;
import exception.course.MemberInformationNotFoundException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		
		MemberInformationEntity memberInformationEntityExpected = new MemberInformationEntity(1, 1, "Alice Nguyen");
		
		Assert.assertEquals(memberInformationEntityExpected, memberInformationEntityExpected);
	
		memberInformationRequestDTO.setMemberId(4);
		Assert.assertThrows(MemberInformationNotFoundException.class, () -> {
			memberInformationRepository.getMemberInformationByMemberInformationId(memberInformationRequestDTO);
		});
	}
	
}
