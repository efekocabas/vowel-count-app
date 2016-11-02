package com.luxoft.assignment.model;

import java.util.Iterator;
import java.util.Set;

public class Word {

    private final int wordLength;
    private final int vowelCount;
    private final Set<String> vowelSet;

    public Word(int wordLength, int vowelCount, Set<String> vowelSet) {
        this.wordLength = wordLength;
        this.vowelCount = vowelCount;
        this.vowelSet = vowelSet;
    }

    public int getVowelCount() {
        return vowelCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (wordLength != word.wordLength) return false;
        return vowelSet.equals(word.vowelSet);

    }

    @Override
    public int hashCode() {
        int result = wordLength;
        result = 31 * result + vowelSet.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("({");

        Iterator<String> iterator = vowelSet.iterator();

        for(int i=0; i<vowelSet.size(); i++) {
            String vowel = iterator.next();
            sb.append(vowel);
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append("}, ");
        sb.append(wordLength);
        sb.append(")");
        return sb.toString();
    }
}
