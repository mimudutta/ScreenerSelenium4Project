package Screener.Selenium4Project.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties properties;
	public static FileInputStream fis;
	public static long PAGE_LOAD_TIMEOUT =15;
	public static long IMPLICITE_WAIT =10;
	
	public TestBase() {
		
		try {
			properties=new Properties();
			fis=new FileInputStream(System.getProperty("user.dir")+
										"\\src\\main\\java\\Screener\\Selenium4Project\\ExternalFiles\\config.properties");
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
	        if (fis != null) {
	            try {
	                fis.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}
		}

	public static void initialization() {
		//System.out.println(properties.getProperty("browser"));
		String browserName=properties.getProperty("browser");
			if(browserName.equalsIgnoreCase("chrome")) {
				driver= WebDriverManager.chromedriver().create();
				//driver=WebDriverManager.chromedriver().driverVersion("93.0.4577.63").setup(); //If wanted to execute on some specific browser version
				
			}
			else if(browserName.equalsIgnoreCase("firefox")) {
				driver= WebDriverManager.firefoxdriver().create(); 
			}
			
			else if(browserName.equalsIgnoreCase("firefoxheadless")) {				
				FirefoxOptions options=new FirefoxOptions();
				options.addArguments("window-size=1400,800");
				options.addArguments("headless");
				driver= WebDriverManager.firefoxdriver().capabilities(options).create();
			}
		
			else  {
				WebDriverManager.chromedriver().setup(); 
				ChromeOptions options=new ChromeOptions();
				options.addArguments("window-size=1400,800");
				options.addArguments("headless");
				driver= new ChromeDriver(options);		
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITE_WAIT));

		driver.get(properties.getProperty("PRODUCT_URL"));	
	}
	
	public static void tearDown() {
		driver.quit();
	}
	
}
