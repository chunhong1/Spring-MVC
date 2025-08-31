package com.rungroup.Web.service;

import com.rungroup.Web.dto.RegistrationDto;
import com.rungroup.Web.models.UserEntity;

public interface UserService
{
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
