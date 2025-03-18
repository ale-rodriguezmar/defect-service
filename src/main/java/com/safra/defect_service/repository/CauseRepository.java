package com.safra.defect_service.repository;

import com.safra.defect_service.entity.Cause;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CauseRepository extends JpaRepository<Cause, Long> {

}
