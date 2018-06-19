package com.codecool.thehistory;

import java.util.Arrays;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        //TODO: check the TheHistory interface for more information
        // Easier and faster solution
//        wordsArray = text.split(" ");

        // This solution takes way longer, but its way more accurate and also saves time later
        wordsArray = text.replaceAll("\\n\\t|\\s{2,}", " ").trim().split(" ");
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        //TODO: check the TheHistory interface for more information

        // Getting the new array's length
        int newArrayLength = wordsArray.length;
        for (String word : wordsArray) {
            if (word.equals(wordToBeRemoved)) {

                // This solution takes way longer, but its way more accurate
//            if (word.toLowerCase().replaceAll("[,.!?]", "").equals(wordToBeRemoved.toLowerCase())) {

                newArrayLength--;
            }
        }

        String[] newArray = new String[newArrayLength];

        // Rewriting the elements to the updated array
        for (int i = 0, j = 0; i < wordsArray.length; i++) {
            if (!wordsArray[i].equals(wordToBeRemoved)) {

                // This solution takes way longer, but its way more accurate
//            if (!wordsArray[i].toLowerCase().replaceAll("[,.!?]", "").equals(wordToBeRemoved.toLowerCase())) {

                newArray[j] = wordsArray[i];
                j++;
            }
        }

        // Replacing old array with the new one
        wordsArray = newArray;
    }

    @Override
    public int size() {
        //TODO: check the TheHistory interface for more information
        return wordsArray.length;
    }

    @Override
    public void clear() {
        //TODO: check the TheHistory interface for more information
        wordsArray = new String[0];
    }

    @Override
    public void replaceOneWord(String from, String to) {
        //TODO: check the TheHistory interface for more information
        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(from)) {
                wordsArray[i] = to;
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information

        // Temporary array for copying
        String[] tempArray;
        // Final array which contains the 'toWords' as well
        String[] updatedArray = new String[0];
        // Index reference to know where we need to start copying
        int startIndex = 0;
        boolean isMatching;

        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(fromWords[0])) {
                isMatching = true;
                // Checking if all following word is matching
                for (int j = 0; j < fromWords.length; j++) {
                    if ((i + j) >= wordsArray.length || !wordsArray[i + j].equals(fromWords[j])) {
                        isMatching = false;
                        break;
                    }
                }
                // If matching -> initializing tempArray length, and copying from updatedArray and wordsArray
                if (isMatching) {
                    tempArray = new String[updatedArray.length + (i - startIndex) + toWords.length];
//                    System.out.println(tempArray.length);

                    System.arraycopy(updatedArray, 0, tempArray, 0, updatedArray.length);
//                    System.out.println(Arrays.toString(tempArray));
                    System.arraycopy(wordsArray, startIndex, tempArray, updatedArray.length, i - startIndex);
//                    System.out.println(Arrays.toString(tempArray));

//                    for (int j = 0; j < toWords.length; j++) {
//                        tempArray[updatedArray.length + (i - startIndex) + j] = toWords[j];
//                    }
                    System.arraycopy(toWords, 0, tempArray, updatedArray.length + (i - startIndex), toWords.length);
//                    System.out.println(Arrays.toString(tempArray));

                    startIndex = i + fromWords.length;
                    i += fromWords.length - 1;
                    updatedArray = tempArray;
                }
            }
        }
        // If there is remaining items at the end
        if (startIndex != wordsArray.length) {
            tempArray = new String[updatedArray.length + (wordsArray.length - startIndex)];

            System.arraycopy(updatedArray, 0, tempArray, 0, updatedArray.length);
            System.arraycopy(wordsArray, startIndex, tempArray, updatedArray.length, wordsArray.length - startIndex);
//            System.out.println(Arrays.toString(tempArray));

            updatedArray = tempArray;
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
