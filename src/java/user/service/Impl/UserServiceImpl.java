/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.service.impl;

import user.converter.login.LoginResponseConverter;
import user.dto.login.LoginRequestDTO;
import user.dto.login.LoginResponseDTO;
import user.entity.UserEntity;
import user.factory.repository.UserRepositoryFactory;
import user.service.itf.UserService;

/**
 *
 * @author ngoct
 */
public class UserServiceImpl implements UserService {

    @Override
    public LoginResponseDTO isLogin(LoginRequestDTO loginRequestDTO) {
        UserEntity userEntity = UserRepositoryFactory.getUserRepository().isLogin(loginRequestDTO);
        LoginResponseDTO result = LoginResponseConverter.toLoginResponDTOConverter(userEntity);
        return result;
    }
}
