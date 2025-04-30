/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.converter;

import jakarta.servlet.http.HttpServletRequest;
import user.dto.RegisterDTO;
import utils.RequestMapper;

/**
 *
 * @author ngoct
 */
public class RegisterRequestConverter {

    public static RegisterDTO toConvertBasicInformation(HttpServletRequest request) {
        RegisterDTO temp = RequestMapper.mapToObject(request, RegisterDTO.class);
        return temp;
    }

}
