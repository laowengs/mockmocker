package com.laowengs.mocker.env;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MockEnvironment implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {
        if(environment instanceof ConfigurableEnvironment){
            ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
            MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
            Properties properties = new Properties();
            properties.put("wengjp","11121");
            properties.put("wengjp2","2222");
            propertySources.addLast(new PropertiesPropertySource("mock",properties));
            propertySources.addLast(new PropertiesPropertySource("mock2",properties));
        }

    }
}
