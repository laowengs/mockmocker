package com.laowengs.mocker.retry;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class RetryAutoConfigureRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RetryBeanDefinitionScanner scanner = new RetryBeanDefinitionScanner(registry, false);
        scanner.setResourceLoader(resourceLoader);
        //这里增加对注解类的扫描
        scanner.addIncludeFilter(new AnnotationTypeFilter(Retry.class));
        //测试直接写死，可改成要scan的目录
        scanner.doScan("com.laowengs");
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
