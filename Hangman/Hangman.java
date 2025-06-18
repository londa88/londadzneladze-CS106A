import java.awt.Color;
import java.io.IOException;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class Hangman extends ConsoleProgram {
	public static final int MAX_NUM = 8;
	RandomGenerator rgen = RandomGenerator.getInstance();
	HangmanLexicon lex;
	private HangmanCanvas canvas;
	private int score = 0;

	public void init() {
		try {
			lex = new HangmanLexicon();
		} catch (IOException e) {
			e.printStackTrace();
		}
		canvas = new HangmanCanvas();
		add(canvas);

	}

//divading this task in two parts, designing and playing
	public void run() {
		designTheGame();
		playTheGame();
	}

//this method makes relevant space for game
	private void designTheGame() {
		println("Welcome to Hangman");
		canvas.canvasStartingPos();
		canvas.setBackground(Color.PINK);
		canvas.yourScore(score);
	}

//this method leads game process
	private void playTheGame() {
		int guesses = MAX_NUM;
		int index = rgen.nextInt(0, lex.getWordCount() - 1);
		String givenWord = lex.getWord(index);
		String blankWord = "";
		for (int j = 0; j < givenWord.length(); j++) {
			blankWord = blankWord + "-";
		}
		canvas.displayWord(blankWord);
		println("The World now looks like this:" + blankWord);
		println("You have " + guesses + " guesses left");
		while (!blankWord.equals(givenWord) && guesses != 0) {
			String ch = readLine("Your guess:");
			if (ch.length() == 0) {
				continue;
			}
			if (ch == ch.toLowerCase()) { // every lowercase char will be formed into the upper one
				ch = ch.toUpperCase();
			}

			if (ch.length() > 1 || ch.charAt(0) < 'A' || ch.charAt(0) > 'Z') { // if player enters any symbol other than
																				// letters
				println("MISTAKE! TRY AGAIN!");
				continue;
			}
			if (blankWord.contains(ch)) { // if this char was already guessed, loop starts again
				continue;
			}
			for (int i = 0; i < givenWord.length(); i++) {
				if (ch.charAt(0) == givenWord.charAt(i)) {
					blankWord = blankWord.substring(0, i) + ch.charAt(0) + blankWord.substring(i + 1);
					canvas.displayWord(blankWord);
					canvas.yourScore(score);
				}
			}
			if (givenWord.contains(ch)) { // if you've guessed letter, your score increases with 20 points
				score += 20;
				canvas.yourScore(score);
				println("Your guess is correct!");
				println("You have " + guesses + " guesses left");
				println("The world now looks like this:" + blankWord);
			} else {
				score -= 10; // if you've made mistake, your score dicreases and another body part shows on
								// the canvas

				canvas.yourScore(score);
				canvas.reset(MAX_NUM - guesses);
				guesses--;
				println("There are no " + ch + "'s in the world");
				println("You have " + guesses + " guesses left");
				println("The world now looks like this:" + blankWord);
				canvas.noteIncorrectGuess(ch);
			}

		}
		if (guesses == 0) { // if your guesses equals 0, game ends
			println("You have lost, sorry :(");
			println("You are completely hung");
			println("The word was " + givenWord);
			gameRestart();
		}

		if (blankWord.equals(givenWord)) {
			println("YOU HAVE WON!CONGRATS :)");
			gameRestart();
		}
	}

	private void gameRestart() { // is your score is above 10, you can restart game
		if (score > 10) {
			int num = readInt(
					"Your score is above 10 point, so you can start again if you want to. enter 1 to play again, enter 0 otherwise:");
			if (num == 1) {
				canvas.removeAll();
				canvas.restart();
				designTheGame();
				playTheGame();
			} else {
				println("Okay, have a lucky day");
			}
		}
	}

}
