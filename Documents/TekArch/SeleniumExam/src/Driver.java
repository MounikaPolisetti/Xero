import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
	static WebDriver driver;

	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		// AutomationScripts.NavigateToXERO();
		/*
		 * AutomationScripts.LoginToSalesForce();
		 * AutomationScripts.CheckRemeberMe();
		 * AutomationScripts.ForgotPassword4A();
		 * AutomationScripts.ForgotPassword4B(); AutomationScripts.TC05();
		 * AutomationScripts.TC06(); AutomationScripts.TC07();
		 * AutomationScripts.TC08(); AutomationScripts.TC09();
		 * AutomationScripts.TC10();
		 */
		String dtTablePath = "/Users/mounikapolisetti/Desktop/TestSuit-S.xls";
		String[][] recData = ReUsableMethods.readExcel(dtTablePath, "Sheet1");
		String testCase, flag, firefox, chrome;
		for (int i = 1; i < recData.length; i++) {

			flag = recData[i][1];
			if (flag.equalsIgnoreCase("Y")) {
				firefox = recData[i][3];
				if (firefox.equalsIgnoreCase("Y")) {

					testCase = recData[i][2];
					ReUsableMethods.startReport(testCase,
							"/Users/mounikapolisetti/Documents/SeleniumExam/");
					System.setProperty("webdriver.gecko.driver",
							"/Users/mounikapolisetti/Downloads/geckodriver");
					driver = new FirefoxDriver();
					Method tc = AutomationScripts.class.getMethod(testCase);
					tc.invoke(tc);
					driver.quit();
					ReUsableMethods.bw.close();

				}
				chrome = recData[i][4];
				if (chrome.equalsIgnoreCase("Y")) {
					testCase = recData[i][2];
					ReUsableMethods.startReport(testCase,
							"/Users/mounikapolisetti/Documents/SeleniumExam/");
					System.setProperty("webdriver.chrome.driver",
							"/Users/mounikapolisetti/Documents/chromedriver");
					driver = new ChromeDriver();
					Method tc = AutomationScripts.class.getMethod(testCase);
					tc.invoke(tc);
					driver.quit();

					ReUsableMethods.bw.close();
				}

			}
		}
	}
}
