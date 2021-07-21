package com.nulabinc.zxcvbn.matchers;

import com.nulabinc.zxcvbn.WipeableString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryMatcher extends BaseMatcher {

    private final Map<String, Map<String, Integer>> rankedDictionaries;

    public DictionaryMatcher(Map<String, Map<String, Integer>> rankedDictionaries) {
        if (rankedDictionaries == null) rankedDictionaries = new HashMap<String, Map<String, Integer>>();
        this.rankedDictionaries = rankedDictionaries;
    }

    @Override
    public List<Match> execute(CharSequence password) {
        List<Match> matches = new ArrayList<Match>();
        int len = password.length();
        WipeableString passwordLower = WipeableString.lowerCase(password);
        for (Map.Entry<String, Map<String, Integer>> rankedDictionaryRef: this.rankedDictionaries.entrySet()) {
            String dictionaryName = rankedDictionaryRef.getKey();
            Map<String, Integer> rankedDict = rankedDictionaryRef.getValue();
            for(int i = 0; i < len; i++) {
                for(int j = i; j < len; j++) {
                    CharSequence word = passwordLower.subSequence(i, j + 1);
                    if (rankedDict.containsKey(word)) {
                        int rank = rankedDict.get(word);
                        WipeableString token = WipeableString.copy(password,i, j + 1);
                        matches.add(MatchFactory.createDictionaryMatch(i, j, token, word, rank, dictionaryName));
                    }
                }
            }
        }
        passwordLower.wipe();
        return this.sorted(matches);
    }
}
