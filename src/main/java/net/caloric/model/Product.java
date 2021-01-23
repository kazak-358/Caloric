package net.caloric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "caloric")
	private double caloric;

	@Column(name = "protein")
	private double protein;

	@Column(name = "fat")
	private double fat;

	@Column(name = "carbohydrates")
	private double carbohydrates;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCaloric() {
		return caloric;
	}

	public void setCaloric(double caloric) {
		this.caloric = caloric;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

}