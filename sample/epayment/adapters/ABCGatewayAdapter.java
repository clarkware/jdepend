package epayment.adapters;

import epayment.adapters.*;

import epayment.framework.IGatewayAdapter;
import epayment.framework.IPaymentRequest;
import epayment.framework.IPaymentResponse;
import epayment.framework.PaymentException;
import epayment.response.PaymentResponse;

/**
 * The <code>ABCGatewayAdapter</code> class is an
 * <code>IGatewayAdapter</code> which adapts
 * to a vendor-specific epayment package.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */
 
public class ABCGatewayAdapter implements IGatewayAdapter {

	//
	// Vendor-specific proxy class.
	//
	//private ABCProxy _proxy;
	
	/**
	 * Constructs an <code>ABCGatewayAdapter</code> instance.
	 */
	public ABCGatewayAdapter() {
		//_proxy = new ABCProxy();
	}
	
	/**
	 * Sets the payment gateway host.
	 *
	 * @param host Gateway host.
	 */
	public void setHost(String host) {
		//_proxy.setGatewayHostName(host);		
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
		_proxy.setAction("authorize");
		_proxy.setUserId(request.getUserId());
		_proxy.setPassword(request.getUserPassword());		
		_proxy.setAccountHolderName(request.getAccountName());
		_proxy.setAccountHolderNumber(request.getAccountNumber());
		_proxy.setUserDefinedField1(request.getComment());
		_proxy.setTransactionAmount(request.getAmount());
		*/
		
		//
		// Perform the transaction against
		// the vendor-specific API.
		//
		/*	
		boolean success = false;
		try {
		
			success = _proxy.performTransaction();
		
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
		
			response.setResponseMessage(_proxy.getResult());
			response.setProcessedDate(_proxy.getDate());
	
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
