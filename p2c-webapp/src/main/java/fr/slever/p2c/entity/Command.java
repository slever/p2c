package fr.slever.p2c.entity;

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
