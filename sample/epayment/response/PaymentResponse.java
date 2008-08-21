package epayment.response;

import epayment.framework.IPaymentResponse;

/**
 * The <code>PaymentResponse</code> class is an
 * <code>IPaymentResponse</code> that encapsulates
 * the basic payment response information.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */
 
public class PaymentResponse implements IPaymentResponse {

	private String _processedDate;
	private String _responseMessage;
	
	/**
	 * Constructs a <code>PaymentResponse</code>
	 * with default values.
	 */
	public PaymentResponse() {
		_processedDate = "";
		_responseMessage = "";
	}

	/**
	 * Returns the processed date.
	 *
	 * @return Processed date.
	 */
	public String getProcessedDate() {
		return _processedDate;
	}
	
	/**
	 * Sets the processed date.
	 *
	 * @param date Processed date.
	 */
	protected void setProcessedDate(String date) {
		_processedDate = date;
	}
	
	/**
	 * Returns the response message.
	 *
	 * @return Response message.
	 */
	public String getResponseMessage() {
		return _responseMessage;
	}
	
	/**
	 * Sets the response message.
	 *
	 * @param message Response message.
	 */
	protected void setResponseMessage(String message) {
		_responseMessage = message;
	}
	
	/**
	 * Returns the string representation of this object.
	 *
	 * @return String representation.
	 */
	public String toString() {
	
		StringBuffer contents = new StringBuffer();
		contents.append("Response Message = " + _responseMessage + "\n");
		contents.append("Processed Date = " + _processedDate + "\n");
		
		return contents.toString();
	}
}
