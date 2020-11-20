package com.chat.service;

import com.chat.model.UserDTO;
import com.chat.storage.UserStorage;
import com.chat.utility.AppConstants;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public int findUser(final UserDTO userDTO) {
        boolean isExists = UserStorage.getInstance().getUsers().contains(userDTO.getUserName());
        if (isExists && userDTO.getPassword().equals(AppConstants.COMMON_PASSWORD)) {
            return 200;
        }
        return 0;
    }
}
