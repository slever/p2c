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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.slever.p2c.data.entity.Command;
import fr.slever.p2c.data.repository.CommandRepository;
import fr.slever.p2c.data.repository.UserRepository;

/**
 * @author sebastienlever
 *
 */
public class CommandServiceImpl implements CommandService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandServiceImpl.class);
	private final UserRepository userRepository;
	private final CommandRepository commandRepository;

	@Autowired
	public CommandServiceImpl(UserRepository userRepository, CommandRepository commandRepository) {
		this.userRepository = userRepository;
		this.commandRepository = commandRepository;
	}

	@Override
	public List<Command> findCommandsByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

}
