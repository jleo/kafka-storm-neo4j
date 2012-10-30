/**
 * 
 */
package com.brandtology.entity;

/**
 * @author leah
 *
 */
public class Place {

	String name;
	String fullName;
	
	double latitude;
	double longitude;	
	String url;
	
	String countryName;
	String countryCode;
	
	public Place(double lat, double longi){
		this.latitude = lat;
		this.longitude = longi;
		this.url = null;
		this.name = null;
		this.fullName = null;
		this.countryName = null;
		this.countryCode = null;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getGeoURL() {
		return url;
	}

	public void setGeoURL(String geoURL) {
		this.url = geoURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String writeToLogger(){
		StringBuffer res = new StringBuffer();
		res.append(fullName+", "+countryName+"\t["+latitude+", "+longitude+"]\t"+url);
		return res.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
