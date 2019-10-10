package com.xinchen.wordguessing.model;

import com.xinchen.wordguessing.entities.GameStatus;
import com.xinchen.wordguessing.entities.Player;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TestGamePlay {

    @Test
    public void testUpdateGameStatus() {
        GuessWord guessWord = new GuessWord("test");
        GamePlay gamePlay;
        Player player;

        /**
         * Test whole word guess -> WIN
         */
        gamePlay = new GamePlay(guessWord);
        player = new Player("gamer");
        gamePlay.setPlayer(player);
        gamePlay.setGuess("test");
        gamePlay.updateGameStatus();
        AssertJUnit.assertEquals(GameStatus.WIN, gamePlay.getGameStatus());

        /**
         * Test whole word guess -> CONTINUE
         */
        gamePlay = new GamePlay(guessWord);
        player = new Player("gamer");
        gamePlay.setPlayer(player);
        gamePlay.setGuess("wrong");
        gamePlay.updateGameStatus();
        AssertJUnit.assertEquals(GameStatus.CONTINUE, gamePlay.getGameStatus());

        /**
         * Test one letter guess -> LOSE
         */
        gamePlay = new GamePlay(guessWord);
        player = new Player("gamer");
        gamePlay.setPlayer(player);
        gamePlay.set_printWord("te?t");
        gamePlay.setGuess("o");
        gamePlay.set_guessCount(5);
        gamePlay.updateGameStatus();
        AssertJUnit.assertEquals(GameStatus.LOSE, gamePlay.getGameStatus());

        /**
         * Test one letter guess -> LIFESAVER
         */
        gamePlay = new GamePlay(guessWord);
        player = new Player("gamer");
        gamePlay.setPlayer(player);
        gamePlay.set_printWord("te?t");
        gamePlay.setGuess("o");
        gamePlay.set_guessCount(4);
        gamePlay.updateGameStatus();
        AssertJUnit.assertEquals(GameStatus.LIFESAVER, gamePlay.getGameStatus());

        /**
         * Test one letter guess -> WIN
         */
        gamePlay = new GamePlay(guessWord);
        player = new Player("gamer");
        gamePlay.setPlayer(player);
        gamePlay.set_printWord("te?t");
        gamePlay.setGuess("s");
        gamePlay.updateGameStatus();
        AssertJUnit.assertEquals(GameStatus.WIN, gamePlay.getGameStatus());
    }

    @Test
    public void testLifeSaver() {
        GuessWord guessWord = new GuessWord("test");
        GamePlay gamePlay = new GamePlay(guessWord);
        gamePlay.lifeSaver();
        AssertJUnit.assertEquals("??s?", gamePlay.getPrintWord());
    }

    @Test
    public void testCheckCount() {
        GuessWord guessWord = new GuessWord("test");
        GamePlay gamePlay;

        /**
         * Test checkCount -> CONTINUE
         */
        gamePlay = new GamePlay(guessWord);
        gamePlay.set_guessCount(4);
        gamePlay.checkCount();
        AssertJUnit.assertEquals(GameStatus.CONTINUE, gamePlay.getGameStatus());

        /**
         * Test checkCount -> LIFESAVER
         */
        gamePlay = new GamePlay(guessWord);
        gamePlay.set_guessCount(5);
        gamePlay.checkCount();
        AssertJUnit.assertEquals(GameStatus.LIFESAVER, gamePlay.getGameStatus());

        /**
         * Test checkCount -> LOSE
         */
        gamePlay = new GamePlay(guessWord);
        gamePlay.set_guessCount(6);
        gamePlay.checkCount();
        AssertJUnit.assertEquals(GameStatus.LOSE, gamePlay.getGameStatus());
    }

    @Test
    public void testCalculateScore() {
        GuessWord guessWord = new GuessWord("test");
        GamePlay gamePlay;
        /**
         * Test checkCount -> CONTINUE
         */
        gamePlay = new GamePlay(guessWord);
        Player player = new Player("gamer");
        gamePlay.setPlayer(player);
        gamePlay.set_guessCount(0);
        gamePlay.set_difficulty(2);
        gamePlay.calculateScore();
        AssertJUnit.assertEquals(40, player.get_score());
    }

}
