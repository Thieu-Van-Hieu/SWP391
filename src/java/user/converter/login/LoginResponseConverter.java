package user.converter.login;

import user.dto.login.LoginResponseDTO;
import user.entity.UserEntity;
import user.entity.UserInformationEntity;
import user.factory.repository.UserRepositoryFactory;
import utils.Mapper;

public class LoginResponseConverter {

    public static LoginResponseDTO toLoginResponDTOConverter(UserEntity userEntity) {
        UserInformationEntity userInformationEntity = UserRepositoryFactory.getUserInformationRepository().getInformationById(userEntity.getUserId());
        LoginResponseDTO result = Mapper.mapToObject(userInformationEntity, LoginResponseDTO.class);
        result = Mapper.mapObjectIgnoreNull(result, userEntity);
        return result;

    }
}
