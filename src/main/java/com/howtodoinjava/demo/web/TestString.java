package com.howtodoinjava.demo.web;

public class TestString {
	public static void main(String[] args) {
		String s = "2020-05-02";
		System.out.println(s.substring(0, 4) + s.substring(5, 7) + s.substring(8, 10));

		String str = "//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table/tbody/tr[3]/td[5]";
		str = str.substring(0,8) + "\\" + str.substring(8, str.length());
		str = str.substring(0,13) + "\\" + str.substring(13, str.length());

		System.out.println(str);

	}
}
