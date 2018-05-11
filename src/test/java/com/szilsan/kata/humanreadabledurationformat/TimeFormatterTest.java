package com.szilsan.kata.humanreadabledurationformat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeFormatterTest {

    @Test
    public void testFormatDurationExamples() {
        // Example Test Cases

        assertEquals("1 second", TimeFormatter.formatDuration(1));
        assertEquals("1 minute and 2 seconds", TimeFormatter.formatDuration(62));
        assertEquals("2 minutes", TimeFormatter.formatDuration(120));
        assertEquals("1 hour", TimeFormatter.formatDuration(3600));
        assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.formatDuration(3662));
        assertEquals("3 years, 4 days, 2 hours, 23 minutes and 4 seconds", TimeFormatter.formatDuration(31536000 * 3 + 4 * 86400 + 2 * 3600 + 23 * 60 + 4));

    }

}