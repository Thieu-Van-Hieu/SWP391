/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.dto.login;

import java.util.Date;

/**
 *
 * @author ngoct
 */
public class LoginResponseDTO {

    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private String studentId;
    private String email;
    private String gender;
    private Date birthday;
    private int roleId;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(Builder build) {
        this.userId = build.userId;
        this.username = build.username;
        this.firstName = build.firstName;
        this.lastName = build.lastName;
        this.studentId = build.studentId;
        this.email = build.email;
        this.gender = build.gender;
        this.birthday = build.birthday;
        this.roleId = build.roleId;
    }

    public class Builder {

        private int userId;
        private String username;
        private String firstName;
        private String lastName;
        private String studentId;
        private String email;
        private String gender;
        private Date birthday;
        private int roleId;

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setRoleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setBirthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public LoginResponseDTO build() {
            return new LoginResponseDTO(this);
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
