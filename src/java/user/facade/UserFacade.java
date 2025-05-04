package user.facade;

import jakarta.servlet.http.HttpServletRequest;
import user.converter.changeInfor.changePasswordRequestConverter;
import user.converter.login.LoginRequestConverter;
import user.converter.register.RegisterRequestConverter;
import user.dto.changeInfor.ChangePasswordRequestDTO;
import user.dto.login.LoginRequestDTO;
import user.dto.login.LoginResponseDTO;
import user.dto.register.RegisterDTO;
import user.factory.service.UserServiceFactory;

public class UserFacade {

    public static LoginResponseDTO isLogin(HttpServletRequest request) {
        LoginRequestDTO loginRequestDTO = LoginRequestConverter.toConverterLogin(request);
        return UserServiceFactory.getUserService().isLogin(loginRequestDTO);
    }

    public static RegisterDTO isRegister(HttpServletRequest request) {
        RegisterDTO registerDTO = RegisterRequestConverter.toConvertBasicInformation(request);
        return registerDTO;
    }
    
    public static boolean changePassword(HttpServletRequest request){
        ChangePasswordRequestDTO changePasswordRequestDTO = changePasswordRequestConverter.toConvertChangePassword(request);
        return UserServiceFactory.getUserService().changePasword(changePasswordRequestDTO);
    }
}
