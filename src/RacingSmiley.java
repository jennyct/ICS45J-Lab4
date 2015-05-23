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
	
	private int ticks;
	private int pixelsPerTick;
	public static final long TOTAL_LAPS = 4;
	
	private Random generator;
	
	public RacingSmiley(AnimatedSmiley orig, String name, Color nameColor) {
		super(orig);
		
		smileyName = name;
		smileyNameColor = nameColor;
		generator = new Random();
		currentLap = 0;
		currentDirection = 1;
		ticks = 0;
	}
	
	public RacingSmiley(RacingSmiley orig, String newName, Color newColor) {
		
		super(orig);
		
		smileyName = newName;
		smileyNameColor = newColor;
		generator = new Random();
		currentLap = 0;
		currentDirection = 1;
		ticks = 0;
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
}
