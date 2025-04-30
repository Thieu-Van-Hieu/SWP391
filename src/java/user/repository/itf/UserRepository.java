/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package user.repository.itf;

import user.dto.LoginRequestDTO;

/**
 *
 * @author ngoct
 */
public interface UserRepository {

    boolean isLogin(LoginRequestDTO loginRequestDTO);
}
