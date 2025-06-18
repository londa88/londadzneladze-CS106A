
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	public void reset(int caseNum) {
		canvasStartingPos();
		toHangTheMan(caseNum);

	}

	public void canvasStartingPos() { // draws everything you need before playing on the canvas
		drawScaffold();
		drawBeam();
		drawRope();
	}

	private Object toHangTheMan(int caseNum) { // draws body parts one by one after losing guesses
		switch (caseNum) {
		case 0:
			return drawHead();
		case 1:
			return drawBody();
		case 2:
			return drawLeftArm();
		case 3:
			return drawRightArm();
		case 4:
			return drawLeftLeg();
		case 5:
			return drawRightLeg();
		case 6:
			return drawLeftFoot();
		case 7:
			return drawRightFoot();
		}
		return caseNum;

	}

	private void drawScaffold() { // draws scaffold
		int X1 = this.getWidth() / 2 - BEAM_LENGTH;
		int Y1 = this.getHeight() - SCAFFOLD_Y_OFFSET;
		int X2 = this.getWidth() / 2 - BEAM_LENGTH;
		int Y2 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET;
		GLine scaffold = new GLine(X1, Y1, X2, Y2);
		scaffold.setColor(Color.CYAN);
		add(scaffold);
	}

	private void drawBeam() { // draws beam
		int X1 = this.getWidth() / 2 - BEAM_LENGTH;
		int Y1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET;
		int X2 = this.getWidth() / 2;
		int Y2 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET;
		GLine beam = new GLine(X1, Y1, X2, Y2);
		beam.setColor(Color.CYAN);
		add(beam);
	}

	private void drawRope() { // draws rope
		int X1 = this.getWidth() / 2;
		int Y1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET;
		int X2 = this.getWidth() / 2;
		;
		int Y2 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH;
		GLine rope = new GLine(X1, Y1, X2, Y2);
		rope.setColor(Color.CYAN);
		add(rope);

	}

	private Object drawHead() { // draws head
		int X = this.getWidth() / 2 - HEAD_RADIUS;
		int Y = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH;
		GOval head = new GOval(X, Y, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		head.setColor(Color.CYAN);
		add(head);
		return head;

	}

	private Object drawBody() { // draws body
		int X1 = getWidth() / 2;
		int Y1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS;
		int X2 = getWidth() / 2;
		int Y2 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		GLine body = new GLine(X1, Y1, X2, Y2);
		body.setColor(Color.CYAN);
		add(body);
		return body;

	}

	private Object drawLeftArm() { // draws left arm
		int upperX1 = this.getWidth() / 2 - UPPER_ARM_LENGTH;
		int upperY1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ ARM_OFFSET_FROM_HEAD;
		int upperX2 = this.getHeight() / 2;
		int upperY2 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ ARM_OFFSET_FROM_HEAD;
		GLine upperleftarm = new GLine(upperX1, upperY1, upperX2, upperY2);
		upperleftarm.setColor(Color.CYAN);
		add(upperleftarm);
		int lowerX1 = this.getWidth() / 2 - UPPER_ARM_LENGTH;
		int lowerY1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ ARM_OFFSET_FROM_HEAD;
		int lowerX2 = this.getWidth() / 2 - UPPER_ARM_LENGTH;
		int lowerY2 = lowerY1 + LOWER_ARM_LENGTH;
		GLine lowerleftarm = new GLine(lowerX1, lowerY1, lowerX2, lowerY2);
		lowerleftarm.setColor(Color.CYAN);
		add(lowerleftarm);
		return lowerleftarm;

	}

	private Object drawRightArm() { // draws right arm
		int upperX1 = this.getWidth() / 2;
		int upperY1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ ARM_OFFSET_FROM_HEAD;
		int upperX2 = upperX1 + UPPER_ARM_LENGTH;
		int upperY2 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ ARM_OFFSET_FROM_HEAD;
		GLine upperrightarm = new GLine(upperX1, upperY1, upperX2, upperY2);
		upperrightarm.setColor(Color.CYAN);
		add(upperrightarm);
		int lowerX1 = upperX1 + UPPER_ARM_LENGTH;
		int lowerY1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ ARM_OFFSET_FROM_HEAD;
		int lowerX2 = upperX1 + UPPER_ARM_LENGTH;
		int lowerY2 = lowerY1 + LOWER_ARM_LENGTH;
		GLine lowerrightarm = new GLine(lowerX1, lowerY1, lowerX2, lowerY2);
		lowerrightarm.setColor(Color.CYAN);
		add(lowerrightarm);
		return lowerrightarm;

	}

	private Object drawLeftLeg() { // draws left leg
		int hipX1 = this.getWidth() / 2;
		int hipY1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ BODY_LENGTH;
		int hipX2 = this.getWidth() / 2 - HIP_WIDTH;
		int hipY2 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ BODY_LENGTH;
		GLine hip = new GLine(hipX1, hipY1, hipX2, hipY2);
		hip.setColor(Color.CYAN);
		add(hip);
		int legX1 = this.getWidth() / 2 - HIP_WIDTH;
		int legY1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ BODY_LENGTH;
		int legX2 = this.getWidth() / 2 - HIP_WIDTH;
		int legY2 = legY1 + LEG_LENGTH;
		GLine leg = new GLine(legX1, legY1, legX2, legY2);
		leg.setColor(Color.CYAN);
		add(leg);
		return leg;

	}

	private Object drawRightLeg() { // draws right leg
		int hipX1 = this.getWidth() / 2;
		int hipY1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ BODY_LENGTH;
		int hipX2 = this.getWidth() / 2 + HIP_WIDTH;
		int hipY2 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ BODY_LENGTH;
		GLine hip = new GLine(hipX1, hipY1, hipX2, hipY2);
		hip.setColor(Color.CYAN);
		add(hip);
		int legX1 = this.getWidth() / 2 + HIP_WIDTH;
		int legY1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ BODY_LENGTH;
		int legX2 = this.getWidth() / 2 + HIP_WIDTH;
		int legY2 = legY1 + LEG_LENGTH;
		GLine leg = new GLine(legX1, legY1, legX2, legY2);
		leg.setColor(Color.CYAN);
		add(leg);
		return leg;

	}

	private Object drawLeftFoot() {// draws left foot
		int footX1 = this.getWidth() / 2 - HIP_WIDTH;
		int footY1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ BODY_LENGTH + LEG_LENGTH;
		int footX2 = footX1 - FOOT_LENGTH;
		int footY2 = footY1;
		GLine leftfoot = new GLine(footX1, footY1, footX2, footY2);
		leftfoot.setColor(Color.CYAN);
		add(leftfoot);
		return leftfoot;

	}

	private Object drawRightFoot() { // draws right foot
		int footX1 = this.getWidth() / 2 + HIP_WIDTH;
		int footY1 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ BODY_LENGTH + LEG_LENGTH;
		int footX2 = footX1 + FOOT_LENGTH;
		int footY2 = this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET + ROPE_LENGTH + 2 * HEAD_RADIUS
				+ BODY_LENGTH + LEG_LENGTH;
		GLine rightfoot = new GLine(footX1, footY1, footX2, footY2);
		rightfoot.setColor(Color.CYAN);
		add(rightfoot);
		return rightfoot;

	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String blankWord) { // formates string under the scaffold
		if (blankSpace != null) {
			remove(blankSpace);
		}
		blankSpace = new GLabel("", this.getWidth() / 2 - BEAM_LENGTH, this.getHeight() - BLANKSPACE_Y_OFFSET);
		add(blankSpace);
		blankSpace.setLabel(blankWord);
		blankSpace.setFont("-30");
		blankSpace.setColor(Color.CYAN);

	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user. Calling
	 * this method causes the next body part to appear on the scaffold and adds the
	 * letter to the list of incorrect guesses that appears at the bottom of the
	 * window.
	 */
	public void noteIncorrectGuess(String letter) { // formates string, which shows already used wrong letters
		if (wrongChars != null) {
			remove(wrongChars);
		}
		if (!incorrectGuesses.contains(letter)) {
			incorrectGuesses += letter;
		}
		wrongChars = new GLabel("", this.getWidth() / 2 - BEAM_LENGTH, this.getHeight() - WRONGCHARS_Y_OFFSET);
		add(wrongChars);
		wrongChars.setLabel(incorrectGuesses);
		wrongChars.setFont("-20");
		wrongChars.setColor(Color.WHITE);

	}

	public void yourScore(int score) { // shows your score on the canvas
		if (yourScore != null) {
			remove(yourScore);
		}
		String yourscore = "Your score is:" + Integer.toString(score);
		yourScore = new GLabel("", 10, this.getHeight() - SCAFFOLD_HEIGHT - SCAFFOLD_Y_OFFSET - 10);
		add(yourScore);
		yourScore.setLabel(yourscore);
		yourScore.setFont("-20");
		yourScore.setColor(Color.WHITE);

	}

	public void restart() { // restarts the string, which indicates already written wrong numbers.
		incorrectGuesses = "";

	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_Y_OFFSET = 80;
	private static final int BLANKSPACE_Y_OFFSET = 50;
	private static final int WRONGCHARS_Y_OFFSET = 20;
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private GLabel blankSpace;
	private String incorrectGuesses = "";
	private GLabel wrongChars;
	private GLabel yourScore;
}
