package com.selloramotsheki.finddoctors.service.User;

import com.selloramotsheki.finddoctors.Dto.UserDto;
import com.selloramotsheki.finddoctors.model.User;
import com.selloramotsheki.finddoctors.request.CreateUserRequest;
import com.selloramotsheki.finddoctors.request.UpdateUserRequest;

import java.util.List;

public interface IUserService {
    User createUser(CreateUserRequest request);
    User getUserById(Long userId);
    //List<User> getAllUsers();
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);
    UserDto convertUserToDto(User user);
}
