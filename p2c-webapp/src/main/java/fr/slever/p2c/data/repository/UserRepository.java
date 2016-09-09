/**
 * Copyright 2016 sebastien lever
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package fr.slever.p2c.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import fr.slever.p2c.data.entity.User;

/**
 * CRUD Repository for user entity
 * 
 * @author sebastienlever
 *
 */
// RepositoryRestResource(path = "users",
// collectionResourceDescription = @Description("List of users of the
// application"),
// collectionResourceRel = "users",
// itemResourceRel = "user",
// itemResourceDescription = @Description("A user with profile (ex: consumer,
// producer, transporter)"))
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  /**
   * Count the users with specified <i>login</i>
   * 
   * @param login
   * @return
   */
  @Query("SELECT COUNT(u) FROM User u WHERE u.login=:login")
  Integer countUsersWithLogin(@Param("login") String login);

  /**
   * @param login
   * @return the user by login
   */
  Optional<User> findByLogin(@Param("login") String login);

  /**
   * @param id
   *          the id of the user
   * @return the user by id
   */
  User findById(@Param("id") Long id);

}
