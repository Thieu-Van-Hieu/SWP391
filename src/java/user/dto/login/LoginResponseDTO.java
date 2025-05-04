package user.dto.login;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
