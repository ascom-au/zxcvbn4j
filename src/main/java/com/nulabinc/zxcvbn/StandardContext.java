package com.nulabinc.zxcvbn;

import com.nulabinc.zxcvbn.matchers.Dictionary;
import com.nulabinc.zxcvbn.matchers.Keyboard;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

class StandardContext {

    static Context build() throws IOException {
        Map<String, Dictionary> dictionaryMap = new LinkedHashMap<String, Dictionary>();
        for (Dictionary dictionary : StandardDictionaries.loadAllDictionaries()) {
            dictionaryMap.put(dictionary.getName(), dictionary);
        }

        Map<String, Keyboard> keyboardMap = new LinkedHashMap<String, Keyboard>();
        for (Keyboard keyboard : StandardKeyboards.loadAllKeyboards()) {
            keyboardMap.put(keyboard.getName(), keyboard);
        }

        return new Context(dictionaryMap, keyboardMap);
    }

}
