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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.slever.p2c.entity.Role;
import fr.slever.p2c.entity.User;
import fr.slever.p2c.exception.ResourceNotFound;
import fr.slever.p2c.service.UserService;
import fr.slever.p2c.web.rest.dto.Link;
import fr.slever.p2c.web.rest.dto.UserDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * User Resource
 * 
 * @author sebastienlever
 */
@RestController
@RequestMapping(USERS_API)
@EnableAutoConfiguration
public class UserResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

  @Autowired
  private UserService userService;

  /**
   * Security purpose
   * 
   * @param user
   * @return the connected user principal if connected
   */
  @RequestMapping(value = "/me", method = RequestMethod.GET)
  public Principal user(Principal user) {
    // FIXME this is a security issue (password is in http response)
    return user;
  }

  /**
   * @return all registered users.
   */
  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  @Transactional(readOnly = true)
  @ApiOperation(value = "getUsers", nickname = "getUsers")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = UserDTO.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure") })
  public List<UserDTO> getUsers() {
    return this.userService.findAllUsers().stream().map(UserDTO::new).collect(Collectors.toList());
  }

  /**
   * URL get for a particular registered user.
   * 
   * @return List<UserDTO> all registered users.
   */
  @RequestMapping(value = "/{login:.+}", method = RequestMethod.GET)
  @ResponseBody
  @Transactional(readOnly = true)
  public UserDTO getUser(@PathVariable("login") String login) {
    User user = this.userService.findUserByLogin(login);
    if (user == null) {
      LOGGER.warn("user not found for login {}", login);
      throw new ResourceNotFound(USERS_API + "/" + login);
    }

    return new UserDTO(user);
  }

  /**
   * URL get for a particular registered user.
   * 
   * @return List<UserDTO> all registered users.
   */
  @RequestMapping(value = "/{login:.+}/commands", method = RequestMethod.GET)
  @ResponseBody
  @Transactional(readOnly = true)
  public UserDTO getConsumerCommands(@PathVariable("login") String login) {
    User user = this.userService.findUserByLogin(login);
    if (user == null) {
      LOGGER.warn("user not found for login {}", login);
      throw new ResourceNotFound(USERS_API + "/" + login);
    }

    return new UserDTO(user);
  }

  /**
   * Add an user
   * 
   * @param userDTO
   *          users informations
   * @return the created user URI
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @Transactional(readOnly = false)
  public Link addUser(@RequestBody UserDTO userDTO) {
    User user = new User();
    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setEmail(userDTO.getEmail());
    user.setMobile(userDTO.getMobile());

    List<Role> roles = new ArrayList<>();
    for (String roleName : userDTO.getRoles()) {
      Role role = new Role();
      role.setName(roleName);
      roles.add(role);
    }
    user.setRoles(roles);

    return new Link(USERS_API + "/" + userService.addUser(user).getLogin());
  }

  /**
   * Delete a user by <i>login</i>
   * 
   * @param login
   */
  @RequestMapping(value = "/{login:.+}", method = RequestMethod.DELETE)
  @ResponseBody
  @Transactional(readOnly = false)
  public void deleteUser(@PathVariable("login") String login) {
    userService.deleteUser(login);
  }

}
