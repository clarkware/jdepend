package epayment.processor;

import epayment.framework.IGatewayAdapter;
import epayment.framework.IPaymentCommand;
import epayment.framework.IPaymentResponse;
import epayment.framework.PaymentException;

/**
 * The <code>PaymentProcessor</code> class is a bridge
 * for an <code>IGatewayAdapter</code>.  This class is
 * responsible for processing <code>IPaymentCommand</code>
 * instances, while decoupling them from specific
 * payment processing implementations.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */
 
public class PaymentProcessor {

	private IGatewayAdapter _adapter;
	private static PaymentProcessor _processor;
	
	/**
	 * Constructs a <code>PaymentProcessor</code>
	 * instance using the default configurator.
	 */
	public PaymentProcessor() {
		
		try {
		
			PaymentProcessorConfigurator configurator = 
				new PaymentProcessorConfigurator();
			configurator.configure(this);			
		
		} catch(Exception e) {
			System.err.println("Payment processor configuration error: " +
				e.getMessage());
			// default to consistent state
		}
	}

	/**
	 * Constructs a <code>PaymentProcessor</code>
	 * instance with the specified gateway adapter.
	 *
	 * @param adapter Gateway adapter.
	 */
	public PaymentProcessor(IGatewayAdapter adapter) {
		setGatewayAdapter(adapter);
	}
	
	/**
	 * Returns the sole instance of this class.
	 *
	 * @return Payment processor.
	 */
	public static PaymentProcessor getProcessor() {
		if (_processor == null) {
			_processor = new PaymentProcessor();
		}
		return _processor;
	}
		
	/**
	 * Sets the gateway adapter.
	 *
	 * @param adapter Gateway adapter.
	 */
	protected void setGatewayAdapter(IGatewayAdapter adapter) {
		_adapter = adapter;
	}
	
	/**
	 * Processes the specified payment command using
	 * the specified payment request and returns a 
	 * payment response.
	 *
	 * @param command Payment command.
	 * @return response Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse process(IPaymentCommand command) 
		throws PaymentException {
		
		return command.execute(_adapter);
	}
}
