package service;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SpellerProperties {

    public static final String TEST_PROPS = "src/test/resources/test.properties";

    @SneakyThrows
    public static Properties getProperties() {
        Properties props = new Properties();
        InputStream input = new FileInputStream(TEST_PROPS);
        props.load(input);
        return props;
    }
}
