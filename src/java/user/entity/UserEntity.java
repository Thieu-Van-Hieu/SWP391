/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ngoct
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private int userId;
    private String username;
    private String password;
    private int roleId;

    public int getUserId() {
        return userId;
    }
}
