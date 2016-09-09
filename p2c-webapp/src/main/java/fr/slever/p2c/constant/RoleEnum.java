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
package fr.slever.p2c.constant;

/**
 * Users roles
 * 
 * @author sebastienlever
 *
 */
public enum RoleEnum {
	/**
	 * Administrator: manage users
	 */
	ADMIN,
	/**
	 * Consumer: command products
	 */
	CONSUMER, 
	/**
	 * Producer: has a farm and produce products
	 */
	PRODUCER, 
	/**
	 * Transporter: manage delivery
	 */
	TRANSPORTER;
}
