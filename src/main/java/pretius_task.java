import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class pretius_task {
    public static void main(String[] args) {
        System.out.println("Program started");
        try {
            DirectoryBuilder.createDirectories();

        } catch (IOException exception) {
            System.out.println("Cannot create directories");
        }

        System.out.println("Enter *stop* to stop program");
        FileManager fm = new FileManager();
        while (true) {
            Scanner scanner = new Scanner(System.in);


            try (Stream<Path> filePathStream=Files.walk(Paths.get("HOME"))) {
                filePathStream.forEach(filePath -> {
                    if (Files.isRegularFile(filePath) && !filePath.getFileName().toString().equals("count.txt")) {
                        try {
                            fm.moveFileToProperDirectory(filePath.getFileName().toString());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}