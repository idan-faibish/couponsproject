package il.ac.hit.couponsproject.controller;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.actions.impl.AddCouponAction;
import il.ac.hit.couponsproject.controller.actions.impl.AddFavoriteCouponAction;
import il.ac.hit.couponsproject.controller.actions.impl.AdminLoginAction;
import il.ac.hit.couponsproject.controller.actions.impl.AdminLogoutAction;
import il.ac.hit.couponsproject.controller.actions.impl.DeleteCouponAction;
import il.ac.hit.couponsproject.controller.actions.impl.DeleteFavoriteCouponAction;
import il.ac.hit.couponsproject.controller.actions.impl.ForwardToAdminLoginPageAction;
import il.ac.hit.couponsproject.controller.actions.impl.ForwardToAdminPageAction;
import il.ac.hit.couponsproject.controller.actions.impl.ForwardToGuestPageAction;
import il.ac.hit.couponsproject.controller.actions.impl.ForwardWithUpdatedFavoritesCouponsAction;
import il.ac.hit.couponsproject.controller.actions.impl.UpdateAndRetrieveFavoritesCouponsAction;
import il.ac.hit.couponsproject.controller.actions.impl.RetrieveNonExpiredCouponsAction;
import il.ac.hit.couponsproject.controller.actions.impl.RetrieveThreeClosestCouponsAction;
import il.ac.hit.couponsproject.controller.actions.impl.UpdateCouponAction;
import il.ac.hit.couponsproject.controller.actions.logic.IAction;
import il.ac.hit.couponsproject.model.dao.impl.hibernate.HibernateUserDAO;
import il.ac.hit.couponsproject.model.dao.logic.IUserDAO;
import il.ac.hit.couponsproject.model.dto.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This is the controller of CouponsProject web application.<br/>
 * it's responsible for routing through the different actions according to the specific 'action' parameter</br> value
 * that is "coming" with the HTTP request from the client.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */

/*
 * the controller is using two init params for adding an admin to the CouponsProject web application
 */
@WebServlet(urlPatterns = { "/Controller/*" }, loadOnStartup = 1, initParams = {
		@WebInitParam(name = IConstants.USER, value = "admin"),
		@WebInitParam(name = IConstants.PASSWORD, value = "qwer1234") })
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IUserDAO userDAO = HibernateUserDAO.getInstance();

	/**
	 * this logger is for the use of the controller and IAction implementers
	 */
	public static final Logger LOGGER = Logger.getLogger("CouponsProjectLogger");

	/*
	 * this Map object is responsible for holding all the IAction objects that this controller is using in this
	 * application
	 */
	private Map<String, IAction> actionsMap = null;

	/**
	 * this init method is first using the HttpServlet to initialize the servlet and then adding the one and only admin
	 * user of this web application and configure the logger. and after that we initialize the actionMap with the
	 * various IAction objects
	 * 
	 * @param config
	 *            the ServletConfig object
	 * 
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		User userToAdd = new User();
		userToAdd.setUserName(config.getInitParameter(IConstants.USER));
		userToAdd.setPassword(config.getInitParameter(IConstants.PASSWORD));

		loggerConfigure();
		userDAO.addUser(userToAdd);
		if (actionsMap == null) {
			actionsMap = new HashMap<String, IAction>();
			addActionsToMap(new AdminLoginAction());
			addActionsToMap(new AddCouponAction());
			addActionsToMap(new UpdateCouponAction());
			addActionsToMap(new DeleteCouponAction());
			addActionsToMap(new AdminLogoutAction());
			addActionsToMap(new UpdateAndRetrieveFavoritesCouponsAction());
			addActionsToMap(new AddFavoriteCouponAction());
			addActionsToMap(new DeleteFavoriteCouponAction());
			addActionsToMap(new RetrieveThreeClosestCouponsAction());
			addActionsToMap(new ForwardWithUpdatedFavoritesCouponsAction());
			addActionsToMap(new RetrieveNonExpiredCouponsAction());
			addActionsToMap(new ForwardToAdminPageAction());
			addActionsToMap(new ForwardToAdminLoginPageAction());
			addActionsToMap(new ForwardToGuestPageAction());
		}
		LOGGER.info("the servlet has been initialized successfully");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//calling the doProcess method for further processing
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		//calling the doProcess method for further processing
		doProcess(request, response);
	}

	@Override
	public void destroy() {
		super.destroy();
		userDAO.deleteUser("admin");
		LOGGER.info("the servlet has been destroyed successfully \n");
	}

	/**
	 * This method is routing the execution flow of the controller according to the 'action' parameter<br/>
	 * that is "coming" with the HTTP request.<br/>
	 * this method is invoked in both the doGet and doPost methods of this controller
	 * 
	 * @param request
	 *            the HttpServletRequest object from the client
	 * @param response
	 *            response the HttpServletResponse object that will be sent to the client
	 * @throws ServletException
	 * @throws IOException
	 * 
	 */
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String actionName = request.getParameter(IConstants.ACTION);
		if (actionName != null) {
			if (actionsMap.containsKey(actionName)) {
				IAction action = (IAction) actionsMap.get(actionName);
				action.perform(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, IConstants.ERROR_MESSAGE);
			}
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, IConstants.ERROR_MESSAGE);
		}
	}

	/* this method is a helper one that gets an IAction object and adding him and his name to the actionMap Map object */
	private void addActionsToMap(IAction action) {
		actionsMap.put(action.getName(), action);
	}

	private void loggerConfigure() {
		// configure the logger according to a predefined properties file
		PropertyConfigurator.configure(IConstants.LOG4J_PROPERTIES_FILE_PATH);
	}
}
