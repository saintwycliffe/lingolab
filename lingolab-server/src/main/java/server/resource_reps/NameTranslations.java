package server;

import java.util.ArrayList;
import java.util.List;
import com.google.cloud.translate.*;

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

    private final Translate translate;
    private final List<Language> supportedLanguages;
    private final Language nativeLanguage;
    private final String originalName;
    private final List<NameTranslation> translations;

    public NameTranslations(String originalName, String nativeLanguage_code, Translate translate) {
        this.translate = translate;
        this.supportedLanguages = translate.listSupportedLanguages();

        Language tempLanguage = NameTranslations.getLangFromCode(nativeLanguage_code, this.supportedLanguages);
        this.nativeLanguage = (tempLanguage != null) ? tempLanguage : this.getLangFromCode("en", this.supportedLanguages);

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

        for (int i = 0; i < 12; i++) {
            Language targetLanguage = this.supportedLanguages.get(i);
            Translation translation = translate.translate(originalName,
                Translate.TranslateOption.sourceLanguage(nativeLanguage.getCode()),
                Translate.TranslateOption.targetLanguage(targetLanguage.getCode()));

            this.translations.add(new NameTranslation(targetLanguage, translation.getTranslatedText()));
        }
    }

    public static Language getLangFromCode(String lang_code, List<Language> supportedLanguages) {
        return supportedLanguages.stream()
            .filter(language -> lang_code.equals(language.getCode()))
            .findAny()
            .orElse(null);
    }

    public static Language getLangFromCode(String lang_code, List<Language> supportedLanguages) {
        return supportedLanguages.stream()
            .filter(language -> lang_code.equals(language.getCode()))
            .findAny()
            .orElse(null);
    }
}