/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.entity;

import java.util.Date;
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
public class UserInformationEntity {

    private int id;
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String studentId;
    private String address;
    private String gender;
    private Date birthday;

}
