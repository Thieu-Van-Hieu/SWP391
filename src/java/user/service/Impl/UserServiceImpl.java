/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.service.impl;

import user.dto.LoginRequestDTO;
import user.factory.repository.UserRepositoryFactory;
import user.service.UserService;

/**
 *
 * @author ngoct
 */
public class UserServiceImpl implements UserService {
    @Override
    public boolean isLogin(LoginRequestDTO loginRequestDTO) {
        return UserRepositoryFactory.getUserRepository().isLogin(loginRequestDTO);
    }
}
