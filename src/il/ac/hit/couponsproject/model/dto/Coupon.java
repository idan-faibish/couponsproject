package il.ac.hit.couponsproject.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * This class represent a coupon.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */
public class Coupon implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String description;
	private String category;
	private double value;
	private int percentDiscount;
	private Timestamp expirationDate;
	private String picURL;
	private double longitude;
	private double latitude;

	/**
	 * @return returns the id of the coupon
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setting the coupon's id with the given int id
	 * 
	 * @param id
	 *            the int id that is about to be set
	 */
	public void setId(int id) {
		if (id > 0)
			this.id = id;
	}

	/**
	 * @return returns the name of the coupon
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setting the coupon's name with the given String 'name'
	 * 
	 * @param name
	 *            the String name that is about to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return returns the description of the coupon
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setting the coupon's description with the given String 'description'
	 * 
	 * @param description
	 *            the String description that is about to be set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return returns the value of the coupon
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Setting the coupon's value with the given double 'value'
	 * 
	 * @param value
	 *            the double value that is about to be set
	 */
	public void setValue(double value) {
		if (value > 0)
			this.value = value;
	}

	/**
	 * @return returns the percent discount of the coupon
	 */
	public int getPercentDiscount() {
		return percentDiscount;
	}

	/**
	 * Setting the coupon's percent discount with the given int 'percentDiscount'
	 * 
	 * @param percentDiscount
	 *            the int percentDiscount that is about to be set
	 */
	public void setPercentDiscount(int percentDiscount) {
		if (percentDiscount >= 0 && percentDiscount <= 100)
			this.percentDiscount = percentDiscount;
	}

	/**
	 * @return returns the category of the coupon
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Setting the coupon's category with the given String 'category'
	 * 
	 * @param category
	 *            the String category that is about to be set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return returns the expiration timestamp of the coupon (date & time)
	 */
	public Timestamp getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * Setting the coupon's expiration-date with the given Timestamp 'expirationDate'
	 * 
	 * @param expirationDate
	 *            the Timestamp that is about to be set
	 */
	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return returns the longitude of the coupon
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Seting the coupon's longitude with the given double 'longitude'
	 * 
	 * @param longitude
	 *            the double longitude that is about to be set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return returns the latitude of the coupon
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Setting the coupon's latitude with the given double 'latitude'
	 * 
	 * @param latitude
	 *            the double latitude that is about to be set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return returns the picture-url of the coupon
	 */
	public String getPicURL() {
		return picURL;
	}

	/**
	 * Setting the coupon's picture-url with the given String 'picURL'
	 * 
	 * @param picURL
	 *            the String picURL that is about to be set
	 */
	public void setPicURL(String picURL) {
		this.picURL = picURL;
	}

	/**
	 * @return a String representation of the coupon
	 */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
				+ ", value=" + value + ", percentDiscount=" + percentDiscount + ", expirationDate=" + expirationDate
				+ ", picURL=" + picURL + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
}
