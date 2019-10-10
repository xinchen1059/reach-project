package com.xinchen.wordguessing.model;

import java.util.List;
import java.util.HashMap;
import java.util.Random;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Define word dictionary and implement word pick
 */
public class WordDict {

    private HashMap<Integer, List<String>> _wordDict;

    /**
     * Init Word Dict HashMap.
     * @throws Exception when fails to load word list.
     */
    public WordDict() throws Exception {
        _wordDict = new HashMap<Integer, List<String>>();
        initWordDict();
    }

    /**
     * Word Dict stored in HashMap with key being 1-10 difficulty.
     * @throws Exception when fails to load word list.
     */
    private void initWordDict() throws Exception {
        // Initialize the dictionary
        for (int i = 1; i <= 10; i++) {
            String url = "http://app.linkedin-reach.io/words?difficulty=" + i;
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            BufferedReader rd = null;
            try {
                HttpResponse response = client.execute(request);
                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            } catch (Exception e) {
                throw new Exception("Unable to connect to online dictionary.");
            }
            String line;
            List<String> wordList = new ArrayList<String>();
            while ((line = rd.readLine()) != null) {
                wordList.add(line.toLowerCase());
            }
            _wordDict.put(i, wordList);
        }
    }

    /**
     * Randomly pick a word to start the game.
     * @return a word to start onePlay.
     */
    public String randomPick(int difficulty) {
        int size =_wordDict.get(difficulty).size();
        Random random = new Random();
        return _wordDict.get(difficulty).get(random.nextInt(size));
    }
}
