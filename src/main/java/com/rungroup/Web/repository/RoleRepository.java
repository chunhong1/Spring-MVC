package com.rungroup.Web.repository;

import com.rungroup.Web.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Role findByName(String name);
}
