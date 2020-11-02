package io.ctdev.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = "pretty",
        monochrome = true,
        glue = "io.ctdev.cucumber.steps",
        features = "C:\\Projects\\AutomationHW4\\src\\test\\java\\io\\ctdev\\cucumber\\features\\JuiceShopSignUpTestCucumber.feature"
)
public class CucumberBaseTest extends AbstractTestNGCucumberTests {
}
