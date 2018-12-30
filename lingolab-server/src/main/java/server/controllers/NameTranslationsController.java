package server;

import com.google.cloud.translate.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.io.*;


@RestController
public class NameTranslationsController {
  private final String API_KEY;
  private final Translate translate;
  private final List<Language> supportedLanguages;

  NameTranslationsController() {
    String tempApiKey = "";
    Translate tempTranslate = null;
    List<Language> tempSupportedLanguages = new ArrayList<Language>();
    try {
      tempApiKey = this.getApiKey();
      tempTranslate = TranslateOptions.newBuilder().setApiKey(tempApiKey).build().getService();
      tempSupportedLanguages = tempTranslate.listSupportedLanguages();
    }
    catch (IOException e) {
      System.out.println(e);
    }
    finally {
      this.API_KEY = tempApiKey;
      this.translate = (tempTranslate != null) ? TranslateOptions.newBuilder().build().getService() : tempTranslate;
      this.supportedLanguages = tempSupportedLanguages;
    }
  }

  private String getApiKey() throws IOException, FileNotFoundException{
    Properties prop = new Properties();
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

    if (inputStream != null) {
      prop.load(inputStream);
      inputStream.close();
    }
    else {
      throw new FileNotFoundException("config.properties");
    }

    return prop.getProperty("apikey");
  }

  @RequestMapping("/translate")
  public NameTranslations translate(@RequestParam(value="name") String name, @RequestParam(value="lang") String lang_code) {
    return new NameTranslations(name, lang_code, this.supportedLanguages);
  }
}