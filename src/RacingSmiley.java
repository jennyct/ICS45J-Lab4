import java.awt.Color;
import java.util.Random;

// RacingSmiley.java
// 
// ICS 45J : Lab Assignment 4

/**
 * Extended AnimatedSmiley that can perform in races
 * @author Stephen Em and Jenny Tang
 *
 */
public class RacingSmiley extends AnimatedSmiley implements RacingSmileyInterface {

	private String smileyName;
	private Color smileyNameColor;
	private int currentLap;
	private int currentDirection;
	
	private static Random generator = new Random();
	private int ticks;
	private int strategy;
	private int baseSpeed;
	private int speedAdjustment;

	public static final int TOTAL_LAPS = 10;
	public static final int MAX_MOVEMENT = 15;
	
	
	/**
	 * The default constructor for a RacingSmiley
	 * @param orig - animated smiley
	 * @param name - name of racing smiley
	 * @param nameColor - color of the name string
	 */
	public RacingSmiley(AnimatedSmiley orig, String name, Color nameColor) {
		super(orig,generator.nextInt(MAX_MOVEMENT)+1,0);
		
		smileyName = name;
		smileyNameColor = nameColor;
		currentLap = 0;
		currentDirection = 1;
		ticks = 0;
		baseSpeed = getCurrentXMovement();
		strategy = generateStrat();
		speedAdjustment = generator.nextInt(baseSpeed)/2;
	}
	
	/**
	 * The copy constructor for a RacingSmiley
	 * @param orig - racing smiley
	 * @param name - new name of racing smiley
	 * @param nameColor - new color of the name string
	 */
	public RacingSmiley(RacingSmiley orig, String newName, Color newColor) {
		
		super(orig,generator.nextInt(MAX_MOVEMENT)+1,0);
		
		smileyName = newName;
		smileyNameColor = newColor;
		currentLap = 0;
		currentDirection = 1;
		ticks = 0;
		baseSpeed = getCurrentXMovement();
		strategy = generateStrat();
		speedAdjustment = generator.nextInt(baseSpeed)/2;
	}
	
	/**
	 * Change whether the smiley faces left or right
	 */
	public void changeSmileyProfile() {
		changeEye();
		changeSmile();
	}
	
	/**
	 * Swap eye colors
	 */
	private void changeEye() {
		Color tempEyeColor = getLeftEye().getColor();
		getLeftEye().setColor(getRightEye().getColor());
		getRightEye().setColor(tempEyeColor);
	}
	
	/**
	 * Swap position of the smiley to be on the opposite edge
	 */
	private void changeSmile() {
		getSmile().translate((int)((currentDirection == 1) ? getFace().getXLength() : -getFace().getXLength()), 0);
	}
	
	/**
	 * Generate a strategy for the smiley
	 * @return an int corresponding to the strategy
	 * 0 - no change
	 * 1 - slow up on each lap
	 * 2 - speed up on each lap
	 */
	private int generateStrat() {
		switch(generator.nextInt(3)) {
		case 0:
			return 0;
		case 1:
			return 1;
		case 2:
			return 2;
		default:
			return -1;
		}
	}
	
	/**
	 * Determines if smiley has finished the race
	 * True if laps are equal to total laps of race, otherwise false
	 */
	@Override
	public boolean finishedRace() {
		return (currentLap == TOTAL_LAPS) ? true : false;
	}

	/**
	 * Move the racing smiley for one tick
	 */
	@Override
	public void raceForOneTick() {
		moveIt();
		ticks++;
	}

	@Override
	public int getTicks() {
		return ticks;
	}

	@Override
	public String getSmileyName() {
		return smileyName;
	}

	@Override
	public Color getSmileyNameColor() {
		return smileyNameColor;
	}

	@Override
	public int getLapsCompleted() {
		return currentLap;
	} 
	
	public int getCurrentDirection() {
		return currentDirection;
	}
	
	/**
	 * 
	 * @param direction - direction for smiley to move:
	 * 1 for right
	 * -1 for left
	 */
	public void setCurrentDirection(int direction) {
		currentDirection = direction;
	}
	
	/**
	 * Increase the lap by one
	 */
	public void incrementLap() {
		currentLap++;
	}
	
	public int getStrategy() {
		return strategy;
	}
	
	public int getBaseSpeed() {
		return baseSpeed;
	}
	
	public int getSpeedAdjustment() {
		return speedAdjustment;
	}
}
