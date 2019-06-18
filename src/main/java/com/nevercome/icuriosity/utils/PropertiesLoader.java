package com.nevercome.icuriosity.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Properties文件的加载类
 * Properties值的获取由Global这个类来完成
 * 以system的properties为优先
 *
 * @author: sun
 * @date: 2019/6/18
 */
@Slf4j
public class PropertiesLoader {

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    private final Properties properties;

    public PropertiesLoader(String... resourcePaths) {
        this.properties = loadProperties(resourcePaths);
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * 取出String类型的Property，但以System的Property优先,如果都为Null则抛出异常.
     */
    public String getProperty(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    /**
     * 取出String类型的Property，但以System的Property优先.如果都为Null则返回Default值.
     */
    public String getProperty(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 取出Property，但以System的Property优先,取不到返回空字符串.
     */
    private String getValue(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return "";
    }

    /**
     * 取出Integer类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
     */
    public Integer getInteger(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Integer.valueOf(value) : defaultValue;
    }

    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
     */
    public Double getDouble(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Double.valueOf(value);
    }

    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都为Null抛出异常,如果内容不是true/false则返回false.
     */
    public Boolean getBoolean(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Boolean.valueOf(value);
    }

    private Properties loadProperties(String... resourcePaths) {
        Properties properties = new Properties();
        for (String path : resourcePaths) {
            log.debug("Loading properties file from: {}", path);

            InputStream is = null;
            try {
                Resource resource = resourceLoader.getResource(path);
                is = resource.getInputStream();
                properties.load(is);
            } catch (IOException e) {
                log.info("Could not load properties from path: {}, message: {}", path, e.getMessage());
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
        return properties;
    }
}
