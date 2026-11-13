package TestListeners;

import Reports.ExtentReportNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Listeners implements ITestListener {

    ExtentReports extent = ExtentReportNG.getReportObject();

    ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(
                result.getMethod().getMethodName()
        );

        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(result.getThrowable());

        try {

            // Retrieve Playwright Page instance
            Page page = (Page) result.getTestClass()
                    .getRealClass()
                    .getField("page")
                    .get(result.getInstance());

            if (page != null && !page.isClosed()) {

                // Create screenshot directory
                Path screenshotDir = Paths.get(
                        System.getProperty("user.dir"),
                        "reports",
                        "screenshots"
                );

                Files.createDirectories(screenshotDir);

                // Screenshot file path
                String fileName = result.getMethod()
                        .getMethodName() + ".png";

                Path screenshotPath =
                        screenshotDir.resolve(fileName);

                // Capture screenshot
                page.screenshot(
                        new Page.ScreenshotOptions()
                                .setPath(screenshotPath)
                );

                // Attach screenshot to Extent Report
                extentTest.get().addScreenCaptureFromPath(
                        screenshotPath.toString(),
                        "Failure Screenshot"
                );
            }

        } catch (Exception e) {

            extentTest.get().warning(
                    "Screenshot capture failed: " + e.getMessage()
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.get().skip("Test Skipped");
    }

    @Override
    public void onStart(ITestContext context) {

        // Test suite execution started
    }

    @Override
    public void onFinish(ITestContext context) {

        // Flush report after execution
        extent.flush();
    }
}
