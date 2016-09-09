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
package fr.slever.p2c.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import fr.slever.p2c.constant.RoleEnum;

/**
 * Utils for Authentication
 * 
 * @author sebastienlever
 *
 */
public final class AuthUtil {
	private AuthUtil() {}
	
	/**
	 * Check if Authentication has any authority
	 * @param auth
	 * @param roles
	 * @return true if has authority
	 */
	public static boolean hasAnyAuthority(Authentication auth, RoleEnum...roles) {
		if (auth == null){
			return false;
		}

		for (GrantedAuthority grant : auth.getAuthorities()) {
			for (RoleEnum role : roles) {
				if (grant.getAuthority().equals(role.name()))
					return true;
			}
		}
		return false;
	}
}
