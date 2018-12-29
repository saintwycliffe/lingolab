package server;

import java.util.ArrayList;
import java.util.List;

public class NameTranslations {

    class NameTranslation {
        private final String language;
        private final String translation;

        NameTranslation(String translation, String language) {
            this.translation = translation;
            this.language = language;
        }

        public String getLanguage() {
            return language;
        }

        public String getTranslation() {
            return translation;
        }
    }

    private final String original_name;
    private final String native_language; // in production, should switch to enum
    private final List<NameTranslation> translations;

    public NameTranslations(String original_name, String native_language) {
        this.original_name = original_name;
        this.native_language = native_language;
        this.translations = new ArrayList<NameTranslation>();
        this.createTranslations();
    }

    public String getOriginalName() {
        return original_name;
    }

    public String getNativeLanguage() {
        return native_language;
    }

    public List<NameTranslation> getTranslations() {
        return translations;
    }

    void createTranslations() {
        this.translations.add(new NameTranslation(original_name, native_language));
    }
}