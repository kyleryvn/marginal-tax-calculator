package com.github.kyleryvn.marginaltax.utility;

import java.util.Map;
import java.util.Set;

public class EnvironmentUtility {
    private static final Map<String, String> env = System.getenv();

    public EnvironmentUtility() {
    }

    public static Set<String> keys() {
        return env.keySet();
    }

    public static Map<String, String> getEnvironmentVariable() {
        return env;
    }

    public static String get(String key) {
        return (String) env.getOrDefault(key, "/notFound");
    }

    public static void main(String[] args) {
        System.out.println(get("CST_4713_DATA"));
    }
}
