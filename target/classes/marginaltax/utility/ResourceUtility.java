package marginaltax.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceUtility {

    public static List<String> get(String fileName) {
        return get(fileName, 0);
    }

    public static List<String> get(String fileName, int skip) {
        return get(fileName, skip, e -> e, p -> true);
    }

    public static <T> List<T> get(String fileName, int skip, Function<String, T> conversion) {
        return get(fileName, skip, conversion, p -> true);
    }

    public static <T> List<T> get(String fileName, int skip
            , Function<String, T> conversion, Predicate<T> predicate) {

        // ResourceUtility classloader, try-catch with resources
        try {
            ClassLoader classLoader = ResourceUtility.class.getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            System.out.println("\nFile \"" + fileName + "\" found : " + file.exists());

            Path path = file.toPath();

            // Read all lines from the file as a stream
            // Skip nth amount of lines, convert from JSON, include all lines, add data to List
            try (Stream<String> stream = Files.lines(path)) {
                return stream.skip(skip)
                        .map(conversion)
                        .filter(predicate)
                        .collect(Collectors.toList());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (NullPointerException ex) {
            System.out.println("ERROR: File not found");
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }
}
