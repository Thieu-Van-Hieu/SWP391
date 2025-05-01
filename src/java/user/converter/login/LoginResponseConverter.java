/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.converter.login;

import user.dto.login.LoginResponseDTO;
import user.entity.UserEntity;
import user.entity.UserInformationEntity;
import user.factory.repository.UserRepositoryFactory;
import utils.Mapper;

/**
 *
 * @author ngoct
 */
public class LoginResponseConverter {

    public static LoginResponseDTO toLoginResponDTOConverter(UserEntity userEntity) {
        UserInformationEntity userInformationEntity = UserRepositoryFactory.getUserInformationRepository().getInformationById(userEntity.getUserId());
        LoginResponseDTO result = Mapper.mapToObject(userInformationEntity, LoginResponseDTO.class);
        result = Mapper.mapObjectIgnoreNull(result, userEntity);
        return result;

    }
}
