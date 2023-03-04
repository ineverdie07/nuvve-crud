package com.nuvve.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nuvve.model.VehiculeEntity;

public interface VehiculeRepository extends JpaRepository<VehiculeEntity, Long> {
	  List<VehiculeEntity> findByCompany(String company);
	  List<VehiculeEntity> findByModelContaining(String model);
}