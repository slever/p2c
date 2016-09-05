package fr.slever.p2c.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import fr.slever.p2c.entity.User;

/**
 * Mostly used as a facade so all controllers related to the User class have a
 * single point of entry.
 * 
 * @author sebastienlever
 */
public interface UserService extends UserDetailsService {

  /**
   * Find user by login.
   * 
   * @param login
   * @return the user with the associated login.
   */
  public User findUserByLogin(String login);

  /**
   * Find all users present in the database.
   * 
   * @return All users
   */
  public List<User> findAllUsers();

  /**
   * Save the user added in database.
   * 
   * @param user
   *          The user added
   */
  public User addUser(User user);

  /**
   * Delete a user from database.
   * 
   * @param login
   *          the login of the user login to delete
   */
  public void deleteUser(String login);

}
