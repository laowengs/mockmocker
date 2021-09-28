package com.laowengs.mocker.env;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class TestEnvironment implements EnvironmentAware{

    private TestConfig testConfig;

    @Autowired
    public TestEnvironment(TestConfig testConfig) {
        this.testConfig = testConfig;
        System.out.println(testConfig);
    }



    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("wengjp:"+environment.getProperty("wengjp"));
        System.out.println("wengjp2:"+environment.getProperty("wengjp2"));
        System.out.println(environment.getProperty("wengjp", Integer.class)+1);
    }
}
