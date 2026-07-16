package core;

import java.util.Map;
import java.util.Properties;

final class DatabaseConfig {
    private static final String ENV_PREFIX = "RENTACAR_DB_";
    private static final String PROPERTY_PREFIX = "rentacar.db.";

    private final String url;
    private final String user;
    private final String password;

    private DatabaseConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    static DatabaseConfig load() {
        return from(System.getenv(), System.getProperties());
    }

    static DatabaseConfig from(Map<String, String> environment, Properties properties) {
        return new DatabaseConfig(
                required(environment, properties, "URL", "url"),
                required(environment, properties, "USER", "user"),
                required(environment, properties, "PASSWORD", "password")
        );
    }

    private static String required(
            Map<String, String> environment,
            Properties properties,
            String environmentSuffix,
            String propertySuffix
    ) {
        String environmentName = ENV_PREFIX + environmentSuffix;
        String propertyName = PROPERTY_PREFIX + propertySuffix;
        String value = environment.get(environmentName);

        if (isBlank(value)) {
            value = properties.getProperty(propertyName);
        }

        if (isBlank(value)) {
            throw new IllegalStateException(
                    "Missing database configuration: set " + environmentName
                            + " or -D" + propertyName + "=<value>."
            );
        }

        return value;
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    String getUrl() {
        return url;
    }

    String getUser() {
        return user;
    }

    String getPassword() {
        return password;
    }
}
