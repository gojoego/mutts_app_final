package com.mutts_app.repositories;

import com.mutts_app.pojos.Role;
import org.springframework.data.jpa.repository.JpaRepository;

// repositories extend

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);

}
