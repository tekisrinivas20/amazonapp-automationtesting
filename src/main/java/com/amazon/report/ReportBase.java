package com.amazon.report;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


/**
 * @author Srinivas teki
 *
 */
public class ReportBase {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;
	public static ExtentTest test;
	public static Logger logger;

}
