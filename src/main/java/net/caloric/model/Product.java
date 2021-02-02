package net.caloric.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "manufacturer_product", joinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_manufacturer", referencedColumnName = "id"))
	@JsonManagedReference
	private Set<Manufacturer> manufacturers = new HashSet<>();

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

	public Set<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(Set<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public void addManufacturer(Manufacturer manufacturer) {
		manufacturers.add(manufacturer);
		manufacturer.getProducts().add(this);
	}

	public void removeManufacturer(Manufacturer manufacturer) {
		manufacturers.remove(manufacturer);
		manufacturer.getProducts().remove(this);
	}

	public void removeManufacturers() {
		for (Manufacturer manufacturer : new HashSet<>(manufacturers)) {
			removeManufacturer(manufacturer);
		}
	}

}