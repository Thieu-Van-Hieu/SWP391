/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.repository.impl;

import config.DBContext;
import java.sql.*;
import user.dto.LoginRequestDTO;
import user.repository.UserRepository;

/**
 *
 * @author ngoct
 */
public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean isLogin(LoginRequestDTO loginRequestDTO) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         select * from users 
                         where username = ? and password = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, loginRequestDTO.getUsername());
            st.setString(2, loginRequestDTO.getPassword());
            return st.executeQuery().next();
        } catch (Exception e) {
            return false;
        }
    }
}
