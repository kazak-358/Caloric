package net.caloric.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.caloric.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}