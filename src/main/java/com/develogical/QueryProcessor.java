package com.develogical;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryProcessor {

    public String process(String query) {

        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        return "";
    }

    private String extractTwoNumbersAnd(String query, BinaryOperator<Integer> func) {
        Pattern twoNumbers = Pattern.compile("What is (\\d+) (plus|multiplied by) (\\d+)?");
        Matcher matcher = twoNumbers.matcher(query);
        if (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(3));
            return String.valueOf(func.apply(a, b));
        }
        return null;
    }
}
