package il.ac.hit.couponsproject.controller.actions.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.Controller;
import il.ac.hit.couponsproject.controller.actions.logic.IAction;

/**
 * This action is responsible for disconnecting this session and actually remove his admin's privileges.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 * @see IAction
 */
public class AdminLogoutAction implements IAction {

	/**
	 * this method returns the name of this action: "admin-logout-action"
	 */
	@Override
	public String getName() {
		return IConstants.ADMIN_LOGOUT_ACTION;
	}

	/**
	 * this method is actually removing the attribute that was exist in the session (that identified the user as an
	 * admin). after this action, the user needs to login again to gain the admin privileges once again. if the
	 * operation has finished successfully or not we write an appropriate message in the response
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
		HttpSession session = request.getSession();
		if (session != null) {
			session.removeAttribute(IConstants.ATTR_LOGIN_STATUS);
			Controller.LOGGER.info("the user has logged out successfully(session: " + session.getId() + ")");
			response.getWriter().print(IConstants.SUCCESS);
		} else {
			Controller.LOGGER.error("the user failed to logout");
			response.getWriter().print(IConstants.FAILURE);
		}
	}

}
