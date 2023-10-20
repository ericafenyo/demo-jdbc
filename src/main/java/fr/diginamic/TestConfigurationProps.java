package fr.diginamic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TestConfigurationProps {
  public static void main(String[] args) throws IOException {
    Path path = Paths.get("src/main/resources/maven.properties");

    if (Files.exists(path)) {
      List<String> result = Files.readAllLines(path);
      Map<String, String> properties = new HashMap<>();

      for (String element : result) {
        String[] chunks = element.split("=");
        String key = chunks[0];
        String value = chunks[1];
        properties.put(key.trim(), value.trim());
      }

      System.out.println(properties);
    }
  }
}
