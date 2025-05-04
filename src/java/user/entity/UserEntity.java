package user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
