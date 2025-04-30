/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.facade;

import jakarta.servlet.http.HttpServletRequest;
import user.converter.LoginRequestConverter;
import user.converter.RegisterRequestConverter;
import user.dto.LoginRequestDTO;
import user.dto.RegisterDTO;
import user.factory.service.UserServiceFactory;

/**
 *
 * @author ngoct
 */
public class UserFacade {

    public static boolean isLogin(HttpServletRequest request) {
        LoginRequestDTO loginRequest = LoginRequestConverter.toConverterLogin(request);
        return UserServiceFactory.getUserService().isLogin(loginRequest);
    }

    public static boolean isRegister(HttpServletRequest request) {
        RegisterDTO registerDTO = RegisterRequestConverter.toConvertBasicInformation(request);

        return true;
    }
}
