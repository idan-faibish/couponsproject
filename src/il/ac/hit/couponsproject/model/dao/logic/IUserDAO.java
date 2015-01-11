package il.ac.hit.couponsproject.model.dao.logic;

import il.ac.hit.couponsproject.model.dto.User;

/**
 * This DAO interface is declaring some basic operations about users systems which interacting with any kind of DB. <br/>
 * Classes which about to implement this interface will need to provide all the services that this interface is
 * offering.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */
public interface IUserDAO {

	/**
	 * This method is getting a User object and adding him to the underlying DB
	 * 
	 * @param userToAdd
	 *            User object that you want to add to the underlying DB
	 * @return returns <em>true</em> if the operation was finished successfully, otherwise returns <em>false<em>
	 */
	public boolean addUser(User userToAdd);

	/**
	 * This method is getting a userName string and deletes his corresponding user from the underlying DB
	 * 
	 * @param userName
	 *            a String represents the name of the User you want to delete from the underlying DB
	 * @return returns <em>true</em> if the operation was finished successfully, otherwise returns <em>false</em>
	 */
	public boolean deleteUser(String userName);

	/**
	 * This method is getting an User object and checks if he exists in the underlying DB
	 * 
	 * @param userToCheck
	 *            User object to check is existance in the underlying DB
	 * @return returns <em>true</em> if the user is indeed exist in the underlying DB, otherwise returns <em>false</em>
	 */
	public boolean isUserExist(User userToCheck);
}
