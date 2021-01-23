package net.caloric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.caloric.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
	@Query(value = "SELECT c FROM Manufacturer c WHERE lower(c.name) LIKE '%' || lower(:keyword) || '%'")
	public List<Manufacturer> search(@Param("keyword") String keyword);
}