package com.rungroup.Web.repository;

import com.rungroup.Web.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);

    UserEntity findFirstByUsername(String username);
}
