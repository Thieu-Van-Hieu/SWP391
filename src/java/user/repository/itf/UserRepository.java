package user.repository.itf;

import exceptions.DataException;
import user.dto.changeInfor.ChangePasswordRequestDTO;
import user.dto.changeInfor.UserEditLogRequestDTO;
import user.dto.login.LoginRequestDTO;
import user.entity.UserEntity;

public interface UserRepository {

    UserEntity isLogin(LoginRequestDTO loginRequestDTO);
    
    boolean changePassword(ChangePasswordRequestDTO changePasswordRequestDTO);
    
    String getPasswordById(int id) throws DataException;
    
    void savingToHistory(UserEditLogRequestDTO userEditLogRequestDTO);
}
