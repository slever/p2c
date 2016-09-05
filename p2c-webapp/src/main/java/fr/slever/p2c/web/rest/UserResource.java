package fr.slever.p2c.web.rest;

import static fr.slever.p2c.web.rest.API_URI.USERS_API;

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
  List<UserDTO> getUsers() {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("getUsers");
    }
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
  UserDTO getUser(@PathVariable("login") String login) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("getUser({}) from login", login);
    }
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
  String addUser(@RequestBody UserDTO userDTO) {
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

    return USERS_API + "/" + userService.addUser(user).getLogin();
  }

  /**
   * Delete a user by <i>login</i>
   * 
   * @param
   */
  @RequestMapping(value = "/{login:.+}", method = RequestMethod.DELETE)
  @ResponseBody
  @Transactional(readOnly = false)
  void deleteUser(@PathVariable("login") String login) {
    userService.deleteUser(login);
  }

}
