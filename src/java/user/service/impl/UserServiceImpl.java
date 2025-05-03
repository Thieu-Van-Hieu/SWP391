package user.service.impl;

import user.converter.login.LoginResponseConverter;
import user.dto.login.LoginRequestDTO;
import user.dto.login.LoginResponseDTO;
import user.entity.UserEntity;
import user.factory.repository.UserRepositoryFactory;
import user.service.itf.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public LoginResponseDTO isLogin(LoginRequestDTO loginRequestDTO) {
        UserEntity userEntity = UserRepositoryFactory.getUserRepository().isLogin(loginRequestDTO);
        LoginResponseDTO result = LoginResponseConverter.toLoginResponDTOConverter(userEntity);
        return result;
    }
}
