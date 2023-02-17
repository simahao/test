package mock;

import java.io.File;

public class FileUtils {
    public static boolean isFile(String fileName) {
        return new File(fileName).isFile();
    }

}
