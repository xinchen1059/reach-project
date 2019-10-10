package com.xinchen.wordguessing.controller;

import com.xinchen.wordguessing.model.GamePlay;
import com.xinchen.wordguessing.model.GuessWord;
import com.xinchen.wordguessing.model.LeaderBoard;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

public class TestGameController {

    @Test
    public void testInitPlayer() {
        LeaderBoard leaderBoard = new LeaderBoard();
        GameController gameController = new GameController(leaderBoard);
        ByteArrayInputStream in = new ByteArrayInputStream("test".getBytes());
        System.setIn(in);
        gameController.initPlayer();
        AssertJUnit.assertEquals("test", gameController.getPlayer().get_name());
        System.setIn(System.in);
    }

    @Test
    public void testChooseDifficulty() {
        LeaderBoard leaderBoard = new LeaderBoard();
        GameController gameController = new GameController(leaderBoard);
        ByteArrayInputStream in;
        int difficulty;

        /**
         * test difficulty 1 -> 5
         */
        in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);
        difficulty = gameController.chooseDifficulty(1);
        AssertJUnit.assertEquals(5, difficulty);
        System.setIn(System.in);

        /**
         * test invalid input difficulty 5 -> 5
         */
        in = new ByteArrayInputStream("20".getBytes());
        System.setIn(in);
        difficulty = gameController.chooseDifficulty(5);
        AssertJUnit.assertEquals(5, difficulty);
        System.setIn(System.in);
    }

    @Test
    public void testPlayerGuess() {
        LeaderBoard leaderBoard = new LeaderBoard();
        GameController gameController = new GameController(leaderBoard);
        GuessWord guessWord = new GuessWord("test");
        GamePlay gamePlay = new GamePlay(guessWord);
        ByteArrayInputStream in = new ByteArrayInputStream("t".getBytes());
        System.setIn(in);
        String guess = gameController.playerGuess(gamePlay);
        AssertJUnit.assertEquals("t", guess);
        System.setIn(System.in);

    }

}
