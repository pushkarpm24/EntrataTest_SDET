package com.entrata.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Capabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.entrata.base.TestBase;

public class ExtentManager {

	public static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {

		extent = new ExtentReports();

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName).viewConfigurer().viewOrder()
				.as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.AUTHOR, ViewName.CATEGORY,
						ViewName.DEVICE, ViewName.EXCEPTION, ViewName.LOG })
				.apply();

		// htmlReporter.config().setTheme(Theme.STANDARD);
		// htmlReporter.config().setDocumentTitle(fileName);
		// htmlReporter.config().setEncoding("utf-8");
		// htmlReporter.config().setReportName(fileName);
		htmlReporter.config().setTimelineEnabled(false);
		htmlReporter.config().setCss(".sysenv-container{right:50%} .category-container{left:50%}");
		htmlReporter.config().setJs(
                "document.querySelector('.category-container .card .card-header p').innerHTML='Cases/Scenarios <br> Note: Skipped Tests - Either not selected during run / error during run';");

		try {
			htmlReporter.loadXMLConfig(new File(".\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("Automation Tester", "Pushkar Morey");
		extent.setSystemInfo("Organization", "Entrata Test");
		extent.setSystemInfo("Build no", "0.1");

		return extent;
	}

}
