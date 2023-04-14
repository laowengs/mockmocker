package com.laowengs.mocker.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 环境变量扩展
 */
@Order
public class TestEnvironmentPostProcessor implements EnvironmentPostProcessor {
 
	private static final Logger logger = LoggerFactory.getLogger(TestEnvironmentPostProcessor.class);
 
	private static final String DEFAULT_SEARCH_LOCATIONS = "classpath:/,classpath:/config/,file:./,file:./config/";
	private static final String DEFAULT_NAMES = "myapp";
	private static final String DEFAULT_FILE_EXTENSION = ".properties";
 
	private static final String PREFIX = "com.spring.environmentpostprocessor.";
	private static final String CALCUATION_MODE = "calculation_mode";
	private static final String GROSS_CALCULATION_TAX_RATE = "gross_calculation_tax_rate";
	private static final String CALCUATION_MODE_DEFAULT_VALUE = "NET";
	private static final double GROSS_CALCULATION_TAX_RATE_DEFAULT_VALUE = 0;
 
	List<String> names = Arrays.asList(CALCUATION_MODE,
			GROSS_CALCULATION_TAX_RATE);

	private static Map<String, Object> defaults = new LinkedHashMap<>();
 
	static {
		defaults.put(CALCUATION_MODE, CALCUATION_MODE_DEFAULT_VALUE);
		defaults.put(GROSS_CALCULATION_TAX_RATE,
				GROSS_CALCULATION_TAX_RATE_DEFAULT_VALUE);
	}
 
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment,
									   SpringApplication application) {
 

		List<String> list = Arrays
				.asList(StringUtils.trimArrayElements(StringUtils
						.commaDelimitedListToStringArray(DEFAULT_SEARCH_LOCATIONS)));
 
		Collections.reverse(list);
		Set<String> reversedLocationSet = new LinkedHashSet<>(list);
		System.out.println(reversedLocationSet);
 
		ResourceLoader defaultResourceLoader = new DefaultResourceLoader();
		// YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new
		// YamlPropertiesFactoryBean();
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
 
		List<Properties> loadedProperties = new ArrayList<>(16);
		// List<Resource> propertiesPathList = new ArrayList<>(16);
 
		reversedLocationSet.forEach(location -> {
			Resource resource = defaultResourceLoader.getResource(location
					+ DEFAULT_NAMES + DEFAULT_FILE_EXTENSION);
			System.out.println(location + DEFAULT_NAMES
					+ DEFAULT_FILE_EXTENSION);
			if (resource == null || !resource.exists()) {
				return;
			}
			System.out.println("################33");
 
			Properties p = new Properties();
			try {
				InputStream inputStream = resource.getInputStream();
				p.load(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
 
			loadedProperties.add(p);
		});
 
		System.out.println(loadedProperties);
 
		Properties filteredProperties = new Properties();
		Set<Object> addedKeys = new LinkedHashSet<>();
		for (Properties propertySource : loadedProperties) {
			for (Object key : propertySource.keySet()) {
				String stringKey = (String) key;
				if (addedKeys.add(key)) {
					filteredProperties.setProperty(stringKey,
							propertySource.getProperty(stringKey));
				}
			}
		}
 

		System.out.println(filteredProperties);
 
		PropertiesPropertySource propertySources = new PropertiesPropertySource(
				DEFAULT_NAMES, filteredProperties);
		environment.getPropertySources().addLast(propertySources);
	}
 
}