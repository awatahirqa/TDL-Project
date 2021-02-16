package com.qa.springtdl.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Sql(scripts =  {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ToDoListSeleniumTest {
	private static RemoteWebDriver driver;
	private static WebElement targ;
	private final String URL = "http://localhost:8080/ToDoList.html";
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
		// closes the chrome driver! this is essential
		driver.quit();
		//clean up extent reports
		report.flush();
		report.close();

	} 
	@Test
	public void createTodolist() throws InterruptedException{
		test = report.startTest("Create Todolist Selenium Test");
		//Spin up URL 
		driver.get(URL);
		//Input Vales
		targ.findElement(By.name("ListCreate"));
		targ.sendKeys("selenium test case");
		//execute create
		targ.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/button"));
		targ.click();
		targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"createConfirmed\"]/p")));
		
		//targ = driver.findElement(By.xpath("//*[@id=\"createConfirmed\"]/p"));
		String result = targ.getText();
		String expected = "your List pokemon was created";
		assertEquals(expected,result);
}
	@Test
	public void readallTodolist() throws InterruptedException{
		test = report.startTest("readall Todolist Selenium Test");
		//Spin up URL 
		driver.get(URL);
		//Input Vales
		targ.findElement(By.id("ListName"));
		targ.sendKeys("selenium test case");
		//execute create
		targ.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/button"));
		targ.click();
		//execute read all
		targ.findElement(By.xpath("/html/body/div[2]/div/div/button"));
		targ.click();
targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"readall\"]/p")));
		
		//targ = driver.findElement(By.xpath("//*[@id="readall"]/p"));
		String result = targ.getText();
		String expected = "undefined | selenium test case";
		assertEquals(expected,result);
}
	@Test
	public void readbyidTodolist() {
		test = report.startTest("readbyid Todolist Selenium Test");
		//Spin up URL 
		driver.get(URL);
		//Input Vales
		targ.findElement(By.name("ListCreate"));
		targ.sendKeys("selenium test case");
		//execute create
		targ.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/button"));
		targ.click();
		//input id for read
		targ.findElement(By.name("ListNameRead"));
		targ.sendKeys("1");
		//execute read by id
		targ.findElement(By.xpath("/html/body/div[3]/div[2]/div/button"));
		targ.click();
targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"readByID\"]/p")));
		
		//targ = driver.findElement(By.xpath("//*[@id="readByID"]/p"));
		String result = targ.getText();
		String expected = "{\"list_id\":21,\"name\":\"selenium test case\",\"tasklist\":[]}";
		assertEquals(expected,result);
		
}
	@Test
	public void updateTodolist()throws InterruptedException {
		test = report.startTest("update Todolist Selenium Test");
		//Spin up URL 
		driver.get(URL);
		//Input Vales 
		targ.findElement(By.name("ListCreate"));
		targ.sendKeys("selenium test case");
		//execute create
		targ.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/button"));
		targ.click();
		//input id for update
		targ.findElement(By.id("ListIdUpdate"));
		targ.sendKeys("1");
		//input new name
		targ.findElement(By.id("ListNameUpdate"));
		targ.sendKeys("update test");
		//execute update
		targ.findElement(By.xpath("/html/body/div[5]/div[2]/div/button"));
		targ.click();
targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"updateConfirmed\"]/p")));
		
		//targ = driver.findElement(By.xpath("//*[@id="updateConfirmed"]/p"));
		String result = targ.getText();
		String expected = "your todolist 1 was updated)";
		assertEquals(expected,result);
		
}
	@Test
	public void deleteTodolist()throws InterruptedException {
		test = report.startTest("delete Todolist Selenium Test");
		//Spin up URL 
		driver.get(URL);
		//Input Vales 
		targ.findElement(By.name("ListCreate"));
		targ.sendKeys("selenium test case");
		//execute create
		targ.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/button"));
		targ.click();
		//input id for delete
		targ.findElement(By.id("ListNameDelete"));
		targ.sendKeys("1");
		//execute delete
		targ.findElement(By.xpath("/html/body/div[5]/div[2]/div/button"));
		targ.click();
		
targ = new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"DeleteConfirmed\"]/p")));
		
		//targ = driver.findElement(By.xpath("//*[@id="DeleteConfirmed"]/p"));
		String result = targ.getText();
		String expected = "your List with id your List with id 2 was deleted";
		assertEquals(expected,result);
		
}
}
