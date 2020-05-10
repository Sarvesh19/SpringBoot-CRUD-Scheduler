package com.sarvesh.springboot.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.asynchandler.AsyncConfiguration;
import com.asynchandler.Hosting;
import com.sarvesh.springboot.demo.exception.RecordNotFoundException;
import com.sarvesh.springboot.demo.model.AmazonItemBean;
import com.sarvesh.springboot.demo.model.EmployeeEntity;
import com.sarvesh.springboot.demo.model.HotelBean;
import com.sarvesh.springboot.demo.repository.EmployeeRepository;

@Service
@Import({AsyncConfiguration.class})
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private Hosting hosting;
	
	
	@Autowired
	private RestTemplate restTemp;
	
//	@Autowired
//	private Executor executor;
//	
	
	


	@Autowired
	List<AmazonItemBean> itm;
	
	
	@Primary
	@Scheduled(cron = "0 59 15 1/1 * ?")
	public void cronJobSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Java cron job expression:: " + strDate);
	}
	
	

	// @Autowired
	// private TaskExecutor taskExecutor;

	//@Async
	public List<EmployeeEntity> getAllEmployees() {
		List<EmployeeEntity> employeeList = repository.findAll();

		if (employeeList.size() > 0) {
			return employeeList;
		} else {
			return new ArrayList<EmployeeEntity>();
		}
	}

	//@Async

	public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
		Optional<EmployeeEntity> employee = repository.findById(id);
		System.out.println(this.restTemp);
		//System.out.println(executor);
		System.out.println(hosting);
		
		
		//Collection<E>

		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}
//	public EmployeeEntity findEmployeeByIdNative(Long id) throws RecordNotFoundException {
//		Optional<EmployeeEntity> employee = repository.findEmployeeByIdNative(id);
//
//		if (employee.isPresent()) {
//			return employee.get();
//		} else {
//			throw new RecordNotFoundException("No employee record exist for given id");
//		}
//	}
	//ALTER TABLE VEHICLE_DETAILS ADD id SMALLINT UNSIGNED NOT NULL DEFAULT 0;



	//ALTER TABLE VEHICLE_DETAILS ADD CONSTRAINT fk_id FOREIGN KEY (id) REFERENCES TBL_EMPLOYEES(id);
	
	
//	
	
	public List<EmployeeEntity> findEmployeeByIdNative(Long id) throws RecordNotFoundException {
//		Session currentSession = entityManager.unwrap(Session.class);
//		Query<EmployeeEntity> query = currentSession.createQuery("from EmployeeEntity", EmployeeEntity.class);
//		List<EmployeeEntity> list = query.getResultList();
		
		
//		Query query = entityManager.createNativeQuery(  "select * from  TBL_EMPLOYEES  t where t.id = ?");  
//		   query.setParameter(1, 1);  
//		 Object o =   query.getSingleResult();
//		 System.out.println(o);
		
		
		return null;
	}
	
	

	@Async("taskExecutor")

	public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException

	{

		entity = repository.save(entity);

		Optional<EmployeeEntity> employee = repository.findById(entity.getId());

		if (employee.isPresent()) {
			EmployeeEntity newEntity = employee.get();
			newEntity.setEmail(entity.getEmail());
			newEntity.setFirstName(entity.getFirstName());
			newEntity.setLastName(entity.getLastName());

			newEntity = repository.save(newEntity);

			return newEntity;
		} else {
			entity = repository.save(entity);

			return entity;
		}
	}

	@Async

	public void deleteEmployeeById(Long id) throws RecordNotFoundException {
		Optional<EmployeeEntity> employee = repository.findById(id);

		if (employee.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public CompletableFuture<List<AmazonItemBean>> getAmazonProductList(String item) {
		// List<AmazonItemBean> itm = new ArrayList<>();

		// LOGGER.info("Request to get a list of cars");

		WebDriver driver = new HtmlUnitDriver(true);

		driver.get("https://www.amazon.in");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Locate the searchbox using its name
		WebElement element = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));

		// Enter a search query
		element.sendKeys(item);

		driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();

		List<Object> size = (List<Object>) js.executeScript(
				"return document.getElementsByClassName('s-result-list s-search-results sg-row')[0].children");

		List<String> listContent = new ArrayList<>();
		for (int i = 0; i < size.size(); i++) {
			String str = (String) js.executeScript(
					"return document.getElementsByClassName('s-result-list s-search-results sg-row')[0].children" + "["
							+ i + "]" + ".innerText")
					.toString();
			str = str.trim().replaceAll("(?m)(^ *| +(?= |$))", "").replaceAll("(?m)^$([\r\n]+?)(^$[\r\n]+?^)+", "$1");
			String[] strList = str.split("\\r?\\n");
			AmazonItemBean am = new AmazonItemBean();
			am.setItemName(strList[0]);

			if (strList.length >= 2) {
				am.setRating(strList[2]);

			}
			am.setAvailibility(strList[strList.length - 1]);
			itm.add(am);
			listContent.add(str);

		}

		return CompletableFuture.completedFuture(itm);

	}

	public List<HotelBean> getHotelSearchedResult(String dateStartDay, String dateStartMonth, String dateStartYear,
			String dateEndDay, String dateEndMonth, String dateEndYear, String adult, String numOfRoom, String place) {
		WebDriver driver = new HtmlUnitDriver(true);
		// String place = ;

		// dateStartDay = "16";
		// dateStartMonth = "4";
		// dateStartYear = "2020";

		// dateEndDay = "23";
		// dateEndMonth = "5";
		// dateEndYear = "2020";
		// adult = "1";
		// numOfRoom = "1";

		String link = "https://www.booking.com/searchresults.en-gb.html?aid=378266&label=bdot-Os1*aFx2GVFdW3rxGd0M"
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
				+ "&b_h4u_keep_filters=&from_sf=1";

		driver.get(link);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String s = driver.findElement(By.xpath("//*[@id=\"right\"]/div[5]/div/div[1]/div")).toString();
		Object row = js.executeScript("return document.getElementsByClassName('sr_header ')[0].innerText\n" + "");
		List<HotelBean> list = new ArrayList<>();
		List<Object> leftCalenderTemp = (List<Object>) js
				.executeScript("return document.getElementById('hotellist_inner').children");

		for (int i = 0; i < leftCalenderTemp.size(); i++) {

			String script = "return document.getElementById('hotellist_inner').children[" + i
					+ "].getElementsByClassName('sr-hotel__name')[" + i + "].innerText";
			
			String script1 ="return document.getElementById('hotellist_inner').children[0].children[1].children[0].children[0].innerText" + 
					"";

			Object hotelName = js.executeScript(script1);

			Object hotelPrice = js.executeScript("return document.getElementById('hotellist_inner').children[" + i
					+ "].getElementsByClassName('bui-review-score__badge')[" + i + "].innerText\n" + "");

			Object hotelreview = js.executeScript("return document.getElementById('hotellist_inner').children[" + i
					+ "].getElementsByClassName('bui-price-display__value prco-inline-block-maker-helper')[" + i
					+ "].innerText\n" + "");

			list.add(new HotelBean(String.valueOf(hotelName), String.valueOf(hotelPrice), String.valueOf(hotelreview)));

		}

		return list;
	}

}