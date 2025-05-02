/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.converter.changeInfor;

import jakarta.servlet.http.HttpServletRequest;
import user.dto.changeInfor.ChangePasswordRequestDTO;
import utils.RequestMapper;

/**
 *
 * @author hunggt1572004
 */
public class changePasswordRequestConverter {
    public static ChangePasswordRequestDTO toConvertChangePassword(HttpServletRequest request){
        return RequestMapper.mapToObject(request, ChangePasswordRequestDTO.class);
    }
}
