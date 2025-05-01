/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.repository.impl;

import config.DBContext;
import java.sql.*;
import user.dto.login.LoginRequestDTO;
import user.entity.UserEntity;
import user.repository.itf.UserRepository;

/**
 *
 * @author ngoct
 */
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
}
