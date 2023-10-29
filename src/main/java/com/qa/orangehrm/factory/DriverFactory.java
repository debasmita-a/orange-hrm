package com.qa.orangehrm.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	public WebDriver initDriver(Properties prop) {
		OptionManager optionManager = new OptionManager(prop);
		highlight = prop.getProperty("highlight").trim();
		switch(prop.getProperty("browser").toLowerCase().trim()) {
		
		case "chrome" :
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions(prop)));
			break;
		case "edge" :
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions(prop)));
			break;
		case "firefox" :
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions(prop)));
			break;
		default:
			System.out.println("Please provide correct browser name.."+prop.getProperty("browser"));
			break;
		}
		
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
		prop = new Properties();
		FileInputStream fileInput;
		try {
			fileInput = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(fileInput);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		return prop;
	}
	
	public static String getScreenshot() {
		File srcfile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcfile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	

}
