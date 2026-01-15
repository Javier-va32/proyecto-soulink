// RolesRepository.java
package com.soulink.repository;

import com.soulink.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Rol, Long> {}
