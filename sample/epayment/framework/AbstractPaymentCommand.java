package epayment.framework;

/**
 * The <code>AbstractPaymentCommand</code> class provides
 * the default behavior for the <code>IPaymentCommand</code>
 * interface. 
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */
 
public abstract class AbstractPaymentCommand implements IPaymentCommand {

	private IPaymentRequest _request;
	
	/**
	 * Constructs an <code>AbstractPaymentCommand</code>
	 * instance.
	 */
	public AbstractPaymentCommand(IPaymentRequest request) {
		_request = request;
	}
	
	/**
	 * Executes this command using the specified payment 
	 * adapter and returns a payment response.
	 *
	 * @param adapter Payment adapter.
	 * @return response Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public abstract IPaymentResponse execute(IGatewayAdapter adapter)
		throws PaymentException;
		
	/**
	 * Returns the payment request.
	 *
	 * @return Payment request.
	 */
	protected IPaymentRequest getPaymentRequest() {
		return _request;
	}	
}
