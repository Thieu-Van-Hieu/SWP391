/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import repository.Impl.UserRepository;
import utils.DBContext;
import java.sql.*;

/**
 *
 * @author ngoct
 */
public class User implements UserRepository {

    @Override
    public boolean isLogin(String username, String password) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                         select * from users 
                         where username = ? and password = ?
                         """;
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            return st.executeQuery().next();
        } catch (Exception e) {
            return false;
        }
    }
}
