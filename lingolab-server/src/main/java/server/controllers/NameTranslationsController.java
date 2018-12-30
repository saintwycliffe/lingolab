package server;

import com.google.cloud.translate.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.io.*;


@RestController
public class NameTranslationsController {
  private final Translate translate;

  NameTranslationsController() {
    String tempApiKey = "";
    Translate tempTranslate = null;
    try {
      tempApiKey = this.getApiKey();
      tempTranslate = TranslateOptions.newBuilder().setApiKey(tempApiKey).build().getService();
    }
    catch (IOException e) {
      System.out.println(e);
    }
    finally {
<<<<<<< HEAD
=======
      this.API_KEY = tempApiKey;
>>>>>>> Rate limiting to 12, to make it work
      this.translate = (tempTranslate != null) ? tempTranslate : TranslateOptions.newBuilder().build().getService();
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
    return new NameTranslations(name, lang_code, this.translate);
  }
}