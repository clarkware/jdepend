package epayment.commands;

import epayment.framework.*;

/**
 * The <code>VoidSaleCommand</code> class is an
 * <code>AbstractPaymentCommand</code> for voiding
 * a previous sale.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */

public class VoidSaleCommand extends AbstractPaymentCommand {

	/**
	 * Constructs an <code>VoidSaleCommand</code> with
	 * the specified payment request.
	 *
	 * @param request Payment request.
	 */
	public VoidSaleCommand(IPaymentRequest request) {
		super(request);
	}
	
	/**
	 * Executes this command using the specified payment 
	 * adapter and returns a payment response.
	 *
	 * @param adapter Payment adapter.
	 * @return response Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse execute(IGatewayAdapter adapter)
		throws PaymentException {
		
		return adapter.voidSale(getPaymentRequest());
	}
}
