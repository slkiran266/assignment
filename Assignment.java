package PackAut;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Assignment {
	
	public static void main(String args[]) {
		try {
			
			//intializing the driver
			System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://www.fitpeo.com/");
			
			//opening revenue calculator
			if(driver.findElement(By.xpath("//button[@aria-label='open drawer']")).isDisplayed()) {
				driver.findElement(By.xpath("//span[contains(text(),'Revenue Calculator')]")).click();
			}else {
				driver.findElement(By.xpath("//div[contains(text(),'Revenue Calculator')]")).click();
			}
			
			//waiting to load
			Thread.sleep(3000);
			

			
			WebElement sliderRevenue = driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-root')]//input[@type='range']"));
			
			//scroll down to revenue calculator slider
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sliderRevenue);
			
			if (sliderRevenue.isDisplayed()) {
	            System.out.println("Slider is visible.");
	        } else {
	            System.out.println("Slider is not visible.");
	            return;
	        }
			
			//updating the slider to 820
			Actions actions = new Actions(driver);
			actions.clickAndHold(sliderRevenue).moveByOffset(93, 0).release().perform();
			WebElement inputFieldRevenue = driver.findElement(By.xpath("//input[@type='number']"));

			String numberToEnter = "560";
			
			//updating to 560
			actions.click(inputFieldRevenue)
	        .keyDown(Keys.CONTROL)
	        .sendKeys("a")
	        .keyUp(Keys.CONTROL)
	        .sendKeys(Keys.BACK_SPACE)
	        .sendKeys(numberToEnter)
	        .perform(); 
			
			
			
			String numberPresent = sliderRevenue.getAttribute("aria-valuenow");
			
			//checking wether number was updated accordingly
			if(numberToEnter.equals(numberPresent)) {
				System.out.println("The value 560 is entered in the text field, the slider's position is updated to reflect the value 560.");
			}
			else {
				System.out.println("value entered incorrect");
			}
			
			//updating to 820 as before
			actions.click(inputFieldRevenue)
	        .keyDown(Keys.CONTROL)
	        .sendKeys("a")
	        .keyUp(Keys.CONTROL)
	        .sendKeys(Keys.BACK_SPACE)
	        .sendKeys("820")
	        .perform();
			
			
			//click the desired check boxes
			driver.findElement(By.xpath("//div/p[contains(text(),'CPT-99091')]//following-sibling::label/span/input")).click();
			driver.findElement(By.xpath("//div/p[contains(text(),'CPT-99453')]//following-sibling::label/span/input")).click();
			driver.findElement(By.xpath("//div/p[contains(text(),'CPT-99454')]//following-sibling::label/span/input")).click();
			driver.findElement(By.xpath("//div/p[contains(text(),'CPT-99474')]//following-sibling::label/span/input")).click();
			
			//checking the revenue bill
			String headerTextValidate = "Total Recurring Reimbursement for all Patients Per Month:\n"+ "$110700";
			String headerTextPresent= driver.findElement(By.xpath("(//div[contains(@class,'MuiBox')]/header/div/p)[4]")).getText();
			
			if(headerTextValidate.equals(headerTextPresent)) {
				System.out.println("Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $110700.");
			}
			else {
				System.out.println("value incorrect");
			}
			
			
			//closing the drivers
			driver.close();
			
	        //printing test case is passed
			System.out.println("All steps were passed");

			}catch (Exception e){
				System.out.println("Something went wrong, test case failed: \n"+e);
		}

	}

}
