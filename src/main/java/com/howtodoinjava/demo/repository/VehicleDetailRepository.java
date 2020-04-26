package com.howtodoinjava.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.VehicleDetail;



@Repository
public interface VehicleDetailRepository extends JpaRepository<VehicleDetail,Long>{

}
