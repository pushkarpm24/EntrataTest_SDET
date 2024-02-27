package com.entrata.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.entrata.base.TestBase;
import com.entrata.listeners.CustomListeners;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestUtil extends TestBase {

	public static String ScreenShotPath;

	public static void PassScreenShot(String methodName) throws IOException, InterruptedException {

		ScreenShotPath = CustomListeners.ScreenShotFolder + "\\" + " " + methodName.toUpperCase() + ".jpeg";

		// String
		// ScreenShotPath="./target/Passcreenshots/"+DS.TestCaseDes+DS.PassScreenShot+".jpeg";
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "JPEG", new File(ScreenShotPath));

	}

	public static void FailScreenShot(String methodName) throws IOException, InterruptedException {

		ScreenShotPath=CustomListeners.FailedScreenShotFolder+"\\"+" "+methodName.toUpperCase()+".jpeg";
		//String ScreenShotPath="./target/Failscreenshots/"+DS.TestCaseDes+DS.FailedScreenShot+".jpeg";
		Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "JPEG", new File(ScreenShotPath));
		
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		//System.out.println("Sheet name in current data provider is::"+sheetName);
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];

		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String, String>();

			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}
	
	
	@DataProvider(name = "dp2")
	public Object[][] getData2(Method m) {

		String sheetName = m.getName();
		//System.out.println("Sheet name in current data provider is::"+sheetName);
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];

		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String, String>();

			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {

		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);

		for (int rNum = 2; rNum <= rows; rNum++) {

			String testCase = excel.getCellData(sheetName, "TC Name", rNum);

			if (testCase.equalsIgnoreCase(testName)) {

				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				Module=excel.getCellData(sheetName, "Module", rNum);
				TestCaseType=excel.getCellData(sheetName, "Test Type", rNum);

				if (runmode.equalsIgnoreCase("Yes"))
					
					return true;
				else
					return false;
			}

		}
		return false;
	}
	

}
