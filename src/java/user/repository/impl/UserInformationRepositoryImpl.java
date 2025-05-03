package user.repository.impl;

import config.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import user.entity.UserInformationEntity;
import user.repository.itf.UserInformationRepository;

public class UserInformationRepositoryImpl implements UserInformationRepository {

    @Override
    public UserInformationEntity getInformationById(int id) {
        DBContext db = DBContext.getInstance();
        UserInformationEntity result = new UserInformationEntity();
        try {
            String sql = """
                       select * from user_informations ui
                       where ui.userId = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result.setFirstName(rs.getString("firstName"));
                result.setUserId(rs.getInt("userId"));
                result.setStudentId(rs.getString("studentId"));
                result.setGender(rs.getString("gender"));
                result.setEmail(rs.getString("email"));
                result.setLastName(rs.getString("lastName"));
                result.setAddress(rs.getString("address"));
                result.setBirthday(rs.getDate("birthday"));
            }
        } catch (Exception e) {
            return new UserInformationEntity();
        }
        return result;
    }
}
