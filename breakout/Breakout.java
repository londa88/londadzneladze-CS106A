import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.sun.media.jfxmedia.AudioClip;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.ConsoleProgram;
import acm.program.GraphicsProgram;
import acm.util.MediaTools;
import acm.util.RandomGenerator;
public class Breakout extends GraphicsProgram{
/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;
 
/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private static final int DISTANCE_FROM_SIDE=(APPLICATION_WIDTH-NBRICKS_PER_ROW*BRICK_WIDTH-(NBRICKS_PER_ROW-1)*BRICK_SEP)/2;
    private GRect paddle;
	private GOval ball;
	private RandomGenerator rgen=RandomGenerator.getInstance();
	private double vy;
	private double vx;
	private GRect brick;
	java.applet.AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	int NUM_OF_BRICKS=NBRICKS_PER_ROW*NBRICK_ROWS;
	int  livesLeft=NTURNS;
/** Runs the Breakout program. */
	
   public void init() {
	   addMouseListeners();
	  
   }
		
	
	
	public void run() {
     	designTheGame();
        waitForClick();
		playTheGame();
	}
     	private void designTheGame() { //this method designs the canvas
     		setBackground(Color.PINK);
			toArrangeBricks();
		    designThePaddle();
			designTheBall();
			
		}
		private void toArrangeBricks() { // this method adds bricks on the canvas
		for(int i=0; i<NBRICKS_PER_ROW;i++) {
			for(int j=0; j<NBRICK_ROWS;j++) {
				int x=DISTANCE_FROM_SIDE+i*(BRICK_SEP+BRICK_WIDTH);
				int y=BRICK_Y_OFFSET+j*(BRICK_SEP+BRICK_HEIGHT);
				brick=new GRect(x,y, BRICK_WIDTH,BRICK_HEIGHT);
				add(brick);
				brick.setFilled(true);
                if(j<=1) {
                	brick.setColor(Color.RED);
                }
                if(j<=3&&j>1) {
                	brick.setColor(Color.ORANGE);
                }
                if(j<=5&&j>3) {
                	brick.setColor(Color.YELLOW);
                }
                if(j<=7&&j>5) {
                	brick.setColor(Color.GREEN);
                }
                if(j<=9&&j>7) {
                	brick.setColor(Color.CYAN);
                	
                }
			}
		}
		

	}
	    private void designThePaddle() { //this method adds a paddle on the canvas
	    int paddleX=(getWidth()-PADDLE_WIDTH)/2;
	    int paddleY=getHeight()-PADDLE_Y_OFFSET-PADDLE_HEIGHT;
	    paddle=new GRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
		add(paddle);
		paddle.setFilled(true);
		}
	    public void mouseMoved( MouseEvent e ){
			double x=e.getX();
			double y=getHeight()-PADDLE_HEIGHT-PADDLE_Y_OFFSET;
			 if(x>=0&&x<=getWidth()-PADDLE_WIDTH) {
			paddle.setLocation(x,y);
			 }
			
			}

		private void designTheBall() { // this method adds a ball on the canvas
		    int ballX=getWidth()/2-BALL_RADIUS;
	    	int ballY=getHeight()/2-BALL_RADIUS;
		    ball=new GOval(ballX, ballY,2*BALL_RADIUS, 2*BALL_RADIUS);
			add(ball);
			ball.setFilled(true);
		}
		private void moveTheBall() {
			ball.move(vx, vy);
			pause(5);
		}
		

     private boolean ballCrushesTheWalls() { // this method returns the coordinates of the ball, which is out of the walls
	      return ball.getX()>=getWidth()-2*BALL_RADIUS||ball.getX()<0;
	
}
    private boolean ballCrushesTheFloor() {// this method returns the Y coordinate of the ball, which is outside of the canvas
	    return ball.getY()>=(getHeight()-2*BALL_RADIUS);
}
    private boolean ballCrushesTheCeiling() { // this method returns the Y coordinate of the ball, which is also outside the game space
	   return ball.getY()<0;
} 

    private GObject getCollidingObject() { //these are the coordinates of the objects, which will be touched by the ball
	 GObject object1=getElementAt(ball.getX(),ball.getY());
     GObject object2=getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY());
	 GObject object3=getElementAt(ball.getX(),ball.getY()+2*BALL_RADIUS);
	 GObject object4=getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY()+2*BALL_RADIUS);
	 GObject object5=getElementAt(ball.getX()+BALL_RADIUS, ball.getY());
	 GObject object6=getElementAt(ball.getX(), ball.getY()+BALL_RADIUS);
	 GObject object7=getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+2*BALL_RADIUS);
	 GObject object8=getElementAt(ball.getX()+2*BALL_RADIUS, ball.getY()+BALL_RADIUS);
	if(object1!=null) {
		return object1;
	}
	else if(object2!=null) {
		return object2;
	}
	else if(object3!=null) {
		return object3;
	}
	
	else if(object4!=null) {
		return object4;
	}
	else if(object5!=null) {
		return object5;
	}
	else if(object6!=null) {
		return object6;
	}
	else if(object7!=null) {
	return	object7;
	}
	else if(object8!=null) {
		return object8;
	}
	else {
		return null;
	}
   }


