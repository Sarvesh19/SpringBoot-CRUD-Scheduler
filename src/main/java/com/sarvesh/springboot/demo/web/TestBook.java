package com.sarvesh.springboot.demo.web;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBook {

	public static void main1(String[] args) throws InterruptedException {

		WebDriver driver = new HtmlUnitDriver(true);
		driver.get("https://www.booking.com");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[1]/div[2]/div")).click();

		WebElement element = driver
				.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table"));

		List<Object> size = (List<Object>) js.executeScript(
				"return  document.evaluate('//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.children[1].children");
		int num = size.size();
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

			for (int j = 1; j < num; j++) {

				List<Object> size1 = (List<Object>) js.executeScript(
						"return document.evaluate('//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table',"
								+ " document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.children[1].children["
								+ j + "]");

				for (int i = 0; i < size1.size(); i++) {

					xPath = "//*[@id=\"frm\"]/div[1]/div[2]/" + "div[2]/div/div/div[3]/div[1]/table/tbody/tr[" + j
							+ "]/td[" + (i + 1) + "]";

					Object date = js.executeScript(
							"return document.evaluate('//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table',"
									+ " document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.children[1].children["
									+ j + "]" + ".children[" + i + "].dataset.date");

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

				List<Object> num1 = (List<Object>) js.executeScript(
						"return  document.evaluate('//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/table', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.children[1].children");

				for (int j = 1; j < num1.size(); j++) {

					List<Object> size1 = (List<Object>) js.executeScript(
							"return document.evaluate('//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/table',"
									+ " document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.children[1].children["
									+ j + "]");

					for (int i = 0; i < size1.size(); i++) {

						xPath = "//*[@id=\"frm\"]/div[1]/div[2]/" + "div[2]/div/div/div[3]/div[2]/table/tbody/tr[" + j
								+ "]/td[" + (i + 1) + "]";

						Object date = js.executeScript(
								"return document.evaluate('//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/table',"
										+ " document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.children[1].children["
										+ j + "]" + ".children[" + i + "].dataset.date");

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
