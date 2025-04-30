/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.factory.service;

import user.service.itf.UserService;
import user.service.impl.UserServiceImpl;

/**
 *
 * @author ngoct
 */
public class UserServiceFactory {

    private static final UserService userService = new UserServiceImpl();

    public static UserService getUserService() {
        return userService;
    }

}
