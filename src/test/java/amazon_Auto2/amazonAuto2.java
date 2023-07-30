package amazon_Auto2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class amazonAuto2 {
	
	WebDriver driver;
	int productwanted=2;
  
	@Test
  public static void amazontest() throws InterruptedException {
	  
	  amazonAuto2 a1=new amazonAuto2();
	 
	  a1.openbrowser();
	  a1.productclicked();
	  //a1.product2clicked();
	  a1.quantityproduct1add();
	 a1.quantityproduct2add();
	 a1.cartcalcu();
  }
  public void openbrowser() {
	  
	  System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
			driver =new ChromeDriver();
			driver.manage().window().maximize() ;
			driver.get("https://www.amazon.co.in");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// Implicit wait 
	  
	  
  }
  public void productclicked() throws InterruptedException {
  
	  
	  for (int i = 0; i <productwanted; i++) 
	  {
		    findElement(By.linkText("Mobiles")).click();
		    WebElement elei = driver.findElement(By.xpath("(((//div[@class='sl-sobe-carousel-viewport'])/div[@class='sl-sobe-carousel-viewport-row']/ol[@class='sl-sobe-carousel-viewport-row-inner'])[2]//following-sibling::li)[" + (i+1) + "]"));
		    scrollIntoView(elei);
		    elei.click();
		    productaddcart();
		}

  }
  
/* public void product2clicked() throws InterruptedException {
	  
	  findElement(By.linkText("Mobiles")).click();
	  WebElement ele4=driver.findElement(By.xpath("(((//div[@class='sl-sobe-carousel-viewport'])/div[@class='sl-sobe-carousel-viewport-row']/ol[@class='sl-sobe-carousel-viewport-row-inner'])[2]//following-sibling::li)[2]"));
//	  JavascriptExecutor js=(JavascriptExecutor)driver;
//	  js.executeScript("arguments[0].scrollIntoView()",ele1);
	  scrollIntoView(driver, ele4);
	  ele4.click();
	  productaddcart();//calling the method from the below
  
}*/

public void quantityproduct1add() throws InterruptedException {
	Thread.sleep(5000);
	//(//div[@class='sc-list-item-content']//child::div[@class='sc-item-content-group']//following-sibling::div[@class='a-row sc-action-links']//span)[1]
	findElement(By.xpath(" (//div[@class='a-row sc-action-links'][1]//span[@class='sc-invisible-when-no-js']//following-sibling::span[@class='a-button a-button-dropdown quantity'])[1]")).click();
	//(//div[@class='a-popover a-dropdown a-dropdown-common a-declarative']//child::div[@class="a-popover-inner"]//li)[2]  
     findElement(By.xpath(" (//div[@class='a-popover a-dropdown a-dropdown-common a-declarative'])[1]//child::div[@class='a-popover-inner']//li[3]")).click();
}

public void quantityproduct2add() throws InterruptedException {
	Thread.sleep(5000);
	//(//div[@class='sc-list-item-content']//child::div[@class='sc-item-content-group']//following-sibling::div[@class='a-row sc-action-links']//span)[1]
	findElement(By.xpath(" (//div[@class='a-row sc-action-links'][1]//span[@class='sc-invisible-when-no-js']//following-sibling::span[@class='a-button a-button-dropdown quantity'])[2]")).click();
	//(//div[@class='a-popover a-dropdown a-dropdown-common a-declarative']//child::div[@class="a-popover-inner"]//li)[2]  
	Thread.sleep(5000);
	findElement(By.xpath(" (//div[@class='a-popover a-dropdown a-dropdown-common a-declarative'])[2]//child::div[@class='a-popover-inner']//li[2]")).click();
}





public void productaddcart() throws InterruptedException {
	//  findElement( By.xpath("((//div[@class='a-button-stack']//child::span[@class='a-button-inner'])//input)[1]")).click();
	 
	  WebElement elex=findElement(By.xpath("//span[text()='Add to Cart']"));
	  scrollIntoView(elex);
	  Actions act=new Actions(driver);
	  act.click(elex).perform();
	  Thread.sleep(7000);

	  WebElement eley=findElement(By.xpath("//div[@class='a-row a-spacing-top-small']//form"));
	  // findElement(By.xpath("//div[@class='a-fixed-left-grid-col a-col-right']//form")).click();
	   act.click(eley).perform();
  
  }

public void cartcalcu() throws InterruptedException {

	 String[] productStringPrices = new String[productwanted];
     String[] productQuantities = new String[productwanted];
     float[] productAmounts = new float[productwanted];

     for (int i = 1; i <= productwanted; i++) {
         // Extract product price
         productStringPrices[i - 1] = driver.findElement(By.xpath("((//div[@class='sc-item-content-group'])[" + i + "]//ul//div)[2]//span")).getText();
         System.out.println("product" + i + " stringprice is: " + productStringPrices[i - 1]);

         // Extract product quantity
         productQuantities[i - 1] = driver.findElement(By.xpath("(//div[@class='sc-item-content-group'])["+i+"]//div[@class='a-row sc-action-links']//span[@class='a-dropdown-prompt']")).getText();
         System.out.println("product" + i + " quant is: " + productQuantities[i - 1]);

         // Calculate product amount
         productAmounts[i - 1] = removablecomma(productQuantities[i - 1]) * removablecomma(productStringPrices[i - 1]);
         System.out.println("Product " + i + " amount: " + productAmounts[i - 1]);

         Thread.sleep(3000); // Add a sleep to introduce a delay if needed
     }
     float Expectedcartsum = 0;
     for (float amount : productAmounts) {
    	 Expectedcartsum += amount;
     }

     System.out.println("Expectedcartsum: " + Expectedcartsum);


	/*String product1stringprice=driver.findElement(By.xpath("((//div[@class=\"sc-item-content-group\"])[1]//ul//div)[2]//child::span")).getText();
	System.out.println("product1stringprice is:"+product1stringprice);
	Thread.sleep(3000);
	String product1quant=driver.findElement(By.xpath("(//div[@class=\"sc-item-content-group\"])[1]//div[@class=\"a-row sc-action-links\"]//span[@class='a-dropdown-prompt']")).getText();
	System.out.println("product1quant is:"+product1quant);
			
	String product2stringprice=driver.findElement(By.xpath("((//div[@class=\"sc-item-content-group\"])[2]//ul//div)[2]//child::span")).getText();
	System.out.println("product2stringprice is:"+product2stringprice);
	Thread.sleep(3000);
	String product2quant=driver.findElement(By.xpath("(//div[@class=\"sc-item-content-group\"])[2]//div[@class=\"a-row sc-action-links\"]//span[@class='a-dropdown-prompt']")).getText();
	System.out.println("product1quant is:"+product2quant);
	
	
	String actualstringtotal=driver.findElement(By.xpath("//div[@class=\"a-row a-spacing-mini sc-subtotal sc-subtotal-activecart sc-java-remote-feature\"]//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")).getText();
	System.out.println("actualstringtotal is:"+actualstringtotal);
	

	 float product1amount = removablecomma(product1quant)*removablecomma(product1stringprice);
	  System.out.println("first product value:"+product1amount);
		
		float  product2price=removablecomma(product2quant)*removablecomma(product2stringprice);
		System.out.println("second product value:"+product2price);
		
		float  Expectedcartsum= product1amount + product2price;
		
	
		
	float Actual_CartAmount=removablecomma(actualstringtotal);
		System.out.println("the calcuated expected value"+Expectedcartsum);
		
	
		
		if(Expectedcartsum==Actual_CartAmount)
		{
			Reporter.log("Cart Amount logic is worked as expected");
			System.out.println("Cart Amount logic is worked as expected");
		}
		
		else {
		
		Reporter.log("cart value is failed");
		System.out.println("cart value is failed");
		}
 */
	
	
}

public static void assertEquals(float expectedcartsum,float actual_CartAmount) {
	
	Assert.assertEquals(expectedcartsum, actual_CartAmount);
	
}
public static float removablecomma(String product) {
	  String str1=product.replace(",","");
	  float f=Float.parseFloat(str1);
	  	return f;
	  	
	  }



  public WebElement findElement(By by) {
	  WebElement ele=driver.findElement(by);
	  
	  if(driver instanceof JavascriptExecutor) {
		  ((JavascriptExecutor)(driver)).executeScript("arguments[0].style.border='5px solid yellow'",ele);
	  }
	  return ele;
	  
  }
  public  void scrollIntoView(WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].scrollIntoView()", element);
  }
}