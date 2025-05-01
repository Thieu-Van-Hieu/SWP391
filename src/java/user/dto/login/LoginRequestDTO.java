/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.dto.login;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ngoct
 */
@Getter
@Setter
public class LoginRequestDTO {

    private String username;
    private String password;

    public LoginRequestDTO() {
    }
}
