package com.entrata.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.entrata.base.TestBase;
import com.entrata.utilities.ExtentManager;
import com.entrata.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	String SD = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());
	static String messageBody;
	private static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "\\reports\\" + fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	public static ExtentTest test;

	public static String currentDir;
	public static String outPutFolder;
	public static String ScreenShotFolder;
	public static String FailedScreenShotFolder;
	public static File flOutput;
	public static File flScreenShotFolder, src, dest;
	public static File flFailedScreenShotFolder;
	public static String PassScreenShot;
	public static String FailedScreenShot;

	public static List<ITestNGMethod> passedtests = new ArrayList<ITestNGMethod>();
	public static List<ITestNGMethod> failedtests = new ArrayList<ITestNGMethod>();
	public static List<ITestNGMethod> skippedtests = new ArrayList<ITestNGMethod>();
	public static LocalDateTime startTime;
	public static LocalDateTime endTime;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public void onTestStart(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		log.info("Test Case_  " + methodName.toUpperCase() + " _Successfully Started");

		test = extent.createTest("     @TestCase : " + result.getMethod().getMethodName().toUpperCase());
		testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();

		log.info("Test Case_  " + methodName.toUpperCase() + " _Successfully Passed");

		System.out.println("**********  " + methodName + "  _Get Passed  ***********************");

		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);

		testReport.get().pass(m);
		// testReport.get().assignCategory(TestCaseType);

		testReport.get().assignCategory(Module);

		passedtests.add(result.getMethod());

		try {

			TestUtil.PassScreenShot(methodName);

		} catch (IOException e) {

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestFailure(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		log.error(
				" _ " + methodName.toUpperCase() + " _ " + ": Get Failed" + "\n" + result.getThrowable().getMessage());

		System.out.println("**********  " + methodName + "_Get Failed  ***********************");
		// System.out.println("Exception Message:: " +
		// result.getThrowable().getMessage());

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		// String excepionMessage= result.getThrowable().getMessage();
		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");
		testReport.get().assignCategory(Module);

		failedtests.add(result.getMethod());

		try {

			// TestUtil.captureScreenshot();
			TestUtil.FailScreenShot(methodName);

			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.ScreenShotPath).build());

		} catch (IOException e) {

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);

		// System.exit(1);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

		skippedtests.add(result.getMethod());

		extent.removeTest(test);

		log.info("Test Case_  " + methodName.toUpperCase() + " _Is Skipped As In Runmode it's 'N' ");

		// System.out.println("*****************"+methodName+"***************");

//		int rowNum = excel.getCellRowNum("test_suite", "TC Name",methodName);
//
//		System.out.println(rowNum);
//		excel.setCellData("test_suite", "Status", rowNum, "SKIP");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onStart(ISuite arg0) {

		PassScreenShot = "PassScreenShot_" + SD;
		FailedScreenShot = "FailedScreenShot_" + SD;

		currentDir = System.getProperty("user.dir") + "\\POT";
		outPutFolder = currentDir + "\\POT_" + SD;
		ScreenShotFolder = outPutFolder + "\\" + PassScreenShot;
		FailedScreenShotFolder = outPutFolder + "\\" + FailedScreenShot;

		flOutput = new File(outPutFolder);
		flScreenShotFolder = new File(ScreenShotFolder);
		flFailedScreenShotFolder = new File(FailedScreenShotFolder);
		flOutput.mkdir();
		flScreenShotFolder.mkdir();
		flFailedScreenShotFolder.mkdir();

		startTime = LocalDateTime.now();

	}

	public void onFinish(ISuite arg0) {

		String DataEnginFile = outPutFolder + "//DataEngin_" + SD + ".xlsx";

		File src = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
		File dest = new File(DataEnginFile);

		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		endTime = LocalDateTime.now();
		if (driver != null) {

			extent.flush();
		}

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

}
