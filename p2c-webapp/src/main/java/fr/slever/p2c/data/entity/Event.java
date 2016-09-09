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
