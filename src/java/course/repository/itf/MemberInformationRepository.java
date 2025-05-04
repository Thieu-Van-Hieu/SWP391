package course.repository.itf;

import course.dto.member_information.MemberInformationRequestDTO;
import course.entity.MemberInformationEntity;

public interface MemberInformationRepository {
	public MemberInformationEntity getMemberInformationByMemberInformationId(MemberInformationRequestDTO memberInformationRequestDTO);
}
