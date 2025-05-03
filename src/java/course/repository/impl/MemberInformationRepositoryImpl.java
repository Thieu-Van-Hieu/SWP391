/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package course.repository.impl;

import config.DBContext;
import course.dto.member_information.MemberInformationRequestDTO;
import course.entity.MemberInformationEntity;
import course.repository.itf.MemberInformationRepository;
import exception.course.MemberInformationNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberInformationRepositoryImpl implements MemberInformationRepository {

	@Override
	public MemberInformationEntity getMemberInformationByMemberInformationId(MemberInformationRequestDTO memberInformationRequestDTO) throws MemberInformationNotFoundException {
		DBContext db = DBContext.getInstance();
		try {
			String sql = """
				select *
                from member_informations
                where memberId = ?
                """;
			PreparedStatement statement = db.getConnection().prepareStatement(sql);
			statement.setInt(1, memberInformationRequestDTO.getMemberId());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				int memberInformationId = rs.getInt("id");
				int memberId = rs.getInt("memberId");
				String name = rs.getString("name");
				MemberInformationEntity memberInformationEntity = new MemberInformationEntity(memberInformationId, memberId, name);
				return memberInformationEntity;
			}
		} catch (Exception e) {
		}
		throw new MemberInformationNotFoundException();
	}

}
