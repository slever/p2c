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
package fr.slever.p2c.data.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import fr.slever.p2c.constant.CommandStatus;

/**
 * Consumer command with products
 * 
 * @author sebastienlever
 *
 */
@Entity
public class Command {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  @NotNull
  private BigDecimal price;

  @Column(nullable = false)
  @NotNull
  private CommandStatus status;

  @OneToOne(optional = false)
  @NotNull
  private User producer;

  @OneToOne(optional = false)
  @NotNull
  private User consumer;

  @OneToOne(optional = true)
  private User transporter;

  @OneToMany(mappedBy = "commandId")
  private List<Event> history;

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
   * @return the price
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * @param price
   *          the price to set
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * @return the producer
   */
  public User getProducer() {
    return producer;
  }

  /**
   * @param producer
   *          the producer to set
   */
  public void setProducer(User producer) {
    this.producer = producer;
  }

  /**
   * @return the consumer
   */
  public User getConsumer() {
    return consumer;
  }

  /**
   * @param consumer
   *          the consumer to set
   */
  public void setConsumer(User consumer) {
    this.consumer = consumer;
  }

  /**
   * @return the transporter
   */
  public User getTransporter() {
    return transporter;
  }

  /**
   * @param transporter
   *          the transporter to set
   */
  public void setTransporter(User transporter) {
    this.transporter = transporter;
  }

  /**
   * @return the status
   */
  public CommandStatus getStatus() {
    return status;
  }

  /**
   * @param status
   *          the status to set
   */
  public void setStatus(CommandStatus status) {
    this.status = status;
  }

  /**
   * @return the history
   */
  public List<Event> getHistory() {
    return history;
  }

  /**
   * @param history
   *          the history to set
   */
  public void setHistory(List<Event> history) {
    this.history = history;
  }
}
