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

/**
 * URI Constants
 * 
 * @author sebastienlever
 *
 */
public class URI {

  /**
   * Private constructor for constant class
   */
  private URI() {
  }

  /**
   * Root API URI
   */
  private static final String API = "/api";

  /**
   * Users API
   */
  public static final String USERS_API = API + "/users";

  /**
   * Products API
   */
  public static final String PRODUCT_API = API + "/products";

}
