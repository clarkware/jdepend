package epayment.framework;

/**
 * The <code>IPaymentCommand</code> interface defines
 * the common interface for all electronic payment 
 * actions.
 * <p>
 * Implementors of this interface encapsulate a payment
 * request as an object which can be generically 
 * processed by the <code>PaymentProcessor</code>.
 * The <code>PaymentProcessor</code> is responsible
 * for installing an appropriate implementation of
 * an <code>IGatewayAdapter</code>.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */
 
public interface IPaymentCommand {

	/**
	 * Executes this command using the specified payment 
	 * adapter and returns a payment response.
	 *
	 * @param adapter Payment adapter.
	 * @return response Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse execute(IGatewayAdapter adapter)
		throws PaymentException;  	
}
