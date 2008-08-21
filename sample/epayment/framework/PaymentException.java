package epayment.framework;

/**
 * The <code>PaymentException</code> class is an
 * <code>Exception</code> thrown when a payment
 * processing error occurs.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */
 
public class PaymentException extends Exception {

	/**
	 * Constructs a <code>PaymentException</code> with
	 * the specified message.
 	 *
	 * @param message Message.
	 */
	public PaymentException(String message) {
		super (message);
	}	
}
