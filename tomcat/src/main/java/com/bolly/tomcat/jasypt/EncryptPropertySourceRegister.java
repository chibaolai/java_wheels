package com.bolly.tomcat.jasypt;

import com.bolly.support.utils.UnexpectedException;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.spring31.properties.EncryptablePropertiesPropertySource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

public class EncryptPropertySourceRegister implements BeanFactoryPostProcessor, EnvironmentAware,
    ResourceLoaderAware {

    private String propertyFileLocation = "classpath:/app.properties";

    private ResourceLoader resourceLoader;

    private Environment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(JasyptGui.DEFAULT_SALT);
        EncryptableProperties encryptableProperties = new EncryptableProperties(stringEncryptor);
        try (InputStream inputStream = resourceLoader.getResource(propertyFileLocation).getInputStream()) {
            encryptableProperties.load(inputStream);
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
        EncryptablePropertiesPropertySource propertySource = new EncryptablePropertiesPropertySource(
            "encryptablePropertiesPropertySource", encryptableProperties);
        ((ConfigurableEnvironment) environment).getPropertySources().addLast(propertySource);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setPropertyFileLocation(String propertyFileLocation) {
        this.propertyFileLocation = propertyFileLocation;
    }

}
