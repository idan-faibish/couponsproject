package il.ac.hit.couponsproject.controller.actions.logic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface represents an action of CouponsProject web application.<br/>
 * each class that will implement this interface is actually represents a specific HTTP request from the client.
 * 
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */
public interface IAction {

	/**
	 * This method returns the name of this action
	 * 
	 * @return a String representation of the action
	 */
	public String getName();

	/**
	 * This method is checking the HttpServletRequest 'request' for a parameter 'action' and do business operations
	 * according to his value
	 * 
	 * @param request
	 *            the HttpServletRequest object from the client
	 * @param response
	 *            response the HttpServletResponse object that will be sent to the client
	 * @throws ServletException
	 * @throws IOException
	 */
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
