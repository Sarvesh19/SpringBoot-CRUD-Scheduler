package com.howtodoinjava.demo.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.AmazonItemBean;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.model.HotelBean;
import com.howtodoinjava.demo.service.EmployeeService;

import io.github.bonigarcia.wdm.WebDriverManager;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = { "http://localhost:4200", "http://antonboot.s3-website.ap-south-1.amazonaws.com" })
// http://antonboot.s3-website.ap-south-1.amazonaws.com", 
public class EmployeeController {
	@Autowired
	EmployeeService service;
	
	@Autowired
	private EntityManager entityManager;

	@GetMapping("/testHeroku")
	public String getAllEmpl() throws IOException {

		Document document = Jsoup.connect("https://www.trivago.in/").get();
		Elements linksOnPage = document.getElementsByClass("js-query input querytext querytext--padding-right")
				.val("Kerla");

		Response response = Jsoup.connect("https://www.trivago.in/").userAgent("Mozilla/5.0").timeout(10 * 1000)
				.method(Method.POST).data("js-query input querytext querytext--padding-right", "Mumbai")
				.followRedirects(true).execute();

		System.out.println(document);
		System.out.println(response);

		return "Hello World";
	}

	@GetMapping("/getAllEmpolyee")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
		List<EmployeeEntity> list = service.getAllEmployees();

		return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
		EmployeeEntity entity = service.getEmployeeById(id);
		
		// Just for check
		
//		Session currentSession = entityManager.unwrap(Session.class);
//		Query<EmployeeEntity> query = currentSession.createQuery("from EmployeeEntity", EmployeeEntity.class);
//		List<EmployeeEntity> list = query.getResultList();
		
		
		
		
		//List<EmployeeEntity> entity2 = service.findEmployeeByIdNative(id);
		
		//System.out.println(entity2);

		return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping(path = "/save-employee", consumes = "application/json", produces = "application/json")
	public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(@RequestBody EmployeeEntity employee)
			throws RecordNotFoundException {

		EmployeeEntity updated = service.createOrUpdateEmployee(employee);
		return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-student/{id}")
	public HttpStatus deleteEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.deleteEmployeeById(id);
		return HttpStatus.FORBIDDEN;
	}

	@GetMapping("item/{item}")
	public @ResponseBody CompletableFuture<List<AmazonItemBean>> getAmazonTopList(@PathVariable("item") String item) {

		CompletableFuture<List<AmazonItemBean>> list = service.getAmazonProductList(item);
		return list;

	}

	@GetMapping("/hotel/{dateStart}/{dateEnd}/{place}")
	public ResponseEntity<List<HotelBean>> getHotelInfo(@PathVariable("dateStart") String dateStart,
			@PathVariable("dateEnd") String dateEnd, @PathVariable("place") String place) throws IOException {
		String dateStartDay = dateStart.split("-")[0];
		String dateStartMonth = dateStart.split("-")[1];
		String dateStartYear = dateStart.split("-")[2];
		String dateEndDay = dateEnd.split("-")[0];
		String dateEndMonth = dateEnd.split("-")[1];
		String dateEndYear = dateEnd.split("-")[2];
		String adult = "";
		String numOfRoom = "1";

		List<HotelBean> hotelList = service.getHotelSearchedResult(dateStartDay, dateStartMonth, dateStartYear,
				dateEndDay, dateEndMonth, dateEndYear, adult, numOfRoom, place);
		return new ResponseEntity<List<HotelBean>>(hotelList, new HttpHeaders(), HttpStatus.OK);

	}

}