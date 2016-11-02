package com.luxoft.assignment.process;

import com.luxoft.assignment.model.Word;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VowelCounter {

    private static final String SPACE = " ";
    private final String filePath;
    private final String outputFilePath;

    public VowelCounter(String filePath, String outputFilePath) {
        this.filePath = filePath;
        this.outputFilePath = outputFilePath;
    }

    public void processFile() {
        Map<Word, List<Integer>> vowelMap = new HashMap<>();
        String content = getStringFromFile();

        if (content != null) {
            String[] words = content.replace(".", "").split(SPACE);

            fillVowelMap(vowelMap, words);
            writeStringToOutputFile(vowelMap);
        }
    }

    private void writeStringToOutputFile(Map<Word, List<Integer>> vowelMap) {
        try {
            File file = new File(outputFilePath);
            Files.deleteIfExists(file.toPath());
            for (Map.Entry<Word, List<Integer>> entry : vowelMap.entrySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append(entry.getKey().toString());
                sb.append(" -> ");
                sb.append(getAverage(entry));
                sb.append(System.lineSeparator());
                Files.write(file.toPath(), sb.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    private double getAverage(Map.Entry<Word, List<Integer>> entry) {
        double totalVowel = 0;
        for (int vowelCount : entry.getValue()) {
            totalVowel += vowelCount;
        }
        return totalVowel / entry.getValue().size();
    }

    private void fillVowelMap(Map<Word, List<Integer>> vowelMap, String[] words) {
        for (String word : words) {
            Word vowelWord = getWord(word);
            List<Integer> vowelCount;

            if (vowelMap.containsKey(vowelWord)) {
                vowelCount = vowelMap.get(vowelWord);
                vowelCount.add(vowelWord.getVowelCount());
            } else {
                vowelCount = new ArrayList<>();
                vowelCount.add(vowelWord.getVowelCount());
            }

            vowelMap.put(vowelWord, vowelCount);
        }
    }

    private Word getWord(String word) {
        List<String> vowels = getVowels(word);
        Set<String> vowelSet = new HashSet<>(vowels);
        return new Word(word.length(), vowels.size(), vowelSet);
    }

    private List<String> getVowels(String word) {
        List<String> vowels = new ArrayList<>();
        String lowercaseWord = word.toLowerCase();

        for (int i=0; i<lowercaseWord.length(); i++) {
            char c = lowercaseWord.charAt(i);

            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels.add(String.valueOf(c));
            }
        }

        return vowels;
    }

    private String getStringFromFile() {
        String content = null;

        try {
            File file = new File(filePath);
            content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.print(e);
        }

        return content;
    }
}
