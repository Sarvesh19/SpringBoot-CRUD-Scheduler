package com.howtodoinjava.demo.model;

import org.springframework.stereotype.Component;

@Component
public class HotelBean {
	private String hotelName;

	private String review;
	private String price;

	public HotelBean(String hotelName, String review, String price) {
		super();
		this.hotelName = hotelName;
		this.review = review;
		this.price = price;
	}
	
	

	public HotelBean() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "HotelBean [hotelName=" + hotelName + ", review=" + review + ", price=" + price + "]";
	}

}
