package fr.slever.p2c.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Any technical error, represented by a <b>HTTP 500</b> response code.
 * 
 * @author sebastienlever
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class TechnicalException extends RuntimeException {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -3078310123346739290L;

  /**
   * @param message
   * @param cause
   */
  public TechnicalException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public TechnicalException(String message) {
    super(message);
  }

}
