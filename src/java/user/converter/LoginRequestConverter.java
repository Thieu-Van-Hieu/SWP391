/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.converter;

import jakarta.servlet.http.HttpServletRequest;
import user.dto.LoginRequestDTO;
import utils.RequestMapper;

/**
 *
 * @author ngoct
 */
public class LoginRequestConverter {

    public static LoginRequestDTO fromHttpServletRequest(HttpServletRequest request) {
        return RequestMapper.mapToObject(request, LoginRequestDTO.class);
    }
}
