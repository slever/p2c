package fr.slever.p2c.repository;

import org.springframework.data.repository.CrudRepository;

import fr.slever.p2c.entity.User;

/**
 * CRUD Repository for user entity
 * 
 * @author sebastienlever
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

  /**
   * @return the user by login
   */
  User findByLogin(String login);

  /**
   * @return the user by id
   */
  User findById(Long id);

}
