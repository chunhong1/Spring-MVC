package com.rungroup.Web.service.impl;

import com.rungroup.Web.dto.RegistrationDto;
import com.rungroup.Web.models.Role;
import com.rungroup.Web.models.UserEntity;
import com.rungroup.Web.repository.RoleRepository;
import com.rungroup.Web.repository.UserRepository;
import com.rungroup.Web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto)
    {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());

        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
}
