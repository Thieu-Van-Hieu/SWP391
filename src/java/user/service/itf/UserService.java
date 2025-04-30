/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package user.service.itf;

import user.dto.login.LoginRequestDTO;
import user.dto.login.LoginResponseDTO;

/**
 *
 * @author ngoct
 */
public interface UserService {

    LoginResponseDTO isLogin(LoginRequestDTO loginRequestDTO);

}
