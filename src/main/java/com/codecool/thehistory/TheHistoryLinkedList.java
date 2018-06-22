package com.codecool.thehistory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TheHistoryLinkedList implements TheHistory {
    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private List<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {
        for (String word : text.split(" ")) {
            wordsLinkedList.add(word);
        }
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        ListIterator<String> listIterator = wordsLinkedList.listIterator();

        while (listIterator.hasNext()) {
            if (listIterator.next().equals(wordToBeRemoved)) {
                listIterator.remove();
            }
        }
    }

    @Override
    public int size() {
        return wordsLinkedList.size();
    }

    @Override
    public void clear() {
        wordsLinkedList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        ListIterator<String> listIterator = wordsLinkedList.listIterator();

        while (listIterator.hasNext()) {
            if (listIterator.next().equals(from)) {
                listIterator.set(to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        ListIterator<String> listIterator = wordsLinkedList.listIterator();
        boolean isMatching;
        // If we moved x times forward the iterator but no match was found we need to set it back to x-1 position
        // because if not we will miss (step through) elements which could be match
        int listIteratorSetBack;

        while (listIterator.hasNext()) {
            if (listIterator.next().equals(fromWords[0])) {
                isMatching = true;
                listIteratorSetBack = 1;

                for (int i = 1; i < fromWords.length; i++) {
                    if (!listIterator.hasNext() || !listIterator.next().equals(fromWords[i])) {
                        isMatching = false;
                        break;
                    }
                    else {
                        listIteratorSetBack++;
                    }
                }

                if (isMatching) {
                    // If match was found we go back where the match started to change the elements
                    for (int i = 0; i < fromWords.length; i++) {
                        listIterator.previous();
                    }
                    // Basic modification
                    // Changing the elements what we can without adding/removing
                    // (which already have a spot in the list)
                    for (int i = 0; i < Math.min(fromWords.length, toWords.length); i++) {
                        listIterator.next();
                        listIterator.set(toWords[i]);
                    }
                    // Extra modifications (adding or removing elements)
                    // If we need to remove elements, we remove them
                    if (fromWords.length > toWords.length) {
                        for (int i = 0; i < fromWords.length - toWords.length; i++) {
                            listIterator.next();
                            listIterator.remove();
                        }
                    }
                    // Or if we need to add element, we add them
                    else if (fromWords.length < toWords.length) {
                        for (int i = 0; i < toWords.length - fromWords.length; i++) {
                            listIterator.add(toWords[fromWords.length + i]);
                        }
                    }
                }
                // If no match was found we need to set the iterator back not to miss elements
                else {
                    if (listIterator.hasNext()) {
                        for (int i = 0; i < listIteratorSetBack; i++) {
                            listIterator.previous();
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsLinkedList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
