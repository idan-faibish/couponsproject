package il.ac.hit.couponsproject.configuration.constants;
//change
import java.text.SimpleDateFormat;

/**
 * This interface is wrapping all the constants of the application.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */
public interface IConstants {
	
	
	//resources' constants
	
	/** represents the bundle base */
	public static final String	BUNDLE_BASE				 		  	 = "il.ac.hit.couponsproject.res";
	/**represents the DB bundle of hibernate in this application */
	public static final String	DB_BUNDLE_HIBERNATE		  		  	 = BUNDLE_BASE + ".hibernate.db";
	/**represents the resource key of 'get three closest coupons' */
	public static final String  RES_KEY_GET_THREE_CLOSEST_COUPONS 	 = "get.three.closest.coupons";
	/**represents the resource key of 'retrieve coupons' */
	public static final String  RES_KEY_RETRIEVE_COUPONS 			 = "retrieve.coupons";
	/**represents the resource key of 'retrieve non-expired coupons' */
	public static final String  RES_KEY_RETRIEVE_NON_EXPIRED_COUPONS = "retrieve.non.expired.coupons";

	
	
	//actions constants
	
	/**the string 'action' */
	public static final String  ACTION 								  		  = "action";
	/**represents the action:'admin login action'  */
	public static final String  ADMIN_LOGIN_ACTION		 			    	  = "admin-login-action";
	/**represents the action:'retrieve coupons action' */
	public static final String  RETRIEVE_COUPONS_ACTION 				 	  = "retrieve-coupons-action";
	/**represents the action:'add coupon action'  */
	public static final String  ADD_COUPON_ACTION 						 	  = "add-coupon-action";
	/**represents the action:'delete coupon action' */
	public static final String  DELETE_COUPON_ACTION 					 	  = "delete-coupon-action";
	/**represents the action:'update coupon action' */
	public static final String  UPDATE_COUPON_ACTION 						  = "update-coupon-action";
	/**represents the action:'pick coupon by id action' */
	public static final String 	PICK_COUPON_BY_ID_ACTION				      = "pick-coupon-by-id-action";
	/**represents the action:'admin logout action' */
	public static final String  ADMIN_LOGOUT_ACTION						 	  = "admin-logout-action";
	/**represents the action:'update and retrieve favorites coupons action' */
	public static final String 	UPDATE_AND_RETRIEVE_FAVORITES_COUPONS_ACTION  = "update-and-retrieve-favorites-coupons-action";
	/**represents the action:'add favorite coupon action' */
	public static final String  ADD_FAVORITE_COUPON_ACTION					  = "add-favorite-coupon-action";
	/**represents the action:'delete favorite coupon action' */
	public static final String 	DELETE_FAVORITE_COUPON_ACTION 				  = "delete-favorite-coupon-action";
	/**represents the action:'retrieve three closest coupons action' */
	public static final String  RETRIEVE_THREE_CLOSEST_COUPONS_ACTION	 	  = "retrieve-three-closest-coupons-action";
	/**represents the action:'forward with updated favorites coupons action' */
	public static final String  FORWARD_WITH_UPDATED_FAVORITES_COUPONS_ACTION = "forward-with-updated-favorites-coupons-action";
	/**represents the action:'retrieve non expired coupons action' */
	public static final String  RETRIEVE_NON_EXPIRED_COUPONS_ACTION			  = "retrieve-non-expired-coupons-action";
	/**represents the action:'forward to admin page action' */
	public static final String  FORWARD_TO_ADMIN_PAGE_ACTION				  = "forward-to-admin-page-action";
	/**represents the action:'forward to admin login page action' */
	public static final String  FORWARD_TO_ADMIN_LOGIN_PAGE_ACTION			  = "forward-to-admin-login-page-action";
	/**represents the action:'forward to guest page action' */
	public static final String  FORWARD_TO_GUEST_PAGE_ACTION				  = "forward-to-guest-page-action";
	
	
	
	//path constants 
	
