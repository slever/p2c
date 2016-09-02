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
  public static final String API = "/api";

  /**
   * GET <i>/users</i>: return users list
   */
  public static final String GET_ALL_USERS = "/users";

  /**
   * GET <i>/users/{login}</i>: get user attributes
   */
  public static final String GET_USER = "/users/{login:.+}";

  /**
   * POST <i>/users</i>: Crée un User
   */
  public static final String POST_USER = "/users";

  /**
   * DELETE <i>/users</i>: Supprimer un User
   */
  public static final String DELETE_USER = "/users/{login:.+}";

  /**
   * POST <i>/products</i>: Create a product
   */
  public static final String POST_PRODUCT = "/products";

}
