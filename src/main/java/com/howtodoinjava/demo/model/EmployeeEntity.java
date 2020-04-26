package com.howtodoinjava.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TBL_EMPLOYEES")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

//    public EmployeeEntity() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	EmployeeEntity() {
	}

	public EmployeeEntity(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", nullable = false, length = 200)
	private String email;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "employeeEntity",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true)
	private List<VehicleDetail> list;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "employeeEntity",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true)
	
	@MapKey(name = "id")
	private Map<Long, VehicleDetail> mapVehicle;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public List<VehicleDetail> getList() {
		return list;
	}

	public void setList(List<VehicleDetail> list) {
		this.list = list;
	}
	
	

	public Map<Long, VehicleDetail> getMapVehicle() {
		return mapVehicle;
	}

	public void setMapVehicle(Map<Long, VehicleDetail> mapVehicle) {
		this.mapVehicle = mapVehicle;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", list=" + list + ", mapVehicle=" + mapVehicle + "]";
	}

	
	
	

}