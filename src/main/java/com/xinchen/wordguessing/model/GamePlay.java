package com.xinchen.wordguessing.model;

import com.xinchen.wordguessing.entities.GameStatus;
import com.xinchen.wordguessing.entities.Player;
import com.xinchen.wordguessing.entities.Score;
import com.xinchen.wordguessing.view.GameDisplay;
import org.apache.commons.lang3.StringUtils;

/**
 * Logic for word guessing game.
 */
public class GamePlay {

    private GuessWord _guessWord;
    private Player _player;
    private String _guess;
    private String _printWord;
    private GameStatus _gameStatus ;
    private String _wrongGuesses;
    private Boolean _lifeSaver;
    private int _guessRemained;
    private int _guessCount;
    private int _difficulty;

    /**
     * Construct a game with the guess word.
     * Set initial values to field variables
     * @param guessWord
     */
    public GamePlay(GuessWord guessWord) {
        _guessWord = guessWord;
        _guessCount = 0;
        _guessRemained = 6;
        _wrongGuesses = "";
        _printWord = StringUtils.repeat("?",guessWord.wordLength());
        _gameStatus = GameStatus.CONTINUE;
        _lifeSaver = true;
        _difficulty = 1;
    }

    /**
     * Set guesscount, for testing purpose.
     * @param guessCount to be set
     */
    public void set_guessCount(int guessCount) {
        _guessCount = guessCount;
        _guessRemained = 6 - _guessCount;
    }

    /**
     * Set difficulty level
     * @param difficulty
     */
    public void set_difficulty(int difficulty) {
        _difficulty = difficulty;
    }
    /**
     * Print hidden word shown to gamer.
     * @return word shown to gamer.
     */
    public String getPrintWord() {
        return _printWord;
    }

    /**
     * Get game status.
     * @return gameStatus.
     */
    public GameStatus getGameStatus() {
        return _gameStatus;
    }

    /**
     * Set print word, for testing purpose.
     * @param printWord
     */
    public void set_printWord(String printWord) {
        _printWord = printWord;
    }

    /**
     * Set gamer guess.
     * @param guess
     */
    public void setGuess(String guess) {
        _guess = guess;
    }

    /**
     * Set gamer.
     * @param player
     */
    public void setPlayer(Player player) {
        _player = player;
    }

    /**
     * process after each letter guess.
     */
    public void guessOneLetter() {
        /* method 1 : iterate through each characters in renderGuessWord.
        String tempWord = "";
        for (int i = 0; i < _guessWord.wordLength(); i++) {
            if (_guessWord(i) == _guess.charAt(0)) {
                tempWord += _guess.charAt(0);
            } else if (_printWord.charAt(i) != '?') {
                tempWord += _printWord.charAt(0);
            } else {
                tempWord += "?";
            }
        }*/

        String tempWord = _guessWord.checkGuess(_printWord, _guess);
        if (tempWord.equals(_printWord)) {
            wrongGuess();
        } else {
            _printWord = tempWord;
            GameDisplay.renderCorrectLetter(_printWord);
            printInfo();
            if (_printWord.equals(_guessWord.getString())) {
                _gameStatus = GameStatus.WIN;
                calculateScore();
            }
        }
    }

    /**
     * process when guess is wrong.
     */
    public void wrongGuess() {
        _guessCount++;
        _wrongGuesses = _wrongGuesses + " " + _guess;
        GameDisplay.renderHangman(_guessCount);
        checkCount();
        printInfo();
    }

    /**
     * Print game info to gamer.
     */
    public void printInfo() {
        _guessRemained = 6 - _guessCount;
        GameDisplay.renderGuessRemained(_guessRemained);
        GameDisplay.renderWrongGuesses(_wrongGuesses);
    }

    /**
     * Update game status by checking counts.
     */
    public void checkCount() {
        if ((_guessCount == 5)&&(_lifeSaver)) {
            _gameStatus = GameStatus.LIFESAVER;
        }else if (_guessCount < 6) {
            _gameStatus = GameStatus.CONTINUE;
        }else{
            _gameStatus = GameStatus.LOSE;
        }
    }

    /**
     * Calculate score.
     */
    public void calculateScore() {
        _player.addScore(Score.winScore);
        _player.addScore(_guessRemained-1);
        int lifeSaverBonus = _lifeSaver ? Score.lifeBonus : 0;
        _player.addScore(lifeSaverBonus);
        _player.multiplyScore(_difficulty);
    }

    /**
     * process after each whole word guess.
     */
    public void guessWholeWord() {
        if (_guess.equals(_guessWord.getString())){
            _gameStatus = GameStatus.WIN;
            printInfo();
            calculateScore();
        }else{
            wrongGuess();
        }
    }

    /**
     * Check whether the guess is a letter or a word.
     */
    public void updateGameStatus() {
        if(_guess.length() > 1) {
            guessWholeWord();
        }else if (_guessWord.wordLength() == 1){
            guessWholeWord();
        }else{
            guessOneLetter();
        }
    }

    /**
     * Life saver feature to reveal one letter.
     */
    public void lifeSaver() {
        _lifeSaver = false;
        _printWord = _guessWord.lifeSaver(_printWord);
        GameDisplay.renderCorrectLetter(_printWord);
        printInfo();
        if (_printWord.equals(_guessWord.getString())){
            _gameStatus = GameStatus.WIN;
            calculateScore();
        }else{
            _gameStatus = GameStatus.CONTINUE;
        }
    }

}
