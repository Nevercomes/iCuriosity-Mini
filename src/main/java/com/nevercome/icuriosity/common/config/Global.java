package com.nevercome.icuriosity.common.config;

import com.nevercome.icuriosity.utils.PropertiesLoader;
import com.nevercome.icuriosity.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类 懒汉式单例类.在第一次调用的时候实例化自己
 *
 * @author: sun
 * @date: 2019/6/18
 */
public class Global {

    private Global() {
    }

    private static Global global = null;

    public static synchronized Global getInstance() {

        if (global == null) {
            synchronized (Global.class) {
                if (global == null) {
                    global = new Global();
                }
            }
        }
        return global;
    }

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<>();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("icuriosity.properties");

    /**
     * 获取配置信息
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

}
