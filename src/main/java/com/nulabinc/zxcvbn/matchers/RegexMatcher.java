package com.nulabinc.zxcvbn.matchers;

import com.nulabinc.zxcvbn.Context;
import com.nulabinc.zxcvbn.WipeableString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;

public class RegexMatcher extends BaseMatcher {

    private static final Map<String, String> REGEXEN = new HashMap<String, String>();
    static {
        REGEXEN.put("recent_year", "19\\d\\d|200\\d|201\\d|202\\d");
    }

    public RegexMatcher(final Context context) {
        super(context);
    }

    @Override
    public List<Match> execute(CharSequence password) {
        List<Match> matches = new ArrayList<Match>();
        for(Map.Entry<String, String> regexenRef: REGEXEN.entrySet()) {
            String name = regexenRef.getKey();
            java.util.regex.Matcher rxMatch = Pattern.compile(regexenRef.getValue()).matcher(password);
            while(rxMatch.find()){
                CharSequence token = new WipeableString(rxMatch.group());
                matches.add(MatchFactory.createRegexMatch(
                        rxMatch.start(),
                        rxMatch.start() + token.length() - 1,
                        token,
                        name,
                        rxMatch));
            }
        }
        return this.sorted(matches);
    }
}
