package com.github.kyleryvn.marginaltax.utility;

import java.util.Map;
import java.util.Set;

/***
 * Used to return the file directory pointed to by a specific system environment variable
 */

public class EnvironmentUtility {
    private static final Map<String, String> environment = System.getenv();

    public static Set<String> keys() {
        return environment.keySet();
    }

    public static Map<String, String> getVariable() {
        return environment;
    }

    public static String get(String key) {
        return (String) environment.getOrDefault(key, "/notFound");
    }

    public static void main(String[] args) {
        System.out.println(environment);
    }
}
