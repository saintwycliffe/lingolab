package server;

import com.google.cloud.translate.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Properties;
import java.io.*;


@RestController
public class NameTranslationsController {
  private final String API_KEY;

  NameTranslationsController() {
    String tempApiKey = "";
    try {
      Properties prop = new Properties();
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

      if (inputStream != null) {
        prop.load(inputStream);
        inputStream.close();
      }
      else {
        throw new FileNotFoundException("config.properties");
      }
      tempApiKey = prop.getProperty("apikey");
    }
    catch (IOException ioe) {
      System.out.println(ioe);
      tempApiKey = "none";
    }
    finally {
      this.API_KEY = tempApiKey;
    }
  }

  @RequestMapping("/translate")
  public NameTranslations translate(@RequestParam(value="name") String name, @RequestParam(value="lang") String lang_code) {

    Translate translate = TranslateOptions.newBuilder().setApiKey(this.API_KEY).build().getService();

      Language langObject = translate.listSupportedLanguages().stream()
        .filter(language -> lang_code.equals(language.getCode()))
        .findAny()
        .orElse(null);
      return new NameTranslations(name, langObject);
  }
}