package com.sarvesh.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sarvesh.springboot.demo.model.VehicleDetail;



@Repository
public interface VehicleDetailRepository extends JpaRepository<VehicleDetail,Long>{

}
