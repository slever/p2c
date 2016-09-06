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
package fr.slever.p2c.web.security.auth;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role For security
 * 
 * @author sebastienlever
 *
 */
public class SecurityRole implements GrantedAuthority {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -2680887946434949994L;

  private String authority;

  /**
   * @param roleName
   */
  public SecurityRole(String roleName) {
    this.authority = roleName;
  }

  @Override
  public String getAuthority() {
    return this.authority;
  }

}
