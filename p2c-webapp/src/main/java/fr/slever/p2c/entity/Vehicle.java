package fr.slever.p2c.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User's Vehicle (for transporters)
 * 
 * @author sebastienlever
 */
@Entity
public class Vehicle {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String licenceNumber;

}
