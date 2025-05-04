package user.factory.service;

import user.service.itf.UserService;
import user.service.impl.UserServiceImpl;

public class UserServiceFactory {

    private static final UserService userService = new UserServiceImpl();

    public static UserService getUserService() {
        return userService;
    }

}
