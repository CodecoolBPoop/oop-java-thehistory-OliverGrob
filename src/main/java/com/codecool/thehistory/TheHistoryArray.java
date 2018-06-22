package com.codecool.thehistory;

import java.util.Arrays;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        // Easier and faster solution
        wordsArray = text.split(" ");

        // This solution takes way longer, but its way more accurate and also maybe saves time later
//        wordsArray = text.replaceAll("\\n\\t|\\s{2,}", " ").trim().split(" ");
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        // Getting the new array's length
        int updatedArrayLength = wordsArray.length;
        for (String word : wordsArray) {
            if (word.equals(wordToBeRemoved)) {

                // This solution takes way longer, but its way more accurate
//            if (word.toLowerCase().replaceAll("[,.!?]", "").equals(wordToBeRemoved.toLowerCase())) {

                updatedArrayLength--;
            }
        }

        String[] updatedArray = new String[updatedArrayLength];

        // Rewriting the elements to the updated array
        for (int i = 0, j = 0; i < wordsArray.length; i++) {
            if (!wordsArray[i].equals(wordToBeRemoved)) {

                // This solution takes way longer, but its way more accurate
//            if (!wordsArray[i].toLowerCase().replaceAll("[,.!?]", "").equals(wordToBeRemoved.toLowerCase())) {

                updatedArray[j] = wordsArray[i];
                j++;
            }
        }

        // Replacing old array with the new one
        wordsArray = updatedArray;
    }

    @Override
    public int size() {
        return wordsArray.length;
    }

    @Override
    public void clear() {
        wordsArray = new String[0];
    }

    @Override
    public void replaceOneWord(String from, String to) {
        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(from)) {
                wordsArray[i] = to;
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        // Getting the updated array's length
        boolean isMatching;
        int updatedArrayLength = 0;

        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(fromWords[0])) {
                isMatching = true;
                // Checking if all following words are matching
                for (int j = 0; j < fromWords.length; j++) {
                    if ((i + j) >= wordsArray.length || !wordsArray[i + j].equals(fromWords[j])) {
                        isMatching = false;
                        break;
                    }
                }
                if (isMatching) {
                    updatedArrayLength++;
                    i += fromWords.length - 1;
                }
            }
        }

        // Creating updated array which will contain the 'toWords' as well (with the initial length we got before)
        String[] updatedArray = new String[wordsArray.length - (updatedArrayLength * fromWords.length) + (updatedArrayLength * toWords.length)];
        // Indexes in both arrays to monitor where to copy from
        int originArrayStartIndex = 0, updatedArrayStartIndex = 0;

        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(fromWords[0])) {
                isMatching = true;
                // Checking if all following words are matching
                for (int j = 0; j < fromWords.length; j++) {
                    if ((i + j) >= wordsArray.length || !wordsArray[i + j].equals(fromWords[j])) {
                        isMatching = false;
                        break;
                    }
                }
                // If matching -> copying from wordsArray and from toWords
                if (isMatching) {
                    System.arraycopy(wordsArray, originArrayStartIndex, updatedArray, updatedArrayStartIndex, i - originArrayStartIndex);
                    updatedArrayStartIndex += i - originArrayStartIndex;

                    System.arraycopy(toWords, 0, updatedArray, updatedArrayStartIndex, toWords.length);
                    updatedArrayStartIndex += toWords.length;

                    originArrayStartIndex += (i - originArrayStartIndex) + fromWords.length;
                    i += fromWords.length - 1;
                }
            }
        }

        // If there are remaining items at the end, we copy them here
        if (originArrayStartIndex != wordsArray.length) {
            System.arraycopy(wordsArray, originArrayStartIndex, updatedArray, updatedArrayStartIndex, wordsArray.length - originArrayStartIndex);
        }

        wordsArray = updatedArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArray) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }
}
