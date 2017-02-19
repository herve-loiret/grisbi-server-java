package grisbiweb.server.utils;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    private static final String CONFIGFILE_LOCATION = "grisbiweb.server.configfile.location";

    /**
     * This method return the grisbi data file url
     * 
     * @return
     */
    public static File findGrisbiAccountFile() {

        Path path = null;

        // case 1 : the file is in the home directory and his name is
        // account.xhb...
        path = Paths.get(System.getProperty("user.home"), "account.gsb");
        if (Files.exists(path)) {
            return new File(path.toUri());
        }

        // case 2 : otherwise, read the environment variable :
        String configUrlStr = System.getenv(CONFIGFILE_LOCATION);
        if (configUrlStr != null) {
            path = Paths.get(configUrlStr);
            if (Files.exists(path)) {
                return new File(path.toUri());
            } else {
                throw new RuntimeException(
                        "The grisbi data file defined in " + CONFIGFILE_LOCATION + " is not valid : " + configUrlStr);
            }
        }

        // case 3
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("Example_1.0.gsb");
        File file = new File(url.getFile());
        return file;
    }

    /**
     * For testing purpose
     * 
     * @param args
     */
    public static void main(String... args) {
        findGrisbiAccountFile();
    }
}
