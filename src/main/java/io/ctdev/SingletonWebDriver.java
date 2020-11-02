package io.ctdev;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SingletonWebDriver {
    private static SingletonWebDriver instanceOfSingletonDriver = null;

    private ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    private SingletonWebDriver() {
        switch (System.getProperty("driverType").toLowerCase()) {
            case "chrome":
                if (TestProperties.config.remote()) {
                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    capabilities.setBrowserName(BrowserType.CHROME);
                    try {
                        drivers.set(new RemoteWebDriver(new URL(TestProperties.config.remoteUrl()), capabilities));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver());
                }
                break;
            case "firefox":
                if (TestProperties.config.remote()) {
                    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    capabilities.setBrowserName(BrowserType.FIREFOX);
                    //capabilities.setCapability(CapabilityType.BROWSER_VERSION,"82.0");
                    try {
                        drivers.set(new RemoteWebDriver(new URL(TestProperties.config.remoteUrl()), capabilities));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    WebDriverManager.firefoxdriver().setup();
                    drivers.set(new FirefoxDriver());
                }
                break;
            case "edge":
                if (TestProperties.config.remote()) {
                    DesiredCapabilities capabilities = DesiredCapabilities.edge();
                    capabilities.setBrowserName(BrowserType.EDGE);
                    try {
                        drivers.set(new RemoteWebDriver(new URL(TestProperties.config.remoteUrl()), capabilities));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    WebDriverManager.edgedriver().setup();
                    drivers.set(new EdgeDriver());
                }
                break;
            default:
                System.err.println("Invalid driver type: " + System.getProperty("driverType"));
                break;
        }
        drivers.get().manage().window().maximize();
        drivers.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static WebDriver getInstanceOfSingletonWebDriver() {
        if (instanceOfSingletonDriver == null) {
            instanceOfSingletonDriver = new SingletonWebDriver();
        }
        return instanceOfSingletonDriver.drivers.get();

    }
}
