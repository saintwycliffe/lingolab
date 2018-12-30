package server;

import java.util.*;
import com.google.cloud.translate.*;

public class NameTranslations {
    private class NameTranslation {
        private final Language language;
        private final String translation;

        NameTranslation(Language language, String translation) {
            this.language = language;
            this.translation = translation;
        }

        public String getLanguageCode() {
            return language.getCode();
        }

        public String getLanguageName() {
            return language.getName();
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

    public int getSize() {
        return supportedLanguages.size();
    }

    void createTranslations() {
        // this.translations.add(new NameTranslation(nativeLanguage, originalName));

        Set<String> seenNames = new HashSet<String>();
        seenNames.add(originalName);

        for (Language targetLanguage : supportedLanguages) {
            if (targetLanguage.getCode().equals(nativeLanguage.getCode())) {continue;}
            Translation translation = translate.translate(originalName,
                Translate.TranslateOption.sourceLanguage(nativeLanguage.getCode()),
                Translate.TranslateOption.targetLanguage(targetLanguage.getCode()));

            String newName = translation.getTranslatedText();
            if (!seenNames.contains(newName)) {
                this.translations.add(new NameTranslation(targetLanguage, newName));
                seenNames.add(newName);
            }
        }
    }

    public static Language getLangFromCode(String lang_code, List<Language> supportedLanguages) {
        return supportedLanguages.stream()
            .filter(language -> lang_code.equals(language.getCode()))
            .findAny()
            .orElse(null);
    }
}