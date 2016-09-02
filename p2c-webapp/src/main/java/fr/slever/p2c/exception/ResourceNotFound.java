package fr.slever.p2c.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Resource has not been found
 * 
 * @author sebastienlever
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends FunctionalException {
  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 4849578461460660164L;

  /**
   * @param url
   */
  public ResourceNotFound(String url) {
    super("The ressource has not been found: " + url);
  }
}
