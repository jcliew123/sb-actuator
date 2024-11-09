package jc.spring.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    private static final String EXTERNAL_API_URL = "https://example.com/health";
    private static final long MIN_FREE_DISK_SPACE = 100 * 1024 * 1024L; // 100 MB

    @Override
    public Health health() {
        boolean apiStatus  = checkExternalApi();
        boolean diskSpaceStatus = checkDiskSpace();
        if (apiStatus && diskSpaceStatus) {
            return Health.up()
                    .withDetail("CustomHealth", "All systems are operational")
                    .build();
        } else {
            return Health.down()
                    .withDetail("External API", apiStatus? "OK": "DOWN")
                    .withDetail("Disk Space", diskSpaceStatus? "OK": "DOWN")
                    .build();
        }
    }

    // Check external API health
    private boolean checkExternalApi() {
        try {
            URL url = new URL(EXTERNAL_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(3));
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            return responseCode == 200;
        } catch (Exception e) {
            return false;
        }
    }

    // Check if there's sufficient free disk space
    private boolean checkDiskSpace() {
        try {
            FileStore fileStore = Files.getFileStore(Paths.get("/"));
            long freeSpace = fileStore.getUsableSpace();
            return freeSpace >= MIN_FREE_DISK_SPACE;
        } catch (Exception e) {
            return false;
        }
    }
}
