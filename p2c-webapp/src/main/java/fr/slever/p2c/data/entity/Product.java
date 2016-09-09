/**
 * Copyright 2016 sebastien lever
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
import javax.persistence.ManyToOne;

/**
 * Product of the farm.
 * 
 * @author sebastienlever
 */
@Entity
public class Product {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(optional = false)
  private Farm farm;

  @Column(columnDefinition = "Decimal(10,2) default '100.00'", nullable = false)
  private double price;

  @Column(nullable = false)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "product_category", joinColumns = {
      @JoinColumn(name = "product_id", referencedColumnName = "id") }, inverseJoinColumns = {
          @JoinColumn(name = "category_id", referencedColumnName = "id") })
  private List<Category> categories;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the price
   */
  public Double getPrice() {
    return price;
  }

  /**
   * @param price
   *          the price to set
   */
  public void setPrice(Double price) {
    this.price = price;
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

}
