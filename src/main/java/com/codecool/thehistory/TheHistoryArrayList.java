package com.codecool.thehistory;

import java.util.*;

public class TheHistoryArrayList implements TheHistory {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        for (String word : text.split(" ")) {
            wordsArrayList.add(word);
        }

        // This solution takes way longer, but its way more accurate
//        wordsArrayList.addAll(Arrays.asList(text.split(" ")))
    }

    @Override
    public void removeWord(String wordToBeRemoved) {

        // Using same solution as in SimpleArray
        // Getting the new array's length
        int updatedArrayListLength = wordsArrayList.size();
        for (String word : wordsArrayList) {
            if (word.equals(wordToBeRemoved)) {

                // This solution takes way longer, but its way more accurate
//            if (word.toLowerCase().replaceAll("[,.!?]", "").equals(wordToBeRemoved.toLowerCase())) {

                updatedArrayListLength--;
            }
        }

        List<String> updatedArrayList = new ArrayList<String>(updatedArrayListLength);

        // Rewriting the elements to the updated array
        for (int i = 0; i < wordsArrayList.size(); i++) {
            if (!wordsArrayList.get(i).equals(wordToBeRemoved)) {

                // This solution takes way longer, but its way more accurate
//            if (!wordsArray[i].toLowerCase().replaceAll("[,.!?]", "").equals(wordToBeRemoved.toLowerCase())) {

                updatedArrayList.add(wordsArrayList.get(i));
            }
        }

        // Replacing old array with the new one
        wordsArrayList = updatedArrayList;
    }

    @Override
    public int size() {
        return wordsArrayList.size();
    }

    @Override
    public void clear() {
        wordsArrayList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        for (int i = 0; i < wordsArrayList.size(); i++) {
            if (wordsArrayList.get(i).equals(from)) {
                wordsArrayList.set(i, to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        // Getting the updated array's length
        boolean isMatching;
        int updatedArrayLength = 0;

        for (int i = 0; i < wordsArrayList.size(); i++) {
            if (wordsArrayList.get(i).equals(fromWords[0])) {
                isMatching = true;
                // Checking if all following words are matching
                for (int j = 0; j < fromWords.length; j++) {
                    if ((i + j) >= wordsArrayList.size() || !wordsArrayList.get(i + j).equals(fromWords[j])) {
                        isMatching = false;
                        break;
                    }
                }
                if (isMatching) {
                    updatedArrayLength++;
                }
            }
        }

        // Creating updated array which will contain the 'toWords' as well (with the initial length we got before)
        List<String> updatedArrayList = new ArrayList<String>(wordsArrayList.size() - (updatedArrayLength * fromWords.length) + (updatedArrayLength * toWords.length));
        // Index in the array to monitor where to copy from
        int originArrayListStartIndex = 0;

        for (int i = 0; i < wordsArrayList.size(); i++) {
            if (wordsArrayList.get(i).equals(fromWords[0])) {
                isMatching = true;
                // Checking if all following words are matching
                for (int j = 0; j < fromWords.length; j++) {
                    if ((i + j) >= wordsArrayList.size() || !wordsArrayList.get(i + j).equals(fromWords[j])) {
                        isMatching = false;
                        break;
                    }
                }
                // If matching -> copying from wordsArray and from toWords
                if (isMatching) {
                    for (int j = 0; j < i - originArrayListStartIndex; j++) {
                        updatedArrayList.add(wordsArrayList.get(originArrayListStartIndex + j));
                    }

                    for (int j = 0; j < toWords.length; j++) {
                        updatedArrayList.add(toWords[j]);
                    }

                    originArrayListStartIndex += (i - originArrayListStartIndex) + fromWords.length;
                    i += fromWords.length - 1;
                }
            }
        }

        // If there are remaining items at the end, we copy them here
        if (originArrayListStartIndex != wordsArrayList.size()) {
            for (int j = originArrayListStartIndex; j < wordsArrayList.size(); j++) {
                updatedArrayList.add(wordsArrayList.get(j));
            }
        }

        wordsArrayList = updatedArrayList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
