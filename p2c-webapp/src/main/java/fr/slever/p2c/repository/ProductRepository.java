package fr.slever.p2c.repository;

import org.springframework.data.repository.CrudRepository;

import fr.slever.p2c.entity.Product;

/**
 * CRUD Repository for product entity
 * 
 * @author sebastienlever
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

}
