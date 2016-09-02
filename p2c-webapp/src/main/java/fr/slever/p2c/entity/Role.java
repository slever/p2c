package fr.slever.p2c.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The class <code>Role</code> represents the role for a user
 * 
 * @author sebastienlever
 */
@Entity
public class Role {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

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

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Role [id=" + id + ", name=" + name + "]";
  }
}
