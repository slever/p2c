package fr.slever.p2c.web.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.slever.p2c.entity.Role;
import fr.slever.p2c.entity.User;
import fr.slever.p2c.web.rest.dto.UserDTO;

/**
 * User Details for security
 * 
 * @author sebastienlever
 *
 */
public class SecurityUser extends UserDTO implements UserDetails {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -3829846003708193691L;

  private String password;

  /**
   * @param user
   */
  public SecurityUser(User user) {
    super(user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getLogin(),
        user.getMobile(),
        user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
    this.password = user.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SecurityRole> roles = new ArrayList<>();
    for (String role : getRoles()) {
      roles.add(new SecurityRole(role));
    }
    return roles;
  }

  @Override
  public String getUsername() {
    return getLogin();
  }

  @Override
  public boolean isAccountNonExpired() {
    // user is never expired
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // user is never locked
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // user is never expired
    return true;
  }

  @Override
  public boolean isEnabled() {
    // user is always enabled
    return true;
  }

  @Override
  public String getPassword() {
    return this.password;
  }
}
