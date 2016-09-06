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
package fr.slever.p2c.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Resource has not been found
 * 
 * @author sebastienlever
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends FunctionalException {
  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 4849578461460660164L;

  /**
   * @param url
   */
  public ResourceNotFound(String url) {
    super("The ressource has not been found: " + url);
  }
}
