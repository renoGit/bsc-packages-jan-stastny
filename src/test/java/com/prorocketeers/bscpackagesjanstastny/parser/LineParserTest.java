package com.prorocketeers.bscpackagesjanstastny.parser;

import com.prorocketeers.bscpackagesjanstastny.domain.PackagePost;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineParserTest {

    LineParser lineParser;

    @BeforeEach
    void init() {
        lineParser = new LineParser();
    }

    @Test
    void parsePostCodeLineValid() {
        val expected = PackagePost.builder()
                .weight(1.1D)
                .postCode("12345")
                .build();
        assertEquals(expected, lineParser.parsePostCodeLine("1.1 12345"));
    }

    @Test
    void parsePostCodeLineValidMaximalDecimalPlaces() {
        val expected = PackagePost.builder()
                .weight(1.123D)
                .postCode("12345")
                .build();
        assertEquals(expected, lineParser.parsePostCodeLine("1.123 12345"));
    }

    @Test
    void parsePostCodeLineNotValidMaximalDecimalPlaces() {
        assertNull(lineParser.parsePostCodeLine("1.1234 12345"));
    }

    @Test
    void parsePostCodeLineValidDecimalPoint() {
        val expected = PackagePost.builder()
                .weight(1.1D)
                .postCode("12345")
                .build();
        assertEquals(expected, lineParser.parsePostCodeLine("1.1 12345"));
    }

    @Test
    void parsePostCodeLineNotValidDecimalPoint() {
        assertNull(lineParser.parsePostCodeLine("1,1 12345"));
    }

    @Test
    void parsePostCodeLineValidWithoutDecimalPoint() {
        val expected = PackagePost.builder()
                .weight(1D)
                .postCode("12345")
                .build();
        assertEquals(expected, lineParser.parsePostCodeLine("1 12345"));
    }

    @Test
    void parsePostCodeLinenNotValidNegative() {
        assertNull(lineParser.parsePostCodeLine("-1 12345"));
    }

    @Test
    void parsePostCodeLineNotValidPostCodeRequiredOnlyDigits() {
        assertNull(lineParser.parsePostCodeLine("1.1 ABCDE"));
    }

    @Test
    void parsePostCodeLineValidPostCodeRequiredDigits() {
        val expected = PackagePost.builder()
                .weight(1.1D)
                .postCode("12345")
                .build();
        assertEquals(expected, lineParser.parsePostCodeLine("1.1 12345"));
    }

    @Test
    void parsePostCodeLineNotValidPostCodeRequiredDigitsLess() {
        assertNull(lineParser.parsePostCodeLine("1.1 1234"));
    }

    @Test
    void parsePostCodeLineNotValidPostCodeRequiredDigitsMore() {
        assertNull(lineParser.parsePostCodeLine("1.1 123456"));
    }


}