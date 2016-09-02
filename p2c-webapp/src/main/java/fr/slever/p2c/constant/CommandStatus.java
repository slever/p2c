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
