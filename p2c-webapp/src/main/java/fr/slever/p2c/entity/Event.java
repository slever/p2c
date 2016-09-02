package fr.slever.p2c.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import fr.slever.p2c.constant.CommandStatus;

/**
 * Event in the history
 * 
 * @author sebastienlever
 *
 */
@Entity
public class Event {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  private Long commandId;

  @Column(nullable = false)
  @NotNull
  private CommandStatus status;

  @Column
  private LocalDateTime dateTime;

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
   * @return the dateTime
   */
  public LocalDateTime getDateTime() {
    return dateTime;
  }

  /**
   * @param dateTime
   *          the dateTime to set
   */
  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }
}
