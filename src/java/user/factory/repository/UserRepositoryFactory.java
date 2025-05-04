package user.factory.repository;

import user.repository.impl.UserInformationRepositoryImpl;
import user.repository.itf.UserRepository;
import user.repository.impl.UserRepositoryImpl;
import user.repository.itf.UserInformationRepository;

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
