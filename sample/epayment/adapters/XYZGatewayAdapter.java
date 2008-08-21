package epayment.adapters;

import epayment.framework.IGatewayAdapter;
import epayment.framework.IPaymentRequest;
import epayment.framework.IPaymentResponse;
import epayment.framework.PaymentException;
import epayment.response.PaymentResponse;

/**
 * The <code>XYZGatewayAdapter</code> class is an
 * <code>IGatewayAdapter</code> which adapts
 * to a vendor-specific epayment package.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */
 
public class XYZGatewayAdapter implements IGatewayAdapter {

	//
	// Vendor-specific proxy class.
	//
	//private XYZProxy _proxy;
	
	/**
	 * Constructs an <code>XYZGatewayAdapter</code> instance.
	 */
	public XYZGatewayAdapter() {
		//_proxy = new XYZProxy();
	}
	
	/**
	 * Sets the payment gateway host.
	 *
	 * @param host Gateway host.
	 */
	public void setHost(String host) {
		//_proxy.setHostName(host);		
	}
	
	/** 
	 * Performs an authorize for the specified payment request 
	 * information and returns a payment response.
	 *
	 * @param request Payment request.
	 * @return Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse authorize(IPaymentRequest request)
		throws PaymentException {
		
		PaymentResponse response = new PaymentResponse();
		
		//
		// Adapt the request information to the 
		// vendor-specific proxy API.
		//
		/*
		_proxy.setAction("1");
		_proxy.setUser(request.getUserId());
		_proxy.setPass(request.getUserPassword());		
		_proxy.setName(request.getAccountName());
		_proxy.setNumber(request.getAccountNumber());
		_proxy.setComment(request.getComment());
		_proxy.setAmount(request.getAmount());
		*/
		
		//
		// Perform the transaction against
		// the vendor-specific API.
		//
		/*	
		boolean success = false;
		try {
		
			success = _proxy.execute();
		
		} catch (Exception e) {
			throw new PaymentException(e.getMessage());
		}
		*/
		
		//
		// Adapt the vendor-specific response information 
		// to the generic response API.
		//		
		/*
		if (success) {
		
			response.setResponseMessage(_proxy.getExecutionResult());
			response.setProcessedDate(_proxy.getDateTime());
	
		} else {
			throw new PaymentException(_proxy.getResult());
		}
		*/

		return response;
	}
	
	/** 
	 * Performs a capture for the specified payment 
	 * request information and returns a payment response.
	 *
	 * @param request Payment request.
	 * @return Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse capture(IPaymentRequest request)
		throws PaymentException {
		
		// similar to authorize()
		
		return new PaymentResponse();
	}
	
	/** 
	 * Performs a sale (authorize and capture) for the specified 
	 * payment request information and returns a payment response.
	 *
	 * @param request Payment request.
	 * @return Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse sale(IPaymentRequest request)
		throws PaymentException {
		
		// similar to authorize()
		
		return new PaymentResponse();
	}
	
	/** 
	 * Performs a credit for the specified payment request 
	 * information and returns a payment response.
	 *
	 * @param request Payment request.
	 * @return Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse credit(IPaymentRequest request)
		throws PaymentException {
		
		// similar to authorize()
		
		return new PaymentResponse();
	}
	
	/** 
	 * Performs a void for the specified payment request 
	 * information and returns a payment response.
	 *
	 * @param request Payment request.
	 * @return Payment response.
	 * @throws PaymentException If an error occurs.
	 */
	public IPaymentResponse voidSale(IPaymentRequest request)
		throws PaymentException {
		
		// similar to authorize()
		
		return new PaymentResponse();
	}
}
