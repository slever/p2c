package fr.slever.p2c.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import fr.slever.p2c.service.UserService;

/**
 * Security configuration for REST API
 * 
 * @author sebastienlever
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;

  /**
   * @param auth
   * @throws Exception
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // allow h2-console
    http.headers().frameOptions().disable();

    http.httpBasic().and().authorizeRequests()
        .antMatchers("/", "/index.html", "/app/**/*.js", "/app/**/*.html").permitAll()
        .antMatchers("/swagger-ui.html").permitAll()
        .antMatchers("/swagger-resources/**").permitAll()
        .antMatchers("/v2/api-docs").permitAll()
        .anyRequest().authenticated()
        .antMatchers("/api/users/**").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.POST, "/api/products").hasAuthority("PRODUCER")
        .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and().formLogin().loginPage("/#login").permitAll()
        .and().logout().permitAll().logoutUrl("/logout")
        .logoutSuccessUrl("/");
  }
}
