import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DirectoryBuilder {
    final static String[] directories = {"HOME", "DEV", "TEST"};
    static public void createDirectories() throws IOException {
        for (String dir : directories) {
            Path path = Paths.get(dir);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            if (Objects.equals(dir, "HOME")) {
                Path Countpath = Paths.get("HOME/count.txt");
                FileManager fm = new FileManager();
                if (!Files.exists(Countpath)) {
                    createCountFile();
                }
            }
        }
    }

    static private void createCountFile() {
        File file = new File("HOME/count.txt");

        HashMap<String, Integer> map = new HashMap<>();
        map.put("TOTAL", 0);
        map.put("DEV", 0);
        map.put("TEST", 0);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, Integer> entry :
                    map.entrySet()) {
                bf.write(entry.getKey() + ": "
                        + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
