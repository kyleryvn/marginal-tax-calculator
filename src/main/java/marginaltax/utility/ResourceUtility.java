package marginaltax.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The getResource method is not working in the JAR file
 * If we try to access a file that is inside a JAR,
 * It throws NoSuchFileException (linux), InvalidPathException (Windows)
 * Still works from within the IDE however
 *
 * Resource URL Sample: file:java-io.jar!/json/file1.json
 */

@Deprecated
public class ResourceUtility {

    public static List<String> get(String filename) {
        return get(filename, 0);
    }

    public static List<String> get(String filename, int skip) {
        return get(filename, skip, e -> e, p -> true);
    }

    public static <T> List<T> get(String filename, int skip, Function<String, T> conversion) {
        return get(filename, skip, conversion, p -> true);
    }

    public static <T> List<T> get(String filename, int skip
            , Function<String, T> conversion, Predicate<T> predicate) {

        // ResourceUtility classloader, try-catch with resources
        try {
            ClassLoader classLoader = ResourceUtility.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
            System.out.println("\nFile \"" + filename + "\" found : " + file.exists());

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
