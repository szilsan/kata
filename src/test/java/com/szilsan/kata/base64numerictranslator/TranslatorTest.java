package com.szilsan.kata.base64numerictranslator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TranslatorTest {

    @Test
    public void translate() {
        Assertions.assertEquals(Translator.translate("A"), 0);
        Assertions.assertEquals(Translator.translate("BA"), 64);
        Assertions.assertEquals(Translator.translate("BB"), 65);
        Assertions.assertEquals(Translator.translate("WIN"), 90637);
    }

}
