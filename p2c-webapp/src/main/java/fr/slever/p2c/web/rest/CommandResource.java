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

import static fr.slever.p2c.web.rest.constant.URI.COMMAND_API;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.slever.p2c.entity.User;
import fr.slever.p2c.exception.ResourceNotFound;
import fr.slever.p2c.service.UserService;
import fr.slever.p2c.web.rest.dto.UserDTO;

/**
 * User Resource
 * 
 * @author sebastienlever
 */
@RestController
@RequestMapping(COMMAND_API)
@EnableAutoConfiguration
public class CommandResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(CommandResource.class);

  @Autowired
  private UserService userService;

  /**
   * URL get for a particular registered user.
   * 
   * @return List<UserDTO> all registered users.
   */
  @RequestMapping(value = "/{login:.+}/mycommands", method = RequestMethod.GET)
  @ResponseBody
  @Transactional(readOnly = true)
  public UserDTO getConsumerCommands(@PathVariable("login") String login) {
    User user = this.userService.findUserByLogin(login);
    if (user == null) {
      LOGGER.warn("user not found for login {}", login);
      throw new ResourceNotFound(COMMAND_API + "/" + login + "/mycommands");
    }

    return new UserDTO(user);
  }

}
