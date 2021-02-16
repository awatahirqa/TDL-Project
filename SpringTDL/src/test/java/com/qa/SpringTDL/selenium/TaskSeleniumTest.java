package com.qa.springtdl.selenium;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Sql(scripts =  {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TaskSeleniumTest {
	
	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/Task.html";
	private static ExtentReports report;
	private static ExtentTest test;
	
	@BeforeAll
	public static void beforeAll() {
		//extent report
		report = new ExtentReports("target/TestReports/AllTaskReport.html", true); 
		
		
		//driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
		
		ChromeOptions config = new ChromeOptions();
		config.setHeadless(!true);// this stops the window pop up if true - this can reduce the time of the
									// runtime test
		driver = new ChromeDriver(config);
		driver.manage().window().setSize(new Dimension(1366, 786)); // sets the size of the window that runs the test
	}
	@AfterEach
	public void endTest() {
		report.endTest(test);
	}
	

	@AfterAll
	public static void afterAll() {
		// closes the chrome driver
		driver.quit();
		//clean up extent reports
		report.flush();
		report.close();

	} 
	@Test
	public void createTask() throws InterruptedException{
		test = report.startTest("Create Task Selenium Test");
		//Spin up URL 
		driver.get(URL);
		//Input Vales
		targ.findElement(By.xpath("//*[@id=\"summary\"]"));
		targ.sendKeys("selenium test case");
		targ.findElement(By.xpath("//*[@id=\"priority\"]"));
		targ.sendKeys("2");
		targ.findElement(By.xpath("//*[@id=\"deadline\"]"));
		targ.sendKeys("20/02/2021");
		targ.findElement(By.xpath("//*[@id=\"myList\"]"));
		targ.sendKeys("1");
		targ.findElement(By.xpath("//*[@id=\"status\"]"));
		targ.sendKeys("Ongoing");
		//execute create
		targ.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button"));
		targ.click();
		//then check it has worked 
				targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"createConfirmed\"]/p")));
				
				//targ = driver.findElement(By.xpath("//*[@id=\"createConfirmed\"]/p"));
				String result = targ.getText();
				String expected = "was created, use read all to find the id";
				
}
	@Test
	public void readalltest() throws InterruptedException {
		test = report.startTest("read all Selenium Test");
		//Spin up URL 
		driver.get(URL);
		//Input Vales
		targ.findElement(By.xpath("//*[@id=\"summary\"]"));
		targ.sendKeys("selenium test case");
		targ.findElement(By.xpath("//*[@id=\"priority\"]"));
		targ.sendKeys("2");
		targ.findElement(By.xpath("//*[@id=\"deadline\"]"));
		targ.sendKeys("20/02/2021");
		targ.findElement(By.xpath("//*[@id=\"myList\"]"));
		targ.sendKeys("1");
		targ.findElement(By.xpath("//*[@id=\"status\"]"));
		targ.sendKeys("Ongoing");
				//execute create
				targ.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button"));
				//click on readall
				targ.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/button"));
	}
	@Test
	public void readbyidtest() throws InterruptedException{
		test = report.startTest("read by id Selenium Test");
		//Spin up URL 
		driver.get(URL);
		//Input Vales
		targ.findElement(By.xpath("//*[@id=\"summary\"]"));
		targ.sendKeys("selenium test case");
		targ.findElement(By.xpath("//*[@id=\"priority\"]"));
		targ.sendKeys("2");
		targ.findElement(By.xpath("//*[@id=\"deadline\"]"));
		targ.sendKeys("20/02/2021");
		targ.findElement(By.xpath("//*[@id=\"myList\"]"));
		targ.sendKeys("1");
		targ.findElement(By.xpath("//*[@id=\"status\"]"));
		targ.sendKeys("Ongoing");
				//execute create
				targ.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button"));
				//click on readall
				targ.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/button"));
	}
	@Test
	public void updatetest() {
		test = report.startTest("update Selenium Test");
		//Spin up URL 
		driver.get(URL);
		//Input Vales
		targ.findElement(By.xpath("//*[@id=\"summary\"]"));
		targ.sendKeys("selenium test case");
		targ.findElement(By.xpath("//*[@id=\"priority\"]"));
		targ.sendKeys("2");
		targ.findElement(By.xpath("//*[@id=\"deadline\"]"));
		targ.sendKeys("20/02/2021");
		targ.findElement(By.xpath("//*[@id=\"myList\"]"));
		targ.sendKeys("1");
		targ.findElement(By.xpath("//*[@id=\"status\"]"));
		targ.sendKeys("Ongoing");
				//execute create
				targ.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button"));
				//fill in update fields
				targ.findElement(By.id("SummaryUpdate"));
				targ.sendKeys("update test");
				targ.findElement(By.id("PriorityUpdate"));
				targ.sendKeys("1");
				targ.findElement(By.id("DeadLineUpdate"));
				targ.sendKeys("25/03/2021");
				targ.findElement(By.id("MyListIDUpdate"));
				targ.sendKeys("1");
				targ.findElement(By.id("StatusUpdate"));
				targ.sendKeys("completed");
				targ.findElement(By.id("TaskIdUpdate"));
				targ.sendKeys("1");
				//execute update
				targ.findElement(By.xpath("/html/body/div[4]/div[2]/div/div/div/div/button"));
	}
	
	@Test
	public void deletetest() throws InterruptedException{
		test = report.startTest("delete Selenium Test");
		//Spin up URL 
		driver.get(URL);
		//Input Vales
		targ.findElement(By.xpath("//*[@id=\"summary\"]"));
		targ.sendKeys("selenium test case");
		targ.findElement(By.xpath("//*[@id=\"priority\"]"));
		targ.sendKeys("2");
		targ.findElement(By.xpath("//*[@id=\"deadline\"]"));
		targ.sendKeys("20/02/2021");
		targ.findElement(By.xpath("//*[@id=\"myList\"]"));
		targ.sendKeys("1");
		targ.findElement(By.xpath("//*[@id=\"status\"]"));
		targ.sendKeys("Ongoing");
				//execute create
				targ.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/button"));
				//input ID to delete 
				targ.findElement(By.id("TaskIdDelete"));
				targ.sendKeys("1");
				//execute delete 
				targ.findElement(By.xpath("/html/body/div[5]/div[2]/div/button"));
}
}