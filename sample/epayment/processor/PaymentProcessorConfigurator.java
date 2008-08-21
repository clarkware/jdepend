package epayment.processor;

import epayment.framework.IGatewayAdapter;

/**
 * The <code>PaymentProcessorConfigurator</code> class
 * is responsible for configuring the run-time
 * environment of the <code>PaymentProcessor</code>.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */

public class PaymentProcessorConfigurator {

	/**
	 * Constructs a <code>PaymentProcessorConfigurator</code>.
	 */
	public PaymentProcessorConfigurator() {
	}
	
	/**
	 * Configures the specified payment processor.
	 *
	 * @param processor Payment processor to configure.
	 * @throws IOException If a configuration error occurred.
	 */
	public void configure(PaymentProcessor processor) {
			
		//
		// Production applications should use a dynamic 
		// configuration mechanism with an adapter factory
		// to construct the appropriate adapter.
		//
		IGatewayAdapter adapter = null;
		
		/*
		adapter = makeAdapter("XYZGatewayAdapter");
		adapter.setHost(someHostName);
		*/
		
		processor.setGatewayAdapter(adapter);
	}	
}
