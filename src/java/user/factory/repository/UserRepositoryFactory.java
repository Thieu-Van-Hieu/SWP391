/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.factory.repository;

import user.repository.impl.UserInformationRepositoryImpl;
import user.repository.itf.UserRepository;
import user.repository.impl.UserRepositoryImpl;
import user.repository.itf.UserInformationRepository;

/**
 *
 * @author ngoct
 */
public class UserRepositoryFactory {

    private static final UserRepository userRepository = new UserRepositoryImpl();

    private static final UserInformationRepository userInformationRepository = new UserInformationRepositoryImpl();

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static UserInformationRepository getUserInformationRepository() {
        return userInformationRepository;
    }
}
