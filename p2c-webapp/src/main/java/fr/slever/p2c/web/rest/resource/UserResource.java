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
package fr.slever.p2c.web.rest.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import fr.slever.p2c.constant.RoleEnum;
import fr.slever.p2c.data.entity.User;
import fr.slever.p2c.util.AuthUtil;
import fr.slever.p2c.web.rest.UserController;

/**
 * HATEOAS resource representing a user
 * 
 * @author sebastienlever
 *
 */
public class UserResource extends Resource<User> {

  @JsonUnwrapped
  RolesResource rolesResource;

  public UserResource(User user) {
    super(user, new ArrayList<>());

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    add(ControllerLinkBuilder
        .linkTo(ControllerLinkBuilder.methodOn(UserController.class).getUser(user.getLogin()))
        .withSelfRel());

    add(ControllerLinkBuilder
        .linkTo(ControllerLinkBuilder.methodOn(UserController.class).getUserRoles(user.getLogin()))
        .withRel("roles"));

    List<RoleResource> roleResources = user.getRoles().stream().map(RoleResource::new)
        .collect(Collectors.toList());
    rolesResource = new RolesResource(roleResources);

    if (AuthUtil.hasAnyAuthority(auth, RoleEnum.ADMIN)) {
      add(ControllerLinkBuilder
          .linkTo(ControllerLinkBuilder.methodOn(UserController.class).deleteUser(user.getLogin()))
          .withRel("delete")); // TODO realy needed ?
    }
  }

  /**
   * @return the rolesResource
   */
  public RolesResource getRolesResource() {
    return rolesResource;
  }

  /**
   * @param rolesResource
   *          the rolesResource to set
   */
  public void setRolesResource(RolesResource rolesResource) {
    this.rolesResource = rolesResource;
  }

}
