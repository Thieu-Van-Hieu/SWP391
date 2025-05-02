/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.dto.changeInfor;

import java.time.LocalDateTime;

/**
 *
 * @author hunggt1572004
 */
public class UserEditLogRequestDTO {
    
    private int userId;
    private String changedField;
    private String oldValue;
    private String newValue;
    private LocalDateTime changeAt;

    public UserEditLogRequestDTO(int userId, String changedField, String oldValue, String newValue, LocalDateTime changeAt) {
        this.userId = userId;
        this.changedField = changedField;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changeAt = changeAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getChangedField() {
        return changedField;
    }

    public void setChangedField(String changedField) {
        this.changedField = changedField;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public LocalDateTime getChangeAt() {
        return changeAt;
    }

    public void setChangeAt(LocalDateTime changeAt) {
        this.changeAt = changeAt;
    }
    
    
}
