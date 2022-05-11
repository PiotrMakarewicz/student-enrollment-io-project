package pl.edu.agh.niebieskiekotki.utility;

import java.util.Objects;

public enum Language {
    ENGLISH,
    POLISH;

    public static Language fromString(String lang){
        if (Objects.equals(lang, "polish")){
            return Language.POLISH;
        } else if (Objects.equals(lang, "english")){
            return Language.ENGLISH;
        } else {
            return Language.ENGLISH;
        }
    }
}
