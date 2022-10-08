import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.io.FilenameUtils;

public class FileManager {
    public void moveFileToProperDirectory(String filename) throws IOException {
        String path = "HOME/" + filename;
        String extension = FilenameUtils.getExtension(path);
        Integer creationHour = getFileCreationHour(path);

        if (extension.equals("jar") && creationHour % 2 != 0) {
            Files.move(Paths.get(path), Paths.get("TEST/" + filename), StandardCopyOption.REPLACE_EXISTING);
            updateCountFile("TEST");
        } else if (extension.equals("jar") && creationHour % 2 == 0) {
            Files.move(Paths.get(path), Paths.get("DEV/" + filename), StandardCopyOption.REPLACE_EXISTING);
            updateCountFile("DEV");
        } else if (extension.equals("xml")) {
            Files.move(Paths.get(path), Paths.get("DEV/" + filename), StandardCopyOption.REPLACE_EXISTING);
            updateCountFile("DEV");
        }
    }

    private Integer getFileCreationHour(String path) {
        Path filepath = Paths.get(path);
        BasicFileAttributes attr;
        try {
            attr = Files.readAttributes(filepath, BasicFileAttributes.class);
            FileTime ft = attr.creationTime();
            DateFormat dateFormat = new SimpleDateFormat("HH");
            String creationHour = dateFormat.format(ft.toMillis());
            return Integer.valueOf(creationHour);
        } catch (IOException e) {
            System.out.println("Error message: " + e.getMessage());
        }
        return -1;
    }

    private void updateCountFile(String directory) {
        HashMap<String, Integer> dataCounterMap = readFile();
        dataCounterMap.put(directory, dataCounterMap.get(directory) + 1);
        dataCounterMap.put("TOTAL", dataCounterMap.get("TOTAL") + 1);
        saveFile(dataCounterMap, "HOME/count.txt");
    }

    private HashMap<String, Integer> readFile() {
        HashMap<String, Integer> dataCounter = new HashMap<>();
        try {
            File myObj = new File("HOME/count.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] keyValuePair = line.split(": ");
                dataCounter.put(keyValuePair[0], Integer.valueOf(keyValuePair[1]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
            e.printStackTrace();
        }
        return dataCounter;
    }

    private void saveFile(HashMap<String, Integer> map, String path) {
        File file = new File(path);

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
