/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package user.repository.itf;

import user.entity.UserInformationEntity;

/**
 *
 * @author ngoct
 */
public interface UserInformationRepository {

    UserInformationEntity getInformationById(int id);
}
