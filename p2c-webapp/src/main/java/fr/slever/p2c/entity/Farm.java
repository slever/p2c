package fr.slever.p2c.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Farm (where are the products)
 * 
 * @author sebastienlever
 *
 */
@Entity
public class Farm {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column
  private String description;

  @OneToMany
  private List<Product> products;

  @OneToOne
  private Address adress;
}