private void playTheGame() {//this is the process of the game, firstly ball starts moving, than crushing the objects and removing them.
	 vx=rgen.nextDouble(1.0,1.5);
	 vy=1;
	 if (rgen.nextBoolean(0.5)) vx = -vx;
	
	while(true) {
		moveTheBall();
		GObject collider=getCollidingObject();
		if(ballCrushesTheWalls()) {
			vx*=-1;
		}
		if(ballCrushesTheFloor()&&livesLeft!=0) { // if ball will be outside the game space, the game will be done and the number of lives will be reduced by 1.
			remove(ball);
			livesLeft-=1;
			 if(livesLeft==0) { // if the number of the lives equals 0, the game is done and the relevant label shows on the canvas.
					removeAll();
					GLabel label1=new GLabel("YOU HAVE LOST", getWidth()/2-50, getHeight()/2+50);
					add(label1);
				    waitForClick(); // but after one click, you cant start again.
		        	remove(label1);
					designTheGame();
				    livesLeft=NTURNS;
			    	playTheGame();
					
					
				}
			GLabel label=new GLabel("YOU HAVE LEFT: "+ livesLeft  +" tries",getWidth()/2-50, getHeight()/2+50); // this label shows you the nnumber of chances you have left.
			add(label);
			waitForClick();
			add(ball,getWidth()/2-BALL_RADIUS,getHeight()/2-BALL_RADIUS);
		    remove(label);
	    	playTheGame();

		
		}
		
		if(ballCrushesTheCeiling()) { // if ball crushes the ceiling, it should change the direction with changing the vy.
			vy*=-1;
		}
		if(collider==paddle&&collider!=ball) { // if ball crushes the paddle, it should also change the direction.
			vy*=-1;
			bounceClip.play();
		}
		else if(collider!=null&&collider!=ball) {// if ball crushes bricks, they should be removed
			remove(collider);
			bounceClip.play();
			vy*=-1;
			NUM_OF_BRICKS-=1;
			
		}
		if(NUM_OF_BRICKS==0) { // if all the bricks will be removed, then it is a win, relevant label shows up again.
			ball.setVisible(false); 
			brick.setVisible(false);
			paddle.setVisible(false);
			GLabel label2=new GLabel("YOU HAVE WON", getWidth()/2-50, getHeight()/2+50);
			add(label2);
			waitForClick(); //but after the click you can start game again.
			remove(label2);
			designTheGame();
		    livesLeft=NTURNS;
	    	playTheGame();		
		}
		
		if(ball.getX()>paddle.getX()&&ball.getX()<PADDLE_WIDTH+paddle.getX()-2*BALL_RADIUS&&paddle.getY()-2*BALL_RADIUS<ball.getY()&&ball.getY()<paddle.getY()+PADDLE_HEIGHT/2) { //if ball is stuck in the paddle, it is replaced above the paddle.
			ball.setLocation(ball.getX(), paddle.getY()-2*BALL_RADIUS);
		
		}
		if(ball.getX()>paddle.getX()&&ball.getX()<PADDLE_WIDTH+paddle.getX()-2*BALL_RADIUS&&ball.getY()>=paddle.getY()+PADDLE_HEIGHT/2&&ball.getY()<paddle.getY()+PADDLE_HEIGHT) { //if the ball is stuck in the paddle, it is replaced under the paddle.
		ball.setLocation(ball.getX(), paddle.getY()+PADDLE_HEIGHT);
		
		}
		
		if(ball.getY()<paddle.getY()+PADDLE_HEIGHT+2*BALL_RADIUS&&ball.getY()>paddle.getY()-2*BALL_RADIUS&&ball.getX()==paddle.getX()-2*BALL_RADIUS) { // if ball hits the side of the paddle, it changes the direction.
		vx=-vx;
		
		}
		if(ball.getY()<paddle.getY()+PADDLE_HEIGHT+2*BALL_RADIUS&&ball.getY()>paddle.getY()-2*BALL_RADIUS&&ball.getX()==paddle.getX()+PADDLE_WIDTH) { // if ball hits the side of the paddle, it changes the direction.
		vx=-vx;
		
		}
	}
	
}
}




 

	
	
	
	
	
	
	
