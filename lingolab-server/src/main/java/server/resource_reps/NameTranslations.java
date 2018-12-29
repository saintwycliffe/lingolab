package server;

import java.util.ArrayList;
import java.util.List;

public class NameTranslations {

    class NameTranslation {
        private final String language;
        private final String translation;

        NameTranslation(String language, String translation) {
            this.language = language;
            this.translation = translation;
        }

        public String getLanguage() {
            return language;
        }

        public String getTranslation() {
            return translation;
        }
    }

    private final String nativeLanguage; // in production, should switch to enum
    private final String originalName;
    private final List<NameTranslation> translations;

    public NameTranslations(String originalName, String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
        this.originalName = originalName;
        this.translations = new ArrayList<NameTranslation>();
        this.createTranslations();
    }

    public String getNativeLanguage() {
        return nativeLanguage;
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