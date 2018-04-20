package tk.carlyle2k;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Properties;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.util.stream.Collectors.toSet;

public class ConfigAlm {
    /**
     * file contains program path which add to almrun
     */
    private static final String PROGRAM_PATH = "program_path";

    /**
     * alm ini backup file path
     */
    private static final String ALM_BAK_FILE = "alm_bak_file";

    /**
     * alm ini backup file path
     */
    private static final String ALM_INI_FILE = "alm_ini_file";

    /**
     * config file
     */
    private static final String CONFIG_INI = "config.ini";

    /**
     * ini file properties
     */
    private static final Properties PROPERTIES = new Properties();

    public static final ConfigAlm me = new ConfigAlm();

    private ConfigAlm() {}

    /**
     * singleton
     */
    public static ConfigAlm getMe() {
        //init
        try (InputStream iniFileStream = ALM.class.getClassLoader().getResource(CONFIG_INI).openStream()) {
            PROPERTIES.load(iniFileStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return me;
    }

    /**
     * read program paths
     */
    public Collection<String> getProgramPaths() throws IOException {
        return Files.lines(Paths.get(PROPERTIES.getProperty(PROGRAM_PATH)))
                .filter(line -> line.length() > 0)
                .collect(toSet());
    }

    /**
     * save result
     */
    public void writeToAlmConfigFile(Collection<String> lines) throws IOException {
        Path backup = Paths.get(PROPERTIES.getProperty(ALM_BAK_FILE));
        Path formal = Paths.get(PROPERTIES.getProperty(ALM_INI_FILE));

        //restore ini file from back up
        Files.copy(backup, formal, REPLACE_EXISTING);

        //append commands to ini file
        Files.write(formal, lines, APPEND);
    }
}
