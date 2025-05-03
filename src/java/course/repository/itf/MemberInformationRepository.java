/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package course.repository.itf;

import course.dto.member_information.MemberInformationRequestDTO;
import course.entity.MemberInformationEntity;

public interface MemberInformationRepository {
	public MemberInformationEntity getMemberInformationByMemberInformationId(MemberInformationRequestDTO memberInformationRequestDTO);
}
