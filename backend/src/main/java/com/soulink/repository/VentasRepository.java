// VentasRepository.java
package com.soulink.repository;

import com.soulink.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentasRepository extends JpaRepository<Venta, Long> {}
