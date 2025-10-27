package util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertiesUtil {
    private final Properties INSTANCE = new Properties();

    static {
        load();
    }

    private static void load() {
        try(InputStream stream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            INSTANCE.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(String key) {
        return INSTANCE.getProperty(key);
    }
}
