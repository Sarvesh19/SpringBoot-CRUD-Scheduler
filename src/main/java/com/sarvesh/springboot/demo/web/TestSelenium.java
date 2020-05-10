package com.sarvesh.springboot.demo.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sarvesh.springboot.demo.model.HotelBean;

public class TestSelenium {

	public static void main3(String[] args) {

		WebDriver driver = new HtmlUnitDriver(true);
		String place = "Shimla";

		String dateStartDay = "16";
		String dateStartMonth = "4";
		String dateStartYear = "2020";

		String dateEndDay = "23";
		String dateEndMonth = "5";
		String dateEndYear = "2020";
		String adult = "1";
		String numOfRoom = "1";

		driver.get("https://www.booking.com/searchresults.en-gb.html?aid=378266&label=bdot-Os1*aFx2GVFdW3rxGd0M"
				+ "YQS410513480118%3Apl%3Ata%3Ap1%3Ap22%2C590%2C000%3Aac%3Aap%3Aneg%3Afi%3Atikwd-334108349%3Alp"
				+ "9040245%3Ali%3Adec%3Adm%3Appccp%3DUmFuZG9tSVYkc2RlIyh9YYriJK-Ikd_dLBPOo0BdMww&sid=38db8907"
				+ "f9ff120f4f1b8781b7f07d78&sb=1&sb_lp=1&src=index&src_elem=sb&error_url=https%3A%2F%2F"
				+ "www.booking.com%2Findex.en-gb.html%3Faid%3D378266%3Blabel%3Dbdot-Os1%252AaFx2GVFdW3rxG"
				+ "d0MYQS410513480118%253Apl%253Ata%253Ap1%253Ap22%252C590%252C000%253Aac%253Aap%253"
				+ "Aneg%253Afi%253Atikwd-334108349%253Alp9040245%253Ali%253Adec%253Adm%253Appccp%253DU"
				+ "mFuZG9tSVYkc2RlIyh9YYriJK-Ikd_dLBPOo0BdMww%3Bsid%3D38db8907f9ff120f4f1b8781b7f07d78%3"
				+ "Bsb_price_type%3Dtotal%3Bsrpvid%3D29c23caa634e0072%26%3B&ss=" + place + "&is_ski_area=0&" + "ssne="
				+ place + "&ssne_untouched=" + place + "&dest_id=-2092174&dest_type=city&checkin_year=" + dateStartYear
				+ "&checkin_month=" + dateStartMonth + "&checkin_monthday=" + dateStartDay + "&checkout_year="
				+ dateEndYear + "&checkout_month=" + dateEndMonth + "&" + "checkout_monthday=" + dateEndDay
				+ "&group_adults=" + adult + "&group_children=0&no_rooms=" + numOfRoom
				+ "&b_h4u_keep_filters=&from_sf=1");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String s = driver.findElement(By.xpath("//*[@id=\"right\"]/div[5]/div/div[1]/div")).toString();
		Object row = js.executeScript("return document.getElementsByClassName('sr_header ')[0].innerText\n" + "");
		List<HotelBean> list = new ArrayList<>();
		List<Object> leftCalenderTemp = (List<Object>) js
				.executeScript("return document.getElementById('hotellist_inner').children");

		for (int i = 0; i < leftCalenderTemp.size(); i++) {

			Object hotelName = js.executeScript("return document.getElementById('hotellist_inner').children[" + i
					+ "].getElementsByClassName('sr-hotel__name')[" + i + "].innerText\n" + "");

			Object hotelPrice = js.executeScript("return document.getElementById('hotellist_inner').children[" + i
					+ "].getElementsByClassName('bui-review-score__badge')[" + i + "].innerText\n" + "");

			Object hotelreview = js.executeScript("return document.getElementById('hotellist_inner').children[" + i
					+ "].getElementsByClassName('bui-price-display__value prco-inline-block-maker-helper')[" + i
					+ "].innerText\n" + "");

			list.add(new HotelBean(String.valueOf(hotelName), String.valueOf(hotelPrice), String.valueOf(hotelreview)));

		}

		System.out.println(row);

	}

