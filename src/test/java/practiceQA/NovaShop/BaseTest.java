package practiceQA.NovaShop;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseTest {

    Playwright playwright;
    BrowserContext context;
    Page page;
    Properties prop;
    Browser browser;

    @BeforeMethod
    public void launchBrowser() throws IOException {
        // Calling a static create() of playwright and its return a playwright object storing in variable
        playwright =Playwright.create();

        // Reading browser from the properties file
        prop = new Properties();
        FileInputStream fis =new FileInputStream
                (System.getProperty("user.dir")+"/src/main/resources/PropertyFile.properties");
        prop.load(fis);

        String browserName =
                System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        boolean headless = Boolean.parseBoolean(prop.getProperty("headless"));

        // launch method used to invoke the browser, setChannel used to launch chrome from local machine instead of chromium bundles
        if(browserName.equalsIgnoreCase("chrome")){
            browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        }
        else if (browserName.equalsIgnoreCase("chromium")) {
            browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        }
        else {
            throw new IllegalArgumentException("Invalid browser name: " + browserName);

        }
        // Creating object of browser newContext window
        context=browser.newContext();
        // Start tracing with screenshots, snapshots, and sources
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        //Creating page object of the context
        page=context.newPage();
        page.navigate("https://www.practiceqaautomation.com/shop");
    }

    @AfterMethod
    public void tearDown(){
        //Stop the tracing using stop method and storing in a path
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("target","trace.zip")));
    }
}
