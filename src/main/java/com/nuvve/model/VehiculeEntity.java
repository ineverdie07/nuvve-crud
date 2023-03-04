package com.nuvve.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicule")
public class VehiculeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "company")
	private String company;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "year")
	private int year;
	
	public VehiculeEntity() {

	}

	public VehiculeEntity(String company, String model, int year) {
		this.company = company;
		this.model = model;
		this.year =year;
	}

	public long getId() {
		return id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", Company=" + company + ", Model=" + model + ", year=" + year + "]";
	}

}
