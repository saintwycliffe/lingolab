package server;

import java.util.ArrayList;
import java.util.List;
import com.google.cloud.translate.Language;

public class NameTranslations {

    class NameTranslation {
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

    private final Language nativeLanguage; // in production, should switch to enum
    private final String originalName;
    private final List<NameTranslation> translations;

    public NameTranslations(String originalName, Language nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
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
}