package net.caloric.service;

import java.util.List;

import net.caloric.model.Manufacturer;

public interface ManufacturerService {

	void create(Manufacturer client);

	List<Manufacturer> readAll();

	Manufacturer read(long id);

	boolean update(Manufacturer client, String name);

	boolean delete(long id);
}