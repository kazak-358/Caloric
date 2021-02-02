package net.caloric.dto;
import java.util.HashSet;
import java.util.Set;
 
public class ProductDto {
	private Long id;
	private String name;
	private double caloric;
	private double protein;
	private double fat;
	private double carbohydrates;
	private Set<String> manufacturers = new HashSet<>();

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

	public Set<String> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(Set<String> manufacturers) {
		this.manufacturers = manufacturers;
	}

}