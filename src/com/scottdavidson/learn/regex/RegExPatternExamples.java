package com.scottdavidson.learn.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExPatternExamples {

    public static void main(String[] args) {

        RegExPatternExamples regExPatternExamples = new RegExPatternExamples();
        regExPatternExamples.matchNumericAlphanumeric();
        regExPatternExamples.matchEmailAddress("My email address is: scott@scott-davidson.com");
        regExPatternExamples.matchEmailAddress("My email address is: scott@scottdavidson.com");
        regExPatternExamples.matchEmailAddress("My email address is: scott.davidson@scottdavidson.com");
        regExPatternExamples.matchSixHexDigit("BGCOLOR=\"#336633\"");
        regExPatternExamples.matchSimplifiedHypenatedDate("10-10-2017");
        regExPatternExamples.matchSimplifiedHypenatedDate("1-1-2017");
        regExPatternExamples.matchSimplifiedHypenatedDate("1-31-2017");
        regExPatternExamples.matchSimplifiedHypenatedDate("12-6-2017");
        regExPatternExamples.lazyVersusGreedy();
        regExPatternExamples.multilineMode();
        regExPatternExamples.groupingSubexpressions();
    }

    /**
     * Short cut to match numeric or alphabetic
     */
    public void matchNumericAlphanumeric() {

        String stringToMatch = "940877 A1B2C3 678455 Z1Z2Z3";
        String regex = "\\w\\d\\w\\d\\w\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToMatch);
        System.out.println("Text: " + stringToMatch + "\n\t --> find (" + regex + ") = " + matcher.reset().find()
                + " , start = " + matcher.start() + " , end = " + matcher.end());

    }

    public void matchEmailAddress(String stringToMatch) {

        // String regex = "\\w+@[\\w-]+\\.\\w+";
        String regex = "\\w+[\\w.-]*@\\w+[\\w-]*.\\w";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToMatch);
        boolean matchFound = matcher.reset().find();
        if (matchFound) {
            System.out.println("Text: " + stringToMatch + "\n\t --> find (" + regex + ") = " + matcher.reset().find()
                    + " , start = " + matcher.start() + " , end = " + matcher.end());
        } else {
            System.out.println("Text: " + stringToMatch + "\n\t --> find (" + regex + ") = NO MATCH");
        }

    }

    public void matchSixHexDigit(String stringToMatch) {

        String regex = "#[0-9A-Fa-f]{6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToMatch);
        boolean matchFound = matcher.reset().find();
        if (matchFound) {
            System.out.println("Text: " + stringToMatch + "\n\t --> find (" + regex + ") = " + matcher.reset().find()
                    + " , start = " + matcher.start() + " , end = " + matcher.end());
        } else {
            System.out.println("Text: " + stringToMatch + "\n\t --> find (" + regex + ") = NO MATCH");
        }

    }

    /**
     * Matches the "form" of a valid date but doesn't restrict month to 1 - 12
     * range, day to 1 - 31 range nor the year to a reasonable value
     *
     * @param stringContainingDate
     */
    public void matchSimplifiedHypenatedDate(String stringContainingDate) {

        String regex = "\\d{1,2}-\\d{1,2}-\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringContainingDate);
        boolean matchFound = matcher.reset().find();
        if (matchFound) {
            System.out.println("Text: " + stringContainingDate + "\n\t --> find (" + regex + ") = "
                    + matcher.reset().find() + " , start = " + matcher.start() + " , end = " + matcher.end());
        } else {
            System.out.println("Text: " + stringContainingDate + "\n\t --> find (" + regex + ") = NO MATCH");
        }

    }

    public void multilineMode() {

        String javascriptCodeSnippet = "<SCRIPT>\n" + "function doSpellCheck(form, field) {\n"
                + "   // Make sure not empty\n" + "   if ( field.value == ''){\n" + "      return false;\n" + "   }\n"
                + "   // Init \n" + "   var x = 1;\n" + "   var y = 5;\n" + "   // Another comment\n" + " ...  \n"
                + "}\n" + "</SCRIPT>\n";

        String javascriptCommentRegex = "(?m)^\\s*//.*$";
        Pattern javascriptPattern = Pattern.compile(javascriptCommentRegex);
        Matcher matcher = javascriptPattern.matcher(javascriptCodeSnippet);

        // Loop through matches
        System.out.println("\n\n -- MULTI-LINE -- ");
        while (matcher.find()) {
            System.out.println("Found : " + javascriptCodeSnippet.substring(matcher.start(), matcher.end()));
        }

    }

    public void lazyVersusGreedy() {

        String stringToMatch = "<B>AK</B> and <B>HI</B>";

        String greedyDefaultRegex = "<[Bb]>.*</[Bb]>";
        Pattern greedyPattern = Pattern.compile(greedyDefaultRegex);
        Matcher matcher = greedyPattern.matcher(stringToMatch);

        // Loop through matches
        System.out.println(" -- GREEDY -- ");
        while (matcher.find()) {
            System.out.println("Found : " + stringToMatch.substring(matcher.start(), matcher.end()));
        }

    }

    public void groupingSubexpressions() {

        // Initial IP Address
        {
            // Initial IP Address which allows *invalid* values ( the value has
            // to be <= 255 )
            String ipAddressTextSnippet = "Pinging hog.forta.com [12.159.46.200]\nwith 32 bytes of data,"
                    + " or possibly just use failover IP 33.2.544.6." + " Here's an invalid IP Address : 1.2.3.256";

            String ipAddressCommentRegex = "(\\d{1,3}\\.){3}\\d{1,3}";
            Pattern ipAddressPattern = Pattern.compile(ipAddressCommentRegex);
            Matcher matcher = ipAddressPattern.matcher(ipAddressTextSnippet);

            // Loop through matches
            System.out.println("\n\n -- Grouping Subexpressions (Non-Value Check IP Address) -- ");
            while (matcher.find()) {
                System.out.println("Found : " + ipAddressTextSnippet.substring(matcher.start(), matcher.end()));
            }
        }

        // Year
        {
            String yearTextSnippet = "DOB: 1967-08-17 ( Death: 2011-10-03 )";

            String yearCommentRegex = "(19|20)\\d{2}";
            Pattern yearPattern = Pattern.compile(yearCommentRegex);
            Matcher matcher = yearPattern.matcher(yearTextSnippet);

            // Loop through matches
            System.out.println("\n\n -- Grouping Subexpressions (year) -- ");
            while (matcher.find()) {
                System.out.println("Found : " + yearTextSnippet.substring(matcher.start(), matcher.end()));
            }
        }

        // Comprehensive IP Address
        {
            // In addition to the original, also ensure values are <= 255
            String ipAddressTextSnippet = "Pinging hog.forta.com [12.159.46.200]\nwith 32 bytes of data,"
                    + " or possibly just use failover IP 33.2.544.6." + " Here's an invalid IP Address : 1.2.3.256";

            // Series of checks based on the algorithm:
            //
            // Either: ( 1 or 2 digit number ) or ( 3 digit starting with a "1" ) or
            //         ( 3 digit starting with 2 followed by 0 - 4 ) or
            //         ( 3 digit starting with 25 followed by 0 - 5 )
            // , followed by a ".", repeated 3 times followed by the final value
            String ipAddressCommentRegex =
                    "(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))";

            Pattern ipAddressPattern = Pattern.compile(ipAddressCommentRegex);
            Matcher matcher = ipAddressPattern.matcher(ipAddressTextSnippet);

            // Loop through matches
            System.out.println("\n\n -- Grouping Subexpressions (Value Check IP Address) -- ");
            while (matcher.find()) {
                System.out.println("Found : " + ipAddressTextSnippet.substring(matcher.start(), matcher.end()));
            }
        }

    }


}
