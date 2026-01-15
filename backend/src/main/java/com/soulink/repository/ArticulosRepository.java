// ArticulosRepository.java
package com.soulink.repository;

import com.soulink.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticulosRepository extends JpaRepository<Articulo, Long> {}
