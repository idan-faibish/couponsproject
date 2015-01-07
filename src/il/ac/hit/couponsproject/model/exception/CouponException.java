package il.ac.hit.couponsproject.model.exception;

/**
 * an Exception that indicates a malfunction in one of the methods of ICouonDAO implementations.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */
public class CouponException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that get two parameters: 'msg' as a String representation of the problem and<br/>
	 * 'chainedException' as an another exception that preceded this exception (in this way we won't lose the
	 * 'chainedException')
	 * 
	 * @param msg
	 *            the representation of the the problem that causes this exception
	 * @param chainedException
	 *            a previous exception that preceded this exception
	 */
	public CouponException(String msg, Throwable chainedException) {
		super(msg, chainedException);
	}

	
	/**
	 * Constructor that get 'msg' as a String representation of the problem
	 * 
	 * @param msg the representation of the problem that causes this exception
	 */
	public CouponException(String msg) {
		super(msg);
	}
}
