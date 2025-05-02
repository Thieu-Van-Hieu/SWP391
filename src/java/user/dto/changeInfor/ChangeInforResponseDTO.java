/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user.dto.changeInfor;

/**
 *
 * @author hunggt1572004
 */
public class ChangeInforResponseDTO {

    private String status;
    private String updateAt;

    public ChangeInforResponseDTO() {
    }

    public ChangeInforResponseDTO(Builder build) {
        this.status = build.status;
        this.updateAt = build.updateAt;
        
    }

    public static class Builder {

        private String status;
        private String updateAt;

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public ChangeInforResponseDTO build() {
            return new ChangeInforResponseDTO(this);
        }
    }

    public String getStatus() {
        return status;
    }

    public String getUpdateAt() {
        return updateAt;
    }
}
