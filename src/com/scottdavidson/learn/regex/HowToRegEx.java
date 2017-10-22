package com.scottdavidson.learn.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HowToRegEx {

    public static void main(String[] args) {

        HowToRegEx howToRegEx = new HowToRegEx();
        howToRegEx.howToUsePatternMatcher();
        howToRegEx.howToUseLookingAt();
        howToRegEx.howToUseGroup();
        howToRegEx.howToUseReplaceFirstReplaceAll();
    }

    /**
     * Demonstrates the basics of using the Pattern and Matcher classes. <br>
     * The dot ( "." ) character matches any character
     */
    public void howToUsePatternMatcher() {

        printMethodHeader("howToUsePatternMatcher");

        // First, create a Pattern ( regex ) string
        String regex1 = ".*http://.*";
        Pattern pattern1 = Pattern.compile(regex1);
        String regex2 = ".a[0-9].xml";
        Pattern pattern2 = Pattern.compile(regex2);

        // Next Create the Matcher (via the compiled Pattern) with the text to
        // be matched
        String text1 = "This is a string to be searched for occurrence of HTTP URL: http://www.google.com";
        Matcher matcher1 = pattern1.matcher(text1);
        String text2 = "Names of files: na1.xml , na2.xml, ca1.xml sam.xml";
        Matcher matcher2 = pattern2.matcher(text2);

        // Use the Matcher to check:
        //
        // (1) if the regex matches the text ( a complete match )
        // (2) find the first (and subsequent) matches of the regex on the text
        System.out.println("Text: " + text1 + "\n\t --> matches (" + regex1 + ") = " + matcher1.matches());
        System.out.println("Text: " + text2 + "\n\t --> matches (" + regex2 + ") = " + matcher2.matches());
        System.out.println("Text: " + text2 + "\n\t --> find (" + regex2 + ") = " + matcher2.reset().find()
                + " , start = " + matcher2.start() + " , end = " + matcher2.end());
        System.out.println("Text: " + text2 + "\n\t --> find (2) (" + regex2 + ") = " + matcher2.find() + " , start = "
                + matcher2.start() + " , end = " + matcher2.end());
        System.out.println("Text: " + text2 + "\n\t --> find (3) (" + regex2 + ") = " + matcher2.find() + " , start = "
                + matcher2.start() + " , end = " + matcher2.end());

    }

    public void howToUseLookingAt() {
        printMethodHeader("howToUseLookingAt");

        String text = "This is the text to be searched " + "for occurrences of the http:// pattern.";

        String patternString = "This is the";

        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        System.out.println("lookingAt = " + matcher.lookingAt() + " ; this is true b/c the initial text matches.");
        System.out.println("matches   = " + matcher.matches()
                + " ; this is false b/c the entire text does *not* match.");

    }

    public void howToUseGroup() {
        printMethodHeader("howToUseGroup");

        String text = "John Jakes writes about this, and John Doe writes about that,"
                + " and John Wayne writes about everything.";

        String patternString1 = "((John) (.+?)) ";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println("found: <" + matcher.group(1) + "> <" + matcher.group(2) + "> <" + matcher.group(3)
                    + ">");
        }
    }

    public void howToUseReplaceFirstReplaceAll() {
        printMethodHeader("howToUseReplaceFirstReplaceAll");

        String text = "John Jakes writes about this, and John Doe writes about that,"
                + " and John Wayne writes about everything.";

        String patternString1 = "((John) (.+?)) ";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);

        String replaceAll = matcher.replaceAll("Jill Simpson");
        System.out.println("Replace All : "  + replaceAll);

        String replaceFirst = matcher.replaceFirst("Jill Simpson");
        System.out.println("Replace First : "  + replaceFirst);

    }

    protected void printMethodHeader(String methodName) {
        System.out.println("\n***************** (" + methodName + ") *****************");
    }

}
