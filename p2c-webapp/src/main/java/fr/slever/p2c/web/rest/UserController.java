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
package fr.slever.p2c.web.rest;

import static fr.slever.p2c.web.rest.constant.URI.USERS_API;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.slever.p2c.data.entity.User;
import fr.slever.p2c.exception.ResourceNotFound;
import fr.slever.p2c.service.UserService;
import fr.slever.p2c.web.rest.resource.RoleResource;
import fr.slever.p2c.web.rest.resource.RolesResource;
import fr.slever.p2c.web.rest.resource.UserResource;
import fr.slever.p2c.web.rest.resource.UsersResource;

/**
 * User Resource
 * 
 * @author sebastienlever
 */
@RestController
@RequestMapping(USERS_API)
// EnableHypermediaSupport(type = { HypermediaType.HAL })
public class UserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  /**
   * @return all registered users.
   */
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(readOnly = true)
  public ResponseEntity<UsersResource> getUsers() {
    List<UserResource> userResources = this.userService.findAllUsers().stream().map(UserResource::new)
        .collect(Collectors.toList());
    UsersResource resources = new UsersResource(userResources);
    return new ResponseEntity<>(resources, HttpStatus.OK);
  }

  /**
   * Get user for <i>login</i>
   * 
   * @param login
   * @return the user resource
   */
  @RequestMapping(value = "/{login:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(readOnly = true)
  public ResponseEntity<UserResource> getUser(@PathVariable("login") String login) {
    User user = userService.findUserByLogin(login);
    if (user == null) {
      LOGGER.warn("user not found for login {}", login);
      throw new ResourceNotFound(USERS_API + "/" + login);
    }

    return new ResponseEntity<>(new UserResource(user), HttpStatus.OK);
  }

  /**
   * URL get for a particular registered user.
   * 
   * @return List<UserDTO> all registered users.
   */
  @RequestMapping(value = "/{login:.+}/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(readOnly = true)
  public ResponseEntity<RolesResource> getUserRoles(@PathVariable("login") String login) {
    User user = userService.findUserByLogin(login);
    if (user == null) {
      LOGGER.warn("user not found for login {}", login);
      throw new ResourceNotFound(USERS_API + "/" + login);
    }

    List<RoleResource> roleResources = user.getRoles().stream().map(RoleResource::new)
        .collect(Collectors.toList());

    return new ResponseEntity<>(new RolesResource(roleResources), HttpStatus.OK);
  }

  /**
   * Add an user
   * 
   * @param userDTO
   *          users informations
   * @return the created user URI
   */
  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(readOnly = false)
  public ResponseEntity<?> addUser(@RequestBody User user) {
    Map<String, String> validationErrors = new HashMap<String, String>();
    if (user.getFirstName() == null) {
      validationErrors.put("error", "missing firstName");
    } // TODO complete
    if (!validationErrors.isEmpty()) {
      new ResponseEntity<Object>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    User createdUser = userService.addUser(user);
    return new ResponseEntity<>(new UserResource(createdUser), HttpStatus.CREATED);
  }

  /**
   * Delete a user by <i>login</i>
   * 
   * @param login
   */
  @RequestMapping(value = "/{login:.+}", method = RequestMethod.DELETE)
  @Transactional(readOnly = false)
  public ResponseEntity<?> deleteUser(@PathVariable("login") String login) {
    userService.deleteUser(login);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
