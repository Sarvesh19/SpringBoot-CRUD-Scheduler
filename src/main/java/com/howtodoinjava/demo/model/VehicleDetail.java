package com.howtodoinjava.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "VEHICLE_DETAILS")
public class VehicleDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@Column(name = "vehcile_no")
	private String vehcile_no;

	@Column(name = "vehcile_name")
	private String vehcile_name;

	@Column(name = "vehicle_model")
	private String vehicle_model;

	
	@JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
	private EmployeeEntity employeeEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehcile_no() {
		return vehcile_no;
	}

	public void setVehcile_no(String vehcile_no) {
		this.vehcile_no = vehcile_no;
	}

	public String getVehcile_name() {
		return vehcile_name;
	}

	public void setVehcile_name(String vehcile_name) {
		this.vehcile_name = vehcile_name;
	}

	public String getVehicle_model() {
		return vehicle_model;
	}

	public void setVehicle_model(String vehicle_model) {
		this.vehicle_model = vehicle_model;
	}

	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public void setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
	}

	@Override
	public String toString() {
		return "VehicleDetail [id=" + id + ", vehcile_no=" + vehcile_no + ", vehcile_name=" + vehcile_name
				+ ", vehicle_model=" + vehicle_model + ", employeeEntity=" + employeeEntity + "]";
	}
	
	


}
