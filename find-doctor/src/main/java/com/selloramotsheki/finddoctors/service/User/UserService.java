package com.selloramotsheki.finddoctors.service.User;

import com.selloramotsheki.finddoctors.Dto.UserDto;
import com.selloramotsheki.finddoctors.exceptions.AlreadyExistsException;
import com.selloramotsheki.finddoctors.exceptions.ResourceNotFound;
import com.selloramotsheki.finddoctors.model.User;
import com.selloramotsheki.finddoctors.repository.UserRepository;
import com.selloramotsheki.finddoctors.request.CreateUserRequest;
import com.selloramotsheki.finddoctors.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    user.setEmail(request.getEmail());
                    user.setPassword(req.getPassword());
                    return userRepository.save(user);
                }).orElseThrow(() -> new AlreadyExistsException("User with the " + request.getEmail() + " already exists!"));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found!"));
    }

//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setFirstName(request.getFirstName());
                    existingUser.setLastName(request.getLastName());
                    existingUser.setEmail(request.getEmail());
                    return userRepository.save(existingUser);
                }).orElseThrow(() -> new ResourceNotFound("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository :: delete, () -> {
            throw new ResourceNotFound("User not found!");
        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
