package com.qa.orangehrm.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	
	
	public WebDriver initDriver(Properties prop) {
		highlight = prop.getProperty("highlight").trim();
		switch(prop.getProperty("browser").toLowerCase().trim()) {
		
		case "chrome" :
			driver = new ChromeDriver();
			break;
		case "edge" :
			driver = new EdgeDriver();
			break;
		case "firefox" :
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Please provide correct browser name.."+prop.getProperty("browser"));
			break;
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		
		return driver;
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

}
