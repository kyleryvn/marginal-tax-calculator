package com.github.kyleryvn.marginaltax.utility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ResourceStreamUtility {
    private static final String FILE_ERROR = "ERROR: File cannot be found";

    public static List<String> getResource(String filename) {
        return getResource(filename, 0);
    }

    public static List<String> getResource(String filename, int lineSkip) {
        return getResource(filename, lineSkip, e -> e);
    }

    public static <T> List<T> getResource(String filename, int lineSkip, Function<String, T> conversion) {
        Predicate<? super String> predicate = p -> true;
        InputStream inputStream = getFileFromResourceAsStream(filename);

        try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(streamReader)) {

            return bufferedReader.lines()
                    .skip(lineSkip)
                    .filter(predicate)
                    .map(conversion)
                    .collect(Collectors.toList());

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    // Get a file from the resources folder
    // Works everywhere, IDEA, unit test and JAR file.
    private static InputStream getFileFromResourceAsStream(String filename) {

        // The class loader that loaded the class
        ClassLoader classLoader = ResourceStreamUtility.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);

        // The stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException(FILE_ERROR);
        }
        else {
            return inputStream;
        }
    }

}
