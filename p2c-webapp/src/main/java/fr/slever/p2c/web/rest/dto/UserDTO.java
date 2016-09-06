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
package fr.slever.p2c.web.rest.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import fr.slever.p2c.entity.Role;
import fr.slever.p2c.entity.User;

/**
 * @author sebastienlever
 *
 */
public class UserDTO {
  @Size(max = 50)
  private String firstName;

  @Size(max = 50)
  private String lastName;

  @Email
  @Size(min = 5, max = 100)
  private String email;

  private Set<String> roles = new HashSet<>();

  private String login;

  private String mobile;

  public UserDTO() {
    super();
  }

  /**
   * @param user
   */
  public UserDTO(User user) {
    this(user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getLogin(),
        user.getMobile(),
        user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
  }

  /**
   * @param firstName
   * @param lastName
   * @param email
   * @param login
   * @param mobile
   * @param password
   * @param roles
   */
  public UserDTO(String firstName, String lastName, String email, String login, String mobile, Set<String> roles) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.roles = roles;
    this.login = login;
    this.mobile = mobile;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName
   *          the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName
   *          the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the roles
   */
  public Set<String> getRoles() {
    return roles;
  }

  /**
   * @param roles
   *          the roles to set
   */
  public void setRoles(Set<String> roles) {
    this.roles = roles;
  }

  /**
   * @return the login
   */
  public String getLogin() {
    return login;
  }

  /**
   * @param login
   *          the login to set
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * @return the mobile
   */
  public String getMobile() {
    return mobile;
  }

  /**
   * @param mobile
   *          the mobile to set
   */
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

}
