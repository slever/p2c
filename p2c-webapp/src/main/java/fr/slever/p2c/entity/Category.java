package fr.slever.p2c.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Product Category
 * 
 * @author sebastienlever
 */
@Entity
public class Category {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "product_category", joinColumns = {
      @JoinColumn(name = "category_id", referencedColumnName = "id") }, inverseJoinColumns = {
          @JoinColumn(name = "product_id", referencedColumnName = "id") })
  private List<Product> products;

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
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the products
   */
  public List<Product> getProducts() {
    return products;
  }

  /**
   * @param products
   *          the products to set
   */
  public void setProducts(List<Product> products) {
    this.products = products;
  }

}
