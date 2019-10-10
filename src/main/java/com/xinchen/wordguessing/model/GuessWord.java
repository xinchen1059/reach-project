package com.xinchen.wordguessing.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Data structure to represent guess word
 */
public class GuessWord {

    private String _guessWord;
    private HashMap<Character, List<Integer>> _indexMap;

    /**
     * Construct renderGuessWord with string.
     * Also create _indexMap to store letter,index map.
     * @param guessWord
     */
    public GuessWord(String guessWord) {
        _guessWord = guessWord;
        _indexMap = new HashMap<Character, List<Integer>>();
        initMap();
    }

    /**
     * Get String of renderGuessWord.
     * @return renderGuessWord String.
     */
    public final String getString() {
        return _guessWord;
    }

    /**
     * return word length of renderGuessWord.
     * @return word length.
     */
    public int wordLength() {
        return _guessWord.length();
    }

    /**
     * create _indexMap to store letter,index map.
     */
    private void initMap(){
        for (int i = 0; i < _guessWord.length(); i++) {
            if (_indexMap.containsKey(_guessWord.charAt(i))) {
                _indexMap.get(_guessWord.charAt(i)).add(i);
            }else{
                //List<Integer> list = Arrays.asList(i);
                List<Integer> list = new ArrayList<Integer>(Arrays.asList(i));
                _indexMap.put(_guessWord.charAt(i),list);
            }
        }
    }

    /**
     * Check whether guess is correct
     * @param printWord previous word revealed to gamer.
     * @param guess from current round of guess.
     * @return word shown to gamer after new guess.
     */
    public String checkGuess(String printWord, String guess){
        StringBuilder tempWord = new StringBuilder(printWord);
        if (_indexMap.containsKey(guess.charAt(0))) {
            List<Integer> list = _indexMap.get(guess.charAt(0));
            _indexMap.remove(guess.charAt(0));
            for (int i : list){
                tempWord.setCharAt(i, guess.charAt(0));
            }
        }
        return tempWord.toString();
    }

    /**
     * Reveal one letter in life saver mode.
     * @param printWord previous word revealed to gamer.
     * @return word shown to gamer after new guess.
     */
    public String lifeSaver(String printWord) {
        StringBuilder tempWord = new StringBuilder(printWord);
        Character saver = _indexMap.keySet().iterator().next();
        for (int i : _indexMap.get(saver)){
            tempWord.setCharAt(i, saver);
        }
        return tempWord.toString();
    }

}
