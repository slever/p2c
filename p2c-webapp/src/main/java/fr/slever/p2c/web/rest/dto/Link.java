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

/**
 * Value Object for REST Link, see HATEOAS (Hypermedia As The Engine Of
 * Application State).
 * 
 * @author sebastienlever
 *
 */
public class Link {

  public static final String REL_SELF = "self";
  public static final String REL_FIRST = "first";
  public static final String REL_PREVIOUS = "prev";
  public static final String REL_NEXT = "next";
  public static final String REL_LAST = "last";

  private String uri;
  private String rel;

  /**
   * Creates a new link to the given URI with the self rel.
   * 
   * @see #REL_SELF
   * @param href
   *          must not be {@literal null} or empty.
   */
  public Link(String href) {
    this(href, REL_SELF);
  }

  /**
   * Creates a new {@link Link} to the given URI with the given rel.
   * 
   * @param href
   *          must not be {@literal null} or empty.
   * @param rel
   *          must not be {@literal null} or empty.
   */
  public Link(String href, String rel) {
    this.uri = href;
    this.rel = rel;
  }

  /**
   * @return the uri
   */
  public String getUri() {
    return uri;
  }

  /**
   * @param uri
   *          the uri to set
   */
  public void setUri(String uri) {
    this.uri = uri;
  }

  /**
   * @return the rel
   */
  public String getRel() {
    return rel;
  }

  /**
   * @param rel
   *          the rel to set
   */
  public void setRel(String rel) {
    this.rel = rel;
  }

}
