import java.awt.Color;
import java.util.Random;

// RacingSmiley.java
// 
// ICS 45J : Lab Assignment 4

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
	public static final int MAX_MOVEMENT = 10;
	
	
	public RacingSmiley(AnimatedSmiley orig, String name, Color nameColor) {
		super(orig,generator.nextInt(MAX_MOVEMENT)+1,0);
		
		smileyName = name;
		smileyNameColor = nameColor;
		currentLap = 0;
		currentDirection = 1;
		ticks = 0;
		baseSpeed = getCurrentXMovement();
		strategy = generateStrat();
		speedAdjustment = generator.nextInt(baseSpeed)+3;
		System.out.println(smileyName + " strategy is " + strategy);
	}
	
	public RacingSmiley(RacingSmiley orig, String newName, Color newColor) {
		
		super(orig,generator.nextInt(MAX_MOVEMENT)+1,0);
		
		smileyName = newName;
		smileyNameColor = newColor;
		currentLap = 0;
		currentDirection = 1;
		ticks = 0;
		baseSpeed = getCurrentXMovement();
		strategy = generateStrat();
		speedAdjustment = generator.nextInt(baseSpeed)+3;
		System.out.println(smileyName + " strategy is " + strategy);
	}
	
	public void changeSmileyProfile() {
		changeEye();
		changeSmile();
	}
	
	@Override
	public boolean finishedRace() {
		return (currentLap == TOTAL_LAPS) ? true : false;
	}

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
	
	public void setCurrentDirection(int direction) {
		currentDirection = direction;
	}
	
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
	
	private void changeEye() {
		Color tempEyeColor = getLeftEye().getColor();
		getLeftEye().setColor(getRightEye().getColor());
		getRightEye().setColor(tempEyeColor);
	}
	
	private void changeSmile() {
		getSmile().translate((int)((currentDirection == 1) ? getFace().getXLength() : -getFace().getXLength()), 0);
	}
	
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
}
