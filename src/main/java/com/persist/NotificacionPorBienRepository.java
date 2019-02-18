package com.persist;

import com.domain.NotificacionPorBien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionPorBienRepository extends JpaRepository<NotificacionPorBien, Long>{}
 

