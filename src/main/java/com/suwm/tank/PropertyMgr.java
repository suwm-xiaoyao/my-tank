package com.suwm.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.ini"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        if (properties == null) {
            return null;
        }
        return properties.get(key);
    }


    public static void main(String[] args) {
        System.out.println(properties.get("initTankCount"));
    }
}
