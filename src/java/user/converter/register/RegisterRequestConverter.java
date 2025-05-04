package user.converter.register;

import jakarta.servlet.http.HttpServletRequest;
import user.dto.register.RegisterDTO;
import utils.RequestMapper;

public class RegisterRequestConverter {

    public static RegisterDTO toConvertBasicInformation(HttpServletRequest request) {
        RegisterDTO basicInformation = RequestMapper.mapToObject(request, RegisterDTO.class);

        return basicInformation;
    }

}
