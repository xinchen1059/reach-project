package com.xinchen.wordguessing.view;

import com.xinchen.wordguessing.entities.Player;
import com.xinchen.wordguessing.model.GuessWord;
import java.util.List;

/**
 * Views in different scenarios.
 */
public class GameDisplay {
    /**
     * New game view.
     */
    public static void renderNewGame(int difficulty){
        System.out.println("Please select by typing: 1. Start new game, current difficulty = " + difficulty);
        System.out.println("                         2. Change difficulty level");
        System.out.println("                         3. View leaderboard");
        System.out.println("                         4. Exit game");
    }

    /**
     * End game view.
     */
    public static void renderBye(){
        System.out.println("Thank you for playing. See you next time!");
    }

    /**
     * Win view.
     */
    public static void renderWin(){
        System.out.println("Congratulations, you WIN!");

        System.out.println("              _ _");
        System.out.println("             /   \\");
        System.out.println("            | O O |");
        System.out.println("             \\_v_/");
        System.out.println("           v___|___v");
        System.out.println("               | ");
        System.out.println("              / \\ ");
        System.out.println("             /   \\");
    }

    /**
     * Lose game view and reveal guess word.
     * @param guessWord
     */
    public static void renderLose(GuessWord guessWord) {
        System.out.println("Sorry, you LOSE! You can play again.");
        System.out.println("The word is: " + guessWord.getString());
    }

    /**
     * Msg to indicate wrong difficulty input
     */
    public static void renderInvalidDifficulty() {
        System.out.println("input is not valid (int 1-10). Failed to change difficulty.");
    }

    /**
     * Msg to ask gamer to input guess.
     */
    public static void renderInputGuess(){
        System.out.println("Please guess a letter or a whole word: ");
    }

    /**
     * Msg to ask gamer to input name.
     */
    public static void renderInputName(){
        System.out.println("Welcome, first please enter username: ");
    }

    /**
     * Msg to ask for valid input.
     */
    public static void renderInputNotValid(){
        System.out.println("Sorry, your input is not valid. ");
    }

    /**
     * Print out leader board.
     * Same score same rank.
     * @param allPlayer List of sorted all Players.
     */
    public static void renderLeaderBoard(List<Player> allPlayer) {
        //allScore.entrySet().stream()
        //        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        //        .forEach(System.out::println);
        System.out.println("Rank\t\tName Score");
        int rank = 1;
        System.out.format("%4d%12s%6d%n", rank, allPlayer.get(0).get_name(), allPlayer.get(0).get_score());
        for (int i = 1; i < allPlayer.size(); i++ ){
            if (allPlayer.get(i).get_score() < allPlayer.get(i-1).get_score()) {
                rank = i + 1;
            }
            System.out.format("%4d%12s%6d%n", rank, allPlayer.get(i).get_name(), allPlayer.get(i).get_score());
        }
    }

    /**
     * Mst to ask gamer to choose difficult.
     */
    public static void renderInputDifficulty(){
        System.out.println("Please choose difficulty level 1-10 (10 is the most difficult): ");
    }

    /**
     * Print guess word with word length.
     * @param word with letters hidden and word length revealed.
     */
    public static void renderGuessWord(String word){
        System.out.println("Please guess word: " + word);
    }

    /**
     * When guess is correct, print out.
     * @param word revealed with correct guesses.
     */
    public static void renderCorrectLetter(String word){
        System.out.println("One more letter is revealed: " + word);
    }

    /**
     * Msg to ask whether invoke life saver.
     */
    public static void renderLifeSaver() {
        System.out.println("Do you want a life saver (once per game)? Type Y if yes. ");
    }

    /**
     * Print guess counts remained.
     * @param guessRemained
     */
    public static void renderGuessRemained(int guessRemained) {
        System.out.println("The number of guesses remaining: " + guessRemained);
    }

    /**
     * Print attempted wrong guesses.
     * @param wrongGuesses
     */
    public static void renderWrongGuesses(String wrongGuesses) {
        System.out.println("Attempted wrong guesses are:" + wrongGuesses);
    }

    /**
     * Draw wordguessing with six failed attempts.
     * @param guessCount
     */
    public static void renderHangman(int guessCount){
        if (guessCount == 1) {
            System.out.println("Wrong, 1st attempt. Please try again.");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (guessCount == 2) {
            System.out.println("Wrong, 2nd attempt. Please try again.");
            System.out.println("   _____________");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___");
        }
        if (guessCount == 3) {
            System.out.println("Wrong, 3rd attempt. Please try again.");
            System.out.println("   _____________");
            System.out.println("   |           |");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        | O O |");
            System.out.println("   |         \\_^_/");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (guessCount == 4) {
            System.out.println("Wrong, 4th attempt. Please try again.");
            System.out.println("   _____________");
            System.out.println("   |           |");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        | O O |");
            System.out.println("   |         \\_^_/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (guessCount == 5) {
            System.out.println("Wrong, 5th attempt. Please try again.");
            System.out.println("   _____________");
            System.out.println("   |           |");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        | O O |");
            System.out.println("   |         \\_^_/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |          / \\ ");
            System.out.println("   |         /   \\");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (guessCount == 6) {
            System.out.println("Wrong, 6th attempt. No more try.");
            System.out.println("   _____________");
            System.out.println("   |           |");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        | O O |");
            System.out.println("   |         \\_^_/");
            System.out.println("   |        ___|___");
            System.out.println("   |           | ");
            System.out.println("   |          / \\ ");
            System.out.println("   |         /   \\");
            System.out.println("   |");
            System.out.println("___|___");
        }
    }

}
