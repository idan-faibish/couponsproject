package il.ac.hit.couponsproject.model.dto;

/**
 * This class represent an user
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */
public class User {

	private String userName;
	private String password;

	/**
	 * @return returns the user name of the user
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Setting the user's user name with the given String 'userName'
	 * 
	 * @param userName
	 *            the String userName that is about to be set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return returns the password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setting the user's password with the given String 'password'
	 * 
	 * @param password the String password that is about to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return a String representation of the user
	 */
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}

}
