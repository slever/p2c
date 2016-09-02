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
