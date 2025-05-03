package user.converter.login;

import jakarta.servlet.http.HttpServletRequest;
import user.dto.login.LoginRequestDTO;
import utils.RequestMapper;

public class LoginRequestConverter {

    public static LoginRequestDTO toConverterLogin(HttpServletRequest request) {
        return RequestMapper.mapToObject(request, LoginRequestDTO.class);
    }
}
