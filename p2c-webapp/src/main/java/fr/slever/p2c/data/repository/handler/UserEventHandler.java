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
package fr.slever.p2c.data.repository.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Component;

import fr.slever.p2c.data.entity.User;
import fr.slever.p2c.data.repository.UserRepository;

/**
 * Event handler for User
 * 
 * @author sebastienlever
 *
 */
@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserEventHandler.class);
	
	private final UserRepository userRepository;

	/**
	 * @param userRepository
	 */
	@Autowired
	public UserEventHandler(UserRepository userRepository){
		this.userRepository=userRepository;
	}
	
	/**
	 * @param user
	 */
	@HandleBeforeCreate
	public void handleUserCreate(User user) {
		String uniqueLogin = getUniqueLogin(user.getFirstName(), user.getLastName());
		user.setLogin(uniqueLogin);
		LOGGER.debug("Generate new password for new use: {}",uniqueLogin);
		user.setPassword(KeyGenerators.string().generateKey());
	}

	private String getUniqueLogin(String firstName, String lastName) {
		String loginBase = firstName.toLowerCase() + "." + lastName.toLowerCase();
		String login = loginBase;
		int suffix = 1;
		while (userRepository.countUsersWithLogin(login)>0) {
			login = loginBase + suffix++;
		}
		return login;
	}
}
