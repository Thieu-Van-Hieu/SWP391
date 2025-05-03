package user.service.itf;

import user.dto.login.LoginRequestDTO;
import user.dto.login.LoginResponseDTO;

public interface UserService {

    LoginResponseDTO isLogin(LoginRequestDTO loginRequestDTO);

}
