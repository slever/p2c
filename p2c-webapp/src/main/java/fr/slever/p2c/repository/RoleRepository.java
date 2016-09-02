package fr.slever.p2c.repository;

import org.springframework.data.repository.CrudRepository;

import fr.slever.p2c.entity.Role;

/**
 * CRUD Repository for user's role entity
 * 
 * @author sebastienlever
 *
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

  /**
   * @param name
   * @return the role by name
   */
  Role findByName(String name);
}
