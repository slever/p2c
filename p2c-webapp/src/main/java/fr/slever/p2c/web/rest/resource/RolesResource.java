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

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;


/**
 * HATEOAS resource representing a users collection
 * 
 * @author sebastienlever
 *
 */
public class RolesResource extends Resources<RoleResource> {

	public RolesResource(Iterable<RoleResource> roles) {
		super(roles, new ArrayList<Link>());
	}
	
}
