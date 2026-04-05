package com.auth.services.impl;

import com.auth.dtos.UserDto;
import com.auth.entities.Provider;
import com.auth.entities.User;
import com.auth.repositories.UserRepository;
import com.auth.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    //private final RoleRepository roleRepository;

    public UserDto createUser(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("User with given email already exists");
        }
        // if you have extra checks __put here...
        User user = modelMapper.map(userDto, User.class);
        user.setProvider(userDto.getProvider() != null ? userDto.getProvider() : Provider.LOCAL);
        //role assign here to user___for authorization

        //TODO:
        //assign the default role

      /*  Role role = roleRepository.findByName("ROLE_" + AppConstants.GUEST_ROLE).orElse(null);
        user.getRoles().add(role);
*/

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

}
