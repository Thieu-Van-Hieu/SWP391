package user.repository.itf;

import user.dto.login.LoginRequestDTO;
import user.entity.UserEntity;

public interface UserRepository {

    UserEntity isLogin(LoginRequestDTO loginRequestDTO);

}
