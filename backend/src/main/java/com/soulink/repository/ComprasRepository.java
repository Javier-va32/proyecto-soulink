// ComprasRepository.java
package com.soulink.repository;

import com.soulink.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprasRepository extends JpaRepository<Compra, Long> {}
