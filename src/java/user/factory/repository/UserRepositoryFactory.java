/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.factory.repository;

import user.repository.UserRepository;
import user.repository.impl.UserRepositoryImpl;

/**
 *
 * @author ngoct
 */
public class UserRepositoryFactory {

    public static final UserRepository userRepository = new UserRepositoryImpl();

    public static UserRepository getUserRepository() {
        return userRepository;
    }
}
