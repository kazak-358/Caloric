package net.caloric.service;

import java.util.List;

import net.caloric.model.Manufacturer;

public interface ManufacturerService {

	public List<Manufacturer> readAll();

	public Manufacturer read(long id);

	public boolean delete(long id);

	public List<Manufacturer> search(String keyword);

	public Manufacturer save(Manufacturer manufacturer);
}