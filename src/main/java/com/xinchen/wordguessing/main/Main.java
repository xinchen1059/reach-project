package com.xinchen.wordguessing.main;

import com.xinchen.wordguessing.controller.GameController;
import com.xinchen.wordguessing.model.LeaderBoard;

/**
 * Hangman program for Linkedin REACH, 2019 Fall
 * @author xin chen
 */
public class Main {

    /**
     * load leader board and start the game.
     * @param args
     * @throws Exception when fails to load word list.
     */
    public static void main(String[] args) throws Exception {
        LeaderBoard leaderBoard = new LeaderBoard();
        GameController gameController = new GameController(leaderBoard);
        gameController.init();
        gameController.play();
        gameController.exit();
    }
}