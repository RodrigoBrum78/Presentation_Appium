

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Orientation_Etc {
    private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APPIUM = "http://localhost:4723/wd/hub";

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Emulator");
       // caps.setCapability("deviceName", "CVH7N15A15002877");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", APP);
        driver = new AndroidDriver(new URL(APPIUM), caps);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testScreenMethods() throws IOException {
    	
    	// Work with Orientation API
        ScreenOrientation curOrientation = driver.getOrientation();
        System.out.println("Orientation: " + curOrientation);
        if (curOrientation != ScreenOrientation.LANDSCAPE) {
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }
        
        // Window size
        Dimension size = driver.manage().window().getSize();
        System.out.println("Screen size: " + size);
        
        // Take Screenshot
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        File saveFile = new File("/Users/rodri/Desktop/screen.png");
        FileUtils.copyFile(screenshot, saveFile);
        
        // Return the device in PORTRAIT mode
        driver.rotate(ScreenOrientation.PORTRAIT);
    }
}
