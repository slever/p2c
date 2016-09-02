package fr.slever.p2c.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Postal Address
 * 
 * @author sebastienlever
 *
 */
@Entity
public class Address {

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long longitude;

  @Column
  private Long latitude;

  @Column
  private String street;

  @Column
  private String city;

  @Column
  private String state;

  @Column(name = "ZIP_CODE")
  private String zip;

  /**
   * @return the longitude
   */
  public Long getLongitude() {
    return longitude;
  }

  /**
   * @param longitude
   *          the longitude to set
   */
  public void setLongitude(Long longitude) {
    this.longitude = longitude;
  }

  /**
   * @return the latitude
   */
  public Long getLatitude() {
    return latitude;
  }

  /**
   * @param latitude
   *          the latitude to set
   */
  public void setLatitude(Long latitude) {
    this.latitude = latitude;
  }

  /**
   * @return the street
   */
  public String getStreet() {
    return street;
  }

  /**
   * @param street
   *          the street to set
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city
   *          the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @param state
   *          the state to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return the zip
   */
  public String getZip() {
    return zip;
  }

  /**
   * @param zip
   *          the zip to set
   */
  public void setZip(String zip) {
    this.zip = zip;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Adress [longitude=" + longitude + ", latitude=" + latitude + ", street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip
        + "]";
  }
}
