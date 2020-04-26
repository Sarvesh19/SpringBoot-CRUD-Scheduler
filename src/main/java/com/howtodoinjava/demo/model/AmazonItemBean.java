package com.howtodoinjava.demo.model;

import org.springframework.stereotype.Component;

@Component
public class AmazonItemBean {

	
	private String itemName;
	
	private String rating;
	
	private String availibility;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getAvailibility() {
		return availibility;
	}

	public void setAvailibility(String availibility) {
		this.availibility = availibility;
	}

	@Override
	public String toString() {
		return "AmazonItemBean [itemName=" + itemName + ", rating=" + rating + ", availibility=" + availibility + "]";
	}
	
	
	
	
}
