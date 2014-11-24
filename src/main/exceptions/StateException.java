package exceptions;

/**
 * Throw this when the Model state does not match the method call.
 * @author David
 *
 */
public class StateException extends Exception {
	  public StateException() {
		  super(); 
	  }
	  public StateException(String message) {
		  super(message); 
	  }
	  public StateException(String message, Throwable cause) {
		  super(message, cause); 
	  }
	  public StateException(Throwable cause) { 
		  super(cause); 
	  }
	}
