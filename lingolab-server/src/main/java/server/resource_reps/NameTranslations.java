package server;

import java.util.ArrayList;
import java.util.List;
import com.google.cloud.translate.Language;

public class NameTranslations {
    private class NameTranslation {
        private final Language language;
        private final String translation;

        NameTranslation(Language language, String translation) {
            this.language = language;
            this.translation = translation;
        }

        public String getLanguage() {
            return language.getCode();
        }

        public String getTranslation() {
            return translation;
        }
    }

    private final List<Language> supportedLanguages;
    private final Language nativeLanguage;
    private final String originalName;
    private final List<NameTranslation> translations;

    public NameTranslations(String originalName, String nativeLanguage_code, List<Language> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;

        Language tempLanguage = NameTranslations.getLangFromCode(nativeLanguage_code, supportedLanguages);
        this.nativeLanguage = (tempLanguage != null) ? tempLanguage : this.getLangFromCode("en", supportedLanguages);

        this.originalName = originalName;
        this.translations = new ArrayList<NameTranslation>();
        this.createTranslations();
    }

    public String getNativeLanguage() {
        return nativeLanguage.getCode();
    }

    public String getOriginalName() {
        return originalName;
    }

    public List<NameTranslation> getTranslations() {
        return translations;
    }

    void createTranslations() {
        this.translations.add(new NameTranslation(nativeLanguage, originalName));
    }

    public static Language getLangFromCode(String lang_code, List<Language> supportedLanguages) {
        return supportedLanguages.stream()
            .filter(language -> lang_code.equals(language.getCode()))
            .findAny()
            .orElse(null);
    }
}