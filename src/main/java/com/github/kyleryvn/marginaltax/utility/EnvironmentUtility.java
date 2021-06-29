package com.github.kyleryvn.marginaltax.utility;

import java.util.Map;
import java.util.Set;

/***
 * Used to return the file directory pointed to by a specific system environment variable
 */

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
}
