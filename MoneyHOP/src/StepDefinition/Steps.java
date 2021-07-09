package StepDefinition;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;		
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;	

public class Steps {				

	WebDriver driver; 
	String driverloc = System.getProperty("user.dir")+"\\"+"Driver"+"\\"+"chromedriver.exe";
	
    @Given("^Search Cars in Bangalore$")				
    public void Search_Cars_in_Bangalore() throws Throwable							
    {		
        System.out.println("This Step Open the Chrome and Search for Cars in Bangalore.");	
        System.setProperty("webdriver.chrome.driver", driverloc);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Cars in Bangalore");
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.findElement(By.name("btnK")).click();
		
    }		

    @When("^Validating the links for Cardekho or Carwale$")					
    public void Validating_the_links_for_Cardekho_or_Carwale() throws Throwable 							
    {		

    	List <WebElement> linklist = driver.findElements(By.tagName("a"));  	
    	for(int i = 0; i<linklist.size(); i++)
    	{
    		String linkText = linklist.get(i).getText();
    		System.out.println(linkText);
    	}
    	
		
		  List <WebElement> linklist1 = driver.findElements(By.xpath("//cite[contains(text(),'carwale')]")); 
		  System.out.println(linklist1.size());
		  for(int i = 0; i<linklist1.size(); i++) 
		  { 
			  String linkText = linklist1.get(i).getText();
			  System.out.println(linkText); 
		  }
		  		  
		  List <WebElement> linklist2 = driver.findElements(By.xpath("//cite[contains(text(),'cardekho')]")); 
		  System.out.println(linklist2.size());
		  for(int i = 0; i<linklist2.size(); i++) 
		  { 
			  String linkText = linklist2.get(i).getText();
			  System.out.println(linkText); 
		  }
		 
    }		

    @Then("^Navigate to link and confirm the Title$")					
    public void Navigate_to_link_and_confirm_the_Title() throws Throwable 							
    {    		
    	
    	driver.findElement(By.xpath("//cite[contains(text(),'cardekho')]//parent::div[@class = 'TbwUpd NJjxre']")).click();  	  	
    	assertTrue(driver.getTitle().contains("Used Cars in Bangalore"));
    	
    	driver.navigate().back();
    	Thread.sleep(1000);

    	driver.findElement(By.xpath("(//cite[contains(text(),'carwale')]//parent::div[@class = 'TbwUpd NJjxre'])[2]")).click();  	  
    	assertTrue(driver.getTitle().contains("Used Cars in Bangalore"));
    	   	
    	Thread.sleep(1000);
    	driver.quit();
    }
}
