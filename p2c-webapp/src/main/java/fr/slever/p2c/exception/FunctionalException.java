package fr.slever.p2c.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Any functional error, represented by a "bad request" HTTP 400 response code.
 * 
 * @author sebastienlever
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FunctionalException extends RuntimeException {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -1213291549849455969L;

  /**
   * Default constructor
   */
  public FunctionalException() {
    super();
  }

  /**
   * @param message
   * @param cause
   */
  public FunctionalException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message
   */
  public FunctionalException(String message) {
    super(message);
  }

}
