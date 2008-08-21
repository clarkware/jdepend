package epayment.framework;

import java.io.Serializable;

/**
 * The <code>IPaymentResponse</code> interface defines
 * the interface for payment response information.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */
 
public interface IPaymentResponse extends Serializable {
	
	/**
	 * Returns the processed date.
	 *
	 * @return Processed date.
	 */
	public String getProcessedDate();
	
	/**
	 * Returns the response message.
	 *
	 * @return Response message.
	 */
	public String getResponseMessage();
}
