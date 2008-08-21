package epayment.framework;

import java.io.Serializable;

/**
 * The <code>IPaymentRequest</code> interface defines
 * the interface for payment request information.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */

public interface IPaymentRequest extends Serializable {

	/**
	 * Sets the user id.
	 *
	 * @param id User id.
	 */
	public void setUserId(String id);
	
	/**
	 * Sets the user password.
	 *
	 * @param password User password.
	 */
	public void setUserPassword(String password);
	
	/**
	 * Sets the cardholder name.
	 *
	 * @param name Cardholder name.
	 */
	public void setAccountName(String name);
	
	/**
	 * Sets the cardholder's account number.
	 * <p>
	 * This number will be stripped of all spaces,
	 * non-numeric characters, and dashes.
	 *
	 * @param number Card account number.
	 */
	public void setAccountNumber(String number);
	
	/**
	 * Sets the amount of the transaction.
	 * <p>
	 * The format is xxx.yy.
	 *
	 * @param amount Amount in dollars.
	 */
	public void setAmount(double amount);
	
	/**
	 * Sets the reporting comment.
	 *
	 * @param comment Reporting comment.
	 */
	public void setComment(String comment);
}
