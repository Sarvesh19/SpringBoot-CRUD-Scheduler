package com.howtodoinjava.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.EmployeeEntity;
 
@Repository
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Long> {
	
	
	@Query(value = "SELECT * FROM TBL_EMPLOYEES u WHERE u.id = :id", 
			  nativeQuery = true)
	Optional<EmployeeEntity> findEmployeeByIdNative(
			  @Param("id") Long id);
 
}
