package user.repository.impl;

import config.DBContext;
import exceptions.DataException;
import java.sql.*;
import java.time.LocalDateTime;
import user.dto.changeInfor.ChangePasswordRequestDTO;
import user.dto.changeInfor.UserEditLogRequestDTO;
import user.dto.login.LoginRequestDTO;
import user.entity.UserEntity;
import user.repository.itf.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public UserEntity isLogin(LoginRequestDTO loginRequestDTO) {
        DBContext db = DBContext.getInstance();
        UserEntity result = new UserEntity();
        try {
            String sql = """
                         select * from users 
                         where username = ? and password = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, loginRequestDTO.getUsername());
            st.setString(2, loginRequestDTO.getPassword());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result.setUsername(rs.getString("username"));
                result.setUserId(rs.getInt("id"));
                result.setRoleId(rs.getInt("role"));
            }
        } catch (Exception e) {
            return new UserEntity();
        }
        return result;
    }

    @Override
    public boolean changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        DBContext db = DBContext.getInstance();
        LocalDateTime changeAt = LocalDateTime.now();
        String oldPassword = getPasswordById(changePasswordRequestDTO.getUserId());
        try {
            String sql = """
                         update users
                         set password = ?
                         where id = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, changePasswordRequestDTO.getPassword());
            st.setInt(2, changePasswordRequestDTO.getUserId());
            int rs = st.executeUpdate();
            if (rs > 0) {
                savingToHistory(new UserEditLogRequestDTO(changePasswordRequestDTO.getUserId(), "password", oldPassword, changePasswordRequestDTO.getPassword(), changeAt));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getPasswordById(int id) throws DataException {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         select password from users
                         where id = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString("password");
            } else {
                throw new DataException("User with ID " + id + " not found.");
            }
        } catch (Exception e) {
            throw new DataException("Error get password for user ID: " + id, e);
        }
    }

    @Override
    public void savingToHistory(UserEditLogRequestDTO userEditLogRequestDTO) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         insert into user_edit_logs
                         values (?, ?, ?, ?, ?);
                         """;

            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setInt(1, userEditLogRequestDTO.getUserId());
            st.setString(2, userEditLogRequestDTO.getChangedField());
            st.setNString(3, userEditLogRequestDTO.getOldValue());
            st.setNString(4, userEditLogRequestDTO.getNewValue());
            st.setObject(5, userEditLogRequestDTO.getChangeAt());
            st.executeUpdate();
        } catch (Exception e) {
            throw new DataException("Can't insert into user_edit_logs for userID " + userEditLogRequestDTO.getUserId(), e);
        }
    }

}
