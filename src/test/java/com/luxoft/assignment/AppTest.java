package com.luxoft.assignment;

import com.luxoft.assignment.process.VowelCounter;
import org.junit.Test;

public class AppTest {

    @Test
    public void testVowelCount() {
        VowelCounter vowelCounter = new VowelCounter("src/test/resources/INPUT.TXT", "src/test/resources/OUTPUT.TXT");
        vowelCounter.processFile();
    }
}
