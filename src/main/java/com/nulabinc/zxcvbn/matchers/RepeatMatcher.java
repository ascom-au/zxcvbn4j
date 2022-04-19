package com.nulabinc.zxcvbn.matchers;

import com.nulabinc.zxcvbn.Context;
import com.nulabinc.zxcvbn.Matching;
import com.nulabinc.zxcvbn.Scoring;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.WipeableString;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class RepeatMatcher extends BaseMatcher {

    public RepeatMatcher(final Context context) {
        super(context);
    }

    @Override
    public List<Match> execute(CharSequence password) {
        List<Match> matches = new ArrayList<Match>();
        Pattern greedy = Pattern.compile("(.+)\\1+");
        Pattern lazy = Pattern.compile("(.+?)\\1+");
        Pattern lazyAnchored = Pattern.compile("^(.+?)\\1+$");
        int passwordLength = password.length();
        int lastIndex = 0;
        Scoring scoring = new Scoring(this.getContext());
        while(lastIndex < passwordLength) {
            java.util.regex.Matcher greedyMatch = greedy.matcher(password);
            java.util.regex.Matcher lazyMatch = lazy.matcher(password);
            greedyMatch.region(lastIndex, passwordLength);
            lazyMatch.region(lastIndex, passwordLength);
            if (!greedyMatch.find()) break;
            java.util.regex.Matcher match;
            CharSequence baseToken;
            if(greedyMatch.group(0).length() > (lazyMatch.find() ? lazyMatch.group(0).length() : 0)) {
                match = greedyMatch;
                Matcher matcher = lazyAnchored.matcher(match.group(0));
                baseToken = matcher.find() ? matcher.group(1) : match.group(0);
            } else {
                match = lazyMatch;
                baseToken = match.group(1);
            }
            int i = match.start(0);
            int j = match.start(0) + match.group(0).length() - 1;
            Strength baseAnalysis = scoring.mostGuessableMatchSequence(baseToken, new Matching(this.getContext(), new ArrayList<String>()).omnimatch(baseToken));
            List<Match> baseMatches = baseAnalysis.getSequence();
            double baseGuesses = baseAnalysis.getGuesses();
            baseToken = new WipeableString(baseToken);
            matches.add(MatchFactory.createRepeatMatch(i, j, match.group(0), baseToken, baseGuesses, baseMatches, match.group(0).length() / baseToken.length()));
            lastIndex = j + 1;
        }
        return matches;
    }
}