	public static void main2(String[] args) throws InterruptedException {

		String xPathForStartDate = "";
		String XPathForEndDate = "";
		String xPath;
		String dateStartDay = "16";
		String dateStartMonth = "04";
		String dateStartYear = "2020";

		String dateEndDay = "23";
		String dateEndMonth = "05";
		String dateEndYear = "2020";
		int count = 0;
		String city = "Mumbai";

		WebDriver driver = new HtmlUnitDriver(true);
		driver.get("https://www.booking.com");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// String s =
		// driver.findElement(By.xpath("//*[@id=\"right\"]/div[5]/div/div[1]/div")).toString();
		driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[1]/div[2]")).click();
		//driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]")).click();

		//WebElement el3 = driver.findElement(
		//		By.xpath("/html/body/div[4]/div/div/div[2]/form/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table/tbody/tr[3]/td[5]"));
		//System.out.println(el3);
		
		
		
		
		List<WebElement> allDates=driver.findElements(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table"));
		
		for(WebElement ele:allDates)
		{
			
			String date=ele.getText();
			
			if(date.equalsIgnoreCase("28"))
			{
				ele.click();
				break;
			}
			
		}

		List<Object> leftCalenderTemp = (List<Object>) js
				.executeScript("return document.getElementsByClassName('bui-calendar__row')");
		int divNum = 1;
		while (count != 2) {
			int rowNum = 0;
			for (int i = 1; i <= leftCalenderTemp.size(); i++) {
				rowNum++;
				Object row = js.executeScript(
						"return document.getElementsByClassName('bui-calendar__row')[" + i + "].children[0].innerText");
				// document.getElementsByClassName('bui-calendar__row')[6].children[0].innerText

				if (String.valueOf(row).contains("Su")) {
					rowNum = 0;
					continue;
				}

				List<Object> leftCalenderTemp1 = (List<Object>) js.executeScript(
						"return document.getElementsByClassName('bui-calendar__row')[" + i + "].children");

				if (leftCalenderTemp1.size() == 1) {
					continue;
				}

				for (int j = 1; j <= leftCalenderTemp1.size(); j++) {

					xPath = "//*[@id=\"frm\"]/div[1]/div[2]/" + "div[2]/div/div/div[3]/div[" + divNum
							+ "]/table/tbody/tr[" + (rowNum) + "]/td[" + j + "]";

					xPath = xPath.substring(0,8) + "\\" + xPath.substring(8, xPath.length());
					xPath = xPath.substring(0,13) + "\\" + xPath.substring(13, xPath.length());
					
					
					Object date = js.executeScript("return document.getElementsByClassName('bui-calendar__row')[" + i
							+ "].children[" + (j - 1) + "].dataset.date");

					if (String.valueOf(date).isEmpty()) {
						continue;
					} else if (String.valueOf(date).substring(0, 4).equals(dateStartYear)
							&& String.valueOf(date).substring(5, 7).equals(dateStartMonth)
							&& String.valueOf(date).substring(8, 10).equals(dateStartDay)) {

						dateStartYear = dateEndYear;
						dateStartMonth = dateEndMonth;
						dateStartDay = dateEndDay;
						if (count == 0)
							xPathForStartDate = xPath;

						if (count == 1)
							XPathForEndDate = xPath;
						divNum = 2;

						count++;

						if (count == 2)
							break;

					}

				}

				if (count == 2)
					break;

			}

			if (count != 2) {
				driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[2]")).click();
			} else {
				driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[1]/div[2]")).click();

				Thread.sleep(5000);
				WebElement el = driver.findElement(By.xpath(xPathForStartDate));

				System.out.println(el);
				Thread.sleep(5000);

				driver.findElement(By.xpath(XPathForEndDate)).click();

			}

		}

		WebElement element1 = driver.findElement(By.xpath("//*[@id=\"ss\"]"));

		// Enter a search query
		element1.sendKeys(city);

		driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[4]/div[2]/button")).click();

		System.out.println();

		Object booked = js.executeScript("return document.getElementsByClassName('sr_header ')[0].innerText");

		System.out.println(booked);

	}

	public static void main2() throws InterruptedException {

		WebDriver driver = new HtmlUnitDriver(true);
		driver.get("https://www.booking.com");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[1]/div[2]")).click();

//		WebElement element = driver
//				.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table"));

		List<Object> leftCalenderTemp = (List<Object>) js
				.executeScript("return document.getElementsByClassName('bui-calendar__row')" + "");

		Object s = js.executeScript(
				"return document.getElementsByClassName('bui-calendar__row')[2].children[2].dataset.date" + "");

		driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[1]/div[3]")).click();

		List<Object> rightCalenderTempoo = (List<Object>) js
				.executeScript("return document.getElementsByTagName('tbody')");

		List<Object> rightCalenderTemp = (List<Object>) js
				.executeScript("return document.getElementsByTagName('tbody')[0].children");

		List<Object> leftCalender = (List<Object>) js
				.executeScript("return document.getElementsByTagName('tbody')[0].children");

		List<Object> rightCalender = (List<Object>) js
				.executeScript("return document.getElementsByTagName('tbody')[1].children");
		// List<Object> leftCalender = (List<Object>) js.executeScript(
		// "return
		// document.getElementsByClassName('bui-calendar__content')[0].children[0].children[1].children[1].children");

		// List<Object> rightCalender = (List<Object>) js.executeScript(
		// "return
		// document.getElementsByClassName('bui-calendar__content')[0].children[1].children[1].children[1].children");

		System.out.println(leftCalender);

		// List<Object> size = (List<Object>) js.executeScript(
		// "return
		// document.getElementsByClassName('bui-calendar__dates')[0].getElementsByClassName('bui-calendar__row')");
		int num = leftCalender.size();
		int num1 = rightCalender.size();
		String xPathForStartDate = "";
		String XPathForEndDate = "";
		String xPath;
		String dateStartDay = "16";
		String dateStartMonth = "04";
		String dateStartYear = "2020";

		String dateEndDay = "23";
		String dateEndMonth = "05";
		String dateEndYear = "2020";
		int count = 0;
		String city = "Mumbai";

		while (count != 2) {

			for (int j = 0; j < num; j++) {

				List<Object> size1 = (List<Object>) js
						.executeScript("return document.getElementsByTagName('tbody')[0].children[" + j + "].children");

				for (int i = 0; i < size1.size(); i++) {

					xPath = "//*[@id=\"frm\"]/div[1]/div[2]/" + "div[2]/div/div/div[3]/div[1]/table/tbody/tr[" + (j + 1)
							+ "]/td[" + (i + 1) + "]";

					Object date = js.executeScript("return document.getElementsByTagName('tbody')[0].children[" + j
							+ "].children" + "[" + i + "].dataset.date");

					if (String.valueOf(date).isEmpty()) {
						continue;
					} else if (String.valueOf(date).substring(0, 4).equals(dateStartYear)
							&& String.valueOf(date).substring(5, 7).equals(dateStartMonth)
							&& String.valueOf(date).substring(8, 10).equals(dateStartDay)) {

						dateStartYear = dateEndYear;
						dateStartMonth = dateEndMonth;
						dateStartDay = dateEndDay;
						if (count == 0)
							xPathForStartDate = xPath;

						if (count == 1)
							XPathForEndDate = xPath;

						count++;

						if (count == 2)
							break;

					}

				}

			}

			if (count != 2) {
				for (int j = 0; j < num1; j++) {

					List<Object> size1 = (List<Object>) js.executeScript(
							"return document.getElementsByTagName('tbody')[1].children[" + j + "].children");

					for (int i = 0; i < size1.size(); i++) {

						xPath = "//*[@id=\"frm\"]/div[1]/div[2]/" + "div[2]/div/div/div[3]/div[2]/table/tbody/tr["
								+ (j + 1) + "]/td[" + (i + 1) + "]";

						Object date = js.executeScript("return document.getElementsByTagName('tbody')[1].children[" + j
								+ "].children" + "[" + i + "].dataset.date");

						if (String.valueOf(date).isEmpty()) {
							continue;
						} else if (String.valueOf(date).substring(0, 4).equals(dateStartYear)
								&& String.valueOf(date).substring(5, 7).equals(dateStartMonth)
								&& String.valueOf(date).substring(8, 10).equals(dateStartDay)) {

							dateStartYear = dateEndYear;
							dateStartMonth = dateEndMonth;
							dateStartDay = dateEndDay;
							if (count == 0)
								xPathForStartDate = xPath;

							if (count == 1)
								XPathForEndDate = xPath;

							count++;

							if (count == 2)
								break;

						}

					}

				}
			}

			if (count != 2) {
				driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[2]")).click();
			} else {
				driver.findElement(By.xpath(xPathForStartDate)).click();
				Thread.sleep(500);

				driver.findElement(By.xpath(XPathForEndDate)).click();

			}

		}
		// while end

		WebElement element1 = driver.findElement(By.xpath("//*[@id=\"ss\"]"));

		// Enter a search query
		element1.sendKeys(city);

		driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[4]/div[2]/button")).click();

		System.out.println();

		Object booked = js.executeScript("return document.getElementsByClassName('sr_header ')[0].innerText");

		System.out.println(booked);

		System.out.println("de");
	}

	public static void main1() {

		// DesiredCapabilities caps = new DesiredCapabilities();
		// caps.setJavascriptEnabled(true);
		// caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		// "E:\\phantomjs.exe");

		WebDriver driver = new HtmlUnitDriver(true);

		// WebDriver driver = new PhantomJSDriver(caps);
		driver.get("https://www.amazon.in");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// WebDriver driver = new PhantomJSDriver();

		// Navigate to Google
		// driver.get("http://www.google.com");

		// Locate the searchbox using its name
		WebElement element = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));

