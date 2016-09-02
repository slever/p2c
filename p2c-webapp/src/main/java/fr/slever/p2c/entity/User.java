package fr.slever.p2c.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Email;

import fr.slever.p2c.entity.converter.CryptoConverter;

/**
 * A User
 * 
 * @author sebastienlever
 *
 */
@Entity
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false, unique = true)
  @NotNull
  @NaturalId
  private String login;

  @Column(nullable = false)
  @NotNull
  @Convert(converter = CryptoConverter.class)
  private String password;

  @Column(nullable = false)
  @NotNull
  @NaturalId
  private String firstName;

  @Column(nullable = false)
  @NotNull
  @NaturalId
  private String lastName;

  @Column(nullable = false, unique = true)
  @NotNull
  @Email
  private String email;

  @Column(nullable = false)
  @NotNull
  @Pattern(regexp = "(^$|[0-9]{10})")
  private String mobile;

  @ManyToOne(fetch = FetchType.LAZY)
  private Address adress;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
             joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
             inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
  private List<Role> roles = new ArrayList<>();

  @OneToOne(fetch = FetchType.LAZY)
  private Vehicle vehicle;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the login
   */
  public String getLogin() {
    return login;
  }

  /**
   * @param login
   *          the login to set
   */
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password
   *          the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName
   *          the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName
   *          the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the roles
   */
  public List<Role> getRoles() {
    return roles;
  }

  /**
   * @param roles
   *          the roles to set
   */
  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  /**
   * @return the mobile
   */
  public String getMobile() {
    return mobile;
  }

  /**
   * @param mobile
   *          the mobile to set
   */
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  /**
   * @return the adress
   */
  public Address getAdress() {
    return adress;
  }

  /**
   * @param adress
   *          the adress to set
   */
  public void setAdress(Address adress) {
    this.adress = adress;
  }

  /**
   * @return the vehicle
   */
  public Vehicle getVehicle() {
    return vehicle;
  }

  /**
   * @param vehicle
   *          the vehicle to set
   */
  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

}
