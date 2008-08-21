package epayment.framework;

/**
 * The <code>IGatewayAdapter</code> interface defines
 * the common interface for all electronic payment 
 * gateways.  
 * <p>
 * Implementors of this interface serve as interface
 * adapters to vendor-specific payment gateways.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */
 
public interface IGatewayAdapter {
	
	/**
	 * Sets the payment gateway host.
	 *
	 * @param host Gateway host.
	 */
	public void setHost(String host);
	
	/** 
	 * Performs an authorizes for the specified payment request 
	 * information and returns a payment response.
	 *
	 * @param request Payment request.
	 * @return Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse authorize(IPaymentRequest request)
		throws PaymentException;
	
	/** 
	 * Performs a capture for the specified payment request 
	 * information and returns a payment response.
	 *
	 * @param request Payment request.
	 * @return Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse capture(IPaymentRequest request)
		throws PaymentException;
		
	/** 
	 * Performs a sale (authorize and capture) for the specified 
	 * payment request information and returns a payment response.
	 *
	 * @param request Payment request.
	 * @return Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse sale(IPaymentRequest request)
		throws PaymentException;
	
	/** 
	 * Performs a credit for the specified payment request 
	 * information and returns a payment response.
	 *
	 * @param request Payment request.
	 * @return Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse credit(IPaymentRequest request)
		throws PaymentException;	
		
	/** 
	 * Performs a void for the specified payment request 
	 * information and returns a payment response.
	 *
	 * @param request Payment request.
	 * @return Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse voidSale(IPaymentRequest request)
		throws PaymentException;	
}
