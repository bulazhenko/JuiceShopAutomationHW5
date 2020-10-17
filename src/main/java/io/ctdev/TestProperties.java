package io.ctdev;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources("classpath:TestProperties.properties")
public interface TestProperties extends Config {

    TestProperties config = ConfigFactory.create(TestProperties.class, System.getenv(), System.getProperties());

    @Key("juice.shop.url")
    String juiceShopUrl();
}