	/**represents the path of the admin page */
	public static final String  ADMIN_PAGE_PATH 		 	= "/WEB-INF/jsp/adminPage.jsp";
	/**represents the path of the admin login page */
	public static final String 	ADMIN_LOGIN_PAGE_PATH		= "/WEB-INF/jsp/adminLoginPage.jsp";
	/**represents the path of the closest coupons page */
	public static final String  CLOSEST_COUPONS_PAGE_PATH 	= "/WEB-INF/jsp/closestCoupons.jsp";
	/**represents the path of the guest page */
	public static final String  GUEST_PAGE_PATH				= "/WEB-INF/jsp/guestPage.jsp";
	/**represents the path of the favorites coupons page */
	public static final String  FAVORITES_COUPONS_PAGE_PATH = "/WEB-INF/jsp/favoritesCoupons.jsp";
	/**represents the path of log4j.properties file */
	public static final String  LOG4J_PROPERTIES_FILE_PATH	= "C:/Users/idan/workspace/CouponsProject/src/log4j.properties";
	
	
	
	//constants for init parameters of servlet
	
	/**represents the String 'user' */
	public static final String  USER 					= "user";
	/**represents the String 'password' */
	public static final String  PASSWORD 				= "password";

	
	
	//coupons properties constants
	
	/**represents the String 'id' */
	public static final String  ID 						= "id";
	/**represents the String 'name' */
	public static final String  NAME 					= "name";
	/**represents the String 'description' */
	public static final String  DESCRIPTION				= "description";
	/**represents the String 'category' */
	public static final String  CATEGORY 				= "category";
	/**represents the String 'value's */
	public static final String  VALUE 					= "value";
	/**represents the String 'percentDiscount' */
	public static final String  PERCENT_DISCOUNT 		= "percentDiscount";
	/**represents the String 'expirationDate' */
	public static final String  EXPIRATION_DATE			= "expirationDate";
	/**represents the String 'picURL' */
	public static final String  PICTURE_URL 			= "picURL";
	/**represents the String 'longitude' */
	public static final String  LONGITUDE 				= "longitude";
	/**represents the String 'latitude' */
	public static final String  LATITUDE 				= "latitude";
	
	
	
	//general constants
	
	/**represents the String 'true' */
	public static final String  TRUE 					= "true";
	/**represents the String 'false' */
	public static final String  FALSE					= "false";
	/**represents the String 'success' */
	public static final String  SUCCESS					= "success";
	/**represents the String 'failure' */
	public static final String  FAILURE					= "failure";
	/**represents the String 'OK' */
	public static final String  OK						= "OK";
	/**represents the String 'userName' */
	public static final String  USER_NAME 				= "userName";
	/**represents the String 'password' */
	public static final String  USER_PASSWORD 			= "password";
	/**represents the name of the algorithm md5 as 'MD5's */
	public static final String  MD5						= "MD5";
	/**represents the String 'idToSearch' */
	public static final String  ID_TO_SEARCH 			= "idToSearch";
	/**represents the String '{}' */
	public static final String  EMPTY_JSON_OBJECT       = "{}";
	/**represents the String '[]' */
	public static final String  EMPTY_JSON_ARRAY		= "[]";
	/**represents the String 'userLatitude' */
	public static final String  USER_LATITUDE			= "userLatitude";
	/**represents the String 'userLongitude' */
	public static final String  USER_LONGITUDE			= "userLongitude";
	/**represents the String 'currentDate' */
	public static final String  CURRENT_DATE			= "currentDate";
	/**represents the error message: 'This page doesn't exist!' */
	public static final String  ERROR_MESSAGE			= "This page doesn't exist!";
	
	
	
	//attributes constants
	
	/**represents the attribute named: 'favoritesCouponsList' */
	public static final String  ATTR_FAVORITES_COUPONS_LIST = "favoritesCouponsList";
	/**represents the attribute named: 'threeclosestCoupons' */
	public static final String  ATTR_THREE_CLOSEST_COUPONS	= "threeClosestCoupons";
	/**represents the attribute named: 'loginStatus' */
	public static final String  ATTR_LOGIN_STATUS			= "loginStatus";
	
	
	
	//time patterns constants
	
	/**represents the date pattern: 'yyyy-MM-dd' */
	public static final String  YYYY_MM_DD_DATE_PATTERN	= "yyyy-MM-dd";
	/**represents the time pattern: 'HH:mm'*/
	public static final String 	HH_MM_TIME_PATTERN		= "HH:mm";
	/**represents the date and time pattern: 'yyyy-MM-dd HH:mm' as a SimpleDateFormat object */
	public static final SimpleDateFormat SIMPLE_FORMAT 	= new SimpleDateFormat(YYYY_MM_DD_DATE_PATTERN+" "+HH_MM_TIME_PATTERN);
}
