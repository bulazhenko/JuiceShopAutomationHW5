package io.ctdev.cucumber.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public interface TestConfig extends Config {
    TestConfig cfg = ConfigFactory.create(TestConfig.class);

    @DefaultValue("true")
    boolean remote();

    @DefaultValue("chrome")
    String browser();

    @DefaultValue(" http://3.18.213.48")
    String url();

}