		// Enter a search query
		element.sendKeys("titan");

		// Submit the query. Webdriver searches for the form using the text input
		// element automatically
		// No need to locate/find the submit button
		// *[@id="nav-search"]/form/div[2]/div/input
		driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();
		// element.submit();

		WebElement el = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[4]/div[1]"));
		// This code will print the page title
		// for
		// //*[@id="search"]/div[1]/div[1]/div/span[4]/div[1]
		// el.;

		// el.getAttribute(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[4]/div[1]/div[1]/div/span/div/div/div[2]/div[2]"));

		List<Object> size = (List<Object>) js.executeScript(
				"return document.getElementsByClassName('s-result-list s-search-results sg-row')[0].children");

		List<Object> listContent = new ArrayList<>();

		for (int i = 0; i < size.size(); i++) {
			String str = (String) js.executeScript(
					"return document.getElementsByClassName('s-result-list s-search-results sg-row')[0].children" + "["
							+ i + "]" + ".innerText")
					.toString();
			str = str.trim().replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1");
			String[] strList = str.split("\\r?\\n");
			listContent.add(str);

		}

//		List<?> list = new ArrayList<>();
//	    if (obj.getClass().isArray()) {
//	        list = Arrays.asList((Object[])obj);
//	    }
//		

		String allChild = el.findElement(By.cssSelector("*")).getText();
		// *[@id="search"]/div[1]/div[1]/div/span[4]/div[1]

//		for (String string : allChild.get) {
//			
//		}

		List<WebElement> childs = driver.findElements(By.xpath(".//*"));

		// List<WebElement> childs =
		// driver.findElements(By.xpath("//tr[@class='parent']//*"));

		// List<WebElement> childs1 = driver.findElements(By.className("s-result-list
		// s-search-results sg-row"));

		System.out.println(childs.get(0).getText());
		for (WebElement webElement : childs) {

			System.out.println(webElement.getText());

		}

		System.out.println("Page title is: " + driver.getTitle() + el.getText() + allChild);

		driver.quit();
	}

}
