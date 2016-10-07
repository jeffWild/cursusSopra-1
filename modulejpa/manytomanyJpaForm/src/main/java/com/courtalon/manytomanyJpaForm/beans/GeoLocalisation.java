package com.courtalon.manytomanyJpaForm.beans;

import javax.persistence.Embeddable;

@Embeddable
public class GeoLocalisation {
	private double longitude;
	private double latitude;
	
	public double getLongitude() {return longitude;}
	public void setLongitude(double longitude) {this.longitude = longitude;}
	public double getLatitude() {return latitude;}
	public void setLatitude(double latitude) {this.latitude = latitude;}
	
	public GeoLocalisation() {this(0, 0); }
	public GeoLocalisation(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "GeoLocalisation [longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	

}
