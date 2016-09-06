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
package fr.slever.p2c.constant;

/**
 * Status of a Command
 * 
 * @author sebastienlever
 *
 */
public enum CommandStatus {
  /**
   * Consumer created the command
   */
  NEW(0),
  /**
   * a tranporter matched the travel
   */
  PLANIFIED(10),
  /**
   * Producer acknowledged the command an start to prepare
   */
  ACKNOLEDGED(20),
  /**
   * Command is prepared waiting for transporter
   */
  PREPARED(30),
  /**
   * transporter get the command
   */
  SENT(40),
  /**
   * transporter delivered the command to customer
   */
  DELIVERED(50),
  /**
   * the command is in error and require admin action
   */
  ERROR(60);

  /**
   * the code for the status (stored in db)
   */
  public final int code;

  private CommandStatus(int code) {
    this.code = code;
  }

  /**
   * @param code
   * @return the CommandStatus matching the code, if not found return ERROR
   */
  public static CommandStatus valueOf(int code) {
    for (CommandStatus status : values()) {
      if (status.code == code) {
        return status;
      }
    }

    return CommandStatus.ERROR;
  }
}
