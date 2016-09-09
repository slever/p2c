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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.slever.p2c.data.entity.Role;
import fr.slever.p2c.data.repository.RoleRepository;
import fr.slever.p2c.web.rest.constant.URI;
import fr.slever.p2c.web.rest.resource.RoleResource;
import fr.slever.p2c.web.rest.resource.RolesResource;

/**
 * User Resource
 * 
 * @author sebastienlever
 */
@RestController
@RequestMapping(URI.ROLES_API)
// EnableHypermediaSupport(type = { HypermediaType.HAL })
public class RoleController {

  private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

  @Autowired
  private RoleRepository roleRepository;

  /**
   * @return all registered users.
   */
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(readOnly = true)
  public ResponseEntity<RolesResource> getRoles() {
    List<RoleResource> roleResources = new ArrayList<>();

    for (Role role : this.roleRepository.findAll()) {
      roleResources.add(new RoleResource(role));
    }

    RolesResource roles = new RolesResource(roleResources);
    return new ResponseEntity<>(roles, HttpStatus.OK);
  }

  /**
   * Get Role by name.
   * 
   * @return the role.
   */
  @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @Transactional(readOnly = true)
  public ResponseEntity<RoleResource> getRole(@PathVariable("name") String name) {
    Role role = roleRepository.findByName(name);
    if (role == null) {
      LOGGER.warn("Not found {}", name);
      throw new ResourceNotFoundException();
    }

    return new ResponseEntity<>(new RoleResource(role), HttpStatus.OK);
  }

}
