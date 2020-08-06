package com.prorocketeers.bscpackagesjanstastny.parser;

import com.prorocketeers.bscpackagesjanstastny.domain.Fee;
import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse user input line or line from imported files
 */
@Slf4j
@Component
public class LineParser implements ILineParser {

    /**
     * Create object if line is valid
     * @param line user input line or line from imported files
     * @return PackagePost object for saving
     */
    public PackagePost parsePostCodeLine(String line) {
        final String[] lineSplit = line.split(" ");

        try {
            Double weight = null;
            String postCode = null;

            if (isSequenceInputValid(lineSplit[0], "^[0-9]+([.][0-9]{1,3})")
                    || isSequenceInputValid(lineSplit[0], "^\\d{1,10}$")) {
                weight = Double.parseDouble(lineSplit[0]);
            }

            if (isSequenceInputValid(lineSplit[1], "^\\d{5}$")) {
                postCode = lineSplit[1];
            }

            if (weight != null && postCode != null) {
                return PackagePost.builder()
                        .weight(weight)
                        .postCode(postCode)
                        .build();
            }

        } catch (Exception e) {
            log.debug("Parsing exception: " + e.getClass() + ": " + e.getMessage());
        }

        System.out.println("User input line is not valid.");
        return null;
    }

    /**
     * Create object if line is valid
     * @param line user input line or line from imported files
     * @return Fee object for saving
     */
    public Fee parseFeeLine(String line) {
        final String[] lineSplit = line.split(" ");

        try {
            Double weight = null;
            BigDecimal fee = null;

            if (isSequenceInputValid(lineSplit[0], "^[0-9]+([.][0-9]{1,3})")
                    || isSequenceInputValid(lineSplit[0], "^\\d{1,10}$")) {
                weight = Double.parseDouble(lineSplit[0]);
            }

            if (isSequenceInputValid(lineSplit[1], "^[0-9]+([.][0-9]{2})")) {
//                fee = Double.parseDouble(lineSplit[1]);
                fee = new BigDecimal(lineSplit[1]);
            }

            if (weight != null && fee != null) {
                return new Fee(weight, fee);
            }
        } catch (Exception e) {
            log.debug("Parsing exception: " + e.getClass() + ": " + e.getMessage());
        }
        System.out.println("Line is not valid.");
        return null;
    }

    /**
     * Evaluate the tested string by the regular expression
     * @param sequenceInput tested string
     * @param regexp regular expression
     * @return
     */
    private static boolean isSequenceInputValid(String sequenceInput, String regexp) {
        final Pattern pattern = Pattern.compile(regexp);
        Matcher m = pattern.matcher(sequenceInput);
        return m.matches();
    }

}
