import java.util.Arrays;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() { // game will go on untli all the categories of each players will be used, so it
								// will take n-scoring-categories*nplayers rounds.
		formateScoresMatrix();
		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			for (int j = 0; j < nPlayers; j++) {
				firstRoll(j);
				selectAndReroll();
				updateScore(j);
			}
		}
		endGame();
	}

	private void firstRoll(int playerNum) { // in this method, the first roll is initialized, using random generator to
											// formate every die with random numbers in between (1,6)
		display.printMessage(playerNames[playerNum] + "'s turn. Click \"Roll Dice\" button to roll the dice");
		display.waitForPlayerToClickRoll(playerNum + 1);
		dice = new int[N_DICE];
		for (int i = 0; i < N_DICE; i++) {
			dice[i] = rgen.nextInt(1, 6);
		}
		display.displayDice(dice);
	}

	// this method initializes other rolls, but if the player already likes the set
	// of dices, after first roll, with just clicking roll again once, player can
	// select a category.
	private void selectAndReroll() {
		for (int i = 0; i < NROLLS; i++) {
			display.printMessage("Select the dice you wish to re-roll and click roll again");
			display.waitForPlayerToSelectDice();
			if (noDicesWereSelected()) {
				break;
			} else {
				formateDiceArray();
				display.displayDice(dice);
			}
		}
	}

	private void formateDiceArray() { // this method formates the dices, that had been selected by player, using
										// random generator to fill these dices with random numbers.
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			}

		}
	}

	private boolean noDicesWereSelected() { // this method tells if the dices had been choosen after first roll.
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				return false;
			}
		}
		return true;
	}

	private void updateScore(int playerNum) { // with this method, player selects category and relevant score shows up.
		display.printMessage("Select a category for this roll");
		int category = display.waitForPlayerToSelectCategory();
		while (true) {
			if (categoryIsUnused(category, playerNum) == false) {
				display.printMessage("This category was already choosen");
				category = display.waitForPlayerToSelectCategory();
			} else
				break;
		}
		if (checkRelevanceOfCategories(dice, category, playerNum)) {
			formateResult(category, dice, playerNum);

		} else
			scores[category - 1][playerNum] = 0;
		display.updateScorecard(category, playerNum + 1, scores[category - 1][playerNum]);
		display.updateScorecard(TOTAL, playerNum + 1, totalNum(playerNum));

	}

	private int totalNum(int playerNum) { // this method calculates the score, which should be written in the TOTAL
											// category and it changes after every iteration.
		int total = 0;
		for (int i = 0; i < N_CATEGORIES; i++) {
			if (scores[i][playerNum] != -1) {
				total += scores[i][playerNum];
			}
		}
		return total;
	}

	private void formateScoresMatrix() {// this method fills whole matrix of scores with -1, in order to differentiate
										// every category from already used categories.
		scores = new int[N_CATEGORIES][nPlayers];
		for (int i = 0; i < N_CATEGORIES; i++) {
			for (int j = 0; j < nPlayers; j++) {
				scores[i][j] = -1;
			}
		}
	}

	private int countExactNumbers(int[] dice, int num) { // this method counts number of dices, which is same to the
															// concrete number inbetween 1,6
		int maxCount = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == num) {
				maxCount++;
			}
		}
		return maxCount;
	}

	private boolean checkRelevanceOfCategories(int[] dice, int category, int playerNum) { // this method checks the
																							// relevance of selected
																							// category.
		Arrays.sort(dice);

		if (firstSixCategoryIsRelevant(category)) {
			return true;
		}

		if (threeOfAKindIsRelevant(category)) {
			return true;
		}
		if (fourOfAKindIsRelevant(category)) {
			return true;
		}
		if (fullhouseIsRelevant(category)) {
			return true;
		}
		if (smallStraightIsRelevant(category)) {
			return true;
		}
		if (largeStraightIsRelevant(category)) {
			return true;
		}
		if (yahtzeeIsRelevant(category)) {
			return true;
		}
		if (category == CHANCE) {
			return true;
		}
		return false;
	}

	private void formateResult(int category, int[] dice, int playerNum) {// this method calculates scores after every
																			// iteration
		int score = 0;
		if (category >= ONES && category <= SIXES) {
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == category) {
					score += dice[i];
				}
			}
		} else if (category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND) {
			for (int i = 0; i < N_DICE; i++) {
				score += dice[i];
			}
		} else if (category == FULL_HOUSE) {
			score = 25;
		} else if (category == SMALL_STRAIGHT) {
			score = 30;
		} else if (category == LARGE_STRAIGHT) {
			score = 40;
		} else if (category == YAHTZEE) {
			score = 50;
		} else if (category == CHANCE) {
			for (int i = 0; i < N_DICE; i++) {
				score += dice[i];
			}
		}
		scores[category - 1][playerNum] = score;
		display.updateScorecard(category, playerNum + 1, score);

	}

	private boolean firstSixCategoryIsRelevant(int category) { // checks relevance of first six categories with just
																// simply checking if any number on dices is same as
																// category num.
		if (category >= ONES && category <= SIXES) {
			for (int i = 0; i < N_DICE; i++) {
				if (dice[i] == category) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean threeOfAKindIsRelevant(int category) {// checks three of a kind category, if any number on dices is
															// repeated at least 3 times.
		if (category == THREE_OF_A_KIND) {
			for (int i = 1; i <= 6; i++) {
				if (countExactNumbers(dice, i) >= 3) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean fourOfAKindIsRelevant(int category) {// checks four of a kind category, if any number on dices is
															// repeated at least 4 times.
		if (category == FOUR_OF_A_KIND) {
			for (int i = 1; i <= 6; i++) {
				if (countExactNumbers(dice, i) >= 4) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean yahtzeeIsRelevant(int category) {// checks yahtzee category, if every number on dice is same.
		if (category == YAHTZEE) {
			for (int i = 1; i <= 6; i++) {
				if (countExactNumbers(dice, i) == 5) {
					return true;
				}
			}
		}
		return false;

	}

	private boolean fullhouseIsRelevant(int category) { // checks fullhouse category, if there is 2 and 3 same numbers
														// on the dices.
		if (category == FULL_HOUSE) {
			for (int i = 1; i <= 6; i++) {
				for (int j = 1; j <= 6; j++) {
					if (countExactNumbers(dice, i) == 3 && countExactNumbers(dice, j) == 2) {
						return true;
					}
				}
			}
		}
		return false;

	}

	private boolean smallStraightIsRelevant(int category) { // checks smallstraight category, if there is at least 4
															// following numbers on the dices.
		if (category == SMALL_STRAIGHT) {
			int count = 1;
			for (int i = 0; i < N_DICE - 1; i++) {
				if (dice[i + 1] == dice[i] + 1) {
					count++;
				}
			}
			if (count == 4 || count == 5) {
				return true;
			} else
				return false;
		}
		return false;
	}

	private boolean largeStraightIsRelevant(int category) {// checks largestraight category, if there 5 following
															// numbers on the dices.
		if (category == LARGE_STRAIGHT) {
			int count = 1;
			for (int i = 0; i < N_DICE - 1; i++) {
				if (dice[i + 1] == dice[i] + 1) {
					count++;
				}
			}
			if (count == 5) {
				return true;
			} else
				return false;
		}
		return false;
	}

	private boolean categoryIsUnused(int category, int playerNum) {// checks if category has been already used.
		if (scores[category - 1][playerNum] == -1) {
			return true;
		} else
			return false;

	}

	private void endGame() { // ends the game
		calculateTheEndingScores();
		decideTheWinner();
	}

	private void calculateTheEndingScores() { // calculates the ending scores, which should be written on the
												// upperscore, lowerscore, upperbonus categories.
		for (int playerNum = 0; playerNum < nPlayers; playerNum++) {
			int upperScore = 0;
			int lowerScore = 0;
			int bonusScore = 0;
			int total = 0;
			for (int i = 0; i <= (SIXES - 1); i++) {
				upperScore += scores[i][playerNum];
			}
			display.updateScorecard(UPPER_SCORE, playerNum + 1, upperScore);
			if (upperScore > 63) {
				bonusScore = upperScore + 35;
			} else
				bonusScore = 0;
			display.updateScorecard(UPPER_BONUS, playerNum + 1, bonusScore);
			for (int i = THREE_OF_A_KIND - 1; i <= (CHANCE - 1); i++) {
				lowerScore += scores[i][playerNum];
			}
			display.updateScorecard(LOWER_SCORE, playerNum + 1, lowerScore);
			total = upperScore + lowerScore + bonusScore;
			display.updateScorecard(TOTAL, playerNum + 1, total);
		}
	}

	private void decideTheWinner() { // checks total scores of each player and reveals the winner
		int maxScore = 0;
		int winnerPlayer = 0;
		for (int i = 1; i < nPlayers; i++) {
			if (scores[TOTAL - 1][i] > scores[TOTAL - 1][winnerPlayer]) {
				maxScore = scores[TOTAL - 1][i];
				winnerPlayer = i;
			}
		}
		display.printMessage("congrats, " + playerNames[winnerPlayer] + " you are the winner! with the maximum score: " + maxScore);

	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] dice;
	private int NROLLS = 2;
	private int[][] scores;

}
