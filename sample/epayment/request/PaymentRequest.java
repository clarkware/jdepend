package epayment.request;

import epayment.framework.IPaymentRequest;

/**
 * The <code>PaymentRequest</code> class is an
 * <code>IPaymentRequest</code> that encapsulates
 * the basic payment request information.
 * <p>
 * This class is strictly an example.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting</a>
 */

public class PaymentRequest implements IPaymentRequest {

	private String _userId;
	private String _userPassword;
	private String _name;
	private String _accountNumber;
	private double _amount;
	private String _comment;
	
	/**
	 * Constructs a <code>PaymentRequest</code>.
	 * with default values.
	 */
	public PaymentRequest() {
		_userId = "";
		_userPassword = "";
		_name = "";
		_accountNumber = "";
		_amount = 0.0;
		_comment = "";
	} 
	
	/**
	 * Sets the user id.
	 *
	 * @param id User id.
	 */
	public void setUserId(String id) {
		_userId = id;
	}
	
	/**
	 * Returns the user id.
	 *
	 * @return User id.
	 */
	protected String getUserId() {
		return _userId;
	}
	
	/**
	 * Sets the user password.
	 *
	 * @param password User password.
	 */
	public void setUserPassword(String password) {
		_userPassword = password;
	} 
	
	/**
	 * Returns the user password.
	 *
	 * @return User password.
	 */
	protected String getUserPassword() {
		return _userPassword;
	} 
	
	/**
	 * Sets the name.
	 *
	 * @param name Name.
	 */
	public void setAccountName(String name) {
		_name = name;
	}
	
	/**
	 * Returns the name.
	 *
	 * @return Name.
	 */
	protected String getAccountName() {
		return _name;
	}
	
	/**
	 * Sets the account number.
	 *
	 * @param number Account number.
	 */
	public void setAccountNumber(String number) {
		_accountNumber = number;
	}
	
	/**
	 * Returns the account number.
	 *
	 * @return Account number.
	 */
	protected String getAccountNumber() {
		return _accountNumber;
	}
	
	/**
	 * Sets the amount of the transaction.
	 *
	 * @param amount Amount in dollars.
	 */
	public void setAmount(double amount) {
		_amount = amount;
	}
	
	/**
	 * Returns the amount of the transaction.
	 *
	 * @return Amount in dollars. 
	 */
	protected double getAmount() {
		return _amount;
	}

	/**
	 * Sets the reporting comment.
	 *
	 * @param comment Reporting comment.
	 */
	public void setComment(String comment) {
		_comment = comment;
	}
	
	/**
	 * Returns the reporting comment.
	 *
	 * @return Reporting comment.
	 */
	protected String getComment() {
		return _comment;
	}
	
	/**
	 * Returns the string representation of this object.
	 *
	 * @return String representation.
	 */
	public String toString() {
	
		StringBuffer contents = new StringBuffer();
	
		contents.append("User ID = " + _userId + "\n");
		contents.append("User Password = " + _userPassword + "\n");
		contents.append("Name = " + _name + "\n");
		contents.append("Account Number = " + _accountNumber + "\n");
		contents.append("Amount = " + _amount + "\n");
		
		return contents.toString();
	}
}




 
 
