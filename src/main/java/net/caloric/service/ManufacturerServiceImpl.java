package net.caloric.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.caloric.model.Manufacturer;
import net.caloric.repository.ManufacturerRepository;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Override
	public void create(Manufacturer manufacturer) {
		manufacturerRepository.save(manufacturer);
	}

	@Override
	public List<Manufacturer> readAll() {
		return manufacturerRepository.findAll().stream().sorted(Comparator.comparing(Manufacturer::getId))
				.collect(Collectors.toList());
	}

	@Override
	public Manufacturer read(long id) {
		return manufacturerRepository.getOne(id);
	}

	@Override
	public boolean update(Manufacturer manufacturer, String name) {
		if (manufacturerRepository.existsById(manufacturer.getId())) {
			manufacturer.setName(name);
			manufacturerRepository.save(manufacturer);
			return true;
		}

		return false;
	}

	@Override
	public boolean delete(long id) {
		if (manufacturerRepository.existsById(id)) {
			manufacturerRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public List<Manufacturer> search(String keyword) {
		return manufacturerRepository.search(keyword);
	}

}