package user.repository.itf;

import user.entity.UserInformationEntity;

public interface UserInformationRepository {

    UserInformationEntity getInformationById(int id);
}
