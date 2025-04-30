/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.converter.login;

import jakarta.servlet.http.HttpServletRequest;
import user.dto.login.LoginRequestDTO;
import utils.RequestMapper;

public class LoginRequestConverter {

    public static LoginRequestDTO toConverterLogin(HttpServletRequest request) {
        return RequestMapper.mapToObject(request, LoginRequestDTO.class);
    }
}
