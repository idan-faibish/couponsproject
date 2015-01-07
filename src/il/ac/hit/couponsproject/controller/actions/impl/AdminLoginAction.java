package il.ac.hit.couponsproject.controller.actions.impl;

import java.io.IOException;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.Controller;
import il.ac.hit.couponsproject.controller.actions.logic.IAction;
import il.ac.hit.couponsproject.model.dao.impl.hibernate.HibernateUserDAO;
import il.ac.hit.couponsproject.model.dao.logic.IUserDAO;
import il.ac.hit.couponsproject.model.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This action is responsible for authenticating a user with the users in the DB, and accordingly to mark his session
 * for allowing him the admin's privileges.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 * @see IAction
 */
public class AdminLoginAction implements IAction {
	IUserDAO userDAO = HibernateUserDAO.getInstance();

	/**
	 * this method returns the name of this action: "admin-login-action"
	 */
	@Override
	public String getName() {
		return IConstants.ADMIN_LOGIN_ACTION;
	}

	/**
	 * this method is actually building a User object(according to parameters that came in the HTTP request) and checks
	 * if this user exists in the DB. if the user is indeed exists, then we allow this session to access an 'admin page'
	 * with the help of setting a specific attribute to his session (in the 'admin page' we will check for the existence
	 * of this attribute). also, if the user is exists or not we write an appropriate message in the response
	 * 
	 * @param request
	 *            the HttpServletRequest object from the client
	 * @param response
	 *            response the HttpServletResponse object that will be sent to the client
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		String userName = request.getParameter(IConstants.USER_NAME);
		String password = request.getParameter(IConstants.USER_PASSWORD);
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);

		boolean isExist = userDAO.isUserExist(user);

		if (isExist == true) {
			HttpSession session = request.getSession();
			session.setAttribute(IConstants.ATTR_LOGIN_STATUS, IConstants.OK);
			Controller.LOGGER.info("the login of the user: " + user.getUserName() + " finished successfully");
			response.getWriter().print(IConstants.TRUE);
		} else {
			Controller.LOGGER.error("the login of the user: " + user.getUserName() + " failed");
			response.getWriter().print(IConstants.FALSE);
		}
	}
}
