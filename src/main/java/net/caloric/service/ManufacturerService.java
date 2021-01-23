package net.caloric.service;

import java.util.List;

import net.caloric.model.Manufacturer;

public interface ManufacturerService {

	void create(Manufacturer manufacturer);

	List<Manufacturer> readAll();

	Manufacturer read(long id);

	boolean update(Manufacturer manufacturer, String name);

	boolean delete(long id);

	public List<Manufacturer> search(String keyword);
}