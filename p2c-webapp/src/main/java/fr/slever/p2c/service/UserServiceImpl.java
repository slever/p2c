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
package fr.slever.p2c.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.slever.p2c.entity.Role;
import fr.slever.p2c.entity.User;
import fr.slever.p2c.exception.FunctionalException;
import fr.slever.p2c.repository.RoleRepository;
import fr.slever.p2c.repository.UserRepository;
import fr.slever.p2c.web.security.auth.SecurityUser;

/**
 * Service for user
 * 
 * @author sebastienlever
 */
@Component("userService")
@Transactional
public class UserServiceImpl implements UserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public List<User> findAllUsers() {
    List<User> users = new ArrayList<>();
    for (User user : userRepository.findAll()) {
      users.add(user);
    }

    return users;
  }

  @Override
  public User addUser(User user) {
    String firstName = user.getFirstName();
    String lastName = user.getLastName();
    if (firstName == null || "".equals(firstName) || lastName == null || "".equals(lastName)) {
      throw new FunctionalException("firstName and lastName should not be empty");
    }
    user.setLogin(getUniqueLogin(firstName, lastName));
    user.setPassword(generateUniquePassword());

    List<Role> roles = new ArrayList<>();
    for (Role r : user.getRoles()) {
      Role role = roleRepository.findByName(r.getName());
      roles.add(role);
    }
    user.setRoles(roles);

    return userRepository.save(user);
  }

  private String generateUniquePassword() {
    // TODO Auto-generated method stub
    return KeyGenerators.string().generateKey();
  }

  private String getUniqueLogin(String firstName, String lastName) {
    String login = firstName.toLowerCase() + "." + lastName.toLowerCase();
    String newLogin = new String(login);
    int suffix = 1;
    while (userRepository.findByLogin(newLogin) != null) {
      newLogin = login + suffix;
      suffix++;
    }
    return newLogin;
  }

  @Override
  public void deleteUser(String login) {
    User user = userRepository.findByLogin(login);
    if (user == null) {
      LOGGER.warn("user not found for login={}", login);
    } else {
      userRepository.delete(user.getId());
    }
  }

  @Override
  public User findUserByLogin(String login) {
    User user = userRepository.findByLogin(login);
    if (user == null) {
      LOGGER.warn("user not found for login={}", login);
    }
    return user;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = findUserByLogin(userName);
    if (user == null) {
      throw new UsernameNotFoundException(userName);
    }
    return new SecurityUser(user);
  }
}
