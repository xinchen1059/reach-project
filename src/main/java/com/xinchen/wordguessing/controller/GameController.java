package com.xinchen.wordguessing.controller;

import com.xinchen.wordguessing.entities.GameStatus;
import com.xinchen.wordguessing.model.GuessWord;
import com.xinchen.wordguessing.entities.Player;
import com.xinchen.wordguessing.model.GamePlay;
import com.xinchen.wordguessing.model.LeaderBoard;
import com.xinchen.wordguessing.model.WordDict;
import com.xinchen.wordguessing.view.GameDisplay;

import java.util.Scanner;

/**
 * Controller to init, play and exit game.
 */
public class GameController {

    private WordDict _wordDict;
    private Player _player;
    private LeaderBoard _leaderBoard;
    public GameController(LeaderBoard leaderboard) {
        _leaderBoard = leaderboard;
    }

    /**
     * Initiate the game.
     * Init the player and load wordList.
     * @throws Exception when hen fails to load word list.
     */
    public void init() throws Exception {
        initPlayer();
        _wordDict = new WordDict();
    }

    /**
     * Init the Player with gamer inputs.
     * Get player record or new Player.
     */
    public void initPlayer() {
        GameDisplay.renderInputName();
        String userName = "";
        while (userName.length() == 0) {
            Scanner scanner = new Scanner(System.in);
            userName = scanner.nextLine();
        }
        _player = _leaderBoard.getPlayerRecord(userName);

    }

    /**
     * For testing
     * @return Player
     */
    public Player getPlayer() {
        return _player;
    }

    /**
     * Ask gamer to choose difficulty level.
     * @return chosen difficulty level.
     */
    public int chooseDifficulty(int difficulty) {
        // Init the difficulty
        GameDisplay.renderInputDifficulty();
        Scanner scanner = new Scanner(System.in);
        int chosenDifficulty;
        try {
            chosenDifficulty = Integer.parseInt(scanner.nextLine());
            if ((chosenDifficulty > 10)||(chosenDifficulty < 1)) {
                chosenDifficulty = difficulty;
                GameDisplay.renderInvalidDifficulty();
            }
        }catch(NumberFormatException e) {
            chosenDifficulty = difficulty;
            GameDisplay.renderInvalidDifficulty();
        }
        return chosenDifficulty;
    }

    /**
     *
     * @param gamePlay
     * @return guess letter or word from gamer
     */
    public String playerGuess(GamePlay gamePlay){
        GameDisplay.renderGuessWord(gamePlay.getPrintWord());
        Scanner scanner;
        String guess = "";
        while(guess.length() == 0){
            GameDisplay.renderInputGuess();
            scanner = new Scanner(System.in);
            guess = scanner.nextLine().toLowerCase();
        }
        return guess;
    }

    /**
     * Each round of Hangman with 6 attempts.
     * @param difficulty
     */
    public void onePlay(int difficulty) {
        GuessWord guessWord = new GuessWord(_wordDict.randomPick(difficulty));
        GamePlay gamePlay = new GamePlay(guessWord);
        gamePlay.setPlayer(_player);
        gamePlay.set_difficulty(difficulty);

        while (gamePlay.getGameStatus() == GameStatus.CONTINUE
                || gamePlay.getGameStatus() == GameStatus.LIFESAVER) {
            String guess = playerGuess(gamePlay);
            gamePlay.setGuess(guess);
            gamePlay.updateGameStatus();
            if (gamePlay.getGameStatus() == GameStatus.LIFESAVER) {
                GameDisplay.renderLifeSaver();
                Scanner scanner = new Scanner(System.in);  // Create a Scanner object
                String input = scanner.nextLine();
                if (input.toLowerCase().equals("y")){
                    gamePlay.lifeSaver();
                }
            }
        }

        if (gamePlay.getGameStatus() == GameStatus.LOSE ){
            GameDisplay.renderLose(guessWord);
        }else{
            GameDisplay.renderWin();
        }
    }

    /**
     * Gamer will input user name and choose:
     * 1. Start new game, current difficult =
     * 2. Change difficulty level
     * 3. View leader board
     * 4. Exit game
     */
    public void play() {
        int difficulty=1; //game start with easiest mode
        while(true){
            GameDisplay.renderNewGame(difficulty);
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            String input = scanner.nextLine();
            if(input.equals("1")){
                onePlay(difficulty);
                _leaderBoard.updatePlayerRecord();
            }else if (input.equals("2")){
                difficulty=chooseDifficulty(difficulty);
            }else if (input.equals("3")){
                leaderBoard();
            }else if (input.equals("4")){
                break;
            }else{
                GameDisplay.renderInputNotValid();
            }
        }
    }

    /**
     * Show leader board.
     */
    public void leaderBoard(){
        GameDisplay.renderLeaderBoard(_leaderBoard.getSortedPlayers());
    }

    /**
     * exit the game.
     */
    public void exit() {
        GameDisplay.renderBye();
    }

}
